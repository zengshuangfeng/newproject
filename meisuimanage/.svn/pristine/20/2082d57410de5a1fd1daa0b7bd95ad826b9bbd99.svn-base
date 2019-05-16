package com.meisui.manage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.forman.foundation.library.DateUtils;
import com.forman.foundation.library.RedisUtil;
import com.forman.log4j.Log4jHandel;
import com.meisui.manage.dao.IadminDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ipartner_RecordDao;
import com.meisui.manage.dao.IsquareDao;
import com.meisui.manage.dao.IsquareFeeRecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Chat;
import com.meisui.manage.entity.Partner_Record;
import com.meisui.manage.entity.Square;
import com.meisui.manage.entity.SquareFeeRecord;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.qiniu.api.Client;
import com.meisui.manage.qiniu.api.Hub;
import com.meisui.manage.qiniu.api.Stream;
import com.meisui.manage.qiniu.api.Stream.SnapshotOptions;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.meisui.manage.utils.RedisUtilWrapper;

/**
 * <p>文件名称：SquareService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Service
public class SquareService extends HessianService {
	private static Logger log = Logger.getLogger(SquareService.class.getClass());
	@Autowired
	private IsquareDao isquareDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Ipartner_RecordDao ipartner_RecordDao;
	@Autowired
	private IadminDao iadminDao;
	@Autowired 
	private IsquareFeeRecordDao isquare_Fee_RecordDao;
	private static Hub hubv2;
	/**
	 * 
	 * <p>功能描述：直播间管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 上午11:02:26</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getSquareList(long uid, String nickname, int type, int page, Model model)
	{
		try {		
			Date date = new Date();
			List<Square> squareList = isquareDao.getSquareList(uid, nickname, type, date, (page-1)*20, 20);
			for (Square _square : squareList) {
				if(!StringUtils.isBlank(_square.getVirtual_time())&&AuthUtil.formatStringToDate(_square.getVirtual_time()).after(date))
				{
					_square.setHots(_square.getHots()+_square.getVirtual_add_hots());
				}
				else {
					_square.setVirtual_add_hots(0);
				}
				_square.setCreate_time(DateUtils.sdf2.format(new Date(_square.getTimes())));

				if (_square.getIs_secret() == 1) {
					_square.setType(2);//私密播
				}
			}
			int totalRecord = isquareDao.getSquareCount(uid, nickname, type);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("square/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("squareList", squareList);
			model.addAttribute("activeUrl", "square");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("type", type);
		} catch (Exception e) {
			log.error("直播间管理列表", e);
		}
		return "square/list";
	}
	/**
	 * 
	 * <p>功能描述：直播间修改虚拟热度</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 上午11:31:18</p>
	 *
	 * @param id 广场直播间表id
	 * @param virtual_add_hots 增加虚拟热度值
	 * @param virtual_time 虚拟热度持续时间至
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateSquareVirtualHots(int id, int virtual_add_hots, Date virtual_time, String w_name, HttpServletRequest request)
	{
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			int result = isquareDao.updateSquareVirtualHots(id, virtual_add_hots, virtual_time, w_name);
			imanage_RecordDao.insertManageRecord(w_name, "直播间修改虚拟热度", "t_square", id, IPUtil.getIp(request), new Date());
			return result;
		} catch (Exception e) {
			log.error("直播间修改虚拟热度", e);
		}
		return 0;
	}

	/**
	 * 
	 * <p>功能描述：更新VIP</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年9月19日 下午14:12:18</p>
	 *
	 * @param f_uuid 主播房间号
	 * @param is_vip 是否VIP
	 * @return
	 */	
	public int updateSquareVIP(Long uid,int is_vip,String w_name,HttpServletRequest request){
		int result = 1;

		try {
			result = isquareDao.updateSquareVIP(uid, is_vip);
			User_Anchor user_Anchor  =  iuserDao.getUserAnchor(uid);
			user_Anchor.setIs_vip(is_vip);
			if (result==1) {
				result = iuserDao.updateUserAnchor(user_Anchor);	
			}		
			imanage_RecordDao.insertManageRecord(w_name, "更新是否VIP,is_vip:"+is_vip, "t_square", 0, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			log.error("更新VIP异常", e);
		}

		return result;
	}


	public int closeAnchor(String f_uuid, String w_name, HttpServletRequest request)
	{

		/*try {
			Stream stream = hubv2.get(f_uuid);

			stream.disable();
			log.info("关闭房间"+f_uuid+"流状态:"+stream.info());
			stream = hubv2.get(f_uuid);
			log.info("关闭房间"+f_uuid+"流状态:"+stream.info());
			stream.disable();
		} catch (Exception e) {
			log.error("关闭房间七牛流出错", e);
		}*/
		try {
			if (!"".equals(f_uuid)) {			
					w_name = String.valueOf(request.getAttribute("w_name"));
					closeroom(f_uuid);
		
					Partner_Record record = new Partner_Record();
					long uid = iuserDao.getUserIdWithF_uuid(f_uuid);
					User user = iuserDao.getUser(uid);
					
					record.setF_uuid(Long.parseLong(f_uuid));
					record.setUid(uid);
					record.setAnchor_name(user.getNickname());
					record.setReason("直播间管理踢出");
					record.setPic("");
					record.setCreate_time(new Date());
					record.setW_name(w_name);;
					record.setType(1);
					
					ipartner_RecordDao.insertPartnerRecord(record);
					imanage_RecordDao.insertManageRecord(w_name, "踢出"+"“"+"f_uuid："+f_uuid+"”"+"主播", "t_square", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
		return 1;
	}
	
	public int closeAnchor2(String f_uuid, String w_name, HttpServletRequest request)
	{

		/*try {
			Stream stream = hubv2.get(f_uuid);

			stream.disable();
			log.info("关闭房间"+f_uuid+"流状态:"+stream.info());
			stream = hubv2.get(f_uuid);
			log.info("关闭房间"+f_uuid+"流状态:"+stream.info());
			stream.disable();
		} catch (Exception e) {
			log.error("关闭房间七牛流出错", e);
		}*/
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			closeroom(f_uuid);
			imanage_RecordDao.insertManageRecord(w_name, "踢出"+"“"+"f_uuid："+f_uuid+"”"+"主播", "t_square", 0, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
		return 1;
	}
	@SuppressWarnings("rawtypes")
	public String getSquareList2(long f_uuid, int page, Model model)
	{
		try {		
			Date date = new Date();
			List<Square> squareList = isquareDao.getSquareList2(f_uuid, date, (page-1)*18, 18);
			for (Square _square : squareList) {
				_square.setCreate_time(DateUtils.sdf2.format(new Date(_square.getTimes())));
				User_Anchor user_Anchor = iuserDao.getUserAnchor(Long.parseLong(_square.getUid()));
				if(user_Anchor!=null)
				{
					_square.setIs_trial(user_Anchor.getIs_trial());
				}
			}
			setWatchCountByRedis(squareList);
			//速率
			String is_ucloud = PropertyUtil.getValue("is_ucloud");
			if(StringUtils.isBlank(is_ucloud)||is_ucloud.equals("null")||is_ucloud.equals("0"))
			{
				try{
					setHub();
					for(Square _square : squareList){
						Stream stream = hubv2.get(String.valueOf(_square.getF_uuid()));

						if(stream!=null)
						{
							try{
								_square.setKbs(stream.liveStatus().bps/1024/8);
							}catch(Exception e){
								_square.setKbs(0d);
								//e.printStackTrace();
							}

						}

					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			List<Square> allSquareList = isquareDao.getAllSquareList();
			int totalRecord = isquareDao.getSquareCount2(f_uuid);
			int totalUser = 0;
			Set<String> domainList = RedisUtil.GetWhereKeys(2, "*");
			for (String domainModel : domainList) {
				String all_f_uuids = RedisUtil.Gethget(2, domainModel, "all_f_uuids");
				if(!StringUtils.isBlank(all_f_uuids)&&!all_f_uuids.equals("null"))
				{
					try {
						JSONArray jsonArray = JSONArray.fromObject(all_f_uuids);
						for (int i=0;i<jsonArray.size();i++) {
							Iterator iterator = jsonArray.getJSONObject(i).keys();
							while(iterator.hasNext())
							{
								String key = iterator.next().toString();
								int num = jsonArray.getJSONObject(i).getInt(key);
								totalUser += num;
							}
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			PageUtil pageUtil = new PageUtil(18, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("square2/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("squareList", squareList);
			model.addAttribute("allSquareList", allSquareList);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("totalUser", totalUser);
			model.addAttribute("activeUrl", "square2");
			model.addAttribute("rtmpPath", PropertyUtil.getValue("rtmpPath"));
		} catch (Exception e) {
			log.error("直播间监控列表", e);
		}
		return "square/list2";
	}
	@SuppressWarnings("rawtypes")
	public String getWatchCount(Long[] f_uuid_arr) {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultObject = new JSONObject();
		try {
			List<Square> squareList =  new ArrayList<Square>();
			for(long f_uuid : f_uuid_arr)
			{
				Square square = new Square();
				square.setF_uuid(f_uuid);
				squareList.add(square);
			}
			setWatchCountByRedis(squareList);
			//速率
			String is_ucloud = PropertyUtil.getValue("is_ucloud");
			if(StringUtils.isBlank(is_ucloud)||is_ucloud.equals("null")||is_ucloud.equals("0"))
			{
				try{
					setHub();
					for(Square _square : squareList){
						Stream stream = hubv2.get(String.valueOf(_square.getF_uuid()));
						if(stream!=null)
						{
							try{
								_square.setKbs(stream.liveStatus().bps/1024/8);
							}catch(Exception e){
								_square.setKbs(0d);
								//e.printStackTrace();
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			for(Square _square : squareList){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("f_uuid", _square.getF_uuid());
				jsonObject.put("watch_count", _square.getWatch_count());
				jsonObject.put("real_count", _square.getReal_count());
				jsonObject.put("kbs", _square.getKbs());
				jsonArray.add(jsonObject);
			}

			int totalRecord = isquareDao.getSquareCount2(0L);
			int totalUser = 0;
			Set<String> domainList = RedisUtil.GetWhereKeys(2, "*");
			for (String domainModel : domainList) {
				String all_f_uuids = RedisUtil.Gethget(2, domainModel, "all_f_uuids");
				if(!StringUtils.isBlank(all_f_uuids)&&!all_f_uuids.equals("null"))
				{
					try {
						JSONArray jsonArray2 = JSONArray.fromObject(all_f_uuids);
						for (int i=0;i<jsonArray2.size();i++) {
							Iterator iterator = jsonArray2.getJSONObject(i).keys();
							while(iterator.hasNext())
							{
								String key = iterator.next().toString();
								int num = jsonArray2.getJSONObject(i).getInt(key);
								totalUser += num;
							}
						}

					} catch (Exception e) {
					}
				}
			}
			resultObject.put("d", jsonArray);
			resultObject.put("totalRecord", totalRecord);
			resultObject.put("totalUser", totalUser);
		} catch (Exception e) {
			log.error("获取主播在线数和速率", e);
		}
		return resultObject.toString();
	}
	public String getWatchCountFromFraming(Long[] f_uuid_arr) {
		JSONArray jsonArray = new JSONArray();
		try {
			List<Square> squareList =  new ArrayList<Square>();
			for(long f_uuid : f_uuid_arr)
			{
				Square square = new Square();
				square.setF_uuid(f_uuid);
				squareList.add(square);
			}
			setWatchCountByRedis(squareList);
			//速率
			String is_ucloud = PropertyUtil.getValue("is_ucloud");
			if(StringUtils.isBlank(is_ucloud)||is_ucloud.equals("null")||is_ucloud.equals("0"))
			{
				try{
					setHub();
					for(Square _square : squareList){
						Stream stream = hubv2.get(String.valueOf(_square.getF_uuid()));
						if(stream!=null)
						{
							SnapshotOptions opts = new SnapshotOptions(stream.getKey()+System.currentTimeMillis()+".jpg", 0, "jpg");
							String imgUrl= PropertyUtil.getValue("video_url")+ stream.snapshot(opts);//直播截屏
							_square.setAnchor_cover(imgUrl);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			for(Square _square : squareList){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("f_uuid", _square.getF_uuid());
				jsonObject.put("watch_count", _square.getWatch_count());
				jsonObject.put("real_count", _square.getReal_count());
				jsonObject.put("anchor_cover", _square.getAnchor_cover());
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			log.error("获取主播在线数和速率", e);
		}
		return jsonArray.toString();
	}
	@SuppressWarnings("rawtypes")
	public void setWatchCountByRedis(List<Square> list){
		try {
			Set<String> domainList = RedisUtil.GetWhereKeys(2, "*");

			for (String domainModel : domainList) {
				String all_f_uuids = RedisUtil.Gethget(2, domainModel, "all_f_uuids");
				if(!StringUtils.isBlank(all_f_uuids)&&!all_f_uuids.equals("null"))
				{
					try {
						JSONArray jsonArray = JSONArray.fromObject(all_f_uuids);
						for (int i=0;i<jsonArray.size();i++) {
							Iterator iterator = jsonArray.getJSONObject(i).keys();
							while(iterator.hasNext())
							{
								String key = iterator.next().toString();
								String f_uuid = key.replace("主播房间号:", "");
								int num = jsonArray.getJSONObject(i).getInt(key);

								for(Square square:list){
									if (Long.parseLong(f_uuid)==square.getF_uuid()) {
										square.setReal_count(num);
										square.setServerIP(domainModel);
									}		
								}	
							}
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}		

			for(Square square:list){
				long count= RedisUtil.GetZLen(3, "uuid_vest"+square.getF_uuid());
				square.setWatch_count((int)count);
			}
		}catch(Exception e){
			log.error("redis读取错误:"+e);
		}
	}

	@SuppressWarnings("rawtypes")
	public String getOnlineNum(int page, Model model)
	{
		try {
			Set<String> domainList = RedisUtil.GetWhereKeys(2, "*");
			Map<String, Integer> numMap = new HashMap<String, Integer>();
			for (String domainModel : domainList) {
				String all_f_uuids = RedisUtil.Gethget(2, domainModel, "all_f_uuids");
				if(!StringUtils.isBlank(all_f_uuids)&&!all_f_uuids.equals("null"))
				{
					try {
						//System.out.println(all_f_uuids);
						JSONArray jsonArray = JSONArray.fromObject(all_f_uuids);
						for (int i=0;i<jsonArray.size();i++) {
							Iterator iterator = jsonArray.getJSONObject(i).keys();
							while(iterator.hasNext())
							{
								String key = iterator.next().toString();
								String f_uuid = key.replace("主播房间号:", "");
								int num = jsonArray.getJSONObject(i).getInt(key);
								if(!numMap.containsKey(f_uuid))
									numMap.put(f_uuid, num);
								else {
									if(num>0)
									{
										numMap.put(f_uuid, num);
									}
								}
							}
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			Date date = new Date();
			int totalWatchCount = 0;
			Iterator iter = numMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				totalWatchCount += Integer.parseInt(entry.getValue().toString());
			}
			List<Square> squareList = isquareDao.getSquareList(0L, "", -1, date, (page-1)*20, 20);
			for (Square _square : squareList) {
				Iterator iter2 = numMap.entrySet().iterator();
				while (iter2.hasNext()) {
					Map.Entry entry = (Map.Entry) iter2.next();
					if(String.valueOf(_square.getF_uuid()).equals(entry.getKey().toString()))
					{
						_square.setWatch_count(Integer.parseInt(entry.getValue().toString()));
					}
				}
			}
			int totalRecord = isquareDao.getSquareCount(0L, "", -1);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchoronline/list");
			model.addAttribute("totalWatchCount", totalWatchCount);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("squareList", squareList);
			model.addAttribute("activeUrl", "anchoronline");
		} catch (Exception e) {
			log.error("获取主播房间在线人数列表异常"+e);
		}
		return "square/onlinelist";
	}
	public String getWaringCount(long uid) {
		JSONObject json=new JSONObject();
		try{
			Integer count = ipartner_RecordDao.getWaringCount(uid,20);
			json.put("code", 0);
			json.put("msg",count);
		}catch(Exception e){
			log.error("获取20分钟内警告次数异常"+e);
			json.put("code", -1);
			json.put("msg","获取20分钟内警告次数异常");
		}
		return json.toString();
	}
	public String getSnapshotByStreamKey(String streamKey) {
		JSONObject json=new JSONObject();
		try{
			String is_ucloud = PropertyUtil.getValue("is_ucloud");
			if(StringUtils.isBlank(is_ucloud)||is_ucloud.equals("null")||is_ucloud.equals("0"))
			{
				if(StringUtils.isBlank(streamKey)){
					json.put("code", 0);
					json.put("msg", "主播不在线");
					return json.toString();
				}
				setHub();
				Stream stream = hubv2.get(streamKey);
				if(stream!=null)
				{
					SnapshotOptions opts = new SnapshotOptions(stream.getKey()+System.currentTimeMillis()+".jpg", 0, "jpg");
					String imgUrl= PropertyUtil.getValue("video_url")+ stream.snapshot(opts);//直播截屏
					json.put("code", 1);
					json.put("msg",imgUrl);
					return json.toString();
				}
			}
			json.put("code", 1);
			json.put("msg", "");
		}catch(Exception e){
			log.error("获取分帧循环异常"+e);
			json.put("code", 1);
			json.put("msg", "");
		}
		return json.toString();
	}
	public String save(int id, int type, long uid, long f_uuid, String anchor_name, String reason, String pic, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			int second = 30;
			if(type==0&&id==0)
			{
				Partner_Record lastPartner_Record = ipartner_RecordDao.getLastWaring(uid, f_uuid);
				if(lastPartner_Record!=null)
				{
					if(System.currentTimeMillis()-AuthUtil.formatStringToDate(lastPartner_Record.getCreate_time()).getTime()<second*1000)
					{
						jsonObject.put("code", 0);
						jsonObject.put("msg", "警告太频繁，请稍后再试");
						return jsonObject.toString();
					}
				}
			}
			w_name = String.valueOf(request.getAttribute("w_name"));
			Partner_Record partner_Record = new Partner_Record();
			partner_Record.setType(type);
			partner_Record.setUid(uid);
			partner_Record.setF_uuid(f_uuid);
			partner_Record.setAnchor_name(anchor_name);
			partner_Record.setReason(reason);
			partner_Record.setPic(pic);
			partner_Record.setW_name(w_name);
			Date date = new Date();
			int result = 0;
			if(id==0)
			{
				partner_Record.setCreate_time(date);
				result = ipartner_RecordDao.insertPartnerRecord(partner_Record);
				if(result>0)
				{
					if(type==0)
					{
						String token = iuserDao.getUserInfoExtraToken(uid);
						adminJinGao(String.valueOf(f_uuid), token, "警告", reason, String.valueOf(second));
					}
					else {
						closeroom(String.valueOf(f_uuid));
					}
				}
				imanage_RecordDao.insertManageRecord(w_name, type==0?"添加主播警告记录":"添加主播关闭直播记录", "t_partner_record", id, IPUtil.getIp(request), new Date());
			}
			else {
				partner_Record.setId(id);
				result = ipartner_RecordDao.updatePartnerRecord(partner_Record);
				imanage_RecordDao.insertManageRecord(w_name, type==0?"更新主播警告记录":"更新主播关闭直播记录", "t_partner_record", id, IPUtil.getIp(request), new Date());
			}
			if(result>0)
			{
				jsonObject.put("code", 1);
				jsonObject.put("msg", "操作成功");
				return jsonObject.toString();
			}
		} catch (Exception e) {
			log.error("保存主播警告记录/保存主播关闭直播记录", e);
		}
		jsonObject.put("code", 0);
		jsonObject.put("msg", "操作失败");
		return jsonObject.toString();
	}
	public String getSquareJson(int page)
	{
		JSONArray jsonArray = new JSONArray();
		try {
			Date date = new Date();
			List<Square> squareList = isquareDao.getSquareList2(0L, date, (page-1)*18, 18);
			for (Square _square : squareList) {
				_square.setCreate_time(DateUtils.sdf2.format(new Date(_square.getTimes())));
				User_Anchor user_Anchor = iuserDao.getUserAnchor(Long.parseLong(_square.getUid()));
				if(user_Anchor!=null)
				{
					_square.setIs_trial(user_Anchor.getIs_trial());
				}
			}
			setWatchCountByRedis(squareList);
			//速率
			String is_ucloud = PropertyUtil.getValue("is_ucloud");
			if(StringUtils.isBlank(is_ucloud)||is_ucloud.equals("null")||is_ucloud.equals("0"))
			{
				try{
					setHub();
					for(Square _square : squareList){
						Stream stream = hubv2.get(String.valueOf(_square.getF_uuid()));
						if(stream!=null)
						{
							try{
								_square.setKbs(stream.liveStatus().bps/1024/8);
							}catch(Exception e){
								_square.setKbs(0d);
								//e.printStackTrace();
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			jsonArray = JSONArray.fromObject(squareList);
		} catch (Exception e) {
			log.error("获取视频监控json数据", e);
		}
		return jsonArray.toString();
	}
	private void setHub()
	{
		if(hubv2==null)
		{
			Client cli = new Client(PropertyUtil.getValue("access_key"), PropertyUtil.getValue("secret_key"));
			hubv2 = cli.newHub(PropertyUtil.getValue("hub_name"));
		}
	}
	public String getWaringOrCloseList(int type, long uid, long f_uuid, String w_name, int page, Model model)
	{
		try {
			List<String> wnameList = iadminDao.getALLAdminList();
			List<Partner_Record> partnerRecordList = ipartner_RecordDao.getPartnerRecordList(type, uid, f_uuid, w_name, (page-1)*20, 20);
			int totalRecord = ipartner_RecordDao.getPartnerRecordCount(type, uid, f_uuid, w_name);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName(type==0?"waring/list":"close/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("partnerRecordList", partnerRecordList);
			model.addAttribute("wnameList", wnameList);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("uid", uid);
			model.addAttribute("w_name", w_name);
			model.addAttribute("activeUrl", type==0?"waring":"close");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("视频警告/关闭列表", e);
		}
		return type==0?"square/waringlist":"square/closelist";
	}
	@SuppressWarnings("null")
	public String getCommentList(Long uid,Long f_uuid,String comment,Date date,int second, int page, Model model) {
		try{
			List<Chat> chats=new ArrayList<Chat>();
			Calendar calendar = new GregorianCalendar(); 
			Set<String> list = null;
			long count = 0l;

			if (date!=null || uid!=null || f_uuid!=null || comment.length()>0) {
				if (date==null) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					date = sf.parse(sf.format(new Date()));
			    }
				double min_time = date.getTime();//所选日期的00:00:00
				double max_time   = min_time + 86399000d;//所选日期的23:59:59 86399000 = (24*60*60 - 1)* 1000;

				list = RedisUtilWrapper.GetZrangeByScoreJedis(3, "chat", min_time, max_time);
                if (list!=null) {
    				for(Iterator<String> it = list.iterator();it.hasNext();){ 
    					JSONObject json=JSONObject.fromObject(it.next());
    						if ((json!=null)&&(((uid!=null)&&(json.getLong("uid")!=uid.longValue()))||((f_uuid!=null)&&(json.getLong("anchor_f_uuid")!=f_uuid.longValue()))
    								||(((comment!=null)&&(!"".equals(comment)))&&(!json.getString("msg").contains(comment))))) {
    							it.remove();
    						}
    					}
    				int index = 0;
    				count = list.size(); 
    				int index_min = (page - 1) * 30;
    				int index_max = page * 30 - 1;
    				for(Iterator<String> it = list.iterator();it.hasNext();){ 
    					    it.next();
                            if ((index<count)&&((index<index_min)||(index>index_max))) {
    							it.remove();
    						}
                            index++;
    				}				
				}

			}else {
				list = RedisUtil.GetZrevrangeJedis(3, "chat", (page-1)*30, page*30-1);
				count = RedisUtil.GetJedisMapSize(3, "chat");
			}
			if (list!=null) {
				for(String key:list){
					JSONObject json=JSONObject.fromObject(key);
					if(null!=json){
						Chat chat=new Chat();
						chat.setUid(json.getLong("uid"));
						chat.setNickname(json.getString("nickname"));
						chat.setMsg(json.getString("msg"));
						chat.setTime(json.getString("date"));
						chat.setAnchor_f_uuid(json.getLong("anchor_f_uuid"));
						User user = iuserDao.getUser(chat.getUid());
						if(user!=null)
						{
							boolean is_forbid = false;
							if(user.getIs_forbid()==1)
							{
								if(user.getForbid_hour()==0)
								{
									is_forbid = true;
								}
								else
								{
									calendar.setTime(user.getForbid_start_time()); 
									calendar.add(Calendar.HOUR,user.getForbid_hour());
									Date end_time = calendar.getTime();
									if(user.getForbid_hour()>0&&end_time.after(new Date()))
										is_forbid = true;
								}
							}
							chat.setIs_forbid(is_forbid);
						}
						String anchor_nickname = iuserDao.getUserNicknameWithF_uuid(chat.getAnchor_f_uuid());
						chat.setAnchor_name(anchor_nickname);
						if(isquareDao.getSquareCount2(chat.getAnchor_f_uuid())>0)
							chat.setSleep(false);
						chats.add(chat);
					  }
				}				
			}

			PageUtil pageUtil = new PageUtil(30, (int)count, page);
			pageUtil.setTotalRecord((int)count);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("comment/list");
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("comment", comment);
			model.addAttribute("chatList", chats);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "comment");
			model.addAttribute("second", second);
			model.addAttribute("date", date!=null?AuthUtil.formatDateToString(date, "yyyy-MM-dd"):"");
		}catch(Exception e){
			e.printStackTrace();
			log.error("获取评论列表异常:"+e);
		}
		return "square/commentlist";
	}
	/**
	 * 
	 * <p>功能描述：添加用户禁言</p>
	 *
	 * @param uid 用户uid
	 * @param hour 小时数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveUserForbid(long uid, int hour, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {	
			w_name = String.valueOf(request.getAttribute("w_name"));		
			Date date = new Date();
			int result = iuserDao.saveUserForbid(uid, date, hour, w_name, date);
			imanage_RecordDao.insertManageRecord(w_name, "添加用户禁言，uid:"+uid, "t_user", 0, IPUtil.getIp(request), date);
			if(result>0)
			{
				String token = iuserDao.getUserInfoExtraToken(uid);
				RedisUtil.SetHsetJedis(0, token, "is_forbid", "1");
				RedisUtil.SetHsetJedis(0, token, "forbid_hour", String.valueOf(hour));
				RedisUtil.SetHsetJedis(0, token, "forbid_start_time", DateUtils.sdf2.format(date));
				//通知im服务。。
				gaguid(token, hour);
				isjin(token, "0", false, false);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "禁言成功");
			}

		} catch (Exception e) {
			log.error("添加用户禁言",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：取消用户禁言</p>
	 *
	 * @param uid 用户uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String cancelUserForbid(long uid, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {			
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			int result = iuserDao.cancelUserForbid(uid, w_name, date);
			imanage_RecordDao.insertManageRecord(w_name, "取消用户禁言，uid:"+uid, "t_user", 0, IPUtil.getIp(request), date);
			if(result>0)
			{
				//通知im服务。。
				String token = iuserDao.getUserInfoExtraToken(uid);
				RedisUtil.SetHsetJedis(0, token, "is_forbid", "0");
				isjin(token, "0", false, true);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "取消禁言成功");
			}

		} catch (Exception e) {
			log.error("取消用户禁言",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 分帧监控
	 * @param id
	 * @param page
	 * @param model
	 * @return
	 */
	public String getFramingList(long f_uuid, int page, Model model){
		try{
			Date date = new Date();
			List<Square> squareList = isquareDao.getSquareList2(f_uuid, date, (page-1)*18, 18);
			int totalRecord = isquareDao.getSquareCount2(f_uuid);
			setWatchCountByRedis(squareList);
			setHub();

			for (Square square : squareList) {
				Stream stream = hubv2.get(String.valueOf(square.getF_uuid()));
				if(stream!=null)
				{
					SnapshotOptions opts = new SnapshotOptions(stream.getKey()+System.currentTimeMillis()+".jpg", 0, "jpg");
					String imgUrl= PropertyUtil.getValue("video_url")+ stream.snapshot(opts);//直播截屏
					square.setAnchor_cover(imgUrl);
				}
			}
			List<Square> allSquareList = isquareDao.getAllSquareList();
			PageUtil pageUtil = new PageUtil(18, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("framing/list");
			model.addAttribute("squareList", squareList);
			model.addAttribute("allSquareList", allSquareList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("activeUrl", "framing");
		}catch(Exception e){
			log.error("获取分帧列表异常"+e);
		}
		return "square/framinglist";
	}


	public int charge(Long uid,Long f_uuid,String nickname,Long times,String w_name,int fee_count,HttpServletRequest request){

		int result = -1;
		int is_fee = -1;
		if ((fee_count>=10)&&(fee_count<=10000)) {
			is_fee = 1;
		}
		w_name = String.valueOf(request.getAttribute("w_name"));
		SquareFeeRecord square_Fee_Record= new SquareFeeRecord();
		square_Fee_Record.setUid(uid);
		square_Fee_Record.setF_uuid(f_uuid);
		square_Fee_Record.setNickname(nickname);
		square_Fee_Record.setStart_time(new Date(times));
		square_Fee_Record.setCreate_time(new Date());
		square_Fee_Record.setW_name(w_name);
		square_Fee_Record.setFee_count(fee_count);
		try {
			result = isquareDao.updateFee_count(uid, fee_count,is_fee,1);
			result = isquare_Fee_RecordDao.insertSquareFeeRecord(square_Fee_Record);
			try {
				String token = iuserDao.getUserInfoExtraToken(uid);
				String lastend = RedisUtil.Gethget(0, token, "lastend");
				if(!StringUtils.isBlank(lastend)&&!lastend.equals("null"))
				{
					long lastend_long = Long.parseLong(lastend);
					Square _square = isquareDao.getSquare(f_uuid, uid);
					if(_square.getIs_fee()==0&&System.currentTimeMillis()-lastend_long>3*3600*1000)//大于3个小时重新开播要再付费
						isquareDao.deleteSquareFee(f_uuid);
				}
			}
			catch(Exception e)
			{
				Log4jHandel.myerror("开播时间lastend转换异常", e);
			}
			imanage_RecordDao.insertManageRecord(w_name, "修改直播房间收费,f_uuid："+f_uuid, "t_manage_record", 0, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			log.error("更新直播间收费失败",e);
		}
		return result;
	}
	public static void main(String[] args) {
		Long tv = 2l;
		long tx = 1l;
		System.err.println(tx!=tv);
	}
}

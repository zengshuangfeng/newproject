package com.meisui.manage.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.forman.foundation.library.DoubleUtil;
import com.forman.foundation.library.RedisUtil;
import com.forman.log4j.Log4jHandel;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_CutrecordDao;
import com.meisui.manage.dao.Ianchor_TimeDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.Ianchor_Virtual_Change_RecordDao;
import com.meisui.manage.dao.IapplyDao;
import com.meisui.manage.dao.IconfigDao;
import com.meisui.manage.dao.IkeyDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IsquareDao;
import com.meisui.manage.dao.IsquareFeeRecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_AdminDao;
import com.meisui.manage.dao.Iuser_PushDao;
import com.meisui.manage.entity.AdmissionTicket;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Anchor_Cutrecord;
import com.meisui.manage.entity.Anchor_Time;
import com.meisui.manage.entity.Anchor_Time_Total;
import com.meisui.manage.entity.Anchor_Virtual_Change_Record;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Profit;
import com.meisui.manage.entity.SquareFeeRecord;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Admin;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.entity.User_Push;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

/**
 * <p>文件名称：User_AnchorService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Service
public class User_AnchorService extends HessianService {
	private static Logger log = Logger.getLogger(User_AnchorService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Ianchor_TimeDao ianchor_TimeDao;
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Ianchor_CutrecordDao ianchor_CutrecordDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IapplyDao iapplyDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private Iuser_PushDao iuser_PushDao;
	@Autowired
	private IkeyDao ikeyDao;
	@Autowired
	private Ianchor_Virtual_Change_RecordDao ianchor_Virtual_Change_RecordDao;
	@Autowired
	private IconfigDao iconfigDao;
	@Autowired
	private IsquareDao isquareDao;
	@Autowired
	private IsquareFeeRecordDao isquareFeeRecordDao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Iagent_PromoterDao iagentpromoterdao;
	@Autowired
	private Iuser_AdminDao iuser_AdminDao;
	/**
	 * 
	 * <p>功能描述：主播列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午4:11:32</p>
	 *
	 * @param uid 用户id
	 * @param f_uuid 用户房间号
	 * @param name 昵称
	 * @param anchor_state 主播状态0休息1直播2禁播
	 * @param union_id 公会id
	 * @param is_trial 是否是试播 1是 0否
	 * @param last_anchor_time 最后直播时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getUserAnchorList(long uid, long f_uuid, String nickname, int operate_centerid, int page, Model model) {
		try {
			List<User_Anchor> user_AnchorList = iuserDao.getUserAnchorList(uid, f_uuid, nickname, operate_centerid, (page-1)*20, 20);			
			int totalRecord = iuserDao.getUserAnchorCount(uid, f_uuid, nickname, operate_centerid);
			for(User_Anchor ua:user_AnchorList){
				int operate_id=ua.getOperate_center_id();
				Operate_Center operate_center=ioperate_centerdao.getoperateName(operate_id);
				if(operate_center !=null){
					String operateName=operate_center.getName();
					ua.setOperateName(operateName);		
					int operate_divede=operate_center.getDivide();
					ua.setOperate_divide(operate_divede);	
				}

				int agent_id=ua.getAgent_id();
				Agent agent=iagentdao.getAgentByagentid(agent_id);
				if(agent !=null){
					String agentName=agent.getName();
					int agent_divide=agent.getDivide();
					ua.setAgent_divide(agent_divide);
					ua.setAgentName(agentName);
				}

				int agentpromoterid=iagentpromoterdao.getpromoterByUid(ua.getUid());
				int ispromoter=0;
				if(agentpromoterid>0){
					ispromoter=1;
				}
				ua.setIspromoter(ispromoter);
			
			}
			List<Operate_Center> operate=ioperate_centerdao.getOperateCenter();

			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(18);
			pageUtil.setUrlName("useranchor/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userAnchorList", user_AnchorList);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("operate_centerid", operate_centerid);
			model.addAttribute("activeUrl", "useranchor");		
			model.addAttribute("operate", operate);

		} catch (Exception e) {
			log.error("主播列表", e);
		}
		return "useranchor/list";
	}
	/**
	 * 
	 * <p>功能描述：删除主播</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午5:39:12</p>
	 *
	 * @param uid 主播uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int deleteUserAnchor(long uid, String w_name, HttpServletRequest request)
	{
		int result=0;
		try {
			if (uid>0) {
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iuserDao.deleteUserAnchor(uid);
				String token = iuserDao.getUserInfoExtraToken(uid);
				RedisUtil.SetHsetJedis(0, token, "is_anchor", "0");
				imanage_RecordDao.insertManageRecord(w_name, "删除"+"“"+"uID："+uid+"”"+"主播", "t_user_anchor", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("删除主播",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：更新主播直播状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午6:18:42</p>
	 *
	 * @param uid 主播uid
	 * @param state 主播状态0休息1直播2禁播
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserAnchorState(String uid, int anchor_type, String w_name, HttpServletRequest request)
	{
		int result=0;
		try {
			if (!StringUtils.isBlank(uid)) {
				int state = 0;
				if(anchor_type==0)
					state = 2;
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iuserDao.updateUserAnchorState(state, anchor_type, uid);
				if(result>0)
				{
					String token = iuserDao.getUserInfoExtraToken(Long.parseLong(uid));
					RedisUtil.SetHsetJedis(0, token, "anchor_type", String.valueOf(anchor_type));
					if(state==2)
					{
						RedisUtil.SetHsetJedis(0, token, "anchor_state", String.valueOf(state));
					}
					else {
						if(RedisUtil.Gethget(0, token, "anchor_state").equals("2"))
						{
							RedisUtil.SetHsetJedis(0, token, "anchor_state", "0");
						}
					}
				}
				imanage_RecordDao.insertManageRecord(w_name, "更新"+"“"+"uID："+uid+"”"+"主播直播状态，anchor_state:"+state, "t_user_anchor", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("更新主播直播状态",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：获取主播分成占比</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 上午9:15:37</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	public int getUserAnchorDivide(long uid)
	{		
		int result=0;
		try {
			Integer divide = iuserDao.getUserAnchorDivideProportion(uid);
			if(divide!=null)
				result = (int)divide;
		} catch (Exception e) {
			log.error("获取主播分成占比",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：更新主播分成占比</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 上午9:00:37</p>
	 *
	 * @param uid 主播uid
	 * @param divide_proportion 分成占比
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserAnchorDivide(long uid, int divide_proportion, String w_name, HttpServletRequest request)
	{
		int result=0;
		try {
			Integer old_divide_proportion = iuserDao.getUserAnchorDivideProportion(uid);
			if(old_divide_proportion==null)
				old_divide_proportion = 0;
			Date date =  new Date();
			Anchor_Cutrecord anchor_Cutrecord = new Anchor_Cutrecord();
			anchor_Cutrecord.setUid(uid);
			anchor_Cutrecord.setStart_handle((double)old_divide_proportion);
			anchor_Cutrecord.setEnd_handle((double)divide_proportion);
			anchor_Cutrecord.setStartup(date);
			w_name = String.valueOf(request.getAttribute("w_name"));
			anchor_Cutrecord.setW_name(w_name);
			result = iuserDao.updateUserAnchorDivideProportion(divide_proportion, uid);
			if(result>0)
			{
				ianchor_CutrecordDao.insertAnchorCutrecord(anchor_Cutrecord);
			}
			imanage_RecordDao.insertManageRecord(w_name, "更新"+"“"+"uID："+uid+"”"+"主播分成占比，divide_proportion:"+divide_proportion, "t_user_info", 0, IPUtil.getIp(request), date);
		} catch (Exception e) {
			log.error("更新主播分成占比",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：更新主播分成占比</p>
	 * <p>创建人：</p>
	 *
	 * @param uid 主播uid
	 * @param divide_proportion 分成占比
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserAnchorOneDivide(long uid, int one_divide_proportion, String w_name, HttpServletRequest request)
	{
		int result=0;
		try {
			result = iuserDao.updateUserAnchorOneDivideProportion(one_divide_proportion, uid);
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "更新"+"“"+"uID："+uid+"”"+"主播一对一分成占比，one_divide_proportion:"+one_divide_proportion, "t_user_info", 0, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			log.error("更新主播分成占比",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：获取主播提现方式</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 上午9:22:56</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	public String getUserAnchorWithdraw(long uid)
	{		
		JSONObject jsonObject = new JSONObject();
		try {
			User_Info user_Info = iuserDao.getUserAnchorWithdraw(uid);
			jsonObject = JSONObject.fromObject(user_Info);
		} catch (Exception e) {
			log.error("获取主播提现方式",e);
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：保存主播提现方式</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午1:49:04</p>
	 *
	 * @param uid 主播uid
	 * @param withdraw_type 0支付宝
	 * @param withdraw_pass 提现账号
	 * @param withdraw_name 账号姓名
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveUserAnchorWithdrawType(long uid, int withdraw_type, String withdraw_pass, String withdraw_name, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			Date date = new Date();
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserAnchorWithdraw(withdraw_type, withdraw_pass, withdraw_name, uid);
			imanage_RecordDao.insertManageRecord(w_name, "更新"+"“"+"uID："+uid+"”"+"主播提现方式", "t_user_info", 0, IPUtil.getIp(request), date);
			if(result>0)
			{
				jsonObject.put("code", 0);
				jsonObject.put("msg", "保存成功");
			}

		} catch (Exception e) {
			log.error("保存主播提现方式信息",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：主播信息页</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午3:42:31</p>
	 *
	 * @param uid 主播uid
	 * @param model
	 */
	public String getUserAnchorInfo(long uid, int apply_id, String url, Model model)
	{

		User_Anchor user_Anchor = iuserDao.getUserAnchor(uid);
		User user = iuserDao.getUser(uid);
		User_Info user_Info = iuserDao.getUserInfo(uid);
		List<String> headList =  new ArrayList<String>();
		if(apply_id>0)
		{
			String head = iapplyDao.getApplyHead(apply_id);
			if(!StringUtils.isBlank(head))
				headList = Arrays.asList(head.split(","));
		}
		int hasInviteCode = -1;
		if(user != null) {
			if(user.getOperate_center_id() == 0 && user.getAgent_id() == 0) {
				hasInviteCode = 0;
			}
		}
		model.addAttribute("headList", headList);
		model.addAttribute("userAnchor", user_Anchor);
		model.addAttribute("tuser", user);
		model.addAttribute("user_Info", user_Info);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", url);
		model.addAttribute("hasInviteCode", hasInviteCode);

		return "useranchor/info";
	}
	/**
	 * 
	 * <p>功能描述：保存主播信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午3:42:42</p>
	 *
	 * @param id 主播表id
	 * @param uid 主播uid
	 * @param anchor_cover 封面
	 * @param remark 备注
	 * @param union_id 公会id
	 * @param w_name 编辑人员
	 * @param divide_proportion 主播分成
	 * @param is_trial 是否是试播 1是 0否
	 * @param is_vip 是否是VIP 1是 0否
	 * @param birthday_pic 生日卡片图
	 * @param request
	 * @return
	 */
	public String saveUserAnchor(int id, long uid, String anchor_cover, String remark, int is_trial,  int type, int youxi, int live,int is_vip,  String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			if(uid==0)
			{
				jsonObject.put("code", -1);
				jsonObject.put("msg", "uid不能为空");
				return jsonObject.toString();
			}
			Date date = new Date();
			User_Anchor user_Anchor = new User_Anchor();
			user_Anchor.setAnchor_cover(anchor_cover);
			user_Anchor.setRemark(remark);
			user_Anchor.setUid(uid);
			user_Anchor.setIs_trial(is_trial);
			user_Anchor.setUpdate_time(date);
			w_name = String.valueOf(request.getAttribute("w_name"));
			user_Anchor.setW_name(w_name);
			user_Anchor.setType(type);
			user_Anchor.setIs_vip(is_vip);
			if(youxi==0&&live==0)
				user_Anchor.setAnchor_type(0);
			else if(youxi==1&&live==1)
				user_Anchor.setAnchor_type(3);
			else if(youxi==0&&live==1)
				user_Anchor.setAnchor_type(1);
			else if(youxi==1&&live==0)
				user_Anchor.setAnchor_type(2);
			int result = 0;
			if(id==0)
			{
				user_Anchor.setAnchor_state(0);
				user_Anchor.setCreate_time(date);
				Integer has_del = iuserDao.getUserAnchorIsDel(uid);
				if(has_del!=null&&has_del>0)
				{
					iuserDao.updateUserAnchorIsDel(uid);
					result = iuserDao.updateUserAnchor(user_Anchor);
				}
				else
				{
					result = iuserDao.insertUserAnchor(user_Anchor);
				}
				imanage_RecordDao.insertManageRecord(w_name, "添加"+"“"+"uID："+uid+"”"+"主播信息", "t_user_anchor", user_Anchor.getId(), IPUtil.getIp(request), date);
				if(result>0)
				{
					iuserDao.updateUserIsAnchor(1, uid);
					String token = iuserDao.getUserInfoExtraToken(uid);
					RedisUtil.SetHsetJedis(0, token, "is_anchor", "1");
					RedisUtil.SetHsetJedis(0, token, "anchor_state", "0");
					RedisUtil.SetHsetJedis(0, token, "anchor_cover", anchor_cover);
					RedisUtil.SetHsetJedis(0, token, "anchor_type", String.valueOf(user_Anchor.getAnchor_type()));
					RedisUtil.SetHsetJedis(0, token, "anchor_notice", "");

				}
			}
			else {
				user_Anchor.setId(id);
				result = iuserDao.updateUserAnchor(user_Anchor);
				String token = iuserDao.getUserInfoExtraToken(uid);
				RedisUtil.SetHsetJedis(0, token, "anchor_type", String.valueOf(user_Anchor.getAnchor_type()));

				imanage_RecordDao.insertManageRecord(w_name, "更新"+"“"+"uID："+uid+"”"+"主播信息", "t_user_anchor", user_Anchor.getId(), IPUtil.getIp(request), date);
			}
			if(result>0)
			{		

				jsonObject.put("code", 0);
				jsonObject.put("msg", "保存成功");
			}
			//更新视频监控列表中的vip信息
			isquareDao.updateSquareVIP(user_Anchor.getUid(), is_vip);

		} catch (Exception e) {
			log.error("保存主播信息",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：获取主播昵称</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午4:12:52</p>
	 *
	 * @param uid 主播id
	 * @return
	 */
	public String getUserName(long uid)
	{
		User user = iuserDao.getUser(uid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("nickname", user==null?"":user.getNickname());
		jsonObject.put("is_anchor", user==null?0:user.getIs_anchor());
		int hasInviteCode = 0;
		if(user != null) {
			if(user.getOperate_center_id() > 0 && user.getAgent_id() > 0) {
				hasInviteCode = 1;
			}
		}else {
			hasInviteCode = -1;
		}
		jsonObject.put("hasInviteCode", hasInviteCode);
		return jsonObject.toString();
	}	
	/**
	 * 
	 * <p>功能描述：主播时长列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午4:11:32</p>
	 *
	 * @param uid 用户id
	 * @param name 昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getAnchorTimeList(long uid, Date date, Date end_date, int page, String url, Model model) {
		try {
			if(date==null)
				date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(new Date(),"yyyy-MM")+"-01","yyyy-MM-dd");
			if(end_date==null)
				end_date=new Date();
			model.addAttribute("end_date", AuthUtil.formatDateToString(end_date,"yyyy-MM-dd"));
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(end_date); 
			calendar.add(Calendar.DATE,1);
			end_date = calendar.getTime();
			List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList(uid, 0, date, end_date, (page-1)*20, 20);
			List<Anchor_Time> anchor_TimeList2 = new ArrayList<Anchor_Time>();
			for (Anchor_Time anchor_Time : anchor_TimeList) {
				if(anchor_Time.getStart_time().before(date))
					anchor_Time.setStart_time(date);

				Date  current_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd")+" 00:00:00");
				Date  current_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getEnd_time(), "yyyy-MM-dd")+" 00:00:00");
				if(current_start_date.before(current_end_date))
				{
					Anchor_Time temp = new Anchor_Time();
					temp.setUid(anchor_Time.getUid());
					temp.setStart_time(anchor_Time.getStart_time());
					temp.setEnd_time(anchor_Time.getEnd_time());
					temp.setStart_time(current_end_date);
					anchor_TimeList2.add(temp);
					anchor_Time.setEnd_time(AuthUtil.formatStringToDate(AuthUtil.formatDateToString(current_start_date,"yyyy-MM-dd")+" 23:59:59"));
					anchor_TimeList2.add(anchor_Time);
				}
				else
					anchor_TimeList2.add(anchor_Time);
			}
			List<Anchor_Time_Total> anchor_Time_TotalList =  new ArrayList<Anchor_Time_Total>();
			for (Anchor_Time anchor_Time : anchor_TimeList2) {
				long millminute = anchor_Time.getEnd_time().getTime() - anchor_Time.getStart_time().getTime();
				anchor_Time.setSingle_total_time(getTotalTime(millminute));
				String dateString = AuthUtil.formatDateToString(anchor_Time.getStart_time(),"MM月dd日");
				Long total_virtual = iprofitDao.getProfitSum(uid, anchor_Time.getStart_time(), anchor_Time.getEnd_time());
				if(total_virtual==null)
					total_virtual = 0L;
				anchor_Time.setTotal_virtual(total_virtual);

				boolean contain = false;
				for (Anchor_Time_Total anchor_Time_Total : anchor_Time_TotalList) {
					if(anchor_Time_Total.getDate().equals(dateString))
					{
						anchor_Time_Total.getAnchor_TimeList().add(anchor_Time);
						anchor_Time_Total.setTotal_millminute(anchor_Time_Total.getTotal_millminute()+millminute);
						contain = true;
						break;
					}
				}
				if(!contain)
				{
					Anchor_Time_Total anchor_Time_Total = new Anchor_Time_Total();
					anchor_Time_Total.setDate(dateString);
					anchor_Time_Total.getAnchor_TimeList().add(anchor_Time);
					anchor_Time_Total.setTotal_millminute(millminute);
					anchor_Time_TotalList.add(anchor_Time_Total);
				}
			}
			for (Anchor_Time_Total anchor_Time_Total : anchor_Time_TotalList) {
				anchor_Time_Total.setTotal(getTotalTime(anchor_Time_Total.getTotal_millminute()));
			}
			int totalRecord = ianchor_TimeDao.getAnchorTimeCount(uid, 0, date);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName(url+"/timelist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorTimeTotalList", anchor_Time_TotalList);
			model.addAttribute("uid", uid);
			model.addAttribute("date", AuthUtil.formatDateToString(date,"yyyy-MM-dd"));
			model.addAttribute("activeUrl", url);
		} catch (Exception e) {
			log.error("主播时长列表", e);
		}
		return "useranchor/timelist";
	}
	/**
	 * 
	 * <p>功能描述：主播当月时长统计</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 上午11:51:36</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	public String getAnchorMonthTimeTotal(long uid, Date date, Date end_date) {
		JSONObject jsonObject = new JSONObject();
		try {
			int total_day=0,realy_total_day=0;
			long millminute = 0;
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(end_date); 
			calendar.add(Calendar.DATE,1);
			end_date = calendar.getTime();
			Map<String, Long> dayMap = new HashMap<String, Long>();
			Map<Date, Date> dayDateMap = new LinkedHashMap<Date, Date>();
			List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList2(uid, 0, date, end_date);
			List<Anchor_Time> anchor_TimeList2 = new ArrayList<Anchor_Time>();
			for (Anchor_Time anchor_Time : anchor_TimeList) {
				if(anchor_Time.getStart_time().before(date))
					anchor_Time.setStart_time(date);
				if(anchor_Time.getEnd_time().after(end_date))
				{
					anchor_Time.setEnd_time(end_date);
				}
				Date  current_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd")+" 00:00:00");
				Date  current_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getEnd_time(), "yyyy-MM-dd")+" 00:00:00");
				if(current_start_date.before(current_end_date))
				{
					Anchor_Time temp = new Anchor_Time();
					temp.setUid(anchor_Time.getUid());
					temp.setStart_time(anchor_Time.getStart_time());
					temp.setEnd_time(anchor_Time.getEnd_time());
					temp.setStart_time(current_end_date);
					anchor_TimeList2.add(temp);
					anchor_Time.setEnd_time(AuthUtil.formatStringToDate(AuthUtil.formatDateToString(current_start_date,"yyyy-MM-dd")+" 23:59:59"));
					anchor_TimeList2.add(anchor_Time);
				}
				else
					anchor_TimeList2.add(anchor_Time);
			}
			//不满半小时的不算进去， 断线3分钟内可累加
			for (Anchor_Time anchor_Time : anchor_TimeList2) {
				boolean is_contain = false;
				for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) {  
					if(anchor_Time.getStart_time().getTime()-entry.getValue().getTime()<=180000&&anchor_Time.getStart_time().getTime()-entry.getValue().getTime()>0)
					{
						entry.setValue(anchor_Time.getEnd_time());
						is_contain = true;
					}
				}
				if(!is_contain)
					dayDateMap.put(anchor_Time.getStart_time(), anchor_Time.getEnd_time());
				millminute += anchor_Time.getEnd_time().getTime()-anchor_Time.getStart_time().getTime();
			}
			/*for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) { 
				System.out.println(AuthUtil.formatDateToString(entry.getKey())+" "+AuthUtil.formatDateToString(entry.getValue()));
			}
			 */						
			for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) { 
				if(entry.getValue().getTime()-entry.getKey().getTime()>1800000)//大于半小时才算有效记录
				{
					String current_date = AuthUtil.formatDateToString(entry.getKey(),"yyyy-MM-dd");
					if(dayMap.containsKey(current_date))
					{
						dayMap.put(current_date, dayMap.get(current_date) + (entry.getValue().getTime()-entry.getKey().getTime()));
					}
					else
					{
						dayMap.put(current_date, entry.getValue().getTime()-entry.getKey().getTime());
					}
				}
			}
			for (Map.Entry<String, Long> entry : dayMap.entrySet()) { 
				total_day+=1;
				if(entry.getValue()>=7200000)//大于两个小时 毫秒
					realy_total_day+=1;
			}

			/*


			Map<String, Long> dayMap = new HashMap<String, Long>();
			List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList(uid, 0, date, end_date, 0, 1000000);
			List<Anchor_Time> anchor_TimeList2 = new ArrayList<Anchor_Time>();
			for (Anchor_Time anchor_Time : anchor_TimeList) {
				if(anchor_Time.getStart_time().before(date))
					anchor_Time.setStart_time(date);
				if(anchor_Time.getEnd_time().before(end_date))
					anchor_Time.setEnd_time(end_date);
				Date  current_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd")+" 00:00:00");
				Date  current_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getEnd_time(), "yyyy-MM-dd")+" 00:00:00");
				if(current_start_date.before(current_end_date))
				{
					Anchor_Time temp = new Anchor_Time();
					temp.setUid(anchor_Time.getUid());
					temp.setStart_time(anchor_Time.getStart_time());
					temp.setEnd_time(anchor_Time.getEnd_time());
					temp.setStart_time(current_end_date);
					anchor_TimeList2.add(temp);
					anchor_Time.setEnd_time(AuthUtil.formatStringToDate(AuthUtil.formatDateToString(current_start_date,"yyyy-MM-dd")+" 23:59:59"));
					anchor_TimeList2.add(anchor_Time);
				}
				else
					anchor_TimeList2.add(anchor_Time);
			}
			for (Anchor_Time anchor_Time : anchor_TimeList2) {
				String current_date = AuthUtil.formatDateToString(anchor_Time.getStart_time(),"yyyy-MM-dd");
				if(dayMap.containsKey(current_date))
					dayMap.put(current_date, dayMap.get(current_date) + (anchor_Time.getEnd_time().getTime()-anchor_Time.getStart_time().getTime()));
				else
					dayMap.put(current_date, anchor_Time.getEnd_time().getTime()-anchor_Time.getStart_time().getTime());
			}
			for (Map.Entry<String, Long> entry : dayMap.entrySet()) {  
				total_day +=1;
				if(entry.getValue()>=7200000)//大于两个小时 毫秒
					realy_total_day+=1;
				millminute +=entry.getValue();
			}*/
			String total = getTotalTime(millminute);
			/*Date lastDate = ianchor_TimeDao.getAnchorTimeWithTime(uid, end_date);
			if(lastDate!=null&&lastDate.after(end_date))
				end_date=lastDate;*/
			Long total_virtual = iprofitDao.getProfitSum(uid, date, end_date);
			if(total_virtual==null)
				total_virtual = 0L;
			jsonObject.put("total_day", total_day);
			jsonObject.put("realy_total_day", realy_total_day);
			jsonObject.put("total", total);
			jsonObject.put("total_virtual", total_virtual);
		}
		catch (Exception e) {
			log.error("主播当月时长统计", e);
		}
		return jsonObject.toString();
	}
	private String getTotalTime(long millminute)
	{
		Integer ss = 1000;  
		Integer mi = ss * 60;  
		Integer hh = mi * 60;   

		Long hour = millminute / hh;  
		Long minute = (millminute - hour * hh) / mi;  
		Long second = (millminute - hour * hh - minute * mi) / ss; 
		StringBuffer sbBuffer = new StringBuffer();
		if(hour>0)
		{
			sbBuffer.append(hour);
			sbBuffer.append("小时");
		}
		if(minute>0)
		{
			sbBuffer.append(minute);
			sbBuffer.append("分钟");
		}
		if(second>0)
		{
			sbBuffer.append(second);
			sbBuffer.append("秒");
		}
		return sbBuffer.toString();
	}

	/**
	 * 
	 * <p>功能描述：门票房统计列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年09月28日 下午1:54:23</p>
	 *
	 * @param nickname 主播昵称
	 * @param uid 主播uid
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */		
	public String getAnchorsAdmissionTicketList(String nickname,Long uid,Date start_date,Date end_date,int page,Model model){

		Date  selected_start_date = null;
		Date  selected_end_date = null;	

		try {
			if ((start_date!=null)&&(end_date!=null)) {
				selected_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")+" 00:00:00");
				selected_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(end_date, "yyyy-MM-dd")+" 23:59:59");
			}
		} catch (ParseException e) {
			log.error("日期解析异常",e);
		}

		List<AdmissionTicket> list = ianchor_TimeDao.getAnchorsAdmissionTicketList(nickname, uid, selected_start_date, selected_end_date,(page-1)*20,20);
		for (AdmissionTicket admissionTicket : list) {
			long total_time = admissionTicket.getEnd_time().getTime()-admissionTicket.getStart_time().getTime();
			admissionTicket.setSingle_total_time(getTotalTime(total_time));
		}
		int totalRecord = ianchor_TimeDao.getAnchorsAdmissionTicketCount(nickname, uid, selected_start_date, selected_end_date);
		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
		String fee_up_limit =  iconfigDao.getConfigValue("fee_up_limit");
		String fee_down_limit =  iconfigDao.getConfigValue("fee_down_limit");
		pageUtil.setTotalRecord(totalRecord);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(18);
		pageUtil.setUrlName("useranchor/admissionticketlist");
		model.addAttribute("fee_down_limit", fee_down_limit);
		model.addAttribute("fee_up_limit", fee_up_limit);
		model.addAttribute("showPage", pageUtil);
		model.addAttribute("admissionticketlist", list);
		model.addAttribute("activeUrl", "admissionticketlist");
		model.addAttribute("nickname",nickname);
		model.addAttribute("uid", uid);
		model.addAttribute("start_date", start_date!=null?AuthUtil.formatDateToString(start_date,"yyyy-MM-dd"):"");
		model.addAttribute("end_date", end_date!=null?AuthUtil.formatDateToString(end_date,"yyyy-MM-dd"):"");

		return "useranchor/admissionticketlist";
	}

	public String getAdmissionTicketRecordList(Long uid,String nickname,Date start_date,Date end_date,int page,Model model){

		List<SquareFeeRecord> list = isquareFeeRecordDao.getSquareFeeRecordList(uid, nickname, start_date, end_date,(page-1)*20,20);

		int totalRecord = isquareFeeRecordDao.getSquareFeeRecordCount(uid, nickname, start_date, end_date);
		PageUtil pageUtil = new PageUtil(20, totalRecord, page);

		pageUtil.setTotalRecord(totalRecord);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(18);
		pageUtil.setUrlName("useranchor/ticketupdaterecordlist");
		model.addAttribute("showPage", pageUtil);	
		model.addAttribute("activeUrl", "ticketupdaterecordlist");
		model.addAttribute("recordlist", list);
		model.addAttribute("uid", uid);
		model.addAttribute("nickname", nickname);
		model.addAttribute("start_date", start_date!=null?AuthUtil.formatDateToString(start_date, "yyyy-MM-dd"):"");
		model.addAttribute("end_date", end_date!=null?AuthUtil.formatDateToString(end_date, "yyyy-MM-dd"):"");

		return "useranchor/ticketupdaterecord";
	}

	public int setAdmissionTicketFeeCountLimit(int fee_count_min,int fee_count_max,String w_name,HttpServletRequest request){
		int result = -1;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			if (((fee_count_min>=0)&&(fee_count_min<10001))&&((fee_count_max>=0)&&(fee_count_max<10001))&&(fee_count_min<=fee_count_max)) {
				result = iconfigDao.setFeeCountLimit("fee_down_limit", ""+fee_count_min);
				result = iconfigDao.setFeeCountLimit("fee_up_limit", ""+fee_count_max);
			}	
			imanage_RecordDao.insertManageRecord(w_name, "设置门票上下限", "t_config", 14, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			log.error("设置门票上下限", e);
		}

		return result;
	}
	/**
	 * 
	 * <p>功能描述：主播魅力值流水列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 下午3:54:23</p>
	 *
	 * @param uid 主播uid
	 * @param date 日期
	 * @param type 0用户赠送  4后台奖励 5后台追回
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getProfitList(long uid, int type, String date, int page, Model model)
	{
		try {
			if(StringUtils.isBlank(date))
				date = AuthUtil.formatDateToString(new Date(), "yyyy-MM");
			model.addAttribute("date", date);
			date = date + "-01";
			List<Profit> profitList = iprofitDao.getProfitList(uid, type, date, (page-1)*20, 20);
			int totalRecord = iprofitDao.getProfitCount(uid, type, date);
			User user = iuserDao.getUser(uid);
			User_Info user_Info = iuserDao.getUserInfo(uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/profitlist");
			model.addAttribute("profitList", profitList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "useranchor");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", user.getNickname());
			model.addAttribute("userInfo", user_Info);
			model.addAttribute("type", type);
		} catch (Exception e) {
			log.error("主播魅力值流水列表", e);
		}
		return "useranchor/profitlist";
	}
	/**
	 * 
	 * <p>功能描述：用户奖励收益</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午3:40:40</p>
	 *
	 * @param uid 用户id
	 * @param virtual 奖励收益数
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveReward(long uid, int virtual, String remark, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			Profit profit = new Profit();
			profit.setUid(uid);
			profit.setGift_virtual(virtual);
			profit.setSend_uid(0);
			profit.setW_name(w_name);
			profit.setCreate_time(date);
			profit.setRemark(remark);
			profit.setType(4);
			int result = 0;
			result = iprofitDao.insertProfit(profit);
			imanage_RecordDao.insertManageRecord(w_name, "添加主播奖励收益信息", "t_profit", profit.getId(), IPUtil.getIp(request), date);
			if(result>0)
			{
				iuserDao.updateUserSurplusAnchorVirtual(0-(long)virtual, uid);
				iuserDao.updateUserTotalAnchorVirtual(0-(long)virtual, uid);

				String token = iuserDao.getUserInfoExtraToken(uid);
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				_user_push.setTitle(String.format("您获得了%s个魅力奖励！请注意查收~",virtual));
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(1);
				_user_push.setHref("");
				_user_push.setUrl("fm://myearning");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "保存成功");
			}

		} catch (Exception e) {
			log.error("添加主播奖励收益信息",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：用户追回收益</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午3:40:40</p>
	 *
	 * @param uid 用户id
	 * @param virtual 奖励收益数
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveReturn(long uid, int virtual, String remark, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			Profit profit = new Profit();
			profit.setUid(uid);
			profit.setGift_virtual(virtual);
			profit.setSend_uid(0);
			profit.setW_name(w_name);
			profit.setCreate_time(date);
			profit.setType(5);
			int result = 0;
			result = iprofitDao.insertProfit(profit);
			imanage_RecordDao.insertManageRecord(w_name, "添加主播追回收益信息", "t_profit", profit.getId(), IPUtil.getIp(request), date);
			if(result>0)
			{
				iuserDao.updateUserSurplusAnchorVirtual((long)virtual, uid);
				iuserDao.updateUserTotalAnchorVirtual((long)virtual, uid);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "保存成功");
			}

		} catch (Exception e) {
			log.error("添加主播追回收益信息",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：获取主播提现金额</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午4:02:49</p>
	 *
	 * @param uid 用户id
	 * @param withdraw_virtual 提现魅力值
	 * @return
	 */
	public double getWithdrawRmb(long uid, long withdraw_virtual)
	{	
		double withdraw_rmb = 0;
		try {
			Integer divide_proportion = iuserDao.getUserAnchorDivideProportion(uid);
			if(divide_proportion==null)
				divide_proportion = 0;
			withdraw_rmb = DoubleUtil.mul(DoubleUtil.div(withdraw_virtual, 100, 2), ((double)divide_proportion)/100);
		} catch (Exception e) {
			log.error("获取主播提现金额",e);
		}
		return withdraw_rmb;
	}
	/**
	 * 
	 * <p>功能描述：奖励主播钻石</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月3日 下午5:28:08</p>
	 *
	 * @param uid 主播用户uid
	 * @param add 增加钻石数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveBalanceVirsualAdd(long uid, int add, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			Date date = new Date();
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserBalanceVirtual(add, uid);
			imanage_RecordDao.insertManageRecord(w_name, "奖励主播钻石，uid:"+uid, "t_user", 0, IPUtil.getIp(request), date);
			if(result>0)
			{
				String token = iuserDao.getUserInfoExtraToken(uid);
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				_user_push.setTitle(String.format("您获得了%s个钻石奖励！请注意查收~",add));
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(1);
				_user_push.setHref("");
				_user_push.setUrl("fm://my");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "保存成功");
			}

		} catch (Exception e) {
			log.error("奖励主播钻石",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}

	public int closeAnchor(String f_uuid)
	{
		try {
			closeroom(f_uuid);
			/*HessianProxyFactory factory = new HessianProxyFactory();
			factory.setOverloadEnabled(true);
			IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, PropertyUtil.getValue("im_url"));
			iWebCommunication.closeroom(f_uuid);*/
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
		return 1;
	}
	/**
	 * 
	 * <p>功能描述：获取工会分成比例</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:19:46</p>
	 *
	 * @param id 
	 * @return
	 */
	public int getAnchorUnionDivideProportion(int union_id)
	{
		try {
			Integer divide_proportion = ianchor_UnionDao.getAnchorUnionDivideProportion(union_id);
			if(divide_proportion==null)
				divide_proportion = 0;
			return divide_proportion;
		} catch (Exception e) {
			log.error("获取工会分成比例", e);
		}
		return 0;
	}	
	/**
	 * 
	 * <p>功能描述：获取工会一对一分成比例</p>
	 * <p>创建人：</p>
	 *
	 * @param id 
	 * @return
	 */
	public int getAnchorUnionOneDivideProportion(int union_id)
	{
		try {
			Integer one_divide_proportion = ianchor_UnionDao.getAnchorUnionOneDivideProportion(union_id);
			if(one_divide_proportion==null)
				one_divide_proportion = 0;
			return one_divide_proportion;
		} catch (Exception e) {
			log.error("获取工会一对一分成比例", e);
		}
		return 0;
	}	
	/**
	 * 
	 * <p>功能描述：奖励用户钥匙数</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午4:33:35</p>
	 *
	 * @param id 用户uid
	 * @param add 增加数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int rewardUserKeyCount(long id, int add, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			result = iuserDao.updateUserKeyCount(id, add);
			if(result>0)
			{	
				w_name = String.valueOf(request.getAttribute("w_name"));
				Date date = new Date();
				ikeyDao.insertKey(id, 2, add, date, String.format("操作人：%s", w_name));
				String token = iuserDao.getUserInfoExtraToken(id);
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				_user_push.setTitle(String.format("您获得了%s把钥匙奖励！请注意查收~",add));
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(1);
				_user_push.setHref("");
				_user_push.setUrl("fm://shop");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				imanage_RecordDao.insertManageRecord(w_name, "修改用户钥匙数,id："+id, "t_user", 0, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("修改用户钥匙数",e);
		}
		return result;
	}

	/***
	 * 主播魅力值变更日志列表
	 * @param uid 主播uid
	 * @param page 页码
	 * @param model
	 */
	public String getAnchorVirtualChangeRecordList(long uid, int page, Model model)
	{
		try {
			List<Anchor_Virtual_Change_Record> anchor_Virtual_Change_RecordList = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeRecordList(uid, (page-1)*20, 20);
			int totalRecord = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeRecordCount(uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/virtualchangelist");
			User user = iuserDao.getUser(uid);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorVirtualChangeRecordList", anchor_Virtual_Change_RecordList);
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", user.getNickname());
			model.addAttribute("activeUrl", "useranchor");
		} catch (Exception e) {
			log.error("主播魅力值变更日志列表", e);
		}
		return "useranchor/virtualchangelist";
	}
	public String exportExcel(long uid, long f_uuid, String nickname, int operate_centerid, HttpServletResponse response)
	{
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();

			Date end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 23:59:59");
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(end_date); 
			calendar.add(Calendar.DATE,-1);
			Date start_date=calendar.getTime();
			String fileName = new String(("主播列表"+AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			XSSFSheet sheet = workBook.createSheet("sheet1");  
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			String[] titles = new String[]{"主播UID","房间号","昵称","运营中心","分成占比","代理","分成占比","总魅力值","可用魅力值","直播累计所得魅力值","推广累计所得魅力值"};
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  
			//List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeListWithDate(start_date, end_date);
			List<User_Anchor> user_AnchorList = iuserDao.getUserAnchorList(uid, f_uuid, nickname, operate_centerid, 0, Integer.MAX_VALUE);
			// 构建表体数据  

			/*	if (anchor_TimeList != null && anchor_TimeList.size() > 0)  
			{ */
			//Date timeDate = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")+" 08:00:00");
			for (int j = 0; j < user_AnchorList.size(); j++)  
			{  
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user_AnchorList.get(j).getUid());  

				//	User user = iuserDao.getUser(Long.parseLong(anchor_TimeList.get(j).getUid()));

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user_AnchorList.get(j).getF_uuid());   

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user_AnchorList.get(j).getNickname());

				/*	User_Anchor user_Anchor = iuserDao.getUserAnchor(user_AnchorList.get(j).getUid());
					Anchor_Union anchor_Union =  ianchor_UnionDao.getAnchorUnion(user_Anchor.getUnion_id());*/
				int operate_id=user_AnchorList.get(j).getOperate_center_id();//运营中心id
				Operate_Center operate_center=ioperate_centerdao.getoperateName(operate_id);
				String operateName=operate_center.getName();					
				int operate_divede=operate_center.getDivide();

				int agent_id=user_AnchorList.get(j).getAgent_id();
				Agent agent=iagentdao.getAgentByagentid(agent_id);
				String agentName=agent.getName();
				int agent_divide=agent.getDivide();



				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operateName);

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_divede);

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(agentName);

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(agent_divide);

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(user_AnchorList.get(j).getTotal_anchor_virtual());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(user_AnchorList.get(j).getSurplus_anchor_virtual());

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(user_AnchorList.get(j).getTotal_live_virtual());

				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(user_AnchorList.get(j).getTotal_promote_virtual());

				/*	cell = bodyRow.createCell(11);  
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(AuthUtil.formatDateToString(anchor_TimeList.get(j).getEnd_time()));*/

			}  
			/*} */
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		} catch (Exception e) {
			log.error("导出主播列表", e);
		}
		return null;
	}  
	public String upload(HttpServletRequest request, HttpServletResponse response)
	{
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = mulRequest.getFile("excel");  
		String filename = file.getOriginalFilename();  
		if (filename == null || "".equals(filename))  
		{  
			return null;  
		}  
		try  
		{  
			InputStream input = file.getInputStream();  
			XSSFWorkbook workBook = new XSSFWorkbook(input);  
			XSSFSheet sheet = workBook.getSheetAt(0);  
			if (sheet != null)  
			{  
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)  
				{  
					XSSFRow row = sheet.getRow(i);  
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++)  
					{  
						XSSFCell cell = row.getCell(j);  
						String cellStr = cell.toString();  
						System.out.print("【"+cellStr+"】 ");  
					}  
					System.out.println();  
				}  

			}  
		}  
		catch (Exception e)  
		{  
			e.printStackTrace();  
		}  
		return "/useranchor/exportexcel";
	}
	public void clear()
	{
		List<Anchor_Virtual_Change_Record> clearList = iconfigDao.getClearList();
		for (Anchor_Virtual_Change_Record anchor_Virtual_Change_Record : clearList) {
			User_Info user_Info = iuserDao.getUserInfo(anchor_Virtual_Change_Record.getUid());
			double money = DoubleUtil.div(DoubleUtil.mul(anchor_Virtual_Change_Record.getChange_virtual(), DoubleUtil.div(user_Info.getDivide_proportion(),100,2)),100,2);
			anchor_Virtual_Change_Record.setRemark(String.valueOf(money));
			anchor_Virtual_Change_Record.setCreate_time(new Date());
			ianchor_Virtual_Change_RecordDao.insertAnchorVirtualChangeRecord(anchor_Virtual_Change_Record);
		}
	}
	public String exportExcel2(HttpServletResponse response)
	{
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 00:00:00");
			String fileName = new String(("主播魅力值变更日志").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"编号","主播UID","主播房间号","昵称","变更魅力值","总魅力值","已使用魅力值","可用魅力值","操作时间"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  
			/*	if(union_id>0)
			{
				uidList = iuserDao.getUIdListWithUnionId(union_id);
			}*/
			List<Anchor_Virtual_Change_Record> anchor_Virtual_Change_RecordList = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeRecordList2(date);

			// 构建表体数据  
			int j=0;
			for (Anchor_Virtual_Change_Record anchor_Virtual_Change_Record : anchor_Virtual_Change_RecordList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 
				User user = iuserDao.getUser(anchor_Virtual_Change_Record.getUid());

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Change_Record.getId());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Change_Record.getUid());   

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user.getF_uuid());				
				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user.getNickname());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Change_Record.getChange_virtual());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Change_Record.getTotal_anchor_virtual());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Virtual_Change_Record.getTotal_change_virtual());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Virtual_Change_Record.getSurplus_anchor_virtual());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Virtual_Change_Record.getCreate_time());

				j++;
			} 
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		} catch (Exception e) {
			log.error("导出主播魅力变更", e);
		}
		return null;
	}

	public String exportAdmissionTicketExcel(String nickname,Long uid,Date start_date,Date end_date,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 00:00:00");
			String fileName = new String(("门票房统计").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"主播昵称","主播ID","开始时间","结束时间","总计播放时长","票价（钻）"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  

			Date  selected_start_date = null;
			Date  selected_end_date = null;	

			try {
				if ((start_date!=null)&&(end_date!=null)) {
					selected_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")+" 00:00:00");
					selected_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(end_date, "yyyy-MM-dd")+" 23:59:59");
				}
			} catch (ParseException e) {
				log.error("日期解析异常",e);
			}
			int totalCount = ianchor_TimeDao.getAnchorsAdmissionTicketCount(nickname, uid, selected_start_date, selected_end_date);
			List<AdmissionTicket> list = ianchor_TimeDao.getAnchorsAdmissionTicketList(nickname, uid, selected_start_date, selected_end_date,0,totalCount);

			// 构建表体数据  
			int j=0;
			for (AdmissionTicket admissionTicket  : list) {
				long total_time = admissionTicket.getEnd_time().getTime()-admissionTicket.getStart_time().getTime();
				admissionTicket.setSingle_total_time(getTotalTime(total_time));

				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(admissionTicket.getNickname());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(admissionTicket.getUid());   


				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(AuthUtil.formatDateToString(admissionTicket.getStart_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(AuthUtil.formatDateToString(admissionTicket.getEnd_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(admissionTicket.getSingle_total_time());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(admissionTicket.getFee_count());

				j++;
			} 
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		} catch (Exception e) {
			log.error("导出门票房统计", e);
		}
		return null;
	}

	public String exportAdmissionTicketRecordExcel(String nickname,Long uid,Date start_date,Date end_date,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 00:00:00");
			String fileName = new String(("门票修改记录").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"主播昵称","主播ID","开播时间","票价（钻）","操作人","操作时间"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  

			int totalCount = isquareFeeRecordDao.getSquareFeeRecordCount(uid, nickname, start_date, end_date);
			List<SquareFeeRecord> list = isquareFeeRecordDao.getSquareFeeRecordList(uid, nickname, start_date, end_date,0,totalCount);


			// 构建表体数据  
			int j=0;
			for (SquareFeeRecord record  : list) {


				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getNickname());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getUid());   


				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(AuthUtil.formatDateToString(record.getStart_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getFee_count());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getW_name());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(AuthUtil.formatDateToString(record.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));

				j++;
			} 
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		} catch (Exception e) {
			log.error("导出门票修改记录", e);
		}
		return null;
	}


	public String saveOneDisturb(long uid, int one_disturb, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			Date date = new Date();
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserAnchorOneDisturb(uid, one_disturb);
			imanage_RecordDao.insertManageRecord(w_name, "设置免打扰。uid:"+uid, "t_user_anchor", 0, IPUtil.getIp(request), date);
			if(result>0)
			{
				jsonObject.put("code", 0);
				jsonObject.put("msg", "设置免打扰成功");
			}

		} catch (Exception e) {
			log.error("设置免打扰",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "设置免打扰失败");
		}
		return jsonObject.toString();
	}
	public String getAnchorOneTimeList(long uid, Date date, Date end_date, int page, String url, Model model) {
		try {
			if(date==null)
				date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(new Date(),"yyyy-MM")+"-01","yyyy-MM-dd");
			if(end_date==null)
				end_date=new Date();
			model.addAttribute("end_date", AuthUtil.formatDateToString(end_date,"yyyy-MM-dd"));
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(end_date); 
			calendar.add(Calendar.DATE,1);
			end_date = calendar.getTime();
			List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList(uid, 1, date, end_date, (page-1)*20, 20);
			List<Anchor_Time> anchor_TimeList2 = new ArrayList<Anchor_Time>();
			for (Anchor_Time anchor_Time : anchor_TimeList) {
				if(anchor_Time.getStart_time().before(date))
					anchor_Time.setStart_time(date);

				Date  current_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd")+" 00:00:00");
				Date  current_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getEnd_time(), "yyyy-MM-dd")+" 00:00:00");
				if(current_start_date.before(current_end_date))
				{
					Anchor_Time temp = new Anchor_Time();
					temp.setUid(anchor_Time.getUid());
					temp.setStart_time(anchor_Time.getStart_time());
					temp.setEnd_time(anchor_Time.getEnd_time());
					temp.setStart_time(current_end_date);
					anchor_TimeList2.add(temp);
					anchor_Time.setEnd_time(AuthUtil.formatStringToDate(AuthUtil.formatDateToString(current_start_date,"yyyy-MM-dd")+" 23:59:59"));
					anchor_TimeList2.add(anchor_Time);
				}
				else
					anchor_TimeList2.add(anchor_Time);
			}
			List<Anchor_Time_Total> anchor_Time_TotalList =  new ArrayList<Anchor_Time_Total>();
			for (Anchor_Time anchor_Time : anchor_TimeList2) {
				long millminute = anchor_Time.getEnd_time().getTime() - anchor_Time.getStart_time().getTime();
				anchor_Time.setSingle_total_time(getTotalTime(millminute));
				String dateString = AuthUtil.formatDateToString(anchor_Time.getStart_time(),"MM月dd日");
				Long total_virtual = iprofitDao.getOneProfitSum(uid, anchor_Time.getStart_time(), anchor_Time.getEnd_time());
				if(total_virtual==null)
					total_virtual = 0L;
				anchor_Time.setTotal_virtual(total_virtual);
				boolean contain = false;
				for (Anchor_Time_Total anchor_Time_Total : anchor_Time_TotalList) {
					if(anchor_Time_Total.getDate().equals(dateString))
					{
						anchor_Time_Total.getAnchor_TimeList().add(anchor_Time);
						anchor_Time_Total.setTotal_millminute(anchor_Time_Total.getTotal_millminute()+millminute);
						contain = true;
						break;
					}
				}
				if(!contain)
				{
					Anchor_Time_Total anchor_Time_Total = new Anchor_Time_Total();
					anchor_Time_Total.setDate(dateString);
					anchor_Time_Total.getAnchor_TimeList().add(anchor_Time);
					anchor_Time_Total.setTotal_millminute(millminute);
					anchor_Time_TotalList.add(anchor_Time_Total);
				}
			}
			for (Anchor_Time_Total anchor_Time_Total : anchor_Time_TotalList) {
				anchor_Time_Total.setTotal(getTotalTime(anchor_Time_Total.getTotal_millminute()));
			}
			int totalRecord = ianchor_TimeDao.getAnchorTimeCount(uid, 0, date);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName(url+"/timelist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorTimeTotalList", anchor_Time_TotalList);
			model.addAttribute("uid", uid);
			model.addAttribute("date", AuthUtil.formatDateToString(date,"yyyy-MM-dd"));
			model.addAttribute("activeUrl", url);
		} catch (Exception e) {
			log.error("主播一对一时长列表", e);
		}
		return "oneanchor/timelist";
	}
	public String getUserAnchorAdminList(long f_uuid, Model model) {
		try {
			List<User_Admin> user_AdminList = iuser_AdminDao.getUserAdminList(f_uuid, 0, 10);
			List<User_Admin> user_ControlList = iuser_AdminDao.getUserControlList(f_uuid, 0, 10);
			model.addAttribute("user_AdminList", user_AdminList);	
			model.addAttribute("user_ControlList", user_ControlList);		
			model.addAttribute("activeUrl", "useranchor");	
		} catch (Exception e) {
			log.error("主播房管列表", e);
		}
		return "useranchor/adminlist";
	}
	public int deleteAdmin(int id, long uid, HttpServletRequest request)
	{
		try {
			
			String user_token = iuserDao.getUserInfoExtraToken(uid);
			iuser_AdminDao.deleteUserAdmin(id, new Date());
			RedisUtil.SetHsetJedis(0, user_token, "is_admin", "0");
			String w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除"+"“"+"uID："+uid+"”"+"房管", "t_user_admin", id, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			Log4jHandel.myerror("删除房管",e);
		}
		return 1;
	}
	public int deleteControl(int id, long uid, HttpServletRequest request)
	{
		try {
			String user_token = iuserDao.getUserInfoExtraToken(uid);
			iuser_AdminDao.deleteUserControl(id, new Date());
			RedisUtil.SetHsetJedis(0, user_token, "is_control", "0");
			String w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除"+"“"+"uID："+uid+"”"+"场控", "t_user_admin", id, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			Log4jHandel.myerror("删除场控",e);
		}
		return 1;
	}
}

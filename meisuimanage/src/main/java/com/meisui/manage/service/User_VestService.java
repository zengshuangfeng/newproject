package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.forman.foundation.library.StringUtil;
import com.forman.log4j.Log4jHandel;
import com.meisui.manage.dao.IconfigDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Area;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.entity.User_Info_Extra;
import com.meisui.manage.entity.User_Video;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.meisui.manage.utils.QiNiuUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>文件名称：UserVestService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月10日 上午9:47:34
 */
@Service
public class User_VestService {
	private static Logger log = Logger.getLogger(User_VestService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IconfigDao iconfigDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：马甲列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午10:07:33</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getList(long uid, String nickname, int page, Model model)
	{
		try {
			List<User> userList = iuserDao.getUserVestList(uid, nickname, (page-1)*20, 20);
			for (User user : userList) {
				User_Info user_Info = iuserDao.getUserInfo(user.getId());
				if(user_Info!=null)
				{
					user.setFollower_count(user_Info.getFollower_count());
					user.setTotal_anchor_virtual(user_Info.getTotal_anchor_virtual());
				}
				user.setVideo_count(0);
				user.setReply_count(0);
			}
			int totalRecord = iuserDao.getUserVestCount(uid, nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("uservest/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userList", userList);
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("activeUrl", "uservest");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("马甲列表", e);
		}
		return "uservest/list";
	}
	/**
	 * 
	 * <p>功能描述：马甲添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午11:16:14</p>
	 *
	 * @param model
	 * @return
	 */
	public String add(Model model)
	{
		try {
			List<Area> areaList = iconfigDao.getAreaList(0);
			model.addAttribute("areaList", areaList);
			model.addAttribute("activeUrl", "uservest");
		} catch (Exception e) {
			log.error("马甲添加页面", e);
		}
		return "uservest/add";
	}
	/**
	 * 
	 * <p>功能描述：获取地区列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年5月5日 上午10:15:29</p>
	 *
	 * @param f_id 父id
	 * @return
	 */
	public String getAreaList(int f_id)
	{
		try {
			List<Area> areaList = iconfigDao.getAreaList(f_id);
			return JSONArray.fromObject(areaList).toString();
		} catch (Exception e) {
			log.error("获取地区列表", e);
		}
		return "[]";
	}
	/**
	 * 
	 * <p>功能描述：马甲编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午11:16:14</p>
	 *
	 * @param id 用户id
	 * @param model
	 * @return
	 */
	public String edit(long id, Model model)
	{
		try {
			User user = iuserDao.getUser(id);
			int age = 0;
			if(!StringUtils.isBlank(user.getBirthday()))
			{
				age = AuthUtil.getAge(AuthUtil.formatStringToDate(user.getBirthday(), "yyyy-MM-dd"));
			}
			List<Area> areaList = iconfigDao.getAreaList(0);
			List<Area> areaList2 = new ArrayList<Area>();
			if(user.getProvince()>0)
				areaList2 = iconfigDao.getAreaList(user.getProvince());
			model.addAttribute("userVest", user);
			model.addAttribute("age", age);
			model.addAttribute("areaList", areaList);
			model.addAttribute("areaList2", areaList2);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "uservest");
		} catch (Exception e) {
			log.error("马甲编辑页面", e);
		}
		return "uservest/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存马甲信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午10:26:45</p>
	 *
	 * @param id 用户id
	 * @param nickname 昵称
	 * @param sex 性别
	 * @param head 头像
	 * @param level 等级
	 * @param area 地区
	 * @param province 省份
	 * @param city 城市
	 * @param age 年龄
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveInfo(long id, String nickname, int sex, String head, int level, String area, int province, int city, int age, String w_name, HttpServletRequest request)
	{
		try
		{
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			User _user = new User();
			String token = StringUtil.getToken();
			_user.setNickname(nickname);
			_user.setRegister_time(date);
			_user.setSex(sex);
			_user.setHead(head);
			_user.setLevel(level);
			_user.setIsvest(1);
			_user.setId(id);
			_user.setArea(area);
			_user.setProvince(province);
			_user.setCity(city);
			Date vip_time = AuthUtil.formatStringToDate("1970-01-01 00:00:00");
			_user.setVip_time(vip_time);
			if(age>0)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(new Date()); 
				calendar.add(Calendar.YEAR, 0-age);
				_user.setBirthday(AuthUtil.formatStringToDate(calendar.get(Calendar.YEAR)+"-01-01", "yyyy-MM-dd"));
			}
			int result = 0;
			if(id==0)
			{
				String f_uuid = RedisUtil.rpoplpush(4, "uid_make");
				String invite_code = RedisUtil.rpoplpush(4, "invite_make");
				_user.setF_uuid(f_uuid);
				_user.setInvite_code(invite_code);
				result = iuserDao.insertUser(_user);
				User_Info _user_info = new User_Info();
				_user_info.setUid(_user.getId());
				iuserDao.insertUserInfo(_user_info);
				User_Info_Extra _user_info_extra = new User_Info_Extra();
				_user_info_extra.setT1("");
				_user_info_extra.setToken(token);
				_user_info_extra.setUid(_user.getId());
				iuserDao.insertUserInfoExtra(_user_info_extra);
				insert(_user.getId(), "4", "", JSONObject.fromObject(_user, StringUtil.GetJsongFilter(new String[]{"id","total_balance_virtual","last_use_time","last_login_time","w_name","video_count","reply_count","last_video_time","total_anchor_virtual","update_time"})), JSONObject.fromObject(_user_info, StringUtil.GetJsongFilter(new String[]{"id","uid","balance_virtual","total_gift","total_anchor_virtual","surplus_anchor_virtual","wealth_level","divide_proportion"})), token);
			}
			else {
				result = iuserDao.updatetUser(_user);
				token = iuserDao.getUserInfoExtraToken(id);
				update(token, nickname, sex, head, level);
			}
			imanage_RecordDao.insertManageRecord(w_name, "添加马甲号，UID："+_user.getId(), "t_user", 0, IPUtil.getIp(request), date);
			if(result > 0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error("保存版本更新信息", ex);
			ex.printStackTrace();
		}	
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	private void insert(long id, String f, String v, JSONObject _user, JSONObject _user_info, String token) {

		try {

			HashMap<String, String> map = new HashMap<>();

			map.put("uid", String.valueOf(id));
			map.put("anchor_state", "0");
			map.put(f, v);
			putMap(map, _user);
			putMap(map, _user_info);
			RedisUtil.SetHmsetJedis(0, token, map);
			RedisUtil.SetHsetJedis(0, token, "is_forbid", "0");
			RedisUtil.SetHsetJedis(0, token, "forbid_hour", "0");
			RedisUtil.SetHsetJedis(0, token, "forbid_start_time", "");
			RedisUtil.SetHsetJedis(0, token, "is_first", "1");
			RedisUtil.SetHsetJedis(0, token, "is_im_first", "1");
			RedisUtil.SetHsetJedis(0, token, "update_time", "");
		} catch (Exception e) {
			Log4jHandel.myerror("AddDriveid", e);
		}
	}
	private void update(String token, String nickname, int sex, String head, int level)
	{
		RedisUtil.SetHsetJedis(0, token, "nickname", nickname);
		RedisUtil.SetHsetJedis(0, token, "sex", String.valueOf(sex));
		RedisUtil.SetHsetJedis(0, token, "head", head);
		RedisUtil.SetHsetJedis(0, token, "level", String.valueOf(level));
	}
	private void putMap(HashMap<String, String> map, JSONObject jsonObject)
	{
		@SuppressWarnings("rawtypes")
		Iterator it = jsonObject.keys();  
		while (it.hasNext())  
		{  
			String key = String.valueOf(it.next());  
			String value = jsonObject.get(key).toString();  
			if(!map.containsKey(key))
				map.put(key, value);  
		}  
	}
	/**
	 * 
	 * <p>功能描述：保存马甲视频信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 下午2:13:22</p>
	 *
	 * @param uid 用户uid
	 * @param f_uuid 用户房间号
	 * @param cover 视频封面
	 * @param video 视频
	 * @param seconds 视频秒数
	 * @param source 来源 0用户发布 1美拍
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveVideo(long uid, long f_uuid, String cover, String video, int seconds, int source, String w_name, HttpServletRequest request)
	{
		try
		{
			w_name = String.valueOf(request.getAttribute("w_name"));
			User user = iuserDao.getUser(uid);
			Date date = new Date();
			User_Video user_Video = new User_Video();
			user_Video.setUid(uid);
			user_Video.setF_uuid(f_uuid);
			String token = iuserDao.getUserInfoExtraToken(uid);
			user_Video.setToken(token);
			user_Video.setCreate_time(date);
			String newvideo = video;
			if(!StringUtils.isBlank(newvideo))
			{
				try {
					QiNiuUtil qiNiuUtil = new QiNiuUtil();
					switch (source) {
					case 1://美拍
						newvideo = video.replace("PeiPei_", "MeiPai_");
						qiNiuUtil.renameFile("peipei", video, newvideo);
						break;
					}
				} catch (Exception e) {
					log.error("七牛重命名视频", e);
				}
				QiNiuUtil qiNiuUtil = new QiNiuUtil();
				double size = qiNiuUtil.getVideoSize(PropertyUtil.getValue("meisui_pic_url")+newvideo);
				size = size/1024/1024;
				user_Video.setSize(size);
			}
			if(StringUtils.isBlank(cover))
				cover = newvideo + "?vframe/jpg/offset/0";
			user_Video.setCover(cover);
			user_Video.setVideo(newvideo);
			if(seconds==0)
			{
				QiNiuUtil qiNiuUtil = new QiNiuUtil();
				seconds = qiNiuUtil.getVideoSecond(PropertyUtil.getValue("meisui_pic_url")+video);
			}
			user_Video.setSeconds(seconds);
			user_Video.setSource(source);
			user_Video.setSex(String.valueOf(user.getSex()));
			int result = 0;
			imanage_RecordDao.insertManageRecord(w_name, "添加马甲视频，UID："+uid, "t_user_video", user_Video.getId(), IPUtil.getIp(request), date);
			if(result > 0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error("保存马甲视频信息", ex);
			ex.printStackTrace();
		}	
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	public int getNicknameExist(long uid, String nickname)
	{
		try {
			Long id = iuserDao.getUserNicknameExist(nickname);
			if(id==null)
				return 0;
			if(id!=null&&id==uid)
				return 0;
		} catch (Exception e) {
			log.error("获取昵称是否存在", e);
		}
		return 1;
	}
	/**
	 * 
	 * <p>功能描述：马甲删除，做封号处理</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 用户id
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int deleteUserVest(long id, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			result = iuserDao.updateUserIsBlocked(id, 1);
			if(result>0)
			{
				w_name = String.valueOf(request.getAttribute("w_name"));
				String token = iuserDao.getUserInfoExtraToken(id);
				RedisUtil.SetHsetJedis(0, token, "is_blocked", "1");
				iuserDao.updateUserAnchorState(0, 0, String.valueOf(id));
				RedisUtil.SetHsetJedis(0, token, "anchor_type", "0");
				RedisUtil.SetHsetJedis(0, token, "anchor_state", "0");
				imanage_RecordDao.insertManageRecord(w_name, "马甲删除，做封号处理,id："+id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("马甲删除，做封号处理",e);
		}
		return result;
	}
}

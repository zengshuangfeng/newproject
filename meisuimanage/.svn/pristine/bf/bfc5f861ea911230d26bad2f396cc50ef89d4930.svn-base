package com.meisui.manage.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;




import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.caucho.hessian.client.HessianProxyFactory;
import com.forman.foundation.library.DateUtils;
import com.forman.foundation.library.RedisUtil;
import com.forman.log4j.Log4jHandel;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_ForbidDao;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Forbid;
import com.meisui.manage.interfaces.IWebCommunication;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：User_ForbidService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午6:11:38
 */
@Service
public class User_ForbidService extends HessianService {
	private static Logger log = Logger.getLogger(User_ForbidService.class.getClass());
	@Autowired
	private Iuser_ForbidDao iuser_ForbidDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：主播下用户禁言列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午6:34:20</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getUserForbidList(long attention_uid, long f_uuid, int page, Model model)
	{
		try {
			List<User_Forbid> user_ForbidList = iuser_ForbidDao.getUserForbidList(f_uuid, (page-1)*20, 20);
			int totalRecord = iuser_ForbidDao.getUserForbidCount(f_uuid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/userforbid/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userForbidList", user_ForbidList);
			model.addAttribute("activeUrl", "useranchor");
			model.addAttribute("attention_uid", attention_uid);
			model.addAttribute("f_uuid", f_uuid);
		} catch (Exception e) {
			log.error("主播下用户禁言列表", e);
		}
		return "userforbid/list";
	}
	/**
	 * 
	 * <p>功能描述：用户禁言列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月12日 下午2:26:42</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 昵称
	 * @param is_forbid 是否禁言 -1全部 0否 1是
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getUserForbidList(long uid, long f_uuid, String nickname, int is_forbid, int page, Model model)
	{
		try {
			Date date = new Date();
			List<User_Forbid> user_ForbidList = new ArrayList<User_Forbid>();
			List<User> userList = iuserDao.getUserForbidList(uid, f_uuid, nickname, is_forbid, date, (page-1)*20, 20);
			for (User user : userList) {
				User_Forbid user_Forbid = setUser_Forbid(user);
				user_Forbid.setIs_from(1);
				user_ForbidList.add(user_Forbid);
			}
			int totalRecord = iuserDao.getUserForbidCount(uid, f_uuid, nickname, is_forbid, date);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("userforbid/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userForbidList", user_ForbidList);
			model.addAttribute("activeUrl", "userforbid");
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("is_forbid", is_forbid);
		} catch (Exception e) {
			log.error("用户禁言列表", e);
		}
		return "userforbid/alllist";
	}

	private User_Forbid setUser_Forbid(User user) throws ParseException
	{
		User_Forbid user_Forbid = new User_Forbid();
		user_Forbid.setUid(user.getId());
		user_Forbid.setF_uuid(user.getF_uuid());
		user_Forbid.setNickname(user.getNickname());
		user_Forbid.setStart_time(user.getForbid_start_time());
		if(user.getForbid_hour()>0)
		{
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(user.getForbid_start_time()); 
			calendar.add(Calendar.HOUR,user.getForbid_hour());
			user_Forbid.setEnd_time(calendar.getTime());
		}
		user_Forbid.setW_name(user.getW_name());
		user_Forbid.setUpdate_time(user.getUpdate_time());
		Date current_time = new Date();
		if(user.getIs_forbid()==1&&user.getForbid_hour()==0)
		{
			user_Forbid.setStatus_name("禁言中");
		}
		else if (user.getIs_forbid()==1&&user.getForbid_hour()>0&&AuthUtil.formatStringToDate(user_Forbid.getEnd_time()).after(current_time))
		{
			user_Forbid.setStatus_name("禁言中");
		}
		else {
			user_Forbid.setStatus_name("可发言");
		}
		return user_Forbid;
	}
	/**
	 * 
	 * <p>功能描述：获取用户是否被禁言</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月12日 下午3:28:12</p>
	 *
	 * @param uid 用户Uid
	 * @return
	 */
	public int getIsForbid(long uid, long f_uuid)
	{
		if(uid==0&&f_uuid>0)
		{
			uid = iuserDao.getUserIdWithF_uuid(String.valueOf(f_uuid));
		}
		User user = iuserDao.getUserForbid(uid);
		Date end_time = null;
		if(user.getIs_forbid()==1&&user.getForbid_hour()>0)
		{
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(user.getForbid_start_time()); 
			calendar.add(Calendar.HOUR,user.getForbid_hour());
			end_time = calendar.getTime();
		}
		if(user.getIs_forbid()==1&&user.getForbid_hour()==0)
		{
			return 1;
		}
		else if (user.getIs_forbid()==1&&user.getForbid_hour()>0&&end_time.after(new Date()))
		{
			return 1;
		}
		else {
			return 0;
		}
	}
	/**
	 * 
	 * <p>功能描述：添加用户禁言</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月12日 下午3:43:16</p>
	 *
	 * @param uid 用户uid
	 * @param hour 小时数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveUserForbid(long uid, long f_uuid, int hour, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {	
			w_name = String.valueOf(request.getAttribute("w_name"));		
			Date date = new Date();
			if(uid==0&&f_uuid>0)
			{
				uid = iuserDao.getUserIdWithF_uuid(String.valueOf(f_uuid));
			}
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
				/*try {
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, PropertyUtil.getValue("im_url"));
					iWebCommunication.gaguid(token, String.valueOf(hour));
					iWebCommunication.isjin(token, "0", false, false);
				} catch (Exception e) {
					Log4jHandel.myerror("通知im异常",e);
				}*/
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
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月12日 下午3:43:16</p>
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
				/*try {
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, PropertyUtil.getValue("im_url"));
					iWebCommunication.isjin(token, "0", false, true);
				} catch (Exception e) {
					Log4jHandel.myerror("通知im异常",e);
				}*/
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
}

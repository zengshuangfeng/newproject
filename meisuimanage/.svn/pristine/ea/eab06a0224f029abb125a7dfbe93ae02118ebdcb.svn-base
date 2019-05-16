package com.meisui.manage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.DateUtils;
import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IreportDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_PushDao;
import com.meisui.manage.entity.Report;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.entity.User_Push;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

/**
 * <p>文件名称：ReportService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 下午2:49:53
 */
@Service
public class ReportService extends HessianService {
	private static Logger log = Logger.getLogger(ReportService.class.getClass());
	@Autowired
	private IreportDao ireportDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Iuser_PushDao iuser_PushDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：举报主播列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 下午2:54:51</p>
	 *
	 * @param uid 被举报人uid
	 * @param nickname 被举报人昵称
	 * @param o_uid 举报人uid
	 * @param o_nickname 举报人昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getAnchorReportList(long uid, long f_uuid, String nickname, long o_uid, String o_nickname, int page, Model model)	
	{
		try {
			List<Report> reportList = ireportDao.getAnchorReportList(uid, f_uuid, nickname, o_uid, o_nickname, (page-1)*20, 20);
			int totalRecord = ireportDao.getAnchorReportCount(uid, f_uuid, nickname, o_uid, o_nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorreport/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("reportList", reportList);
			model.addAttribute("activeUrl", "anchorreport");
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("o_uid", o_uid);
			model.addAttribute("o_nickname", o_nickname);
		} catch (Exception e) {
			log.error("举报主播列表", e);
		}
		return "anchorreport/list";
	}
	/**
	 * 
	 * <p>功能描述：举报用户列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 下午2:54:51</p>
	 *
	 * @param uid 被举报人uid
	 * @param nickname 被举报人昵称
	 * @param o_uid 举报人uid
	 * @param o_nickname 举报人昵称
	 * @param islook 是否处理 0等待处理 1已禁言 2已忽略
	 * @param page 页码
	 * @param model
	 * @return
	 */
	/*public String getUserReportList(long uid, String nickname, long o_uid, String o_nickname, int islook, int page, Model model)	
	{
		try {
			List<Report> reportList = ireportDao.getUserReportList(islook, uid, nickname, o_uid, o_nickname, (page-1)*20, 20);
			for (Report report : reportList) {
				if(report.getIs_forbid()==1)
				{
					if(report.getForbid_hour()>0)
					{
						Calendar calendar = new GregorianCalendar(); 
						calendar.setTime(report.getForbid_start_time()); 
						calendar.add(Calendar.HOUR,report.getForbid_hour());
						if(calendar.getTime().before(new Date()))//禁言时间已到
							report.setIs_forbid(0);
					}
				}
			}
			int totalRecord = ireportDao.getUserReportCount(islook, uid, nickname, o_uid, o_nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("userreport/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("reportList", reportList);
			model.addAttribute("activeUrl", "userreport");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("o_uid", o_uid);
			model.addAttribute("o_nickname", o_nickname);
			model.addAttribute("islook", islook);
		} catch (Exception e) {
			log.error("举报用户列表", e);
		}
		return "userreport/list";
	}*/
	public String getUserReportList(long uid, String nickname, Date start_time, Date end_time, int page, Model model)	
	{
		try {
			String end_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<Report> reportList = ireportDao.getUserReportList(uid, nickname, start_time, end_time, (page-1)*20, 20);
			for (Report report : reportList) {
				User_Info user_Info = iuserDao.getUserInfo(report.getUid());
				if(user_Info !=null)
				report.setTotal_anchor_virtual(user_Info.getTotal_anchor_virtual());
				report.setFollower_count(user_Info.getFollower_count());
				report.setVideo_count(0);
			}
			int totalRecord = ireportDao.getUserReportCount(uid, nickname, start_time, end_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("userreport/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("reportList", reportList);
			model.addAttribute("activeUrl", "userreport");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", end_timeString);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("举报用户列表", e);
		}
		return "userreport/list2";
	}
	/**
	 * 
	 * <p>功能描述：更新用户举报状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月16日 上午10:56:15</p>
	 *
	 * @param id 用户举报表id
	 * @param islook 是否处理 0等待处理 1已禁言 2已忽略
	 * @param w_name 禁言操作者
	 * @param request
	 * @return
	 */
	public int updateReportIsLook(int id, int islook, String w_name, HttpServletRequest request)
	{
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = ireportDao.updateReportIsLook(id, islook, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "更新用户举报状态", "t_report", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("更新用户举报状态",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：举报列表用户禁言</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月16日 上午11:03:35</p>
	 *
	 * @param id 用户举报表id
	 * @param uid 用户uid
	 * @param hour 禁言小时数 0表示永久性
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveUserForbid(int id, long uid, int hour, String w_name, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {			
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			int result = iuserDao.saveUserForbid(uid, date, hour, w_name, date);
			imanage_RecordDao.insertManageRecord(w_name, "添加用户禁言，uid:"+uid, "t_user", 0, IPUtil.getIp(request), date);
			if(result>0)
			{
				ireportDao.updateReportIsLook(id, 1, w_name, date);
				String token = iuserDao.getUserInfoExtraToken(uid);
				RedisUtil.SetHsetJedis(0, token, "is_forbid", "1");
				RedisUtil.SetHsetJedis(0, token, "forbid_hour", String.valueOf(hour));
				RedisUtil.SetHsetJedis(0, token, "forbid_start_time", DateUtils.sdf2.format(date));
				//通知im服务。。
				gaguid(token, hour);
				/*try {
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, PropertyUtil.getValue("im_url"));
					iWebCommunication.gaguid(token, String.valueOf(hour));
				} catch (Exception e) {
					Log4jHandel.myerror("通知im异常",e);
				}*/
				jsonObject.put("code", 0);
				jsonObject.put("msg", "保存成功");
			}

		} catch (Exception e) {
			log.error("举报列表用户禁言",e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：修改用户头像锁定</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param head_lock 是否锁定 1是 0否
	 * @param w_name
	 * @param request
	 * @return
	 */
	public int updateUserHeadLock(long id, int head_lock, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			result = iuserDao.updateUserHeadLock(id, head_lock);
			if(result>0)
			{
				w_name = String.valueOf(request.getAttribute("w_name"));
				String token = iuserDao.getUserInfoExtraToken(id);
				Date date = new Date();
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				if(head_lock==1)
				{
					RedisUtil.SetHsetJedis(0, token, "head", "default.png");
					_user_push.setTitle("您的头像含有不良信息，已被设为违规头像!");
				}
				else
				{
					_user_push.setTitle("您的头像已解锁，您可以在“我-基本资料”中修改您的头像。");
				}
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(0);
				_user_push.setHref("");
				_user_push.setUrl("");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				imanage_RecordDao.insertManageRecord(w_name, "修改用户头像锁定,id："+id, "t_user", 0, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("修改用户头像锁定",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：修改用户昵称锁定</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param nickname_lock 是否锁定 1是 0否
	 * @param w_name
	 * @param request
	 * @return
	 */
	public int updateUserNicknameLock(long id, int nickname_lock, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			result = iuserDao.updateUserNicknameLock(id, nickname_lock);
			if(result>0)
			{
				w_name = String.valueOf(request.getAttribute("w_name"));
				String token = iuserDao.getUserInfoExtraToken(id);
				Date date = new Date();
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				if(nickname_lock==1)
				{
					RedisUtil.SetHsetJedis(0, token, "nickname", "违规昵称");
					_user_push.setTitle("您的昵称含有不良信息，已被设为违规昵称!");
				}
				else
				{
					_user_push.setTitle("您的昵称已解锁，您可以在“我-基本资料”中修改您的昵称。");
				}
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(0);
				_user_push.setHref("");
				_user_push.setUrl("");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				imanage_RecordDao.insertManageRecord(w_name, "修改用户昵称锁定,id："+id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("修改用户昵称锁定",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：封号/解封用户</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 用户id
	 * @param is_blocked 是否封号 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserIsBlocked(long id, int is_blocked, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			result = iuserDao.updateUserIsBlocked(id, is_blocked);
			if(result>0)
			{
				w_name = String.valueOf(request.getAttribute("w_name"));
				String token = iuserDao.getUserInfoExtraToken(id);
				RedisUtil.SetHsetJedis(0, token, "is_blocked", String.valueOf(is_blocked));
				if(is_blocked==1)
				{
					iuserDao.updateUserAnchorState(0, 0, String.valueOf(id));
					RedisUtil.SetHsetJedis(0, token, "anchor_type", "0");
					RedisUtil.SetHsetJedis(0, token, "anchor_state", "0");
				}				
				//通知客户端
				imanage_RecordDao.insertManageRecord(w_name, "封号/解封用户,id："+id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("封号/解封用户",e);
		}
		return result;
	}
}

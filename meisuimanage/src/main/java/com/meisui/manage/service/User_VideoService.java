package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IadminDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Admin;
import com.meisui.manage.entity.Reply_Video;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Video;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：User_VideoService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月6日 上午11:13:05
 */
@Service
public class User_VideoService {
	private static Logger log = Logger.getLogger(User_VideoService.class.getClass());
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IadminDao iadminDao;
	/**
	 * 
	 * <p>功能描述：视频审核列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 上午11:28:36</p>
	 *
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param status 状态
	 * @param page 页码
	 * @param model
	 * @return
	 */
	/*public String getAuditList(Date start_time, Date end_time, int status, int page, Model model)
	{
		try {
			String e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<User_Video> user_VideoList = iuser_VideoDao.getAuditUserVideoList(status, start_time, end_time, (page-1)*12, 12);
			for (User_Video user_Video : user_VideoList) {
				user_Video.setNickname(RedisUtil.Gethget(0, user_Video.getToken(), "nickname"));
				String sex = RedisUtil.Gethget(0, user_Video.getToken(), "sex");
				if(!StringUtils.isBlank(sex))
				{
					switch (sex) {
					case "0":
						user_Video.setSex("男");
						break;
					case "1":
						user_Video.setSex("女");
						break;
					default:
						user_Video.setSex("");
						break;
					}
				}
			}
			int totalRecord = iuser_VideoDao.getAuditUserVideoCount(status, start_time, end_time);
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("auditvideo/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			model.addAttribute("status", status);
			model.addAttribute("userVideoList", user_VideoList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "auditvideo");
		} catch (Exception e) {
			log.error("视频审核列表", e);
		}
		return "video/auditlist";
	}*/
	public String getAuditVideoList(int video_id, String nickname, String cookie, Model model)
	{
		try {
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<User_Video> user_VideoList = new ArrayList<User_Video>();
			List<Integer> videoIdList = new ArrayList<Integer>();
			String [] admStrings = cookie.split(",");
			Admin admin= iadminDao.getAdminByName(admStrings[0]);
			if(video_id>0 || !StringUtils.isBlank(nickname))
			{
				user_VideoList = null;
			}
			else {
				videoIdList = null;
				if(videoIdList.size()>0)
				{
					List<User_Video> noAuditList = null;
					user_VideoList.addAll(noAuditList);
				}
				int rows = 50 - videoIdList.size();
				if(rows>0)
				{
					List<User_Video> otherVideoList = null;
					user_VideoList.addAll(otherVideoList);
				}
			}
			for (User_Video user_Video : user_VideoList) {
				user_Video.setNickname(RedisUtil.Gethget(0, user_Video.getToken(), "nickname"));
				user_Video.setHead(RedisUtil.Gethget(0, user_Video.getToken(), "head"));
				String sex = RedisUtil.Gethget(0, user_Video.getToken(), "sex");
				if(!StringUtils.isBlank(sex))
				{
					switch (sex) {
					case "0":
						user_Video.setSex("男");
						break;
					case "1":
						user_Video.setSex("女");
						break;
					default:
						user_Video.setSex("");
						break;
					}
				}
				List<String> picList = new ArrayList<String>();
				if(user_Video.getSeconds()>0)
				{
					if(user_Video.getSeconds()<5)
					{
						for(int i=0;i<user_Video.getSeconds();i++)
						{
							picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+i);
						}
					}
					else {
						if(user_Video.getSeconds()>1000)
							user_Video.setSeconds(user_Video.getSeconds()/1000);
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+0);
						int num = user_Video.getSeconds()/2;
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(num-1));
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+num);
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(num+1));
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(user_Video.getSeconds()-1));
					}
				}
				user_Video.setPicList(picList);
			}
			model.addAttribute("video_id", video_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("userVideoList", user_VideoList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "auditvideo");
		} catch (Exception e) {
			log.error("通过视频列表", e);
		}
		return "video/auditlist2";
	}
	/**
	 * 
	 * <p>功能描述：视频发布列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午2:12:49</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 用户昵称
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param is_recommend 是否推荐 1是 0否
	 * @param isvest -1 全部 0普通用户 1马甲用户
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getPublishList(long uid, String nickname, Date start_time, Date end_time, int is_recommend, int isvest, int page, Model model)
	{
		try {
			String e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<User_Video> user_VideoList = null;
			for (User_Video user_Video : user_VideoList) {
				user_Video.setNickname(RedisUtil.Gethget(0, user_Video.getToken(), "nickname"));
				String sex = RedisUtil.Gethget(0, user_Video.getToken(), "sex");
				if(!StringUtils.isBlank(sex))
				{
					switch (sex) {
					case "0":
						user_Video.setSex("男");
						break;
					case "1":
						user_Video.setSex("女");
						break;
					default:
						user_Video.setSex("");
						break;
					}
				}
				User user =  iuserDao.getUser(user_Video.getUid());
				user_Video.setIsvest(user.getIsvest());
			}
			int totalRecord = 0;
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("publishvideo/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			model.addAttribute("is_recommend", is_recommend);
			model.addAttribute("isvest", isvest);
			model.addAttribute("userVideoList", user_VideoList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "publishvideo");
		} catch (Exception e) {
			log.error("视频发布列表", e);
		}
		return "video/publishlist";
	}
	/**
	 * 
	 * <p>功能描述：视频回复列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午2:12:49</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 用户昵称
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param is_blocked 0正常用户 1被封号用户
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getReplyList(long uid, String nickname, Date start_time, Date end_time, int is_blocked, int page, Model model)
	{
		try {
			String e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<Reply_Video> reply_VideoList = null;
			for (Reply_Video reply_Video : reply_VideoList) {
				reply_Video.setS_nickname(RedisUtil.Gethget(0, reply_Video.getS_token(), "nickname"));
				reply_Video.setR_nickname(RedisUtil.Gethget(0, reply_Video.getR_token(), "nickname"));
				String sex = RedisUtil.Gethget(0, reply_Video.getS_token(), "sex");
				if(!StringUtils.isBlank(sex))
				{
					switch (sex) {
					case "0":
						reply_Video.setSex("男");
						break;
					case "1":
						reply_Video.setSex("女");
						break;
					default:
						reply_Video.setSex("");
						break;
					}
				}
				User user = iuserDao.getUser(reply_Video.getS_uid());
				reply_Video.setUser_is_blocked(user.getIs_blocked());
			}
			int totalRecord = 0;
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("replyvideo/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			model.addAttribute("is_blocked", is_blocked);
			model.addAttribute("replyVideoList", reply_VideoList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "replyvideo");
		} catch (Exception e) {
			log.error("视频回复列表", e);
		}
		return "video/replylist";
	}
	/**
	 * 
	 * <p>功能描述：更新用户视频状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 视频id
	 * @param status 状态 0未审核 1通过 2屏蔽
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserVideoStatus(int id, int status, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = 0;
			if(result>0)
			{
				imanage_RecordDao.insertManageRecord(w_name, "更新用户视频状态", "t_user_video", id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("更新用户视频状态",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：推荐用户视频</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 视频id
	 * @param is_recommend 是否推荐1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserVideoIsRecommend(int id, int is_recommend, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {
			if(result>0)
			{
				w_name = String.valueOf(request.getAttribute("w_name"));
				imanage_RecordDao.insertManageRecord(w_name, "推荐用户视频", "t_user_video", id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("推荐用户视频",e);
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
	/**
	 * 
	 * <p>功能描述：视频通过列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月27日 下午3:36:24</p>
	 *
	 * @param video_id 视频id
	 * @param nickname 昵称
	 * @param admin_id 审核者id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getPassVideoList(int video_id, String nickname, int admin_id, int page, Model model)
	{
		try {
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<User_Video> user_VideoList = null;
			for (User_Video user_Video : user_VideoList) {
				user_Video.setNickname(RedisUtil.Gethget(0, user_Video.getToken(), "nickname"));
				user_Video.setHead(RedisUtil.Gethget(0, user_Video.getToken(), "head"));
				String sex = RedisUtil.Gethget(0, user_Video.getToken(), "sex");
				if(!StringUtils.isBlank(sex))
				{
					switch (sex) {
					case "0":
						user_Video.setSex("男");
						break;
					case "1":
						user_Video.setSex("女");
						break;
					default:
						user_Video.setSex("");
						break;
					}
				}
				List<String> picList = new ArrayList<String>();
				if(user_Video.getSeconds()>0)
				{
					if(user_Video.getSeconds()<5)
					{
						for(int i=0;i<user_Video.getSeconds();i++)
						{
							picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+i);
						}
					}
					else {
						if(user_Video.getSeconds()>1000)
							user_Video.setSeconds(user_Video.getSeconds()/1000);
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+0);
						int num = user_Video.getSeconds()/2;
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(num-1));
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+num);
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(num+1));
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(user_Video.getSeconds()-1));
					}
				}
				user_Video.setPicList(picList);
			}
			int totalRecord = 0;
			List<Admin> adminList = iadminDao.getVideoAdminList(0, "", 0, 100000);
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("passvideo/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("video_id", video_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("admin_id", admin_id);
			model.addAttribute("userVideoList", user_VideoList);
			model.addAttribute("adminList", adminList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "passvideo");
		} catch (Exception e) {
			log.error("通过视频列表", e);
		}
		return "video/passlist";
	}
	/**
	 * 
	 * <p>功能描述：视频不通过列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月27日 下午3:36:24</p>
	 *
	 * @param video_id 视频id
	 * @param nickname 昵称
	 * @param admin_id 审核者id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getNoPassVideoList(int video_id, String nickname, int admin_id, int page, Model model)
	{
		try {
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<User_Video> user_VideoList = null;
			for (User_Video user_Video : user_VideoList) {
				user_Video.setNickname(RedisUtil.Gethget(0, user_Video.getToken(), "nickname"));
				user_Video.setHead(RedisUtil.Gethget(0, user_Video.getToken(), "head"));
				String sex = RedisUtil.Gethget(0, user_Video.getToken(), "sex");
				if(!StringUtils.isBlank(sex))
				{
					switch (sex) {
					case "0":
						user_Video.setSex("男");
						break;
					case "1":
						user_Video.setSex("女");
						break;
					default:
						user_Video.setSex("");
						break;
					}
				}
				List<String> picList = new ArrayList<String>();
				if(user_Video.getSeconds()>0)
				{
					if(user_Video.getSeconds()<5)
					{
						for(int i=0;i<user_Video.getSeconds();i++)
						{
							picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+i);
						}
					}
					else {
						if(user_Video.getSeconds()>1000)
							user_Video.setSeconds(user_Video.getSeconds()/1000);
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+0);
						int num = user_Video.getSeconds()/2;
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(num-1));
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+num);
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(num+1));
						picList.add(user_Video.getVideo()+"?vframe/jpg/offset/"+(user_Video.getSeconds()-1));
					}
				}
				user_Video.setPicList(picList);
			}
			int totalRecord = 0;
			List<Admin> adminList = iadminDao.getVideoAdminList(0, "", 0, 100000);
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("nopassvideo/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("video_id", video_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("admin_id", admin_id);
			model.addAttribute("userVideoList", user_VideoList);
			model.addAttribute("adminList", adminList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "nopassvideo");
		} catch (Exception e) {
			log.error("通过视频列表", e);
		}
		return "video/nopasslist";
	}
	/**
	 * 
	 * <p>功能描述：审核用户视频</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月27日 下午4:42:30</p>
	 *
	 * @param id 视频id
	 * @param status 状态
	 * @param w_name 编辑人员
	 * @param cookie
	 * @param request
	 * @return
	 */
	public int auditUserVideo(int id, int status, String remark, String w_name, String cookie, HttpServletRequest request)
	{		
		int result=0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			String [] admStrings = cookie.split(",");
			Admin admin= iadminDao.getAdminByName(admStrings[0]);
			iadminDao.updateAdminVideoCount(admin.getId(), 1);
			result = 0;
			if(result>0)
			{
				imanage_RecordDao.insertManageRecord(w_name, "审核用户视频", "t_user_video", id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("审核用户视频",e);
		}
		return result;
	}
}

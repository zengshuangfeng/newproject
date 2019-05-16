package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.User_VideoService;

/**
 * <p>文件名称：User_VideoController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月6日 上午11:30:48
 */
@Controller
public class User_VideoController {
	@Autowired
	private User_VideoService user_VideoService;

	/**
	 * 
	 * <p>功能描述：视频审核列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 上午11:28:36</p>
	 *
	 * @param video_id 视频id
	 * @param nickname 昵称
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auditvideo/list", method = RequestMethod.GET)
	public String getAuditList(@RequestParam(value = "video_id", required = false,defaultValue="0") int video_id,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@CookieValue(value="admin",required=true,defaultValue="")String cookie,
			Model model) {
		return user_VideoService.getAuditVideoList(video_id, nickname, cookie,model);
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
	@RequestMapping(value = "/publishvideo/list", method = RequestMethod.GET)
	public String getPublishList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "is_recommend", required = false,defaultValue="-1") int is_recommend,
			@RequestParam(value = "isvest", required = false,defaultValue="-1") int isvest,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return user_VideoService.getPublishList(uid, nickname, start_time, end_time, is_recommend, isvest, page, model);
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
	@RequestMapping(value = "/replyvideo/list", method = RequestMethod.GET)
	public String getReplyList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "is_blocked", required = false,defaultValue="0")int is_blocked,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return user_VideoService.getReplyList(uid, nickname, start_time, end_time, is_blocked, page, model);
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
	@ResponseBody
	@RequestMapping(value = {"/auditvideo/updatestatus","/publishvideo/updatestatus"}, method = RequestMethod.POST)
	public int updateUserVideoStatus(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "status", required = false,defaultValue="0")int status,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_VideoService.updateUserVideoStatus(id, status, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "/publishvideo/updateisrecommend", method = RequestMethod.POST)
	public int updateUserVideoIsRecommend(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "is_recommend", required = false,defaultValue="0")int is_recommend,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_VideoService.updateUserVideoIsRecommend(id, is_recommend, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "/replyvideo/updateuserisblocked", method = RequestMethod.POST)
	public int updateUserIsBlocked(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "is_blocked", required = false,defaultValue="0")int is_blocked,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_VideoService.updateUserIsBlocked(id, is_blocked, w_name, request);
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
	@RequestMapping(value = "/passvideo/list", method = RequestMethod.GET)
	public String getPassList(@RequestParam(value = "video_id", required = false,defaultValue="0") int video_id,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "admin_id", required = false,defaultValue="0") int admin_id,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return user_VideoService.getPassVideoList(video_id, nickname, admin_id, page, model);
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
	@RequestMapping(value = "/nopassvideo/list", method = RequestMethod.GET)
	public String getNoPassList(@RequestParam(value = "video_id", required = false,defaultValue="0") int video_id,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "admin_id", required = false,defaultValue="0") int admin_id,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return user_VideoService.getNoPassVideoList(video_id, nickname, admin_id, page, model);
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
	@ResponseBody
	@RequestMapping(value = {"/auditvideo/audit","/passvideo/audit", "/nopassvideo/audit"}, method = RequestMethod.POST)
	public int auditUserVideo(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "status", required = false,defaultValue="0")int status,
			@RequestParam(value = "remark", required = false,defaultValue="")String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			@CookieValue(value="admin",required=true,defaultValue="")String cookie,
			HttpServletRequest request) {

		return user_VideoService.auditUserVideo(id, status, remark, w_name, cookie, request);
	}
}

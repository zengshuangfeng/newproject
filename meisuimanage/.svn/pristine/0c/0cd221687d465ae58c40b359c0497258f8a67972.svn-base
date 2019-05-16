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

import com.meisui.manage.service.ReportService;

/**
 * <p>文件名称：ReportController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 下午2:55:39
 */
@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;
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
	@RequestMapping(value = "anchorreport/list", method = RequestMethod.GET)
	public String getAnchorReportList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "o_uid", required = false,defaultValue="0") long o_uid, 
			@RequestParam(value = "o_nickname", required = false,defaultValue="") String o_nickname, 
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return reportService.getAnchorReportList(uid, f_uuid, nickname, o_uid, o_nickname, page, model);
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
	@RequestMapping(value = "userreport/list", method = RequestMethod.GET)
	public String getUserReportList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return reportService.getUserReportList(uid, nickname, start_time, end_time, page, model);
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
	@ResponseBody
	@RequestMapping(value = "userreport/updateislook", method = RequestMethod.POST)
	public int updateUserIsLook(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "islook", required = false,defaultValue="0")int islook,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return reportService.updateReportIsLook(id, islook, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "userreport/forbiduser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveUserForbid(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "hour", required = false,defaultValue="0")int hour,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return  reportService.saveUserForbid(id, uid, hour, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "userreport/updateheadlock", method = RequestMethod.POST)
	public int updateUserHeadLock(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "head_lock", required = false,defaultValue="0")int head_lock,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return reportService.updateUserHeadLock(id, head_lock, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "userreport/updatenicknamelock", method = RequestMethod.POST)
	public int updateUserNicknameLock(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "nickname_lock", required = false,defaultValue="0")int nickname_lock,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return reportService.updateUserNicknameLock(id, nickname_lock, w_name, request);
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
	@RequestMapping(value = "userreport/updateisblocked", method = RequestMethod.POST)
	public int updateIsBlocked(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "is_blocked", required = false,defaultValue="0")int is_blocked,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return reportService.updateUserIsBlocked(id, is_blocked, w_name, request);
	}
}

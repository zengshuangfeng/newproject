package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.User_ForbidService;

/**
 * <p>文件名称：User_ForbidController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月5日 下午3:08:04
 */
@RequestMapping("/userforbid")
@Controller
public class User_ForbidController {
	@Autowired
	private User_ForbidService user_ForbidService;
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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUserForbidList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid, 
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname, 
			@RequestParam(value = "is_forbid", required = false,defaultValue="-1")int is_forbid,  
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_ForbidService.getUserForbidList(uid, f_uuid, nickname, is_forbid, page, model);
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
	@ResponseBody
	@RequestMapping(value = "/getisforbid", method = RequestMethod.GET)
	public int getUserIsForbid(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid) {	
		return user_ForbidService.getIsForbid(uid, f_uuid);
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
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveUserForbid(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "hour", required = false,defaultValue="0")int hour,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_ForbidService.saveUserForbid(uid, f_uuid, hour, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String cancelUserForbid(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_ForbidService.cancelUserForbid(uid, w_name, request);
	}
}

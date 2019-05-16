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

import com.meisui.manage.service.User_AdminService;

/**
 * <p>文件名称：Global_Admin_Controller.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月20日 上午10:56:35
 */
@Controller
@RequestMapping("/globaladmin")
public class Global_Admin_Controller {
	@Autowired
	private User_AdminService user_AdminService;
	/**
	 * 
	 * <p>功能描述：全局管理员列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月20日 上午10:53:39</p>
	 *
	 * @param page页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getGlobalAdminList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_AdminService.getGlobalUserAdminList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：删除全局管理员</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月20日 上午10:55:30</p>
	 *
	 * @param id 全局管理员id
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int deleteGlobalAdmin(@RequestParam(value = "id", required = true, defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_AdminService.deleteGlobalUserAdmin(id, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：保存全局管理员信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月20日 上午10:55:30</p>
	 *
	 * @param uid 全局管理员uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveGlobalAdmin(@RequestParam(value = "uid", required = true, defaultValue="0") Long uid,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_AdminService.saveGlobalUserAdmin(uid, w_name, request);
	}
}

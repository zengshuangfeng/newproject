package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.AdminService;

/**
 * 
 * <p>文件名称：LoginController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月12日 下午3:49:07
 */
@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;
	
	/***
	 * 
	 * <p>功能描述：登录页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @return
	 */
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(@CookieValue(value = "admin", required = false) String cookie) {
		if(!StringUtils.isBlank(cookie))
			return "redirect:/main"; 
		return "login";
	}
	/***
	 * 
	 * <p>功能描述：登录验证</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @param remember 是否记住 1:是 0:否
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public String validate(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "remember",defaultValue="0") int remember,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		
		return adminService.validate(username, password, remember, request, response, model);
	}
	/***
	 * 
	 * <p>功能描述：重置密码页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/resetpwd", method = RequestMethod.GET)
	public String resetpwd() {
		return "resetpwd";
	}
	/***
	 * 
	 * <p>功能描述：密码重置</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @param password 新密码
	 * @param password2 再一次密码
	 * @param cookie 用户cookie
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login/resetpwd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String resetPwd(@RequestParam(value = "password") String password,
			@RequestParam(value = "password2") String password2,
			@CookieValue(value = "admin", required = true) String cookie,
			HttpServletResponse response) {

		return adminService.resetPwd(password, password2, cookie, response);
	}
	/***
	 * 
	 * <p>功能描述：用户登出</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, HttpServletResponse response, Model model) {

		return adminService.adminLogout(request, response, model);
	}
	/**
	 * <p>功能描述：</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login/alert", method = RequestMethod.GET)
	public String changepassword(@RequestParam(value = "id") int  id,
			Model model) {
		return adminService.alert(id,model);
	}
	/**
	 * <p>功能描述：保存密码</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @param username 用户名		
	 * @param opw 旧密码
	 * @param npw 新密码	
	 * @param npw2 确认新密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login/savepwd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String savePwd(
			@RequestParam(value="id", required = false,defaultValue="0") String  id,
			@RequestParam(value="opwd", required = false,defaultValue="") String opwd,
			@RequestParam(value="npwd", required = false,defaultValue="") String npwd,
			@RequestParam(value="npwd2", required = false,defaultValue="") String npwd2,
			HttpServletResponse response
			) {
		return adminService.save(id, opwd, npwd, npwd2, response);
	}
	/**
	 * 
	 * <p>功能描述：主页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @param cookie 用户cookie
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(@CookieValue(value = "admin", required = true) String cookie, HttpServletRequest request, HttpServletResponse response, Model model) {

		return adminService.main(cookie, request, response, model);
	}
	/***
	 * 
	 * <p>功能描述：登录默认页</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public String blank() {
		return "common/blank";
	}
	/***
	 * 
	 * <p>功能描述：404页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/404.html", method = RequestMethod.GET)
	public String error() {
		return "404";
	}
	/***
	 * 
	 * <p>功能描述：500页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:49:07</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/500.html", method = RequestMethod.GET)
	public String forbid() {
		return "500";
	}
}

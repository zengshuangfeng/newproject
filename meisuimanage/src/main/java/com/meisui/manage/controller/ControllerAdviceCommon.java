package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.meisui.manage.service.AdminService;

/**
 * <p>文件名称：ControllerAdvice.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月12日 下午5:48:37
 */
@ControllerAdvice
public class ControllerAdviceCommon {
	@Autowired
	private AdminService adminService;
	@ModelAttribute
	public void populateModel(@CookieValue(value = "admin", required = false) String cookie, @CookieValue(value = "w_name", required = false) String w_name, HttpServletRequest request, HttpServletResponse response, Model model) {    
		adminService.common(cookie, w_name, request, response, model);
	}    
}

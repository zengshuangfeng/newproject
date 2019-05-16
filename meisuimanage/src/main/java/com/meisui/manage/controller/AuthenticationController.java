package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.entity.Authentication;
import com.meisui.manage.service.AccountReceivableService;
import com.meisui.manage.service.AuditRecordService;
import com.meisui.manage.service.AuthenticationService;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationservice;
	
	@Autowired
	private AuditRecordService auditRecordService;
	
	@Autowired
	private AccountReceivableService accountReceivableService;
	
	/**
	 * 获取身份认证审核记录列表
	 * */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAuthenticationList(@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model) {
		return authenticationservice.getAuthenticationList(page,model);
	}
	/**
	 * 实名验证初审审核通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkPass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String checkPass(int id ,int is_status,@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request ) {
		return authenticationservice.checkPass(id,is_status,w_name,request);
	}
	/**
	 * 实名验证初审审核未通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkunPass", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String checkunPass(Integer auth_id ,Integer is_status,String remark,@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request ) {
		return authenticationservice.checkunPass(auth_id,is_status,remark,w_name,request);
	}
	/**
	 * 获取身份认证审核记录信息
	 * */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(int id,Model model) {
		return authenticationservice.getAuthentication(id,model);
	}
	
	/**
	 * 获取审核记录历史列表
	 * */
	@RequestMapping(value = "/recordlist", method = RequestMethod.GET)
	public String getAuditRecordList(@RequestParam(value = "authentication_id", required = false,defaultValue="0") int authentication_id,@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model) {
		return auditRecordService.getAuditRecordList(authentication_id,page,model);
	}
	/**
	 * 获取账户审核信息
	 * */
	@RequestMapping(value = "/getAccountReceivable", method = RequestMethod.GET)
	public String getAccountReceivable(@RequestParam(value = "authentication_id", required = false,defaultValue="0") int authentication_id, Model model) {
		return accountReceivableService.getAccountReceivable(authentication_id,model);
	}
	/**
	 * 实名验证复审审核通过----------未通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkaccountPass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void checkaccountPass(int authentication_id ,int is_status,String payer_name,String pay_account,String pay_number,
			String pay_type ,String remark, @CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {
		authenticationservice.checkaccountPass(authentication_id,is_status,payer_name,pay_account,pay_number,pay_type,remark,w_name,request);
	}
	/**
	 * 验证账户金额
	 * */
	@ResponseBody
	@RequestMapping(value = "/chekpaynumber", method = RequestMethod.POST)
	public String chekpaynumber( int authentication_id,String pay_number) {
		return accountReceivableService.chekpaynumber(authentication_id,pay_number);
	}
}

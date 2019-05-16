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

import com.meisui.manage.service.RechargeBalanceVirtualService;

@RequestMapping(value = "/rechargebalancevirtual")
@Controller
public class RechargeBalanceVirtualController {
	@Autowired
	private RechargeBalanceVirtualService rechargebalancevirtualservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String rechargebalancevirtual(
			@RequestParam(value = "uid", required = true, defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = true, defaultValue="")String f_uuid,
			@RequestParam(value = "nickname", required = true, defaultValue="")String nickname,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			HttpServletRequest request,Model model) {
		
		return rechargebalancevirtualservice.rechargebalancevirtual(uid,f_uuid,nickname,request,page,model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/savediamonds", method = RequestMethod.POST)
	public int savediamonds(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "diamondsremark", required = false,defaultValue="")String diamondsremark,
			@RequestParam(value = "diamondsa", required = false,defaultValue="")String diamondsa,
			@RequestParam(value = "diamondsb", required = false,defaultValue="")String diamondsb,			
			@RequestParam(value = "diamondscount", required = false,defaultValue="0")int balance_virtual,
			@RequestParam(value = "money", required = false,defaultValue="0")int money,
			@RequestParam(value = "remark", required = false,defaultValue="")String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return rechargebalancevirtualservice.updatebalance_virtual(id, balance_virtual, money, diamondsremark, diamondsa,diamondsb, w_name,request);
	}
}

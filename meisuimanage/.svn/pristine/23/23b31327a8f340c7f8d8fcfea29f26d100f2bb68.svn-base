package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Anchor_Guard_RechargeService;
import com.meisui.manage.service.StatisticalService;

@RequestMapping("/guardrecharge")
@Controller
public class Anchor_Guard_RechargeController {
	@Autowired
	private Anchor_Guard_RechargeService anchor_Guard_RechargeService;
	@Autowired
	private StatisticalService statisticalService;
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "anchor_f_uuid", required = false,defaultValue="0") long anchor_f_uuid, 
			@RequestParam(value = "pay_type", required = false,defaultValue="-1") int pay_type, 
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "o_id", required = false,defaultValue="") String o_id, 
			@RequestParam(value = "platform_channel", required = false,defaultValue="") String platform_channel,
			@RequestParam(value = "is_pay", required = false,defaultValue="1") int is_pay,  
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			@CookieValue(value="admin",required=false,defaultValue="")String cookie,
			Model model) {	
		return anchor_Guard_RechargeService.getList(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform_channel, is_pay, start_time, end_time, operate_center_id, agent_id, cookie, page, model);
	}
	@RequestMapping(value = "exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "anchor_f_uuid", required = false,defaultValue="0") long anchor_f_uuid, 
			@RequestParam(value = "pay_type", required = false,defaultValue="-1") int pay_type, 
			@RequestParam(value = "type", required = false,defaultValue="-1") int type, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "o_id", required = false,defaultValue="") String o_id, 
			@RequestParam(value = "platform_channel", required = false,defaultValue="") String platform_channel,
			@RequestParam(value = "is_pay", required = false,defaultValue="1") int is_pay,  
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id,  
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			HttpServletResponse response) {	
		return anchor_Guard_RechargeService.exportExcel(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform_channel, is_pay, operate_center_id,agent_id, start_time, end_time, type, response);
	}
	@ResponseBody
	@RequestMapping(value = "agentlist", produces = "text/json;charset=UTF-8")
	public String agentList(int centerId) {
		return statisticalService.agentList(centerId);
	}
}

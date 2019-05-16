package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.StatisticalService;


@Controller
@RequestMapping("")
public class StatisticalController{

	@Autowired
	private StatisticalService statisticalService;

	@RequestMapping(value = "/statistical/profitalllist", method = RequestMethod.GET)
	public String getProfitList(@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "type", required = false,defaultValue="-1")int type,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {

		return statisticalService.getProfitList(operate_center_id, agent_id, s_time, e_time, f_uuid, nickname, type, page, model, request);
	}
	@RequestMapping(value = "/statistical/exportprofitall", method = RequestMethod.GET)
	public String exportProfit(@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "type", required = false,defaultValue="-1")int type,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			HttpServletRequest request, HttpServletResponse response) {	
		return statisticalService.exportProfit(operate_center_id, agent_id, s_time, e_time, f_uuid, nickname, type, response, request);
	}
	@RequestMapping(value = "/statistical2/giftprofit")
	public String giftProfitList(@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", defaultValue = "1")int page,
			HttpServletRequest request, Model model) {
		return statisticalService.giftProfitList(operate_center_id, agent_id, s_time, e_time, page, request, model);
	}
	@ResponseBody
	@RequestMapping(value = {"/statistical/agentlist","/statistical2/agentlist"}, produces = "text/json;charset=UTF-8")
	public String agentList(int centerId) {
		return statisticalService.agentList(centerId);
	}
}
package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.ExchangeVirtualService;
import com.meisui.manage.service.StatisticalService;

@Controller
@RequestMapping(value = "exchangevirtual")
public class ExchangeVirtualController {
	@Autowired
	private ExchangeVirtualService exchangevirtualService;
	@Autowired
	private StatisticalService statisticalService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getExchangeVirtualList(
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 			
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			HttpServletRequest request,
			Model model) {	
		return exchangevirtualService.getExchangeVirtualList(operate_center_id,agent_id,f_uuid, nickname, start_time, end_time, page, model, request);
	}
	
	 @ResponseBody
		@RequestMapping(value = "/agentlist", produces = "text/json;charset=UTF-8")
		public String agentList(int centerId) {
			return statisticalService.agentList(centerId);
		}

}

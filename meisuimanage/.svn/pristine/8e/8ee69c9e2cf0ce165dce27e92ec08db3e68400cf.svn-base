package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

import com.meisui.manage.service.Activity_AnnualService;

@RequestMapping("/activityannual")
@Controller
public class Activity_AnnualController {
	@Autowired
	private Activity_AnnualService activityannualservice;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getActivityAnnualList(
			@RequestParam(value = "type", required = false,defaultValue="0") int type,			
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return activityannualservice.getActivityAnnualList(type,s_time, e_time, page, model);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String getActivityAnnualDetail(
			@RequestParam(value = "wheel", required = false,defaultValue="0") int wheel,		
			@RequestParam(value = "activity_id", required = false,defaultValue="0") int activity_id,
			@RequestParam(value = "count", required = false,defaultValue="0") int count,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return activityannualservice.getActivityAnnualDetail(wheel,activity_id,count,page, model);
	}
	@ResponseBody
	@RequestMapping(value = "/saveremark", method = RequestMethod.POST)
	public int saveUserRemark(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "remark", required = false,defaultValue="")String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return activityannualservice.saveUserRemak(id, remark, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/changetake",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String ChangeTake(@RequestParam(value = "id", required = false, defaultValue = "0")int id,
			@RequestParam(value = "is_take", required = false, defaultValue = "0")int is_take,
		HttpServletRequest request) {
		return activityannualservice.ChangeTake(id,is_take, request);
	}
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletResponse response) {	
		return activityannualservice.exportExcel(response);
	}
	@RequestMapping(value = "/exportexcel2", method = RequestMethod.GET)
	public String exportExcel2(
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			HttpServletResponse response) {	
		return activityannualservice.exportExcel2(s_time,e_time,response);
	}
	
}

package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Activity_StarService;

@RequestMapping("/activitystar")
@Controller
public class Activity_Star_AnchorController {
	@Autowired
	private Activity_StarService activitystarservice;
	
	/**
	 * 最强新星参赛主播列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getActivityList(
			@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return activitystarservice.getActivityList(uid, nickname, page, model);
	}
	
	/**
	 * 编辑活动积分
	 */
	@ResponseBody
	@RequestMapping(value = "/editscore",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String editScore(@RequestParam(value = "id", required = false, defaultValue = "0")int id,	
		@RequestParam(value = "uid", required = false, defaultValue = "0")int uid,	
		@RequestParam(value = "addscore", required = false, defaultValue="0")int addscore,
		@RequestParam(value = "count", required = false, defaultValue="0")int count,
		HttpServletRequest request) {
		return activitystarservice.editScore(id, uid,addscore,count, request);
	}
	
	/**
	 * PK记录
	 */
	@RequestMapping(value = "/starrecord", method = RequestMethod.GET)
	public String getPKRecord(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			 Model model) {
		return activitystarservice.getPKRecord(id,uid,page, model);
	}
	
	/**
	 * PK记录下取消本场参赛资格
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelone", method = RequestMethod.POST)
	public int updateCancelOne(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return activitystarservice.updateCancelOne(id, w_name, request);
	}
	
	/**
	 * 参赛主播列表取消参赛资格
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public int updateCancel(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "count", required = false, defaultValue="0")int count,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return activitystarservice.updateCancel(id, count,w_name, request);
	}
	
	/**
	 * 导出功能
	 */
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "uid", required = false,defaultValue="0")Integer uid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			HttpServletResponse response) {	
		return activitystarservice.exportExcel(uid,nickname,response);
	}
	
	/**
	 * 编辑PK时间
	 */
	@ResponseBody
	@RequestMapping(value = "/edittime",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String editTime(@RequestParam(value = "id", required = false, defaultValue = "0")int id,	
		@RequestParam(value = "status", required = false, defaultValue = "0")int status,	
		@RequestParam(value = "start_time", required = false, defaultValue="")String start_time,
		@RequestParam(value = "end_time", required = false, defaultValue="")String end_time,
		HttpServletRequest request) {
		return activitystarservice.editTime(id,status,start_time,end_time, request);
	}

}

package com.meisui.manage.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.UserStatisticsService;

/**
 * 用户统计管理
 * @author huang
 *
 */

@Controller
@RequestMapping(value = "userStatistics")
public class UserStatisticsController {

	@Autowired
	private UserStatisticsService userStatisticsService;
	
	@RequestMapping(value = "/list")
	public String list(
		@RequestParam(value="uid", defaultValue="0")long uid,
		@RequestParam(value="fuuid", defaultValue="0")long fuuid,
		@RequestParam(value="nickName", defaultValue="")String nickName,
		@RequestParam(value = "level", required = false,defaultValue="") String level,
		@RequestParam(value="startTime" ,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd")String startTime,
		@RequestParam(value="endTime" ,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd")String endTime,
	    @RequestParam(value="page", defaultValue="1")int page,Model model)
	{
		return userStatisticsService.list(uid, fuuid, nickName, level, startTime, endTime, page, model);
	}
	
	@RequestMapping(value = "/exportExcel")
	public String exportExcel(
			@RequestParam(value="uid", defaultValue="0")long uid,
			@RequestParam(value="fuuid", defaultValue="0")long fuuid,
			@RequestParam(value="nickName", defaultValue="")String nickName,
			@RequestParam(value = "level", required = false,defaultValue="") String level,
			@RequestParam(value="startTime" ,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd")String startTime,
			@RequestParam(value="endTime" ,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd")String endTime,HttpServletResponse response){
		return userStatisticsService.exportExcel(uid, fuuid, nickName, level, startTime, endTime, response);
		
	}
}

package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.NewActivityService;

@RequestMapping("/newactivity")
@Controller
public class New_ActivityController {
	@Autowired
	private NewActivityService newactivityService;

	/**
	 * @param is_online
	 * @param page
	 * @param model
	 * @return
	 * 新活动首页列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return newactivityService.getlist(is_online, page, model);
	}
	
	/**
	 * @param model
	 * @return
	 * 活动添加页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		return newactivityService.add(model);
	}
	
	/**
	 * @param model
	 * @return
	 * 活动添加页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String add(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,Model model) {	
		return newactivityService.edit(id,model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "url", required = false, defaultValue="") String url,
			@RequestParam(value = "order", required = false, defaultValue="0") int order,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "is_share", required = false, defaultValue="0") int is_share,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "state_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date state_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "platform", required = false,defaultValue="00") String platform,
			@RequestParam(value = "time_type", required = false, defaultValue="0") int time_type,
			@RequestParam(value = "position", required = false, defaultValue="0") int position,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return newactivityService.save(id, name, url,order,type,is_share,pic, state_time, end_time, is_online, platform, time_type,position,w_name, request);
	}

/**
 * @param id
 * @param is_online
 * @param w_name
 * @param request
 * @return
 * 更改上线状态
 */
@ResponseBody
@RequestMapping(value = "/updateonline", method = RequestMethod.POST)
public int updateOnline(@RequestParam(value = "id", required = false, defaultValue="0") int id,
		@RequestParam(value = "is_online", required = false, defaultValue="0")int is_online,
		@CookieValue(value="w_name", defaultValue="")String w_name,
		HttpServletRequest request) {	
	return newactivityService.updateOnline(w_name, id, is_online, request);
}
}



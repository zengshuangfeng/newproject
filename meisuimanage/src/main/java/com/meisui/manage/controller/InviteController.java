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

import com.meisui.manage.service.InviteService;

@RequestMapping("/invite")
@Controller
public class InviteController {
	@Autowired
	private InviteService inviteService;

	/**
	 * 邀请有礼列表
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return inviteService.list(page, model);
	}
	/**
	 * 添加邀请有礼页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("activeUrl", "invite");	
		return "invite/add";
	}
	/**
	 * 编辑邀请有礼页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return inviteService.edit(id, model);
	}
	/**
	 * 添加邀请有礼信息
	 * @param id
	 * @param content 文案
	 * @param virtual_count 邀请者获得钻石
	 * @param to_virtual_count 被邀请者获得钻石
	 * @param effect_time 生效时间
	 * @param is_online 是否上线 1是0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "content", required = false, defaultValue="") String content,
			@RequestParam(value = "virtual_count", required = false, defaultValue="0") int virtual_count,
			@RequestParam(value = "to_virtual_count", required = false, defaultValue="0") int to_virtual_count,
			@RequestParam(value = "effect_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date effect_time,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return inviteService.save(id, content, virtual_count, to_virtual_count, effect_time, is_online, w_name, request);
	}
	/***
	 * 更新邀请有礼上线状态
	 * @param w_name 编辑人员
	 * @param id 邀请有礼ID
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateonline", method = RequestMethod.POST)
	public int updateOnline(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			@RequestParam(value = "is_online", required = false,defaultValue="0") int is_online, 
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request,
			Model model) {	
		return inviteService.updateOnline(w_name, id, is_online, request);
	}
}

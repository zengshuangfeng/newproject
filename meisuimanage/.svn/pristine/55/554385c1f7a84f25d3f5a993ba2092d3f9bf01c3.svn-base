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

import com.meisui.manage.service.Anchor_Guard_ChangeService;

@RequestMapping("/guardchange")
@Controller
public class Anchor_Guard_ChangeController {
	@Autowired
	private Anchor_Guard_ChangeService anchorguardchangeservie;
	
	/**
	 * @param page
	 * @param model
	 * 钻石管理==》守护配置列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return anchorguardchangeservie.getGuardChangeList(page, model);
	}
	
	/**
	 *改变套餐状态  上线或下线
	 */
	@ResponseBody
	@RequestMapping(value = "/updateisonline", method = RequestMethod.POST)
	public int updateIsOnline(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "is_online", required = false,defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return anchorguardchangeservie.updateIsOnline(w_name, id, is_online, request);
	}
	
	
	/**
	 * 守护配置添加页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAnchorGuardChange(Model model) {
		return anchorguardchangeservie.addAnchorGuardChange(model);
	}
	
	/**
	 * 守护配置编辑页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editAnchorGuardChange(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			Model model) {
		return anchorguardchangeservie.editAnchorGuardChange(id, model);
	}
	/**
	 * 守护配置添加和编辑页面
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String savePopularAnchor(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "change_rmb", required = false, defaultValue = "0") int change_rmb,
			@RequestParam(value = "guard_head", required = false, defaultValue = "") String guard_head,
			@RequestParam(value = "entrance_pic", required = false, defaultValue = "") String entrance_pic,
			@RequestParam(value = "type", required = false, defaultValue = "0") int type,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return anchorguardchangeservie.saveAnchorGuard(id, name, change_rmb, guard_head, entrance_pic, type, w_name,request);
	}
}

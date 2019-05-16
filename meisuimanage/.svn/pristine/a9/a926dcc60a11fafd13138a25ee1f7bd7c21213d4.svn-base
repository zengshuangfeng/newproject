package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.WxShareService;

@Controller
@RequestMapping("/wxshare")
public class WxShareController {
	
	@Autowired
	private WxShareService wService;
	
	@RequestMapping("edit")
	public String edit(Model model){
		model.addAttribute("activeUrl", "wxshare");
		return wService.edit(model);
	}
	
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id",defaultValue = "0")int id,
					@RequestParam(value = "name", required = true)String name,
					@RequestParam(value = "title", required = true)String title,
					@RequestParam(value = "icon", required = true)String icon,
					@RequestParam(value = "url" ,required = true)String url,
					HttpServletRequest request){
		return wService.save(id, name, title, icon, url, request);
		
	}
}

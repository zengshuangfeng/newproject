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

import com.meisui.manage.service.H5Service;

@RequestMapping("/h5")
@Controller
public class H5Controller {
	@Autowired
	private H5Service h5Service;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return h5Service.getList(page, model);
	}	
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveRecommend(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "title", required = false, defaultValue="") String title,
			@RequestParam(value = "author", required = false, defaultValue="") String author,
			@RequestParam(value = "banner", required = false, defaultValue="") String banner,
			@RequestParam(value = "content", required = false, defaultValue="") String content,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return h5Service.save(id, title, author, banner, content, w_name, request);
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model)
	{
		model.addAttribute("activeUrl", "h5");
		return "h5/add";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, Model model)
	{
		return h5Service.edit(id, model);
	}
}

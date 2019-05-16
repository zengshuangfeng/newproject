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

import com.meisui.manage.service.MengYanService;

@RequestMapping("/mengyan")
@Controller
public class MengYanController {
	@Autowired
	private MengYanService mengyanservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getMengYanList(
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return mengyanservice.getMengYanList( s_time, e_time, page, model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Add(Model model) {	
		model.addAttribute("activeUrl", "mengyan");
		return "mengyan/add";
	}
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String Save(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "sort", required = false, defaultValue="-1") int sort,
			HttpServletRequest request,Model model) {	
		
		return mengyanservice.Save(id, pic, type, sort, request, model);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, Model model)
	{
		return mengyanservice.edit(id, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = true, defaultValue="-1") int id,
			HttpServletRequest request) {	
		return mengyanservice.delete(id, request);
	}

}

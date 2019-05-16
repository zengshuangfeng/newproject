package com.meisui.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.ChristmasService;

@RequestMapping("/christmas")
@Controller
public class ChristmasController {
	@Autowired
	private ChristmasService christmasservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getChristmasList(
			@RequestParam(value = "f_uuid", required = false,defaultValue="") String f_uuid,
			@RequestParam(value = "uid", required = false,defaultValue="0") int uid,
			@RequestParam(value = "type", required = false,defaultValue="-1") int type,			
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return christmasservice.getChristmasList(f_uuid,uid,type,start_time,end_time,page, model);
	}

}

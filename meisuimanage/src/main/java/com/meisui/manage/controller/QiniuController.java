package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.QiniuService;

@Controller
@RequestMapping("/qiniu")
public class QiniuController {
	@Autowired
	private QiniuService qiniuService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {	
		return qiniuService.getList(model);
	}
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public int disable(@RequestParam(value = "f_uuid", required = false,defaultValue="")String f_uuid, HttpServletRequest request) {	
		return qiniuService.disable(f_uuid, request);
	}
}

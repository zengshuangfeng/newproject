package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Operate_Service;
import com.meisui.manage.service.ProvinceCenterService;

@RequestMapping("/provincecenter")
@Controller
public class Province_CenterController {
	@Autowired
	private ProvinceCenterService provincecenterService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getProviceCenterList(
			@RequestParam(value = "province_center_name", defaultValue = "")String province_center_name,
			@RequestParam(value = "province_center_id", defaultValue = "0")int province_center_id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return provincecenterService.getProvinceCenterList(province_center_name,province_center_id,page, model);
	}
	
	@RequestMapping("/add")
	public String addProviceCenter(Model model) {
		return provincecenterService.addProvinceCenter(model);
	}
	@RequestMapping("/edit")
	public String editProviceCenter(@RequestParam(value = "province_center_id", required = false,defaultValue="0")int province_center_id, Model model) {
		return provincecenterService.editProvinceCenter(province_center_id, model);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/save",produces = "text/json;charset=UTF-8")
	public String save(
		@RequestParam(value = "id", required = false, defaultValue = "0")int id,
		@RequestParam(value = "name", required = true)String name,
		@RequestParam(value = "username", required = true)String username,
		@RequestParam(value = "password", required = true)String password,
		@RequestParam(value = "remark", defaultValue = "", required = false)String remark,
		HttpServletRequest request
			) {
		return provincecenterService.save(id ,name,username, password, remark, request);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getusernameexist", method = RequestMethod.GET)
	public int getUsernameExist(@RequestParam(value = "id", required = false, defaultValue="0")int id,
			@RequestParam(value = "username", required = true, defaultValue="")String username,
			HttpServletRequest request) {	
		return provincecenterService.getUsernameExist(username, id);
	}
	
	@ResponseBody
	@RequestMapping(value = "updateprovinceisonline")
	private void updateProviceIsonline(@RequestParam(value = "id")int id, @RequestParam(value = "is_forbid")int is_forbid, HttpServletRequest request) {
		provincecenterService.updateProvinceIsonline(id,is_forbid, request);
	}

}

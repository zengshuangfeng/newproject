package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.VehicleService;

@RequestMapping("/vehicle")
@Controller
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getVehicleList(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "level", required = false, defaultValue="-1") int level,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return vehicleService.getVehicleList(id, name,is_online,level, page, model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String VehicleAdd(Model model) {	
		model.addAttribute("activeUrl", "vehicle");
		return "vehicle/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String Save(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "info", required = false, defaultValue="") String info,
			@RequestParam(value = "level", required = false, defaultValue="-1") int level,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "static_pic", required = false, defaultValue="") String static_pic,
			@RequestParam(value = "special_pic", required = false, defaultValue="") String special_pic,
			@RequestParam(value = "price", required = false, defaultValue="0") int price,
			@RequestParam(value = "time_limit", required = false, defaultValue="0") int time_limit,
			@RequestParam(value = "rare_level", required = false, defaultValue="0") int rare_level,
			@RequestParam(value = "divide", required = false, defaultValue="0") int divide,
			@RequestParam(value = "type", required = false, defaultValue="-1") int type,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@RequestParam(value = "platform", required = false, defaultValue="") String platform,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "alias_name", required = false, defaultValue="") String alias_name,
			HttpServletRequest request,Model model) {	
		
		return vehicleService.Save(id, name,info, level, pic,static_pic,special_pic,price,time_limit,rare_level,divide,sort,platform, is_online,type,alias_name,request, model);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, Model model)
	{
		return vehicleService.edit(id, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = true, defaultValue="-1") int id,
			HttpServletRequest request) {	
		return vehicleService.delete(id, request);
	}
}

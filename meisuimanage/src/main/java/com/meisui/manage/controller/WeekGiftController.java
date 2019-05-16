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

import com.meisui.manage.service.WeekGiftService;

@RequestMapping("/weekgift")
@Controller
public class WeekGiftController {
	@Autowired
	private WeekGiftService weekgiftservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getWeekGiftList(
			@RequestParam(value = "gift_id", required = false,defaultValue="0") int gift_id,
			@RequestParam(value = "gift_name", required = false,defaultValue="") String gift_name,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return weekgiftservice.getWeekGiftList( gift_id, gift_name, page, model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Add(Model model) {	
		return weekgiftservice.addGiftBox(model);	
	}
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String Save(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,			
			@RequestParam(value = "gift_head", required = false, defaultValue="") String gift_head,
			@RequestParam(value = "sort", required = false, defaultValue="-1") int sort,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "online_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date online_time,
			HttpServletRequest request,Model model) {	
		
		return weekgiftservice.Save(id, gift_id, gift_head,sort,is_online,online_time, request, model);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, Model model)
	{
		return weekgiftservice.edit(id, model);
	}
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return weekgiftservice.deleteWeekGift(w_name, id, request);
	}
	@ResponseBody
	@RequestMapping(value = "/getgifthead", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getGifthead(@RequestParam(value = "gift_id", required = false, defaultValue = "0") int gift_id) {
		return weekgiftservice.getGifthead(gift_id);
	}
}

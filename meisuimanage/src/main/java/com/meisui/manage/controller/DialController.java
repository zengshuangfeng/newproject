package com.meisui.manage.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.DialService;

@RequestMapping("/dial")
@Controller
public class DialController {
	@Autowired
	private DialService dialservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getMengYanList(
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return dialservice.getDialList(page, model);
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Add(Model model) {	
		
		
		return dialservice.getAddList(model);
	}
	
	@ResponseBody
	@RequestMapping(value="/giftnameshow",method=RequestMethod.GET)
	public Map<String,Object> getMessageShow(
			@RequestParam(value = "gift_id",required = false)Integer gift_id,
			Model model) {
		
		return dialservice.getGiftnameShow(gift_id,model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String Save(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "giftpic", required = false, defaultValue="") String giftpic,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "prize_name", required = false, defaultValue="") String prize_name,
			@RequestParam(value = "prize_count", required = false, defaultValue="0") int prize_count,
			@RequestParam(value = "probability", required = false, defaultValue="0") double probability,
			@RequestParam(value = "inventory", required = false, defaultValue="0") int inventory,
			@RequestParam(value = "start_time", required = false, defaultValue="")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time", required = false, defaultValue="")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			HttpServletRequest request,Model model) {	
		
		return dialservice.Save(id,gift_id, pic,giftpic, type, prize_name,prize_count,probability,inventory, start_time,end_time,request, model);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, Model model)
	{
		return dialservice.edit(id, model);
	}
	
	@RequestMapping(value = "/dialuser", method = RequestMethod.GET)
	public String getDialUser(
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") Long f_uuid,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model)
	{
		return dialservice.getDialUser(f_uuid,page, model);
	}
}

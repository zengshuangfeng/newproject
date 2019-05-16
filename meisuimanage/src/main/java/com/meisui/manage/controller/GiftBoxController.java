package com.meisui.manage.controller;

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

import com.meisui.manage.service.Gift_BoxService;


@RequestMapping("/giftbox")
@Controller
public class GiftBoxController {
	@Autowired
	private Gift_BoxService giftboxservice;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(
			@RequestParam(value = "uid", required = false, defaultValue="0") int uid,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return giftboxservice.getGiftBoxList(uid,gift_id, page, model);
	}
	
	@RequestMapping(value = "/operatelog", method = RequestMethod.GET)
	public String getOperatelogList(
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model) {	
		return giftboxservice.getOperateLog(begin_time,end_time, page, model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		return giftboxservice.addGiftBox(model);
	}
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "uid", required = false, defaultValue="0") int uid,
			@RequestParam(value = "gift_id", required = false, defaultValue="") int gift_id,
			@RequestParam(value = "gift_count", required = false, defaultValue="0") int gift_count,	
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return giftboxservice.saveGiftBox(uid,gift_id,gift_count,w_name, request);
	}
	
}

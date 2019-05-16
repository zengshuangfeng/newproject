package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Anchor_RecommendService;

@Controller
@RequestMapping("/anchorrecommend")
public class Anchor_RecommendController {
	@Autowired
	private Anchor_RecommendService anchor_recommendService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getAnchorRecommendList(					
			@RequestParam(value = "f_uuid", required = false,defaultValue="")String f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,						
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {

		return anchor_recommendService.getAnchorRecommendList( f_uuid, nickname, page, model, request);
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Add(Model model) {	
		model.addAttribute("activeUrl", "anchorrecommend");
		return "anchorrecommend/add";
	}
	@ResponseBody
	@RequestMapping(value = "/getfuuidexist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getHotLiveExist(@RequestParam(value = "f_uuid", required = false, defaultValue = "0") long f_uuid) {
		return anchor_recommendService.getFuuidExist(f_uuid);
	}
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String Save(
			@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "f_uuid", required = false, defaultValue="") String f_uuid,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "sort", required = false, defaultValue="-1") int sort,
			HttpServletRequest request,Model model) {	
		
		return anchor_recommendService.Save(id,f_uuid, pic, sort, request, model);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, Model model)
	{
		return anchor_recommendService.edit(id, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = true, defaultValue="-1") int id,
			HttpServletRequest request) {	
		return anchor_recommendService.delete(id, request);
	}
	
	
}

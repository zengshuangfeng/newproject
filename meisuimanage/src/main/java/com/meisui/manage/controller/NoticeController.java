package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	
	@RequestMapping(value = "/list")
	public String list(
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "type", defaultValue = "-1")int type,
			@RequestParam(value = "state", defaultValue = "0")int state,
			@RequestParam(value = "platform", defaultValue = "0")String platform,
			@RequestParam(value = "page", defaultValue = "1")int page,
			Model model
			) {
		return noticeService.list(begin_time, end_time, type, state, platform, page, model);
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("activeUrl", "notice");
		return "notice/edit2";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(@RequestParam(value = "id", required = true)int id, Model model) {
		return noticeService.edit(id, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", produces = "text/json;charset=UTF-8")
	public String save(
			@RequestParam(value = "id", defaultValue = "0")int id,
			@RequestParam(value = "content", required = true)String content,
			@RequestParam(value = "sort",defaultValue = "0")int sort,
			@RequestParam(value = "type", defaultValue = "0")int type,
			@RequestParam(value = "show_time", defaultValue  = "")String show_time,
			@RequestParam(value = "start_time", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date start_time,
			@RequestParam(value = "end_time", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date end_time,
			@RequestParam(value = "interval_minute", defaultValue = "0")int interval_minute,
			@RequestParam(value = "platform", defaultValue = "0")String platform,
			@RequestParam(value = "is_online", defaultValue = "1")int is_online,
			@RequestParam(value = "version", defaultValue = "")String version,
			HttpServletRequest request) {
			return noticeService.save(id, content, sort, type, show_time, start_time, end_time, interval_minute, platform, is_online, version, request);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public int delete(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "id", required = true)int id, HttpServletRequest request) {
		return noticeService.delete(w_name, id, request);
	}
}

package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.Anchor_VirtualService;

@RequestMapping("/anchorvirtual")
@Controller
public class Anchor_VirtualController {
	@Autowired
	private Anchor_VirtualService anchor_VirtualService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "union_id", required = false,defaultValue="-1") int union_id,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return anchor_VirtualService.getList(uid, f_uuid, nickname, union_id, start_time, end_time, page, model);
	}
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "uid", required = false,defaultValue="0") long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "union_id", required = false,defaultValue="0") int union_id,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			HttpServletResponse response) {	
		return anchor_VirtualService.exportExcel(uid, f_uuid, nickname, union_id, start_time, end_time, response);
	}
}

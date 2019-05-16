package com.meisui.manage.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.PKGameService;

@Controller
@RequestMapping("/pkgame")
public class PKGameController {
	@Autowired
	private PKGameService pkgameservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPKGameList(
			@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return pkgameservice.getPKGameList(uid, nickname, page, model);
	}
	
	@RequestMapping(value = "/pkrecord", method = RequestMethod.GET)
	public String getPKGameRecord(
			@RequestParam(value = "anchor_f_uuid", required = false, defaultValue = "0") int anchor_f_uuid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return pkgameservice.getPKGameRecord(anchor_f_uuid, page, model);
	}
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(
			@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			HttpServletResponse response) {	
		return pkgameservice.exportExcel(uid,nickname,response);
	}
}

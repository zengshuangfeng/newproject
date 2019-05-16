package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.MessageService;

@Controller
@RequestMapping(value = "message")
public class MessageController {
	@Autowired
	private MessageService messageservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {

		return messageservice.getMessageList(page, model, request);
	}
	
	@RequestMapping(value = "/goauth", method = RequestMethod.GET)
	public String GoAuth(
			@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {

		return messageservice.getGoAuth(id,page, model, request);
	}
	
	@RequestMapping(value = "/updateread", method = RequestMethod.GET)
	public String updateRead(
			@RequestParam(value = "hid", required = false,defaultValue="0") String hid,
			@RequestParam(value = "read", required = false,defaultValue="-1") int read,
			Model model, HttpServletRequest request) {

		return messageservice.updateRead(hid,read,model, request);
	}
}

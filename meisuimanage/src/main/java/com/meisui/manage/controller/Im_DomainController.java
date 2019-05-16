package com.meisui.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meisui.manage.service.Im_DomainService;

@RequestMapping("/imdomain")
@Controller
public class Im_DomainController {
	@Autowired
	private Im_DomainService im_DomainService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		
			return im_DomainService.get(model);
	}	
}

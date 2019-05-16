package com.meisui.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.AuditRecordService;

@Controller
@RequestMapping("auditrecords")
public class AuditRecordsController {
	
	@Autowired
	private AuditRecordService auditRecordService;
	
	
	
	
	
	/**
	 * 获取审核记录历史列表
	 * */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getAuditRecordList( int id,@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model) {
		return auditRecordService.getAuditRecordList(id,page,model);
	}
}

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

import com.meisui.manage.service.Anchor_GuardService;

@RequestMapping("/anchorguard")
@Controller
public class Anchor_GuardController {
	@Autowired
	private Anchor_GuardService anchor_guardservice;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getGuardList(
			@RequestParam(value = "anchor_f_uuid", required = false,defaultValue="") String anchor_f_uuid,
			@RequestParam(value = "uid", required = false,defaultValue="0") int uid,
			@RequestParam(value = "type", required = false,defaultValue="-1") int type,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return anchor_guardservice.getGuardList(anchor_f_uuid,uid,type,page, model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model)
	{		
		return anchor_guardservice.getGuardAdd(model);							
	}
	@ResponseBody
	@RequestMapping(value = "/getfuuidexist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAnchorFuuidExist(@RequestParam(value = "anchor_f_uuid", required = false, defaultValue = "") String anchor_f_uuid) {
		return anchor_guardservice.getAnchorFuuidExist(anchor_f_uuid);
	}
	@ResponseBody
	@RequestMapping(value = "/getuidexist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUidExist(@RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid) {
		return anchor_guardservice.getUidExist(uid);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getchangeid", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getChangeId(@RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid,
			@RequestParam(value = "anchor_f_uuid", required = false, defaultValue = "") String anchor_f_uuid,
			@RequestParam(value = "change_id", required = false, defaultValue = "0") Integer change_id
			) {
		return anchor_guardservice.getChangeId(uid,anchor_f_uuid,change_id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveAnchorGuard(
			@RequestParam(value = "anchor_f_uuid", required = false,defaultValue="") String anchor_f_uuid,
			@RequestParam(value = "uid", required = false,defaultValue="0") int uid,
			@RequestParam(value = "change_id",required=false,defaultValue="0")int change_id,
			@RequestParam(value = "need_pay", required = false,defaultValue="0") int need_pay,			
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return anchor_guardservice.saveAnchorGuard(anchor_f_uuid, uid, change_id, need_pay, w_name,
				request);
	}
	
	@RequestMapping(value = "/guardrecord", method = RequestMethod.GET)
	public String getGuardRecord(
			@RequestParam(value = "send_uid", required = false,defaultValue="0") long send_uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "anchor_f_uuid", required = false,defaultValue="0") long anchor_f_uuid, 
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "type", required = false,defaultValue="-1") int type, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		
			return anchor_guardservice.getGuardRecord(send_uid,f_uuid,anchor_f_uuid,operate_center_id,nickname,agent_id,type,start_time,end_time,page, model);
	}
	
}

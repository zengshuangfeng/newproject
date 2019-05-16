package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.StatisticalService;
import com.meisui.manage.service.VirtualAccountService;


@RequestMapping("/virtual")
@Controller
public class VirtualAccountController {
	@Autowired
	private VirtualAccountService virtualaccountservice;
	@Autowired
	private StatisticalService statisticalService;
	
	@RequestMapping("/virtualrechargerecord")
	public String list(
			@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid,  
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model
			) {
		return virtualaccountservice.virtualrechargerecord(uid,f_uuid,operate_centerid,begin_time, end_time, page, model);
	}
	
	@RequestMapping("/virtualrecharge")
	@ResponseBody
	public int virtualrecharge(
			@RequestParam(value = "centerid", required = false,defaultValue="0") int centerid, 
			@RequestParam(value = "virtual_count", required = false,defaultValue="0") long virtual_count, 
			@RequestParam(value = "remark", required = false,defaultValue="") String remark, 
			 HttpServletRequest request,
			 Model model
			) {
		return virtualaccountservice.virtualrecharge(centerid,virtual_count,remark,request, model);
	}
	
	@RequestMapping("/rechargequotarecord")
	public String rechargequotarecord(
			@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid,  
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model
			) {
		return virtualaccountservice.rechargequotarecord(operate_centerid,begin_time, end_time, page, model);
	}
	
	@RequestMapping(value = "/giftrecord", method = RequestMethod.GET)
	public String getGiftRecord(@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "uid", required = false, defaultValue="0") long uid,
			@RequestParam(value = "send_uid", required = false, defaultValue="0") long send_uid,
			@RequestParam(value = "re_operate_center_id", required = false, defaultValue="0") int re_operate_center_id,
			@RequestParam(value = "send_operate_center_id", required = false, defaultValue="0") int send_operate_center_id,
			@RequestParam(value = "send_agent_id", required = false, defaultValue="0") int send_agent_id,
			@RequestParam(value = "re_agent_id", required = false, defaultValue="0") int re_agent_id,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return virtualaccountservice.getGiftRecord(s_time, e_time, gift_id, gift_name, uid, send_uid, re_operate_center_id,send_operate_center_id,send_agent_id,re_agent_id,page, model);
	}
	
    @RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
 	public String VirtualExportExcel(@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
 			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
 			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
 			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
 			@RequestParam(value = "uid", required = false, defaultValue="0") long uid,
 			@RequestParam(value = "send_uid", required = false, defaultValue="0") long send_uid,
 			@RequestParam(value = "re_operate_center_id", required = false, defaultValue="0") int re_operate_center_id,
			@RequestParam(value = "send_operate_center_id", required = false, defaultValue="0") int send_operate_center_id,
			@RequestParam(value = "send_agent_id", required = false, defaultValue="0") int send_agent_id,
			@RequestParam(value = "re_agent_id", required = false, defaultValue="0") int re_agent_id,
 			HttpServletResponse response) {	
 		return virtualaccountservice.GiftRecordExportExcel(s_time, e_time, gift_id, gift_name, uid, send_uid, re_operate_center_id,send_operate_center_id,send_agent_id,re_agent_id, response);
 	}
    
	@RequestMapping("/virtuallist")
	public String virtuallist(
			@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,  
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id,  
			@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model
			) {
		return virtualaccountservice.virtuallist(uid,f_uuid,nickname,operate_center_id,agent_id, page, model);
	}
	
	
	  @RequestMapping(value = "/rechargerecordexcel", method = RequestMethod.GET)
	 	public String RechargeRecordExcel(@RequestParam(value = "begin_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
	 			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") String end_time,
	 			@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
				@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
				@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid, 
	 			HttpServletResponse response) {	
	 		return virtualaccountservice.RechargeRecordExcel(begin_time, end_time,uid,f_uuid,operate_centerid, response);
	 	}
	  
	  @RequestMapping(value = "/uservirtualrecord", method = RequestMethod.GET)
	 	public String UserVirtualRecord(
	 			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
				@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
				@RequestParam(value = "page", required = false, defaultValue = "1")int page,
	 			Model model) {	
		  
	 		return virtualaccountservice.UserVirtualRecord(begin_time,end_time,page,model);
	 	}
	  
	  @RequestMapping(value = "/rechargequotarecordexcel", method = RequestMethod.GET)
	 	public String RechargeQuotaRecordExcel(
	 			@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid,  
				@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
				@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
	 			HttpServletResponse response) {	
	 		return virtualaccountservice.RechargeQuotaRecordExcel(operate_centerid,begin_time, end_time, response);
	 	}
	  
		@RequestMapping("/rechargequota")
		public String rechargequota( 
				@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
				@RequestParam(value = "operate_name", required = false,defaultValue="") String operate_name,  
				@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model
				) {
			return virtualaccountservice.rechargequota(operate_center_id,operate_name, page, model);
		}
		
		@ResponseBody
		@RequestMapping(value = "/saverechargequota",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
		public String saveRechargeQuota(@RequestParam(value = "id", required = false, defaultValue = "0")int id,
			@RequestParam(value = "remark", defaultValue = "", required = false)String remark,
			@RequestParam(value = "add", required = false, defaultValue="0")long add,
			HttpServletRequest request) {
			return virtualaccountservice.rechargeQuota(id, add, remark, request);
		}
		
		@ResponseBody
		@RequestMapping(value = "/addvirtual",produces = "application/json;charset=UTF-8")
		public String AddVirtual(@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid,HttpServletRequest request,Model model) {
			return virtualaccountservice.Addvirtual(f_uuid,request, model);
		}
		
		@ResponseBody
		@RequestMapping(value = "/updateisvirtual", method = RequestMethod.POST)
		public int updateUserIsVirtual(@RequestParam(value = "id", required = false,defaultValue="0")long id,
				@RequestParam(value = "is_virtual", required = false,defaultValue="0")int is_virtual,
				@CookieValue(value="w_name", defaultValue="")String w_name,
				HttpServletRequest request) {

			return virtualaccountservice.updateUserIsVirtual(id, is_virtual, w_name, request);
		}
		
		 @ResponseBody
		@RequestMapping(value = "/agentlist", produces = "text/json;charset=UTF-8")
		public String agentList(int centerId) {
				return statisticalService.agentList(centerId);
			}
}

package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.AgentPromoterService;
import com.meisui.manage.service.AgentService;
import com.meisui.manage.service.Operate_Service;
import com.meisui.manage.service.StatisticalService;

@Controller
@RequestMapping("/operate")
public class Operate_CenterController {
	
	@Autowired
	private Operate_Service operate_Service;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentPromoterService agentPromoterService;
	@Autowired
	private StatisticalService statisticalService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "province_center_id", required = false, defaultValue = "0")int province_center_id,
			@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model
			) {
		return operate_Service.list(begin_time, end_time,province_center_id, page, model);
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return operate_Service.add(model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getcity", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String getCity(@RequestParam(value="f_id",required = false, defaultValue = "0")Integer f_id) {
		return operate_Service.getCity(f_id);
	}
	
	
	@RequestMapping("/edit")
	public String edit(@RequestParam(value = "centerId", required = true)int centerId, Model model) {
		return operate_Service.edit(centerId, model);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/save",produces = "text/json;charset=UTF-8")
	public String save(
		@RequestParam(value = "id", required = false, defaultValue = "0")int id,
		@RequestParam(value = "name", required = true)String name,
		@RequestParam(value = "divide", required = true)int divide,
		@RequestParam(value = "username", required = true)String username,
		@RequestParam(value = "password", required = true)String password,
		@RequestParam(value = "nickname", required = false, defaultValue="")String nickname,
		@RequestParam(value = "contact", defaultValue = "", required = false)String contact,
		@RequestParam(value = "card_name", defaultValue = "", required = false)String card_name,
		@RequestParam(value = "card_no", defaultValue = "", required = false)String card_no,
		@RequestParam(value = "province", defaultValue = "", required = false)String province,
		@RequestParam(value = "city", defaultValue = "", required = false)String city,
		@RequestParam(value = "card_bank", defaultValue = "", required = false)String card_bank,
		@RequestParam(value = "company", defaultValue = "", required = false)String company,
		@RequestParam(value = "remark", defaultValue = "", required = false)String remark,
		@RequestParam(value = "province_center_id", defaultValue = "-1", required = false)int province_center_id,
		@RequestParam(value = "settlement_type", required = false, defaultValue="0")int settlement_type,
		HttpServletRequest request
			) {
		return operate_Service.save(id ,name, divide, username, password, nickname, contact, card_name, card_no, province, city, card_bank, company, remark,province_center_id, settlement_type, request);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getusernameexist", method = RequestMethod.GET)
	public int getUsernameExist(@RequestParam(value = "id", required = false, defaultValue="0")int id,
			@RequestParam(value = "username", required = true, defaultValue="")String username,
			HttpServletRequest request) {	
		return operate_Service.getUsernameExist(username, id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveremark")
	public int saveRemark(@RequestParam(value = "begin_time", required = true)String begin_time,
			@RequestParam(value = "end_time", required = true)String end_time,
			@RequestParam(value = "remark", required = true)String remark) {
		return operate_Service.saveRemark(begin_time, end_time, remark);
	}
	
	@RequestMapping(value = "/excel")
	public void Excel(
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time, 
			@RequestParam(value = "province_center_id", required = false, defaultValue = "0")int province_center_id,
			HttpServletRequest request, HttpServletResponse response) {
		operate_Service.excel(begin_time, end_time, province_center_id,request, response);
	}
	@RequestMapping(value = "/promoterexcel")
	public void PromoterExcel(
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time, HttpServletRequest request, HttpServletResponse response) {
		operate_Service.promoterExcel(begin_time, end_time, request, response);
	}
	
	@RequestMapping(value = "/agentexcel")
	public void AgentExcel(
			@RequestParam(value = "centerId", required = true)int centerId,
			@RequestParam(value = "agentName", defaultValue = "")String agentName,
			@RequestParam(value = "agentId", defaultValue = "0")int agentId,
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time, HttpServletRequest request, HttpServletResponse response) {
		operate_Service.AgentExcel(centerId, agentName, agentId,begin_time, end_time, request, response);
	}
	
	@RequestMapping(value = "/promoterlistexcel")
	public void PromoterListExcel(
			@RequestParam(value = "centerId")int centerId, 
			@RequestParam(value = "agentId")int agentId, 
			@RequestParam(value = "uid", defaultValue = "0")long uid,
			@RequestParam(value = "remark", defaultValue = "")String remark,
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time, HttpServletRequest request, HttpServletResponse response) {
		operate_Service.promoterListExcel(centerId, agentId, uid, remark,begin_time, end_time, request, response);
	}
	
	@RequestMapping(value = "/inviteexcel")
	public void InviteListExcel(
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "centerId")int centerId,
			@RequestParam(value = "agent_id", required = false,defaultValue="0")int agent_id,
			@RequestParam(value = "agent_promoter_id", required = false,defaultValue="0") int agent_promoter_id,
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			HttpServletRequest request, HttpServletResponse response) {
		operate_Service.InviteListExcel(begin_time, end_time, centerId, agent_id,agent_promoter_id, uid,request, response);
	}
	
	@RequestMapping(value = "/profit")
	public String profitList(@RequestParam(value = "type", defaultValue = "0", required = false)int type,
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd")String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd")String end_time,
			@RequestParam(value = "centerId", required = true)int centerId,
			@RequestParam(value = "page", defaultValue = "1")int page, Model model) {
		return operate_Service.profitList(type, begin_time, end_time, model, page, centerId);
	}
	
	@RequestMapping(value = "/agent")
	public String agentList(@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "centerId", required = true)int centerId,
			@RequestParam(value = "agentName", defaultValue = "")String agentName,
			@RequestParam(value = "agentId", defaultValue = "0")int agentId,
			@RequestParam(value = "page", defaultValue = "1")int page, Model model) {
		return operate_Service.agentList(begin_time,end_time,centerId, agentName, agentId, page, model);
	}
	
	@RequestMapping(value = "/allagent")
	public String AllagentList(@RequestParam(value = "agentName", defaultValue = "")String agentName,
			@RequestParam(value = "agentId", defaultValue = "0")int agentId,
			@RequestParam(value = "operate_center_id", defaultValue = "0")int operate_center_id,
			@RequestParam(value = "page", defaultValue = "1")int page, Model model) {
		return operate_Service.AllagentList(agentName, agentId,operate_center_id, page, model);
	}
	
	
	@RequestMapping(value = "/centerinvites")
	public String centerInvites(@RequestParam(value = "centerId")int centerId,
			@RequestParam(value = "uid", defaultValue = "0")long uid,
			@RequestParam(value = "page", defaultValue = "1")int page, Model model) {
		return operate_Service.centerInvites(centerId, uid, page, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "updateisforbid")
	private void updateIsForbid(@RequestParam(value = "id")int id, @RequestParam(value = "is_forbid")int is_forbid, HttpServletRequest request) {
		operate_Service.updateIsForbid(id,is_forbid, request);
	}
	
	@RequestMapping(value = "/agentprofit", method = RequestMethod.GET)
	public String getProfitList(@RequestParam(value = "agent_id", required = false,defaultValue="0")int agent_id,
			@RequestParam(value = "type", required = false,defaultValue="0")int type,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {

		return agentService.getProfitList(agent_id, s_time, e_time, type, page, model);
	}
	
	@RequestMapping(value = "/agentedit")
	public String agentEdit(@RequestParam(value = "agentId")int agentId, @RequestParam("centerId")int centerId, Model model) {
		return agentService.edit(agentId, centerId, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/agentsave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0")int id,
			@RequestParam(value = "name", required = false, defaultValue="")String name,
			@RequestParam(value = "remark", required = false, defaultValue="")String remark,
			@RequestParam(value = "divide", required = false, defaultValue="0")int divide,
			@RequestParam(value = "username", required = false, defaultValue="")String username,
			@RequestParam(value = "password", required = false, defaultValue="")String password,
			@RequestParam(value = "nickname", required = false, defaultValue="")String nickname,
			@RequestParam(value = "contact", required = false, defaultValue="")String contact,
			@RequestParam(value = "card_name", required = false, defaultValue="")String card_name,
			@RequestParam(value = "card_no", required = false, defaultValue="")String card_no,
			@RequestParam(value = "card_bank", required = false, defaultValue="")String card_bank,
			@RequestParam(value = "province", required = false, defaultValue="0")int province,
			@RequestParam(value = "city", required = false, defaultValue="0")int city,
			@RequestParam(value = "company", required = false, defaultValue="")String company,
			Model model,HttpServletRequest request) {	
		return agentService.save(id, name, remark, divide, username, password, nickname, contact, card_name, card_no, card_bank, province, city, company, model, request);
	}
	
	@RequestMapping(value = "/promoterlist")
	public String AgentPromoterList(@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "centerId")int centerId, @RequestParam(value = "agentId")int agentId, @RequestParam(value = "uid", defaultValue = "0")long uid,@RequestParam(value = "remark", defaultValue = "")String remark, @RequestParam(value = "page", defaultValue = "1")int page, Model model) {
		return agentPromoterService.list(begin_time,end_time,centerId, agentId, uid, remark, model, page);
	}
	
	@RequestMapping(value = "/allpromoterlist")
	public String AllPromoterList(
			@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "agent_id", required = false,defaultValue="0")int agent_id,
			@RequestParam(value = "f_uuid", required = false,defaultValue="")String f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname,
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0")int operate_center_id,
			@RequestParam(value = "invite_code", required = false,defaultValue="")String invite_code,
			@RequestParam(value = "page", defaultValue = "1")int page, 
			Model model) {
		return agentPromoterService.allpromoterlist(begin_time,end_time,agent_id,f_uuid,nickname,operate_center_id,invite_code,model, page);
	}
	
	
	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public String getInviteList(@RequestParam(value = "begin_time", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") String begin_time,
			@RequestParam(value = "end_time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "centerId")int centerId,
			@RequestParam(value = "agent_id", required = false,defaultValue="0")int agent_id,
			@RequestParam(value = "agentId", required = false,defaultValue="0")int agentId,
			@RequestParam(value = "agent_promoter_id", required = false,defaultValue="0") int agent_promoter_id,
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {
		if(agentId>0&&agent_id==0)
			agent_id = agentId;
		return agentPromoterService.getInviteList(begin_time,end_time,centerId, agent_id, agent_promoter_id, uid, page, model, request);
	}
	
	@ResponseBody
	@RequestMapping("/updateagentisforbid")
	public void updateAgentIsForbid(@RequestParam(value = "id")int id, @RequestParam(value = "is_forbid")int is_forbid, HttpServletRequest request) {
		agentService.updateAgentIsForbid(id, is_forbid, request);
	}
	@ResponseBody
	@RequestMapping(value = "/getagentusernameexist", method = RequestMethod.GET)
	public int getAgentUsernameExist(@RequestParam(value = "id", required = false, defaultValue="0")int id,
			@RequestParam(value = "username", required = true, defaultValue="")String username,
			HttpServletRequest request) {	
		return agentService.getAgentUsernameExist(username, id);
	}
	@ResponseBody
	@RequestMapping(value = "/agentlist", produces = "text/json;charset=UTF-8")
	public String agentList(int centerId) {
		return statisticalService.agentList(centerId);
	}
}

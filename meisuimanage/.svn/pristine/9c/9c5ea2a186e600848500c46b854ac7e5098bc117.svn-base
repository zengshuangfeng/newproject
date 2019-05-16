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

import com.meisui.manage.service.SettlementService;

@Controller
@RequestMapping(value = "settlement")
public class SettlementController {
	@Autowired
	private SettlementService settlementService;

	@RequestMapping(value = "/ocnlist", method = RequestMethod.GET)
	public String getNoList(@RequestParam(value = "centerId", defaultValue = "0", required = true) int centerId,
			@RequestParam(value = "type", required = false, defaultValue = "0") int type,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpServletRequest request) {
		return settlementService.getNoList(centerId, type, page, model, request);
	}

	@RequestMapping(value = "/oclist", method = RequestMethod.GET)
	public String getList(
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "centerId", required = false, defaultValue = "0") int centerId, Model model,
			HttpServletRequest request) {

		return settlementService.getList(s_time, e_time, page, model, request, centerId);
	}

	@RequestMapping(value = "/alist", method = RequestMethod.GET)
	public String getAList(
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date e_time,
			@RequestParam(value = "operate_center_id", required = false, defaultValue = "0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false, defaultValue = "0") int agent_id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "type", required = false, defaultValue = "0") int type, Model model,
			HttpServletRequest request) {

		return settlementService.getAList(s_time, e_time, operate_center_id, agent_id, page, model, request, type);
	}

	@RequestMapping(value = "/anlist", method = RequestMethod.GET)
	public String getANList(
			@RequestParam(value = "operate_center_id", required = false, defaultValue = "0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false, defaultValue = "0") int agent_id,
			@RequestParam(value = "type", defaultValue = "0") int type,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model,
			HttpServletRequest request) {
		return settlementService.getAgentNoList(operate_center_id, agent_id, page, model, request, type);
	}

	@ResponseBody
	@RequestMapping(value = "/cleancenter", method = RequestMethod.POST)
	public int cleanCenter(@RequestParam(value = "type", required = true) int type,
			@RequestParam(value = "centerId", required = true) int centerId, HttpServletRequest request) {
		return settlementService.cleanCenter(type, centerId, request);
	}

	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(
			@RequestParam(value = "operate_center_id", required = false, defaultValue = "0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false, defaultValue = "0") int agent_id,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date e_time,
			@RequestParam(value = "type", defaultValue = "0") int type, HttpServletRequest request,
			HttpServletResponse response) {
		return settlementService.exportExcel(operate_center_id, agent_id, s_time, e_time, request, response, type);
	}

	@RequestMapping(value = "/oclistexcel", method = RequestMethod.GET)
	public String oclistExcel(
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date e_time,
			@RequestParam(value = "centerId", defaultValue = "0") int centerId, HttpServletRequest request,
			HttpServletResponse response) {
		return settlementService.oclistExcel(s_time, e_time, centerId, request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/agentlist", produces = "text/json;charset=UTF-8")
	public String agentList(int centerId) {
		return settlementService.agentList(centerId);
	}
	
	@RequestMapping(value = "/bonus", method = RequestMethod.GET)
	public String getBonusList(
			@RequestParam(value = "type", required = false, defaultValue = "0") int type,
			@RequestParam(value = "operate_center_id", required = false, defaultValue = "0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false, defaultValue = "0") int agent_id,
			@RequestParam(value = "agent_promoter_f_uuid", required = false, defaultValue = "0") int agent_promoter_f_uuid,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date e_time,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpServletRequest request) {
		return settlementService.getBonusList(operate_center_id,agent_id,agent_promoter_f_uuid, type,s_time, e_time,page, model, request);
	}
	

	@RequestMapping(value = "/exportbonusexcel", method = RequestMethod.GET)
	public String exportBonusExcel(
			@RequestParam(value = "type", required = false, defaultValue = "0") int type,
			@RequestParam(value = "operate_center_id", required = false, defaultValue = "0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false, defaultValue = "0") int agent_id,
			@RequestParam(value = "agent_promoter_f_uuid", required = false, defaultValue = "0") int agent_promoter_f_uuid,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date e_time, HttpServletRequest request,
			HttpServletResponse response) {
		return settlementService.exportBonusExcel(operate_center_id,agent_id,agent_promoter_f_uuid, type,s_time, e_time,request,response);
	}
}

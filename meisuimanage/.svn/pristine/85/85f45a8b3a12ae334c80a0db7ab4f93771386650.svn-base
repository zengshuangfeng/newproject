package com.meisui.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.DoubleUtil;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_SettlementDao;
import com.meisui.manage.dao.Ianchor_Virtual_Change_RecordDao;
import com.meisui.manage.dao.IexchangeVirtualDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.Ioperate_Center_SettlementDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Settlement;
import com.meisui.manage.entity.Anchor_Virtual_Change_Record;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Operate_Center_Settlement;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;

import net.sf.json.JSONObject;

@Service
public class SettlementService {

	@Autowired
	private Ioperate_CenterDao operate_CenterDao;
	@Autowired
	private Ioperate_Center_SettlementDao ioperate_Center_SettlementDao;
	@Autowired
	private IagentDao iagentDao;
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Iagent_SettlementDao iagent_SettlementDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Ianchor_Virtual_Change_RecordDao ianchor_Virtual_Change_RecordDao;
	@Autowired
	private IexchangeVirtualDao iexchangevirtualDao;

	private static Logger log = Logger.getLogger(SettlementService.class.getClass());

	public String getNoList(int centerId, int type, int page, Model model, HttpServletRequest request) {
		try {
			Date date = new Date();
			Date start_time = null, end_time = null;
			if(type==0)
			{
				Calendar calendar1 = Calendar.getInstance();  
				Calendar calendar2 = Calendar.getInstance();  
				int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1; //当前星期
				int offset1 = 1 - dayOfWeek;  
				int offset2 = 7 - dayOfWeek;  
				calendar1.add(Calendar.DATE, offset1 - 7);  
				calendar2.add(Calendar.DATE, offset2 - 7);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");//上周一00:00:00
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");//上周日23:59:59		
			}
			else if(type==1)
			{
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 00:00:00");
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 23:59:59");
			}
			List<Operate_Center> centerList = operate_CenterDao.getListAll();
			List<Integer> settlement_id_list = ioperate_Center_SettlementDao.getHasSettlementList(centerId, type, start_time, end_time);
			List<Operate_Center> list = operate_CenterDao.getList2(centerId, type, settlement_id_list, (page - 1) * 20 , 20);
			int totalRecord = operate_CenterDao.getListCount2(centerId, type, settlement_id_list);
			for(Operate_Center operate_Center : list) {
				operate_Center.setTotal_exchange_virtual(iexchangevirtualDao.getExchangeVirtual(operate_Center.getId(),0,start_time, end_time));//已兑换魅力值
				long total_anchor_virtual = iagentDao.getAgentVirtualSum(operate_Center.getId());
				operate_Center.setTotal_anchor_virtual(total_anchor_virtual);
				long surplus_anchor_virtual = 0l;
				if(operate_Center.getSettlement_type()==0)
				{
					surplus_anchor_virtual = iprofitDao.getAllProfitSum(operate_Center.getId(), start_time, end_time);
				}
				else if(operate_Center.getSettlement_type()==1)
				{
					surplus_anchor_virtual = iagentDao.getAgentSurplusVirtualSum(operate_Center.getId());
					Date last_date = ioperate_Center_SettlementDao.getLastSettlementTime(operate_Center.getId(), 1);
					if(last_date!=null)//从上一次结算开始算起
					{
						long current_settlement = iprofitDao.getAllProfitSum(operate_Center.getId(), last_date, date);
						if(current_settlement<surplus_anchor_virtual)
							surplus_anchor_virtual = current_settlement;
					}
				}
				if(surplus_anchor_virtual>0)
				{
					double money_count = DoubleUtil.div((double)surplus_anchor_virtual-(double)iexchangevirtualDao.getExchangeVirtual(operate_Center.getId(),0,start_time, end_time), 100, 2);
					double divide_percent = DoubleUtil.div(operate_Center.getDivide(), 100, 2);
					money_count = DoubleUtil.mul(money_count, divide_percent);
					operate_Center.setMoney_count(money_count);
				}
				operate_Center.setSurplus_anchor_virtual(surplus_anchor_virtual);
			}
			
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("settlement/ocnlist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "settlementonclist");
			model.addAttribute("list", list);
			model.addAttribute("centerId", centerId);
			model.addAttribute("type", type);
			model.addAttribute("centerList", centerList);
		} catch (Exception e) {
			log.error("运营中心未结算", e);
		}
		return "settlement/ocnlist";
	}

	public String getList(Date s_time, Date e_time, int page, Model model, HttpServletRequest request, int centerId) {
		try {
			String e_timeString = e_time != null ? AuthUtil.formatDateToString(e_time, "yyyy-MM-dd") : "";
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			List<Operate_Center> centerList = operate_CenterDao.getListAll();
			List<Operate_Center_Settlement> settlementList = ioperate_Center_SettlementDao.getSettlementList(centerId, s_time, e_time, (page-1)*20, 20);
			Map<Integer, String> centerNameMap = new HashMap<Integer, String>();
			for (Operate_Center_Settlement operate_Center_Settlement : settlementList) {
				operate_Center_Settlement.setTotal_exchange_virtual(iexchangevirtualDao.getExchangeVirtual(operate_Center_Settlement.getOperate_center_id(),0,s_time, e_time));//已兑换魅力值
				
				if(centerNameMap.containsKey(operate_Center_Settlement.getOperate_center_id())) {
					operate_Center_Settlement.setName(centerNameMap.get(operate_Center_Settlement.getOperate_center_id()));
				}else {
					Operate_Center center = operate_CenterDao.getOperateCenterById(operate_Center_Settlement.getOperate_center_id());
					operate_Center_Settlement.setName(center.getName());
					centerNameMap.put(operate_Center_Settlement.getOperate_center_id(), center.getName());
				}
				long total_anchor_virtual = iagentDao.getAgentVirtualSum(operate_Center_Settlement.getOperate_center_id());
				operate_Center_Settlement.setTotal_anchor_virtual(total_anchor_virtual);
			}
			int totalRecord = ioperate_Center_SettlementDao.getSettlementCount(centerId, s_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("settlement/oclist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("settlementList", settlementList);
			model.addAttribute("s_time", s_time != null ? AuthUtil.formatDateToString(s_time, "yyyy-MM-dd") : "");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("activeUrl", "settlementoclist");
			model.addAttribute("centerList", centerList);
			model.addAttribute("centerId", centerId);
		} catch (Exception e) {
			log.error("运营中心已结算", e);
		}
		return "settlement/oclist";
	}

	public int cleanCenter(int type, int centerId, HttpServletRequest request) {
		int result = 0;
		try {
			Date date = new Date();
			Date start_time = null, end_time = null;
			String w_name = String.valueOf(request.getAttribute("w_name"));
			if(type==0)
			{
				Calendar calendar1 = Calendar.getInstance();  
				Calendar calendar2 = Calendar.getInstance();  
				int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1; //当前星期
				int offset1 = 1 - dayOfWeek;  
				int offset2 = 7 - dayOfWeek;  
				calendar1.add(Calendar.DATE, offset1 - 7);  
				calendar2.add(Calendar.DATE, offset2 - 7);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");//上周一00:00:00
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");//上周日23:59:59		
			}
			else if(type==1)
			{
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 00:00:00");
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 23:59:59");
			}
			List<Integer> settlement_id_list = ioperate_Center_SettlementDao.getHasSettlementList(centerId, type, start_time, end_time);
			List<Operate_Center> list = operate_CenterDao.getList3(centerId, type, settlement_id_list);
			for(Operate_Center operate_Center : list) {
				long total_anchor_virtual = iagentDao.getAgentVirtualSum(operate_Center.getId());
				if(total_anchor_virtual == 0) {//总魅力值为0 不参与结算
					continue;
				}
				operate_Center.setTotal_anchor_virtual(total_anchor_virtual);
				long surplus_anchor_virtual = 0l;
				if(operate_Center.getSettlement_type()==0)
				{
					surplus_anchor_virtual = iprofitDao.getAllProfitSum(operate_Center.getId(), start_time, end_time);
				}
				else if(operate_Center.getSettlement_type()==1)
				{
					surplus_anchor_virtual = iagentDao.getAgentSurplusVirtualSum(operate_Center.getId());//默认清全部剩余
					Date last_date = ioperate_Center_SettlementDao.getLastSettlementTime(operate_Center.getId(), 1);
					if(last_date!=null)//从上一次结算开始算起
					{
						long current_settlement = iprofitDao.getAllProfitSum(operate_Center.getId(), last_date, date);
						if(current_settlement<surplus_anchor_virtual)
							surplus_anchor_virtual = current_settlement-iexchangevirtualDao.getExchangeVirtual(operate_Center.getId(),0,start_time, end_time);//所统计魅力值减去兑换钻石所消费魅力值
					}
				}
				if(surplus_anchor_virtual>0)
				{
					double money_count = DoubleUtil.div((double)surplus_anchor_virtual, 100, 2);
					double divide_percent = DoubleUtil.div(operate_Center.getDivide(), 100, 2);
					money_count = DoubleUtil.mul(money_count, divide_percent);
					operate_Center.setMoney_count(money_count);
				}
				operate_Center.setSurplus_anchor_virtual(surplus_anchor_virtual);
				ioperate_Center_SettlementDao.add(operate_Center.getId(),operate_Center.getSettlement_type(),operate_Center.getSurplus_anchor_virtual(),operate_Center.getDivide(),operate_Center.getMoney_count(), start_time, end_time, date, w_name);
				if(surplus_anchor_virtual>0&&operate_Center.getSettlement_type()==0)
				{
					List<Integer> operate_Center_Ids = new ArrayList<>();
					operate_Center_Ids.add(operate_Center.getId());
					List<Agent> agentSList = iagentDao.getAgentSettlementList(operate_Center_Ids, 0, 0, Integer.MAX_VALUE);
					for (Agent agent : agentSList) {
						long virtual_count = iprofitDao.getProfitSumWithAgentId(agent.getId(), start_time, end_time);
						if(virtual_count>agent.getSurplus_anchor_virtual())
							virtual_count = agent.getSurplus_anchor_virtual();
						if(virtual_count>0)
						{
							Agent_Settlement agent_Settlement = new Agent_Settlement();
							agent_Settlement.setVirtual_count((int)virtual_count);
							double money_count = DoubleUtil.div((double)virtual_count, 100, 2);
							double divide_percent = DoubleUtil.div(agent.getDivide(), 100, 2);
							money_count = DoubleUtil.mul(money_count, divide_percent);
							agent_Settlement.setMoney_count(money_count);
							agent_Settlement.setOperate_center_id(operate_Center.getId());
							agent_Settlement.setAgent_id(agent.getId());
							agent_Settlement.setDivide(agent.getDivide());
							agent_Settlement.setSettlement_type(operate_Center.getSettlement_type());
							agent_Settlement.setStart_time(start_time);
							agent_Settlement.setEnd_time(end_time);
							agent_Settlement.setCreate_time(date);
							agent_Settlement.setW_name(w_name);
							iagent_SettlementDao.insertSettlement(agent_Settlement);
							if(agent_Settlement.getId()>0)
							{
								int aresult = iagentDao.clearAgent(agent.getId(),virtual_count);
								if(aresult>0)
								{
									List<User_Info> user_InfoList = iuserDao.getSettlementUserList(agent.getId());
									for (User_Info user_Info : user_InfoList) {
										long user_clear_virtual_count = 0l;
										if(operate_Center.getSettlement_type()==0)
										{
											user_clear_virtual_count = iprofitDao.getProfitSum(user_Info.getUid(), start_time, end_time);	
											if(user_clear_virtual_count>user_Info.getSurplus_anchor_virtual())
												user_clear_virtual_count = user_Info.getSurplus_anchor_virtual(); 
										}
										Anchor_Virtual_Change_Record anchor_Virtual_Change_Record = new Anchor_Virtual_Change_Record();
										anchor_Virtual_Change_Record.setUid(user_Info.getUid());
										anchor_Virtual_Change_Record.setW_name(w_name);
										anchor_Virtual_Change_Record.setChange_virtual(user_clear_virtual_count);
										anchor_Virtual_Change_Record.setTotal_anchor_virtual(user_Info.getTotal_anchor_virtual());
										anchor_Virtual_Change_Record.setSurplus_anchor_virtual(user_Info.getSurplus_anchor_virtual()-user_clear_virtual_count);
										Long total_change_virtual = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeSum(user_Info.getUid());
										if(total_change_virtual==null)
											total_change_virtual = 0L;
										anchor_Virtual_Change_Record.setTotal_change_virtual(total_change_virtual+user_clear_virtual_count);
										anchor_Virtual_Change_Record.setRemark("");
										anchor_Virtual_Change_Record.setCreate_time(date);
										ianchor_Virtual_Change_RecordDao.insertAnchorVirtualChangeRecord(anchor_Virtual_Change_Record);
										iuserDao.updateUserSurplusAnchorVirtual(user_clear_virtual_count, user_Info.getUid());
									}
								}
							}
						}
					}
				}
			}
			result = 1;
		}catch (Exception e) {
			log.error("运营中心清除魅力值", e);
		}
		return result;
	}
	public String getAgentNoList(int operate_center_id ,int agent_id, int page, Model model, HttpServletRequest request, int type)
	{
		try {
			Date start_time = null, end_time = null, date = new Date();
			if(type == 0)
			{
				Calendar calendar1 = Calendar.getInstance();  
				Calendar calendar2 = Calendar.getInstance();  
				int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1; //当前星期
				int offset1 = 1 - dayOfWeek;  
				int offset2 = 7 - dayOfWeek;  
				calendar1.add(Calendar.DATE, offset1 - 7);  
				calendar2.add(Calendar.DATE, offset2 - 7);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");//上周一00:00:00
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");//上周日23:59:59		
			}
			else if(type == 1)
			{
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 00:00:00");
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 23:59:59");
			}
			List<Integer> centerIds = operate_CenterDao.getIdList(type, operate_center_id);
			List<Agent> agentList = new ArrayList<>();
			List<Agent> agentSList = new ArrayList<>();
			if(centerIds.size()>0)
				agentSList = iagentDao.getAgentSettlementList(centerIds, agent_id, 0, Integer.MAX_VALUE);
			Map<Integer, String> agents = new HashMap<Integer, String>();
			List<Operate_Center> operateCenterList = operate_CenterDao.getListAll();
			for (Agent agent : agentSList) {
				agent.setTotal_exchange_virtual(iexchangevirtualDao.getExchangeVirtual(0,agent.getId(),start_time, end_time));//已兑换魅力值
				for (Operate_Center operate_Center : operateCenterList) {
					if(agent.getOperate_center_id()==operate_Center.getId())
					{
						agent.setOperate_center_name(operate_Center.getName());
						break;
					}
				}
				Date o_last_date = ioperate_Center_SettlementDao.getLastSettlementTime(agent.getOperate_center_id(), type);//运营中心最后结算时间
				Date a_last_date = iagent_SettlementDao.getLastSettlementTime(agent.getId(), type);//代理最后结算时间
				if(o_last_date!=null&&(a_last_date==null||a_last_date.before(o_last_date)))//运营已结算代理未结算
				{
					long virtual_count = 0;
					if(type == 0)//如果是周结
						virtual_count = iprofitDao.getProfitSumWithAgentId(agent.getId(), start_time, end_time);
					else//日结
					{
						if(a_last_date==null)
						{
							Calendar calendar = new GregorianCalendar();
							calendar.setTime(date);
							calendar.add(Calendar.YEAR, -5);
							a_last_date = calendar.getTime();
						}
						//从上一次运营中心结算开始算起
						virtual_count = iprofitDao.getProfitSumWithAgentId(agent.getId(), a_last_date, o_last_date);
					}
					if(virtual_count>agent.getSurplus_anchor_virtual())
						virtual_count = agent.getSurplus_anchor_virtual();
					if(virtual_count>0)
					{
						agents.put(agent.getId(), agent.getName());
						agent.setVirtual_count((int)virtual_count);
						double money_count = DoubleUtil.div((double)virtual_count-(double)iexchangevirtualDao.getExchangeVirtual(0,agent.getId(),start_time, end_time), 100, 2);
						double divide_percent = DoubleUtil.div(agent.getDivide(), 100, 2);
						money_count = DoubleUtil.mul(money_count, divide_percent);
						agent.setMoney_count(money_count);
						agentList.add(agent);
					}
				}
			}
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("agents", agents);
			model.addAttribute("activeUrl", "settlementanlist");
			model.addAttribute("list", agentList);
			model.addAttribute("type", type);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("operateCenterList", operateCenterList);
		} catch (Exception e) {
			log.error("代理未结算", e);
		}
		return "settlement/anlist";
	}

	public int clearAgent(int type, int agent_id, HttpServletRequest request) {
		try {
			Date start_time = null, end_time = null, date = new Date();
			if(type == 0)
			{
				Calendar calendar1 = Calendar.getInstance();  
				Calendar calendar2 = Calendar.getInstance();  
				int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1; //当前星期
				int offset1 = 1 - dayOfWeek;  
				int offset2 = 7 - dayOfWeek;  
				calendar1.add(Calendar.DATE, offset1 - 7);  
				calendar2.add(Calendar.DATE, offset2 - 7);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");//上周一00:00:00
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");//上周日23:59:59		
			}
			else if(type == 1)
			{
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 00:00:00");
				end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 23:59:59");
			}
			List<Integer> agentIds = iagent_SettlementDao.getAgentIdList(type, start_time, end_time);//获取已结算的代理ID
			List<Agent> agentSList = iagentDao.getAgentSettlementList2(agentIds, type, agent_id);
			String w_name = String.valueOf(request.getAttribute("w_name"));
			for (Agent agent : agentSList) {
				Date o_last_date = ioperate_Center_SettlementDao.getLastSettlementTime(agent.getOperate_center_id(), type);//运营中心最后结算时间
				Date a_last_date = iagent_SettlementDao.getLastSettlementTime(agent.getId(), type);//代理最后结算时间
				if(o_last_date!=null&&(a_last_date==null||a_last_date.before(o_last_date)))//运营已结算代理未结算
				{
					long virtual_count = 0;
					if(type == 0)//如果是周结
						virtual_count = iprofitDao.getProfitSumWithAgentId(agent.getId(), start_time, end_time);
					else//日结
					{
						if(a_last_date==null)
						{
							Calendar calendar = new GregorianCalendar();
							calendar.setTime(date);
							calendar.add(Calendar.YEAR, -5);
							a_last_date = calendar.getTime();
						}
						//从上一次运营中心结算开始算起
						virtual_count = iprofitDao.getProfitSumWithAgentId(agent.getId(), a_last_date, o_last_date);
					}
					if(virtual_count>agent.getSurplus_anchor_virtual())
						virtual_count = agent.getSurplus_anchor_virtual();
					if(virtual_count>0)
					{
						agent.setVirtual_count((int)virtual_count);
						double money_count = DoubleUtil.div((double)virtual_count, 100, 2);
						double divide_percent = DoubleUtil.div(agent.getDivide(), 100, 2);
						money_count = DoubleUtil.mul(money_count, divide_percent);
						agent.setMoney_count(money_count);
						iagent_SettlementDao.add(agent.getId(),agent.getOperate_center_id(),agent.getSettlement_type(),agent.getVirtual_count(),agent.getDivide(),agent.getMoney_count(),start_time, end_time, date, w_name);
					}
				}
			}
			return 1;
		}catch (Exception e) {
			log.error("一键清除代理魅力值", e);
		}
		return 0;
	}

	public String getAList(Date s_time, Date e_time, int operate_center_id, int agent_id, int page, Model model, HttpServletRequest request, int type) {
		try {
			String e_timeString = e_time != null ? AuthUtil.formatDateToString(e_time, "yyyy-MM-dd") : "";
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			Map<Integer, String> agentMap = new HashMap<Integer, String>();
			List<Operate_Center> operateCenterList = operate_CenterDao.getListAll();
			List<Agent_Settlement> settlementList = iagent_SettlementDao.getSettlementList(operate_center_id, agent_id, s_time, e_time, (page-1)*20, 20, type);
			for (Agent_Settlement agent_Settlement : settlementList) {			
				agent_Settlement.setTotal_exchange_virtual(iexchangevirtualDao.getExchangeVirtual(0,agent_Settlement.getAgent_id(),s_time, e_time));//已兑换魅力值
				Agent agent = iagentDao.getAgent(agent_Settlement.getAgent_id());
				agent_Settlement.setName(agent.getName());
				agent_Settlement.setTotal_anchor_virtual(agent.getTotal_anchor_virtual());
				agentMap.put(agent_Settlement.getAgent_id(), agent.getName());
				
				
				for (Operate_Center operate_Center : operateCenterList) {
					if(agent_Settlement.getOperate_center_id()==operate_Center.getId())
					{
						agent_Settlement.setOperate_center_name(operate_Center.getName());
						break;
					}
				}
			}
			int totalRecord = iagent_SettlementDao.getSettlementCount(operate_center_id, agent_id, s_time, e_time, type);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("settlement/alist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("settlementList", settlementList);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("agentMap", agentMap);
			model.addAttribute("s_time", s_time != null ? AuthUtil.formatDateToString(s_time, "yyyy-MM-dd") : "");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("activeUrl", "settlementalist");
			model.addAttribute("type", type);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("operateCenterList", operateCenterList);
		} catch (Exception e) {
			log.error("代理已结算", e);
		}
		return "settlement/alist";
	}

	public String exportExcel(int operate_center_id, int agent_id, Date s_time, Date e_time, HttpServletRequest request,
			HttpServletResponse response, int type) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			String fileName = new String(("代理已结算列表").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"代理ID","代理名称","类型","总魅力值","本次结算魅力值","代理分成", "总收益（元）", "结算时间"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			} 
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			// 构建表体数据  
			int j=0;
			List<Agent_Settlement> settlementList = iagent_SettlementDao.getSettlementList(operate_center_id, agent_id, s_time, e_time, 0, Integer.MAX_VALUE, type);
			for (Agent_Settlement agent_Settlement : settlementList) {
				Agent agent = iagentDao.getAgent(agent_Settlement.getAgent_id());
				agent_Settlement.setName(agent.getName());
				agent_Settlement.setTotal_anchor_virtual(agent.getTotal_anchor_virtual());				

				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getAgent_id());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getName()); 

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getSettlement_type()==0?"周结":"日结");

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getTotal_anchor_virtual());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getVirtual_count());


				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getDivide()+"%");


				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getMoney_count()+"元");

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(agent_Settlement.getCreate_time());

				j++;
			} 
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		} catch (Exception e) {
			log.error("导出代理已结算列表", e);
		}
		return null;
	}

	public String oclistExcel(Date s_time, Date e_time, int centerId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if(e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}

			ServletOutputStream outputStream = response.getOutputStream();  
			String fileName = new String(("运营中心结算日志").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"运营中心ID","运营中心名称","类型","总魅力值","本次结算魅力值","运营中心分成", "总收益（元）", "结算时间"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			} 

			int j=0;
			List<Operate_Center_Settlement> settlementList = ioperate_Center_SettlementDao.getSettlementList(centerId, s_time, e_time, 0, Integer.MAX_VALUE);
			Map<Integer, String> centerNameMap = new HashMap<Integer, String>();
			for (Operate_Center_Settlement operate_Center_Settlement : settlementList) {
				if(centerNameMap.containsKey(operate_Center_Settlement.getOperate_center_id())) {
					operate_Center_Settlement.setName(centerNameMap.get(operate_Center_Settlement.getOperate_center_id()));
				}else {
					Operate_Center center = operate_CenterDao.getOperateCenterById(operate_Center_Settlement.getOperate_center_id());
					operate_Center_Settlement.setName(center.getName());
					centerNameMap.put(operate_Center_Settlement.getOperate_center_id(), center.getName());
				}
				long total_anchor_virtual = iagentDao.getAgentVirtualSum(operate_Center_Settlement.getOperate_center_id());
				operate_Center_Settlement.setTotal_anchor_virtual(total_anchor_virtual);

				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getOperate_center_id());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getName()); 

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getSettlement_type()==0?"周结":"日结");

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getTotal_anchor_virtual());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getVirtual_count());


				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getDivide()+"%");


				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getMoney_count()+"元");

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(operate_Center_Settlement.getCreate_time());

				j++;
			}
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		}catch (Exception e) {

		}
		return null;
	}

	public String agentList(int centerId) {
		JSONObject jsonObject = new JSONObject();
		try {
			List<Agent> list = iagentDao.getAgentListByCenterId(centerId);
			jsonObject.put("list", list);
		}catch (Exception e) {
			log.error("获取运营中心下所有代理列表");
		}
		return jsonObject.toString();
	}

	public String getBonusList(int operate_center_id, int agent_id,int agent_promoter_f_uuid, int type, Date s_time, Date e_time, int page,
			Model model, HttpServletRequest request) {
		try {
			List<User> userlist=iuserDao.getUserByAnchorList(operate_center_id,agent_id,agent_promoter_f_uuid,type,s_time,e_time,(page - 1) * 20 , 20);
			for(User list:userlist){
				if(list.getAgent_promoter_id()==0){
					User user=iuserDao.getUser(list.getId());
					list.setPromoter_f_uuid(user.getF_uuid());
				}else{
					User user=iuserDao.getUser((long)list.getAgent_promoter_id());
					list.setPromoter_f_uuid(user.getF_uuid());
				}
				Long settlement_virtual=iprofitDao.getProfitByPromoter(list.getId(),list.getStart_time(),list.getEnd_time());//该时间内主播获得的魅力值
				//Long surplus_virtual=settlement_virtual;//本次结算魅力值
				list.setSurplus_virtual(settlement_virtual);
			}
			
			int totalRecord=iuserDao.getUserByAnchorCount(operate_center_id,agent_id,agent_promoter_f_uuid,type,s_time,e_time);
			List<Operate_Center> operateCenterList = operate_CenterDao.getListAll();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("settlement/bonus");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "bonus");
			model.addAttribute("type", type);
			model.addAttribute("userlist", userlist);
			model.addAttribute("s_time", s_time);
			model.addAttribute("e_time", e_time);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("agent_promoter_f_uuid", agent_promoter_f_uuid);
			model.addAttribute("operateCenterList", operateCenterList);
		}catch (Exception e) {
			log.error("主播将奖励金列表");
		}
		return "settlement/bonus";
	}

	public String exportBonusExcel(int operate_center_id, int agent_id, int agent_promoter_f_uuid, int type,
			Date s_time, Date e_time, HttpServletRequest request,HttpServletResponse response) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			String fileName = new String(("代主播奖励金列表").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"代理ID","代理名称","所属运营中心","主播昵称","结算类型","推广员账号","本次结算魅力值","代理分成", "奖励金额（元）", "结算时间"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			} 
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			// 构建表体数据  
			int j=0;
			
			List<User> userlist=iuserDao.getUserByAnchorList(operate_center_id,agent_id,agent_promoter_f_uuid,type,s_time,e_time,0,Integer.MAX_VALUE);
			for(User list:userlist){
				if(list.getAgent_promoter_id()==0){
					User user=iuserDao.getUser(list.getId());
					list.setPromoter_f_uuid(user.getF_uuid());
				}else{
					User user=iuserDao.getUser((long)list.getAgent_promoter_id());
					list.setPromoter_f_uuid(user.getF_uuid());
				}
				Long settlement_virtual=iprofitDao.getProfitByPromoter(list.getId(),list.getStart_time(),list.getEnd_time());//该时间内主播获得的魅力值
				list.setSurplus_virtual(settlement_virtual);
				
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getAgent_id());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getAgentName()); 

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getOperate_center_name());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getNickname());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);
				String settlementname="";
				if(list.getSettlement_type()==0){
					settlementname="周结";
				}else{
					settlementname="日结";
				}
				cell.setCellValue(settlementname);


				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getPromoter_f_uuid());


				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getSurplus_virtual());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(list.getDivide()+"%");

				j++;
			}
			
			
			
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			}  
		} catch (Exception e) {
			log.error("导出主播奖励金列表", e);
		}
		return null;
	}

}

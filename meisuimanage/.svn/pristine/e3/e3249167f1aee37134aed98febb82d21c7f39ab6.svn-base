package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_AdminDao;
import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.IareaDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.Area;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.Profit;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

import net.sf.json.JSONObject;

@Service
public class AgentService {
	
	private static Logger log = Logger.getLogger(AgentService.class.getClass());
	
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Igift_InfoDao igift_InfoDao;
	@Autowired
	private IagentDao iagentDao;
	@Autowired
	private IareaDao iareaDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Iagent_AdminDao iagent_AdminDao;
	@Autowired
	private Ianchor_Guard_ChangeDao iancor_guard_changeDao;

	public String getProfitList(int agent_id, Date s_time, Date e_time, int type, int page, Model model)
	{
		try
		{
			String e_timeString = e_time != null ? AuthUtil.formatDateToString(e_time, "yyyy-MM-dd") : "";
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			List<Profit> profitList = iprofitDao.getProfitListWithAgentId(agent_id, type, s_time, e_time, (page-1)*20, 20);
			for (Profit profit : profitList) {
				User user = iuserDao.getUser(profit.getUid());
				profit.setF_uuid(user.getF_uuid());
				profit.setNickname(user.getNickname());
				if(profit.getType()==9 || profit.getType()==10){
					Anchor_Guard_Change2 guardchange=iancor_guard_changeDao.getAnchorGuardNewByid(profit.getGift_id());
					profit.setGift_name(guardchange.getName());
					profit.setGift_original_virtual(guardchange.getChange_virtual());	
				}else{
					Gift_Info gift_Info = igift_InfoDao.getGiftInfo(profit.getGift_id());
					profit.setGift_name(gift_Info.getGift_name());
					profit.setGift_original_virtual(gift_Info.getGift_virtual());	
				}
				
			}
			int totalRecord = iprofitDao.getProfitCountWithAgentId(agent_id, type, s_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/agentprofit");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("profitList", profitList);
			model.addAttribute("s_time", s_time != null ? AuthUtil.formatDateToString(s_time, "yyyy-MM-dd") : "");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("type", type);
			model.addAttribute("activeUrl", "operate");
		}
		catch (Exception e) {
			log.error("代理收益列表", e);
		}
		return "agent/profitlist";
	}

	public String edit(int agentId, int centerId, Model model) {
		try
		{
			Agent agent =  iagentDao.getAgent(agentId);
			List<Area> areaList = iareaDao.getProvinceList();
			List<Area> cityList = new ArrayList<>();
			if(agent.getProvince()>0)
				cityList = iareaDao.getAreaListWithFid(agent.getProvince());
			model.addAttribute("areaList", areaList);
			model.addAttribute("cityList", cityList);
			model.addAttribute("agent", agent);
			model.addAttribute("centerId", centerId);
			model.addAttribute("activeUrl", "operate");
		}
		catch (Exception e) {
			log.error("代理编辑页面", e);
		}
		return "agent/edit";
	}

	public String save(int id, String name, String remark, int divide, String username, String password, String nickname, String contact, String card_name, String card_no, String card_bank, int province, int city, String company,Model model, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try
		{
			Agent agent = new Agent();
			agent.setName(name);
			agent.setRemark(remark);
			agent.setUsername(username);
			if(!StringUtils.isBlank(password))
				password = AuthUtil.MD5(password);
			agent.setPassword(password);
			agent.setNickname(nickname);
			agent.setContact(contact);
			agent.setCard_no(card_no);
			agent.setCard_name(card_name);
			agent.setCard_bank(card_bank);
			agent.setCity(city);
			agent.setProvince(province);
			agent.setCompany(company);
			agent.setDivide(divide);
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			agent.setW_name(w_name);
			agent.setUpdate_time(date);
			agent.setId(id);
			iagentDao.updateAgent(agent);
			iagent_AdminDao.updateAgent(agent);
			int admin_agent_id=iagent_AdminDao.getAdminByUsername(agent.getUsername());
			iagent_AdminDao.deleteUserBinding(admin_agent_id);
			imanage_RecordDao.insertManageRecord("超管后台-"+w_name, "更新代理信息", "t_agent", agent.getId(), IPUtil.getIp(request), date);
			jsonObject.put("code", 0);
			jsonObject.put("msg", "保存成功");
		}
		catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
			log.error("保存代理信息", e);
		}
		return jsonObject.toString();
	}

	public void updateAgentIsForbid(int id, int is_forbid, HttpServletRequest request) {
		try {
			String w_name = String.valueOf(request.getAttribute("w_name"));
			iagentDao.updateIsForBid(id, is_forbid, w_name);
		}catch (Exception e) {
			log.error("代理禁用错误", e);
		}
	}

	public int getAgentUsernameExist(String username, int id) {
		try
		{
			Integer agent_id = iagentDao.getAgentUsernameExist(username, id);
			if(agent_id!=null)
				return 1;
		}
		catch (Exception e) {
			log.error("获取代理后台用户名是否存在", e);
		}
		return 0;
	}
}

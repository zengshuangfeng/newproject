package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IrechargeDao;
import com.meisui.manage.dao.IspendingDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.PageUtil;

@Service
public class AgentPromoterService {

	private static Logger log = Logger.getLogger(AgentPromoterService.class.getClass());

	@Autowired
	private Iagent_PromoterDao iagent_PromoterDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IrechargeDao irechargeDao;
	@Autowired
	private IspendingDao ispendingDao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerDao;
	@Autowired
	private IagentDao iagentDao;
	@Autowired
	private IprofitDao profitDao;

	public String list(String begin_time, String end_time, int operate_center_id, int agentId, long uid, String remark, Model model, int page) {
		try {
			String b_time = "";
			String e_time = "";

			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			List<Agent_Promoter> agent_PromoterList = iagent_PromoterDao.getAgentPromoterList(operate_center_id, agentId, uid, remark, (page-1)*20, 20);
			for (Agent_Promoter agent_Promoter : agent_PromoterList) {
				User promoter_user = iuserDao.getUser(agent_Promoter.getUid());
				agent_Promoter.setNickname(promoter_user.getNickname());
				agent_Promoter.setInvite_code(promoter_user.getInvite_code());

				if("".equals(b_time) && "".equals(e_time)){
					agent_Promoter.setInvite_count(iuserDao.getInviteUserCountWithPromoter(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid()));
					Integer invite_recharge_sum = irechargeDao.getRechargeSumWithPromoterid(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid());
					agent_Promoter.setInvite_recharge_sum(invite_recharge_sum);
					Long invite_spending_sum = ispendingDao.getSpendingSumWithPromoterid(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid());
					agent_Promoter.setInvite_spending_sum(invite_spending_sum);
				}else{
					agent_Promoter.setInvite_count(iuserDao.getInviteUserCountWithPromoter2(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time));		
					Integer invite_recharge_sum = irechargeDao.getRechargeSumWithPromoterid2(operate_center_id,agentId, agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);		
					Long invite_spending_sum = ispendingDao.getSpendingSumWithPromoterid2(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);								
					agent_Promoter.setInvite_recharge_sum(invite_recharge_sum);	
					agent_Promoter.setInvite_spending_sum(invite_spending_sum);
				}	
			}
			int totalRecord = iagent_PromoterDao.getAgentPromoterCount(operate_center_id, agentId, uid, remark);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/promoterlist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("agent_PromoterList", agent_PromoterList);
			model.addAttribute("uid", uid);
			model.addAttribute("remark", remark);
			model.addAttribute("agentId", agentId);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("centerId", operate_center_id);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
		}catch (Exception e) {
			log.error("下属推广员列表错误", e);
		}
		return "agent/promoterlist";
	}

	public String allpromoterlist( String begin_time,String end_time, int agent_id, String f_uuid, String nickname, int operate_center_id, String invite_code,Model model, int page) {
		try {
			String b_time = "";
			String e_time = "";

			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Operate_Center> operateCenterList = ioperate_centerDao.getListAll();
			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentDao.getAgentListByCenterId(operate_center_id);
			}
			
			
			Long uid=0l;
			if(!StringUtils.isBlank(f_uuid))
			{
				uid = iuserDao.getUserIdWithF_uuid(f_uuid);
				if(uid==null)
					uid = 0l;
			}
			Long n_uid=0l;
			if(!StringUtils.isBlank(nickname))
			{
				n_uid=iuserDao.getUserIdByNickname(nickname);
				if(n_uid==null)
					n_uid = 0l;
			}
			
			List<Agent_Promoter> agent_PromoterList = iagent_PromoterDao.getAllAgentPromoterList(uid,n_uid,operate_center_id,agent_id,invite_code,(page-1)*20, 20);
			for (Agent_Promoter agent_Promoter : agent_PromoterList) {
				User promoter_user = iuserDao.getUser(agent_Promoter.getUid());
				agent_Promoter.setIs_virtual(promoter_user.getIs_virtual());
				agent_Promoter.setNickname(promoter_user.getNickname());
				agent_Promoter.setInvite_code(promoter_user.getInvite_code());
				Integer invite_recharge_sum = 0;

				Long invite_spending_sum=0L;
				if("".equals(b_time) && "".equals(e_time)){
					agent_Promoter.setInvite_count(iuserDao.getAllInviteUserCountWithPromoter( agent_Promoter.getId(), agent_Promoter.getUid()));
					invite_recharge_sum = irechargeDao.getAllRechargeSumWithPromoterid(agent_Promoter.getId(), agent_Promoter.getUid());
					invite_spending_sum = ispendingDao.getAllSpendingSumWithPromoterid( agent_Promoter.getId(), agent_Promoter.getUid());

				}else{
					agent_Promoter.setInvite_count(iuserDao.getAllInviteUserCountWithPromoter2( agent_Promoter.getOperate_center_id(),agent_Promoter.getAgent_id(), agent_Promoter.getUid(),b_time,e_time));				
					invite_spending_sum = ispendingDao.getAllSpendingSumWithPromoterid2( agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);
					invite_recharge_sum=irechargeDao.getAllRechargeSumWithPromoterid2(agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);													
				}

				agent_Promoter.setInvite_recharge_sum(invite_recharge_sum);				
				agent_Promoter.setInvite_spending_sum(invite_spending_sum);				
				agent_Promoter.setF_uuid(promoter_user.getF_uuid());
				Operate_Center operate_center=ioperate_centerDao.getOperateCenterById(agent_Promoter.getOperate_center_id());
				agent_Promoter.setOperate_center_name(operate_center.getName());
				Agent agent=iagentDao.getAgent(agent_Promoter.getAgent_id());
				agent_Promoter.setAgent_name(agent.getName());
			}
			int totalRecord = iagent_PromoterDao.getAllAgentPromoterCount(uid,n_uid,operate_center_id,agent_id,invite_code);
			int total_invite =0;
			if("".equals(b_time) && "".equals(e_time)){
				total_invite = iuserDao.totalInvite();
			}else{
				total_invite=iuserDao.totalInvite2(b_time,e_time);
			}


			long total_give = ispendingDao.totalSpending_virtual(b_time, e_time);
			long total_recharge = irechargeDao.totalRecharge(b_time, e_time);
			long spread_Total_Glamour = profitDao.getProfitSumByType2( 8, b_time, e_time);//推广所得总魅力值

			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/allpromoterlist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("agent_PromoterList", agent_PromoterList);
			model.addAttribute("activeUrl", "promoterlist");	
			model.addAttribute("total_invite", total_invite);
			model.addAttribute("total_give", total_give);
			model.addAttribute("total_recharge", total_recharge);
			model.addAttribute("spread_Total_Glamour", spread_Total_Glamour);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("operate_CenterList", operateCenterList);
			model.addAttribute("agentList", agentList);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("invite_code", invite_code);
		}catch (Exception e) {
			log.error("推广员列表错误", e);
		}
		return "agent/allpromoterlist";
	}


	public String getInviteList(String begin_time,String end_time,int centerId, int agent_id, int agent_promoter_id, long uid, int page,
			Model model, HttpServletRequest request) {
		try
		{
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			long agent_promoter_uid = 0;
			if(agent_promoter_id>0)//本身推广员也属于被自己邀请进来
			{
				agent_promoter_uid = iagent_PromoterDao.getUId(agent_promoter_id);
			}
			List<User> invite_UserList = iuserDao.getInviteUserList(centerId, agent_id, agent_promoter_id, uid, agent_promoter_uid,b_time,e_time, (page-1)*20, 20);
			for (User user : invite_UserList) {

				Date last_login_time = iuserDao.getUserLastLoginTime(user.getId());
				if (last_login_time != null)
					user.setLast_login_time(last_login_time);

				Integer recharge_sum = irechargeDao.getRechargeRmbSumWithUid(user.getId(),b_time,e_time);
				user.setSum_recharge(recharge_sum);
				Long spending_sum = ispendingDao.getSpendingSum2(user.getId(),b_time,e_time);
				user.setSum_give(spending_sum);
			}
			int totalRecord = iuserDao.getInviteUserCount2(centerId, agent_id, agent_promoter_id, uid, agent_promoter_uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/invite");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("invite_UserList", invite_UserList);
			model.addAttribute("uid", uid);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("agent_promoter_id", agent_promoter_id);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("centerId", centerId);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
		}
		catch (Exception e) {
			log.error("获取被邀请用户列表", e);
		}
		return "agent/promoterinvite";
	}


}

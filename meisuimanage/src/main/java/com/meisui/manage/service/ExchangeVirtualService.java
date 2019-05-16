package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.IexchangeVirtualDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Exchange_Virtual;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.PageUtil;

@Service
public class ExchangeVirtualService  extends CommonService {
	private static Logger log = Logger.getLogger(ExchangeVirtualService.class.getClass());
	@Autowired
	private IexchangeVirtualDao iexchangevirtualDao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IagentDao iagentDao;

	public String getExchangeVirtualList(int operate_center_id,int agent_id,long f_uuid, String nickname, Date start_time, Date end_time, int page,
			Model model, HttpServletRequest request) {
		try
		{
			
			List<Exchange_Virtual> virtuallist=iexchangevirtualDao.getExchangeVirtualList(operate_center_id,agent_id,f_uuid,nickname,start_time,end_time,(page-1)*20, 20);
			for(Exchange_Virtual list:virtuallist){
				Operate_Center operate=ioperate_centerDao.getOperateCenterById(list.getOperate_center_id());
				list.setOperate_center_name(operate.getName());
				Agent agent=iagentDao.getAgent(list.getAgent_id());
				list.setAgent_name(agent.getName());
				User user=iuserDao.getUserByF_uuid((long)list.getF_uuid());
				if(user!=null)
				list.setNickname(user.getNickname());
			}
			int totalRecord=iexchangevirtualDao.getExchangeVirtualCount(operate_center_id,agent_id,f_uuid,nickname,start_time,end_time);
			List<Operate_Center> operateCenterList = ioperate_centerDao.getListAll();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("exchangevirtual/list");
			model.addAttribute("virtuallist", virtuallist);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "exchangevirtual");
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("operateCenterList", operateCenterList);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
		}
		catch (Exception e) {
			log.error("魅力值兑换钻石记录列表", e);
		}
		return "exchangevirtual/list";
	
	}

}

package com.meisui.manage.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.meisui.manage.dao.IAnchor_GuardDao;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IspendingDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Anchor_Guard;
import com.meisui.manage.entity.Anchor_Guard_Change;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.Anchor_Guard_Manager;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Profit;
import com.meisui.manage.entity.Spending;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

@Service
public class Anchor_GuardService {
	private static Logger log = Logger.getLogger(Anchor_GuardService.class.getClass());
	@Autowired
	private IAnchor_GuardDao ianchor_guardDao;
	@Autowired
	private IuserDao iuserdao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Ianchor_Guard_ChangeDao ianchorguardchangeDao;
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Ianchor_Guard_ChangeDao ianchor_guard_changeDao;
	@Autowired
	private IspendingDao ispendingDao;
	
	public String getGuardList(String anchor_f_uuid, int uid, int type, int page, Model model) {
		try {
			List<Anchor_Guard> Anchor_GuardList = ianchor_guardDao.getGuardList(anchor_f_uuid,uid,type,(page-1)*20, 20);
			int totalRecord = ianchor_guardDao.getGuardListCount(anchor_f_uuid,uid,type);
			for(Anchor_Guard anchor_guard:Anchor_GuardList){
				
				User user=iuserdao.getUserByF_uuid(Long.parseLong(anchor_guard.getAnchor_f_uuid()));
				anchor_guard.setAnchor_nickname(user.getNickname());//主播昵称
				User us=iuserdao.getUser((long)anchor_guard.getUid());
				anchor_guard.setNickname(us.getNickname());//守护者昵称
			}
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorguard/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("Anchor_GuardList", Anchor_GuardList);
			model.addAttribute("activeUrl", "anchorguard");
			model.addAttribute("share_url", PropertyUtil.getValue("share_url"));
			model.addAttribute("anchor_f_uuid", anchor_f_uuid);
			model.addAttribute("uid", uid);
			model.addAttribute("type", type);
		} catch (Exception e) {
			log.error("主播守护列表", e);
		}
		return "anchorguard/list";
	}

	public String getGuardAdd(Model model) {
		List<Anchor_Guard_Change> anchorlist=ianchor_guardDao.getGuardChange();
		model.addAttribute("activeUrl", "anchorguard");
		model.addAttribute("anchorlist", anchorlist);
		return "anchorguard/add";
	}

	public String getAnchorFuuidExist(String anchor_f_uuid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			User useranchor = iuserdao.getIsAnchorByFuuid(anchor_f_uuid);// 通过f_uuid过滤不是主播的用户
			if (useranchor == null)
				jsonObject.put("code", -1);// 该用户不是主播
		} catch (Exception e) {
			log.error("主播是否存在", e);
		}
		return jsonObject.toString();
	}

	public String getUidExist(Integer uid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			User useranchor = iuserdao.getUserIsexist(uid);//查询该用户是否存在 
			if (useranchor == null)
				jsonObject.put("code", -1);// 该用户不存在
		} catch (Exception e) {
			log.error("用户是否存在", e);
		}
		return jsonObject.toString();
	}

	public String saveAnchorGuard(String anchor_f_uuid, int uid, int change_id, int need_pay, String w_name,
			HttpServletRequest request) {
		try{	
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));		
			Anchor_Guard_Change2 guard_change=ianchorguardchangeDao.getAnchorGuardNewByid(change_id);//获取守护套餐数据
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date date=new Date();
			
			Anchor_Guard anchor_guard=new Anchor_Guard();
			anchor_guard.setAnchor_f_uuid(anchor_f_uuid);
			anchor_guard.setUid(uid);
			anchor_guard.setType(guard_change.getType());
			
			Anchor_Guard_Manager guard_manager=new Anchor_Guard_Manager();
			guard_manager.setChange_id(change_id);
			guard_manager.setAnchor_f_uuid(anchor_f_uuid);
			guard_manager.setUid(uid);
			guard_manager.setW_name(w_name);
			guard_manager.setCreate_time(date);
			guard_manager.setNeed_pay(need_pay);
			
			
			if(need_pay>0){//当需要支付钻石时			
				User user=iuserdao.getUser((long)uid);
				if((user.getBalance_virtual()-guard_change.getChange_virtual())<0){
					return "{\"code\":-1,\"msg\":\"余额不足\"}";
				}
			}
			Anchor_Guard  guardlist=ianchor_guardDao.getGuardByuid(anchor_f_uuid,uid);
			int day=0;
			if(guard_change.getType()==0){//包月套餐
				day=30;
			}else if(guard_change.getType()==1){//包季套餐
				day=90;
			}else if(guard_change.getType()==2){//包年套餐
				day=365;
			}
			
			String start_time = df.format(date);
			String end_time = df.format(new Date(date.getTime() + day * 24l * 60l * 60l * 1000l));
			String s_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				s_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			
			if(guardlist==null){//若主播房间号和守护UID查询为空，则直接插入该条数据		
				anchor_guard.setStart_time(format.parse(s_time));
				anchor_guard.setEnd_time(format.parse(e_time));
				result=ianchor_guardDao.insertAnchorGuard(anchor_guard);
				if(result>0){
					guard_manager.setStart_time(format.parse(s_time));
					guard_manager.setEnd_time(format.parse(e_time));
					ianchor_guardDao.inserAnchorGuardManager(guard_manager);
				}
			}else{//若不为空，则更新时间
				
				if(guardlist.getEnd_time().getTime()<date.getTime()){//若结束时间小于当前时间，表示该守护过期。则将开始结束时间覆盖
					anchor_guard.setStart_time(format.parse(s_time));
					anchor_guard.setEnd_time(format.parse(e_time));
					anchor_guard.setId(guardlist.getId());
					result=ianchor_guardDao.updateAnchorGuard(anchor_guard);				
					if(result>0){
						guard_manager.setStart_time(format.parse(s_time));
						guard_manager.setEnd_time(format.parse(e_time));
						ianchor_guardDao.inserAnchorGuardManager(guard_manager);
					}
				}else{
					anchor_guard.setStart_time(guardlist.getStart_time());
					String endtime = df.format(new Date(guardlist.getEnd_time().getTime() + day * 24l * 60l * 60l * 1000l));					
					anchor_guard.setEnd_time(format.parse(endtime+ " 23:59:59"));
					anchor_guard.setId(guardlist.getId());			
					result=ianchor_guardDao.updateAnchorGuard(anchor_guard);	
					if(result>0){
						String entime = df.format(new Date(guardlist.getEnd_time().getTime() + 1l * 1000l));
						guard_manager.setStart_time(format.parse(entime+ " 00:00:00"));
						guard_manager.setEnd_time(format.parse(endtime+ " 23:59:59"));
						ianchor_guardDao.inserAnchorGuardManager(guard_manager);
					}
				}	
			}
			
			if(result > 0){
				if(need_pay>0){
				iuserdao.updatebalance_virtual(-guard_change.getChange_virtual(), (long)uid);//t_user表，余额减去套餐的价格
				}			
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("添加守护", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String getChangeId(Integer uid, String anchor_f_uuid, Integer change_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			Date date=new Date();
			if(change_id>0){
			Anchor_Guard_Change guard_change=ianchorguardchangeDao.getAnchorGuardByid(change_id);//获取守护套餐数据
			Anchor_Guard anchorguard=ianchor_guardDao.getGuardWithuid(uid,anchor_f_uuid);
			if(uid==0 || "".equals(anchor_f_uuid)){
				jsonObject.put("code", -2);//若还未填写主播房间号或守护者UID			
			}else{	
			
			if(anchorguard!=null){//如果守护表存在该记录
				if(anchorguard.getEnd_time().getTime()<date.getTime()){//表示该套餐已经过期
					jsonObject.put("code", 0);//可以任意购买任何类型守护套餐
				}else{
				if(guard_change.getType()-anchorguard.getType()>=0){//现在购买的套餐只能大于等于现在的套餐，不能往下
					jsonObject.put("code", 0);
				}else{
					jsonObject.put("code", -1);//购买失败
					}								
				}
				
			}else{//如果不存在该记录，则可以购买任意守护套餐
				jsonObject.put("code", 0);
				}	
			}
		}else{
			jsonObject.put("code", -1);
		}
		} catch (Exception e) {
			log.error("选择守护套餐", e);
		}
		return jsonObject.toString();
	}

	public String getGuardRecord(long send_uid, long f_uuid, long anchor_f_uuid, int operate_center_id, String nickname,
			int agent_id,int type, Date start_time, Date end_time, int page, Model model) {
		try {
			String e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			
			List<Spending> guardchangeList = ispendingDao.getGuardRecord(send_uid,f_uuid,anchor_f_uuid,operate_center_id,agent_id,type,nickname,start_time,end_time,(page-1)*20, 20);
			for(Spending spending:guardchangeList){				
				Anchor_Guard_Change2 change2=ianchor_guard_changeDao.getAnchorGuardNewByid(spending.getGift_id());
				spending.setGift_name(change2.getName());//守护名称
				User user = iuserdao.getUser(spending.getSpending_uid());//收礼方
				spending.setRev_f_uuid(Long.parseLong(user.getF_uuid()));//主播房间号
				spending.setSpending_nickname(user.getNickname());//主播昵称
				User user2 = iuserdao.getUser(spending.getUid());//送礼方
				spending.setSend_f_uuid(Long.parseLong(user2.getF_uuid()));//赠送人房间号
				spending.setNickname(user2.getNickname());//赠送人昵称
				Operate_Center operate_center=ioperate_centerdao.getOperateCenterById(spending.getOperate_center_id());
				if(operate_center !=null)
				spending.setOperate_center_name(operate_center.getName());//所属运营中心
				
				Agent agent=iagentdao.getAgent(spending.getAgent_id());
				if(agent !=null)
				spending.setAgent_name(agent.getName());//所属代理
			}
			
			
			int totalRecord = ispendingDao.getGuardRecordCount(send_uid,f_uuid,anchor_f_uuid,operate_center_id,agent_id,type,nickname,start_time,end_time);
			
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentdao.getAgentListByCenterId(operate_center_id);
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorguard/guardrecord");
			model.addAttribute("guardchangeList", guardchangeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "guardrecord");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("type", type);
			model.addAttribute("agentList", agentList);
			model.addAttribute("operate_CenterList", operateCenterList);
			model.addAttribute("send_uid", send_uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("anchor_f_uuid", anchor_f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			
		} catch (Exception e) {
			log.error("守护钻石购买记录列表", e);
		}
		return "guardrecord/list";
	}


}

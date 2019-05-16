package com.meisui.manage.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.forman.foundation.library.StringUtil;
import com.meisui.manage.dao.IAnchor_GuardDao;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_Guard_RechargeDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.IapplyDao;
import com.meisui.manage.dao.IattentionDao;
import com.meisui.manage.dao.Ibalance_VirtualrecordDao;
import com.meisui.manage.dao.Igift_BoxDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.IkeyDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IrechargeDao;
import com.meisui.manage.dao.Irecharge_ChannelDao;
import com.meisui.manage.dao.IspendingDao;
import com.meisui.manage.dao.Itotal_FlowingDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_PushDao;
import com.meisui.manage.dao.Iuser_StartDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Anchor_Guard;
import com.meisui.manage.entity.Anchor_Guard_Recharge;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Attention;
import com.meisui.manage.entity.Gift_Box;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Recharge_Channel;
import com.meisui.manage.entity.RegisterLoginStatistics;
import com.meisui.manage.entity.Spending;
import com.meisui.manage.entity.Total_Flowing;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Device;
import com.meisui.manage.entity.User_Extra;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.entity.User_Push;
import com.meisui.manage.entity.User_Real;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

/**
 * <p>文件名称：UserService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Service
public class UserService extends HessianService {
	private static Logger log = Logger.getLogger(UserService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Iuser_StartDao iuser_StartDao;
	@Autowired
	private IrechargeDao irechargeDao;
	@Autowired
	private IattentionDao iattentionDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Itotal_FlowingDao itotal_FlowingDao;
	@Autowired
	private IkeyDao ikeyDao;
	@Autowired
	private Iuser_PushDao iuser_PushDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private Ibalance_VirtualrecordDao ibalance_VirtualrecordDao;
	@Autowired
	private IspendingDao ispendingDao;
	@Autowired
	private IapplyDao iapplyDao;
	@Autowired
	private Irecharge_ChannelDao irecharge_ChannelDao;	
	@Autowired
	private Iagent_PromoterDao iagent_promoterdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerdao;
	@Autowired
	private IspendingDao ispendingdao;
	@Autowired
	private Igift_InfoDao igift_infodao;
	@Autowired
	private Igift_BoxDao igift_boxdao;
	@Autowired
	private Ianchor_Guard_RechargeDao ianchor_guard_rechargedao;
	@Autowired
	private IAnchor_GuardDao ianchor_guardDao;
	/**
	 * 
	 * <p>功能描述：用户列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午5:26:55</p>
	 *
	 * @param uid 用户id
	 * @param f_uuid 用户房间号
	 * @param nickname 昵称
	 * @param remark 备注
	 * @param s_time 注册开始时间
	 * @param e_time 注册结束时间
	 * @param page 页码
	 * @param model
	 * @param uid 
	 * @return
	 */
	public String getUserList(long uid, long f_uuid, String nickname, String remark, Date s_time, Date e_time, String level, int sort, String tel,  int page, Model model) {
		try {
			String e_timeString = e_time != null ? AuthUtil.formatDateToString(e_time, "yyyy-MM-dd") : "";
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			Long t_uid = 0L;//根据tel查到到的uid
			if(StringUtils.isNotBlank(tel)) {
				t_uid = iuserDao.getUidByTelOnUserRealTable(tel);
				if(t_uid == null || t_uid == 0) {
					t_uid = iuserDao.getUidByTelOnUserPhoneTable(tel);
					if(t_uid==null)
					{
						t_uid = -1l;
					}
				}
			}
			
			int start_level = 0, end_level = 0;
			if (!StringUtils.isBlank(level)) {
				if(level.contains("-"))
				{
					String[] levelStrings = level.split("-");
					start_level = Integer.parseInt(levelStrings[0]);
					end_level = Integer.parseInt(levelStrings[1]);
				}
				else {
					start_level = Integer.parseInt(level);
					end_level = Integer.parseInt(level);
				}
			}
			
			
			List<User> userList = new ArrayList<>();
			if(t_uid>=0)
				userList = iuserDao.getUserList(f_uuid, nickname, remark ,s_time, e_time, start_level, end_level,sort, t_uid, uid, (page - 1) * 20, 20);
			for (User user : userList) {
				int operate_center_id=user.getOperate_center_id();
				Operate_Center operate_center=ioperate_centerdao.getoperateName(operate_center_id);
				if(operate_center!=null)
				{
					String operateName=operate_center.getName();
					user.setOperateName(operateName);
				}
				/*User_Info user_Info = iuserDao.getUserInfo(user.getId());
				if (user_Info != null) {
					user.setFollower_count(user_Info.getFollower_count());
					user.setTotal_anchor_virtual(user_Info.getTotal_anchor_virtual());
					user.setTotal_video_virtual(user_Info.getTotal_video_virtual());
				}
				//				user.setVideo_count(iuser_VideoDao.getUserVideoCount(user.getId()));
				if(user.getIs_forbid()==1&&user.getForbid_hour()>0)
				{
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(user.getForbid_start_time());
					calendar.add(Calendar.HOUR, user.getForbid_hour());
					if(calendar.getTime().before(new Date()))
						user.setIs_forbid(0);
				}*/
				Long total_spending = ispendingDao.getSpendingSumAllWithUid(user.getId());
				if(total_spending==null)
					total_spending = 0L;
				user.setTotal_spending(total_spending);

				String phone = iuserDao.getTel(user.getId());
				if(StringUtils.isBlank(phone)) {
					phone = iuserDao.getTelByUserRealTable(user.getId());
					if(StringUtils.isBlank(phone) && user.getIs_anchor() == 1) {
						phone = iapplyDao.getTel(user.getId());
					}
				}
				user.setTel(phone);
				String name = iuserDao.getRealName(user.getId());
				if(StringUtils.isBlank(name) && user.getIs_anchor() == 1) {
					user.setRealname(iapplyDao.getRealName(user.getId()));
				}
				user.setRealname(name);
			}
			int totalRecord = 0;
			if(t_uid>=0)
				totalRecord = iuserDao.getUserCount(f_uuid, nickname, remark ,s_time, e_time, start_level, end_level,sort, t_uid, uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("userinfo/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userList", userList);	
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);		
			model.addAttribute("s_time", s_time != null ? AuthUtil.formatDateToString(s_time, "yyyy-MM-dd") : "");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("tel", tel);
			model.addAttribute("activeUrl", "userinfo");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("uid", uid);
			model.addAttribute("level", level);
			model.addAttribute("sort", sort);
			model.addAttribute("remark", remark);
		} catch (Exception e) {
			log.error("用户列表", e);
		}
		return "userinfo/list";
	}

	/**
	 * 
	 * <p>功能描述：用户详情</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:21:09</p>
	 *
	 * @param id 用户uid
	 * @param page 页码
	 * @param type 类型
	 * @param select 当type=5时 0发布的视频 1回复的视频
	 * @param model
	 * @return
	 */
	public String getUserDetail(long id, int page, int type, int select, Model model) {
		try {
			User user = iuserDao.getUser(id);
			int anchor=user.getIs_anchor();

			Integer total_balance_virtual = irechargeDao.getRechargeSumWithUid(id);
			if (total_balance_virtual == null)
				total_balance_virtual = 0;
			user.setTotal_balance_virtual(total_balance_virtual);
			Date last_use_time = iuser_StartDao.getLastUseTimeWithUid(id);
			if (last_use_time != null)
				user.setLast_use_time(last_use_time);
			Integer login_type = iuserDao.getUserLoginType(id);
			if (login_type == null)
				login_type = 4;
			user.setLogin_type(login_type);
			Date last_login_time = iuserDao.getUserLastLoginTime(id);
			if (last_login_time != null)
				user.setLast_login_time(last_login_time);
			User_Info user_Info = iuserDao.getUserInfo(id);

			User_Real user_Real = iapplyDao.getUserReal(id);
			if (user_Real == null)
				user_Real = new User_Real();

			String phone = iuserDao.getTel(user.getId());
			if(StringUtils.isBlank(phone)) {
				phone = iuserDao.getTelByUserRealTable(user.getId());
				if(StringUtils.isBlank(phone) && user.getIs_anchor() == 1) {
					phone = iapplyDao.getTel(user.getId());
				}
			}
			user.setTel(phone);
			String realname = iuserDao.getRealName(user.getId());
			if(StringUtils.isBlank(realname) && user.getIs_anchor() == 1) {
				user.setRealname(iapplyDao.getRealName(user.getId()));
			}
			user.setRealname(realname);

			List<String> t1List = iuserDao.getUserT1ByUid(id);
			if (t1List.size() == 0) {
				t1List = iuserDao.getUserInfoT1ByUid(id);
			}
			int totalRecord = 0;
			if (type == -1)
				type = 0;
			if (type == 0) {
				if (t1List.size() > 0) {
					List<User_Extra> extraList = iuserDao.getExtraList(t1List, 0, 1);
					model.addAttribute("extraList", extraList);
					totalRecord = extraList.size();
				}
			} else if (type == 1) {
				if (t1List.size() > 0) {
					List<User_Device> user_DeviceList = iuserDao.getDeviceList(t1List, (page - 1) * 20, 20);
					IPUtil ipUtil = IPUtil.getInstance();
					for (User_Device user_Device : user_DeviceList) {
						if (user_Device.getIp() > 0) {
							user_Device.setIp_string(AuthUtil.longToIP(user_Device.getIp()));
							if (!StringUtils.isBlank(user_Device.getIp_string())) {
								user_Device.setIp_address("(" + ipUtil.getAddress(user_Device.getIp_string()) + ")");
							}
						}
					}
					model.addAttribute("deviceList", user_DeviceList);
					totalRecord = iuserDao.getDeviceCount(t1List);
				}
			} else if (type == 2) {
				List<Attention> attentionList = iattentionDao.getAttentionListWithUid(id, (page - 1) * 20, 20);
				model.addAttribute("attentionList", attentionList);
				totalRecord = iattentionDao.getAttentionCountWithUid(id);
			} else if (type == 3) {
				List<Total_Flowing> total_FLowingList = itotal_FlowingDao.getTotalFlowingList(id, (page - 1) * 20, 20);
				model.addAttribute("totalFLowingList", total_FLowingList);
				totalRecord = itotal_FlowingDao.getTotalFlowingCount(id);
			} else if (type == 4) {

				List<Spending>  spendinglist=ispendingdao.getSpendingBysid(id,(page-1)*20,20);
				
				for(Spending sp:spendinglist){
					long seed_uid=sp.getUid();
					int gift_id=sp.getGift_id();
					Gift_Info gift_info=igift_infodao.getGiftInfo(gift_id);
					int giftprize=gift_info.getGift_virtual();//礼物价值
					String name=gift_info.getGift_name();
					int promoter_divide=gift_info.getPromoter_divide();
					User user2=iuserDao.getUser(seed_uid);
					String f_uuid=user2.getF_uuid();//房间号
					int level=user2.getLevel();//送礼方等级
					sp.setGiftprize(giftprize);
					sp.setGift_name(name);
					sp.setF_uuid(f_uuid);
					sp.setLevel(level);
					sp.setPromoter_divide(promoter_divide);

					User user3=iuserDao.getUserByUid(seed_uid);
					String nickname=user3.getNickname();
					sp.setNickname(nickname);
				}
				model.addAttribute("spendinglist", spendinglist);
				totalRecord=ispendingdao.getTotalSpendingCount(id);
			} else if (type == 5) {
				int agent_promoter_id=iagent_promoterdao.getProIdByUid(id);
					List<Spending>	spendinglist=ispendingdao.getSpendingBysid2(agent_promoter_id,(page-1)*20,20);			 				
					for(Spending sp:spendinglist){
						long seed_uid=sp.getUid();
						int gift_id=sp.getGift_id();
						Gift_Info gift_info=igift_infodao.getGiftInfo(gift_id);
						int giftprize=gift_info.getGift_virtual();//礼物价值
						String name=gift_info.getGift_name();
						int anchor_divide=gift_info.getAnchor_divide();			

						User user2=iuserDao.getUser(seed_uid);
						String f_uuid=user2.getF_uuid();//房间号
						int level=user2.getLevel();//送礼方等级
						sp.setGiftprize(giftprize);
						sp.setGift_name(name);
						sp.setF_uuid(f_uuid);
						sp.setLevel(level);
						sp.setAnchor_divide(anchor_divide);

						User user3=iuserDao.getUserByUid(seed_uid);
						String nickname=user3.getNickname();
						sp.setNickname(nickname);
					}				
				model.addAttribute("spendinglist", spendinglist);
				totalRecord=ispendingdao.getTotalSpendingCount2(agent_promoter_id);
			}else if(type == 6){
				List<Gift_Box> giftboxlist=igift_boxdao.getGiftBoxList(id, (page - 1) * 20, 20);
				totalRecord = igift_boxdao.getGiftBoxListCount(id);
				for(Gift_Box giftbox:giftboxlist){
					int giftid=giftbox.getGift_id();
					Gift_Info gift_info=igift_infodao.getGiftInfo(giftid);
					String gift_name=gift_info.getGift_name();
					giftbox.setGift_name(gift_name);
					giftbox.setType(type);
				}
				model.addAttribute("giftboxlist", giftboxlist);
				
			}else if(type==7){
				Date date=new Date();
				List<Anchor_Guard_Recharge> guardlist=ianchor_guard_rechargedao.getGuardRecharge(id, (page - 1) * 20, 20);
				for(Anchor_Guard_Recharge guardrecharge :guardlist){				
				User userguard=iuserDao.getUserByF_uuid((long)guardrecharge.getAnchor_f_uuid());
				guardrecharge.setAnchor_uid((int)userguard.getId());		
				guardrecharge.setAnchor_name(userguard.getNickname());
				if( date.getTime()<guardrecharge.getEnd_time().getTime()){//当前时间在开始时间和结束时间之间
					guardrecharge.setStatus(1);//守护状态正常
				}else if(date.getTime()>guardrecharge.getEnd_time().getTime()){//当前时间都大于结束时间
					guardrecharge.setStatus(0);//过期
					}				
				}			
				model.addAttribute("guardlist", guardlist);
			
			}else if(type==8){
				User userguard=iuserDao.getUser(id);//获取到主播的房间号	
				List<Anchor_Guard_Recharge> guardlist=ianchor_guard_rechargedao.getAnchorGuardRecharge(userguard.getF_uuid(), (page - 1) * 20, 20);
				for(Anchor_Guard_Recharge guardrecharge :guardlist){
					User users=iuserDao.getUser((long)guardrecharge.getUid());
					guardrecharge.setNickname(users.getNickname());//守护者昵称
				}
				model.addAttribute("guardlist", guardlist);
			
			}else if(type==9){
				Date date=new Date();
				List<Spending> guardlist=ispendingDao.getGuardRecharge(id, (page - 1) * 20, 20);
				for(Spending guardrecharge :guardlist){				
					guardrecharge.setAnchor_uid((int)guardrecharge.getSpending_uid());//主播uid
					User user2=iuserDao.getUser(guardrecharge.getSpending_uid());
					guardrecharge.setAnchor_name(user2.getNickname());//主播昵称
					
					Anchor_Guard anchor_guard=ianchor_guardDao.getGuardByuid(user2.getF_uuid().toString(), (int)id);
					if(anchor_guard !=null){
						if( date.getTime()<anchor_guard.getEnd_time().getTime()){//当前时间在开始时间和结束时间之间
							guardrecharge.setStatus(1);//守护状态正常
						}else if(date.getTime()>anchor_guard.getEnd_time().getTime()){//当前时间都大于结束时间
							guardrecharge.setStatus(0);//过期
						}
						guardrecharge.setStart_time(anchor_guard.getStart_time());						
						guardrecharge.setEnd_time(anchor_guard.getEnd_time());
					}
													
				}
				totalRecord=ispendingDao.getGuardRechargeCount(id);
				model.addAttribute("guardlist", guardlist);
				
			}else if(type==10){			
				List<Spending> guardlist=ispendingDao.getGuardRecharge2(id, (page - 1) * 20, 20);
				for(Spending guardrecharge :guardlist){
					User users=iuserDao.getUser((long)guardrecharge.getSpending_uid());
					Anchor_Guard anchor_guard=ianchor_guardDao.getGuardByuid(users.getF_uuid().toString(), (int)guardrecharge.getUid());
					guardrecharge.setNickname(users.getNickname());//守护者昵称
					if(anchor_guard!=null){											
						guardrecharge.setStart_time(anchor_guard.getStart_time());
						guardrecharge.setEnd_time(anchor_guard.getEnd_time());
					}
					
				}			
				totalRecord=ispendingDao.getGuardRechargeCount2(id);
				model.addAttribute("guardlist", guardlist);
			}

			Long currentTime = new Date().getTime();

			int vip_remaining = -1;

			if (user.getVip_time()==null||user.getVip_time().getTime() < currentTime) {
				vip_remaining = -1;
			}else {
				vip_remaining = (int) ((user.getVip_time().getTime() - currentTime)/1000/60/60/24);
			}	
			
			String operateName="";
			if(user.getOperate_center_id()>0){
				Operate_Center iperate_center=ioperate_centerdao.getoperateName(user.getOperate_center_id());
				if(iperate_center !=null)
					operateName=iperate_center.getName();
			}		
			
			String agentName="";
			if(user.getAgent_id()>0){
				Agent agent=iagentdao.getAgentNameByagentid(user.getAgent_id());
				if(agent!=null)
					agentName=agent.getName();
			}
			
			String proName="";
			long f_uuid=0;
			int result=0;
			if(user.getAgent_promoter_id()>0){
				Agent_Promoter agent_promoter=iagent_promoterdao.getProNameByid(user.getAgent_promoter_id());
				if(agent_promoter!=null){
					long apuid=agent_promoter.getUid();
					proName=iuserDao.getUserNickNameByapuid(apuid);
					f_uuid=iuserDao.getUserFuuidByuid(apuid);
					if(apuid==id){
						result=1;//为1时，代表他是推广员
					}
				}					
			}

			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("userinfo/detail");
			model.addAttribute("vip_remaining", vip_remaining);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("tuser", user);
			model.addAttribute("user_Info", user_Info);
			model.addAttribute("userReal", user_Real);
			model.addAttribute("type", type);
			model.addAttribute("select", select);
			model.addAttribute("phone", iuserDao.getUserPhone(id));
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "userinfo");
			model.addAttribute("operateName", operateName);
			model.addAttribute("agentName", agentName);
			model.addAttribute("proName", proName);
			model.addAttribute("F_uuid", f_uuid);
			model.addAttribute("anchor", anchor);
			model.addAttribute("result", result);
		} catch (Exception e) {
			log.error("用户详情", e);
		}
		return "userinfo/detail";
	}

	/**
	 * 
	 * <p>功能描述：修改用户备注</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午8:52:32</p>
	 *
	 * @param id 用户id
	 * @param remark 备注
	 * @return
	 */
	public int saveUserRemak(long id, String remark, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.saveRemak(id, remark);
			if (result > 0)
				imanage_RecordDao.insertManageRecord(w_name, "修改用户备注,id：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			log.error("修改用户备注", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：修改用户是否为测试账号</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月23日 下午5:42:39</p>
	 *
	 * @param id 用户id
	 * @param istest 是否为测试账号 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserIsTest(long id, int istest, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			result = iuserDao.updateUserIsTest(istest, id);
			if (result > 0) {
				w_name = String.valueOf(request.getAttribute("w_name"));
				String token = iuserDao.getUserInfoExtraToken(id);
				RedisUtil.SetHsetJedis(0, token, "istest", String.valueOf(istest));
				imanage_RecordDao.insertManageRecord(w_name, "修改用户是否为测试账号,id：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("修改用户是否为测试账号", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：修改用户钻石数</p>
	 * <p>创建人：sky</p>
	 * <p>创建日期：2017年5月26日 下午8:16:58</p>
	 *
	 * @param id
	 * @param balance_virtual
	 * @param remark
	 * @param diamondsa
	 * @param diamondsb
	 * @param family
	 * @param w_name
	 * @param request
	 * @return
	 */
	public int updatebalance_virtual(long id, int balance_virtual, int money, String remark, String diamondsa, String diamondsb, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updatebalance_virtual(balance_virtual, id);

			if (result > 0) {
				/*
				long score = iuserDao.getUserScore(id);
				score = score + money*5;
				iuserDao.updateUserScore(score, id);
				String token = iuserDao.getUserInfoExtraToken(id);
				if (!StringUtils.isBlank(token)) {
					Integer level = iuserDao.getUserCanLevel(score);
					if (level != null) {
						String current_level = RedisUtil.Gethget(0, token, "level");
						iuserDao.updateUserLevel(level, id);
						RedisUtil.SetHsetJedis(0, token, "level", String.valueOf(level));
						try {
							if (!current_level.equals(String.valueOf(level))) {
								updatelevel(token);
								HessianProxyFactory factory = new HessianProxyFactory();
								factory.setOverloadEnabled(true);
								IWebCommunication iWebCommunication = (IWebCommunication) factory.create(IWebCommunication.class, PropertyUtil.getValue("im_url"));
								iWebCommunication.updatelevel(token);
							}
						} catch (Exception e) {
							Log4jHandel.myerror("等级改变，通知im", e);
						}
					}
					RedisUtil.SetHsetJedis(0, token, "score", String.valueOf(score));
				}
				 */
				if (diamondsa.equals("zftype1")) {

					if (diamondsb.equals("zhifu1")) {
						diamondsb = "0";
					} else {
						diamondsb = "1";
					}
				}
				if (diamondsa.equals("zftype2") || diamondsa.equals("zftype3")) {
					diamondsb = "-1";
				}
				User _user = iuserDao.getUser(id);
				ibalance_VirtualrecordDao.insertBalance_Virtualrecord(id, balance_virtual, money, Integer.valueOf(diamondsb), diamondsa, remark, w_name, AuthUtil.formatDateToString(new Date()), _user.getNickname());
				imanage_RecordDao.insertManageRecord(w_name, "奖励钻石数("+balance_virtual+"),uid：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("修改用户钻石数", e);
		}

		return result;
	}

	public String editdiamonds(long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("result", 0);
		List<Anchor_Union> anchor_UnionList = ianchor_UnionDao.getAnchorUnionList(null, null, 0, 1000);
		model.addAttribute("familyList", anchor_UnionList);
		return "userinfo/edit";
	}

	/**
	 * 
	 * <p>功能描述：修改用户头像锁定</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param head_lock 是否锁定 1是 0否
	 * @param w_name
	 * @param request
	 * @return
	 */
	public int updateUserHeadLock(long id, int head_lock, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserHeadLock(id, head_lock);
			if (result > 0) {
				String token = iuserDao.getUserInfoExtraToken(id);
				Date date = new Date();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR, 1);
				User_Push _user_push = new User_Push();
				if (head_lock == 1) {
					RedisUtil.SetHsetJedis(0, token, "head", "default.png");
					_user_push.setTitle("您的头像含有不良信息，已被设为违规头像!");
				} else {
					_user_push.setTitle("您的头像已解锁，您可以在“我-基本资料”中修改您的头像。");
				}
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(0);
				_user_push.setHref("");
				_user_push.setUrl("");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				imanage_RecordDao.insertManageRecord(w_name, "修改用户头像锁定,id：" + id, "t_user", 0, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("修改用户头像锁定", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：修改用户昵称锁定</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param nickname_lock 是否锁定 1是 0否
	 * @param w_name
	 * @param request
	 * @return
	 */
	public int updateUserNicknameLock(long id, int nickname_lock, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserNicknameLock(id, nickname_lock);
			if (result > 0) {
				String token = iuserDao.getUserInfoExtraToken(id);
				Date date = new Date();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR, 1);
				User_Push _user_push = new User_Push();
				if (nickname_lock == 1) {
					RedisUtil.SetHsetJedis(0, token, "nickname", "违规昵称");
					_user_push.setTitle("您的昵称含有不良信息，已被设为违规昵称!");
				} else {
					_user_push.setTitle("您的昵称已解锁，您可以在“我-基本资料”中修改您的昵称。");
				}
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(0);
				_user_push.setHref("");
				_user_push.setUrl("");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				imanage_RecordDao.insertManageRecord(w_name, "修改用户昵称锁定,id：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("修改用户昵称锁定", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：修改用户VIP</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年9月20日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param vip_days 开通VIP天数
	 * @return
	 */	
	public int updateUserVIP(Long id,int vip_days,String w_name,HttpServletRequest request){

		int result = 0;
		try {
			User user = iuserDao.getUser(id);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
			SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   

			Date date = new Date();
			String future = "";
			//判断是否还在VIP期间内
			if (user.getVip_time()!=null&&date.getTime()<user.getVip_time().getTime()) {//还在VIP期间内
				future = df.format(new Date(user.getVip_time().getTime() + vip_days * 24l * 60l * 60l * 1000l)) + " 23:59:59";
			}else {//不在VIP期间内
				future = df.format(new Date(date.getTime() + (vip_days-1) * 24l * 60l * 60l * 1000l)) + " 23:59:59";
			}
			date = df1.parse(future);
			result = iuserDao.updateUserVIP(id, date);	
			w_name = String.valueOf(request.getAttribute("w_name"));

			imanage_RecordDao.insertManageRecord(w_name, "修改用户VIP天数,id："+id, "t_user", 1, IPUtil.getIp(request), new Date());

		} catch (Exception e) {
			log.error("修改用户VIP失败", e);
		}	
		return result;
	}
	/**
	 * 
	 * <p>功能描述：取消用户VIP</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年9月21日 下午16:11:12</p>
	 *
	 * @param id 用户uid
	 * @return
	 */			
	public int cancelUserVIP(Long id,String w_name,HttpServletRequest request){
		int result = 0;
		try {
			SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   

			String defaultVipTime = "1970-01-01 00:00:00";

			Date date = df1.parse(defaultVipTime);

			result = iuserDao.updateUserVIP(id, date);	

			w_name = String.valueOf(request.getAttribute("w_name"));

			imanage_RecordDao.insertManageRecord(w_name, "取消用户VIP,id："+id, "t_user", 1, IPUtil.getIp(request), new Date());
		} catch (Exception e) {
			log.error("取消用户VIP失败", e);
		}	
		return result;		
	}
	/**
	 * 
	 * <p>功能描述：奖励用户钥匙数</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午4:33:35</p>
	 *
	 * @param id 用户uid
	 * @param add 增加数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int rewardUserKeyCount(long id, int add, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserKeyCount(id, add);
			if (result > 0) {
				Date date = new Date();
				ikeyDao.insertKey(id, 2, add, date, String.format("操作人：%s", w_name));
				String token = iuserDao.getUserInfoExtraToken(id);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR, 1);
				User_Push _user_push = new User_Push();
				_user_push.setTitle(String.format("您获得了%s把钥匙奖励！请注意查收~", add));
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(1);
				_user_push.setHref("");
				_user_push.setUrl("fm://shop");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
				imanage_RecordDao.insertManageRecord(w_name, "修改用户钥匙数,id：" + id, "t_user", 0, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("修改用户钥匙数", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：封号/解封用户</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 用户id
	 * @param is_blocked 是否封号 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserIsBlocked(long id, int is_blocked, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iuserDao.updateUserIsBlocked(id, is_blocked);
			if (result > 0) {
				String token = iuserDao.getUserInfoExtraToken(id);
				RedisUtil.SetHsetJedis(0, token, "is_blocked", String.valueOf(is_blocked));
				if(is_blocked==1)
				{
					iuserDao.updateUserAnchorState(0, 0, String.valueOf(id));
					RedisUtil.SetHsetJedis(0, token, "anchor_type", "0");
					RedisUtil.SetHsetJedis(0, token, "anchor_state", "2");
				}
				else {
					RedisUtil.SetHsetJedis(0, token, "anchor_state", "0");
				}
				//通知客户端
				imanage_RecordDao.insertManageRecord(w_name, "封号/解封用户,id：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("封号/解封用户", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：更新用户视频状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 视频id
	 * @param status 状态 0未审核 1通过 2屏蔽
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserVideoStatus(int id, int status, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			if (result > 0) {
				imanage_RecordDao.insertManageRecord(w_name, "更新用户视频状态", "t_user_video", id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("更新用户视频状态", e);
		}
		return result;
	}

	/**
	 * 
	 * <p>功能描述：推荐用户视频</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 视频id
	 * @param is_recommend 是否推荐1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateUserVideoIsRecommend(int id, int is_recommend, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			if (result > 0) {
				imanage_RecordDao.insertManageRecord(w_name, "推荐用户视频", "t_user_video", id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("推荐用户视频", e);
		}
		return result;
	}
	/**
	 * 解除短信限制
	 * @param uid
	 * @param request
	 * @return
	 */
	public int cancelSmsLimit(Long uid, HttpServletRequest request)
	{
		try {
			String phone = iuserDao.getUserPhone(uid);
			if(!StringUtils.isBlank(phone))
			{
				RedisUtil.del(0, "smsp"+phone+"_num");
				String w_name = String.valueOf(request.getAttribute("w_name"));
				imanage_RecordDao.insertManageRecord(w_name, "解除短信限制，uid:"+uid, "redis", 0, IPUtil.getIp(request), new Date());
			}
			return 1;
		} catch (Exception e) {
			log.error("解除短信限制", e);
		}
		return 0;
	}
	/**
	 * 注册登录查询
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 分页页码
	 * @return
	 */		
	public String getRegisterLoginStatisticsList(Date start_time,Date end_time,String channel_platform,int page,Model model){
		String channel = "";
		Integer platform = null;
		List<RegisterLoginStatistics> list;
		RegisterLoginStatistics summary = null;

		if (StringUtils.isNotBlank(channel_platform)) {
			if (!channel_platform.equals("1111_0")) {
				String [] strings  = channel_platform.split("_");
				channel = strings[0];
				platform = Integer.parseInt(strings[1]);
			}else {
				channel = "";
				platform = null;
			}
		}

		int totalRecord = iuserDao.getRegisterLoginStatisticsCount(start_time, end_time);

		if (start_time==null) {
			list = new ArrayList<RegisterLoginStatistics>();
			totalRecord = 0;
		}else {
			list = iuserDao.getRegisterLoginStatisticsList(start_time, end_time,channel,platform,(page - 1) * 20, 20);
			summary = iuserDao.getRegisterLoginStatisticsSummary(start_time, end_time,channel,platform);
		}
		if(summary == null){
			summary = new RegisterLoginStatistics();
		}
		PageUtil pageUtil = new PageUtil(20, totalRecord, page);

		List<Recharge_Channel> recharge_ChannelList = irecharge_ChannelDao.getRecharge_ChannelList();

		pageUtil.setTotalRecord(totalRecord);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(14);
		pageUtil.setUrlName("userinfo/statisticslist"); 

		model.addAttribute("statisticslist", list);
		model.addAttribute("channellist", recharge_ChannelList);
		model.addAttribute("showPage", pageUtil);
		model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
		model.addAttribute("end_time", end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"");	
		model.addAttribute("activeUrl", "regloginstatistics");
		model.addAttribute("channel_platform", channel_platform);
		model.addAttribute("summary", summary);
		return "userinfo/statisticslist";
	}

	public String exportExcel(Long uid,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  

			String fileName = new String(("用户UID为"+uid+"的流水记录").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			XSSFSheet sheet = workBook.createSheet("sheet1");  
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			String[] titles = new String[]{"类型","收支","时间","备注"};
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  

			int totalRecord = itotal_FlowingDao.getTotalFlowingCount(uid);
			List<Total_Flowing> total_FLowingList = itotal_FlowingDao.getTotalFlowingList(uid, 0, totalRecord);

			// 构建表体数据  

			if (total_FLowingList != null && total_FLowingList.size() > 0)  
			{ 
				for (int j = 0; j < total_FLowingList.size(); j++)  
				{  
					XSSFRow bodyRow = sheet.createRow(j + 1); 

					cell = bodyRow.createCell(0);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(total_FLowingList.get(j).getType());  

					String prefix = total_FLowingList.get(j).getBalance_pay()==0?"+":"-";
					cell = bodyRow.createCell(1);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(prefix+total_FLowingList.get(j).getSpending_virtual());   

					cell = bodyRow.createCell(2);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(total_FLowingList.get(j).getCreate_time());

					cell = bodyRow.createCell(3);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(total_FLowingList.get(j).getRemark());
				}  
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
			log.error("用户UID为"+uid+"的流水记录", e);
		}
		return null;
	}

	public String exportExcel2(Date start_time,Date end_time,String channel_platform,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  

			String fileName = new String(("登录查询").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			XSSFSheet sheet = workBook.createSheet("sheet1");  
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFCell cell = null; 
			String[] titles = new String[]{"日期","新增人数","登录人数","登录次数"};
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  

			String channel = "";
			Integer platform = null;
			List<RegisterLoginStatistics> list;


			if (StringUtils.isNotBlank(channel_platform)) {
				if (!channel_platform.equals("1111_0")) {
					String [] strings  = channel_platform.split("_");
					channel = strings[0];
					platform = Integer.parseInt(strings[1]);
				}else {
					channel = "";
					platform = null;
				}
			}

			int totalRecord = iuserDao.getRegisterLoginStatisticsCount(start_time, end_time);

			if (start_time==null) {
				list = new ArrayList<RegisterLoginStatistics>();
				totalRecord = 0;
			}else {
				list = iuserDao.getRegisterLoginStatisticsList(start_time, end_time,channel,platform,0, totalRecord);
			}

			// 构建表体数据  

			if (list != null && list.size() > 0)  
			{ 
				for (int j = 0; j < list.size(); j++)  
				{  
					XSSFRow bodyRow = sheet.createRow(j + 1); 

					cell = bodyRow.createCell(0);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getStatis_date());  

					cell = bodyRow.createCell(1);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getRegister_count());   

					cell = bodyRow.createCell(2);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getUser_count());

					cell = bodyRow.createCell(3);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getLogin_count());
				}  
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
			log.error("用户注册登录查询", e);
		}
		return null;
	}	
	public String insertValid(String phone)
	{
		String valid = StringUtil.getRand();
		RedisUtil.SetShardedJedis(0, "smsp"+phone, valid);
		return valid;
	}

	public String getPicList(long uid, Model model)
	{
		try {
			User user = iuserDao.getUser(uid);

			model.addAttribute("uid", uid);
			model.addAttribute("head", user.getHead());
			model.addAttribute("activeUrl", "userinfo");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));			
		} catch (Exception e) {
			log.error("用户头像图片编辑页", e);
		}
		return "userinfo/pic";
	}

	public String updatePic(long uid, String head, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			iuserDao.updateUserHead(head, uid);
			String token = iuserDao.getUserInfoExtraToken(uid);
			RedisUtil.SetHsetJedis(0, token, "head", head);
			Date date = new Date();
			String w_name = String.valueOf(request.getAttribute("w_name"));

			imanage_RecordDao.insertManageRecord(w_name, "修改"+"“"+"uID："+uid+"”"+"头像", "t_user", 0, IPUtil.getIp(request), date);
			jsonObject.put("code", 0);
			jsonObject.put("msg", "保存成功");
		} catch (Exception e) {
			log.error("修改用户图片信息", e);
			jsonObject.put("code", -1);
			jsonObject.put("msg", "保存失败");
		}
		return jsonObject.toString();
	}

}

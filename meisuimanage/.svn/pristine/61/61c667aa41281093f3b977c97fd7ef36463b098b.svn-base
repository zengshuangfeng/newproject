package com.meisui.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.forman.foundation.library.DoubleUtil;
import com.meisui.manage.dao.Ianchor_PayDao;
import com.meisui.manage.dao.Ianchor_TimeDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.Ianchor_Virtual_Change_RecordDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Pay;
import com.meisui.manage.entity.Anchor_Pay_Illegal;
import com.meisui.manage.entity.Anchor_Pay_Rule;
import com.meisui.manage.entity.Anchor_Time;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Anchor_Union_Pay;
import com.meisui.manage.entity.Anchor_Virtual_Change_Record;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
@Service
public class Anchor_WeekPayService {
	private static Logger log = Logger.getLogger(Anchor_WeekPayService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private Ianchor_PayDao ianchor_PayDao;
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Ianchor_TimeDao ianchor_TimeDao;
	@Autowired
	private Ianchor_Virtual_Change_RecordDao ianchor_Virtual_Change_RecordDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 主播周结列表
	 * @param uid 主播UID
	 * @param f_uuid 主播房间号
	 * @param nickname 主播昵称
	 * @param union_id 工会id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getList(long uid, long f_uuid, String nickname, int union_id, int page, Model model)
	{
		try {
			List<Anchor_Pay> anchorPayList = new ArrayList<Anchor_Pay>();
			Calendar calendar1 = Calendar.getInstance();  
			Calendar calendar2 = Calendar.getInstance();  
			int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1; //当前星期
			int offset1 = 1 - dayOfWeek;  
			int offset2 = 7 - dayOfWeek;  
			calendar1.add(Calendar.DATE, offset1 - 7);  
			calendar2.add(Calendar.DATE, offset2 - 7);
			Date start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");//上周一00:00:00
			Date end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");//上周日23:59:59
			Date date = new Date();

			int is_show_pay = 0;//结算按钮是否可用
			List<Integer> union_idPayList = ianchor_PayDao.getAnchorUnionPayExistList(start_time, end_time);//t_anchor_union_pay 工会结算记录表   union_id
			List<Anchor_Union> anchorUnionList = ianchor_UnionDao.getWeekSettleAnchorUnionList();//t_anchor_union 工会表 ,id、name
			for (Anchor_Union anchor_Union : anchorUnionList) {
				if(union_idPayList.contains(anchor_Union.getId()))
					anchor_Union.setIs_pay(1);
				
				if(union_id>0&&union_id==anchor_Union.getId()&&anchor_Union.getIs_pay()==0)//搜索工会不为空，经上面一步，工会还属于未结算的
					is_show_pay = 1;
			}
			List<User_Anchor> userAnchorList = new ArrayList<User_Anchor>();

			int totalRecord = 0;
			if(is_show_pay==1)//如果工会属于未结算完成的
			{
				if(union_id>0 || uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))//工会或UID或房间号或昵称不为空
					userAnchorList = iuserDao.getUserAnchorPayList(uid, f_uuid, nickname, union_id, start_time,0,(page-1)*20, 20);//t_user_anchor 主播表
				for (User_Anchor user_Anchor : userAnchorList) {
					Anchor_Pay anchor_Pay = ianchor_PayDao.getAnchorPay(user_Anchor.getUid(), union_id, start_time, end_time,0);//t_anchor_pay 主播结算记录表
					if(anchor_Pay==null)//结算记录为空
					{
						anchor_Pay = new Anchor_Pay();
						anchor_Pay.setUid(user_Anchor.getUid());
						anchor_Pay.setF_uuid(user_Anchor.getF_uuid());
						anchor_Pay.setTotal_anchor_virtual(user_Anchor.getTotal_anchor_virtual());//总魅力值(魅力值==钻石数)
						Long total_stake = 0L;//主播收到游戏流水，即用户押在该主播间游戏的总钻石数
						Double illegal_money = 0d;//总违规金额
						Double extra_money = 0d;//额外奖励
						//不是散播(日结)，周结    可提现魅力值、游戏流水、总违规金额、额外奖励、游戏流水奖励（元）
						Long surplus_anchor_virtual = iprofitDao.getProfitSum(user_Anchor.getUid(), start_time, end_time);//上周累积的可用魅力值
						if(surplus_anchor_virtual==null)//实际收到直播虚拟币总数
							surplus_anchor_virtual = 0L;
						if(surplus_anchor_virtual>user_Anchor.getSurplus_anchor_virtual())
							surplus_anchor_virtual = user_Anchor.getSurplus_anchor_virtual();
						anchor_Pay.setSurplus_anchor_virtual(surplus_anchor_virtual);
						total_stake = ianchor_PayDao.getUserTotalStake(user_Anchor.getF_uuid(), start_time.getTime(), end_time.getTime());
						illegal_money = ianchor_PayDao.getAnchorPayIllegalSum(user_Anchor.getUid(), 0, start_time, end_time);//t_anchor_pay_illegal 按illegal_time查
						extra_money = ianchor_PayDao.getAnchorPayIllegalSum(user_Anchor.getUid(), 1, start_time, end_time);
						if(total_stake!=null)
						{
							double union_reward = 0d;//游戏流水奖励（元）
							if(total_stake>=3500000&&total_stake<6500000)
							{
								union_reward = DoubleUtil.mul((double)anchor_Pay.getSurplus_anchor_virtual(), 0.05);
								union_reward = DoubleUtil.div(union_reward, 100, 2);
							}
							else if(total_stake>=6500000&&total_stake<9500000)
							{
								union_reward = DoubleUtil.mul((double)anchor_Pay.getSurplus_anchor_virtual(), 0.1);
								union_reward = DoubleUtil.div(union_reward, 100, 2);
							}
							else if(total_stake>=9500000)
							{
								union_reward = DoubleUtil.mul((double)anchor_Pay.getSurplus_anchor_virtual(), 0.15);
								union_reward = DoubleUtil.div(union_reward, 100, 2);
							}
							anchor_Pay.setUnion_reward(union_reward);//游戏流水奖励（元）
						}


						if(illegal_money==null)
							illegal_money = 0d;
						if(extra_money==null)
							extra_money = 0d;
						anchor_Pay.setIllegal_money(illegal_money);
						anchor_Pay.setExtra_money(extra_money);
						if(total_stake==null)
							total_stake = 0L;
						anchor_Pay.setTotal_stake(total_stake);
						User_Info user_Info = iuserDao.getUserInfo(user_Anchor.getUid());
						anchor_Pay.setDivide(user_Info.getDivide_proportion());
						//以下是计算直播有效天数
						int realy_total_day = 0;
						Map<String, Long> dayMap = new HashMap<String, Long>();
						Map<Date, Date> dayDateMap = new LinkedHashMap<Date, Date>();
						List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList2(user_Anchor.getUid(), 0, start_time, end_time);
						List<Anchor_Time> anchor_TimeList2 = new ArrayList<Anchor_Time>();
						for (Anchor_Time anchor_Time : anchor_TimeList) {
							if(anchor_Time.getStart_time().before(start_time))
								anchor_Time.setStart_time(start_time);
							if(anchor_Time.getEnd_time().after(end_time))
							{
								anchor_Time.setEnd_time(end_time);
							}
							Date  current_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd")+" 00:00:00");
							Date  current_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getEnd_time(), "yyyy-MM-dd")+" 00:00:00");
							if(current_start_date.before(current_end_date))
							{
								Anchor_Time temp = new Anchor_Time();
								temp.setUid(anchor_Time.getUid());
								temp.setStart_time(anchor_Time.getStart_time());
								temp.setEnd_time(anchor_Time.getEnd_time());
								temp.setStart_time(current_end_date);
								anchor_TimeList2.add(temp);
								anchor_Time.setEnd_time(AuthUtil.formatStringToDate(AuthUtil.formatDateToString(current_start_date,"yyyy-MM-dd")+" 23:59:59"));
								anchor_TimeList2.add(anchor_Time);
							}
							else
								anchor_TimeList2.add(anchor_Time);
						}
						//不满半小时的不算进去， 断线3分钟内可累加
						for (Anchor_Time anchor_Time : anchor_TimeList2) {
							boolean is_contain = false;
							for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) {  
								if(anchor_Time.getStart_time().getTime()-entry.getValue().getTime()<=180000&&anchor_Time.getStart_time().getTime()-entry.getValue().getTime()>0)
								{
									entry.setValue(anchor_Time.getEnd_time());
									is_contain = true;
								}
							}
							if(!is_contain)
								dayDateMap.put(anchor_Time.getStart_time(), anchor_Time.getEnd_time());
						}
						for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) { 
							if(entry.getValue().getTime()-entry.getKey().getTime()>1800000)//大于半小时才算有效记录
							{
								String current_date = AuthUtil.formatDateToString(entry.getKey(),"yyyy-MM-dd");
								if(dayMap.containsKey(current_date))
								{
									dayMap.put(current_date, dayMap.get(current_date) + (entry.getValue().getTime()-entry.getKey().getTime()));
								}
								else
								{
									dayMap.put(current_date, entry.getValue().getTime()-entry.getKey().getTime());
								}
							}
						}
						for (Map.Entry<String, Long> entry : dayMap.entrySet()) { 
							if(entry.getValue()>=7200000)//大于两个小时 毫秒
								realy_total_day+=1;
						}
						anchor_Pay.setEffect_days(realy_total_day);
					}//if(anchor_Pay==null) end
					
					for (Anchor_Union anchor_Union : anchorUnionList) {
						if(anchor_Union.getId()==user_Anchor.getUnion_id())
						{
							anchor_Pay.setUnion_name(anchor_Union.getName());
							break;
						}
					}
					anchor_Pay.setType(user_Anchor.getType());
					anchor_Pay.setNickname(user_Anchor.getNickname());
					anchorPayList.add(anchor_Pay);
				}//for (User_Anchor user_Anchor : userAnchorList) end
				if(union_id>0 || uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))
					totalRecord = iuserDao.getUserAnchorPayCount(uid, f_uuid, nickname, union_id, start_time,0);
				if(uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))
					is_show_pay=0;
			}// if(is_show_pay==1) end
			else { //否则，认为是已结算完成的工会
				if(union_id>0 || uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))
				{
					anchorPayList = ianchor_PayDao.getAnchorPayList(uid, f_uuid, nickname, union_id, start_time, end_time,0,(page-1)*20, 20);
					for (Anchor_Pay anchor_Pay : anchorPayList) {
						User user = iuserDao.getUser(anchor_Pay.getUid());
						anchor_Pay.setNickname(user.getNickname());
						User_Anchor user_Anchor = iuserDao.getUserAnchor(anchor_Pay.getUid());
						anchor_Pay.setType(user_Anchor.getType());
						for (Anchor_Union anchor_Union : anchorUnionList) {
							if(anchor_Union.getId()==user_Anchor.getUnion_id())
							{
								anchor_Pay.setUnion_name(anchor_Union.getName());
								break;
							}
						}
					}
					totalRecord = ianchor_PayDao.getAnchorPayCount(uid, f_uuid, nickname, union_id, start_time, end_time,0);
				}
			}
			Date action_time = AuthUtil.formatStringToDate("2017-06-19 00:00:00");
			if(date.before(action_time))
				is_show_pay = 0;
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(18);
			pageUtil.setUrlName("anchorweekpay/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorPayList", anchorPayList);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("activeUrl", "anchorweekpay");
			model.addAttribute("union_id", union_id);
			model.addAttribute("anchorUnionList", anchorUnionList);
			model.addAttribute("is_show_pay", is_show_pay);
		} catch (Exception e) {
			log.error("主播周结列表", e);
		}
		return "anchorweekpay/list";
	}
	/**
	 * 设置违规处罚
	 * @param uid 用户UID
	 * @param illegal_money 违规金额
	 * @param illegal_time 违规日期
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveAnchorPayIllegal(long uid, double illegal_money, Date illegal_time, String remark, int type, String w_name, HttpServletRequest request)
	{
		try {
			Anchor_Pay_Illegal anchor_Pay_Illegal = new Anchor_Pay_Illegal();
			anchor_Pay_Illegal.setUid(uid);
			anchor_Pay_Illegal.setIllegal_money(illegal_money);
			anchor_Pay_Illegal.setIllegal_time(illegal_time);
			anchor_Pay_Illegal.setRemark(remark);
			Date date = new Date();
			anchor_Pay_Illegal.setCreate_time(date);
			w_name = String.valueOf(request.getAttribute("w_name"));
			anchor_Pay_Illegal.setW_name(w_name);
			anchor_Pay_Illegal.setType(type);
			int result = ianchor_PayDao.insertAnchorPayIllegal(anchor_Pay_Illegal);
			if(result>0)
			{
				imanage_RecordDao.insertManageRecord(w_name, "设置违规处罚", "t_anchor_pay_illegal", anchor_Pay_Illegal.getId(), IPUtil.getIp(request), date);	
				return "{\"code\":0,\"msg\":\"处罚成功\"}";
			}
		} catch (Exception e) {
			log.error("设置违规处罚", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 主播违规处罚备注列表
	 * @param uid 用户UId
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getIllegalList(long uid, int page, Model model)
	{
		try {
			List<Anchor_Pay_Illegal> anchor_Pay_IllegalList = ianchor_PayDao.getAnchorPayIllegalList(uid, (page-1)*20, 20);
			int totalRecord = ianchor_PayDao.getAnchorPayIllegalCount(uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(18);
			pageUtil.setUrlName("anchorweekpay/illegallist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorPayIllegalList", anchor_Pay_IllegalList);
			model.addAttribute("uid", uid);
			model.addAttribute("activeUrl", "anchorpay");
		} catch (Exception e) {
			log.error("主播违规处罚备注列表", e);
		}
		return "anchorweekpay/illegallist";
	}
	/**
	 * 结算
	 * @param union_id 工会id
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String savePay(int union_id, String w_name, HttpServletRequest request)
	{
		try{
			long total_clear_surplus_anchor_virtual = 0l;
			double total_settle_money = 0d;
			Date date = new Date();
			Calendar calendar1 = Calendar.getInstance();  
			Calendar calendar2 = Calendar.getInstance();  
			int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
			int offset1 = 1 - dayOfWeek;  
			int offset2 = 7 - dayOfWeek;  
			calendar1.add(Calendar.DATE, offset1 - 7);  
			calendar2.add(Calendar.DATE, offset2 - 7);
			Date start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");
			Date end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");

			List<User_Anchor> userAnchorList = iuserDao.getUserAnchorPayList(0l, 0l, "", union_id, start_time, 0,0, 1000000);
			List<Anchor_Pay_Rule> anchor_Pay_RuleList = ianchor_PayDao.getAnchorPayRuleList();
			Anchor_Union_Pay anchor_Union_Pay = new Anchor_Union_Pay();
			anchor_Union_Pay.setUnion_id(union_id);
			anchor_Union_Pay.setStart_time(start_time);
			anchor_Union_Pay.setEnd_time(end_time);
			anchor_Union_Pay.setCreate_time(date);
			w_name = String.valueOf(request.getAttribute("w_name"));
			anchor_Union_Pay.setW_name(w_name);
			anchor_Union_Pay.setIs_commit(1);
			for (User_Anchor user_Anchor : userAnchorList) {
				Anchor_Pay anchor_Pay = ianchor_PayDao.getAnchorPay(user_Anchor.getUid(), union_id, start_time, end_time,0);
				if(anchor_Pay==null)
				{
					anchor_Pay = new Anchor_Pay();
					anchor_Pay.setUid(user_Anchor.getUid());
					anchor_Pay.setF_uuid(user_Anchor.getF_uuid());
					anchor_Pay.setTotal_anchor_virtual(user_Anchor.getTotal_anchor_virtual());
					anchor_Pay.setType(user_Anchor.getType());
					anchor_Pay.setUnion_id(union_id);
					Long total_stake =  0L;
					Double illegal_money = 0d;
					Double extra_money = 0d;

					Long surplus_anchor_virtual = iprofitDao.getProfitSum(user_Anchor.getUid(), start_time, end_time);
					if(surplus_anchor_virtual==null)
						surplus_anchor_virtual = 0L;
					if(surplus_anchor_virtual>user_Anchor.getSurplus_anchor_virtual())
						surplus_anchor_virtual = user_Anchor.getSurplus_anchor_virtual();
					anchor_Pay.setSurplus_anchor_virtual(surplus_anchor_virtual);
					total_stake = ianchor_PayDao.getUserTotalStake(user_Anchor.getF_uuid(), start_time.getTime(), end_time.getTime());
					illegal_money = ianchor_PayDao.getAnchorPayIllegalSum(user_Anchor.getUid(), 0, start_time, end_time);
					extra_money  = ianchor_PayDao.getAnchorPayIllegalSum(user_Anchor.getUid(), 1, start_time, end_time);
					anchor_Pay.setStart_time(start_time);
					anchor_Pay.setEnd_time(end_time);
					if(total_stake!=null)
					{
						double union_reward = 0d;
						if(total_stake>=3500000&&total_stake<6500000)
						{
							union_reward = DoubleUtil.mul((double)anchor_Pay.getSurplus_anchor_virtual(), 0.05);
							union_reward = DoubleUtil.div(union_reward, 100, 2);
						}
						else if(total_stake>=6500000&&total_stake<9500000)
						{
							union_reward = DoubleUtil.mul((double)anchor_Pay.getSurplus_anchor_virtual(), 0.1);
							union_reward = DoubleUtil.div(union_reward, 100, 2);
						}
						else if(total_stake>=9500000)
						{
							union_reward = DoubleUtil.mul((double)anchor_Pay.getSurplus_anchor_virtual(), 0.15);
							union_reward = DoubleUtil.div(union_reward, 100, 2);
						}
						anchor_Pay.setUnion_reward(union_reward);
					}


					if(total_stake==null)
						total_stake = 0L;
					if(extra_money==null)
						extra_money = 0d;
					anchor_Pay.setTotal_stake(total_stake);
					User_Info user_Info = iuserDao.getUserInfo(user_Anchor.getUid());
					anchor_Pay.setDivide(user_Info.getDivide_proportion());
					anchor_Pay.setIs_commit(1);
					anchor_Pay.setCreate_time(date);
					anchor_Pay.setW_name(w_name);
					if(illegal_money==null)
						illegal_money = 0d;
					anchor_Pay.setIllegal_money(illegal_money);
					anchor_Pay.setExtra_money(extra_money);
					int realy_total_day = 0;
					Map<String, Long> dayMap = new HashMap<String, Long>();
					Map<Date, Date> dayDateMap = new LinkedHashMap<Date, Date>();
					List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList2(user_Anchor.getUid(), 0, start_time, end_time);
					List<Anchor_Time> anchor_TimeList2 = new ArrayList<Anchor_Time>();
					for (Anchor_Time anchor_Time : anchor_TimeList) {
						if(anchor_Time.getStart_time().before(start_time))
							anchor_Time.setStart_time(start_time);
						if(anchor_Time.getEnd_time().after(end_time))
						{
							anchor_Time.setEnd_time(end_time);
						}
						Date  current_start_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd")+" 00:00:00");
						Date  current_end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getEnd_time(), "yyyy-MM-dd")+" 00:00:00");
						if(current_start_date.before(current_end_date))
						{
							Anchor_Time temp = new Anchor_Time();
							temp.setUid(anchor_Time.getUid());
							temp.setStart_time(anchor_Time.getStart_time());
							temp.setEnd_time(anchor_Time.getEnd_time());
							temp.setStart_time(current_end_date);
							anchor_TimeList2.add(temp);
							anchor_Time.setEnd_time(AuthUtil.formatStringToDate(AuthUtil.formatDateToString(current_start_date,"yyyy-MM-dd")+" 23:59:59"));
							anchor_TimeList2.add(anchor_Time);
						}
						else
							anchor_TimeList2.add(anchor_Time);
					}
					//不满半小时的不算进去， 断线3分钟内可累加
					for (Anchor_Time anchor_Time : anchor_TimeList2) {
						boolean is_contain = false;
						for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) {  
							if(anchor_Time.getStart_time().getTime()-entry.getValue().getTime()<=180000&&anchor_Time.getStart_time().getTime()-entry.getValue().getTime()>0)
							{
								entry.setValue(anchor_Time.getEnd_time());
								is_contain = true;
							}
						}
						if(!is_contain)
							dayDateMap.put(anchor_Time.getStart_time(), anchor_Time.getEnd_time());
					}
					/*for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) { 
						System.out.println(AuthUtil.formatDateToString(entry.getKey())+" "+AuthUtil.formatDateToString(entry.getValue()));
					}
					 */						
					for (Map.Entry<Date, Date> entry : dayDateMap.entrySet()) { 
						if(entry.getValue().getTime()-entry.getKey().getTime()>1800000)//大于半小时才算有效记录
						{
							String current_date = AuthUtil.formatDateToString(entry.getKey(),"yyyy-MM-dd");
							if(dayMap.containsKey(current_date))
							{
								dayMap.put(current_date, dayMap.get(current_date) + (entry.getValue().getTime()-entry.getKey().getTime()));
							}
							else
							{
								dayMap.put(current_date, entry.getValue().getTime()-entry.getKey().getTime());
							}
						}
					}
					for (Map.Entry<String, Long> entry : dayMap.entrySet()) { 
						if(entry.getValue()>=7200000)//大于两个小时 毫秒
							realy_total_day+=1;
					}
					anchor_Pay.setEffect_days(realy_total_day);
					
					double weel_reward = 0d, divide = DoubleUtil.div(user_Anchor.getDivide_proportion(), 100, 2);
					if(anchor_Pay.getSurplus_anchor_virtual()<25000)
						divide = 0d;
					for (Anchor_Pay_Rule anchor_Pay_Rule : anchor_Pay_RuleList) {
						if(anchor_Pay_Rule.getType()==user_Anchor.getType()&&anchor_Pay_Rule.getIs_day()==0)
						{
							if(anchor_Pay_Rule.getMin_virtual_count()>0&&anchor_Pay_Rule.getMax_virtual_count()==0)//表示无上限
							{
								if(anchor_Pay.getSurplus_anchor_virtual()>=anchor_Pay_Rule.getMin_virtual_count()&&realy_total_day>=anchor_Pay_Rule.getEffect_days())
								{
									weel_reward = anchor_Pay_Rule.getWeek_reward();
								}
							}
							else {
								if(anchor_Pay.getSurplus_anchor_virtual()>=anchor_Pay_Rule.getMin_virtual_count()&&anchor_Pay.getSurplus_anchor_virtual()<=anchor_Pay_Rule.getMax_virtual_count()&&realy_total_day>=anchor_Pay_Rule.getEffect_days())
								{
									weel_reward = anchor_Pay_Rule.getWeek_reward();
								}
							}
						}
					}
					anchor_Pay.setWeek_reward(weel_reward);
					anchor_Pay.setGift_money(DoubleUtil.div(DoubleUtil.mul(anchor_Pay.getSurplus_anchor_virtual(),divide),100,2));

					double total_money = DoubleUtil.sum(anchor_Pay.getGift_money(), anchor_Pay.getWeek_reward());
					total_money = DoubleUtil.sum(total_money, anchor_Pay.getExtra_money());
					total_money = DoubleUtil.sum(total_money, anchor_Pay.getUnion_reward());
					total_money = DoubleUtil.sub(total_money, anchor_Pay.getIllegal_money());
					total_settle_money = DoubleUtil.sum(total_settle_money, total_money);
					anchor_Pay.setTotal_money(total_money);
					ianchor_PayDao.insertAnchorPay(anchor_Pay);
					if(anchor_Pay.getId()>0)
					{
						total_clear_surplus_anchor_virtual += anchor_Pay.getSurplus_anchor_virtual();
						iuserDao.updateUserSurplusAnchorVirtual(anchor_Pay.getSurplus_anchor_virtual(), anchor_Pay.getUid());
						Anchor_Virtual_Change_Record anchor_Virtual_Change_Record = new Anchor_Virtual_Change_Record();
						anchor_Virtual_Change_Record.setUid(anchor_Pay.getUid());
						anchor_Virtual_Change_Record.setChange_virtual(anchor_Pay.getSurplus_anchor_virtual());
						anchor_Virtual_Change_Record.setTotal_anchor_virtual(user_Anchor.getTotal_anchor_virtual());
						anchor_Virtual_Change_Record.setSurplus_anchor_virtual(user_Anchor.getSurplus_anchor_virtual()-anchor_Pay.getSurplus_anchor_virtual());
						Long total_change_virtual = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeSum(anchor_Pay.getUid());
						if(total_change_virtual==null)
							total_change_virtual = 0L;
						anchor_Virtual_Change_Record.setTotal_change_virtual(total_change_virtual+anchor_Pay.getSurplus_anchor_virtual());
						anchor_Virtual_Change_Record.setRemark(String.valueOf(anchor_Pay.getGift_money()));
						anchor_Virtual_Change_Record.setW_name(w_name);
						anchor_Virtual_Change_Record.setCreate_time(date);
						ianchor_Virtual_Change_RecordDao.insertAnchorVirtualChangeRecord(anchor_Virtual_Change_Record);
					}
				}
			}
			ianchor_PayDao.insertAnchorUnionPay(anchor_Union_Pay);
			imanage_RecordDao.insertManageRecord(w_name, "主播周结-工会ID:"+union_id+"，本次总清除魅力值:"+total_clear_surplus_anchor_virtual+"；本次总结算金额:"+total_settle_money+"元", "t_anchor_pay", 0, IPUtil.getIp(request), date);
			return "{\"code\":0,\"msg\":\"结算成功\"}";
		} catch (Exception e) {
			log.error("主播周结列表", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	public String getAnchorVirtualChangeRecordList(long uid, int page, Model model)
	{
		try {
			List<Anchor_Virtual_Change_Record> anchor_Virtual_Change_RecordList = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeRecordList(uid, (page-1)*20, 20);
			int totalRecord = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeRecordCount(uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/virtualchangelist");
			User user = iuserDao.getUser(uid);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorVirtualChangeRecordList", anchor_Virtual_Change_RecordList);
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", user.getNickname());
			model.addAttribute("activeUrl", "useranchor");
		} catch (Exception e) {
			log.error("主播魅力值变更日志列表", e);
		}
		return "useranchor/virtualchangelist";
	}
	public String exportExcel(int union_id, HttpServletResponse response)
	{
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Calendar calendar1 = Calendar.getInstance();  
			Calendar calendar2 = Calendar.getInstance();  
			int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
			int offset1 = 1 - dayOfWeek;  
			int offset2 = 7 - dayOfWeek;  
			calendar1.add(Calendar.DATE, offset1 - 7);  
			calendar2.add(Calendar.DATE, offset2 - 7);
			Date start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar1.getTime(),"yyyy-MM-dd")+" 00:00:00");
			Date end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar2.getTime(),"yyyy-MM-dd")+" 23:59:59");


			Anchor_Union anchor_Union =ianchor_UnionDao.getAnchorUnion(union_id);
			//List<User_Anchor> userAnchorList = iuserDao.getUserAnchorPayList(0l, 0l, "", union_id, start_time, 0, 1000000);
			String fileName = new String((anchor_Union.getName()+"-主播周结列表").getBytes(), "ISO8859_1");  
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
			String[] titles = new String[]{"主播UID","房间号","昵称","工会","主播类型","游戏流水","游戏流水奖励（元）","总魅力值","可提现魅力值","分成占比","礼物提成（元）","周奖励（元）","额外奖励（元）","直播有效天数","总违规金额（元）","总收益","结算周期","违规备注"};
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  
			List<Anchor_Pay> anchor_PayList = ianchor_PayDao.getAnchorPayList(0L, 0L, "", union_id, start_time, end_time,0, 0, 100000);
			// 构建表体数据  
			int j=0;
			for (Anchor_Pay anchor_Pay : anchor_PayList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Pay.getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Pay.getF_uuid());   

				User user = iuserDao.getUser(anchor_Pay.getUid());
				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user.getNickname());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Union.getName());

				User_Anchor user_Anchor = iuserDao.getUserAnchor(anchor_Pay.getUid());
				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user_Anchor.getType()==1?"A/才艺类":user_Anchor.getType()==2?"B类":"");


				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getTotal_stake());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getUnion_reward());


				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getTotal_anchor_virtual());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getSurplus_anchor_virtual());

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getDivide());

				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(anchor_Pay.getGift_money());

				cell = bodyRow.createCell(11);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(anchor_Pay.getWeek_reward());

				cell = bodyRow.createCell(12);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(anchor_Pay.getExtra_money());

				cell = bodyRow.createCell(13);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(anchor_Pay.getEffect_days());

				cell = bodyRow.createCell(14);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(anchor_Pay.getIllegal_money());

				cell = bodyRow.createCell(15);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(anchor_Pay.getTotal_money());


				cell = bodyRow.createCell(16);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(AuthUtil.formatDateToString(start_time)+"至"+AuthUtil.formatDateToString(end_time));

				cell = bodyRow.createCell(17);  
				cell.setCellStyle(bodyStyle);
				StringBuffer sBuffer = new StringBuffer();
				List<Anchor_Pay_Illegal> anchor_Pay_IllegalList = ianchor_PayDao.getAnchorPayIllegalListWithTime(anchor_Pay.getUid(), start_time, end_time);
				for (Anchor_Pay_Illegal anchor_Pay_Illegal : anchor_Pay_IllegalList) {
					sBuffer.append("【");
					sBuffer.append(anchor_Pay_Illegal.getIllegal_time());
					if(anchor_Pay_Illegal.getType()==0)
						sBuffer.append(" 处罚金额：");
					else 
						sBuffer.append(" 奖励金额：");
					sBuffer.append(anchor_Pay_Illegal.getIllegal_money());
					if(anchor_Pay_Illegal.getType()==0)
						sBuffer.append(" 违规原因：");
					else
						sBuffer.append(" 奖励原因：");
					sBuffer.append(anchor_Pay_Illegal.getRemark());
					sBuffer.append("】");
				}
				cell.setCellValue(sBuffer.toString());;

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
			log.error("导出主播周结列表", e);
		}
		return null;
	}  
}

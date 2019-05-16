package com.meisui.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.IdialDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IspendingDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.GiftSpendingStatistics;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Short_Gift;
import com.meisui.manage.entity.Spending;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

/**
 * <p>文件名称：Gift_InfoService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Service
public class Gift_InfoService {
	private static Logger log = Logger.getLogger(Gift_InfoService.class.getClass());
	@Autowired
	private Igift_InfoDao igift_InfoDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IspendingDao ispendingDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IdialDao idialdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerdao;
	@Autowired
	private Ianchor_Guard_ChangeDao ianchor_guard_changeDao;
	/**
	 * 
	 * <p>功能描述：礼物列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param gift_id 礼物id
	 * @param gift_name 礼物名称
	 * @param is_online 是否上线 1是 0否
	 * @param is_private 是否一对一 1是 0否
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getGiftInfoList(int gift_id, String gift_name, int is_online, int is_private,int type, int page, Model model)
	{
		try {
			int is_box=0;
			
			List<Gift_Info> gift_InfoList = igift_InfoDao.getGiftInfoList2(gift_id, gift_name, is_online, is_private,type,is_box, (page-1)*20, 20);
			int totalRecord = igift_InfoDao.getGiftInfoCount2(gift_id, gift_name, is_online, is_private,type,is_box);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("gift/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("giftInfoList", gift_InfoList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "gift");
			model.addAttribute("gift_id", gift_id);
			model.addAttribute("gift_name", gift_name);
			model.addAttribute("is_online", is_online);
			model.addAttribute("is_private", is_private);
			model.addAttribute("type", type);
		} catch (Exception e) {
			log.error("礼物列表", e);
		}
		return "gift/info/list";
	}
	
	public String getGiftBoxList(int gift_id, String gift_name, int is_online, int is_private, int page, Model model) {

		try {
			int is_box=1;
			List<Gift_Info> gift_InfoList = igift_InfoDao.getGiftInfoList(gift_id, gift_name, is_online, is_private,is_box, (page-1)*20, 20);
			int totalRecord = igift_InfoDao.getGiftInfoCount(gift_id, gift_name, is_online, is_private,is_box);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("gift/box");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("giftInfoList", gift_InfoList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "giftbox");
			model.addAttribute("gift_id", gift_id);
			model.addAttribute("gift_name", gift_name);
			model.addAttribute("is_online", is_online);
			model.addAttribute("is_private", is_private);
		} catch (Exception e) {
			log.error("礼物列表", e);
		}
		return "gift/box/list";
	}
	/**
	 * 
	 * <p>功能描述：礼物添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:22:43</p>
	 *
	 * @param model
	 * @return
	 */
	public String addGiftInfo(Integer gift_type,Model model)
	{
		model.addAttribute("gift_type", gift_type);
		model.addAttribute("activeUrl", "gift");
		return "gift/info/add";
	}
	
	public String addGiftInfoBox(Model model)
	{
		model.addAttribute("activeUrl", "giftbox");
		return "gift/box/add";
	}
	/**
	 * 
	 * <p>功能描述：礼物编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 礼物id
	 * @param model
	 * @return
	 */
	public String editGiftInfo(int id, Model model)
	{
		Gift_Info gift_Info = igift_InfoDao.getGiftInfo(id);
		gift_Info.setId(id);
		model.addAttribute("giftInfo", gift_Info);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "gift");
		return "gift/info/edit";
	}
	
	public String editGiftInfoBox(int id, Model model)
	{
		Gift_Info gift_Info = igift_InfoDao.getGiftInfo(id);
		gift_Info.setId(id);
		model.addAttribute("giftInfo", gift_Info);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "giftbox");
		return "gift/box/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存礼物信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 礼物id
	 * @param gift_head 礼物图片
	 * @param gift_name 礼物名称
	 * @param gift_description 礼物文案
	 * @param gift_virtual 所需虚拟币
	 * @param gift_level 所需等级
	 * @param sort 排序
	 * @param is_online 是否上线 1是 0否
	 * @param anchor_virtual 主播可获得魅力值
	 * @param gift_type 礼物类型 0连击 1特效连击 2飘屏
	 * @param screen_type 飘屏客户端礼物 1法拉利 2宝马车
	 * @param verison 版本
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveGiftInfo(int id, String gift_head, String gift_name, String gift_description,int type, int gift_virtual, int gift_level, int sort, int is_online, int anchor_virtual, int gift_type, int screen_type, String version, int gift_key, String quantifier, String module, int is_private, Integer anchor_divide,Integer promoter_divide,int gift_time,String gift_webp, String gift_head2, String w_name, HttpServletRequest request)
	{
		try{	
			Gift_Info gift_Info = new Gift_Info();
			gift_Info.setGift_head(gift_head);
			gift_Info.setGift_name(gift_name);
			gift_Info.setGift_description(gift_description);
			gift_Info.setGift_virtual(gift_virtual);
			gift_Info.setGift_level(gift_level);
			gift_Info.setSort(sort);
			gift_Info.setIs_online(is_online);
			gift_Info.setAnchor_virtual(anchor_virtual);
			gift_Info.setGift_type(gift_type);
			gift_Info.setScreen_type(screen_type);
			gift_Info.setVersion(version);
			w_name = String.valueOf(request.getAttribute("w_name"));
			gift_Info.setW_name(w_name);
			gift_Info.setGift_key(gift_key);
			gift_Info.setQuantifier(quantifier);
			gift_Info.setIs_private(is_private);
			gift_Info.setAnchor_divide(anchor_divide);
			gift_Info.setPromoter_divide(promoter_divide);
			gift_Info.setIs_box(0);
			gift_Info.setGift_time(gift_time);
			gift_Info.setGift_webp(gift_webp);
			gift_Info.setGift_head2(gift_head2);
			gift_Info.setType(type);
			switch (module) {
			case "1,2":
				gift_Info.setModule(0);
				break;
			case "1":
				gift_Info.setModule(1);
				break;
			case "2":
				gift_Info.setModule(2);
				break;
			}
			Date date = new Date();
			gift_Info.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				gift_Info.setId(id);
				result = igift_InfoDao.updateGiftInfo(gift_Info);
				imanage_RecordDao.insertManageRecord(w_name, "更新礼物信息", "gift_info", gift_Info.getId(), IPUtil.getIp(request), date);
				String gift=id+","+anchor_virtual+","+gift_type+","+gift_name+","+gift_head+","+quantifier+","+gift_virtual;
				RedisUtil.del(4, "gift_"+id);
				RedisUtil.add(4, "gift_"+id,gift);
			}
			else
			{
				gift_Info.setCreate_time(date);
				result = igift_InfoDao.insertGiftInfo(gift_Info);
				imanage_RecordDao.insertManageRecord(w_name, "添加礼物信息", "gift_info", gift_Info.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存礼物信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
	public String saveGiftInfoBox(int id, String gift_head, String gift_name, String gift_description, int gift_virtual, int gift_level, int sort, int is_online, int anchor_virtual, int gift_type, int screen_type, String version, int gift_key, String quantifier, String module, int is_private, Integer anchor_divide,Integer promoter_divide, int old_gift_id, String w_name, HttpServletRequest request)
	{
		try{	
			Gift_Info gift_Info = new Gift_Info();
			gift_Info.setGift_head(gift_head);
			gift_Info.setGift_name(gift_name);
			gift_Info.setGift_description(gift_description);
			gift_Info.setGift_virtual(gift_virtual);
			gift_Info.setGift_level(gift_level);
			gift_Info.setSort(sort);
			gift_Info.setIs_online(is_online);
			gift_Info.setAnchor_virtual(anchor_virtual);
			gift_Info.setGift_type(gift_type);
			gift_Info.setScreen_type(screen_type);
			gift_Info.setVersion(version);
			w_name = String.valueOf(request.getAttribute("w_name"));
			gift_Info.setW_name(w_name);
			gift_Info.setGift_key(gift_key);
			gift_Info.setQuantifier(quantifier);
			gift_Info.setIs_private(is_private);
			gift_Info.setAnchor_divide(anchor_divide);
			gift_Info.setPromoter_divide(promoter_divide);
			gift_Info.setIs_box(1);
			gift_Info.setOld_gift_id(old_gift_id);
			switch (module) {
			case "1,2":
				gift_Info.setModule(0);
				break;
			case "1":
				gift_Info.setModule(1);
				break;
			case "2":
				gift_Info.setModule(2);
				break;
			}
			Date date = new Date();
			gift_Info.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				gift_Info.setId(id);
				result = igift_InfoDao.updateGiftInfo(gift_Info);
				imanage_RecordDao.insertManageRecord(w_name, "更新礼物箱信息", "gift_info", gift_Info.getId(), IPUtil.getIp(request), date);
				if(result==1){			//当礼物箱图片更新时，转盘抽奖的礼物图片也同步更新					
						idialdao.updatedialpic(gift_Info.getGift_head(),gift_Info.getId());		
				}
			}
			else
			{
				gift_Info.setCreate_time(date);
				result = igift_InfoDao.insertGiftInfo(gift_Info);
				imanage_RecordDao.insertManageRecord(w_name, "添加礼物箱信息", "gift_info", gift_Info.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存礼物箱信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除礼物</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 礼物id
	 * @param request
	 * @return
	 */
	public int deleteGiftInfo(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = igift_InfoDao.deleteGiftInfo(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除礼物", "gift_info", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除礼物",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：更新礼物上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:19:31</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 礼物id
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	public int updateGiftInfoOnline(String w_name, int id, int is_online, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = igift_InfoDao.updateGiftInfoOnline(id, is_online, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "更新礼物上线状态", "gift_info", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("更新礼物上线状态",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：送礼记录</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午4:23:42</p>
	 *
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param gift_id 礼物id
	 * @param uid 送礼者uid
	 * @param spending_uid 接收者uid
	 * @param page 页码
	 * @param page2 
	 * @param agent_id 
	 * @param model
	 * @return
	 */
	public String getSpendingList(Date s_time, Date e_time, int gift_id, String gift_name, Integer is_online, long uid, long spending_uid, int operate_center_id, int agent_id, int type,int page, Model model)
	{
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";			
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			List<Spending> spendingList = ispendingDao.getSpendingList(gift_id, gift_name, is_online, uid, spending_uid,operate_center_id,agent_id, type,s_time, e_time, (page-1)*20, 20);
			for (Spending spending : spendingList) {
				User spending_user =  iuserDao.getUser(spending.getSpending_uid());
				if(spending_user!=null){
					spending.setRev_f_uuid(Long.parseLong(spending_user.getF_uuid()));
					spending.setSpending_nickname(spending_user.getNickname());
				}
				
				User user =  iuserDao.getUser(spending.getUid());
				if(user !=null){
				spending.setSend_f_uuid(Long.parseLong(user.getF_uuid()));
				spending.setNickname(user.getNickname());
				spending.setSend_level(user.getLevel());
				}
				if(spending.getType()==4){
					Anchor_Guard_Change2 guardchange=ianchor_guard_changeDao.getAnchorGuardNewByid(spending.getGift_id());
					spending.setGift_name(guardchange.getName());			
					spending.setIs_type(1);
				}else{
				Gift_Info gift_info=igift_InfoDao.getGiftInfo(spending.getGift_id());
				int anchor_divide=gift_info.getAnchor_divide();
				int promoter_divide=gift_info.getPromoter_divide();
				spending.setAnchor_divide(anchor_divide);
				spending.setPromoter_divide(promoter_divide);			
				spending.setIs_type(0);
				}		
			}
			int totalRecord = ispendingDao.getSpendingCount(gift_id, gift_name, is_online, uid, spending_uid,operate_center_id,agent_id,type, s_time, e_time);
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
			pageUtil.setUrlName("giftspending/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("spendingList", spendingList);
			model.addAttribute("activeUrl", "giftspending");
			model.addAttribute("s_time", s_time != null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("gift_id", gift_id);
			model.addAttribute("gift_name", gift_name);
			model.addAttribute("is_online", is_online);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("type", type);
			model.addAttribute("agentList", agentList);
			model.addAttribute("operate_CenterList", operateCenterList);
			int can_export = 0;
			if(s_time!=null&&e_time!=null)
				can_export = 1;
			model.addAttribute("can_export", can_export);
			model.addAttribute("uid", uid);
			model.addAttribute("spending_uid", spending_uid);
		} catch (Exception e) {
			log.error("送礼记录", e);
		}
		return "gift/spending/list";
	}
	
	public String getSpendingStatisticsList(Date start_date,Date end_date,int type,int page,Model model){
		if(start_date==null)
		{
			int add = 0;
			Calendar calendar = new GregorianCalendar(); 
			//calendar.setTime(DateUtils.sdf.parse(DateUtils.sdf.format(new Date()))); 
			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				add=-6;
				break;		
			default:
				add = 0-calendar.get(Calendar.DAY_OF_WEEK)+2;
				break;
			}
			calendar.add(Calendar.DATE,add);
			start_date=calendar.getTime();
		}
		if(end_date==null)
			end_date = new Date();
		model.addAttribute("end_time", AuthUtil.formatDateToString(end_date,"yyyy-MM-dd"));
		Calendar calendar2 = new GregorianCalendar(); 
		calendar2.setTime(end_date); 
		calendar2.add(Calendar.DATE,1);
		end_date = calendar2.getTime();
		
		int totalRecord = 0;  
		Long summaryGiftCount = 0l;
		List<GiftSpendingStatistics> list = new ArrayList<GiftSpendingStatistics>();
		
		if ((start_date != null)&&(end_date != null)) {
			list = ispendingDao.getSpendingStatisticsList(start_date, end_date,type, (page-1)*20, 20);
		    totalRecord = ispendingDao.getGiftSpendingStatisticsCount(start_date, end_date,type);
			summaryGiftCount = ispendingDao.getSpendingStatisticsSummary(start_date, end_date,type);
			if(summaryGiftCount==null)
				summaryGiftCount = 0l;
		}

		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
		pageUtil.setTotalRecord(totalRecord);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(14);
		pageUtil.setUrlName("giftstatistics/list");
		model.addAttribute("summaryGiftCount", summaryGiftCount);
		model.addAttribute("showPage", pageUtil);
		model.addAttribute("statisticslist", list);
		model.addAttribute("activeUrl", "giftstatistics");
		model.addAttribute("start_date", start_date != null?AuthUtil.formatDateToString(start_date, "yyyy-MM-dd"):"");
		model.addAttribute("end_date", end_date != null?AuthUtil.formatDateToString(end_date, "yyyy-MM-dd"):"");	
		model.addAttribute("type", type);
		return "gift/spending/statisticslist";
	}
	
	public String exportExcel(Date s_time, Date e_time, int gift_id, String gift_name, Integer is_online, long uid, long spending_uid, int operate_center_id,int agent_id, int type,HttpServletResponse response)
	{
		try {
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			List<Spending> spendingList = ispendingDao.getSpendingList(gift_id, gift_name, is_online, uid, spending_uid, operate_center_id,agent_id,type,s_time, e_time, 0, 1000000000);
			for (Spending spending : spendingList) {
				User spending_user =  iuserDao.getUser(spending.getSpending_uid());
				spending.setSpending_nickname(spending_user.getNickname());
				User user =  iuserDao.getUser(spending.getUid());
				spending.setNickname(user.getNickname());
				spending.setSend_level(user.getLevel());
				
				if(spending.getType()==4){
					Anchor_Guard_Change2 guardchange=ianchor_guard_changeDao.getAnchorGuardNewByid(spending.getGift_id());
					spending.setGift_name(guardchange.getName());			
					spending.setIs_type(1);
				}else{
				Gift_Info gift_info=igift_InfoDao.getGiftInfo(spending.getGift_id());
				int anchor_divide=gift_info.getAnchor_divide();
				int promoter_divide=gift_info.getPromoter_divide();
				spending.setAnchor_divide(anchor_divide);
				spending.setPromoter_divide(promoter_divide);			
				spending.setIs_type(0);
				}
			}
			ServletOutputStream outputStream = response.getOutputStream();  

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"送礼时间","礼物名称","礼物种类","礼物价值（钻石）","收礼方UID","送礼方UID","送礼方等级"};
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
			response.setHeader("Content-disposition", "attachment; filename="+new String("送礼记录".getBytes(), "ISO8859_1")+".xlsx");// 组装附件名称和格式
			int j=0;
			for (Spending spending : spendingList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getCreate_time());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				if(spending.getIs_box()==0){
					cell.setCellValue(spending.getGift_name());  
				}else if(spending.getIs_box()==1){
					cell.setCellValue(spending.getGift_name()+"(宝箱)");
				}
				
				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				if(spending.getIs_type()==0){
				if(type==0){
					cell.setCellValue("普通礼物");  
				}else if(type==1){
					cell.setCellValue("特殊礼物");  
				}
				}else{
					cell.setCellValue("守护"); 
				}

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getSpending_virtual());  

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("("+spending.getSpending_nickname()+") "+spending.getSpending_uid());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("("+spending.getNickname()+") "+spending.getUid());
				
				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getSend_level());

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
			log.error("导出送礼记录", e);
		}
		return null;
	}
	
	public String exportExcel2(Date start_date,Date end_date,int type,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 00:00:00");
			String fileName = new String(("礼物统计").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"日期","礼物名称","总赠送个数"};
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
			
			int totalRecord = ispendingDao.getGiftSpendingStatisticsCount(start_date, end_date,type);
			List<GiftSpendingStatistics>  list = ispendingDao.getSpendingStatisticsList(start_date, end_date,type, 0, totalRecord);
		    
			

			// 构建表体数据  
			int j=0;
			for (GiftSpendingStatistics record  : list) {

				
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getSpending_date());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getGift_name());   


				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(record.getSpending_count());
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
			log.error("礼物统计", e);
		}
		return null;
	}

	public String getShortGiftList(Model model) {
		try {

			Short_Gift short_gift = igift_InfoDao.getShortGiftList();
			Gift_Info gift_info=igift_InfoDao.getGiftInfo(short_gift.getGift_id());
			short_gift.setGift_info(gift_info);
			model.addAttribute("short_gift", short_gift);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "shortgift");
		} catch (Exception e) {
			log.error("礼物列表", e);
		}
		return "gift/short/list";
	}

	public String editShortGift(int id, Model model) {
		try{
		Short_Gift short_gift=igift_InfoDao.getShortGiftByid(id);		
		Gift_Info gift_Info = igift_InfoDao.getGiftInfo(short_gift.getGift_id());
		List<Gift_Info> gift_info_list=igift_InfoDao.getAllGiftInfo();
		short_gift.setGift_info(gift_Info);
		model.addAttribute("short_gift", short_gift);
		model.addAttribute("gift_id", short_gift.getGift_id());
		model.addAttribute("gift_info_list", gift_info_list);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "shortgift");
		}catch(Exception e){
			log.error("快捷礼物编辑页面", e);
		}
		return "gift/short/edit";
	}

	public String getGiftInfo(int gift_id) {
		JSONObject jsonObject = new JSONObject();	
		try {
			Gift_Info giftinfo=igift_InfoDao.getGiftInfo(gift_id);		
			jsonObject.put("data", giftinfo);//获取到礼物图片
			jsonObject.put("url", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("快捷礼物--礼物表详情", e);
		}
		return jsonObject.toString();
	}

	public String saveShortGift(int id, int gift_id, String gift_pic, String w_name,String remark, HttpServletRequest request) {
		try{	
			Short_Gift short_gift = new Short_Gift();
			short_gift.setId(gift_id);
			short_gift.setGift_id(gift_id);
			short_gift.setGift_pic(gift_pic);		
			Date date = new Date();
			short_gift.setUpdate_date(date);
			w_name = String.valueOf(request.getAttribute("w_name"));
			short_gift.setW_name(w_name);
			short_gift.setRemark(remark);
			int result = 0;
			if(id>0)
			{
				short_gift.setId(id);
				result = igift_InfoDao.updateShortGift(short_gift);
				imanage_RecordDao.insertManageRecord(w_name, "更新快捷礼物信息", "t_short_gift", short_gift.getId(), IPUtil.getIp(request), date);
			}			
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存礼物信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

}

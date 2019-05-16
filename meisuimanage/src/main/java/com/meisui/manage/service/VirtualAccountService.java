package com.meisui.manage.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IRechargeQuotaDao;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IspendingDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Ivirtual_AccountDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Profit_Virtual;
import com.meisui.manage.entity.Recharge_Quota_Virtual;
import com.meisui.manage.entity.Recharge_Virtual;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Virtual_Record;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class VirtualAccountService {
	private static Logger log = Logger.getLogger(VirtualAccountService.class.getClass());

	@Autowired
	private Ivirtual_AccountDao ivirtual_accountDao;
	@Autowired
	private Ioperate_CenterDao operate_CenterDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IRechargeQuotaDao rechargequotadao;
	@Autowired
	private IspendingDao ispendingDao;
	@Autowired
	private Iagent_PromoterDao iagent_promoterdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;

	public String virtualrechargerecord(long uid, long f_uuid, int operate_centerid, String begin_time, String end_time,  int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Recharge_Virtual> list = ivirtual_accountDao.getRechargeVirtualList(uid,f_uuid,operate_centerid,b_time,e_time,(page - 1) * 20 ,20);
			int totalRecord = ivirtual_accountDao.getRechargeVirtualListCount(uid,f_uuid,operate_centerid,b_time,e_time);
			for(Recharge_Virtual virtual:list){				
				User user=iuserDao.getUser((long)virtual.getUid());			
				virtual.setNickname(user.getNickname());
				Operate_Center operatecenter=operate_CenterDao.getOperateCenterById(virtual.getOperate_center_id());
				virtual.setOperate_name(operatecenter.getName());
			}
			List<Operate_Center> operate=operate_CenterDao.getOperateCenter();		
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("virtual/virtualrechargerecord");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "virtualrechargerecord");
			model.addAttribute("list", list);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("operate_centerid", operate_centerid);
			model.addAttribute("operate", operate);
		}catch (Exception e) {
			log.error("会员充值操作记录错误", e);
		}
		return "virtual/rechargerecord";
	}

	public int virtualrecharge( int centerid, long virtual_count,String remark, HttpServletRequest request, Model model) {
		int result = 0;
		try{
			Date date=new Date();
			String w_name = String.valueOf(request.getAttribute("w_name"));
			result=operate_CenterDao.updateRechargeQuota(centerid,virtual_count);
			if(result>0){
				Recharge_Quota_Virtual recharge_quota_virtual=new Recharge_Quota_Virtual();

				recharge_quota_virtual.setRechargequota(virtual_count);
				recharge_quota_virtual.setCreate_time(date);
				recharge_quota_virtual.setW_name(w_name);
				recharge_quota_virtual.setRemark(remark);
				recharge_quota_virtual.setOperate_center_id(centerid);
				rechargequotadao.insertRecord(recharge_quota_virtual);
				return result;
			}			
		}catch(Exception e){
			log.error("会员充值错误",e);
		}	
		return result;
	}

	public String rechargequotarecord(int operate_centerid, String begin_time, String end_time, int page, Model model) {

		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Recharge_Quota_Virtual> list = new ArrayList<Recharge_Quota_Virtual>(20);
			list = rechargequotadao.getRechargeQuotaVirtualList( b_time, e_time, (page - 1) * 20, 20, operate_centerid);
			int totalRecord = rechargequotadao.getRechargeQuotaVirtualListCount(b_time, e_time, operate_centerid);
			for(Recharge_Quota_Virtual recharge:list){
				Operate_Center opreatecenter=operate_CenterDao.getoperateName(recharge.getOperate_center_id());
				recharge.setOperate_name(opreatecenter.getName());
			}
			List<Operate_Center> operate=operate_CenterDao.getOperateCenter();			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("virtual/rechargequotarecord");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "rechargequotarecord");
			model.addAttribute("list", list);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("operate_centerid", operate_centerid);
			model.addAttribute("operate", operate);
		}catch (Exception e) {
			log.error("会员充值操作记录错误", e);
		}
		return "virtual/rechargequotarecord";
	}

	public String getGiftRecord(Date s_time, Date e_time, int gift_id, String gift_name, long uid, long send_uid,int re_operate_center_id,int send_operate_center_id,int send_agent_id,int re_agent_id,
			int page, Model model) {
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			List<Profit_Virtual> virtualList = ispendingDao.getVirtualList(gift_id, gift_name,uid, send_uid,re_operate_center_id,re_agent_id,send_operate_center_id, send_agent_id,s_time, e_time, (page-1)*20, 20);
			for (Profit_Virtual list : virtualList) {
				//送礼方
				User send_user =  iuserDao.getUser(list.getSend_uid());
				list.setSend_f_uuid(Long.parseLong(send_user.getF_uuid()));
				list.setSend_nickname(send_user.getNickname());
				list.setSend_level(send_user.getLevel());
				//送礼方所属运营
				Operate_Center sendoperatecenter=operate_CenterDao.getOperateCenterById(send_user.getOperate_center_id());
				list.setSend_operate_name(sendoperatecenter.getName());
				//送礼方所属代理
				Agent sagent=iagentdao.getAgent(send_user.getAgent_id());
				list.setSend_agent_name(sagent.getName());
				//收礼方(主播uid)
				User user =  iuserDao.getUser(list.getUid());
				list.setRev_f_uuid(Long.parseLong(user.getF_uuid()));
				list.setRev_nickname(user.getNickname());	
				//收礼方所属运营
				Operate_Center reoperatecenter=operate_CenterDao.getOperateCenterById(user.getOperate_center_id());
				list.setRe_operate_name(reoperatecenter.getName());
				//收礼方所属代理
				Agent ragent=iagentdao.getAgent(user.getAgent_id());
				list.setRe_agent_name(ragent.getName());
			}
			List<Operate_Center> operateCenterList = operate_CenterDao.getListAll();
			List<Agent> ReagentList = new ArrayList<>();//收礼方所属代理
			List<Agent> SendagentList = new ArrayList<>();//送礼方所属代理
			if(re_operate_center_id>0)
			{
				ReagentList = iagentdao.getAgentListByCenterId(re_operate_center_id);
			}
			if(send_operate_center_id>0)
			{
				SendagentList = iagentdao.getAgentListByCenterId(send_operate_center_id);
			}

			int totalRecord = ispendingDao.getVirtualCount(gift_id, gift_name, uid, send_uid,re_operate_center_id,re_agent_id,send_operate_center_id, send_agent_id, s_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("virtual/giftrecord");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("virtualList", virtualList);
			model.addAttribute("activeUrl", "virtual");
			model.addAttribute("s_time", s_time != null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("gift_id", gift_id);
			model.addAttribute("gift_name", gift_name);
			int can_export = 0;
			if(s_time!=null&&e_time!=null)
				can_export = 1;
			model.addAttribute("can_export", can_export);
			model.addAttribute("uid", uid);
			model.addAttribute("send_uid", send_uid);
			model.addAttribute("operateCenterList", operateCenterList);
			model.addAttribute("ReagentList", ReagentList);
			model.addAttribute("SendagentList", SendagentList);
			model.addAttribute("re_operate_center_id", re_operate_center_id);
			model.addAttribute("send_operate_center_id", send_operate_center_id);
			model.addAttribute("send_agent_id", send_agent_id);
			model.addAttribute("re_agent_id", re_agent_id);

		} catch (Exception e) {
			log.error("会员送礼记录", e);
		}
		return "virtual/giftrecord";
	}

	public String GiftRecordExportExcel(Date s_time, Date e_time, int gift_id, String gift_name, long uid, long send_uid, int re_operate_center_id,int send_operate_center_id,int send_agent_id,int re_agent_id,
			HttpServletResponse response) {
		try {
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}			
			List<Profit_Virtual> virtualList = ispendingDao.getVirtualList(gift_id, gift_name, uid, send_uid,re_operate_center_id,re_agent_id,send_operate_center_id, send_agent_id, s_time, e_time, 0, 1000000000);			
			for (Profit_Virtual list : virtualList) {
				//送礼方
				User send_user =  iuserDao.getUser(list.getSend_uid());
				list.setSend_f_uuid(Long.parseLong(send_user.getF_uuid()));
				list.setSend_nickname(send_user.getNickname());
				list.setSend_level(send_user.getLevel());
				//送礼方所属运营
				Operate_Center sendoperatecenter=operate_CenterDao.getOperateCenterById(send_user.getOperate_center_id());
				list.setSend_operate_name(sendoperatecenter.getName());
				//送礼方所属代理
				Agent sagent=iagentdao.getAgent(send_user.getAgent_id());
				list.setSend_agent_name(sagent.getName());
				//收礼方(主播uid)
				User user =  iuserDao.getUser(list.getUid());
				list.setRev_f_uuid(Long.parseLong(user.getF_uuid()));
				list.setRev_nickname(user.getNickname());	
				//收礼方所属运营
				Operate_Center reoperatecenter=operate_CenterDao.getOperateCenterById(user.getOperate_center_id());
				list.setRe_operate_name(reoperatecenter.getName());
				//收礼方所属代理
				Agent ragent=iagentdao.getAgent(send_user.getAgent_id());
				list.setRe_agent_name(ragent.getName());				
			}
			ServletOutputStream outputStream = response.getOutputStream();  

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"礼物ID","礼物名","礼物价值（钻石）","收礼方所属运营中心","收礼方所属代理","收礼方UID","收礼方房间号","送礼方所属运营中心","送礼方所属代理","送礼方UID","送礼方房间号","送礼方等级","送礼时间"};
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
			for (Profit_Virtual spending : virtualList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getGift_id());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				if(spending.getIs_box()==0){
					cell.setCellValue(spending.getGift_name()); 
				}else if(spending.getIs_box()==1){
					cell.setCellValue(spending.getGift_name()+"(宝箱)"); 
				}
				 

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getGift_virtual());  

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getRe_operate_name());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getRe_agent_name());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("("+spending.getRev_nickname()+")"+spending.getUid());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getRev_f_uuid());  

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getSend_operate_name());  

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getSend_agent_name());  

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("("+spending.getSend_nickname()+")"+spending.getSend_uid());  

				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getSend_f_uuid());  

				cell = bodyRow.createCell(11);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(spending.getSend_level());  

				cell = bodyRow.createCell(12);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(sdf.format(spending.getCreate_time()));  

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
			log.error("导出会员送礼记录", e);
		}
		return null;
	}

	public String virtuallist(long uid, long f_uuid, String nickname,int operate_center_id,int agent_id,int page,
			Model model) {
		try {
			List<User> list=iuserDao.getVirtualUserList(operate_center_id,agent_id,uid,f_uuid,nickname,(page - 1) * 20, 20);
			int totalRecord=iuserDao.getVirtualUserListCount(operate_center_id,agent_id,uid,f_uuid,nickname);
			List<Operate_Center> operateCenterList = operate_CenterDao.getListAll();
			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentdao.getAgentListByCenterId(operate_center_id);
			}
			for(User user:list){	
				int operateid=user.getOperate_center_id();
				String operateName="";
				if(operateid>0){
					Operate_Center iperate_center=operate_CenterDao.getoperateName(operateid);
					if(iperate_center !=null)
						operateName=iperate_center.getName();
					user.setOperateName(operateName);
				}
				//邀请所属代理平台
				String agentName="";
				if(user.getAgent_id()>0){
					Agent agent=iagentdao.getAgentNameByagentid(user.getAgent_id());
					if(agent!=null)
						agentName=agent.getName();
					user.setAgentName(agentName);
				}
				//邀请所属推广员
				int result = iagent_promoterdao.getpromoterByUid(user.getId());
				user.setIs_promote(result>0?1:0);			
			}			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("virtual/virtuallist");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "virtuallist");
			model.addAttribute("list", list);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("operateCenterList", operateCenterList);
			model.addAttribute("agentList", agentList);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("operate_center_id", operate_center_id);

		}catch (Exception e) {
			log.error("会员列表", e);
		}
		return "/virtual/virtuallist";
	}

	public String RechargeRecordExcel(String begin_time, String end_time, long uid, long f_uuid, int operate_centerid,
			HttpServletResponse response) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}		
			List<Recharge_Virtual> list = ivirtual_accountDao.getRechargeVirtualList(uid,f_uuid,operate_centerid,b_time,e_time,0 ,1000000000);

			for(Recharge_Virtual virtual:list){				
				User user=iuserDao.getUser((long)virtual.getUid());			
				virtual.setNickname(user.getNickname());
				Operate_Center operatecenter=operate_CenterDao.getOperateCenterById(virtual.getOperate_center_id());
				virtual.setOperate_name(operatecenter.getName());
			}
			ServletOutputStream outputStream = response.getOutputStream();  

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"用户ID","用户房间号","昵称","充值数额","所属运营中心","充值时间","充值人"};
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
			for (Recharge_Virtual recharge : list) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getF_uuid());  

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getNickname());  

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getVirtual_count());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getOperate_name());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getCreate_time());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getW_name());  

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
			log.error("导出会员充值日志", e);
		}
		return null;
	}

	public String UserVirtualRecord(String begin_time, String end_time, int page,Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<User_Virtual_Record> virtuallist=ivirtual_accountDao.getUserVirtualRecordAll(b_time,e_time,(page - 1) * 20 ,20);
			int totalRecord = ivirtual_accountDao.getUserVirtualRecordAllCount(b_time,e_time);	
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("virtual/uservirtualrecord");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "virtuallist");
			model.addAttribute("virtuallist", virtuallist);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
		}catch (Exception e) {
			log.error("会员添加日志", e);
		}
		return "virtual/uservirtualrecharge";
	}

	public String RechargeQuotaRecordExcel(int operate_centerid, String begin_time, String end_time,
			HttpServletResponse response) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}		
			List<Recharge_Quota_Virtual> list = new ArrayList<Recharge_Quota_Virtual>(20);
			list = rechargequotadao.getRechargeQuotaVirtualList( b_time, e_time, 0, 1000000000, operate_centerid);
			for(Recharge_Quota_Virtual recharge:list){
				Operate_Center opreatecenter=operate_CenterDao.getoperateName(recharge.getOperate_center_id());
				recharge.setOperate_name(opreatecenter.getName());
			}
			ServletOutputStream outputStream = response.getOutputStream();  

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"运用中心ID","运营中心","充值额度(钻)","备注","充值时间","操作人"};
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
			for (Recharge_Quota_Virtual recharge : list) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getOperate_center_id());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getOperate_name());  

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getRechargequota());  

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getRemark());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getCreate_time());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getW_name());  

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
			log.error("导出会员额度充值流水", e);
		}
		return null;
	}

	public String rechargequota(int operate_center_id, String operate_name, int page, Model model) {
		try{
			List<Operate_Center> list = ivirtual_accountDao.getOperateCenterList(operate_center_id,operate_name,(page - 1) * 20 ,20);
			int totalRecord = ivirtual_accountDao.getOperateCenterCount(operate_center_id,operate_name);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("virtual/rechargequota");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("list", list);	
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("operate_name", operate_name);		
			model.addAttribute("activeUrl", "rechargequota");
		} catch (Exception e) {
			log.error("会员号充值", e);
		}
		return "virtual/rechargequota";
	}

	public String rechargeQuota(int id, long add, String remark, HttpServletRequest request)
	{
		try
		{
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			int result = operate_CenterDao.updateRechargeQuota(id, add);
			if(result>0)
			{
				Recharge_Quota_Virtual recharge_Quota_Virtual = new Recharge_Quota_Virtual();
				recharge_Quota_Virtual.setCreate_time(date);
				recharge_Quota_Virtual.setOperate_center_id(id);
				recharge_Quota_Virtual.setW_name(w_name);
				recharge_Quota_Virtual.setRechargequota(add);
				recharge_Quota_Virtual.setRemark(remark);
				rechargequotadao.insertRecord(recharge_Quota_Virtual);
			}
			return "{\"code\":0,\"msg\":\"保存成功\"}";	
		}
		catch (Exception e) {
			log.error("运营中心充值虚拟号额度",e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";	
	}
	public String Addvirtual(long f_uuid,HttpServletRequest request, Model model) {
		JSONObject jsonObject = new JSONObject();
		try {
			String w_name=String.valueOf(request.getAttribute("w_name"));
			Date date=new Date();
			User user = iuserDao.getUserByF_uuid(f_uuid);
			if(user.getIs_virtual()==1)
			{
				jsonObject.put("code", -3);
				List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
				mapList.add(AuthUtil.getMap("f_uuid", String.format("此房间号：%s已添加请勿重复操作", f_uuid)));
				jsonObject.put("msg", JSONArray.fromObject(mapList));
				return jsonObject.toString();
			}
			else
			{
				int result = 0;
				if(f_uuid > 0)
					result = iuserDao.updatevirtual(f_uuid);
				if(result>0){
					imanage_RecordDao.insertManageRecord(w_name, "添加会员", "user", (int)user.getId(), IPUtil.getIp(request), date);
					iuserDao.insertUserVirtualRecord(user.getId(),w_name,date,0);
					jsonObject.put("code", 1);
				}
			}

		} catch (Exception e) {
			log.error("添加会员", e);
		}

		return jsonObject.toString();
	}

	public int updateUserIsVirtual(long id, int is_virtual, String w_name, HttpServletRequest request) {
		int result = 0;
		try {	
			result = iuserDao.updateUserIsVirtual(is_virtual, id);
			if (result > 0) {
				w_name = String.valueOf(request.getAttribute("w_name"));
				imanage_RecordDao.insertManageRecord(w_name, "取消会员账号：", "user", (int)id, IPUtil.getIp(request), new Date());
				String token = iuserDao.getUserInfoExtraToken(id);
				RedisUtil.SetHsetJedis(0, token, "is_virtual", String.valueOf(is_virtual));
				iuserDao.insertUserVirtualRecord(id, w_name, new Date(), is_virtual==1?0:1);
			}
		} catch (Exception e) {
			log.error("修改用户是否为会员账号", e);
		}
		return result;
	}

}

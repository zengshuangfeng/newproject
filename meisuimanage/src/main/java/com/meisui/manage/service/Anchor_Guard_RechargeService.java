package com.meisui.manage.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
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
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.Ianchor_Guard_RechargeDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Anchor_Guard_Change;
import com.meisui.manage.entity.Anchor_Guard_Recharge;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class Anchor_Guard_RechargeService {
	private static Logger log = Logger.getLogger(Anchor_Guard_RechargeService.class.getClass());
	@Autowired
	private Ianchor_Guard_RechargeDao ianchor_Guard_RechargeDao;
	@Autowired
	private Ianchor_Guard_ChangeDao ianchor_Guard_ChangeDao;
	@Autowired
	private Iagent_PromoterDao iagent_promoterdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerdao;
	@Autowired
	private IuserDao iuserDao;
	public String getList(long uid, long f_uuid, long anchor_f_uuid, int pay_type, String nickname, String o_id, String platform_channel, int is_pay, Date start_time, Date end_time, int operate_center_id, int agent_id,String admin, int page, Model model)
	{
		try
		{
			String e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			String channel = "";
			String platform = "";
			if(!StringUtils.isBlank(platform_channel))
			{
				String[] platform_channelStrings = platform_channel.split("_");
				platform = platform_channelStrings[0];
				channel  = platform_channelStrings[1];
			}
			List<Anchor_Guard_Recharge> anchor_Guard_RechargeList = ianchor_Guard_RechargeDao.getAnchorGuardRechargeList(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id,agent_id,(page-1)*20, 20);
			int totalRecord = ianchor_Guard_RechargeDao.getAnchorGuardRechargeCount(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id,agent_id);
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
			List<Anchor_Guard_Change> anchor_Guard_ChangeList = ianchor_Guard_ChangeDao.getGuardChangeList(0, 10);
			for(Anchor_Guard_Recharge recharge:anchor_Guard_RechargeList){
				User user=iuserDao.getUserByUid((long)recharge.getUid());
				//邀请所属运营中心名称
				if(user.getOperate_center_id()>0){
					for (Operate_Center operate_Center : operateCenterList) {
						if(operate_Center.getId()==user.getOperate_center_id())
						{
							recharge.setOperateName(operate_Center.getName());
							break;
						}
					}
				}
				//邀请所属代理平台
				if(user.getAgent_id()>0){
					Agent agent=iagentdao.getAgentNameByagentid(user.getAgent_id());
					if(agent!=null)
						recharge.setAgentName(agent.getName());			
				}
				//邀请所属推广员		
				if(user.getAgent_promoter_id()>0){
					Agent_Promoter agent_promoter=iagent_promoterdao.getProNameByid(user.getAgent_promoter_id());
					if(agent_promoter!=null){
						long apuid=agent_promoter.getUid();
						recharge.setProName(iuserDao.getUserNickNameByapuid(apuid));					
					}					
				}
				for (Anchor_Guard_Change anchor_Guard_Change : anchor_Guard_ChangeList) {
					if(anchor_Guard_Change.getId()==recharge.getGuard_change_id())
					{
						recharge.setGuard_type(anchor_Guard_Change.getType());
						break;
					}
				}
				User user2 = iuserDao.getUserByF_uuid(recharge.getAnchor_f_uuid());
				recharge.setAnchor_nickname(user2.getNickname());
			}

			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentdao.getAgentListByCenterId(operate_center_id);
			}

			//支付总金额
			long sumrecharge= ianchor_Guard_RechargeDao.getAnchorGuardRechargeSum(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id,agent_id);
			int operate_center_divide = 0, agent_divide=0;
			double operate_center_rmb=0d, agent_rmb=0d;
			if(is_pay==1&&operate_center_id>0)
			{
				Operate_Center operate_Center = ioperate_centerdao.getOperateCenterById(operate_center_id);
				operate_center_divide = operate_Center.getDivide();
				if(agent_id>0)
				{
					Agent agent = iagentdao.getAgent(agent_id);
					agent_divide = agent.getDivide();
				}
			}
			operate_center_rmb = DoubleUtil.mul((double)sumrecharge, DoubleUtil.div(operate_center_divide, 100, 2));
			agent_rmb = DoubleUtil.mul((double)sumrecharge, DoubleUtil.div(agent_divide, 100, 2));
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("guardrecharge/list");
			model.addAttribute("rechargeList", anchor_Guard_RechargeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "guardrecharge");
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("anchor_f_uuid", anchor_f_uuid);
			model.addAttribute("pay_type", pay_type);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("o_id", o_id);
			model.addAttribute("platform_channel", platform_channel);
			model.addAttribute("is_pay", is_pay);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			model.addAttribute("sumrecharge", sumrecharge);
			model.addAttribute("operate_CenterList", operateCenterList);
			model.addAttribute("agentList", agentList);
			model.addAttribute("operate_center_divide", operate_center_divide);
			model.addAttribute("operate_center_rmb", operate_center_rmb);
			model.addAttribute("agent_divide", agent_divide);
			model.addAttribute("agent_rmb", agent_rmb);
		}
		catch (Exception e) {
			log.error("守护订单列表", e);
		}
		return "guardrecharge/list";
	}
	public String exportExcel(long uid, long f_uuid, long anchor_f_uuid, int pay_type, String nickname, String o_id, String platform_channel, int is_pay, int operate_center_id, int agent_id,Date start_time, Date end_time, int type, HttpServletResponse response)
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			ServletOutputStream outputStream = response.getOutputStream();  
			if(start_time==null || end_time==null){
				String fileName = new String(("守护充值记录").getBytes(), "ISO8859_1");  
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			}else{
				String fileName = new String(("守护充值记录"+sdf2.format(start_time)+"~"+sdf2.format(end_time)).getBytes(), "ISO8859_1");  
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			}

			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			String channel = "";
			String platform = "";

			if(!StringUtils.isBlank(platform_channel))
			{
				String[] platform_channelStrings = platform_channel.split("_");
				platform = platform_channelStrings[0];
				channel  = platform_channelStrings[1];
			}


			List<Anchor_Guard_Recharge> anchor_Guard_RechargeList = ianchor_Guard_RechargeDao.getAnchorGuardRechargeList(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id,agent_id,0, 1000000000);
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
			List<Anchor_Guard_Change> anchor_Guard_ChangeList = ianchor_Guard_ChangeDao.getGuardChangeList(0, 10);

			//支付总金额
			long sumrecharge= ianchor_Guard_RechargeDao.getAnchorGuardRechargeSum(uid, f_uuid, anchor_f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id, agent_id);
			int operate_center_divide = 0, agent_divide=0;
			double operate_center_rmb=0d, agent_rmb=0d;
			if(is_pay==1&&operate_center_id>0)
			{
				Operate_Center operate_Center = ioperate_centerdao.getOperateCenterById(operate_center_id);
				operate_center_divide = operate_Center.getDivide();
				if(agent_id>0)
				{
					Agent agent = iagentdao.getAgent(agent_id);
					agent_divide = agent.getDivide();
				}
			}
			operate_center_rmb = DoubleUtil.mul((double)sumrecharge, DoubleUtil.div(operate_center_divide, 100, 2));
			agent_rmb = DoubleUtil.mul((double)sumrecharge, DoubleUtil.div(agent_divide, 100, 2));

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"订单编号","用户","守护时间","守护的主播","支付金额（元）","第三方交易单号","支付方式","所属运营中心","所属代理平台","所属推广员","支付时间"};


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
			for(Anchor_Guard_Recharge recharge:anchor_Guard_RechargeList){
				User user=iuserDao.getUserByUid((long)recharge.getUid());
				//邀请所属运营中心名称
				if(user.getOperate_center_id()>0){
					for (Operate_Center operate_Center : operateCenterList) {
						if(operate_Center.getId()==user.getOperate_center_id())
						{
							recharge.setOperateName(operate_Center.getName());
							break;
						}
					}
				}
				//邀请所属代理平台
				if(user.getAgent_id()>0){
					Agent agent=iagentdao.getAgentNameByagentid(user.getAgent_id());
					if(agent!=null)
						recharge.setAgentName(agent.getName());			
				}
				//邀请所属推广员		
				if(user.getAgent_promoter_id()>0){
					Agent_Promoter agent_promoter=iagent_promoterdao.getProNameByid(user.getAgent_promoter_id());
					if(agent_promoter!=null){
						long apuid=agent_promoter.getUid();
						recharge.setProName(iuserDao.getUserNickNameByapuid(apuid));					
					}					
				}
				for (Anchor_Guard_Change anchor_Guard_Change : anchor_Guard_ChangeList) {
					if(anchor_Guard_Change.getId()==recharge.getGuard_change_id())
					{
						recharge.setGuard_type(anchor_Guard_Change.getType());
						break;
					}
				}
				User user2 = iuserDao.getUserByF_uuid(recharge.getAnchor_f_uuid());
				recharge.setAnchor_nickname(user2.getNickname());
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getO_id());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("UID："+recharge.getUid()+"\n房间号："+recharge.getF_uuid()+"\n昵称："+recharge.getNickname());  

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("开始时间："+sdf2.format(recharge.getStart_time())+"\n结束时间："+sdf2.format(recharge.getEnd_time()));  

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("房间号："+recharge.getAnchor_f_uuid()+"\n昵称："+recharge.getAnchor_nickname());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getRecharge_rmb());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getPay_trade_no());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				if(recharge.getIs_pay()==1)
				{
					switch (recharge.getPay_type()) {
					case 0:
						cell.setCellValue("支付宝");
						break;
					case 1:
						cell.setCellValue("微信");
						break;
					}
				}
				else {
					cell.setCellValue("");
				}

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getOperateName());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getAgentName());

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(recharge.getProName());

				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(sdf.format(recharge.getCreate_time()));

				j++;
			}

			XSSFRow bodyRow = sheet.createRow(j + 2); 

			cell = bodyRow.createCell(0);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue("总计:");  

			cell = bodyRow.createCell(1);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue("");  

			cell = bodyRow.createCell(2);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue("");  

			cell = bodyRow.createCell(3);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue("");

			cell = bodyRow.createCell(4);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(sumrecharge+"元");

			cell = bodyRow.createCell(5);  
			cell.setCellStyle(bodyStyle);  
			if(is_pay==1&&operate_center_id>0)
			{
				cell.setCellValue("运营中心分成："+operate_center_divide+"%");
			}

			cell = bodyRow.createCell(6);  
			cell.setCellStyle(bodyStyle);  
			if(is_pay==1&&operate_center_id>0)
			{
				cell.setCellValue("运营中心收益："+operate_center_rmb+"元");
			}

			cell = bodyRow.createCell(7);  
			cell.setCellStyle(bodyStyle);  
			if(is_pay==1&&agent_id>0)
			{
				cell.setCellValue("代理分成："+agent_divide+"%");
			}

			cell = bodyRow.createCell(8);  
			cell.setCellStyle(bodyStyle);  
			if(is_pay==1&&agent_id>0)
			{
				cell.setCellValue("代理收益："+agent_rmb+"元");
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
			log.error("导出守护充值", e);
		}
		return null;
	}
}

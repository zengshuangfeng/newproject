package com.meisui.manage.service;

import java.io.IOException;
import java.text.DecimalFormat;
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

import com.forman.foundation.library.ThreeDESUtil;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IrechargeDao;
import com.meisui.manage.dao.Irecharge_ChannelDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_deviceDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Balance_virtualrecord;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Recharge;
import com.meisui.manage.entity.RechargeStatistics;
import com.meisui.manage.entity.Recharge_Channel;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：RechargeService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午4:44:35
 */
@Service
public class RechargeService {
	private static Logger log = Logger.getLogger(RechargeService.class.getClass());
	@Autowired
	private IrechargeDao irechargeDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Irecharge_ChannelDao irecharge_ChannelDao;
	@Autowired
	private Iuser_deviceDao iuser_deviceDao;
	@Autowired
	private Iagent_PromoterDao iagent_promoterdao;
	@Autowired
	private IagentDao iagentdao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerdao;
	/**
	 * 
	 * <p>功能描述：充值记录列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午4:47:42</p>
	 *
	 * @param uid 用户id
	 * @param pay_type 付款方式 0:支付宝 1:微信
	 * @param nickname 昵称
	 * @param o_id 订单号
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getRechargeList(long uid, long f_uuid, int pay_type, String nickname, String o_id, String platform_channel, int is_pay, Date start_time, Date end_time, int operate_center_id, int agent_id,int pay_virtual,String admin, int page, Model model)
	{
		try {
			String channel = "";
			String platform = "";
			//支付总金额
			long sumrecharge=0L;
			//支付总钻石
			long sumvirtual=0L;
			if(!StringUtils.isBlank(platform_channel))
			{
				String[] platform_channelStrings = platform_channel.split("_");
				platform = platform_channelStrings[0];
				channel  = platform_channelStrings[1];
			}
			List<Recharge> rechargeList = irechargeDao.getRechargeList(uid, f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id,agent_id,pay_virtual,(page-1)*20, 20);
			int totalRecord = irechargeDao.getRechargeCount(uid, f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id,agent_id,pay_virtual);
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
			for(Recharge recharge:rechargeList){
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
			}
			
			List<Recharge> sumrechargeList = irechargeDao.getSumRecharge(uid, f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_center_id, agent_id,pay_virtual);
			for(Recharge recharge:sumrechargeList){				
				//支付总金额
				long rechargermb=recharge.getRecharge_rmb();			
				 sumrecharge +=rechargermb;
				//支付总钻石
				long changevirtual=recharge.getChange_virtual();
				sumvirtual +=changevirtual;
			}
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
			pageUtil.setUrlName("recharge/list");
			model.addAttribute("rechargeList", rechargeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "recharge");
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("pay_type", pay_type);
			model.addAttribute("pay_virtual", pay_virtual);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("o_id", o_id);
			model.addAttribute("platform_channel", platform_channel);
			model.addAttribute("is_pay", is_pay);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd HH:mm:ss"):"");
			model.addAttribute("end_time", end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd HH:mm:ss"):"");
			model.addAttribute("sumrecharge", sumrecharge);
			model.addAttribute("sumvirtual", sumvirtual);
			model.addAttribute("operate_CenterList", operateCenterList);
			model.addAttribute("agentList", agentList);
			
			int is_admin = 0;
			String admins = ThreeDESUtil.decode(admin, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));	
			String[] adminStrings = admins.split(",");
			for (String string : adminStrings) {
				if(string.equals("sky")||string.equals("yl0815"))
					is_admin = 1;
			}
			model.addAttribute("is_admin", is_admin);
		} catch (Exception e) {
			log.error("充值记录列表", e);
		}
		return "recharge/list";
	}

	public String getBalance_virtualrecordList(long uid, long f_uuid, String s_time,String e_time,String diamondsa,String diamondsb,String nickname,Integer page, Model model)
	{
		//(@Param("uid")Long uid,@Param("s_time")String s_time,@Param("e_time")String e_time,@Param("diamondsa")String diamondsa,@Param("diamondsb")String diamondsb,@Param("family")String family,@Param("nickname")String nickname,@Param("offset")int offset, @Param("rows")int rows);
		try {
			if(diamondsb.equals("-1"))
				diamondsb = "";
			if(f_uuid>0)
				uid = iuserDao.getUserIdWithF_uuid(String.valueOf(f_uuid));
			String e_timeString = e_time;
			if(!StringUtils.isBlank(e_time))
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(AuthUtil.formatStringToDate(e_time,"yyyy-MM-dd")); 
				calendar.add(Calendar.DATE,1);
				e_time = AuthUtil.formatDateToString(calendar.getTime(),"yyyy-MM-dd");
			}
			List<Balance_virtualrecord> balance_virtualrecordList = irechargeDao.getBalance_virtualrecordList(uid, s_time, e_time, diamondsa, diamondsb,nickname,(page-1)*20, 20);
			for (Balance_virtualrecord balance_virtualrecord : balance_virtualrecordList) {
				User user = iuserDao.getUser(balance_virtualrecord.getUid());
				if(user!=null){
					balance_virtualrecord.setF_uuid(user.getF_uuid());
					balance_virtualrecord.setLevel(user.getLevel());
					Long hasDeviceInfo = iuser_deviceDao.getIdByUid(user.getId());
					if ((hasDeviceInfo!=null)&&(hasDeviceInfo>0)) {
						balance_virtualrecord.setHasDeviceInfo(1);
					}else {
						balance_virtualrecord.setHasDeviceInfo(0);
					}
				}
			}
			int totalRecord = irechargeDao.getBalance_virtualrecordCount(uid, s_time, e_time, diamondsa, diamondsb,nickname);
			if(diamondsb.equals(""))
				diamondsb = "-1";
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("balancevirtual/list");
			model.addAttribute("balance_virtualrecordListList", balance_virtualrecordList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("s_time", s_time);
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("diamondsa", diamondsa);
			model.addAttribute("diamondsb", diamondsb);
			model.addAttribute("nickname", nickname);			
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("activeUrl", "balancevirtual");
		} catch (Exception e) {
			log.error("赠送记录列表", e);
		}
		return "recharge/list2";
	}
	
	public String getStatisticsList(Date start_time,Date end_time,int statistics_type,String channel_platform,int recharge_type,Integer page,Model model){
		String channel = "";
		Integer platform = null;
		
        model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd HH:mm:ss"):"");
        model.addAttribute("end_time", end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd HH:mm:ss"):"");
        model.addAttribute("statistics_type", statistics_type);
        model.addAttribute("channel_platform", channel_platform);
        model.addAttribute("recharge_type", recharge_type);
        
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
		
		RechargeStatistics summary = irechargeDao.getStatisticsSummary(start_time, end_time, statistics_type, channel,platform,recharge_type);
		
		int totalCount = irechargeDao.getStatisticsCount(start_time, end_time, statistics_type, channel,platform,recharge_type);
		
		List<Recharge_Channel> recharge_ChannelList = irecharge_ChannelDao.getRecharge_ChannelList();
		
		PageUtil pageUtil = new PageUtil(20, totalCount, page);
		pageUtil.setTotalRecord(totalCount);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(14);		
		pageUtil.setUrlName("rechargestatistics/list");

		List<RechargeStatistics> list = irechargeDao.getStatisticsList(start_time, end_time, statistics_type, channel,platform,recharge_type,(page-1)*20,20);
		
		if (summary == null) {
			summary = new RechargeStatistics();
		}
		
		if ((summary.getRecharge_money()==null)||(summary.getVirtual_money()==null)) {
			summary.setRecharge_money(0l);
			summary.setVirtual_money(0l);
		}
		model.addAttribute("summary", summary);
		model.addAttribute("channellist", recharge_ChannelList);
		model.addAttribute("showPage", pageUtil);
		model.addAttribute("statisticsList", list);
		model.addAttribute("activeUrl", "rechargestatistics");
		return "recharge/statisticslist";
	}
	
	
	
	public String exportExcel(long uid, long f_uuid, int pay_type, String nickname, String o_id, String platform_channel, int is_pay, int operate_centerid, int agent_id,int pay_virtual,Date start_time, Date end_time, int type, HttpServletResponse response)
	{
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 00:00:00");
			String b_time="";
			String e_time="";
			if(start_time==null || end_time==null){
				String fileName = new String(("充值记录"+b_time+"~"+e_time).getBytes(), "ISO8859_1");  
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			}else{
				String fileName = new String(("充值记录"+start_time+"~"+end_time).getBytes(), "ISO8859_1");  
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			}
			
			
			

			String channel = "";
			String platform = "";
			//支付总金额
			long sumrecharge=0L;
			//支付总钻石
			long sumvirtual=0L;
			
			if(!StringUtils.isBlank(platform_channel))
			{
				String[] platform_channelStrings = platform_channel.split("_");
				platform = platform_channelStrings[0];
				channel  = platform_channelStrings[1];
			}
		
	
			List<Recharge> rechargeList = irechargeDao.getRechargeList(uid, f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_centerid,agent_id,pay_virtual,0, 1000000000);
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
			for(Recharge recharge:rechargeList){
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
			}
			
			List<Recharge> sumrechargeList = irechargeDao.getSumRecharge(uid, f_uuid, pay_type, nickname, o_id, platform, channel, is_pay, start_time, end_time, operate_centerid, agent_id,pay_virtual);
			for(Recharge recharge:sumrechargeList){
				
				//支付总金额
				long rechargermb=recharge.getRecharge_rmb();			
				 sumrecharge +=rechargermb;
				//支付总钻石
				long changevirtual=recharge.getChange_virtual();
				sumvirtual +=changevirtual;
			}
			
			
			
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"订单编号","用户UID","用户房间号","昵称","支付金额（元）","钻石","第三方交易单号","支付方式","所属运营中心","所属代理平台","所属推广员","支付时间"};
			
			
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
				for (Recharge recharge : rechargeList) {
					XSSFRow bodyRow = sheet.createRow(j + 1); 

					cell = bodyRow.createCell(0);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getO_id());  

					cell = bodyRow.createCell(1);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getUid());  

					cell = bodyRow.createCell(2);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getF_uuid());  

					cell = bodyRow.createCell(3);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getNickname());

					cell = bodyRow.createCell(4);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getRecharge_rmb());

					cell = bodyRow.createCell(5);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getChange_virtual());

					cell = bodyRow.createCell(6);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getPay_trade_no());

					cell = bodyRow.createCell(7);  
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
						case 2:
							cell.setCellValue("苹果支付");
							break;
						default:
							cell.setCellValue("扫码支付");
							break;
						}
					}
					else {
						cell.setCellValue("");
					}
				
					cell = bodyRow.createCell(8);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getOperateName());
					
					cell = bodyRow.createCell(9);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getAgentName());
					
					cell = bodyRow.createCell(10);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getProName());

					cell = bodyRow.createCell(11);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(recharge.getCreate_time());

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
				cell.setCellValue(sumrecharge);

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(sumvirtual);

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("");

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("");
			
				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("");
				
				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("");
				
				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("");

				cell = bodyRow.createCell(11);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("");
				
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
			log.error("导出充值", e);
		}
		return null;
	}
	public String exportExcel2(long uid, long f_uuid, String s_time,String e_time,String diamondsa,String diamondsb,String nickname, HttpServletResponse response)
	{
		try {
			ServletOutputStream outputStream = response.getOutputStream();  

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"订单编号","用户UID","房间号","等级","设备","支付方式","类别","用户昵称","充钻数量","充钻金额","备注","操作人员","时间"};
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
			response.setHeader("Content-disposition", "attachment; filename=" + new String(("赠送列表").getBytes(), "ISO8859_1") + ".xlsx");// 组装附件名称和格式
			if(diamondsb.equals("-1"))
				diamondsb = "";
			if(f_uuid>0)
				uid = iuserDao.getUserIdWithF_uuid(String.valueOf(f_uuid));
			if(!StringUtils.isBlank(e_time))
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(AuthUtil.formatStringToDate(e_time,"yyyy-MM-dd")); 
				calendar.add(Calendar.DATE,1);
				e_time = AuthUtil.formatDateToString(calendar.getTime(),"yyyy-MM-dd");
			}
			List<Balance_virtualrecord> balance_virtualrecordList = irechargeDao.getBalance_virtualrecordList(uid, s_time, e_time, diamondsa, diamondsb,nickname,0, 10000000);
			for (Balance_virtualrecord balance_virtualrecord : balance_virtualrecordList) {
				User user = iuserDao.getUser(balance_virtualrecord.getUid());
				if(user!=null){
					balance_virtualrecord.setF_uuid(user.getF_uuid());
					balance_virtualrecord.setLevel(user.getLevel());
					Long hasDeviceInfo = iuser_deviceDao.getIdByUid(user.getId());
					if ((hasDeviceInfo!=null)&&(hasDeviceInfo>0)) {
						balance_virtualrecord.setHasDeviceInfo(1);
					}else {
						balance_virtualrecord.setHasDeviceInfo(0);
					}
				}
			}
			int j=0;
			for (Balance_virtualrecord balance_virtualrecord : balance_virtualrecordList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getId());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getUid());  

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getF_uuid());  
				
				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getLevel());  
				
				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(((balance_virtualrecord.getHasDeviceInfo()==1)?"有":"无"));  

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getDiamondsb()==-1?"":balance_virtualrecord.getDiamondsb()==0?"支付宝":"微信");

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue((balance_virtualrecord.getDiamondsa().equals("zftype1")?"用户充值":balance_virtualrecord.getDiamondsa().equals("zftype2")?"家族充值":balance_virtualrecord.getDiamondsa().equals("zftype3")?"奖励充值":"邀请有礼")+balance_virtualrecord.getFamily());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getNickname());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getBalance_virtual());

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getBalance_money());

				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getRemark());

				cell = bodyRow.createCell(11);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getDiamondsa()=="zftype4"?"系统":balance_virtualrecord.getW_name());

				cell = bodyRow.createCell(12);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(balance_virtualrecord.getCreate_date());

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
			log.error("导出赠送记录", e);
		}
		return null;
	}
	public String exportExcel3(Date start_time,Date end_time,int statistics_type,String channel_platform,int recharge_type,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 00:00:00");
			String fileName = new String(("充值统计").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"日期","充值人数","充值次数","充值金额（元）","支付钻石","ARPU值"};
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
			String channel = "";
			Integer platform = null;
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
			int totalCount = irechargeDao.getStatisticsCount(start_time, end_time, statistics_type, channel,platform,recharge_type);
			List<RechargeStatistics> list = irechargeDao.getStatisticsList(start_time, end_time, statistics_type, channel,platform,recharge_type,0,totalCount);

			// 构建表体数据  
			int j=0;
			for (RechargeStatistics statistics  : list) {

				XSSFRow bodyRow = sheet.createRow(j + 1); 
                String time = statistics.getRecharge_year();
                if (statistics.getRecharge_month()!=null) {
					time = time + "-"+statistics.getRecharge_month();
				}
                if (statistics.getRecharge_day()!=null) {
					time = time + "-"+statistics.getRecharge_day();
				}
				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(time);  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(statistics.getPeople_count());   


				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(statistics.getRecharge_count());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(statistics.getRecharge_money());
				
				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(statistics.getVirtual_money());
				
				DecimalFormat df=new DecimalFormat("0.00");
				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(df.format(statistics.getRecharge_money()/(float)statistics.getPeople_count()));

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
			log.error("导出充值统计", e);
		}
		return null;
	}
}

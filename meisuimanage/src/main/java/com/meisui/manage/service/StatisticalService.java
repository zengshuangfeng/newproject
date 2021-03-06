package com.meisui.manage.service;

import com.meisui.manage.dao.*;
import com.meisui.manage.entity.*;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class StatisticalService {
	private static Logger log = Logger.getLogger(StatisticalService.class.getClass());
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Igift_InfoDao igift_InfoDao;
	@Autowired
	private Ioperate_CenterDao ioperate_CenterDao;
	@Autowired
	private IagentDao iagentDao;
	@Autowired
	private Iagent_PromoterDao iagent_PromoterDao;
	@Autowired
	private Ianchor_Guard_ChangeDao ianchor_guard_changeDao;
	public String getProfitList(int operate_center_id, int agent_id, Date s_time, Date e_time, long f_uuid, String nickname, int type, int page, Model model, HttpServletRequest request)
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
			List<Profit> profitList = iprofitDao.getProfitListWithOperateCenterId(operate_center_id, agent_id, f_uuid, nickname, type, s_time, e_time, (page-1)*20, 20);
			for (Profit profit : profitList) {
				User user = iuserDao.getUser(profit.getUid());
				profit.setF_uuid(user.getF_uuid());
				profit.setNickname(user.getNickname());
				if(profit.getType() ==9 || profit.getType()==10){
					Anchor_Guard_Change2 guardchange=ianchor_guard_changeDao.getAnchorGuardNewByid(profit.getGift_id());
					profit.setGift_name(guardchange.getName());
					profit.setGift_original_virtual(guardchange.getChange_virtual());
				}else{

					Gift_Info gift_Info = igift_InfoDao.getGiftInfo(profit.getGift_id());
					profit.setGift_name(gift_Info.getGift_name());
					profit.setGift_original_virtual(gift_Info.getGift_virtual());
				}


				User send_user=iuserDao.getUser(profit.getSend_uid());
				profit.setSend_f_uuid(Long.parseLong(send_user.getF_uuid()));
				profit.setSend_nickname(send_user.getNickname());
			}
			int totalRecord = iprofitDao.getProfitCountWithOperateCenterId(operate_center_id, agent_id, f_uuid, nickname, type, s_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("statistical/profitalllist");
			List<Operate_Center> operate_CenterList = ioperate_CenterDao.getListAll();
			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentDao.getAgentListByCenterId(operate_center_id);
			}
			model.addAttribute("operate_CenterList", operate_CenterList);
			model.addAttribute("agentList", agentList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("profitList", profitList);
			model.addAttribute("s_time", s_time != null ? AuthUtil.formatDateToString(s_time, "yyyy-MM-dd") : "");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("type", type);
			model.addAttribute("activeUrl", "statistical");
		}
		catch (Exception e) {
			log.error("代理收益列表", e);
		}
		return "statistical/profitalllist";
	}
	public String exportProfit(int operate_center_id, int agent_id, Date s_time, Date e_time, long f_uuid, String nickname, int type, HttpServletResponse response, HttpServletRequest request){
		try {
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}	
			ServletOutputStream outputStream = response.getOutputStream();  

			String fileName = new String((AuthUtil.formatDateToString(s_time, "yyyy-MM-dd")+"~"+AuthUtil.formatDateToString(e_time, "yyyy-MM-dd")+"个人收益明细").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"收益方房间号", "收益方昵称", "直播所得魅力值", "推广所得魅力值","礼物名称","礼物价值（钻）","送礼房间号","送礼昵称","送礼时间"};
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
			// 构建表体数据  
			int j=0;
			List<Profit> profitList = iprofitDao.getProfitListWithOperateCenterId(operate_center_id, agent_id, f_uuid, nickname, type, s_time, e_time, 0, Integer.MAX_VALUE);
			for (Profit profit : profitList) {
				User user = iuserDao.getUser(profit.getUid());
				profit.setF_uuid(user.getF_uuid());
				profit.setNickname(user.getNickname());
				if(profit.getType() ==9 || profit.getType()==10){
					Anchor_Guard_Change2 guardchange=ianchor_guard_changeDao.getAnchorGuardNewByid(profit.getGift_id());
					profit.setGift_name(guardchange.getName());
					profit.setGift_original_virtual(guardchange.getChange_virtual());
				}else{

					Gift_Info gift_Info = igift_InfoDao.getGiftInfo(profit.getGift_id());
					profit.setGift_name(gift_Info.getGift_name());
					profit.setGift_original_virtual(gift_Info.getGift_virtual());
				}
				User send_user=iuserDao.getUser(profit.getSend_uid());
				profit.setSend_f_uuid(Long.parseLong(send_user.getF_uuid()));
				profit.setSend_nickname(send_user.getNickname());
				Gift_Info gift_Info = igift_InfoDao.getGiftInfo(profit.getGift_id());
				profit.setGift_name(gift_Info.getGift_name());
				profit.setGift_original_virtual(gift_Info.getGift_virtual());

				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getF_uuid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getNickname()); 

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getType()==0?profit.getGift_virtual():0);			


				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getType()==8?profit.getGift_virtual():0);


				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				if(profit.getIs_box()==0){
					cell.setCellValue(profit.getGift_name());
				}else if(profit.getIs_box()==1){
					cell.setCellValue(profit.getGift_name()+"(宝箱)");
				}
				

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getGift_original_virtual());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getSend_f_uuid());
				
				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getSend_nickname());
				
				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(profit.getCreate_time());

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
			log.error("导出收益列表", e);
		}
		return null;
	}
	public String giftProfitList(int operate_center_id, int agent_id,Date s_time, Date e_time, int page, HttpServletRequest request, Model model) {
		try {
			List<Profit> list = new ArrayList<Profit>();
			int totalRecord = 0; 
			Date end_time = null;
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				end_time = calendar.getTime();
			}
			list = iprofitDao.getGiftProfitList(operate_center_id, agent_id, s_time, end_time, (page-1)*20, 20);
			totalRecord = iprofitDao.getGfitProfitListCount(operate_center_id, agent_id, s_time, end_time);
			Map<Long, User> userMap = new HashMap<Long, User>(); 
			for(Profit profit : list) {
				if(userMap.containsKey(profit.getUid())) {
					profit.setF_uuid(userMap.get(profit.getUid()).getF_uuid());
				}else {
					User user = iuserDao.getUser(profit.getUid());
					profit.setF_uuid(user.getF_uuid());
					userMap.put(user.getId(), user);
				}
				if(userMap.containsKey(profit.getSend_uid())) {
					profit.setSend_f_uuid(Long.parseLong(userMap.get(profit.getSend_uid()).getF_uuid()));
					profit.setSend_nickname(userMap.get(profit.getSend_uid()).getNickname());
					int promoterId = userMap.get(profit.getSend_uid()).getAgent_promoter_id();
					profit.setPromoterId(promoterId);
					if(promoterId == 0) {
						profit.setPromoterFuuid(Long.parseLong(userMap.get(profit.getSend_uid()).getF_uuid()));
					}
				}else {
					User user = iuserDao.getUser(profit.getSend_uid());
					profit.setSend_f_uuid(Long.parseLong(user.getF_uuid()));
					profit.setSend_nickname(user.getNickname());
					int promoterId = user.getAgent_promoter_id();
					profit.setPromoterId(promoterId);
					userMap.put(user.getId(), user);
					if(promoterId == 0) {
						profit.setPromoterFuuid(Long.parseLong(user.getF_uuid()));
					}
				}
				if(profit.getPromoterId() > 0) {
					long promoterUid = iagent_PromoterDao.getUId(profit.getPromoterId());
					long promoterFuuid = Long.parseLong(iuserDao.getUser(promoterUid).getF_uuid());
					profit.setPromoterFuuid(promoterFuuid);
				}
				int divide = profit.getGift_divide();
				if(profit.getGift_virtual()  !=0 && divide !=0){
					int virtual = profit.getGift_virtual() * 100 / divide;//礼物价值
					int promoter_virtual = virtual - profit.getGift_virtual();
					profit.setGift_original_virtual(virtual);
					profit.setPromoterVirtual(promoter_virtual);
				}else{
					int virtual = 0;//礼物价值
					int promoter_virtual = 0;
					profit.setGift_original_virtual(virtual);
					profit.setPromoterVirtual(promoter_virtual);
				}
				
				
			}	
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("statistical2/giftprofit");
			List<Operate_Center> operate_CenterList = ioperate_CenterDao.getListAll();
			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentDao.getAgentListByCenterId(operate_center_id);
			}
			model.addAttribute("operate_CenterList", operate_CenterList);
			model.addAttribute("agentList", agentList);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("activeUrl", "statistical2");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("list", list);
			model.addAttribute("s_time", s_time!=null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_time!=null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"");
		}catch (Exception e) {
			log.error("礼物收益明细", e);
		}
		return "statistical/giftprofitlist";
	}
	public String agentList(int centerId) {
		JSONObject jsonObject = new JSONObject();
		try {
			List<Agent> list = iagentDao.getAgentListByCenterId(centerId);
			jsonObject.put("list", list);
		}catch (Exception e) {
			log.error("获取运营中心下所有代理列表");
		}
		return jsonObject.toString();
	}
}

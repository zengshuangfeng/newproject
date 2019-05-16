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

import com.forman.foundation.library.DateUtils;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Anchor_Record;
import com.meisui.manage.entity.Anchor_Record_Total;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;

import net.sf.json.JSONObject;

/**
 * <p>文件名称：Anchor_RecordService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月13日 下午5:07:54
 */
@Service
public class Anchor_RecordService {
	private static Logger log = Logger.getLogger(Anchor_RecordService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Ioperate_CenterDao ioperate_CenterDao;
	@Autowired
	private IagentDao iagentDao;
	/**
	 * 
	 * <p>功能描述：直播记录列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月13日 下午5:29:23</p>
	 *
	 * @param uid 主播uid
	 * @param nickname 主播昵称
	 * @param is_trial 是否是试播 1是 0否
	 * @param union_id 所属工会
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getAnchorRecordList(int operate_center_id, int agent_id, long uid, long f_uuid, String nickname, int is_trial,  Date start_time, Date end_time, int page, Model model, HttpServletRequest request)
	{
		try {
			if(start_time==null)
			{
				int add = 0;
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(DateUtils.sdf.parse(DateUtils.sdf.format(new Date()))); 
				switch (calendar.get(Calendar.DAY_OF_WEEK)) {
				case 1:
					add=-6;
					break;		
				default:
					add = 0-calendar.get(Calendar.DAY_OF_WEEK)+2;
					break;
				}
				calendar.add(Calendar.DATE,add);
				start_time=calendar.getTime();
			}
			if(end_time==null)
				end_time = new Date();
			model.addAttribute("end_time", AuthUtil.formatDateToString(end_time,"yyyy-MM-dd"));
			Calendar calendar2 = new GregorianCalendar(); 
			calendar2.setTime(end_time); 
			calendar2.add(Calendar.DATE,1);
			end_time = calendar2.getTime();
			List<User_Anchor> user_AnchorList = iuserDao.getUserAnchorList2(operate_center_id, agent_id, uid, f_uuid, nickname, is_trial, start_time, end_time, (page-1)*20, 20);
			List<Anchor_Record_Total> anchor_Record_TotalList = new ArrayList<Anchor_Record_Total>();
			//List<Anchor_Union> anchor_UnionList = ianchor_UnionDao.getAllAnchorUnionList();
			//List<Anchor_Virtual_Rule> anchor_Virtual_RuleList = iuserDao.getAnchorVirtualRuleList();
			for (User_Anchor user_Anchor : user_AnchorList) {
				/*	for (Anchor_Union anchor_Union : anchor_UnionList) {
					if(user_Anchor.getUnion_id()==anchor_Union.getId())
					{
						user_Anchor.setUnion_name(anchor_Union.getName());
						break;
					}
				}*/
				Anchor_Record_Total anchor_Record_Total = new Anchor_Record_Total();
				anchor_Record_Total.setUid(user_Anchor.getUid());
				anchor_Record_Total.setF_uuid(user_Anchor.getF_uuid());
				anchor_Record_Total.setNickname(user_Anchor.getNickname());
				anchor_Record_Total.setDivide_proportion(user_Anchor.getDivide_proportion());	
				anchor_Record_Total.setIs_trial(user_Anchor.getIs_trial());
				anchor_Record_Total.setUnion_id(user_Anchor.getUnion_id());
				anchor_Record_Total.setUnion_name(user_Anchor.getUnion_name());
				List<Anchor_Record> anchor_RecordList = iuserDao.getUserAnchorRecordList(user_Anchor.getUid(), start_time, end_time);
				for (Anchor_Record anchor_Record : anchor_RecordList) {
					anchor_Record_Total.setVirtual_count(anchor_Record_Total.getVirtual_count()+anchor_Record.getVirtual_count());
					if(anchor_Record.getIs_attend()==1)
						anchor_Record_Total.setAttend_days(anchor_Record_Total.getAttend_days()+1);
					if(anchor_Record.getEffective_time()>=7200)
						anchor_Record.setIs_effective(1);
					if(anchor_Record.getIs_effective()==1)
					{
						anchor_Record_Total.setEffective_days(anchor_Record_Total.getEffective_days()+1);
						anchor_Record_Total.setEffective_time(setAnchorTime(anchor_Record_Total.getEffective_time(), anchor_Record.getEffective_time()));
					}
				}
				anchor_Record_TotalList.add(anchor_Record_Total);
			}
			int totalRecord = iuserDao.getUserAnchorCount2(operate_center_id, agent_id, uid, f_uuid, nickname, is_trial, start_time, end_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorrecord/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorRecordTotalList", anchor_Record_TotalList);
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
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("is_trial", is_trial);	
			model.addAttribute("start_time", AuthUtil.formatDateToString(start_time,"yyyy-MM-dd"));			
			model.addAttribute("activeUrl", "anchorrecord"); 
			String w_name = String.valueOf(request.getAttribute("w_name"));
			int show_info = 1;
			if(w_name.equals("二哥"))
				show_info=0;
			model.addAttribute("show_info", show_info);
		} catch (Exception e) {
			log.error("直播记录列表", e);
		}
		return "anchorrecord/list";
	}
	private String setAnchorTime(String total_time, long anchor_second)
	{
		if(!StringUtils.isBlank(total_time))
		{
			String[] timeStrings = total_time.split(":");
			anchor_second = anchor_second + Integer.parseInt(timeStrings[0])*3600 + Integer.parseInt(timeStrings[1])*60 + Integer.parseInt(timeStrings[2]);
		}

		long hour = anchor_second/3600;
		long minute = anchor_second%3600/60;
		long second = (anchor_second%3600)%60;
		return hour+":"+minute+":"+second;
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
	public String exportTime(int operate_center_id, int agent_id, long uid, long f_uuid, String nickname, int is_trial,  Date start_time, Date end_time, HttpServletRequest request, HttpServletResponse response)
	{
		try {
			if(start_time==null)
			{
				int add = 0;
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(DateUtils.sdf.parse(DateUtils.sdf.format(new Date()))); 
				switch (calendar.get(Calendar.DAY_OF_WEEK)) {
				case 1:
					add=-6;
					break;		
				default:
					add = 0-calendar.get(Calendar.DAY_OF_WEEK)+2;
					break;
				}
				calendar.add(Calendar.DATE,add);
				start_time=calendar.getTime();
			}
			if(end_time==null)
				end_time = new Date();
			Calendar calendar2 = new GregorianCalendar(); 
			calendar2.setTime(end_time); 
			calendar2.add(Calendar.DATE,1);
			end_time = calendar2.getTime();
			List<User_Anchor> user_AnchorList = iuserDao.getUserAnchorList2(operate_center_id, agent_id, uid, f_uuid, nickname, is_trial, start_time, end_time, 0, Integer.MAX_VALUE);
			List<Anchor_Record_Total> anchor_Record_TotalList = new ArrayList<Anchor_Record_Total>();
			ServletOutputStream outputStream = response.getOutputStream();  

			String fileName = new String((AuthUtil.formatDateToString(start_time, "yyyy-MM-dd")+"~"+AuthUtil.formatDateToString(end_time, "yyyy-MM-dd")+"直播记录列表").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"主播房间号", "昵称", "运营中心名称", "代理名称","是否试播","有效天"};
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
			for (User_Anchor user_Anchor : user_AnchorList) {
				Anchor_Record_Total anchor_Record_Total = new Anchor_Record_Total();
				anchor_Record_Total.setUid(user_Anchor.getUid());
				anchor_Record_Total.setF_uuid(user_Anchor.getF_uuid());
				anchor_Record_Total.setNickname(user_Anchor.getNickname());
				anchor_Record_Total.setDivide_proportion(user_Anchor.getDivide_proportion());	
				anchor_Record_Total.setIs_trial(user_Anchor.getIs_trial());
				Operate_Center operate_Center = ioperate_CenterDao.getOperateCenterById(user_Anchor.getOperate_center_id());
				anchor_Record_Total.setOperate_center_name(operate_Center.getName());
				Agent agent = iagentDao.getAgent(user_Anchor.getAgent_id());
				anchor_Record_Total.setAgent_name(agent.getName());;
				List<Anchor_Record> anchor_RecordList = iuserDao.getUserAnchorRecordList(user_Anchor.getUid(), start_time, end_time);
				for (Anchor_Record anchor_Record : anchor_RecordList) {
					anchor_Record_Total.setVirtual_count(anchor_Record_Total.getVirtual_count()+anchor_Record.getVirtual_count());
					if(anchor_Record.getIs_attend()==1)
						anchor_Record_Total.setAttend_days(anchor_Record_Total.getAttend_days()+1);
					if(anchor_Record.getEffective_time()>=7200)
						anchor_Record.setIs_effective(1);
					if(anchor_Record.getIs_effective()==1)
					{
						anchor_Record_Total.setEffective_days(anchor_Record_Total.getEffective_days()+1);
						anchor_Record_Total.setEffective_time(setAnchorTime(anchor_Record_Total.getEffective_time(), anchor_Record.getEffective_time()));
					}
				}
				anchor_Record_TotalList.add(anchor_Record_Total);
			}
			for (Anchor_Record_Total anchor_Record_Total : anchor_Record_TotalList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Record_Total.getF_uuid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Record_Total.getNickname()); 

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Record_Total.getOperate_center_name());			


				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Record_Total.getAgent_name());


				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Record_Total.getIs_trial()==1?"试播":"正式");

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Record_Total.getEffective_days());

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
			log.error("导出直播记录列表", e);
		}
		return null;
	}
}

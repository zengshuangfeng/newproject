package com.meisui.manage.service;

import java.io.IOException;
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

import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_TimeDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.IapplyDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Time;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Anchor_Virtual_Total;
import com.meisui.manage.entity.Apply;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：ApplyService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午2:16:57
 */
@Service
public class ApplyService {
	private static Logger log = Logger.getLogger(ApplyService.class.getClass());
	@Autowired
	private IapplyDao iapplyDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Ianchor_TimeDao ianchor_TimeDao;
	@Autowired
	private Iagent_PromoterDao iagentpromoterdao;
	/**
	 * 
	 * <p>功能描述：主播申请列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午2:19:41</p>
	 *
	 * @param is_contact 是否联系 1是 0 否
	 * @param uid 用户uid
	 * @param nickname 昵称
	 * @param qq 
	 * @param phone 电话
	 * @param remark 备注
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getApplyList(int is_contact, long uid, long f_uuid, String nickname, String qq, String phone, String remark, Date start_time, Date end_time, int page, Model model)
	{
		try {
			String e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<Apply> applyList = iapplyDao.getApplyList(is_contact, uid, f_uuid, nickname, qq, phone, remark, start_time, end_time, (page-1)*20, 20);
			for(Apply ap:applyList){
				int agentpromoterid=iagentpromoterdao.getpromoterByUid(ap.getUid());
				int ispromoter=0;
				if(agentpromoterid>0){
					ispromoter=1;
				}
				ap.setIspromoter(ispromoter);		
			}
			
			int totalRecord = iapplyDao.getApplyCount(is_contact, uid, f_uuid, nickname, qq, phone, remark, start_time, end_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("apply/list");
			model.addAttribute("applyList", applyList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "apply");
			model.addAttribute("is_contact", is_contact);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("qq", qq);
			model.addAttribute("phone", phone);
			model.addAttribute("remark", remark);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
		} catch (Exception e) {
			log.error("主播申请列表", e);
		}
		return "apply/list";
	}
	/**
	 * 
	 * <p>功能描述：更新主播申请信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午2:25:18</p>
	 *
	 * @param id 申清id
	 * @param is_contact 是否联系
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String updateApply(int id, int is_contact, String remark, String w_name, HttpServletRequest request)
	{
		try {
			Apply apply = new Apply();
			apply.setId(id);
			apply.setIs_contact(is_contact);
			apply.setRemark(remark);
			w_name = String.valueOf(request.getAttribute("w_name"));
			apply.setW_name(w_name);
			int result = iapplyDao.updateApply(apply);
			imanage_RecordDao.insertManageRecord(w_name, "更新主播申请信息", "t_apply", id, IPUtil.getIp(request), new Date());
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			log.error("更新主播申请信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	public String exportExcel(int is_contact,long uid, long f_uuid, String nickname, String qq, String phone, Date start_time, Date end_time, HttpServletResponse response)
	{
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			if(start_time==null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.DATE,-1);
				start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(calendar.getTime(), "yyyy-MM-dd")+" 00:00:00");
			}
			boolean singleDate = false;
			if(end_time==null)
				end_time = start_time;
			if(end_time.getTime()-start_time.getTime()==0)
				singleDate = true;
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			String fileName = "";
			if(singleDate)
				fileName = new String((AuthUtil.formatDateToString(start_time, "MM月dd日")+"-主播申请列表").getBytes(), "ISO8859_1"); 
			else
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,-1);
				fileName = new String((AuthUtil.formatDateToString(start_time, "MM月dd日")+"~"+AuthUtil.formatDateToString(calendar.getTime(), "MM月dd日")+"-主播申请列表").getBytes(), "ISO8859_1"); 

			}
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"UID","房间号","昵称","图片","手机","QQ","申请时间","是否联系","备注"};
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
			//List<Anchor_Union> anchor_UnionList = ianchor_UnionDao.getAllAnchorUnionList();
			List<Apply> applyList = iapplyDao.getApplyList2(is_contact, uid, f_uuid, nickname, qq, phone, "", start_time, end_time);
			int j=0;
			for (Apply apply : applyList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getF_uuid());  

				//User_Anchor user_Anchor = iuserDao.getUserAnchor(apply.getUid());
				
			
				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getNickname());  
				
				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getHead());  

				//Anchor_Time anchor_Time = ianchor_TimeDao.getFirstAnchorTime(apply.getUid(), 0);
				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getPhone());  
				
				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getQq());  
				
				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getCreate_time());  
				
				int contact=apply.getIs_contact();
				String contactName="";
				if(contact==0){
					contactName="否";
				}else{
					contactName="是";
				}
				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(contactName); 
				
				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(apply.getRemark());
				/*if(anchor_Time!=null)
				{
					cell.setCellValue(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd"));
				}
				else
				{
					cell.setCellValue("");
				}
				long millminute = 0L;
				if(anchor_Time!=null)
				{
					Date first_start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(anchor_Time.getStart_time(), "yyyy-MM-dd") + " 00:00:00");
					Calendar calendar = new GregorianCalendar(); 
					calendar.setTime(first_start_time); 
					calendar.add(Calendar.DATE,1);
					Date first_end_time = calendar.getTime();
					List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList2(apply.getUid(), 0, first_start_time, first_end_time);

					for (Anchor_Time anchor_Time2 : anchor_TimeList) {
						if(anchor_Time2.getStart_time().before(first_start_time))
							anchor_Time2.setStart_time(first_start_time);
						if(anchor_Time2.getEnd_time().after(first_end_time))
							anchor_Time2.setEnd_time(first_end_time);
						millminute += anchor_Time2.getEnd_time().getTime() - anchor_Time2.getStart_time().getTime();
					}
				}*/
			
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
			log.error("导出主播魅力", e);
		}
		return null;
	}
	private String getTotalTime(long millminute)
	{
		Integer ss = 1000;  
		Integer mi = ss * 60;  
		Integer hh = mi * 60;   

		Long hour = millminute / hh;  
		Long minute = (millminute - hour * hh) / mi;  
		Long second = (millminute - hour * hh - minute * mi) / ss; 
		StringBuffer sbBuffer = new StringBuffer();
		if(hour>0)
		{
			sbBuffer.append(hour);
			sbBuffer.append("小时");
		}
		if(minute>0)
		{
			sbBuffer.append(minute);
			sbBuffer.append("分钟");
		}
		if(second>0)
		{
			sbBuffer.append(second);
			sbBuffer.append("秒");
		}
		return sbBuffer.toString();
	}
}

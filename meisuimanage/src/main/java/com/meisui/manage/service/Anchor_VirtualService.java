package com.meisui.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletOutputStream;
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

import com.forman.foundation.library.DateUtils;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Anchor_Virtual_Total;
import com.meisui.manage.entity.SenderStatistics;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class Anchor_VirtualService {
	private static Logger log = Logger.getLogger(Anchor_VirtualService.class.getClass());
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private IuserDao iuserDao;
	
	public String getList(long uid, long f_uuid, String nickname, int union_id, Date start_time, Date end_time, int page, Model model)
	{
		try {
            
			List<Anchor_Virtual_Total> anchor_Virtual_TotalList = new ArrayList<Anchor_Virtual_Total>();
			int totalRecord = 0;
			String e_timeString = "";
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
			calendar2.add(Calendar.DATE,0);
			end_time = calendar2.getTime();
			
			
			if(start_time!=null)
			{
				if(end_time==null)
					end_time = start_time;
				e_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";

				if(start_time!=null&&end_time!=null)
				{  
				    end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(end_time,"yyyy-MM-dd")+" 23:59:59");
					anchor_Virtual_TotalList = iprofitDao.getProfitTotalListWithUid(uid, f_uuid, nickname, union_id, start_time, end_time, (page-1)*20, 20);	

					for (Anchor_Virtual_Total anchor_Virtual_Total : anchor_Virtual_TotalList) {
						User_Anchor user_Anchor = iuserDao.getUserAnchor(anchor_Virtual_Total.getUid());
	
						Long total_anchor_virtual = iprofitDao.getProfitSum(anchor_Virtual_Total.getUid(), start_time, end_time);
						if(total_anchor_virtual==null)
							total_anchor_virtual = 0L;
						anchor_Virtual_Total.setTotal_anchor_virtual(total_anchor_virtual);
						Integer game_profit = iprofitDao.getGameProfit(anchor_Virtual_Total.getUid(), start_time, end_time);
						if(game_profit==null)
							game_profit = 0;
						anchor_Virtual_Total.setGame_profit(game_profit);
						 
						List<SenderStatistics> statisticslist = iprofitDao.getSenderStatisticsList(anchor_Virtual_Total.getUid(), start_time, end_time);
						for(SenderStatistics statistics : statisticslist){
							if (statistics.getLevel()==1) {
								anchor_Virtual_Total.setLevel_one_num(statistics.getCount_sender());
								anchor_Virtual_Total.setLevel_one_virtual(statistics.getTotal_virtual());
							}
                            if (statistics.getLevel()==2) {
    							anchor_Virtual_Total.setLevel_two_num(statistics.getCount_sender());
    							anchor_Virtual_Total.setLevel_two_virtual(statistics.getTotal_virtual());
							}
						}
					}
					totalRecord =iprofitDao.getProfitTotalCountWithUid(uid, f_uuid, nickname, union_id, start_time, end_time);
				}
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorvirtual/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			model.addAttribute("anchorVirtualTotalList", anchor_Virtual_TotalList);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("union_id", union_id);
			model.addAttribute("activeUrl", "anchorvirtual");
		} catch (Exception e) {
			log.error("统计主播魅力", e);
		}
		return "anchorvirtual/list";
	}
	
	public String getList1(long uid, long f_uuid, String nickname, int union_id, Date start_time, Date end_time, int page, Model model){
		
		
		return "";
	}
	
	public String exportExcel(long uid, long f_uuid, String nickname, int union_id, Date start_time, Date end_time, HttpServletResponse response)
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
			if(end_time==null)
				end_time = start_time;
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			//List<User_Anchor> userAnchorList = iuserDao.getUserAnchorPayList(0l, 0l, "", union_id, start_time, 0, 1000000);
			String fileName = new String((AuthUtil.formatDateToString(start_time, "yyyy-MM-dd")+"-主播统计管理").getBytes(), "ISO8859_1");  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"主播UID","主播房间号","主播昵称","魅力值","1级用户魅力值","1级用户数量","2级用户魅力值","2级用户数量"};
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
			
			int totalRecord =iprofitDao.getProfitTotalCountWithUid(uid, f_uuid, nickname, union_id, start_time, end_time);
			List<Anchor_Virtual_Total> anchor_Virtual_TotalList = iprofitDao.getProfitTotalListWithUid(uid, f_uuid, nickname, union_id, start_time, end_time, 0, totalRecord);
			//List<Anchor_Union> anchor_UnionList = ianchor_UnionDao.getAllAnchorUnionList();
			// 构建表体数据  
			int j=0;
			for (Anchor_Virtual_Total anchor_Virtual_Total : anchor_Virtual_TotalList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getF_uuid());   

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getNickname());
		/*		User_Anchor user_Anchor = iuserDao.getUserAnchor(anchor_Virtual_Total.getUid());
				if(user_Anchor!=null)
				{
					for (Anchor_Union anchor_Union : anchor_UnionList) {
						if(anchor_Union.getId()==user_Anchor.getUnion_id())
						{
							anchor_Virtual_Total.setUnion_name(anchor_Union.getName());
							break;
						}
					}
				}
				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getUnion_name());*/

				Long total_anchor_virtual = iprofitDao.getProfitSum(anchor_Virtual_Total.getUid(), start_time, end_time);
				if(total_anchor_virtual==null)
					total_anchor_virtual = 0L;
				anchor_Virtual_Total.setTotal_anchor_virtual(total_anchor_virtual);
				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getTotal_anchor_virtual());
				
			/*	Integer game_profit = iprofitDao.getGameProfit(anchor_Virtual_Total.getUid(), start_time, end_time);
				if(game_profit==null)
					game_profit = 0;
				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(game_profit);*/
				
				List<SenderStatistics> statisticslist = iprofitDao.getSenderStatisticsList(anchor_Virtual_Total.getUid(), start_time, end_time);
				for(SenderStatistics statistics : statisticslist){
					if (statistics.getLevel()==1) {
						anchor_Virtual_Total.setLevel_one_num(statistics.getCount_sender());
						anchor_Virtual_Total.setLevel_one_virtual(statistics.getTotal_virtual());
					}
                    if (statistics.getLevel()==2) {
						anchor_Virtual_Total.setLevel_two_num(statistics.getCount_sender());
						anchor_Virtual_Total.setLevel_two_virtual(statistics.getTotal_virtual());
					}
				}				
				
				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getLevel_one_virtual());
				
				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getLevel_one_num());
				
				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getLevel_two_virtual());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Virtual_Total.getLevel_two_num());

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
			log.error("导出主播统计管理", e);
		}
		return null;
	}
}

package com.meisui.manage.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

import com.meisui.manage.dao.IrechargeDao;
import com.meisui.manage.dao.Irecharge_ChannelDao;
import com.meisui.manage.dao.IuserStatisticsDao;
import com.meisui.manage.entity.Recharge_Channel;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class UserStatisticsService {
	private static Logger log = Logger.getLogger(UserStatisticsService.class.getClass());
	@Autowired
	private IuserStatisticsDao userStatisticsDao;
	@Autowired
	private Irecharge_ChannelDao channelDao;
	@Autowired
	private IrechargeDao rechargeDao;


	public String list(long uid, long fuuid, String nickName, String level, String startTime, String endTime, int page,Model model) {
		int startLevel = 0, endLevel = 0;
		List<Map<String,Object>> userInfoList = new ArrayList<Map<String,Object>>();
		int totalRecord=0;
		try{
			if (!StringUtils.isBlank(level)) {
				if(level.contains("-"))
				{
					String[] levelStrings = level.split("-");
					startLevel = Integer.parseInt(levelStrings[0]);
					endLevel = Integer.parseInt(levelStrings[1]);
				}
				else {
					startLevel = Integer.parseInt(level);
					endLevel = Integer.parseInt(level);
				}
			}
			String sTime="";
			String eTime="";
			if(!startTime.equals("") && !endTime.equals("")){
				sTime = startTime+" "+"00:00:00";
				 eTime = endTime+" "+"23:59:59";
			}else{
				 sTime=startTime;
				 eTime=endTime;
			}
			if(!sTime.equals("")&& !eTime.equals("")) {
				userInfoList = userStatisticsDao.getUserInfo(uid,fuuid,nickName,startLevel, endLevel, sTime,eTime,(page-1)*20, 20);
				totalRecord = userStatisticsDao.getUserInfoCount(uid,fuuid,nickName,startLevel, endLevel, sTime,eTime);							
			}
				List<Recharge_Channel> channelList = channelDao.getRecharge_ChannelList();//渠道
				for(Map<String,Object> map : userInfoList){
					long uid1 = Long.parseLong(map.get("uid").toString());
					Long sumSpending = userStatisticsDao.getSumVirtual(uid1,sTime,eTime);//消费总数
					if(sumSpending == null){
						sumSpending = 0L;
					}
					map.put("sumSpending", sumSpending);					
					if(!map.containsKey("channel"))continue;
					String channel = map.get("channel").toString();
					for(Recharge_Channel recharge_Channel : channelList){//添加渠道名称
						if(channel.equals(recharge_Channel.getChannel())){
							map.put("channelName", recharge_Channel.getName());
						}
					}
					Long rmb = rechargeDao.getRechargeByUid(uid1,sTime,eTime);//充值
					map.put("rmb", rmb);
			/*		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long start = sdf.parse(sTime).getTime();
					Long end = sdf.parse(eTime).getTime();
					Long totalStake = userStatisticsDao.getUserStake(uid1,start,end);//游戏流水（玩）
				map.put("totalStake", totalStake);
					*/									
				}			
				listSort(userInfoList);		
				PageUtil pageUtil = new PageUtil(20, totalRecord, page);
				pageUtil.setTotalRecord(totalRecord);
				pageUtil.setPageNumStart(pageUtil.getPageNumStart());
				pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
				pageUtil.setCurrentPage(page);
				pageUtil.setColumns(14);
				pageUtil.setUrlName("userStatistics/list");
				model.addAttribute("showPage", pageUtil);
				model.addAttribute("userInfoList", userInfoList);
				model.addAttribute("activeUrl", "userStatistics");
				model.addAttribute("uid", uid);
				model.addAttribute("fuuid", fuuid);
				model.addAttribute("nickName", nickName);
				model.addAttribute("level", level);
				model.addAttribute("startTime", startTime);
				model.addAttribute("endTime", endTime);		
		}catch(Exception e){
			log.error("用户统计管理", e);
		}
		
		return "userstatistics/list";
	}
	
	
	public void listSort(List<Map<String,Object>> resultList) throws Exception{  
        // resultList是需要排序的list，其内放的是Map  
        // 返回的结果集  
        Collections.sort(resultList,new Comparator<Map<String,Object>>() {  

         public int compare(Map<String, Object> o1,Map<String, Object> o2) {  

          //o1，o2是list中的Map，可以在其内取得值，按其排序，此例为升序，s1和s2是排序字段值  
        	 long s1 = (Long) o1.get("sumSpending");  
        	 long s2 = (Long) o2.get("sumSpending");  

          if(s1<s2) {  
           return 1;  
          }else {  
           return -1;  
          }  
         }  
        });  
         
       }


	public String exportExcel(long uid, long fuuid, String nickName, String level, String startTime, String endTime,
			HttpServletResponse response) {
		try{
			int startLevel = 0, endLevel = 0;
			if (!StringUtils.isBlank(level)) {
				if(level.contains("-"))
				{
					String[] levelStrings = level.split("-");
					startLevel = Integer.parseInt(levelStrings[0]);
					endLevel = Integer.parseInt(levelStrings[1]);
				}
				else {
					startLevel = Integer.parseInt(level);
					endLevel = Integer.parseInt(level);
				}
			}
			ServletOutputStream outputStream = response.getOutputStream();
			if(!startTime.equals("") && !endTime.equals("")){
				String sTime = startTime+" "+"00:00:00";
				String eTime = endTime+" "+"23:59:59";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long start = sdf.parse(sTime).getTime();
				Long end = sdf.parse(eTime).getTime();
				List<Map<String,Object>> exportExcelList = userStatisticsDao.getExportExcelList(uid,fuuid,nickName,startLevel, endLevel, sTime,eTime);
				List<Recharge_Channel> channelList = channelDao.getRecharge_ChannelList();//渠道
				for(Map<String,Object> map : exportExcelList){
					if(map.containsKey("channel")){
						String channel = map.get("channel").toString();
						for(Recharge_Channel recharge_Channel : channelList){//添加渠道名称
							if(channel.equals(recharge_Channel.getChannel())){
								map.put("channelName", recharge_Channel.getName());
								break;
							}else{
								map.put("channelName", "-");
							}
						}
					}else{
						map.put("channelName", "-");
					}
					long uid1 = Long.parseLong(map.get("uid").toString());
					Long sumSpending = 0L;
					sumSpending = userStatisticsDao.getSumVirtual(uid1,sTime,eTime);//消费总数
					if(sumSpending == null){
						sumSpending = 0L;
					}
					map.put("sumSpending", sumSpending);
					/*Long totalStake = userStatisticsDao.getUserStake(Long.parseLong(map.get("uid").toString()),start,end);//游戏流水（玩）
					if(totalStake == null || totalStake.equals("")){
						totalStake = 0L;
					}
					map.put("totalStake", totalStake);*/
					Long recharge = rechargeDao.getRechargeByUid(Long.parseLong(map.get("uid").toString()),sTime,eTime);//充值
					if(recharge == null || recharge.equals("")){
						recharge = 0L;
					}
					map.put("recharge",  recharge);
					map.put("register_time", map.get("register_time").toString().subSequence(0, 19));
				}
				String fileName = new String(("用户统计管理").getBytes(), "ISO8859_1");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
				// 创建一个workbook 对应一个excel应用文件  
				XSSFWorkbook workBook = new XSSFWorkbook();  
				// 在workbook中添加一个sheet,对应Excel文件中的sheet  
				XSSFSheet sheet = workBook.createSheet("sheet1"); 
				String[] titles = new String[]{"UID","房间号","等级","昵称","渠道","注册时间","充值金额","消费钻石数","钻石余额"};
				for(int i=0;i<titles.length;i++){
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
				for (Map<String,Object> map  : exportExcelList) {

					
					XSSFRow bodyRow = sheet.createRow(j + 1); 

					cell = bodyRow.createCell(0);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("uid").toString());  

					cell = bodyRow.createCell(1);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("f_uuid").toString());   

					cell = bodyRow.createCell(2);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("level").toString());
					
					cell = bodyRow.createCell(3);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("nickname").toString());

					cell = bodyRow.createCell(4);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("channelName").toString());
					
					cell = bodyRow.createCell(5);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("register_time").toString());
					
			/*		cell = bodyRow.createCell(6);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("totalStake").toString());*/
					
					cell = bodyRow.createCell(6);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("recharge").toString());
					
					cell = bodyRow.createCell(7);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("sumSpending").toString());
					
					cell = bodyRow.createCell(8);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(map.get("balance_virtual").toString());

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
			}
		} catch (Exception e) {
			log.error("用户统计管理", e);
		}
	    
		return null;
	}  
}

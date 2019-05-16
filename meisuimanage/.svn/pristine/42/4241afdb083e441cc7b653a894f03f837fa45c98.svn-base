package com.meisui.manage.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.meisui.manage.dao.Iactivity_StarDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Activity_Star_Anchor;
import com.meisui.manage.entity.Activity_Star_Competition;
import com.meisui.manage.entity.Activity_Star_Grading_Rule;
import com.meisui.manage.entity.Activity_Star_Manage_Record;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class Activity_StarService {
	private static Logger log = Logger.getLogger(Activity_StarService.class.getClass());
	@Autowired
	private  Iactivity_StarDao iactivity_starDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IuserDao iuserdao;
	@Autowired
	private Ioperate_CenterDao ioperate_centerDao;

	public String getActivityList(int uid, String nickname, int page, Model model) {
		try {
			Date date=new Date();
			int count=0;
			List<Activity_Star_Anchor> activityList = iactivity_starDao.getActivityStartList(uid,nickname, (page-1)*20, 20);
			for(Activity_Star_Anchor activitystaranchor : activityList){
				
				User user=iuserdao.getUser((long)activitystaranchor.getUid());
				activitystaranchor.setNickname(user.getNickname());
				
				Operate_Center operatecenter=ioperate_centerDao.getOperateCenterById(user.getOperate_center_id());
				activitystaranchor.setOperatename(operatecenter.getName());	
				 count= iactivity_starDao.getStarCompetition(activitystaranchor.getUid(),date);//当前时间，该用户是否存在pk
				 activitystaranchor.setCount(count);
				 
				 Activity_Star_Grading_Rule gradingrule=iactivity_starDao.getactivitygrading(activitystaranchor.getGrading());//通过段位规则表获取该段位名称和所需积分
				 activitystaranchor.setGradingname(gradingrule.getName());
			}
			int totalRecord = iactivity_starDao.getActivityStartCount(uid,nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("activitystar/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activityList", activityList);
			model.addAttribute("activeUrl", "activitystar");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
		} catch (Exception e) {
			log.error("最强新星主播列表", e);
		}
		return "activitystaranchor/list";
	}

	public String editScore(int id,int uid, int addscore,int count, HttpServletRequest request) {
		try
		{
			if(count ==0){
				String w_name = String.valueOf(request.getAttribute("w_name"));
				Date date = new Date();
				Activity_Star_Anchor activity=iactivity_starDao.getstaranchorbyid(id);
				int grading=0;
				if(activity.getScore() + addscore<0){
					return "{\"code\":-1,\"msg\":\"积分不能为负数\"}";
				}
				List<Activity_Star_Grading_Rule> gradinglist=iactivity_starDao.getactivitygradingrule();
				for(Activity_Star_Grading_Rule list : gradinglist){
					if(iactivity_starDao.getactivitygrading(list.getId()+1)!=null){
						if(activity.getScore() + addscore >= iactivity_starDao.getactivityscore(list.getId()) && activity.getScore() + addscore< iactivity_starDao.getactivityscore(list.getId()+1)){
							grading=list.getId();
						}	
					}else{
						if(activity.getScore() + addscore >= iactivity_starDao.getactivityscore(list.getId())){
							grading=list.getId();
						}	
					}
									
				}				
				
				int result = iactivity_starDao.updateactivitystaranchor(id, addscore,grading);
				if(result>0)
				{
					Activity_Star_Manage_Record activitystarrecord=new Activity_Star_Manage_Record();
					activitystarrecord.setUid(uid);
					activitystarrecord.setAdd_score(addscore);
					activitystarrecord.setW_name(w_name);
					activitystarrecord.setCreate_time(date);
					iactivity_starDao.insertstarrecord(activitystarrecord);
				}
				return "{\"code\":0,\"msg\":\"保存成功\"}";	
			}else if(count >1){
				return "{\"code\":-1,\"msg\":\"保存失败\"}";
			}					
		}
		catch (Exception e) {
			log.error("最强新星编辑活动积分",e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";	
	}

	public String getPKRecord(int id,int uid,int page, Model model) {
		try {
			Date date=new Date();
		
			List<Activity_Star_Competition> competionList = iactivity_starDao.getPKRecord(uid,(page-1)*20, 20);
			for(Activity_Star_Competition competion:competionList){
				Activity_Star_Anchor starahcor=iactivity_starDao.getstaranchorbyid(id);
				if(uid==competion.getF_uid()){//如果该uid==f_uid,即是发起者uid
					User user=iuserdao.getUser((long)competion.getT_uid());
					competion.setNickname(user.getNickname());//PK对象
					Activity_Star_Grading_Rule gradingrule=iactivity_starDao.getactivitygrading(starahcor.getGrading());
					competion.setGradingname(gradingrule.getName());//段位名称
					competion.setWinscore(competion.getF_score());//pk获得的积分
					competion.setPkwinscore(competion.getT_score());//pk对象获得的积分
					
					if (competion.getStart_time().getTime() > date.getTime() && competion.getIs_cancel()==0) {// 若开始时间大于当前时间，则表示未开始
						competion.setStatus(0);
					} else if (competion.getEnd_time().getTime() < date.getTime() && competion.getIs_cancel()==0) {// 若结束时间小于当前时间，表示活动结束，下线
						competion.setStatus(2);
					} else if (competion.getStart_time().getTime() < date.getTime() && competion.getEnd_time().getTime() > date.getTime() && competion.getIs_cancel()==0) {//正在进行中
						competion.setStatus(1);
					}else if(competion.getIs_cancel()==1){//已取消
						competion.setStatus(3);
					}					
				}else if(uid==competion.getT_uid()){//如果uid==t_uid,即是接受者uid
					User user=iuserdao.getUser((long)competion.getF_uid());
					competion.setNickname(user.getNickname());//PK对象
					Activity_Star_Grading_Rule gradingrule=iactivity_starDao.getactivitygrading(starahcor.getGrading());
					competion.setGradingname(gradingrule.getName());//段位名称
					competion.setWinscore(competion.getT_score());//pk获得的积分
					competion.setPkwinscore(competion.getF_score());//pk对象获得的积分
					if (competion.getStart_time().getTime() > date.getTime() && competion.getIs_cancel()==0) {// 若开始时间大于当前时间，则表示未开始
						competion.setStatus(0);
					} else if (competion.getEnd_time().getTime() < date.getTime() && competion.getIs_cancel()==0) {// 若结束时间小于当前时间，表示活动结束，下线
						competion.setStatus(2);
					} else if (competion.getStart_time().getTime() < date.getTime() && competion.getEnd_time().getTime() > date.getTime() && competion.getIs_cancel()==0) {//正在进行中
						competion.setStatus(1);
					}else if(competion.getIs_cancel()==1){//已取消
						competion.setStatus(3);
					}	
					
				}
				
			}
			
			int totalRecord = iactivity_starDao.getPKRecordCount(uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("activitystar/starrecord");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("competionList", competionList);
			model.addAttribute("activeUrl", "activitystar");
			model.addAttribute("uid", uid);
		} catch (Exception e) {
			log.error("PK记录列表", e);
		}
		return "activitystaranchor/starcompetition";
	}

	public int updateCancelOne(int id, String w_name, HttpServletRequest request) {
		int result = 0;
		try {	
			Date date=new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = iactivity_starDao.updateCancel(id,w_name,date);
			if (result > 0) {			
				imanage_RecordDao.insertManageRecord(w_name, "取消本场：", "t_activity_star_competition",id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("最强新星取消本场", e);
		}
		return result;
	}

	public int updateCancel(int id, int count,String w_name, HttpServletRequest request) {
		int result = 0;
		try {	
			if(count==0){
				result = iactivity_starDao.updateStartAnchorCancel(id);
				if (result > 0) {
					Date date=new Date();
					w_name = String.valueOf(request.getAttribute("w_name"));
					iactivity_starDao.updateAnchorScore(id,w_name,date);//取消参赛资格后，活动积分归0					
					imanage_RecordDao.insertManageRecord(w_name, "取消本场：", "t_activity_star_competition",id, IPUtil.getIp(request), new Date());
				}
			}else{
				result=0;
			}
			
		} catch (Exception e) {
			log.error("最强新星取消参赛资格", e);
		}
		return result;
	}

	public String exportExcel(int uid, String nickname, HttpServletResponse response) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			String fileName = new String(("最强新星参赛主播列表").getBytes(), "ISO8859_1");  
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
			String[] titles = new String[]{"主播UID","主播昵称","运营中心","胜场","负场","总场","段位","排行","活动积分"};
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  		
			List<Activity_Star_Anchor> activityList = iactivity_starDao.getActivityStartList(uid,nickname, 0, Integer.MAX_VALUE);
			for(Activity_Star_Anchor activitystaranchor : activityList){
				
				User user=iuserdao.getUser((long)activitystaranchor.getUid());
				activitystaranchor.setNickname(user.getNickname());
				
				Operate_Center operatecenter=ioperate_centerDao.getOperateCenterById(user.getOperate_center_id());
				activitystaranchor.setOperatename(operatecenter.getName());	
			}
			for (int j = 0; j < activityList.size(); j++)  
			{  
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(activityList.get(j).getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(activityList.get(j).getNickname());   

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(activityList.get(j).getOperatename());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(activityList.get(j).getWin());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(activityList.get(j).getLose());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(activityList.get(j).getTotal());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle); 
				List<Activity_Star_Grading_Rule> gradinglist=iactivity_starDao.getactivitygradingrule();
				for(Activity_Star_Grading_Rule grading : gradinglist){
					if(activityList.get(j).getGrading()==grading.getId()){
						cell.setCellValue(grading.getName());
					}
					
				}

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(activityList.get(j).getRank());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(activityList.get(j).getScore());

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
			log.error("导出参赛主播列表", e);
		}
		return null;
	}

	public String editTime(int id, int status, String start_time, String end_time, HttpServletRequest request) {
		try
		{
			if(status ==0){
				String w_name = String.valueOf(request.getAttribute("w_name"));
				Date date = new Date();	
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
				Date s_time = format.parse(start_time); 
				Date e_time = format.parse(end_time); 
				int result = iactivity_starDao.updatecompetitiontime(id,s_time,e_time,date,w_name);//更新PK时间
				if(result>0)
				{
					imanage_RecordDao.insertManageRecord(w_name, "编辑PK时间：", "t_activity_star_competition",id, IPUtil.getIp(request), new Date());
					return "{\"code\":0,\"msg\":\"保存成功\"}";	
				}
				
			}else if(status >1){
				return "{\"code\":-1,\"msg\":\"保存失败\"}";
			}					
		}
		catch (Exception e) {
			log.error("最强新星编辑PK时间",e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";	
	}

}

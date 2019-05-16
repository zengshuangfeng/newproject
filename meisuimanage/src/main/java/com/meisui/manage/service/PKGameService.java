package com.meisui.manage.service;

import java.io.IOException;
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

import com.meisui.manage.dao.IPKGameDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.PK_Result;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class PKGameService {
	private static Logger log = Logger.getLogger(PKGameService.class.getClass());
	@Autowired
	private  IPKGameDao ipkgameDao;
	@Autowired
	private IuserDao iuserdao;
	@Autowired
	private Ioperate_CenterDao ioperatecenterdao;
	public String getPKGameList(int uid, String nickname, int page, Model model) {
		try {	
			List<PK_Result> pkList = ipkgameDao.getPKFuuidList(uid,nickname, (page-1)*20, 20);//对记录去重，或许唯一的主播房间号
			int rank=(page-1)*20;
			
			if(pkList!=null){
				for(PK_Result pkresult :pkList){
					
					PK_Result pk_Result=ipkgameDao.getPKresultbyfuuid(pkresult.getAnchor_f_uuid(),-1);//获取得到该用户的总场数
					pkresult.setAll_count(pk_Result.getCount());
					PK_Result pk_Result2=ipkgameDao.getPKresultbyfuuid(pkresult.getAnchor_f_uuid(),0);//获取得到该用户的负场
					pkresult.setFail_count(pk_Result2.getCount());
					PK_Result pk_Result3=ipkgameDao.getPKresultbyfuuid(pkresult.getAnchor_f_uuid(),1);//获取得到该用户的胜场
					pkresult.setSuccess_count(pk_Result3.getCount());
					
					User user=iuserdao.getUserByF_uuid((long)pkresult.getAnchor_f_uuid());
					pkresult.setNickname(user.getNickname());//主播昵称
					pkresult.setUid((int)user.getId());//主播uid
					
					Operate_Center operatecenter=ioperatecenterdao.getOperateCenterById(user.getOperate_center_id());
					pkresult.setOperate_name(operatecenter.getName());//运营中心名称
					
					User_Info userinfo=iuserdao.getUserInfo(user.getId());
					pkresult.setGrading_score(userinfo.getGrading_score());//段位积分
					pkresult.setGrading(userinfo.getGrading());//段位
					rank=rank+1;//排名
					pkresult.setRank(rank);
				}		
			}	
			
			int totalRecord = ipkgameDao.getPKGameCount(uid,nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("pkgame/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("pkList", pkList);
			model.addAttribute("activeUrl", "pkgame");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
		} catch (Exception e) {
			log.error("PK大乱斗主播列表", e);
		}
		return "pkgame/list";
	}
	public String getPKGameRecord(int anchor_f_uuid, int page, Model model) {
		try {	
			List<PK_Result> pkList = ipkgameDao.getPKGameRecordList(anchor_f_uuid,(page-1)*20, 20);//根据主播房间号获得数据
			if(pkList !=null){
				for(PK_Result result:pkList){			
					PK_Result pkresult=ipkgameDao.getpkResultByGameuuid(result.getGameuuid(),result.getAnchor_f_uuid());			
					result.setOp_anchor_f_uuid(	pkresult.getAnchor_f_uuid());//pk对象房间号
					User user=iuserdao.getUserByF_uuid((long)pkresult.getAnchor_f_uuid());
					result.setNickname(user.getNickname());//pk对象昵称
					
					User_Info userinfo=iuserdao.getUserInfo(user.getId());
					result.setGrading(userinfo.getGrading());//当前段位
				}
			}			
			int totalRecord = ipkgameDao.getPKGameRecordCount(anchor_f_uuid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("pkgame/pkrecord");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("pkList", pkList);
			model.addAttribute("activeUrl", "pkgame");
		} catch (Exception e) {
			log.error("PK记录", e);
		}
		return "pkgame/competitionrecord";
	}
	public String exportExcel(int uid, String nickname, HttpServletResponse response) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();

			Date end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 23:59:59");
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(end_date); 
			calendar.add(Calendar.DATE,-1);
			Date start_date=calendar.getTime();
			String fileName = new String(("PK大乱斗参赛主播列表"+AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
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
			
		
			// 构建表体数据  
			List<PK_Result> pkList = ipkgameDao.getPKFuuidList(uid,nickname, 0, 1000000000);//对记录去重，或许唯一的主播房间号
			int rank=0;
			
			if(pkList!=null){
				for(PK_Result pkresult :pkList){
					
					PK_Result pk_Result=ipkgameDao.getPKresultbyfuuid(pkresult.getAnchor_f_uuid(),-1);//获取得到该用户的总场数
					pkresult.setAll_count(pk_Result.getCount());
					PK_Result pk_Result2=ipkgameDao.getPKresultbyfuuid(pkresult.getAnchor_f_uuid(),0);//获取得到该用户的负场
					pkresult.setFail_count(pk_Result2.getCount());
					PK_Result pk_Result3=ipkgameDao.getPKresultbyfuuid(pkresult.getAnchor_f_uuid(),1);//获取得到该用户的胜场
					pkresult.setSuccess_count(pk_Result3.getCount());
					
					User user=iuserdao.getUserByF_uuid((long)pkresult.getAnchor_f_uuid());
					pkresult.setNickname(user.getNickname());//主播昵称
					pkresult.setUid((int)user.getId());//主播uid
					
					Operate_Center operatecenter=ioperatecenterdao.getOperateCenterById(user.getOperate_center_id());
					pkresult.setOperate_name(operatecenter.getName());//运营中心名称
					
					User_Info userinfo=iuserdao.getUserInfo(user.getId());
					pkresult.setGrading_score(userinfo.getGrading_score());//段位积分
					pkresult.setGrading(userinfo.getGrading());//段位
					rank=rank+1;
					pkresult.setRank(rank);
				}		
			}	
		
	
			for (int j = 0; j < pkList.size(); j++)  
			{  
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(pkList.get(j).getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(pkList.get(j).getNickname());   

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(pkList.get(j).getOperate_name());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(pkList.get(j).getSuccess_count());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(pkList.get(j).getFail_count());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(pkList.get(j).getAll_count());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle); 
				if(pkList.get(j).getGrading()==0){
					cell.setCellValue("佳人");	
				}else if(pkList.get(j).getGrading()==1){
					cell.setCellValue("粉黛");
				}else if(pkList.get(j).getGrading()==2){
					cell.setCellValue("蛾眉");
				}else if(pkList.get(j).getGrading()==3){
					cell.setCellValue("玉人");
				}else if(pkList.get(j).getGrading()==4){
					cell.setCellValue("璧人");
				}else if(pkList.get(j).getGrading()==5){
					cell.setCellValue("佼人");
				}else if(pkList.get(j).getGrading()==6){
					cell.setCellValue("丽人");
				}else if(pkList.get(j).getGrading()==7){
					cell.setCellValue("伊人");
				}else if(pkList.get(j).getGrading()==8){
					cell.setCellValue("玉女");
				}else if(pkList.get(j).getGrading()==9){
					cell.setCellValue("淑女");
				}else if(pkList.get(j).getGrading()==10){
					cell.setCellValue("尤物");
				}else if(pkList.get(j).getGrading()==11){
					cell.setCellValue("娇娃");
				}else if(pkList.get(j).getGrading()==12){
					cell.setCellValue("西施");
				}else if(pkList.get(j).getGrading()==13){
					cell.setCellValue("青娥");
				}
						
				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(pkList.get(j).getRank());
				
				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(pkList.get(j).getGrading_score());
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
			log.error("导出PK大乱斗参赛主播列表", e);
		}
		return null;
	}
}

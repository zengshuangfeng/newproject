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

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IAnchor_GuardDao;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.Ianchor_Guard_ManageDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Anchor_Guard_Change;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.Anchor_Guard_Manager;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PageUtil;
@Service
public class Anchor_Guard_ManageService {
	private static Logger log = Logger.getLogger(Anchor_Guard_ManageService.class.getClass());
	@Autowired
	private Ianchor_Guard_ManageDao ianchor_guard_manageDao;
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
	@Autowired
	private IAnchor_GuardDao ianchor_guardDao;
	
	public String getList(long uid, String f_uuid, String anchor_f_uuid, int operate_center_id, int agent_id, int type,
			String nickname, Date start_time, Date end_time, int page, Model model) {
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
			
			
		
			List<Anchor_Guard_Manager> anchor_Guard_ManageList = ianchor_guard_manageDao.getAnchorGuardManageList(uid, f_uuid, anchor_f_uuid, type, nickname, start_time, end_time, operate_center_id,agent_id,(page-1)*20, 20);
			int totalRecord = ianchor_guard_manageDao.getAnchorGuardManageCount(uid, f_uuid, anchor_f_uuid, type, nickname, start_time, end_time, operate_center_id,agent_id);
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
			for(Anchor_Guard_Manager manage:anchor_Guard_ManageList){
			User user=iuserDao.getUser((long)manage.getUid());//获取用户信息
			manage.setNickname(user.getNickname());//用户昵称
			manage.setF_uuid(user.getF_uuid());//用户房间号
			User us=iuserDao.getUserByF_uuid(Long.parseLong(manage.getAnchor_f_uuid()));//用主播房间号获取主播信息
			manage.setAnchor_nickname(us.getNickname());//主播昵称
			Anchor_Guard_Change2 anchorchange=ianchor_Guard_ChangeDao.getAnchorGuardNewByid(manage.getChange_id());
			manage.setType(anchorchange.getType());
			//用户所属营运中心名称
			User useroprate=iuserDao.getUserByUid((long)manage.getUid());
			if(useroprate.getOperate_center_id()>0){
				for (Operate_Center operate_Center : operateCenterList) {
					if(operate_Center.getId()==user.getOperate_center_id())
					{
						manage.setOperate_name(operate_Center.getName());
						break;
					}
				}
			}			
			//邀请所属代理平台
			if(useroprate.getAgent_id()>0){
				Agent agent=iagentdao.getAgentNameByagentid(useroprate.getAgent_id());
				if(agent!=null)
					manage.setAgentname(agent.getName());			
			}
			//邀请所属推广员		
			if(useroprate.getAgent_promoter_id()>0){
				Agent_Promoter agent_promoter=iagent_promoterdao.getProNameByid(useroprate.getAgent_promoter_id());
				if(agent_promoter!=null){
					long apuid=agent_promoter.getUid();
					manage.setPromotername(iuserDao.getUserNickNameByapuid(apuid));					
				}					
			}
		
			}

			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentdao.getAgentListByCenterId(operate_center_id);
			}		
			List<Anchor_Guard_Change> anchorlist=ianchor_guardDao.getGuardChange();
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("guardmanage/list");
			model.addAttribute("manageList", anchor_Guard_ManageList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "guardmanage");
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("anchor_f_uuid", anchor_f_uuid);
			model.addAttribute("type", type);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("agent_id", agent_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", e_timeString);
			model.addAttribute("operate_CenterList", operateCenterList);
			model.addAttribute("agentList", agentList);
			model.addAttribute("anchorlist", anchorlist);

		}
		catch (Exception e) {
			log.error("守护赠送记录", e);
		}
		return "guardmanage/list";
	}

	public String exportExcel(long uid, String f_uuid, String anchor_f_uuid, int operate_center_id, int agent_id,
			int type, String nickname, Date start_time, Date end_time, HttpServletResponse response) {
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

			List<Anchor_Guard_Manager> anchor_Guard_ManageList = ianchor_guard_manageDao.getAnchorGuardManageList(uid, f_uuid, anchor_f_uuid, type, nickname, start_time, end_time, operate_center_id,agent_id,0, 1000000000);
			int totalRecord = ianchor_guard_manageDao.getAnchorGuardManageCount(uid, f_uuid, anchor_f_uuid, type, nickname, start_time, end_time, operate_center_id,agent_id);
			List<Operate_Center> operateCenterList = ioperate_centerdao.getListAll();
	

			List<Agent> agentList = new ArrayList<>();
			if(operate_center_id>0)
			{
				agentList = iagentdao.getAgentListByCenterId(operate_center_id);
			}		
			List<Anchor_Guard_Change> anchorlist=ianchor_guardDao.getGuardChange();
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles = new String[]{"用户房间号","用户","守护时间","守护的主播","守护类型","所属运营中心","所属代理平台","所属推广员","赠送时间"};


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
			for(Anchor_Guard_Manager manage:anchor_Guard_ManageList){
				User user=iuserDao.getUser((long)manage.getUid());//获取用户信息
				manage.setNickname(user.getNickname());//用户昵称
				manage.setF_uuid(user.getF_uuid());//用户房间号
				User us=iuserDao.getUserByF_uuid(Long.parseLong(manage.getAnchor_f_uuid()));//用主播房间号获取主播信息
				manage.setAnchor_nickname(us.getNickname());//主播昵称
				Anchor_Guard_Change2 anchorchange=ianchor_Guard_ChangeDao.getAnchorGuardNewByid(manage.getChange_id());
				manage.setType(anchorchange.getType());
				//用户所属营运中心名称
				User useroprate=iuserDao.getUserByUid((long)manage.getUid());
				if(useroprate.getOperate_center_id()>0){
					for (Operate_Center operate_Center : operateCenterList) {
						if(operate_Center.getId()==user.getOperate_center_id())
						{
							manage.setOperate_name(operate_Center.getName());
							break;
						}
					}
				}			
				//邀请所属代理平台
				if(useroprate.getAgent_id()>0){
					Agent agent=iagentdao.getAgentNameByagentid(useroprate.getAgent_id());
					if(agent!=null)
						manage.setAgentname(agent.getName());			
				}
				//邀请所属推广员		
				if(useroprate.getAgent_promoter_id()>0){
					Agent_Promoter agent_promoter=iagent_promoterdao.getProNameByid(useroprate.getAgent_promoter_id());
					if(agent_promoter!=null){
						long apuid=agent_promoter.getUid();
						manage.setPromotername(iuserDao.getUserNickNameByapuid(apuid));					
					}					
				}
				
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(manage.getF_uuid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(manage.getNickname());  

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("开始时间："+sdf2.format(manage.getStart_time())+"\n结束时间："+sdf2.format(manage.getEnd_time()));  

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue("房间号："+manage.getAnchor_f_uuid()+"\n昵称："+manage.getAnchor_nickname());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				if(manage.getType()==0){
					cell.setCellValue("包月守护");
				}else if(manage.getType()==1){
					cell.setCellValue("包季守护");
				}else if(manage.getType()==2){
					cell.setCellValue("包年守护");
				}
				

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(manage.getOperate_name());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(manage.getAgentname());
				

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(manage.getPromotername());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 String dateString = formatter.format(manage.getCreate_time());
				cell.setCellValue(dateString);

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
			log.error("导出守护赠送记录", e);
		}
		return null;
	}

	public String getGuardAdd(Model model) {
		List<Anchor_Guard_Change2> anchorlist=ianchor_guardDao.getGuardChangeNew();
		model.addAttribute("activeUrl", "guardmanage");
		model.addAttribute("anchorlist", anchorlist);
		return "guardmanage/add";
	}

}

package com.meisui.manage.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.forman.foundation.library.DoubleUtil;
import com.meisui.manage.dao.Ianchor_PayDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.Ianchor_Virtual_Change_RecordDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Pay;
import com.meisui.manage.entity.Anchor_Pay_Illegal;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Anchor_Union_Pay;
import com.meisui.manage.entity.Anchor_Virtual_Change_Record;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * 
 * <p>文件名称：Anchor_DayPayService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年11月7日下午3:15:05 
 */
@Service
public class Anchor_DayPayService {

	private static Logger log = Logger.getLogger(Anchor_DayPayService.class.getClass());
	
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private Ianchor_PayDao ianchor_PayDao;
	@Autowired
	private Ianchor_Virtual_Change_RecordDao ianchor_Virtual_Change_RecordDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;	


	
	public String getList(long uid,long f_uuid,String nickname,int union_id,int page,Model model){
		try {
			List<Anchor_Pay> anchorPayList = new ArrayList<Anchor_Pay>();
			Date date = new Date();

			Date start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date,"yyyy-MM-dd")+" 00:00:00");//今日00:00:00
			Date end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date,"yyyy-MM-dd")+" 23:59:59");//今日23:59:59
			
			int is_show_pay = 0;//结算按钮是否可用
			
			List<Anchor_Union> anchorUnionList = ianchor_UnionDao.getDaySettleAnchorUnionList();//t_anchor_union 工会表 ,id、name
			for (Anchor_Union anchor_Union : anchorUnionList) {
	
				if(ianchor_PayDao.getAnchorUnionPayExist(anchor_Union.getId(), start_time, end_time)!=null)//查询list中的每个工会今天是否有过结算 t_anchor_union_pay 工会结算记录表   id
					anchor_Union.setIs_pay(1);

				if(union_id>0&&union_id==anchor_Union.getId()&&anchor_Union.getIs_pay()==0)//搜索工会不为空，经上面一步处理，工会属于未结算的
					is_show_pay = 1;
			}
			List<User_Anchor> userAnchorList = new ArrayList<User_Anchor>();

			int totalRecord = 0;
			if(is_show_pay==1)//如果工会属于未结算完成的
			{
				if(union_id>0 || uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))//工会或UID或房间号或昵称不为空
					userAnchorList = iuserDao.getUserAnchorPayList(uid, f_uuid, nickname, union_id, null,1,(page-1)*20, 20);//t_user_anchor 主播表    
				for (User_Anchor user_Anchor : userAnchorList) {
					Anchor_Pay anchor_Pay = ianchor_PayDao.getAnchorPay(user_Anchor.getUid(), union_id, start_time, null,1);//t_anchor_pay 主播结算记录表
					if(anchor_Pay==null)//结算记录为空
					{
						anchor_Pay = new Anchor_Pay();
						anchor_Pay.setUid(user_Anchor.getUid());
						anchor_Pay.setF_uuid(user_Anchor.getF_uuid());
						anchor_Pay.setTotal_anchor_virtual(user_Anchor.getTotal_anchor_virtual());//总魅力值
						Long total_stake = 0L;//主播收到游戏流水，即用户押在该主播间游戏的总钻石数
						User_Info user_Info = iuserDao.getUserInfo(user_Anchor.getUid());
						anchor_Pay.setDivide(user_Info.getDivide_proportion());
						anchor_Pay.setSurplus_anchor_virtual(user_Anchor.getSurplus_anchor_virtual());
						double divide_proportion = DoubleUtil.div(anchor_Pay.getDivide(),100, 2);
						double total_rmb = DoubleUtil.div(user_Anchor.getSurplus_anchor_virtual(), 100, 2);
						double total_money = DoubleUtil.mul(total_rmb,divide_proportion);//总收益(元)
						anchor_Pay.setTotal_money(total_money);
						Date last_pay_date = ianchor_PayDao.getAnchorLastPayDate(user_Anchor.getUid());//t_anchor_pay  最后支付日期
						if(last_pay_date==null)
							//直播以来到现在的游戏总流水
							total_stake = ianchor_PayDao.getUserTotalStake(user_Anchor.getF_uuid(),null,null);
						else
							total_stake = ianchor_PayDao.getUserTotalStake(user_Anchor.getF_uuid(), last_pay_date.getTime(), date.getTime());//统计上次结算到现在游戏的总流水
						
						if(total_stake==null)
							total_stake = 0L;
						anchor_Pay.setTotal_stake(total_stake);

					}//if(anchor_Pay==null) end
					
					for (Anchor_Union anchor_Union : anchorUnionList) {
						if(anchor_Union.getId()==user_Anchor.getUnion_id())
						{
							anchor_Pay.setUnion_name(anchor_Union.getName());
							break;
						}
					}
					anchor_Pay.setType(user_Anchor.getType());
					anchor_Pay.setNickname(user_Anchor.getNickname());
					anchorPayList.add(anchor_Pay);
				}//for (User_Anchor user_Anchor : userAnchorList) end
				if(union_id>0 || uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))
					totalRecord = iuserDao.getUserAnchorPayCount(uid, f_uuid, nickname, union_id, start_time,1);
				if(uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))
					is_show_pay=0;
			}// if(is_show_pay==1) end
			else { //否则，认为是已结算完成的工会
				if(union_id>0 || uid!=0 || f_uuid!=0 || !StringUtils.isBlank(nickname))
				{
					anchorPayList = ianchor_PayDao.getAnchorPayList(uid, f_uuid, nickname, union_id, start_time, end_time,1,(page-1)*20, 20);
					for (Anchor_Pay anchor_Pay : anchorPayList) {
						User user = iuserDao.getUser(anchor_Pay.getUid());
						anchor_Pay.setNickname(user.getNickname());
						User_Anchor user_Anchor = iuserDao.getUserAnchor(anchor_Pay.getUid());
						anchor_Pay.setType(user_Anchor.getType());
						for (Anchor_Union anchor_Union : anchorUnionList) {
							if(anchor_Union.getId()==user_Anchor.getUnion_id())
							{
								anchor_Pay.setUnion_name(anchor_Union.getName());
								break;
							}
						}
					}
					totalRecord = ianchor_PayDao.getAnchorPayCount(uid, f_uuid, nickname, union_id, start_time, end_time,1);
				}
			}
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(18);
			pageUtil.setUrlName("anchordaypay/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorPayList", anchorPayList);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("activeUrl", "anchordaypay");
			model.addAttribute("union_id", union_id);
			model.addAttribute("anchorUnionList", anchorUnionList);
			model.addAttribute("is_show_pay", is_show_pay);
		} catch (Exception e) {
			log.error("主播日结列表", e);
		}
		return "anchordaypay/list";
	}
	
	public String  savePay(int union_id,String w_name, HttpServletRequest request){
		try{
			long total_clear_surplus_anchor_virtual = 0l;
			double total_settle_money = 0d;
			Date date = new Date();

			Date start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date,"yyyy-MM-dd")+" 00:00:00");
			Date end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date,"yyyy-MM-dd")+" 23:59:59");
			
			List<User_Anchor> userAnchorList = iuserDao.getUserAnchorPayList(0l, 0l, "", union_id, start_time, 1,0, 1000000);
			Anchor_Union_Pay anchor_Union_Pay = new Anchor_Union_Pay();
			anchor_Union_Pay.setUnion_id(union_id);
			anchor_Union_Pay.setStart_time(start_time);
			anchor_Union_Pay.setEnd_time(end_time);
			anchor_Union_Pay.setCreate_time(date);
			w_name = String.valueOf(request.getAttribute("w_name"));
			anchor_Union_Pay.setW_name(w_name);
			anchor_Union_Pay.setIs_commit(1);
			for (User_Anchor user_Anchor : userAnchorList) {
				if (user_Anchor.getSurplus_anchor_virtual()<15000) {
					continue;
				}
				Anchor_Pay anchor_Pay = ianchor_PayDao.getAnchorPay(user_Anchor.getUid(), union_id, start_time, null,1);
				if(anchor_Pay==null)//今日该用户结算记录为空
				{
					anchor_Pay = new Anchor_Pay();
					anchor_Pay.setUid(user_Anchor.getUid());
					anchor_Pay.setF_uuid(user_Anchor.getF_uuid());
					anchor_Pay.setTotal_anchor_virtual(user_Anchor.getTotal_anchor_virtual());
					anchor_Pay.setType(user_Anchor.getType());
					anchor_Pay.setUnion_id(union_id);
					Long total_stake =  0L;

					anchor_Pay.setSurplus_anchor_virtual(user_Anchor.getSurplus_anchor_virtual());
					Date last_pay_date = ianchor_PayDao.getAnchorLastPayDate(user_Anchor.getUid());
					if(last_pay_date==null)
					{   
						anchor_Pay.setStart_time(user_Anchor.getCreate_time());
						anchor_Pay.setEnd_time(date);
						total_stake = ianchor_PayDao.getUserTotalStake(user_Anchor.getF_uuid(),null,null);
					}
					else
					{
						anchor_Pay.setStart_time(last_pay_date);
						anchor_Pay.setEnd_time(date);
						total_stake = ianchor_PayDao.getUserTotalStake(user_Anchor.getF_uuid(), last_pay_date.getTime(), date.getTime());
					}
                    if (total_stake==null) {
						total_stake = 0l;
					}
					anchor_Pay.setTotal_stake(total_stake);
					User_Info user_Info = iuserDao.getUserInfo(user_Anchor.getUid());
					anchor_Pay.setDivide(user_Info.getDivide_proportion());
					anchor_Pay.setIs_commit(1);
					anchor_Pay.setCreate_time(date);
					anchor_Pay.setW_name(w_name);
	
					User user = iuserDao.getUser(anchor_Pay.getUid());
					anchor_Pay.setNickname(user.getNickname());
					double divide_proportion = DoubleUtil.div(anchor_Pay.getDivide(),100, 2);
					double total_rmb = DoubleUtil.div(anchor_Pay.getSurplus_anchor_virtual(), 100, 2);
					double total_money = DoubleUtil.mul(total_rmb,divide_proportion);//总收益(元)
					
					total_settle_money = DoubleUtil.sum(total_settle_money, total_money);
					anchor_Pay.setTotal_money(total_money);
					ianchor_PayDao.insertAnchorPay(anchor_Pay);
					if(anchor_Pay.getId()>0)
					{
						total_clear_surplus_anchor_virtual += anchor_Pay.getSurplus_anchor_virtual();
						iuserDao.updateUserSurplusAnchorVirtual(anchor_Pay.getSurplus_anchor_virtual(), anchor_Pay.getUid());
						Anchor_Virtual_Change_Record anchor_Virtual_Change_Record = new Anchor_Virtual_Change_Record();
						anchor_Virtual_Change_Record.setUid(anchor_Pay.getUid());
						anchor_Virtual_Change_Record.setW_name(w_name);
						anchor_Virtual_Change_Record.setChange_virtual(anchor_Pay.getSurplus_anchor_virtual());
						anchor_Virtual_Change_Record.setTotal_anchor_virtual(user_Anchor.getTotal_anchor_virtual());
						anchor_Virtual_Change_Record.setSurplus_anchor_virtual(user_Anchor.getSurplus_anchor_virtual()-anchor_Pay.getSurplus_anchor_virtual());
						Long total_change_virtual = ianchor_Virtual_Change_RecordDao.getAnchorVirtualChangeSum(anchor_Pay.getUid());
						if(total_change_virtual==null)
							total_change_virtual = 0L;
						anchor_Virtual_Change_Record.setTotal_change_virtual(total_change_virtual+anchor_Pay.getSurplus_anchor_virtual());
						anchor_Virtual_Change_Record.setRemark(String.valueOf(anchor_Pay.getGift_money()));
						anchor_Virtual_Change_Record.setCreate_time(date);
						ianchor_Virtual_Change_RecordDao.insertAnchorVirtualChangeRecord(anchor_Virtual_Change_Record);
					}
				}
			
			}//for (User_Anchor user_Anchor : userAnchorList) end
			ianchor_PayDao.insertAnchorUnionPay(anchor_Union_Pay);
			imanage_RecordDao.insertManageRecord(w_name, "主播日结-工会ID:"+union_id+"，本次总清除魅力值:"+total_clear_surplus_anchor_virtual+"；本次总结算金额:"+total_settle_money+"元", "t_anchor_pay", 0, IPUtil.getIp(request), date);
			return "{\"code\":0,\"msg\":\"结算成功\"}";
		} catch (Exception e) {
			log.error("主播日结列表-结算", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
   public String exportExcel(int union_id,HttpServletResponse response){
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			Date date = new Date();
			
			Date start_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date,"yyyy-MM-dd")+" 00:00:00");
			Date end_time = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date,"yyyy-MM-dd")+" 23:59:59");

			Anchor_Union anchor_Union =ianchor_UnionDao.getAnchorUnion(union_id);
			String fileName = new String((anchor_Union.getName()+"-今日清除魅力值记录").getBytes(), "ISO8859_1");  
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
			String[] titles = new String[]{"主播UID","房间号","昵称","工会","清除魅力值","分成占比","总收益","清除的时段","操作时间","操作人"};
			for (int i = 0; i < titles.length; i++)  
			{  
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles[i]);  
			}  
			List<Anchor_Pay> anchor_PayList = ianchor_PayDao.getAnchorPayList(0L, 0L, "", union_id, start_time, end_time,1, 0, 100000);
			// 构建表体数据  
			int j=0;
			double total_profit = 0d;
			for (Anchor_Pay anchor_Pay : anchor_PayList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 

				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Pay.getUid());  

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Pay.getF_uuid());   

				User user = iuserDao.getUser(anchor_Pay.getUid());
				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(user.getNickname());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(anchor_Union.getName());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getSurplus_anchor_virtual());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getDivide());
				
				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle); 
				total_profit = DoubleUtil.sum(total_profit, anchor_Pay.getTotal_money());
				cell.setCellValue(anchor_Pay.getTotal_money());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(""+anchor_Pay.getStart_time()+" 至 "+anchor_Pay.getEnd_time());

				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(sf.format(anchor_Pay.getCreate_time()));

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle); 
				cell.setCellValue(anchor_Pay.getW_name());

				j++;
			} 
			
			XSSFRow bodyRow = sheet.createRow(j + 1); 
			cell = bodyRow.createCell(5);  
			cell.setCellStyle(bodyStyle); 
			cell.setCellValue("合计");
			cell = bodyRow.createCell(6);  
			cell.setCellStyle(bodyStyle); 
			cell.setCellValue(""+total_profit+"元");
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
			log.error("导出今日清除魅力值记录", e);
		}	   
	   return null;
   }
}

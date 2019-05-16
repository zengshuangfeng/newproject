package com.meisui.manage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Manage_Record;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Product_Category_Service.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年4月5日 下午2:01:15
 */
@Service
public class Manage_RecordService {

	private static Logger log=Logger.getLogger(Manage_RecordService.class.getClass());
	@Autowired
	private Imanage_RecordDao Imanage_RecordDao;

	public void addManageRecord(String w_name,String action,String table_name,Integer t_id,String ip,Date create_time, HttpServletRequest request)
	{
		if(!action.contains("square2/getjson")&&!action.contains("square2/getwatchcount"))
		{
			w_name = String.valueOf(request.getAttribute("w_name"));
			Imanage_RecordDao.insertManageRecord(w_name, action, table_name, t_id, ip, create_time);
		}
	}
	/**
	 * 
	 * <p>功能描述：后台操作记录管理列表</p>
	 * <p>创建日期：2016年6月8日 上午11:20:43</p>
	 *
	 * @param page 页码
	 * @param s 操作人员
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param model
	 * @return
	 */
	public String  getManageRecordList(int page, String s,Date s_time, Date e_time, Model model) {
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			List<Manage_Record> manage_RecordList = Imanage_RecordDao.getManageRecordList(s,s_time,e_time,(page - 1) * 20, 20);
			int totalRecord = Imanage_RecordDao.getManageRecordCount(s,s_time,e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("managerecord/list");
			model.addAttribute("s_time", s_time != null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("manageRecordList", manage_RecordList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("s", s);
			model.addAttribute("activeUrl", "managerecord");
		} catch (Exception e) {
			log.error("后台操作记录列表异常",e);
		}
		return "managerecord/list";

	}
}

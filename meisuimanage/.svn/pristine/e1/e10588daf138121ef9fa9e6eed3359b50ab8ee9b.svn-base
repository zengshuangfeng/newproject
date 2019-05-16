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
import com.meisui.manage.dao.ImengYanDao;
import com.meisui.manage.entity.MengYan;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class MengYanService {
	private static Logger log = Logger.getLogger(MengYanService.class.getClass());
	@Autowired
	private ImengYanDao imengyandao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	public String getMengYanList(Date s_time, Date e_time, int page, Model model) {
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			
			List<MengYan> mengyanList = imengyandao.getMengYanList(s_time, e_time, (page-1)*20, 20);
			int totalRecord = imengyandao.getMengYanListCount(s_time, e_time);
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("mengyan/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("s_time", s_time != null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "mengyan");
			model.addAttribute("mengyanList", mengyanList);
		} catch (Exception e) {
			log.error("萌颜列表", e);
		}
		return "mengyan/list";
	}

	public String Save(int id, String pic, int type, int sort, HttpServletRequest request, Model model) {
		try{	
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date=new Date();
			MengYan mengyan = new MengYan();
			mengyan.setPic(pic);
			mengyan.setType(type);
			mengyan.setSort(sort);
			mengyan.setW_name(w_name);		
			mengyan.setIs_del(0);		
			int result = 0;
			if(id>0)
			{
				mengyan.setId(id);
				mengyan.setUpdate_time(date);
				result = imengyandao.updatemengyan(mengyan);
				imanage_RecordDao.insertManageRecord(w_name, "更新萌颜信息", "t_mengyan", mengyan.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				mengyan.setCreate_time(date);
				result = imengyandao.insertmengyan(mengyan);
				imanage_RecordDao.insertManageRecord(w_name, "添加萌颜信息", "t_mengyan", mengyan.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存H5信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String edit(int id, Model model) {
		try {
			MengYan mengyan = imengyandao.getMengYan(id);
			
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("mengyan", mengyan);
			model.addAttribute("activeUrl", "mengyan");
		} catch (Exception e) {
			log.error("萌颜信息编辑页面", e);
		}
		return "mengyan/edit";
	}

	public int delete(int id, HttpServletRequest request) {
		int result=0;
		try{
			
			String w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除萌颜信息", "t_mengyan", id, IPUtil.getIp(request), new Date());
			result=imengyandao.deleteMengyan(id);
			return result;
		} catch (Exception e) {
			log.error("萌颜删除", e);
			return result;
		}
	}
	
}

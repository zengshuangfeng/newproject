package com.meisui.manage.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IWeekGiftDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.Week_Gift;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

@Service
public class WeekGiftService {
	private static Logger log = Logger.getLogger(WeekGiftService.class.getClass());
	@Autowired
	private IWeekGiftDao iweekgiftDao;
	@Autowired
	private Igift_InfoDao igift_infoDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Igift_InfoDao giftinfodao;
	
	public String getWeekGiftList(int gift_id, String gift_name, int page, Model model) {
		try {		
			List<Week_Gift> weekgiftList = iweekgiftDao.getWeekGiftList(gift_id, gift_name, (page-1)*20, 20);
			for(Week_Gift weekgift:weekgiftList){
				Gift_Info gift_info=igift_infoDao.getGiftInfo(weekgift.getGift_id());
				weekgift.setGift_name(gift_info.getGift_name());
			}
			
			int totalRecord = iweekgiftDao.getWeekGiftCount(gift_id, gift_name);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("weekgift/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("weekgiftList", weekgiftList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "weekgift");
			model.addAttribute("gift_id", gift_id);
			model.addAttribute("gift_name", gift_name);
		} catch (Exception e) {
			log.error("周星礼物列表", e);
		}
		return "weekgift/list";
	}

	public int deleteWeekGift(String w_name, int id, HttpServletRequest request) {
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iweekgiftDao.deleteWeekGift(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除周星礼物", "t_week_gift", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除周星礼物",e);
		}
		return result;
	}
	
	public String Save(int id, int gift_id, String gift_head, int sort,int is_online,Date online_time, HttpServletRequest request,
			Model model) {
		try{	
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date=new Date();
			Week_Gift weekgift = new Week_Gift();
			weekgift.setGift_id(gift_id);
			weekgift.setGift_head(gift_head);
			weekgift.setSort(sort);
			weekgift.setW_name(w_name);
			weekgift.setOnline_time(online_time);
			weekgift.setIs_online(is_online);
			int result = 0;
			if(id>0)
			{
				weekgift.setId(id);
				weekgift.setUpdate_time(date);
				result = iweekgiftDao.updateweekgift(weekgift);
				imanage_RecordDao.insertManageRecord(w_name, "更新周星礼物信息", "t_week_gift", weekgift.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				weekgift.setCreate_time(date);
				result = iweekgiftDao.inserweekgift(weekgift);
				imanage_RecordDao.insertManageRecord(w_name, "添加周星礼物信息", "t_week_gift", weekgift.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存周星礼物信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String edit(int id, Model model) {
		try {
			Week_Gift weekgift = iweekgiftDao.getWeekGift(id);
			List<Gift_Info> giftinfo=giftinfodao.getAllGiftInfo();
			Gift_Info gift=giftinfodao.getGiftInfo(weekgift.getGift_id());	
			weekgift.setGift_name(gift.getGift_name());
			model.addAttribute("giftinfo", giftinfo);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("weekgift", weekgift);
			model.addAttribute("activeUrl", "weekgift");
		} catch (Exception e) {
			log.error("周星礼物信息编辑页面", e);
		}
		return "weekgift/edit";
	}

	public String addGiftBox(Model model) {
	 SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
	 try{
		List<Gift_Info> giftinfo=giftinfodao.getAllGiftInfo();
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		String datatime=format.format(c.getTime());
		model.addAttribute("activeUrl", "weekgift");
		model.addAttribute("giftinfo", giftinfo);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("datatime", datatime);
	 }catch (Exception e) {
		 log.error("周星礼物信息添加页面", e);
	}
		return "weekgift/add";
	}

	public String getGifthead(int gift_id) {
		JSONObject jsonObject = new JSONObject();
		
		try {
			Gift_Info giftinfo=giftinfodao.getGiftInfo(gift_id);		
			jsonObject.put("data", giftinfo.getGift_head());//获取到礼物图片
			jsonObject.put("url", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("获取礼物图片", e);
		}
		return jsonObject.toString();
	}


}

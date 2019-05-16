package com.meisui.manage.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.InewActivityDao;
import com.meisui.manage.entity.Activity;
import com.meisui.manage.entity.New_Activity;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class NewActivityService {
	private static Logger log = Logger.getLogger(NewActivityService.class.getClass());
	@Autowired
	private InewActivityDao inewactivityDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;

	public String getlist(int is_online, int page, Model model) {
		try {
			List<New_Activity> newactivityList = inewactivityDao.getNewActivityList(is_online, (page - 1) * 20, 20);
			int totalRecord = inewactivityDao.getNewActivityCount(is_online);
			for (int i = 0; i < newactivityList.size(); i++) {
				New_Activity activity = newactivityList.get(i);
				if (!compareDate(activity.getState_time())) {
					activity.setIs_online(2);
				}
				if (compareDate(activity.getEnd_time())) {
					activity.setIs_online(3);
				}
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("newactivity/list");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("newactivityList", newactivityList);
			model.addAttribute("activeUrl", "newactivity");
			model.addAttribute("is_online", is_online);
		} catch (Exception e) {
			log.error("新活动banner列表", e);
		}
		return "newactivity/list";
	}

	public String add(Model model) {
		model.addAttribute("activeUrl", "newactivity");
		return "newactivity/add";
	}

	public String save(int id, String name, String url, int order, int type, int is_share, String pic, Date state_time,
			Date end_time, int is_online, String platform, int time_type, int position, String w_name,
			HttpServletRequest request) {
		try {
			New_Activity newactivity = new New_Activity();
			newactivity.setName(name);
			newactivity.setUrl(url);
			newactivity.setState_time(state_time);
			newactivity.setEnd_time(end_time);
			newactivity.setIs_online(is_online);
			if (platform.equals("10,20"))
				platform = "00";
			newactivity.setPlatform(platform);
			newactivity.setOrder(order);
			newactivity.setType(type);
			newactivity.setTime_type(time_type);
			newactivity.setIs_share(is_share);
			newactivity.setPosition(position);
			newactivity.setPic(pic);
			w_name = String.valueOf(request.getAttribute("w_name"));
			newactivity.setW_name(w_name);
			Date date = new Date();
			newactivity.setUpdate_time(date);
			int result = 0;
			if (id > 0) {
				newactivity.setId(id);
				result = inewactivityDao.updateNewActivity(newactivity);
				imanage_RecordDao.insertManageRecord(w_name, "更新活动信息", "t_new_activity", newactivity.getId(),
						IPUtil.getIp(request), date);
			} else {
				newactivity.setCreate_date(date);
				result = inewactivityDao.insertNewActivity(newactivity);
				imanage_RecordDao.insertManageRecord(w_name, "添加活动信息", "t_new_activity", newactivity.getId(),
						IPUtil.getIp(request), date);
			}
			if (result > 0) {
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception ex) {
			log.error("保存新活动banner信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String edit(int id, Model model) {
		try {
			New_Activity newactivity = inewactivityDao.getNewActivity(id);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activity", newactivity);
			model.addAttribute("activeUrl", "newactivity");
		} catch (Exception e) {
			log.error("新活动编辑页面", e);
		}
		return "newactivity/edit";
	}

	public int updateOnline(String w_name, int id, int is_online, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = inewactivityDao.updateNewActivityOnline(id, is_online, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "更新活动上线状态", "t_new_activity", id, IPUtil.getIp(request),
						date);
			}
		} catch (Exception e) {
			log.error("更新活动上线状态", e);
		}
		return result;
	}

	public static boolean compareDate(Date Time) throws ParseException {
		Date date = new Date();
		if (Time.before(date))
			return true;
		else
			return false;
	}

}

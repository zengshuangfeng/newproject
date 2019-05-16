package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.InoticeDao;
import com.meisui.manage.entity.Notice;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class NoticeService extends HessianService {
	
	@Autowired
	private InoticeDao inoticeDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	private static Logger log = Logger.getLogger(NoticeService.class.getClass());

	public String list(String begin_time, String end_time, int type, int state, String platform, int page,
			Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			Date now = new Date();
			List<Notice> list = inoticeDao.list(b_time, e_time, type, state, platform, (page - 1) * 20, 20, now);
			int totalRecord = inoticeDao.listCount(b_time, e_time, type, state, platform, now);
			/* 状态
			for(Notice notice : list) {
				if(notice.getIs_online() == 0) {
					notice.setType2(2);
					continue;
				}
				if(notice.getStart_time().after(now)) {
					notice.setType2(0);
				}else if(notice.getStart_time().before(now) && notice.getEnd_time().after(now)) {
					notice.setType2(1);
				}else if(notice.getEnd_time().before(now)) {
					notice.setType2(2);
				}
			}
			*/
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("notice/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "notice");
			model.addAttribute("list", list);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("type", type);
			model.addAttribute("state", state);
			model.addAttribute("platform", platform);
		}catch (Exception e) {
			log.error("公告列表错误", e);
		}
		return "notice/list2";
	}

	public String edit(int id, Model model) {
		try {
			Notice notice = inoticeDao.getNoticeById(id);
			model.addAttribute("notice", notice);
			model.addAttribute("activeUrl", "notice");
		}catch (Exception e) {
			log.error("公告编辑错误", e);
		}
		return "notice/edit2";
	}

	public String save(int id, String content, int sort, int type, String show_time, Date start_time, Date end_time,
			int interval_minute, String platform, int is_online, String version, HttpServletRequest request) {
		try {
			Date date = new Date();
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Notice notice = new Notice();
			notice.setContent(content);
			/*
			notice.setSort(sort);
			notice.setType(type);
			String showTime = show_time;
			if(StringUtils.isNotBlank(showTime)) {
				String[] times = showTime.split(",");
				for(String time : times) {
					if(time.equals("0")) {
						showTime = "0";
						break;
					}
				}
			}
			notice.setShow_time(showTime);
			notice.setStart_time(start_time);
			if(type == 1) {
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.YEAR,100);
				end_time=calendar.getTime();
				notice.setEnd_time(end_time);
			}else {
				notice.setEnd_time(end_time);
			}
			notice.setInterval_minute(interval_minute);
			String plat_form = platform;
			if(StringUtils.isNotBlank(platform)) {
				String[] plat = plat_form.split(",");
				if(plat.length == 2) {
					plat_form = "0";
				}
			}
			notice.setPlatform(plat_form);
			notice.setVersion(version);
			notice.setIs_online(is_online);
			*/
			notice.setW_name(w_name);
			if(id > 0) {
				notice.setUpdate_time(date);
				notice.setId(id);
				inoticeDao.update(notice);
			}else {
				notice.setCreate_time(date);
				inoticeDao.save(notice);
				message(content);
			}
			imanage_RecordDao.insertManageRecord(w_name, "公告保存", "t_announcement", notice.getId(), IPUtil.getIp(request), date);
			return "{\"code\":0,\"msg\":\"保存成功\"}";
		}catch (Exception e) {
			log.error("公告保存/更新错误", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public int delete(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			result = inoticeDao.delete(id);
			imanage_RecordDao.insertManageRecord(w_name, "删除公告", "t_announcement", id, IPUtil.getIp(request), new Date());
		}catch (Exception e) {
			log.error("删除公告错误", e);
		}
		return result;
	}

}

package com.meisui.manage.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ilive_TimeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Live_Time;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Live_TimeService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月5日 上午11:28:17
 */
@Service
public class Live_TimeService {
	private static Logger log = Logger.getLogger(Live_TimeService.class.getClass());
	@Autowired
	private Ilive_TimeDao ilive_TimeDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/** 
	 * 
	 * <p>功能描述：直播时间管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:42:39</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getLiveTimeList(int page, Model model)
	{
		try { 
			List<Live_Time> live_TimeList = ilive_TimeDao.getLiveTimeList((page-1)*20, 20);
			int totalRecord = ilive_TimeDao.getLiveTimeCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("livetime/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("liveTimeList", live_TimeList);
			model.addAttribute("activeUrl", "livetime");
		} catch (Exception e) {
			log.error("直播时间管理列表", e);
		}
		return "livetime/list";
	}
	/**
	 * 
	 * <p>功能描述：获取直播时间信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:42:21</p>
	 *
	 * @param id 直播时间id
	 * @param model
	 * @return
	 */
	public String getLiveTime(int id)
	{
		Live_Time live_Time = ilive_TimeDao.getLiveTime(id);
		JSONObject jsonObject = JSONObject.fromObject(live_Time);
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：保存直播时间信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:41:45</p>
	 *
	 * @param id 直播时间id
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveLiveTime(int id, Date s_time, Date e_time, String w_name, HttpServletRequest request)
	{
		try{	
			Live_Time live_Time = new Live_Time();
			live_Time.setS_time(s_time);
			String e_timeString = new SimpleDateFormat("hh:mm").format(e_time);
			if(e_timeString.endsWith("59"))
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.SECOND,59);
				e_time=calendar.getTime();
			}
			live_Time.setE_time(e_time);
			w_name = String.valueOf(request.getAttribute("w_name"));
			live_Time.setW_name(w_name);
			Date date = new Date();
			live_Time.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				live_Time.setId(id);
				result = ilive_TimeDao.updateLiveTime(live_Time);
				imanage_RecordDao.insertManageRecord(w_name, "更新直播时间管理信息", "t_live_time", live_Time.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				live_Time.setCreate_time(date);
				result = ilive_TimeDao.insertLiveTime(live_Time);
				imanage_RecordDao.insertManageRecord(w_name, "添加直播时间管理信息", "t_live_time", live_Time.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存直播时间管理信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除直播时间</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:40:48</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 直播时间id
	 * @param request
	 * @return
	 */
	public int deleteLiveTime(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = ilive_TimeDao.deleteLiveTime(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除直播时间管理信息", "t_live_time", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除直播时间管理信息",e);
		}
		return result;
	}
}

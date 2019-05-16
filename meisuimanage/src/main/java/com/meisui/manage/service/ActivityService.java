package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IactivityDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Activity;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：ActivityService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月14日 下午4:34:26
 */
@Service
public class ActivityService {
	private static Logger log = Logger.getLogger(Gift_InfoService.class.getClass());
	@Autowired
	private IactivityDao iactivityDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	/**
	 * 
	 * <p>功能描述：活动列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:41:00</p>
	 *
	 * @param is_online 状态 1上线 0下线
	 * @param page
	 * @param model
	 * @return
	 */
	public String getlist(int is_online, int page, Model model)
	{
		try {
			List<Activity> activityList = iactivityDao.getActivityList(is_online, (page-1)*20, 20);
			int totalRecord = iactivityDao.getActivityCount(is_online);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("activity/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activityList", activityList);
			model.addAttribute("activeUrl", "activity");
			model.addAttribute("is_online", is_online);
		} catch (Exception e) {
			log.error("活动列表", e);
		}
		return "activity/list";
	}
	/**
	 * 
	 * <p>功能描述：活动添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:42:10</p>
	 *
	 * @param model
	 * @return
	 */
	public String add(Model model)
	{
		model.addAttribute("activeUrl", "activity");
		return "activity/add";
	}
	/**
	 * 
	 * <p>功能描述：活动编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:43:18</p>
	 *
	 * @param id 活动id
	 * @param model
	 * @return
	 */
	public String edit(int id, Model model)
	{
		try {
			Activity activity = iactivityDao.getActivity(id);
			model.addAttribute("activity", activity);
			model.addAttribute("activeUrl", "activity");
		} catch (Exception e) {
			log.error("活动编辑页面", e);
		}
		return "activity/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存活动信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:50:08</p>
	 *
	 * @param id 活动id
	 * @param name 活动名称
	 * @param url 链接
	 * @param start_time 上线时间
	 * @param end_time 下线时间
	 * @param is_online 状态 1上线 0下载
	 * @param platform 平台 0全平台 1IOS 2android
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String save(int id, String name, String url, Date start_time, Date end_time, int is_online, String platform, String w_name, HttpServletRequest request)
	{
		try{	
			Activity activity = new Activity();
			activity.setName(name);
			activity.setUrl(url);
			activity.setStart_time(start_time);
			activity.setEnd_time(end_time);
			activity.setIs_online(is_online);
			if(platform.equals("10,20"))
				platform = "00";
			activity.setPlatform(platform);
			w_name = String.valueOf(request.getAttribute("w_name"));
			activity.setW_name(w_name);
			Date date = new Date();
			activity.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				activity.setId(id);
				result = iactivityDao.updateActivity(activity);
				imanage_RecordDao.insertManageRecord(w_name, "更新活动信息", "t_activity", activity.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				activity.setCreate_time(date);
				result = iactivityDao.insertActivity(activity);
				imanage_RecordDao.insertManageRecord(w_name, "添加活动信息", "t_activity", activity.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存活动信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：更新活动上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:51:49</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 活动id
	 * @param is_online 状态 1上线 0下线
	 * @param request
	 * @return
	 */
	public int updateOnline(String w_name, int id, int is_online, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iactivityDao.updateActivityOnline(id, is_online, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "更新活动上线状态", "t_activity", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("更新活动上线状态",e);
		}
		return result;
	}
}

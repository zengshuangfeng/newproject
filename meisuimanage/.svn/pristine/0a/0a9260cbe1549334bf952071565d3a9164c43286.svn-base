package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IchangeDao;
import com.meisui.manage.dao.Ichange_ActivityDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Change;
import com.meisui.manage.entity.Change_Activity;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Change_ActivityService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午3:00:12
 */
@Service
public class Change_ActivityService {
	private static Logger log = Logger.getLogger(Change_ActivityService.class.getClass());
	@Autowired
	private Ichange_ActivityDao ichange_ActivityDao;
	@Autowired
	private IchangeDao ichangeDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：充值活动列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param p_id 等级特权表id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getChangeActivityList(int page, Model model)
	{
		try {
			List<Change_Activity> change_ActivityList = ichange_ActivityDao.getChangeActivityList((page-1)*20, 20);
			int totalRecord = ichange_ActivityDao.getChangeActivityCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("changeactivity/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("changeActivityList", change_ActivityList);
			model.addAttribute("activeUrl", "changeactivity");		
		} catch (Exception e) {
			log.error("充值活动列表", e);
		}
		return "changeactivity/list";
	}
	/**
	 * 
	 * <p>功能描述：充值活动添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月11日 下午2:30:02</p>
	 *
	 * @param model
	 * @return
	 */
	public String addChangeActivity(Model model)
	{
		List<Change> changeList = ichangeDao.getAllChangeList();
		int change_rmb = 0;
		if(changeList.size()>0)
			change_rmb = changeList.get(0).getChange_rmb();
		model.addAttribute("changeList", changeList);
		model.addAttribute("change_rmb", change_rmb);
		model.addAttribute("activeUrl", "changeactivity");
		return "changeactivity/add";
	}
	/**
	 * 
	 * <p>功能描述：充值活动编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 充值活动id
	 * @param model
	 * @return
	 */
	public String editChangeActivity(int id, Model model)
	{
		Change_Activity change_Activity = ichange_ActivityDao.getChangeActivity(id);
		List<Change> changeList = ichangeDao.getAllChangeList();
		for (Change change : changeList) {
			if(change.getId()==change_Activity.getChange_id())
			{
				change_Activity.setChange_rmb(change.getChange_rmb());
				break;
			}
		}
		model.addAttribute("changeActivity", change_Activity);
		model.addAttribute("changeList", changeList);
		model.addAttribute("activeUrl", "changeactivity");
		return "changeactivity/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存充值活动信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 充值活动id
	 * @param change_id 充值套餐表id
	 * @param activity_rmb 活动价
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param is_first 是否是首充 1是 0否
	 * @param is_online 是否上线 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveChangeActivity(int id, int change_id, int activity_rmb, Date start_time, Date end_time, int is_first, int is_online, String w_name, HttpServletRequest request)
	{
		try{	
			Change_Activity change_Activity = new Change_Activity();
			change_Activity.setChange_id(change_id);
			change_Activity.setActivity_rmb(activity_rmb);
			change_Activity.setStart_time(start_time);
			change_Activity.setEnd_time(end_time);
			change_Activity.setIs_first(is_first);
			change_Activity.setIs_online(is_online);
			w_name = String.valueOf(request.getAttribute("w_name"));
			change_Activity.setW_name(w_name);
			Date date = new Date();
			change_Activity.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				change_Activity.setId(id);
				result = ichange_ActivityDao.updateChangeActivity(change_Activity);
				imanage_RecordDao.insertManageRecord(w_name, "更新充值活动信息", "t_change_activity", change_Activity.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				change_Activity.setCreate_time(date);
				result = ichange_ActivityDao.insertChangeActivity(change_Activity);
				imanage_RecordDao.insertManageRecord(w_name, "添加充值活动信息", "t_change_activity", change_Activity.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存充值活动信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除充值活动</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 充值活动id
	 * @param request
	 * @return
	 */
	public int deleteChangeActivity(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = ichange_ActivityDao.deleteChangeActivity(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除充值活动", "t_change_activity", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除充值活动",e);
		}
		return result;
	}
}

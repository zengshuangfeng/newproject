package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Iuser_TaskDao;
import com.meisui.manage.entity.User_Task;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：User_TaskService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午10:29:47
 */
@Service
public class User_TaskService {
	private static Logger log = Logger.getLogger(User_TaskService.class.getClass());
	@Autowired
	private Iuser_TaskDao iuser_TaskDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：每日任务列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:31:59</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getUserTaskList(int page, Model model)
	{
		try {
			List<User_Task> user_TaskList = iuser_TaskDao.getUserTaskList((page-1)*20, 20);
			int totalRecord = iuser_TaskDao.getUserTaskCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("usertask/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userTaskList", user_TaskList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "usertask");
		} catch (Exception e) {
			log.error("每日任务列表", e);
		}
		return "usertask/list";
	}
	/**
	 * 
	 * <p>功能描述：每日任务编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:33:38</p>
	 *
	 * @param id 每日任务id
	 * @param model
	 * @return
	 */
	public String editUserTask(int id, Model model)
	{
		User_Task user_Task = iuser_TaskDao.getUserTask(id);
		model.addAttribute("userTask", user_Task);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "usertask");
		return "usertask/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存每日任务信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:37:56</p>
	 *
	 * @param id 每日任务id
	 * @param type 类型
	 * @param name 任务名称
	 * @param icon 任务icon
	 * @param num 完成次数
	 * @param reward_count 奖励钻石
	 * @param sort 排序
	 * @param w_name
	 * @param request
	 * @return
	 */
	public String saveUserTask(int id, int type, String name, String icon, int num, int reward_count, int sort, String w_name, HttpServletRequest request)
	{
		try{	
			w_name = String.valueOf(request.getAttribute("w_name"));
			User_Task user_Task = new User_Task();
			user_Task.setName(name);
			user_Task.setIcon(icon);
			user_Task.setType(type);
			user_Task.setNum(num);
			user_Task.setReward_count(reward_count);
			user_Task.setSort(sort);
			user_Task.setW_name(w_name);
			Date date = new Date();
			int result = 0;
			if(id>0)
			{
				user_Task.setId(id);
				result = iuser_TaskDao.updateUserTask(user_Task);
				imanage_RecordDao.insertManageRecord(w_name, "更新每日任务", "t_user_task", user_Task.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				user_Task.setCreate_time(date);
				result = iuser_TaskDao.insertUserTask(user_Task);
				imanage_RecordDao.insertManageRecord(w_name, "添加每日任务", "t_user_task", user_Task.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存每日任务", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除每日任务</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:37:33</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 每日任务id
	 * @param request
	 * @return
	 */
	public int deleteUserTask(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				w_name = String.valueOf(request.getAttribute("w_name"));
				Date date = new Date();
				result = iuser_TaskDao.deleteUserTask(id, w_name);
				imanage_RecordDao.insertManageRecord(w_name, "删除每日任务", "t_user_task", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除每日任务",e);
		}
		return result;
	}
}

package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ilevel_Privilege_GradientDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Level_Privilege_Gradient;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Level_Privilege_GradientService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午2:21:50
 */
@Service
public class Level_Privilege_GradientService {
	private static Logger log = Logger.getLogger(Level_Privilege_GradientService.class.getClass());
	@Autowired
	private Ilevel_Privilege_GradientDao ilevel_Privilege_GradientDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：等级特权梯度列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param p_id 等级特权表id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getLevelPrivilegeGradientList(int p_id, int page, Model model)
	{
		try {
			List<Level_Privilege_Gradient> level_Privilege_GradientList = ilevel_Privilege_GradientDao.getLevelPrivilegeGradientList(p_id, (page-1)*20, 20);
			int totalRecord = ilevel_Privilege_GradientDao.getLevelPrivilegeGradientCount(p_id);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("levelPrivilege/gradient/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("levelPrivilegeGradientList", level_Privilege_GradientList);
			model.addAttribute("activeUrl", "levelprivilege");
			model.addAttribute("p_id", p_id);			
		} catch (Exception e) {
			log.error("等级特权梯度列表", e);
		}
		return "levelprivilegegradient/list";
	}
	/**
	 * 
	 * <p>功能描述：等级特权梯度添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月11日 下午2:30:02</p>
	 *
	 * @param p_id 等级特权表id
	 * @param model
	 * @return
	 */
	public String addLevelPrivilegeGradient(int p_id, Model model)
	{
		model.addAttribute("p_id", p_id);
		model.addAttribute("activeUrl", "levelprivilege");
		return "levelprivilegegradient/add";
	}
	/**
	 * 
	 * <p>功能描述：等级特权梯度编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 等级特权梯度id
	 * @param model
	 * @return
	 */
	public String editLevelPrivilegeGradient(int id, Model model)
	{
		Level_Privilege_Gradient level_Privilege_Gradient = ilevel_Privilege_GradientDao.getLevelPrivilegeGradient(id);
		model.addAttribute("levelPrivilegeGradient", level_Privilege_Gradient);
		model.addAttribute("activeUrl", "levelprivilege");
		return "levelprivilegegradient/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存等级信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 等级特权梯度id
	 * @param p_id 等级特权表id
	 * @param name 等级名称
	 * @param level 解锁等级
	 * @param is_online 是否上线 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveLevelPrivilegeGradient(int id, int p_id, String name, int level, int is_online, String w_name, HttpServletRequest request)
	{
		try{	
			Level_Privilege_Gradient level_Privilege_Gradient = new Level_Privilege_Gradient();
			level_Privilege_Gradient.setName(name);
			level_Privilege_Gradient.setP_id(p_id);
			level_Privilege_Gradient.setLevel(level);
			level_Privilege_Gradient.setIs_online(is_online);
			w_name = String.valueOf(request.getAttribute("w_name"));
			level_Privilege_Gradient.setW_name(w_name);
			Date date = new Date();
			level_Privilege_Gradient.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				level_Privilege_Gradient.setId(id);
				result = ilevel_Privilege_GradientDao.updateLevelPrivilegeGradient(level_Privilege_Gradient);
				imanage_RecordDao.insertManageRecord(w_name, "更新等级特权梯度信息", "t_level_privilege_gradient", level_Privilege_Gradient.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				level_Privilege_Gradient.setCreate_time(date);
				result = ilevel_Privilege_GradientDao.insertLevelPrivilegeGradient(level_Privilege_Gradient);
				imanage_RecordDao.insertManageRecord(w_name, "添加等级特权梯度信息", "t_level_privilege_gradient", level_Privilege_Gradient.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存等级特权梯度信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除等级特权梯度</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 等级特权梯度id
	 * @param request
	 * @return
	 */
	public int deleteLevelPrivilegeGradient(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = ilevel_Privilege_GradientDao.deleteLevelPrivilegeGradient(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除等级特权梯度", "t_level_privilege_gradient", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除等级特权梯度",e);
		}
		return result;
	}
}

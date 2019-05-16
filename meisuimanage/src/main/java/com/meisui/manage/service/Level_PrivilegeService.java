package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ilevel_PrivilegeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Level_Privilege;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：Level_PrivilegeService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午5:36:18
 */
@Service
public class Level_PrivilegeService {
	private static Logger log = Logger.getLogger(Level_PrivilegeService.class.getClass());
	@Autowired
	private Ilevel_PrivilegeDao ilevel_PrivilegeDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：等级特权列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午5:40:21</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getLevelPrivilegeList(int page, Model model)
	{
		try {
			List<Level_Privilege> level_PrivilegeList = ilevel_PrivilegeDao.getLevelPrivilegeList( (page-1)*20, 20);
			int totalRecord = ilevel_PrivilegeDao.getLevelPrivilegeCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("levelprivilege/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("levelPrivilegeList", level_PrivilegeList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "levelprivilege");
		} catch (Exception e) {
			log.error("等级特权列表", e);
		}
		return "levelprivilege/list";
	}
	/**
	 * 
	 * <p>功能描述：等级特权编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午5:41:07</p>
	 *
	 * @param id 等级特权id
	 * @param model
	 * @return
	 */
	public String editLevelPrivilege(int id, Model model)
	{
		Level_Privilege level_Privilege = ilevel_PrivilegeDao.getLevelPrivilege(id);
		model.addAttribute("levelPrivilege", level_Privilege);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "levelprivilege");
		return "levelprivilege/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存等级特权信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午5:42:54</p>
	 *
	 * @param id 等级特权id
	 * @param title 等级特权名称
	 * @param icon 等级特权图标
	 * @param level 解锁等级
	 * @param sort 排序
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveLevelPrivilege(int id, String title, String icon, int level, int sort, String w_name, HttpServletRequest request)
	{
		try{	
			Level_Privilege level_Privilege = new Level_Privilege();
			level_Privilege.setTitle(title);
			level_Privilege.setIcon(icon);
			level_Privilege.setLevel(level);
			level_Privilege.setSort(sort);
			w_name = String.valueOf(request.getAttribute("w_name"));
			level_Privilege.setW_name(w_name);
			Date date = new Date();
			level_Privilege.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				level_Privilege.setId(id);
				result = ilevel_PrivilegeDao.updateLevelPrivilege(level_Privilege);
				imanage_RecordDao.insertManageRecord(w_name, "更新等级特权信息", "level_score", level_Privilege.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存等级特权信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
}

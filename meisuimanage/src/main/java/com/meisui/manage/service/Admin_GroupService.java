package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Iadmin_GroupDao;
import com.meisui.manage.entity.Admin_Group;
import com.meisui.manage.utils.PageUtil;


/**
 * <p>文件名称：Admin_GroupService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年6月21日 上午9:20:27
 */
@Service
public class Admin_GroupService {
	private static Logger log=Logger.getLogger(Admin_GroupService.class.getClass());
	@Autowired
	private Iadmin_GroupDao iadmin_GroupDao;
	/**
	 * <p>功能描述：群组列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	public String getGroupList(int page, Model model) {
		try {
			List<Admin_Group> groupList = iadmin_GroupDao.getGroupList((page-1)*20, 20);
			int totalRecord = iadmin_GroupDao.getGroupCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("admingroup/list");
			model.addAttribute("groupList", groupList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "admingroup");
		} catch (Exception e) {
			log.error("获取群组列表异常");
		}
		return "admingroup/list";
	}
	public String save(int id, String name, String w_name, HttpServletRequest request) {
		 try {
			 Date date=new Date();
			 Admin_Group admin_Group=new Admin_Group();
			 admin_Group.setName(name);
			 admin_Group.setUpdate_time(date);

			 w_name = String.valueOf(request.getAttribute("w_name"));
			 admin_Group.setW_name(w_name);
			 int result = 0;
				if(id > 0)
				{
					admin_Group.setId(id);
					result = iadmin_GroupDao.updateGroup(admin_Group);
				}
				else {
					admin_Group.setCreate_time(date);
					result = iadmin_GroupDao.insertGroup(admin_Group);
				}
				if(result > 0)
					return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
			catch(Exception ex)
			{
				log.error("保存群组信息异常",ex);
				ex.printStackTrace();
			}
			return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * <p>功能描述：编辑群组</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午10:43:54</p>
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	public String getEdit(int id, Model model) {
		try {
			Admin_Group admin_Group= iadmin_GroupDao.getGrou(id);
			model.addAttribute("admin_Group", admin_Group);
			model.addAttribute("activeUrl", "admingroup");
		} catch (Exception e) {
			log.error("编辑群组页面异常",e);
		}
		return "admingroup/edit";
	}
	/**
	 * <p>功能描述：删除群组</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午10:44:01</p>
	 *
	 * @param w_name
	 * @param id
	 * @param request
	 * @return
	 */
	public int delete(String w_name, int id, HttpServletRequest request) {
		try {
			Date date=new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			iadmin_GroupDao.deleteGroup(id, w_name, date);
		} catch (Exception e) {
			log.error("删除群组异常", e);
		}
		return 0; 
	}
}

package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Iuser_AdminDao;
import com.meisui.manage.entity.User_Admin;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：User_AdminService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月29日 上午10:20:12
 */
@Service
public class User_AdminService {
	private static Logger log = Logger.getLogger(Top_GuardService.class.getClass());
	@Autowired
	private Iuser_AdminDao iuser_AdminDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：房管列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 上午10:22:05</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getUserAdminList(long attention_uid, long f_uuid, int page, Model model)
	{
		try {
			List<User_Admin> userAdminList = iuser_AdminDao.getUserAdminList(f_uuid, (page-1)*20, 20);
			int totalRecord = iuser_AdminDao.getUserAdminCount(f_uuid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/useradmin/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userAdminList", userAdminList);
			model.addAttribute("activeUrl", "useranchor");
			model.addAttribute("attention_uid", attention_uid);
			model.addAttribute("f_uuid", f_uuid);
		} catch (Exception e) {
			log.error("房管列表", e);
		}
		return "useradmin/list";
	}
	/**
	 * 
	 * <p>功能描述：全局管理员列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月20日 上午10:53:39</p>
	 *
	 * @param page页码
	 * @param model
	 * @return
	 */
	public String getGlobalUserAdminList(int page, Model model)
	{
		try {
			List<User_Admin> userAdminList = iuser_AdminDao.getUserAdminList(0L, (page-1)*20, 20);
			int totalRecord = iuser_AdminDao.getUserAdminCount(0L);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("globaladmin/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userAdminList", userAdminList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "globaladmin");
		} catch (Exception e) {
			log.error("全局管理员列表", e);
		}
		return "globaladmin/list";
	}
	/**
	 * 
	 * <p>功能描述：删除全局管理员</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月20日 上午10:55:30</p>
	 *
	 * @param id 全局管理员id
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int deleteGlobalUserAdmin(int id, String w_name, HttpServletRequest request)
	{
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除管理员", "t_user_admin", id, IPUtil.getIp(request), date);
			return iuser_AdminDao.deleteUserAdmin(id, date);
		} catch (Exception e) {
			log.error("删除管理员", e);
			return 0;
		}
	}
	/**
	 * 
	 * <p>功能描述：保存全局管理员信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月20日 上午10:55:30</p>
	 *
	 * @param uid 全局管理员uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveGlobalUserAdmin(Long  uid, String w_name, HttpServletRequest request)
	{
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			int result = iuser_AdminDao.insertUserAdmin(0L, uid, date, date);
			imanage_RecordDao.insertManageRecord(w_name, "添加管理员", "t_user_admin", 0, IPUtil.getIp(request), date);
			if(result>0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";;
		} catch (Exception e) {
			log.error("保存全局管理员信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
}

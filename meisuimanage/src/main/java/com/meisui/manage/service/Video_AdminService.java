package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IadminDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Admin;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Video_AdminService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月27日 上午9:45:46
 */
@Service
public class Video_AdminService {
	private static Logger log = Logger.getLogger(ProductService.class.getClass());
	@Autowired
	private IadminDao iadminDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：视频管理权限列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月27日 上午9:57:21</p>
	 *
	 * @param cookie
	 * @param nickname 姓名
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getList(String cookie, String nickname, int page, Model model)
	{
		try {
			String [] admStrings = cookie.split(",");
			Admin admin= iadminDao.getAdminByName(admStrings[0]);
			List<Admin> videoAdminList = iadminDao.getVideoAdminList(admin.getId(), nickname, (page-1)*20, 20);
			int totalRecord = iadminDao.getVideoAdminCount(admin.getId(), nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("videoadmin/list");
			model.addAttribute("videoAdminList", videoAdminList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "videoadmin");
			model.addAttribute("nickname", nickname);
		} catch (Exception e) {
			log.error("视频管理权限列表", e);
		}
		return "videoadmin/list";
	}
	/**
	 * 
	 * <p>功能描述：保存视频审核员信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月27日 上午10:38:55</p>
	 *
	 * @param nickname 姓名
	 * @param username 账号
	 * @param password 密码
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String save(int id, String nickname, String username, String password, String w_name, HttpServletRequest request)
	{
		try
		{
			w_name = String.valueOf(request.getAttribute("w_name"));
			Integer usernameExist = iadminDao.getAdminByUsername(username);
			if(usernameExist!=null&&usernameExist!=id)
			{
				List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
				mapList.add(AuthUtil.getMap("username", "账号名已存在，请重新输入"));
				JSONArray jsonArray = JSONArray.fromObject(mapList);
				if(mapList.size()>0)
				{
					return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
				}
			}
			Admin admin = new Admin();
			admin.setNickname(nickname);
			admin.setUsername(username);
			if(!StringUtils.isBlank(password))
			{
				admin.setPassword(AuthUtil.MD5(password));
			}
			admin.setW_name(w_name);
			admin.setType(1);
			int result = 0;
			if(id==0)
			{
				result = iadminDao.insertAdmin(admin);
				if(admin.getId()>0)
				{
					List<String> menuList = new ArrayList<String>();
					menuList.add("49");
					menuList.add("50");
					menuList.add("57");
					menuList.add("58");
					iadminDao.insertAdminPermission(admin.getId(), menuList);
				}
				imanage_RecordDao.insertManageRecord(w_name, "添加视频审核员", "t_admin", admin.getId(), IPUtil.getIp(request), new Date());
			}
			else
			{
				admin.setId(id);
				result = iadminDao.updateAdmin(admin);
				imanage_RecordDao.insertManageRecord(w_name, "更新视频审核员", "t_admin", admin.getId(), IPUtil.getIp(request), new Date());
			}
			if(result > 0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error("保存视频审核员信息", ex);
			ex.printStackTrace();
		}	
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：更新审核员权限</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月27日 上午11:57:40</p>
	 *
	 * @param id 用户id
	 * @param is_close 是否关闭权限 0否 1是
	 * @param w_name
	 * @param request
	 * @return
	 */
	public int close(int id, int is_close, String w_name, HttpServletRequest request)
	{
		try
		{
			w_name = String.valueOf(request.getAttribute("w_name"));
			int result = iadminDao.updateAdminClose(id, is_close, w_name);
			imanage_RecordDao.insertManageRecord(w_name, "更新审核员权限", "t_admin", id, IPUtil.getIp(request), new Date());
			if(result > 0)
				return 1;
		}
		catch(Exception ex)
		{
			log.error("更新审核员权限", ex);
			ex.printStackTrace();
		}	
		return 0;
	}
}

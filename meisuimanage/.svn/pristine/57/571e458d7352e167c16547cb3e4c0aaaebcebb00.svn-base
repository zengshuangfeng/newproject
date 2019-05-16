package com.meisui.manage.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forman.foundation.library.ThreeDESUtil;
import com.meisui.manage.dao.IadminDao;
import com.meisui.manage.dao.Iadmin_GroupDao;
import com.meisui.manage.dao.Imanage_MenuDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.ImessageDao;
import com.meisui.manage.entity.Admin;
import com.meisui.manage.entity.Admin_Group;
import com.meisui.manage.entity.Manage_Menu;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONArray;


/**
 * <p>文件名称：AdminService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Service
public class AdminService {
	private static Logger log = Logger.getLogger(AdminService.class.getClass());
	@Autowired
	private IadminDao iadminDao;
	@Autowired
	private Iadmin_GroupDao iadmin_GroupDao;
	@Autowired
	private Imanage_MenuDao imanage_MenuDao;
    @Autowired
    private Imanage_RecordDao imanage_RecordDao;
    @Autowired
    private ImessageDao imessageDao;
	/***
	 * 
	 * <p>功能描述：登录验证</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @param remember 是否记住 1:是 0:否
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public String validate(String username, String password, int remember, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		boolean issuccess = true;
		if (StringUtils.isBlank(username)) {
			model.addAttribute("msg", "用户名不能为空");
			issuccess = false;
		} 
		else if (StringUtils.isBlank(password)) {
			model.addAttribute("username", username);
			model.addAttribute("msg", "密码不能为空");
			issuccess = false;
		} else if (password.length() < 6 && password.length() > 30) {
			model.addAttribute("username", username);
			model.addAttribute("msg", "密码最少6个字符，最多30个字符");
			issuccess = false;
		}
		else
		{
			try {
				Admin admin = iadminDao.getAdminByName(username);

				password = AuthUtil.MD5(password);
				if (admin != null && admin.getPassword().equals(password)) {
					AuthUtil.setCookie("admin",ThreeDESUtil.encode(username +","+password , PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv")), 60 * 60 * 24 * 365, remember, response);
					AuthUtil.setCookie("w_name", ThreeDESUtil.encode(URLEncoder.encode(admin.getNickname(),"UTF-8"), PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv")), 60*60*24*365, remember, response);
					issuccess = true;
					if(password.equals("e10adc3949ba59abbe56e057f20f883e"))
						return "redirect:/resetpwd"; 
				}	
				else
				{
					issuccess=false;
					model.addAttribute("msg", "用户名或密码错误");
				}
				if(admin != null && admin.getIs_close()==1)
				{
					model.addAttribute("msg", "您的账号已被关闭权限！");
					issuccess = false;
				}
				if(issuccess)
				{
					model.addAttribute("userIcon",admin.getIcon());
					return "redirect:/main"; 
				}
			} catch (Exception e) {
				log.error("登录异常", e);
			}

		}
		return "login";
	}
	/***
	 * 
	 * <p>功能描述：密码重置</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param password 新密码
	 * @param password2 再一次密码
	 * @param cookie 用户cookie
	 * @param response
	 * @return
	 */
	public String resetPwd(String password, String password2, String cookie, HttpServletResponse response) {
		try
		{
			String admins = ThreeDESUtil.decode(cookie, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));	
			String[] adminStrings = admins.split(",");
			Admin admin  = iadminDao.getAdminByName(adminStrings[0]);
			if(admin !=null && admin.getPassword().equals(adminStrings[1]))
			{
				List<Map<String, String>> mapList = validData(password, password2);
				JSONArray jsonArray = JSONArray.fromObject(mapList);
				if(!StringUtils.isBlank(password))
					admin.setPassword(AuthUtil.MD5(password));
				if(mapList.size()>0)
				{
					return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
				}
				password = AuthUtil.MD5(password);
				admin.setPassword(password);
				iadminDao.resetPwd(admin);
				AuthUtil.setCookie("admin", null, 1, 0, response);
				return "{\"code\":0,\"msg\":\"请重新登录\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("重置密码异常", ex);
			ex.printStackTrace();
		}	
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/***
	 * 
	 * <p>功能描述：验证密码</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param password
	 * @param password2
	 * @return
	 */
	private List<Map<String, String>> validData(String password, String password2)
	{
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();

		if(StringUtils.isBlank(password))
			mapList.add(AuthUtil.getMap("password", "密码不能为空"));
		else if(password.length() < 6 && password.length() > 30)
			mapList.add(AuthUtil.getMap("password", "密码最少6个字符，最多30个字符"));
		else if(password.equals("123456"))
			mapList.add(AuthUtil.getMap("password", "密码不能是123456"));
		if(StringUtils.isBlank(password2))
			mapList.add(AuthUtil.getMap("password2", "再一次密码不能为空"));
		else if(!password.equals(password2))
			mapList.add(AuthUtil.getMap("password2", "两次密码输入不一致"));

		return mapList;
	}
	/***
	 * 
	 * <p>功能描述：用户登出</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public String adminLogout(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("admin"))
				{
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			cookies = request.getCookies();

		} catch (Exception e) {
			log.error("登出异常", e);
		}
		return "login";
	}
	/**
	 * 
	 * <p>功能描述：主页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param cookie 用户cookie
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public String main(String cookie, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String admins = ThreeDESUtil.decode(cookie, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
			
			String[] adminStrings = admins.split(",");
			//后期可直接从redis取
			Admin admin  = iadminDao.getAdminByName(adminStrings[0]);
			List<Manage_Menu> menuList = new ArrayList<Manage_Menu>();
			if(admin !=null && admin.getPassword().equals(adminStrings[1]))
			{
				menuList = imanage_MenuDao.getMenuListByAdminId(admin.getId());
			}
			model.addAttribute("id", admin.getId());
			model.addAttribute("menuList", menuList);
		} catch (Exception e) {
			log.error("跳转主页异常", e);
		}
		return "common/blank";
	}
	/***
	 * 
	 * <p>功能描述：获取后台账号列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param s
	 * @param model
	 * @return
	 */
	public String getAdminList(String s,String cook, Model model) {
		try {
			String admins = ThreeDESUtil.decode(cook, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
			String [] admStrings=admins.split(",");
			Admin admin= iadminDao.getAdminByName(admStrings[0]);
			List<Admin> adminList = iadminDao.getAdminList(s,admin.getId());
			model.addAttribute("s", s);
			model.addAttribute("adminList", adminList);
			model.addAttribute("activeUrl", "permission");
		} catch (Exception e) {
			log.error("后台账号列表异常", e);
		}
		return "permission/list";
	}
	/***
	 * 
	 * <p>功能描述：添加后台账号页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAdmin(String cookie, Model model) {
		try {
			String admins = ThreeDESUtil.decode(cookie, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
			String [] admiStrings=admins.split(",");
			Admin admin=iadminDao.getAdminByName(admiStrings[0]);
			List<Manage_Menu> fMenuList = imanage_MenuDao.getMenuListByAdminId3(admin.getId());
			Admin admin2=imanage_MenuDao.getGroup(admin.getId());
			List<Admin_Group> groupList= new ArrayList<Admin_Group>();
			if(admin.getId()==1)//只有sky才有开管理员的权限
			{
				groupList= iadmin_GroupDao.getAllGrouList();
			}
			model.addAttribute("groupList", groupList);
			model.addAttribute("admin2", admin2);
			model.addAttribute("fMenuList", fMenuList);
			model.addAttribute("activeUrl", "permission");
		} catch (Exception e) {
			log.error("后台账号添加异常", e);
		}
		return "permission/add";
	}
	/***
	 * 
	 * <p>功能描述：编辑后台账号页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param id 账号id
	 * @param model
	 * @return
	 */
	public String editAdmin(int id,String cookie,Model model) {
		try {
			String admins = ThreeDESUtil.decode(cookie, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
			Admin admin = iadminDao.getAdminById(id);
			String [] admiStrings=admins.split(",");
			Admin admin3=iadminDao.getAdminByName(admiStrings[0]);
			List<Integer> userMenuIdList = iadminDao.getMenuId(id);
			List<Manage_Menu> fMenuList = imanage_MenuDao.getMenuListByAdminId2(admin3.getId());
			for (Manage_Menu manage_Menu : fMenuList) {
				boolean isContain = false;
				for (Integer userMenuId: userMenuIdList) {
					if(userMenuId==manage_Menu.getId())
					{
						isContain = true;
						break;
					}
				}
				if(isContain)
					manage_Menu.setAdmin_id(id);
				else 
					manage_Menu.setAdmin_id(0);
			}
			Admin admin2=imanage_MenuDao.getGroup(admin3.getId());
			List<Admin_Group> groupList= new ArrayList<Admin_Group>();
			if(admin3.getId()==1)//只有sky才有开管理员的权限
			{
				groupList= iadmin_GroupDao.getAllGrouList();
			}
			model.addAttribute("groupList", groupList);
			model.addAttribute("admin2", admin2);
			model.addAttribute("admin", admin);
			model.addAttribute("fMenuList", fMenuList);
			model.addAttribute("userMenuIdList", userMenuIdList);
			model.addAttribute("activeUrl", "permission");
		} catch (Exception e) {
			log.error("后台账号编辑异常", e);
		}
		return "permission/edit";
	}
	/***
	 * 
	 * <p>功能描述：保存后台账号信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param mIds 菜单id
	 * @return
	 */
	public String saveAdmin(int id, String nickname, String username, String password, String remark, int group,int is_read, String mIds)
	{
		Admin admin = new Admin();
		try
		{   
			admin.setId(id);
			admin.setNickname(nickname);
			admin.setUsername(username);
			admin.setPassword(password);
			admin.setRemark(remark);
			admin.setGroup(group);
			admin.setIs_read(is_read);
			int z=0;
			List<String> idList = new ArrayList<String>();
			List<String> mIdList = new ArrayList<String>();
			if(!StringUtils.isBlank(mIds))
				mIdList = Arrays.asList(mIds.split(","));
			List<Map<String, String>> mapList = validData(admin);
			JSONArray jsonArray = JSONArray.fromObject(mapList);
			if(!StringUtils.isBlank(admin.getPassword()))
				admin.setPassword(AuthUtil.MD5(admin.getPassword()));
			if (admin.getGroup()!=1) {
				for (int j = 0; j < mIdList.size(); j++) {
					String i =mIdList.get(j);
					if (Integer.parseInt(i)==23) {
						return "{\"code\":-1,\"msg\":\"保存失败\"}";
					}
				}}
			if(mapList.size()>0)
			{
				return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
			}
			if(admin.getId() > 0)
			{
				iadminDao.updateAdmin(admin);
				iadminDao.deleteAdminPermission(admin.getId());
				if(mIdList.size()>0){
					iadminDao.insertAdminPermission(admin.getId(), mIdList);
				}
			}
			else {
				iadminDao.insertAdmin(admin);
				if(mIdList.size()>0)
					iadminDao.insertAdminPermission(admin.getId(), mIdList);
			}
			if (mIdList.size()>0) {
				List<Manage_Menu> fidList = imanage_MenuDao.getMangeFid();
				for (Manage_Menu manage_Menu : fidList) {
					if (mIdList.contains(""+manage_Menu.getF_id())) {
						idList.add(manage_Menu.getId()+"");
						z=z+1;
					}
				}
				if (z>0) {
					iadminDao.insertAdminPermission(admin.getId(), idList);
				}
			}
			return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error((admin.getId() > 0 ? "编辑" : "添加").concat("后台账号信息异常"), ex);
			ex.printStackTrace();
			return "{\"code\":-1,\"msg\":\"保存失败\"}";
		}		
	}
	/***
	 * 
	 * <p>功能描述：验证后台账号信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param admin
	 * @return
	 */
	private List<Map<String, String>> validData(Admin admin)
	{
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();

		if(StringUtils.isBlank(admin.getNickname()))
			mapList.add(AuthUtil.getMap("nickname", "昵称不能为空"));
		else if(admin.getNickname().length()>30)
			mapList.add(AuthUtil.getMap("nickname", "昵称长度不能大于30个字"));
		else
		{
			Integer admin_id = iadminDao.getAdminByNickname(admin.getNickname());
			if(admin_id!=null&&admin_id!=admin.getId())
				mapList.add(AuthUtil.getMap("nickname", "昵称已存在"));
		}	
		if(StringUtils.isBlank(admin.getUsername()))
			mapList.add(AuthUtil.getMap("username", "用户名不能为空"));
		else if(admin.getUsername().length()>30)
			mapList.add(AuthUtil.getMap("username", "用户名长度不能大于30个字"));
		else
		{
			Integer admin_id = iadminDao.getAdminByUsername(admin.getUsername());
			if(admin_id!=null&&admin_id!=admin.getId())
				mapList.add(AuthUtil.getMap("username", "用户名已存在"));
		}
		if(admin.getId()==0 && StringUtils.isBlank(admin.getPassword()))
			mapList.add(AuthUtil.getMap("password", "密码不能为空"));
		else if(!StringUtils.isBlank(admin.getPassword())&&(admin.getPassword().length()>20||admin.getPassword().length()<6))
			mapList.add(AuthUtil.getMap("password", "密码长度应在6-20个字"));
		if(!StringUtils.isBlank(admin.getRemark())&&admin.getRemark().length()>200)
			mapList.add(AuthUtil.getMap("type", "备注长度不能大于200个字"));

		return mapList;
	}
	/***
	 * 
	 * <p>功能描述：删除后台账号</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param id 账号id
	 * @return
	 */
	@Transactional
	public int deleteAdmin(int id)
	{
		try{
			iadminDao.deleteAdminPermission(id);
			return iadminDao.deleteAdmin(id);
		} catch (Exception e) {
			log.error("后台账号删除异常", e);
			return 0;
		}
	}
	/***
	 * 
	 * <p>功能描述：获取后台登录实体</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param username 用户名
	 * @return
	 */
	public Admin getAdminByName(String username)
	{
		return iadminDao.getAdminByName(username);
	}
	/***
	 * 
	 * <p>功能描述：获取后台用户菜单列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param admin_id 后台用户id
	 * @return
	 */
	public List<Manage_Menu> getMenuListByAdminId(int admin_id)
	{
		return imanage_MenuDao.getMenuListByAdminId(admin_id);
	}
	/**
	 * <p>功能描述：修改密码弹窗</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param username
	 * @param model
	 * @return
	 */
	public String alert(int id, Model model) {
		try {
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("修改密码异常");
		}
		return "changepwd";
	}
	/**
	 * <p>功能描述：保存密码</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午3:38:38</p>
	 *
	 * @param username 用户名		
	 * @param opw 旧密码
	 * @param npw 新密码	
	 * @param npw2 确认新密码
	 * @return
	 */
	public String save(String id, String opwd, String npwd, String npw2, HttpServletResponse response) {
		try {
			Admin admin = iadminDao.getAdminById(Integer.parseInt(id));
			opwd= AuthUtil.MD5(opwd);
			List<Map<String, String>> mapList = validPwdData(opwd, admin.getPassword(), npwd, npw2);
			JSONArray jsonArray = JSONArray.fromObject(mapList);
			if(mapList.size()>0)
			{
				return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
			}
			npwd=AuthUtil.MD5(npwd);
			admin.setPassword(npwd);
			iadminDao.resetPwd(admin);
			AuthUtil.setCookie("admin", null, 1, 0, response);
		}
		catch(Exception ex)
		{
			log.error("修改密码异常", ex);
			ex.printStackTrace();
			return "{\"code\":-1,\"msg\":\"保存失败\"}";
		}
		return null;		
	}
	private List<Map<String, String>> validPwdData(String opwd, String opwd2, String npwd, String npwd2)
	{
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		if(StringUtils.isBlank(opwd))
			mapList.add(AuthUtil.getMap("opwd", "旧密码不能为空"));
		else if (!opwd.equals(opwd2)) 
			mapList.add(AuthUtil.getMap("opwd", "旧密码不正确"));
		if(StringUtils.isBlank(npwd))
			mapList.add(AuthUtil.getMap("npwd", "新密码不能为空"));
		else if(!StringUtils.isBlank(npwd)&&(npwd.length()>20||npwd.length()<6))
			mapList.add(AuthUtil.getMap("npwd", "新密码长度应在6-20个字"));
		if(StringUtils.isBlank(npwd2))
			mapList.add(AuthUtil.getMap("npwd2", "新密码密码不能为空"));
		else if(!StringUtils.isBlank(npwd2)&&(npwd2.length()>20||npwd2.length()<6))
			mapList.add(AuthUtil.getMap("npwd2", "新密码长度应在6-20个字"));
		else if (!npwd.equals(npwd2)) 
			mapList.add(AuthUtil.getMap("npwd2", "两次输入的密码不一致"));
		return mapList;
	}
	public void common(String cookie, String w_name, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			if(!StringUtils.isBlank(cookie))
			{
				cookie = ThreeDESUtil.decode(cookie, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
				String[] adminStrings = cookie.split(",");
				//后期可直接从redis取
				Admin admin  = iadminDao.getAdminByName(adminStrings[0]);
				List<Manage_Menu> menuList = new ArrayList<Manage_Menu>();
				if(admin !=null && admin.getPassword().equals(adminStrings[1]))
				{
					menuList = imanage_MenuDao.getMenuListByAdminId(admin.getId());
				}
				model.addAttribute("id", admin.getId());
				model.addAttribute("menuList", menuList);
				model.addAttribute("admin_name", adminStrings[0]);
				int messagecount=imessageDao.messsageCount(admin.getId());
				model.addAttribute("messagecount", messagecount);
			}
			if(!StringUtils.isBlank(w_name))
			{
				w_name = ThreeDESUtil.decode(w_name, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
				model.addAttribute("w_name", w_name);
			}
		} catch (Exception e) {
			log.error("全局方法异常", e);
		}
	}
}

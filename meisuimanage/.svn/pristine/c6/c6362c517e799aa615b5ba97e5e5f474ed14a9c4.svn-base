package com.meisui.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.AdminService;


@Controller
@RequestMapping("/permission")
public class PermissionController{
	static Logger log = Logger.getLogger(PermissionController.class.getClass());

	@Autowired
	private AdminService adminService;

	/***
	 * 
	 * <p>功能描述：获取后台账号列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 下午3:39:10</p>
	 *
	 * @param s 搜索词
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "s", required = false, defaultValue = "") String s,
			           @CookieValue(value="admin",required=true,defaultValue="")String cook,
			Model model) {

		return adminService.getAdminList(s,cook, model);
	}
	/***
	 * 
	 * <p>功能描述：添加后台账号页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 下午3:55:20</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@CookieValue(value="admin",required=false,defaultValue="")String cookie,
			Model model) {

		return adminService.addAdmin(cookie,model);
	}
	/***
	 * 
	 * <p>功能描述：编辑后台账号页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 下午3:53:53</p>
	 *
	 * @param id 账号id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value="admin",required=false,defaultValue="")String cookie,
			Model model) {

		return adminService.editAdmin(id, cookie, model);
	}
	/***
	 * 
	 * <p>功能描述：保存后台账号信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 下午3:42:48</p>
	 *
	 * @param mIds 菜单id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "remark", required = false, defaultValue = "") String remark,
			@RequestParam(value = "group", required = false, defaultValue = "2") int group,
			@RequestParam(value = "is_read", required = false, defaultValue = "0") int is_read,
			@RequestParam(value = "mIds", required = false, defaultValue="") String mIds)
	{

		return adminService.saveAdmin(id, nickname, username, password, remark, group,is_read, mIds);
	}	
	/***
	 * 
	 * <p>功能描述：删除后台账号</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 下午3:40:40</p>
	 *
	 * @param id 账号id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {

		return adminService.deleteAdmin(id);
	}
}

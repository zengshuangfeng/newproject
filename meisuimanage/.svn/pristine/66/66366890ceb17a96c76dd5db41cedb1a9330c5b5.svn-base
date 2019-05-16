package com.meisui.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Video_AdminService;

/**
 * <p>文件名称：Video_AdminController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月27日 上午9:57:44
 */
@RequestMapping("/videoadmin")
@Controller
public class Video_AdminController {
	@Autowired
	private Video_AdminService video_AdminService;
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
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(value="nickname", required=false, defaultValue="")String nickname,
			@RequestParam(value="page", required=false, defaultValue="1")int page,
			@CookieValue(value="admin",required=true,defaultValue="")String cookie,
			Model model){
		return video_AdminService.getList(cookie, nickname, page, model);
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
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname,
			@RequestParam(value = "username", required = false,defaultValue="")String username,
			@RequestParam(value = "password", required = false,defaultValue="")String password,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return video_AdminService.save(id, nickname, username, password, w_name, request);
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
	@ResponseBody
	@RequestMapping(value = "/close", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public int close(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "is_close", required = false,defaultValue="0")int is_close,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return video_AdminService.close(id, is_close, w_name, request);
	}
}

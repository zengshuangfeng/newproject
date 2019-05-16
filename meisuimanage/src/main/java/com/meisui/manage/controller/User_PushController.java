package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.User_PushService;


@Controller
@RequestMapping("/userpush")
public class User_PushController {
	@Autowired
	private User_PushService user_PushService;
	/**
	 * 
	 * <p>功能描述：获取消息推送管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午9:15:20</p>
	 *
	 * @param page 页码
	 * @param status 状态
	 * @param s_time 推送开始时间
	 * @param e_time 推送结束时间
	 * @param title 标题
	 * @param model
	 * @return
	 */		
	@RequestMapping(value="/list", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list(@RequestParam(value="page", required=false, defaultValue="1")int page,
			@RequestParam(value="platform", required=false, defaultValue="")String platform,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			Model model){
		return user_PushService.getUserPushList(page, platform, s_time, e_time, model);		
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addActivity(Model model)
	{
		return user_PushService.addUserPush(model);
	}
	
	/***
	 * 
	 * <p>功能描述：编辑消息推送页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午10:32:25</p>
	 *
	 * @param id 消息推送id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editActivity(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			Model model) {

		return user_PushService.editUserPush(id, model);
	}
	/***
	 * 
	 * <p>功能描述：保存消息推送管理信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午9:33:20</p>
	 *
	 * @param id 消息推送id
	 * @param title 标题
	 * @param pic 图片
	 * @param p_content 推送内容
	 * @param jumpstyle 
	 * @param start_time 推送开始时间
	 * @param end_time 推送结束时间
	 * @param href 跳转内容
	 * @param p_range 推送用户范围0单个用户1多用户
	 * @param userid 用户ID
	 * @param version 版本
	 * @param platform 0 全平台 1 IOS 2 android
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveUserPush(
			@RequestParam(value="id", required=false,defaultValue="0") int id,
			@RequestParam(value="title", required=false,defaultValue="") String title,
			@RequestParam(value="p_content", required=false,defaultValue="") String p_content,
			@RequestParam(value="jumpstyle", required=false,defaultValue="") String jumpstyle,
			@RequestParam(value="start_time", required=false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value="end_time", required=false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value="href", required=false,defaultValue="") String href,
			@RequestParam(value="p_range", required=false,defaultValue="0") int p_range,
			@RequestParam(value="userid", required=false,defaultValue="0") String userid,
			@RequestParam(value="version", required=false) String version,
			@RequestParam(value="platform", required=false) String platform,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {
		
		return user_PushService.saveUserPush(id, title, p_content, jumpstyle, start_time,end_time, href,p_range,userid, version, platform, w_name, request);
	}
	
	/***
	 * 
	 * <p>功能描述：删除消息推送</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午10:30:15</p>
	 *
	 * @param id 消息推送id
	 * @param w_name 操作人员
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int deleteActivity(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_PushService.deleteUserPush(id, w_name, request);
	}
	
	/**
	 * @param id
	 * @param w_name
	 * @param request
	 * @param model
	 * @return
	 * 重新推送消息
	 */
	@ResponseBody
	@RequestMapping(value = "/repush", method = RequestMethod.POST)
	public int repushUserPush(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return user_PushService.repushUserPush(w_name, id, request);
	}
}

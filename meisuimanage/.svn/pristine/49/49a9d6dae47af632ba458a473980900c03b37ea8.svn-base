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

import com.meisui.manage.service.ActivityService;

/**
 * <p>文件名称：ActivityController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@RequestMapping("/activity")
@Controller
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	/**
	 * 
	 * <p>功能描述：活动列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:41:00</p>
	 *
	 * @param is_online 状态 1上线 0下线
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return activityService.getlist(is_online, page, model);
	}
	/**
	 * 
	 * <p>功能描述：活动添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:42:10</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		return activityService.add(model);
	}
	/**
	 * 
	 * <p>功能描述：活动编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:43:18</p>
	 *
	 * @param id 活动id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			Model model) {	
		return activityService.edit(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存活动信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:50:08</p>
	 *
	 * @param id 活动id
	 * @param name 活动名称
	 * @param url 链接
	 * @param start_time 上线时间
	 * @param end_time 下线时间
	 * @param is_online 状态 1上线 0下载
	 * @param platform 平台 0全平台 1IOS 2android
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "url", required = false, defaultValue="") String url,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "platform", required = false,defaultValue="00") String platform,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return activityService.save(id, name, url, start_time, end_time, is_online, platform, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：更新活动上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 下午4:51:49</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 活动id
	 * @param is_online 状态 1上线 0下线
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateonline", method = RequestMethod.POST)
	public int updateOnline(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "is_online", required = false, defaultValue="0")int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return activityService.updateOnline(w_name, id, is_online, request);
	}
}

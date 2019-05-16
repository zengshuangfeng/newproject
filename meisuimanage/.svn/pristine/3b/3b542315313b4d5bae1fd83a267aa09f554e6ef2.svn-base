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

import com.meisui.manage.service.Change_ActivityService;

/**
 * <p>文件名称：Change_ActivityController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午3:00:19
 */
@RequestMapping("/changeactivity")
@Controller
public class Change_ActivityController {
	@Autowired
	private Change_ActivityService change_ActivityService;
	/**
	 * 
	 * <p>功能描述：充值活动列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return change_ActivityService.getChangeActivityList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：充值活动添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月11日 下午2:30:02</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		return change_ActivityService.addChangeActivity(model);
	}
	/**
	 * 
	 * <p>功能描述：充值活动编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 充值活动id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return change_ActivityService.editChangeActivity(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存充值活动信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 充值活动id
	 * @param change_id 充值套餐表id
	 * @param activity_rmb 活动价
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param is_first 是否是首充 1是 0否
	 * @param is_online 是否上线 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "change_id", required = false, defaultValue="0") int change_id,
			@RequestParam(value = "activity_rmb", required = false, defaultValue="0") int activity_rmb,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value = "is_first", required = false, defaultValue="0") int is_first,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return change_ActivityService.saveChangeActivity(id, change_id, activity_rmb, start_time, end_time, is_first, is_online, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除充值活动</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 充值活动id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return change_ActivityService.deleteChangeActivity(w_name, id, request);
	}
}

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

import com.meisui.manage.service.Live_TimeService;

/**
 * <p>文件名称：Live_TimeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月5日 上午11:43:13
 */
@RequestMapping("/livetime")
@Controller
public class Live_TimeController {
	@Autowired
	private Live_TimeService live_TimeService;
	/**
	 * 
	 * <p>功能描述：直播时间管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:42:39</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return live_TimeService.getLiveTimeList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：获取直播时间信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:42:21</p>
	 *
	 * @param id 直播时间id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(@RequestParam(value = "id", required = false, defaultValue="0") int id) {	
		return live_TimeService.getLiveTime(id);
	}
	/**
	 * 
	 * <p>功能描述：保存直播时间信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:41:45</p>
	 *
	 * @param id 直播时间id
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="HH:mm") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="HH:mm") Date e_time,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return live_TimeService.saveLiveTime(id, s_time, e_time, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除直播时间</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月5日 上午11:40:48</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 直播时间id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return live_TimeService.deleteLiveTime(w_name, id, request);
	}
}

package com.meisui.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.Game_ProfitService;

/**
 * <p>文件名称：Game_ProfitController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午2:03:40
 */
@RequestMapping("/gameprofit")
@Controller
public class Game_ProfitController {
	@Autowired
	private Game_ProfitService game_ProfitService;
	/***
	 * 
	 * <p>功能描述：游戏历史收益列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:00:09</p>
	 *
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {	
		return game_ProfitService.getList(start_time, end_time, page, model);
	}
}

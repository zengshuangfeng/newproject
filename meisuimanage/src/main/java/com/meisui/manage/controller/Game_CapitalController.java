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

import com.meisui.manage.service.Game_CapitalService;

/**
 * <p>文件名称：Game_CapitalController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 上午11:40:26
 */
@RequestMapping("/gamecapital")
@Controller
public class Game_CapitalController {
	@Autowired
	private Game_CapitalService game_CapitalService;
	/**
	 * 
	 * <p>功能描述：游戏调控设置</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 上午11:31:11</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {	
		return game_CapitalService.getList(model);
	}
	/**
	 * 
	 * <p>功能描述：保存游戏调控设置信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 上午11:39:19</p>
	 *
	 * @param id 控盘表id
	 * @param upper_limit 房间上限
	 * @param lower_limit 房间下限
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "upper_limit", required = false, defaultValue="0") float upper_limit,
			@RequestParam(value = "lower_limit", required = false, defaultValue="0") float lower_limit,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return game_CapitalService.save(id, upper_limit, lower_limit, w_name, request);
	}
}

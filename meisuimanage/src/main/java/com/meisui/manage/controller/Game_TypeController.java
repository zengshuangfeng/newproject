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

import com.meisui.manage.service.Game_TypeService;

/**
 * <p>文件名称：Game_TypeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月15日 上午10:20:55
 */
@RequestMapping("/gametype")
@Controller
public class Game_TypeController {
	@Autowired
	private Game_TypeService game_TypeService;
	/**
	 * 
	 * <p>功能描述：游戏列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 上午10:19:44</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {	
		return game_TypeService.getList(model);
	}
	/**
	 * 
	 * <p>功能描述：更新游戏是否开启</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 上午10:19:55</p>
	 *
	 * @param id 游戏id
	 * @param is_open 是否开启 1 是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateopen", method = RequestMethod.POST)
	public int updateOpen(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "is_open", required = false, defaultValue="0") int is_open,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return game_TypeService.updateOpen(id, is_open, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：更新游戏排序</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 上午10:19:55</p>
	 *
	 * @param id 游戏id
	 * @param sort 排序
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatesort", method = RequestMethod.POST)
	public int updateSort(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return game_TypeService.updateSort(id, sort, w_name, request);
	}
}

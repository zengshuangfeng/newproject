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

import com.meisui.manage.service.User_TaskService;

/**
 * <p>文件名称：User_TaskController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午10:39:27
 */
@RequestMapping("/usertask")
@Controller
public class User_TaskController {
	@Autowired
	private User_TaskService user_TaskService;
	/**
	 * 
	 * <p>功能描述：每日任务列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:31:59</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_TaskService.getUserTaskList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：每日任务添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:33:38</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {	
		return "usertask/add";
	}
	/**
	 * 
	 * <p>功能描述：每日任务编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:33:38</p>
	 *
	 * @param id 每日任务id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return user_TaskService.editUserTask(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存每日任务信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:37:56</p>
	 *
	 * @param id 每日任务id
	 * @param type 类型
	 * @param name 任务名称
	 * @param icon 任务icon
	 * @param num 完成次数
	 * @param reward_count 奖励钻石
	 * @param sort 排序
	 * @param w_name
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "icon", required = false, defaultValue="") String icon,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "num", required = false, defaultValue="0") int num,
			@RequestParam(value = "reward_count", required = false, defaultValue="0")int reward_count,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_TaskService.saveUserTask(id, type, name, icon, num, reward_count, sort, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除每日任务</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午10:37:33</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 每日任务id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return user_TaskService.deleteUserTask(w_name, id, request);
	}
}

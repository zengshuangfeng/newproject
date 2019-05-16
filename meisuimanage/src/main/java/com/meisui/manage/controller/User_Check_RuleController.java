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

import com.meisui.manage.service.User_Check_RuleService;

/**
 * <p>文件名称：User_Check_RuleController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午11:23:06
 */
@RequestMapping("/checkrule")
@Controller
public class User_Check_RuleController {
	@Autowired
	private User_Check_RuleService user_Check_RuleService;
	/**
	 * 
	 * <p>功能描述：每日签到列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午11:18:00</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_Check_RuleService.getUserCheckRuleList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：提取每日签到信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午11:22:38</p>
	 *
	 * @param id 每日签到id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getDetail(@RequestParam(value = "id", required = false, defaultValue="0") int id) {	
		return user_Check_RuleService.getUserCheckRule(id);
	}
	/**
	 * 
	 * <p>功能描述：更新每日签到</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午11:20:35</p>
	 *
	 * @param id 每日签到id
	 * @param days 签到第几天
	 * @param reward_count 奖励钻石数
	 * @param min_rand 最小随机数
	 * @param max_rand 最大随机数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "days", required = false, defaultValue="0") int days,
			@RequestParam(value = "reward_count", required = false, defaultValue="0") int reward_count,
			@RequestParam(value = "min_rand", required = false, defaultValue="0") int min_rand,
			@RequestParam(value = "max_rand", required = false, defaultValue="0") int max_rand,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_Check_RuleService.saveUserCheckRule(id, days, reward_count, min_rand, max_rand, w_name, request);
	}
}

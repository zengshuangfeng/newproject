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

import com.meisui.manage.service.Level_ScoreService;

/**
 * <p>文件名称：Level_ScoreController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午4:54:48
 */
@RequestMapping("/levelscore")
@Controller
public class Level_ScoreController {
	@Autowired
	private Level_ScoreService level_ScoreService;
	/**
	 * 
	 * <p>功能描述：等级列表</p>
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
		return level_ScoreService.getLevelScoreList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：等级编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 等级id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return level_ScoreService.editLevelScore(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存等级信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 等级id
	 * @param name 等级名称
	 * @param icon 等级icon
	 * @param level 等级
	 * @param score 经验分数
	 * @param recharge_rmb 充值金额
	 * @param is_online 是否上线 1是 0否
	 * @param color 等级图标色值
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "icon", required = false, defaultValue="") String icon,
			@RequestParam(value = "level", required = false, defaultValue="0") int level,
			@RequestParam(value = "score", required = false, defaultValue="0") int score,
			@RequestParam(value = "recharge_rmb", required = false, defaultValue="0") int recharge_rmb,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "color", required = false, defaultValue="") String color,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return level_ScoreService.saveLevelScore(id, name, icon, level, score, recharge_rmb, is_online, color, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除等级</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 等级id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return level_ScoreService.deleteLevelScore(w_name, id, request);
	}
}

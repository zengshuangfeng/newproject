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

import com.meisui.manage.service.Level_PrivilegeService;

/**
 * <p>文件名称：LevelPrivilegeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午5:43:42
 */
@RequestMapping("/levelprivilege")
@Controller
public class Level_PrivilegeController {
	@Autowired
	private Level_PrivilegeService level_PrivilegeService;

	/**
	 * 
	 * <p>功能描述：等级特权列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午5:40:21</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return level_PrivilegeService.getLevelPrivilegeList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：等级特权编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午5:41:07</p>
	 *
	 * @param id 等级特权id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return level_PrivilegeService.editLevelPrivilege(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存等级特权信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午5:42:54</p>
	 *
	 * @param id 等级特权id
	 * @param title 等级特权名称
	 * @param icon 等级特权图标
	 * @param level 解锁等级
	 * @param sort 排序
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "title", required = false, defaultValue="") String title,
			@RequestParam(value = "icon", required = false, defaultValue="") String icon,
			@RequestParam(value = "level", required = false, defaultValue="0") int level,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return level_PrivilegeService.saveLevelPrivilege(id, title, icon, level, sort, w_name, request);
	}
}

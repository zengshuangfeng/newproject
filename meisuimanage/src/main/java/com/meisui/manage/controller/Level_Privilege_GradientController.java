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

import com.meisui.manage.service.Level_Privilege_GradientService;

/**
 * <p>文件名称：Level_Privilege_GradientController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午2:30:55
 */
@RequestMapping("/levelprivilege/gradient")
@Controller
public class Level_Privilege_GradientController {
	@Autowired
	private Level_Privilege_GradientService level_Privilege_GradientService;
	/**
	 * 
	 * <p>功能描述：等级特权梯度列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param p_id 等级特权表id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "p_id", required = false,defaultValue="0") int p_id, 
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return level_Privilege_GradientService.getLevelPrivilegeGradientList(p_id, page, model);
	}
	/**
	 * 
	 * <p>功能描述：等级特权梯度添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月11日 下午2:30:02</p>
	 *
	 * @param p_id 等级特权表id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam(value = "p_id", required = false,defaultValue="0") int p_id, 
			Model model) {	
		return level_Privilege_GradientService.addLevelPrivilegeGradient(p_id, model);
	}
	/**
	 * 
	 * <p>功能描述：等级特权梯度编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 等级特权梯度id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return level_Privilege_GradientService.editLevelPrivilegeGradient(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存等级信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 等级特权梯度id
	 * @param p_id 等级特权表id
	 * @param name 等级名称
	 * @param level 解锁等级
	 * @param is_online 是否上线 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "p_id", required = false, defaultValue="0") int p_id,
			@RequestParam(value = "level", required = false, defaultValue="0") int level,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return level_Privilege_GradientService.saveLevelPrivilegeGradient(id, p_id, name, level, is_online, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除等级特权梯度</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 等级特权梯度id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return level_Privilege_GradientService.deleteLevelPrivilegeGradient(w_name, id, request);
	}
}

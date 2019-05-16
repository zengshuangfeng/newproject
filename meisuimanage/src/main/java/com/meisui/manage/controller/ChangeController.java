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

import com.meisui.manage.service.ChangeService;

/**
 * <p>文件名称：ChangeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午3:16:58
 */
@RequestMapping("/change")
@Controller
public class ChangeController {
	@Autowired
	private ChangeService changeService;
	/**
	 * 
	 * <p>功能描述：钻石套餐列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:05:14</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return changeService.getChangeList(page, model);
	}
	/**
	 * 
	 * <p>功能描述：钻石套餐添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:06:17</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		model.addAttribute("activeUrl", "change");
		return "change/add";
	}
	/**
	 * 
	 * <p>功能描述：钻石套餐编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:06:17</p>
	 *
	 * @param id 钻石套餐id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return changeService.editChange(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存钻石套餐信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:07:56</p>
	 *
	 * @param id 钻石套餐id
	 * @param virtual_count 虚拟币
	 * @param change_rmb 需人民币
	 * @param is_online 是否上线 1是 0否
	 * @param w_name 编辑人员
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "virtual_count", required = false, defaultValue="0") int virtual_count,
			@RequestParam(value = "change_rmb", required = false, defaultValue="0") int change_rmb,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "is_virtual", required = false, defaultValue="0") int is_virtual,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return changeService.saveChange(id, virtual_count, change_rmb, is_online,is_virtual, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：钻石套餐信息删除</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:14:06</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 钻石套餐id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return changeService.delete(w_name, id, request);
	}
	/**
	 * 
	 * <p>功能描述：更新钻石套餐是否上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 钻石套餐id
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateisonline", method = RequestMethod.POST)
	public int updateIsOnline(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "is_online", required = false,defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return changeService.updateIsOnline(w_name, id, is_online, request);
	}
	
	/**
	 * 
	 * <p>功能描述：更新钻石会员价状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 钻石套餐id
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateivirtual", method = RequestMethod.POST)
	public int updateIsVirtual(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "is_virtual", required = false,defaultValue="0") int is_virtual,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return changeService.updateIsVirtual(w_name, id, is_virtual, request);
	}
}

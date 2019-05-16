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

import com.meisui.manage.service.Admin_GroupService;


/**
 * <p>文件名称：Admin_GroupController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/admingroup")
public class Admin_GroupController {
	@Autowired
	private Admin_GroupService admin_GroupService;
	
	/**
	 * <p>功能描述：群组列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model)
	{
		return admin_GroupService.getGroupList(page, model);
	}

	/**
	 * 
	 * <p>功能描述：保存群组</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:40:25</p>
	 *
	 * @param id
	 * @param name 群组名称
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveProductPic(
			@RequestParam(value="id", required = false,defaultValue="0") int id,
			@RequestParam(value="name", required = false,defaultValue="") String name,
			@CookieValue(value="w_name", defaultValue="")String w_name, HttpServletRequest request) {

		return admin_GroupService.save(id, name, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：添加群组</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:40:15</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("activeUrl", "admingroup");
		return  "admingroup/add";
	}
	/**
	 * <p>功能描述：编辑群组</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:40:45</p>
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			Model model
			){
		return admin_GroupService.getEdit(id, model);
	}
	/**
	 * <p>功能描述：</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午10:39:51</p>
	 *
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "id", required = true, defaultValue="-1") int id,
			HttpServletRequest request) {

		return admin_GroupService.delete(w_name, id, request);
	}
}

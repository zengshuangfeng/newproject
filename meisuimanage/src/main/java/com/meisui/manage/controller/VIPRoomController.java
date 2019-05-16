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

import com.meisui.manage.service.VIPRoomService;

/**
 * 
 * <p>文件名称：VIPRoomController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年9月26日下午4:35:04 
 */
@RequestMapping("/viproom")
@Controller
public class VIPRoomController {
	
	@Autowired
	private VIPRoomService vipRoomService;
	
	
	@RequestMapping(value="/vipchangelist",method=RequestMethod.GET)
	public String getVIPChangeList(@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model
			){
		
		return vipRoomService.getVIPChangeList(page,model);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model){
		return vipRoomService.edit(id,model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value="id",required=false,defaultValue="0")int id,
			@RequestParam(value="name",required=false,defaultValue="")String name,
			@RequestParam(value="days",required=false,defaultValue="0")int days,
			@RequestParam(value="price",required=false,defaultValue="0")int change_rmb,
			@RequestParam(value="is_online",required=false,defaultValue="0")int is_online,
			@CookieValue(value="w_name",defaultValue="")String w_name,
		    HttpServletRequest request){
		return vipRoomService.save(id, name,days,change_rmb, is_online, w_name, request);
	}

}

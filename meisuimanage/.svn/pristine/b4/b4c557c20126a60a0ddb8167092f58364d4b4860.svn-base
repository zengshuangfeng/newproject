package com.meisui.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.ThanksgivingService;

/**
 * <p>文件名称：ThanksgivingController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@RequestMapping("/thanks")
@Controller
public class ThanksgivingController {
	
	@Autowired
	private ThanksgivingService thanksService;
	
	/**
	 * 感恩节留言列表
	 * @param fuuid
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(
			@RequestParam(value = "uid", defaultValue = "0", required = false) long uid, 
			@RequestParam(value = "page", defaultValue = "1") int page, Model model){
		return thanksService.list(uid, page, model);
		
	}
	
	/**
	 * 删除留言
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = true) long id){
		 return thanksService.delete(id);
	}
	
	/**
	 * 添加点赞数
	 * @param id
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addGreatCount")
	public int addGreatCount(@RequestParam(value = "id") long id, @RequestParam(value = "num") int num){
		return thanksService.addGreatCount(id, num);
	}
}

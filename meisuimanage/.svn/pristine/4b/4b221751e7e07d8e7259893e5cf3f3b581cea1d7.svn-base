package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Anchor_DayPayService;

/**
 * 
 * <p>文件名称：Anchor_DayPayController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年11月7日下午3:07:09 
 */
@Controller
@RequestMapping("/")
public class Anchor_DayPayController {
	@Autowired
	private Anchor_DayPayService anchor_DayPayService;
	
	@RequestMapping(value = "anchordaypay/list")
	public String getList(@RequestParam(value = "uid",required = false,defaultValue = "0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "union_id", required = false,defaultValue="0") int union_id,
			@RequestParam(value = "start_time",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date start_time,
			@RequestParam(value = "end_time",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model){
		return anchor_DayPayService.getList(uid,f_uuid,nickname,union_id,page,model);
	}
	@ResponseBody
	@RequestMapping(value = "anchordaypay/savepay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String savePay(@RequestParam(value = "union_id", required = false,defaultValue="0")int union_id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return anchor_DayPayService.savePay(union_id, w_name, request);
	}
	@RequestMapping(value = "anchordaypay/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "union_id", required = false,defaultValue="0")int union_id,HttpServletResponse response) {	
		return anchor_DayPayService.exportExcel(union_id, response);
	}	
}

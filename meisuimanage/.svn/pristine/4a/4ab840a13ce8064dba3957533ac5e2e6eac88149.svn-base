package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.User_ExchangeService;

/**
 * <p>文件名称：User_ExchangeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 下午3:47:23
 */
@Controller
@RequestMapping("/userexchange")
public class User_ExchangeController {
	@Autowired
	private User_ExchangeService user_ExchangeService;
	/**
	 * 
	 * <p>功能描述：订单列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 下午3:31:08</p>
	 *
	 * @param status 状态
	 * @param type 类型
	 * @param p_name 商品名称
	 * @param start_time 下单时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "status", required = false,defaultValue="-1") int status,
			@RequestParam(value = "type", required = false,defaultValue="-1") int type,
			@RequestParam(value = "p_name", required = false,defaultValue="") String p_name,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_ExchangeService.getList(status, type, p_name, start_time, page, model);
	}
	/**
	 * 
	 * <p>功能描述：订单详细页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 上午10:28:56</p>
	 *
	 * @param id 订单id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			Model model) {	
		return user_ExchangeService.detail(id, model);
	}
	/**
	 * 
	 * <p>功能描述：订单json</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 下午3:43:01</p>
	 *
	 * @param id 订单id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String get(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			Model model) {	
		return user_ExchangeService.getJson(id);
	}
	/**
	 * 
	 * <p>功能描述：更新订单状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 商品id
	 * @param status 订单状态
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
	public int updateStatus(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "status", required = false,defaultValue="0") int status,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_ExchangeService.updateStatus(w_name, id, status, request);
	}
	/**
	 * 
	 * <p>功能描述：更新订单物流</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 商品id
	 * @param status 订单状态
	 * @param remark 备注
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatelogistics", method = RequestMethod.POST)
	public int updateLogistics(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "status", required = false,defaultValue="0") int status,
			@RequestParam(value = "logistics_name", required = false,defaultValue="") String logistics_name,
			@RequestParam(value = "logistics_code", required = false,defaultValue="") String logistics_code,
			@RequestParam(value = "remark", required = false,defaultValue="") String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_ExchangeService.updateLogistics(w_name, id, status, logistics_name, logistics_code, remark, request);
	}
}

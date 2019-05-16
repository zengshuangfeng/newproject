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

import com.meisui.manage.service.Gameroom_RecordService;

/**
 * <p>文件名称：Gameroom_RecordController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午2:51:27
 */
@RequestMapping("/gameroomrecord")
@Controller
public class Gameroom_RecordController {
	@Autowired
	private Gameroom_RecordService gameroom_RecordService;
	/**
	 * 
	 * <p>功能描述：游戏房间列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:49:44</p>
	 *
	 * @param anchor_state -1全部 1直播中
	 * @param start_time 开始时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "anchor_state", required = false, defaultValue="-1")int anchor_state,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {	
		return gameroom_RecordService.getList(anchor_state, start_time, page, model);
	}
	/**
	 * 
	 * <p>功能描述：游戏房间干预</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:45:11</p>
	 *
	 * @param w_name
	 * @param id 游戏房间数据id
	 * @param is_intervene 是否干预 1是 0否
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anchorlist", method = RequestMethod.GET)
	public String getListWithAnchor(
			@RequestParam(value = "f_uuid", required = false, defaultValue="0")long f_uuid,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {	
		return gameroom_RecordService.getListWithF_uuid(f_uuid, start_time, page, model);
	}
	/**
	 * 
	 * <p>功能描述：主播游戏列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:49:33</p>
	 *
	 * @param f_uuid 主播房间号
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateintervene", method = RequestMethod.POST)
	public int updateIntervene(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return gameroom_RecordService.updateIntervene(w_name, id, request);
	}
}

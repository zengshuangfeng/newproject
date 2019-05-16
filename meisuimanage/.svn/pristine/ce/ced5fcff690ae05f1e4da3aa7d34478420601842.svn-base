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

import com.meisui.manage.service.Anchor_RecordService;
import com.meisui.manage.service.User_AnchorService;

/**
 * <p>文件名称：AnchorRecordController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 */
@Controller
@RequestMapping("/anchorrecord")
public class Anchor_RecordController {
	@Autowired
	private Anchor_RecordService anchor_RecordService;
	@Autowired
	private User_AnchorService user_AnchorService;
	/**
	 * 
	 * <p>功能描述：直播记录列表</p>
	 * <p>创建人：</p>
	 *
	 * @param uid 主播uid
	 * @param nickname 主播昵称
	 * @param is_trial 是否是试播 1是 0否
	 * @param union_id 所属工会
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUserAnchorList(@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,			
			@RequestParam(value = "is_trial", required = false,defaultValue="-1") int is_trial,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model, HttpServletRequest request) {

		return anchor_RecordService.getAnchorRecordList(operate_center_id, agent_id, uid, f_uuid, nickname, is_trial, start_time, end_time, page, model, request);
	}
	@RequestMapping(value = "/timelist", method = RequestMethod.GET)
	public String getUserAnchorTimeList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam(value = "end_date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return user_AnchorService.getAnchorTimeList(uid, date, end_date, page, "anchorrecord", model);
	}
	@RequestMapping(value = "/getinfo", method = RequestMethod.GET)
	public String getUserAnchorInfo(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "apply_id", required = true, defaultValue="0")int apply_id,
			Model model) {	
		return user_AnchorService.getUserAnchorInfo(uid, apply_id, "anchorrecord", model);
	}
	/**
	 * 
	 * <p>功能描述：保存主播信息</p>
	 * <p>创建人：</p>
	 *
	 * @param id 主播表id
	 * @param uid 主播uid
	 * @param anchor_cover 封面
	 * @param remark 备注
	 * @param union_id 工会id
	 * @param w_name 编辑人员
	 * @param divide_proportion 主播分成
	 * @param is_trial 是否是试播 1是 0否
	 * @param birthday_pic 生日卡片图
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveUserAnchorInfo(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "anchor_cover", required = false, defaultValue="") String anchor_cover,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,	
			@RequestParam(value = "is_trial", required = false, defaultValue="0") int is_trial,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "youxi", required = false, defaultValue="0") int youxi,
			@RequestParam(value = "live", required = false, defaultValue="0") int live,
			@RequestParam(value = "is_vip",required = false,defaultValue="0") int is_vip,
		
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	

		return user_AnchorService.saveUserAnchor(id, uid, anchor_cover, remark, is_trial, type, youxi, live,is_vip,  w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/getname", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUserName(@RequestParam(value = "uid", required = true, defaultValue="0") long uid) {	
		return user_AnchorService.getUserName(uid);
	}
	@RequestMapping(value = "/exporttime", method = RequestMethod.GET)
	public String exportProfit(@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id,
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname,
			@RequestParam(value = "is_trial", required = false,defaultValue="-1") int is_trial,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			HttpServletRequest request, HttpServletResponse response) {	
		return anchor_RecordService.exportTime(operate_center_id, agent_id, uid, f_uuid, nickname, is_trial, start_time, end_time, request, response);
	}
	@ResponseBody
	@RequestMapping(value = "/agentlist", produces = "text/json;charset=UTF-8")
	public String agentList(int centerId) {
		return anchor_RecordService.agentList(centerId);
	}
}

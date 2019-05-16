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

import com.meisui.manage.service.Anchor_WeekPayService;

@Controller
@RequestMapping("/")
public class Anchor_WeekPayController {
	@Autowired
	private Anchor_WeekPayService anchor_WeekPayService;
	
	/**
	 * 主播周结列表
	 * @param uid 主播UID
	 * @param f_uuid 主播房间号
	 * @param nickname 主播昵称
	 * @param union_id 工会id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "anchorweekpay/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "union_id", required = false,defaultValue="0") int union_id,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return anchor_WeekPayService.getList(uid, f_uuid, nickname, union_id, page, model);
	}
	/**
	 * 设置违规处罚
	 * @param uid 用户UID
	 * @param illegal_money 违规金额
	 * @param illegal_time 违规日期
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "anchorweekpay/saveanchorpayillegal", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveAnchorPayIllegal(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "illegal_money", required = false,defaultValue="0")double illegal_money,
			@RequestParam(value = "illegal_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date illegal_time,
			@RequestParam(value = "remark", required = false,defaultValue="") String remark,
			@RequestParam(value = "type", required = false,defaultValue="0") int type,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return anchor_WeekPayService.saveAnchorPayIllegal(uid, illegal_money, illegal_time, remark, type, w_name, request);
	}
	/**
	 * 主播违规处罚备注列表
	 * @param uid 用户UId
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "anchorweekpay/illegallist", method = RequestMethod.GET)
	public String getIllegalList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return anchor_WeekPayService.getIllegalList(uid, page, model);
	}
	@ResponseBody
	@RequestMapping(value = "anchorweekpay/savepay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String savePay(@RequestParam(value = "union_id", required = false,defaultValue="0")int union_id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return anchor_WeekPayService.savePay(union_id, w_name, request);
	}
	
	@RequestMapping(value = "anchorweekpay/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "union_id", required = false,defaultValue="0")int union_id,HttpServletResponse response) {	
		return anchor_WeekPayService.exportExcel(union_id, response);
	}
}

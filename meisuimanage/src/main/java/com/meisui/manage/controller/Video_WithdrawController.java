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

import com.meisui.manage.service.Video_WithdrawService;

/**
 * <p>文件名称：Video_WithdrawController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月6日 下午3:49:32
 */
@Controller
public class Video_WithdrawController {
	@Autowired
	private Video_WithdrawService video_WithdrawService;
	/**
	 * 
	 * <p>功能描述：视频提现申请列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午3:41:13</p>
	 *
	 * @param o_id 订单号
	 * @param nickname 昵称
	 * @param status 状态
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/withdrawapply/list", method = RequestMethod.GET)
	public String getAuditList(@RequestParam(value = "status", required = false,defaultValue="-1") int status,
			@RequestParam(value = "o_id", required = false, defaultValue="") String o_id,
			@RequestParam(value = "nickname", required = false, defaultValue="") String nickname,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return video_WithdrawService.getApplyList(o_id, nickname, status, page, model);
	}
	/**
	 * 
	 * <p>功能描述：视频提现待支付列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午3:41:13</p>
	 *
	 * @param o_id 订单号
	 * @param nickname 昵称
	 * @param status 状态
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/withdrawpay/list", method = RequestMethod.GET)
	public String getPayList(@RequestParam(value = "status", required = false,defaultValue="-1") int status,
			@RequestParam(value = "o_id", required = false, defaultValue="") String o_id,
			@RequestParam(value = "nickname", required = false, defaultValue="") String nickname,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return video_WithdrawService.getPayList(o_id, nickname, status, page, model);
	}
	/**
	 * 
	 * <p>功能描述：视频提现申请审核</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午4:40:01</p>
	 *
	 * @param id 视频提现表id
	 * @param status 状态 0待审核 1待支付 2已完成 3已拒绝
	 * @param is_from 0申请列表 1待支付列表
	 * @param remark 备注
	 * @param w_name 操作人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/withdrawapply/audit","/withdrawpay/pay"}, method = RequestMethod.POST)
	public int updateStatus(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "status", required = false,defaultValue="0")int status,
			@RequestParam(value = "is_from", required = false,defaultValue="0")int is_from,
			@RequestParam(value = "remark", required = false,defaultValue="")String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return video_WithdrawService.updateStatus(id, status, is_from, remark, w_name, request);
	}
}

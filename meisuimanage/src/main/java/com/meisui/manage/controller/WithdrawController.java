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

import com.meisui.manage.service.WithdrawService;

/**
 * <p>文件名称：WithdrawController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午3:37:44
 */
@RequestMapping("/withdraw")
@Controller
public class WithdrawController {
	@Autowired
	private WithdrawService withdrawService;
	/**
	 * 
	 * <p>功能描述：获取提现列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午3:36:35</p>
	 *
	 * @param is_pay 是否打款 1是 0否
	 * @param uid 用户id
	 * @param nickname 昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "is_pay", required = false, defaultValue="-1") int is_pay,
			@RequestParam(value = "uid", required = false, defaultValue="0") int uid,
			@RequestParam(value = "nickname", required = false, defaultValue="") String nickname,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return withdrawService.getWithdrawList(is_pay, uid, nickname, page, model);
	}
	/**
	 * 
	 * <p>功能描述：更新提现信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午3:37:06</p>
	 *
	 * @param id 提现信息id
	 * @param is_pay 是否打款 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "is_pay", required = false, defaultValue="0") int is_pay,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return withdrawService.updateWithdraw(id, is_pay, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：添加提现信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午4:14:29</p>
	 *
	 * @param uid 用户id
	 * @param withdraw_type 账号类型 0支付宝
	 * @param withdraw_pass 提现账号
	 * @param withdraw_name 账号名
	 * @param withdraw_virtual 提现虚拟币
	 * @param withdraw_rmb 提现人民币
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savewithdrawinfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveInfo(@RequestParam(value = "uid", required = false,defaultValue="0") long uid,
			@RequestParam(value = "withdraw_type", required = false, defaultValue="0") int withdraw_type,
			@RequestParam(value = "withdraw_pass", required = false, defaultValue="") String withdraw_pass,
			@RequestParam(value = "withdraw_name", required = false, defaultValue="") String withdraw_name,
			@RequestParam(value = "withdraw_virtual", required = false, defaultValue="0") long withdraw_virtual,
			@RequestParam(value = "withdraw_rmb", required = false, defaultValue="0") double withdraw_rmb,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return withdrawService.saveWithdrawInfo(uid, withdraw_type, withdraw_pass, withdraw_name, withdraw_virtual, withdraw_rmb, remark, w_name, request);
	}
}

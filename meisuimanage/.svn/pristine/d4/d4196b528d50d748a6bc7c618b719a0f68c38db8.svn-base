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

import com.alibaba.druid.filter.AutoLoad;
import com.meisui.manage.service.ApplyService;
import com.meisui.manage.service.User_AnchorService;

/**
 * <p>文件名称：ApplyController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午2:25:59
 */
@RequestMapping("/apply")
@Controller
public class ApplyController {
	@Autowired
	private ApplyService applyService;
	@Autowired
	private User_AnchorService user_AnchorService;
	/**
	 * 
	 * <p>功能描述：主播申请列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午2:19:41</p>
	 *
	 * @param is_contact 是否联系 1是 0 否
	 * @param uid 用户uid
	 * @param nickname 昵称
	 * @param qq 
	 * @param phone 电话
	 * @param remark 备注
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "is_contact", required = false, defaultValue="-1") int is_contact,
			@RequestParam(value = "uid", required = false, defaultValue="0") int uid,
			@RequestParam(value = "f_uuid", required = false, defaultValue="0") long f_uuid,
			@RequestParam(value = "nickname", required = false, defaultValue="") String nickname,
			@RequestParam(value = "qq", required = false, defaultValue="") String qq,
			@RequestParam(value = "phone", required = false, defaultValue="") String phone,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return applyService.getApplyList(is_contact, uid, f_uuid, nickname, qq, phone, remark, start_time, end_time, page, model);
	}
	/**
	 * 
	 * <p>功能描述：更新主播申请信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午2:25:18</p>
	 *
	 * @param id 申清id
	 * @param is_contact 是否联系
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "is_contact", required = false, defaultValue="0") int is_contact,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return applyService.updateApply(id, is_contact, remark, w_name, request);
	}
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "is_contact", required = false,defaultValue="-1") int is_contact,
			@RequestParam(value = "uid", required = false,defaultValue="0") long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "qq", required = false,defaultValue="") String qq,
			@RequestParam(value = "phone", required = false,defaultValue="") String phone,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,
			HttpServletResponse response) {	
		return applyService.exportExcel(is_contact, uid, f_uuid, nickname, qq, phone, start_time, end_time, response);
	}

	/**
	 * 
	 * <p>功能描述：主播信息页</p>
	 * <p>创建人：</p>
	 *
	 * @param uid 主播uid
	 * @param model
	 */
	@RequestMapping(value = "/getinfo", method = RequestMethod.GET)
	public String getUserAnchorInfo(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "apply_id", required = true, defaultValue="0")int apply_id,
			Model model) {	
		return user_AnchorService.getUserAnchorInfo(uid, apply_id, "apply", model);
	}
	/**
	 * 
	 * <p>功能描述：保存主播信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午3:42:42</p>
	 *
	 * @param id 主播表id
	 * @param uid 主播uid
	 * @param anchor_cover 封面
	 * @param remark 备注
	 * @param union_id 工会id
	 * @param w_name 编辑人员
	 * @param divide_proportion 主播分成
	 * @param is_trial 是否是试播 1是 0否
	 * @param is_vip 是否是VIP 1是 0否
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

		return user_AnchorService.saveUserAnchor(id, uid, anchor_cover, remark, is_trial,  type, youxi, live,is_vip,  w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：获取主播昵称</p>
	 * <p>创建人：</p>
	 *
	 * @param uid 主播id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getname", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUserName(@RequestParam(value = "uid", required = true, defaultValue="0") long uid) {	
		return user_AnchorService.getUserName(uid);
	}
	@ResponseBody
	@RequestMapping(value = "/getanchoruniondivideproportion", method = RequestMethod.GET)
	public int getAnchorUnionDivideProportion(@RequestParam(value = "union_id", required = false,defaultValue="")int union_id) {

		return user_AnchorService.getAnchorUnionDivideProportion(union_id);
	}
	@ResponseBody
	@RequestMapping(value = "/getanchoruniononedivideproportion", method = RequestMethod.GET)
	public int getAnchorUnionOneDivideProportion(@RequestParam(value = "union_id", required = false,defaultValue="")int union_id) {

		return user_AnchorService.getAnchorUnionOneDivideProportion(union_id);
	}
}

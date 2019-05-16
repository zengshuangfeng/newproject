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

import com.meisui.manage.service.AttentionService;
import com.meisui.manage.service.Top_GuardService;
import com.meisui.manage.service.UserService;
import com.meisui.manage.service.User_AdminService;
import com.meisui.manage.service.User_AnchorService;
import com.meisui.manage.service.User_ForbidService;
import com.meisui.manage.service.WithdrawService;

/**
 * <p>文件名称：User_AnchorController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月13日 下午4:12:08
 */
@RequestMapping("/useranchor")
@Controller
public class User_AnchorController {
	@Autowired
	private User_AnchorService user_AnchorService;
	@Autowired
	private User_AdminService user_AdminService;
	@Autowired
	private Top_GuardService top_GuardService;
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private User_ForbidService user_ForbidService;
	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private UserService userService;
	/**
	 * 
	 * <p>功能描述：主播列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午4:11:32</p>
	 *
	 * @param uid 用户id
	 * @param nickname 昵称
	 * @param anchor_state 主播状态0休息1直播2禁播
	 * @param union_id 公会id
	 * @param is_trial 是否是试播 1是 0否
	 * @param last_anchor_time 最后直播时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUserAnchorList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return user_AnchorService.getUserAnchorList(uid, f_uuid, nickname, operate_centerid, page, model);
	}
	/**
	 * 
	 * <p>功能描述：删除主播</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午5:39:12</p>
	 *
	 * @param uid 主播uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "uid", required = true, defaultValue="0")long uid,
			HttpServletRequest request) {	
		return user_AnchorService.deleteUserAnchor(uid, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：更新主播直播状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午6:18:42</p>
	 *
	 * @param uid 主播uid
	 * @param state 主播状态0休息1直播2禁播
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatestate", method = RequestMethod.POST)
	public int updateState(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "uid", required = true, defaultValue="") String uid,
			@RequestParam(value = "anchor_type", required = true, defaultValue="0") int anchor_type,
			HttpServletRequest request) {	
		return user_AnchorService.updateUserAnchorState(uid, anchor_type, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：获取主播分成占比</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 上午9:15:37</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getdivide", method = RequestMethod.GET)
	public int getUserAnchorDivide(@RequestParam(value = "uid", required = true, defaultValue="0")long uid) {	
		return user_AnchorService.getUserAnchorDivide(uid);
	}
	/**
	 * 
	 * <p>功能描述：更新主播分成占比</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 上午9:00:37</p>
	 *
	 * @param uid 主播uid
	 * @param divide_proportion 分成占比
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatedivide", method = RequestMethod.POST)
	public int updateUserAnchorDivide(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "divide_proportion", required = true, defaultValue="0") int divide_proportion,
			HttpServletRequest request) {	
		return user_AnchorService.updateUserAnchorDivide(uid, divide_proportion, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/updateonedivide", method = RequestMethod.POST)
	public int updateUserAnchorOneDivide(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "one_divide_proportion", required = true, defaultValue="0") int one_divide_proportion,
			HttpServletRequest request) {	
		return user_AnchorService.updateUserAnchorOneDivide(uid, one_divide_proportion, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：获取主播提现方式</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 上午9:22:56</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getwithdraw", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUserAnchorWithdraw(@RequestParam(value = "uid", required = true, defaultValue="0") long uid) {	
		return user_AnchorService.getUserAnchorWithdraw(uid);
	}
	/**
	 * 
	 * <p>功能描述：保存主播提现方式</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午1:49:04</p>
	 *
	 * @param uid 主播uid
	 * @param withdraw_type 0支付宝
	 * @param withdraw_pass 提现账号
	 * @param withdraw_name 账号姓名
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savewithdrawtype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveUserAnchorWithdrawType(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "withdraw_type", required = false, defaultValue="0") int withdraw_type,
			@RequestParam(value = "withdraw_pass", required = false, defaultValue="") String withdraw_pass,
			@RequestParam(value = "withdraw_name", required = false, defaultValue="") String withdraw_name,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_AnchorService.saveUserAnchorWithdrawType(uid, withdraw_type, withdraw_pass, withdraw_name, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：主播信息页</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午3:42:31</p>
	 *
	 * @param uid 主播uid
	 * @param model
	 */
	@RequestMapping(value = "/getinfo", method = RequestMethod.GET)
	public String getUserAnchorInfo(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "apply_id", required = true, defaultValue="0")int apply_id,
			Model model) {	
		return user_AnchorService.getUserAnchorInfo(uid, apply_id, "useranchor", model);
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
	 * @param birthday_pic 生日卡片图
	 * @param is_vip 是否是VIP 1是0否
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

		return user_AnchorService.saveUserAnchor(id, uid, anchor_cover, remark, is_trial, type, youxi, live,is_vip,w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：获取主播昵称</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月14日 下午4:12:52</p>
	 *
	 * @param uid 主播id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getname", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUserName(@RequestParam(value = "uid", required = true, defaultValue="0") long uid) {	
		return user_AnchorService.getUserName(uid);
	}
	/**
	 * 
	 * <p>功能描述：主播时长列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月13日 下午4:11:32</p>
	 *
	 * @param uid 用户id
	 * @param name 昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/timelist", method = RequestMethod.GET)
	public String getUserAnchorTimeList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam(value = "end_date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return user_AnchorService.getAnchorTimeList(uid, date, end_date, page, "useranchor", model);
	}
	/**
	 * 
	 * <p>功能描述：主播当月时长统计</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 上午11:51:36</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/gettimetotal", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAnchorMonthTimeTotal(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam(value = "end_date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date) {		
		return user_AnchorService.getAnchorMonthTimeTotal(uid, date, end_date);
	}
	/**
	 * 
	 * <p>功能描述：门票房统计列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年09月28日 下午1:45:01</p>
	 *
	 * @param nickname 主播昵称
	 * @param uid 主播uid
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */		
	@RequestMapping(value = "/admissionticketlist", method = RequestMethod.GET)
	public String getAnchorsAdmissionTicketList(@RequestParam(value="nickname",required=false)String nickname,
			@RequestParam(value="uid",required=false)Long uid,
			@RequestParam(value="start_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_date,
			@RequestParam(value="end_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_date,
			@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model){
		return user_AnchorService.getAnchorsAdmissionTicketList(nickname, uid, start_date, end_date, page, model);
	}
	/**
	 * 
	 * <p>功能描述：门票房修改记录列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年09月30日 上午09:21:01</p>
	 *
	 * @param nickname 主播昵称
	 * @param uid 主播uid
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */			
	@RequestMapping(value = "/ticketupdaterecordlist", method = RequestMethod.GET)
	public String getAdmissionTicketRecordList(@RequestParam(value="uid",required=false,defaultValue="0")Long uid,
			@RequestParam(value="nickname",required=false,defaultValue="")String nickname,
			@RequestParam(value="start_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_date,
			@RequestParam(value="end_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_date,
			@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model
			){
		 return user_AnchorService.getAdmissionTicketRecordList(uid, nickname, start_date, end_date,page,model);
	}
	/**
	 * 
	 * <p>功能描述：设置门票上下限</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年09月30日 下午5:03:16</p>
	 *
	 * @param fee_count_min 门票下限
	 * @param fee_count_max 门票上限
	 * @return
	 */			
	@ResponseBody
	@RequestMapping(value = "/setfeecountlimit", method = RequestMethod.POST)	
	public int setAdmissionTicketFeeCountLimit(@RequestParam(value="fee_count_min")int fee_count_min,
			@RequestParam(value="fee_count_max")int fee_count_max,
			@CookieValue(value="w_name",required=false,defaultValue="")String w_name,
			HttpServletRequest request){
		return user_AnchorService.setAdmissionTicketFeeCountLimit(fee_count_min, fee_count_max,w_name,request);
	}
	
	/**
	 * 
	 * <p>功能描述：关注列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 上午10:10:07</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间号
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/attention/list", method = RequestMethod.GET)
	public String getAttentionList(@RequestParam(value = "attention_uid", required = false,defaultValue="0")long attention_uid,  
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,  
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return attentionService.getAttentionList(attention_uid, f_uuid, page, model);
	}
	/**
	 * 
	 * <p>功能描述：房管列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 上午10:22:05</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/useradmin/list", method = RequestMethod.GET)
	public String getUserAdminList(@RequestParam(value = "attention_uid", required = false,defaultValue="0")long attention_uid,  
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,  
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_AdminService.getUserAdminList(attention_uid, f_uuid, page, model);
	}
	/**
	 * 
	 * <p>功能描述：守护列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 上午10:14:32</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间号
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/topguard/list", method = RequestMethod.GET)
	public String getTopGuardList(@RequestParam(value = "attention_uid", required = false,defaultValue="0")long attention_uid,  
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,  
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return top_GuardService.getTopGuardList(attention_uid, f_uuid, page, model);
	}
	/**
	 * 
	 * <p>功能描述：禁言列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午6:34:20</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userforbid/list", method = RequestMethod.GET)
	public String getUserForbidList(@RequestParam(value = "attention_uid", required = false,defaultValue="0")long attention_uid,  
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,  
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_ForbidService.getUserForbidList(attention_uid, f_uuid, page, model);
	}
	/**
	 * 
	 * <p>功能描述：主播提现流水列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 下午3:27:24</p>
	 *
	 * @param uid 主播uid
	 * @param date 日期
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/withdraw/waterlist", method = RequestMethod.GET)
	public String getWithdrawWaterList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,   
			@RequestParam(value = "date", required = false,defaultValue="")String date,   
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return withdrawService.getWithdrawList(uid, date, page, model);
	}
	/**
	 * 
	 * <p>功能描述：主播魅力值流水列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 下午3:27:24</p>
	 *
	 * @param uid 主播uid
	 * @param date 日期
	 * @param date 日期
	 * @param type 0用户赠送  4后台奖励 5后台追回
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/profitlist", method = RequestMethod.GET)
	public String getSpendingList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,   
			@RequestParam(value = "type", required = false,defaultValue="-1")int type, 
			@RequestParam(value = "date", required = false,defaultValue="")String date,   
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_AnchorService.getProfitList(uid, type, date, page, model);
	}
	/**
	 * 
	 * <p>功能描述：用户奖励收益</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午3:40:40</p>
	 *
	 * @param uid 用户id
	 * @param virtual 奖励收益数
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savereward", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveReward(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "virtual", required = false, defaultValue="0") int virtual,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_AnchorService.saveReward(uid, virtual, remark, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：用户追回收益</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午3:40:40</p>
	 *
	 * @param uid 用户id
	 * @param virtual 奖励收益数
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savereturn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveReturn(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "virtual", required = false, defaultValue="0") int virtual,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_AnchorService.saveReturn(uid, virtual, remark, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：获取主播提现金额</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午4:02:49</p>
	 *
	 * @param uid 用户id
	 * @param withdraw_virtual 提现魅力值
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getwithdrawrmb", method = RequestMethod.GET)
	public double getWithdrawRmb(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "withdraw_virtual", required = false, defaultValue="0") int withdraw_virtual){
		return user_AnchorService.getWithdrawRmb(uid, withdraw_virtual);
	}
	/**
	 * 
	 * <p>功能描述：奖励主播钻石</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月3日 下午5:28:08</p>
	 *
	 * @param uid 主播用户uid
	 * @param add 增加钻石数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savebalancevirsualadd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveBalanceVirsualAdd(@RequestParam(value = "uid", required = true, defaultValue="0") long uid,
			@RequestParam(value = "add", required = false, defaultValue="0") int add,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return user_AnchorService.saveBalanceVirsualAdd(uid, add, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public int closeAnchor(@RequestParam(value = "id", required = false,defaultValue="")String f_uuid) {

		return user_AnchorService.closeAnchor(f_uuid);
	}
	/**
	 * 
	 * <p>功能描述：获取工会分成比例</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:19:46</p>
	 *
	 * @param id 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getanchoruniondivideproportion", method = RequestMethod.GET)
	public int getAnchorUnionDivideProportion(@RequestParam(value = "union_id", required = false,defaultValue="")int union_id) {

		return user_AnchorService.getAnchorUnionDivideProportion(union_id);
	}
	/**
	 * 
	 * <p>功能描述：奖励用户钥匙数</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午4:33:35</p>
	 *
	 * @param id 用户uid
	 * @param add 增加数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/rewardkeycount", method = RequestMethod.POST)
	public int rewardUserKeyCount(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "add", required = false,defaultValue="0")int add,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_AnchorService.rewardUserKeyCount(id, add, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/savediamonds", method = RequestMethod.POST)
	public int savediamonds(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "diamondsremark", required = false,defaultValue="")String diamondsremark,
			@RequestParam(value = "diamondsa", required = false,defaultValue="")String diamondsa,
			@RequestParam(value = "diamondsb", required = false,defaultValue="")String diamondsb,
			@RequestParam(value = "diamondscount", required = false,defaultValue="0")int balance_virtual,
			@RequestParam(value = "money", required = false,defaultValue="0")int money,
			@RequestParam(value = "remark", required = false,defaultValue="")String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updatebalance_virtual(id, balance_virtual, money, diamondsremark, diamondsa,diamondsb, w_name, request);
	}

	/***
	 * 主播魅力值变更日志列表
	 * @param uid 主播uid
	 * @param page 页码
	 * @param model
	 */
	@RequestMapping(value = "/virtualchangelist", method = RequestMethod.GET)
	public String getAnchorVirtualChangeRecordList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,    
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return user_AnchorService.getAnchorVirtualChangeRecordList(uid, page, model);
	}
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid,
			HttpServletResponse response) {	
		return user_AnchorService.exportExcel(uid,f_uuid,nickname,operate_centerid,response);
	}
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(HttpServletRequest request, HttpServletResponse response) {	
		return user_AnchorService.upload(request, response);
	}
	/*@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear() {	
		user_AnchorService.clear();
		 return "success";
	}*/
	@RequestMapping(value = "/exportexcel2", method = RequestMethod.GET)
	public String exportExcel2(
			HttpServletResponse response) {	
		return user_AnchorService.exportExcel2( response);
	}
	@RequestMapping(value = "/admissionticket/exportexcel", method = RequestMethod.GET)
	public String exportAdmissionTicketExcel(
			@RequestParam(value="nickname",required=false)String nickname,
			@RequestParam(value="uid",required=false)Long uid,
			@RequestParam(value="start_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_date,
			@RequestParam(value="end_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_date,						
			HttpServletResponse response){
		return user_AnchorService.exportAdmissionTicketExcel(nickname,uid,start_date,end_date,response);
	}
	@RequestMapping(value = "/admissionticketrecord/exportexcel", method = RequestMethod.GET)	
    public String exportAdmissionTicketRecordExcel(
			@RequestParam(value="nickname",required=false)String nickname,
			@RequestParam(value="uid",required=false)Long uid,
			@RequestParam(value="start_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_date,
			@RequestParam(value="end_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_date,						
			HttpServletResponse response){
		return user_AnchorService.exportAdmissionTicketRecordExcel(nickname, uid, start_date, end_date, response);  	
    }

	@ResponseBody
	@RequestMapping(value = "/saveonedisturb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveOneDisturb(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "one_disturb", required = false,defaultValue="0")int one_disturb,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {
		return user_AnchorService.saveOneDisturb(uid, one_disturb, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/getanchoruniononedivideproportion", method = RequestMethod.GET)
	public int getAnchorUnionOneDivideProportion(@RequestParam(value = "union_id", required = false,defaultValue="")int union_id) {

		return user_AnchorService.getAnchorUnionOneDivideProportion(union_id);
	}
	@RequestMapping(value = "/onetimelist", method = RequestMethod.GET)
	public String getUserAnchorOneTimeList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam(value = "end_date", required = false,defaultValue="") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return user_AnchorService.getAnchorOneTimeList(uid, date, end_date, page, "useranchor", model);
	}
	@RequestMapping(value = "/adminlist", method = RequestMethod.GET)
	public String getUserAnchorAdminList(@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			Model model) {

		return user_AnchorService.getUserAnchorAdminList(f_uuid, model);
	}
	@ResponseBody
	@RequestMapping(value = "/deleteadmin", method = RequestMethod.POST)
	public int deleteAdmin(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid, HttpServletRequest request) {

		return user_AnchorService.deleteAdmin(id, uid, request);
	}
	@ResponseBody
	@RequestMapping(value = "/deletecontrol", method = RequestMethod.POST)
	public int deleteControl(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid, HttpServletRequest request) {

		return user_AnchorService.deleteControl(id, uid, request);
	}
}

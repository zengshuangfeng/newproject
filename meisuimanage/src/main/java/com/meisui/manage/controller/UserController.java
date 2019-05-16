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

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.service.Gift_BoxService;
import com.meisui.manage.service.UserService;
import com.meisui.manage.service.User_ForbidService;

/**
 * <p>文件名称：UserController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月3日 下午5:29:05
 */
@RequestMapping("/userinfo")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private User_ForbidService user_ForbidService;
	@Autowired
	private Gift_BoxService gift_boxService;
	/**
	 * 
	 * <p>功能描述：用户列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午5:26:55</p>
	 *
	 * @param uid 用户id
	 * @param nickname 昵称
	 * @param remark 备注
	 * @param s_time 注册开始时间
	 * @param e_time 注册结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUserList(
			@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "remark", required = false,defaultValue="") String remark,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "level", required = false,defaultValue="") String level,
			@RequestParam(value = "sort", required = false,defaultValue="0") int sort,
			@RequestParam(value = "tel", required = false,defaultValue="") String tel,			
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return userService.getUserList(uid,f_uuid, nickname,remark, s_time, e_time,level,sort, tel, page, model);
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String getUserDetail(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "type", required = false,defaultValue="0") int type,
			@RequestParam(value = "select", required = false,defaultValue="0") int select,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return userService.getUserDetail(id, page, type, select, model);
	}
	
	@RequestMapping(value = "/totalFLowingList/exportexcel", method = RequestMethod.GET)
	public String exportExcel(
			@RequestParam(value="uid",required = false,defaultValue = "0")Long uid,
			HttpServletResponse response) {	
		return userService.exportExcel(uid,response);
	}
	/**
	 * 
	 * <p>功能描述：修改用户备注</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午8:52:32</p>
	 *
	 * @param id 用户id
	 * @param remark 备注
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveremark", method = RequestMethod.POST)
	public int saveUserRemark(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "remark", required = false,defaultValue="")String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.saveUserRemak(id, remark, w_name, request);
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

		return userService.updatebalance_virtual(id, balance_virtual, money, diamondsremark, diamondsa,diamondsb, w_name,request);
	}

	@RequestMapping(value = "/editdiamonds", method = RequestMethod.GET)
	public String editdiamonds(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			Model model) {

		return userService.editdiamonds(id, model);
	}

	/**
	 * 
	 * <p>功能描述：修改用户是否为测试账号</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月23日 下午5:42:39</p>
	 *
	 * @param id 用户id
	 * @param istest 是否为测试账号 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateistest", method = RequestMethod.POST)
	public int updateUserIsTest(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "istest", required = false,defaultValue="0")int istest,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updateUserIsTest(id, istest, w_name, request);
	}
	
	/**
	 * 
	 * <p>功能描述：修改用户头像锁定</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param head_lock 是否锁定 1是 0否
	 * @param w_name
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateheadlock", method = RequestMethod.POST)
	public int updateUserHeadLock(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "head_lock", required = false,defaultValue="0")int head_lock,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updateUserHeadLock(id, head_lock, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：修改用户昵称锁定</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param nickname_lock 是否锁定 1是 0否
	 * @param w_name
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatenicknamelock", method = RequestMethod.POST)
	public int updateUserNicknameLock(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "nickname_lock", required = false,defaultValue="0")int nickname_lock,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updateUserNicknameLock(id, nickname_lock, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：修改用户VIP</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年9月20日 下午2:11:12</p>
	 *
	 * @param id 用户uid
	 * @param vip_days 开通VIP天数
	 * @return
	 */		
	@ResponseBody
	@RequestMapping(value = "/updateuservip", method = RequestMethod.POST)	
	public int updateUserVIP(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			  @RequestParam(value = "vip_days", required = false,defaultValue="0")int vip_days,
				@CookieValue(value="w_name", defaultValue="")String w_name,
				HttpServletRequest request			  
			){
		return userService.updateUserVIP(id, vip_days,w_name,request);
	}
	
	/**
	 * 
	 * <p>功能描述：取消用户VIP</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年9月21日 下午16:11:12</p>
	 *
	 * @param id 用户uid
	 * @return
	 */		
	@ResponseBody
	@RequestMapping(value = "/canceluservip", method = RequestMethod.POST)		
	public int cancelUserVIP(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request	){
		
		return userService.cancelUserVIP(id,w_name,request);
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

		return userService.rewardUserKeyCount(id, add, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：封号/解封用户</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 用户id
	 * @param is_blocked 是否封号 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateisblocked", method = RequestMethod.POST)
	public int updateIsBlocked(@RequestParam(value = "id", required = false,defaultValue="0")long id,
			@RequestParam(value = "is_blocked", required = false,defaultValue="0")int is_blocked,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updateUserIsBlocked(id, is_blocked, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：更新用户视频状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 视频id
	 * @param status 状态 0未审核 1通过 2屏蔽
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatevideostatus", method = RequestMethod.POST)
	public int updateUserVideoStatus(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "status", required = false,defaultValue="0")int status,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updateUserVideoStatus(id, status, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：推荐用户视频</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 视频id
	 * @param is_recommend 是否推荐1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatevideoisrecommend", method = RequestMethod.POST)
	public int updateUserVideoIsRecommend(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "is_recommend", required = false,defaultValue="0")int is_recommend,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return userService.updateUserVideoIsRecommend(id, is_recommend, w_name, request);
	}
	/**
	 * 禁言/取消禁言
	 * @param uid
	 * @param is_forbid
	 * @param hour
	 * @param w_name
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/forbid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveUserForbid(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "is_forbid", required = false,defaultValue="0")int is_forbid,
			@RequestParam(value = "hour", required = false,defaultValue="0")int hour,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		if(is_forbid==1)
			return user_ForbidService.saveUserForbid(uid, 0, hour, w_name, request);
		else
			return user_ForbidService.cancelUserForbid(uid, w_name, request);
	}
	/**
	 * 解除短信限制
	 * @param uid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelsmslimit", method = RequestMethod.POST)
	public int cancelSmsLimit(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			HttpServletRequest request)
	{
		return userService.cancelSmsLimit(uid, request);
	}
	/**
	 * 注册登录查询
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 分页页码
	 * @return
	 */	
	@RequestMapping(value = "/statisticslist", method = RequestMethod.GET)	
	public String getRegisterLoginStatisticsList(@RequestParam(value="start_time",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_time,
			@RequestParam(value="end_time",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_time,
			@RequestParam(value = "channel_platform",required = false,defaultValue = "") String channel_platform,
			@RequestParam(value="page",required=false,defaultValue="1") int page,
			Model model
			){
		return userService.getRegisterLoginStatisticsList(start_time, end_time, channel_platform,page, model);
	}
	
	@RequestMapping(value = "/statisticslist/exportexcel", method = RequestMethod.GET)
	public String exportExcel2(@RequestParam(value="start_time",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_time,
			@RequestParam(value="end_time",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_time,
			@RequestParam(value = "channel_platform",required = false,defaultValue = "") String channel_platform,
			HttpServletResponse response) {	
		return userService.exportExcel2(start_time, end_time, channel_platform, response);
	}
	@ResponseBody
	@RequestMapping(value = "/openuser", method = RequestMethod.POST)
	public int openUser(@RequestParam(value = "open_uid", required = false,defaultValue="0")String open_uid,
			HttpServletRequest request)
	{
		RedisUtil.deleteJedis(9, "nofilter", open_uid);
		return 1;
	}
	@ResponseBody
	@RequestMapping(value = "/insertvalid", method = RequestMethod.POST)
	public String insertValid(@RequestParam(value = "phone", required = false,defaultValue="")String phone)
	{
		return userService.insertValid(phone);
	}
	@RequestMapping(value = "/piclist", method = RequestMethod.GET)	
	public String getPicList(@RequestParam(value="uid",required=false)long uid, Model model){
		return userService.getPicList(uid, model);
	}
	@ResponseBody
	@RequestMapping(value = "/updatepic", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String updatePic(@RequestParam(value="uid",required=false)long uid,
			@RequestParam(value = "head", required = false,defaultValue="")String head,
			
			HttpServletRequest request)
	{
		return userService.updatePic(uid, head, request);
	}
	
	
	@RequestMapping(value = "/giftbox/delete", method = RequestMethod.POST)
	public String giftboxdelete(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "id", required = true, defaultValue="0")int id,
			@RequestParam(value = "count", required = true, defaultValue="0")int count,
			@RequestParam(value = "uid", required = true, defaultValue="0")int uid,
			@RequestParam(value = "type", required = true, defaultValue="0")int type,
			HttpServletRequest request) {
		
		return gift_boxService.deleteGiftBox(id, count,uid,type,w_name, request);
	}

}

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

import com.meisui.manage.qiniu.api.Stream.SaveOptions;
import com.meisui.manage.service.SquareService;

/**
 * <p>文件名称：SquareController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月18日 上午11:32:53
 */
@RequestMapping("/")
@Controller
public class SquareController {
	@Autowired
	private SquareService squareService;
	/**
	 * 
	 * <p>功能描述：直播间管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 上午11:02:26</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "square/list", method = RequestMethod.GET)
	public String getSquareList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "type", required = false,defaultValue="-1") int type, 
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return squareService.getSquareList(uid, nickname, type, page, model);
	}
	/**
	 * 
	 * <p>功能描述：直播间修改虚拟热度</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 上午11:31:18</p>
	 *
	 * @param id 广场直播间表id
	 * @param virtual_add_hots 增加虚拟热度值
	 * @param virtual_time 虚拟热度持续时间至
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "square/updatevirtualhots", method = RequestMethod.POST)
	public int updateSquareVirtualHots(@RequestParam(value = "id", required = false,defaultValue="0")int id,
			@RequestParam(value = "virtual_add_hots", required = false,defaultValue="0")int virtual_add_hots,
			@RequestParam(value = "virtual_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date virtual_time,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return squareService.updateSquareVirtualHots(id, virtual_add_hots, virtual_time, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "square/close", method = RequestMethod.POST)
	public int closeAnchor(@RequestParam(value = "id", required = false,defaultValue="")String f_uuid,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return squareService.closeAnchor(f_uuid, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "square2/close", method = RequestMethod.POST)
	public int closeAnchor2(@RequestParam(value = "id", required = false,defaultValue="")String f_uuid,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return squareService.closeAnchor2(f_uuid, w_name, request);
	}
	@RequestMapping(value = "square2/list", method = RequestMethod.GET)
	public String getSquareList2(@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, Model model) {	
		return squareService.getSquareList2(f_uuid, page, model);
	}
	@RequestMapping(value="square2/getwatchcount", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getWatchCount(@RequestParam(value="f_uuid_arr[]", required=false, defaultValue="[]")Long[] f_uuid_arr){
		return squareService.getWatchCount(f_uuid_arr);
	}
	@RequestMapping(value="square2/getwatchcountfromframing", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getWatchCountFromFraming(@RequestParam(value="f_uuid_arr[]", required=false, defaultValue="[]")Long[] f_uuid_arr){
		return squareService.getWatchCountFromFraming(f_uuid_arr);
	}
	@RequestMapping(value="square2/getwaringcount", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getWaringCount(@RequestParam(value="uid", required=false, defaultValue="0")long uid){

		return squareService.getWaringCount(uid);
	}
	@RequestMapping(value="square2/getsnapshot", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getSnapshotByStreamKey(@RequestParam(value="streamKey", required=false, defaultValue="")String streamKey){
		return squareService.getSnapshotByStreamKey(streamKey);
	}
	@RequestMapping(value={"square2/savewaring","square2/saveclose"}, method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String save(@RequestParam(value="id", required=false, defaultValue="0")int id,
			@RequestParam(value="type", required=false, defaultValue="0")int type,
			@RequestParam(value="uid", required=false, defaultValue="0")long uid,
			@RequestParam(value="f_uuid", required=false, defaultValue="0")long f_uuid,
			@RequestParam(value="anchor_name", required=false, defaultValue="")String anchor_name,
			@RequestParam(value="reason", required=false, defaultValue="")String reason,
			@RequestParam(value="pic", required=false, defaultValue="")String pic,
			@CookieValue(value="w_name", defaultValue="")String w_name, HttpServletRequest request){
		return squareService.save(id, type, uid, f_uuid, anchor_name, reason, pic, w_name, request);
	}
	@RequestMapping(value={"square2/savevip"}, method = RequestMethod.POST)
	@ResponseBody	
	public int saveVIPSettings(
			@RequestParam(value="uid", required=false, defaultValue="0")long uid,
			@RequestParam(value="is_vip", required=false, defaultValue="0")int is_vip,
			@CookieValue(value="w_name",required=false,defaultValue="")String w_name,
			HttpServletRequest request
			){
		return squareService.updateSquareVIP(uid, is_vip,w_name,request);
	}
	
	@RequestMapping(value="square2/getjson", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getSquareJson(@RequestParam(value = "page", required = false,defaultValue="1") int page){
		return squareService.getSquareJson(page);
	}
	@RequestMapping(value = "waring/list", method = RequestMethod.GET)
	public String getWaringList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "w_name", required = false,defaultValue="")String w_name,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return squareService.getWaringOrCloseList(0, uid, f_uuid, w_name, page, model);
	}
	@RequestMapping(value = "close/list", method = RequestMethod.GET)
	public String getCloseList(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")long f_uuid,
			@RequestParam(value = "w_name", required = false,defaultValue="")String w_name,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return squareService.getWaringOrCloseList(1, uid, f_uuid, w_name, page, model);
	}
	@RequestMapping(value = "comment/list", method = RequestMethod.GET)
	public String getCommentList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			@RequestParam(value = "uid", required = false) Long uid, 
			@RequestParam(value = "f_uuid", required = false) Long f_uuid, 
			@RequestParam(value = "date",required = false)@DateTimeFormat(pattern="yyyy-MM-dd")Date date,
			@RequestParam(value = "comment", required = false,defaultValue="") String comment, 
			@RequestParam(value = "second", required = false,defaultValue="0") int second, 
			Model model) {	
		return squareService.getCommentList(uid,f_uuid,comment,date,second, page, model);
	}
	/**
	 * 
	 * <p>功能描述：添加用户禁言</p>
	 *
	 * @param uid 用户uid
	 * @param hour 小时数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "square2/saveforbid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveUserForbid(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@RequestParam(value = "hour", required = false,defaultValue="0")int hour,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return squareService.saveUserForbid(uid, hour, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：取消用户禁言</p>
	 *
	 * @param uid 用户uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "square2/cancelforbid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String cancelUserForbid(@RequestParam(value = "uid", required = false,defaultValue="0")long uid,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return squareService.cancelUserForbid(uid, w_name, request);
	}
	@RequestMapping(value = "framing/list", method = RequestMethod.GET)
	public String getFramingList(@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return squareService.getFramingList(f_uuid, page, model);
	}
	@RequestMapping(value = "anchoronline/list", method = RequestMethod.GET)
	public String getOnlineList(@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return squareService.getOnlineNum(page, model);
	}
	@ResponseBody
	@RequestMapping(value = "square2/charge", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public int charge(@RequestParam(value = "uid", required = false,defaultValue="0")Long uid,
			@RequestParam(value="f_uuid",required = false,defaultValue="0")Long f_uuid,
			@RequestParam(value="nickname",required = false)String nickname,
			@RequestParam(value="times",required=false,defaultValue="0")Long times,
			@RequestParam(value="fee_count",required=false,defaultValue="0")int fee_count,
			@CookieValue(value="w_name",required=false,defaultValue="")String w_name,
			HttpServletRequest request){
		return squareService.charge(uid,f_uuid,nickname,times,w_name,fee_count,request);
	}
}

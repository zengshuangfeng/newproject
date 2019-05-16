package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Index_RecommendService;



@Controller
public class Index_RecommendController {
	@Autowired
	private Index_RecommendService index_RecommendService;
	
	/***
	 * 
	 * <p>功能描述：推荐位banner列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param style 0banner
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param url 跳转路由
	 * @param status 0:未开始 1:进行中 2:已结束
	 * @param platform 00:全部   10:IOS 20:安卓
	 * @param page
	 * @param s
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recommendbanner/list", method = RequestMethod.GET)
	public String getBannerRecommendList(@RequestParam(value = "style", required = false,defaultValue="0") int style,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "jumpstyle", required = false,defaultValue="") String jumpstyle,
			@RequestParam(value = "status", required = false,defaultValue="-1") int status,
			@RequestParam(value = "platform", required = false,defaultValue="") String platform,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			@RequestParam(value = "s", required = false,defaultValue="") String s,
			Model model) {
		
			return index_RecommendService.getBannerRecommendList(style, s_time, e_time, jumpstyle, status, platform, page, s,  model);
	}
	/***
	 * 
	 * <p>功能描述：推荐位banner添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recommendbanner/add", method = RequestMethod.GET)
	public String addBannerRecommend(Model model) {
		return index_RecommendService.addBannerRecommend(model);
	}
	/***
	 * 
	 * <p>功能描述：推荐位banner编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:53:56</p>
	 *
	 * @param id 首推id
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recommendbanner/edit", method = RequestMethod.GET)
	public String editBannerRecommend(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			Model model) {		
		return index_RecommendService.editBannerRecommend(id, model);
	}
	/***
	 * 
	 * <p>功能描述：开屏列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param url 跳转路由
	 * @param status 0:未开始 1:进行中 2:已结束
	 * @param platform 00:全部   10:IOS 20:安卓
	 * @param page
	 * @param s
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recommendopen/list", method = RequestMethod.GET)
	public String getOpenRecommendList(@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "jumpstyle", required = false,defaultValue="") String jumpstyle,
			@RequestParam(value = "status", required = false,defaultValue="-1") int status,
			@RequestParam(value = "platform", required = false,defaultValue="") String platform,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			@RequestParam(value = "s", required = false,defaultValue="") String s,
			Model model) {
		
			return index_RecommendService.getOpenList(s_time, e_time, jumpstyle, status, platform, page, s,  model);
	}
	/***
	 * 
	 * <p>功能描述：开屏添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recommendopen/add", method = RequestMethod.GET)
	public String addOpenRecommend(Model model) {
		return index_RecommendService.addOpenRecommend(model);
	}
	/***
	 * 
	 * <p>功能描述：开屏编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:53:56</p>
	 *
	 * @param id 开屏id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recommendopen/edit", method = RequestMethod.GET)
	public String editOpenRecommend(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			Model model) {		
		return index_RecommendService.editOpenRecommend(id, model);
	}
	/***
	 * 
	 * <p>功能描述：保存首推信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 下午3:13:29</p>
	 *
	 * @param id
	 * @param title 标题
	 * @param title_en 标题英文
	 * @param pic 图片
	 * @param stayend 停留几秒
	 * @param position 位置
	 * @param jumpstyle 跳转路由
	 * @param href 跳转内容
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param platforms 平台
	 * @param version 最低版本
	 * @param stayend 停留秒数
	 * @param mobile_model 机型
	 * @param is_share 是否可分享 1是 0否
	 * @param share_title 分享标题
	 * @param share_content 分享内容
	 * @param share_weixin_pic 微信分享图片
	 * @param share_weibo_pic 微博分享图片
	 * @param app 应用类型 0通用包 1配配
	 * @param anchor_uid 生日主播uid
	 * @param w_name 编辑人员
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/recommendbanner/save","/recommendopen/save"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveRecommend(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "title", required = false, defaultValue="") String title,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "style", required = false, defaultValue="0") int style,
			@RequestParam(value = "position", required = false, defaultValue="0") int position,
			@RequestParam(value = "jumpstyle", required = false, defaultValue="")String jumpstyle,
			@RequestParam(value = "href", required = false, defaultValue="") String href,
			@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date e_time,
			@RequestParam(value = "platform", required = false, defaultValue="0") String platforms,
			@RequestParam(value = "version", required = false, defaultValue="") String version,
			@RequestParam(value = "stayend", required = false, defaultValue="0") int stayend,
			@RequestParam(value = "mobile_model", required = false, defaultValue="") String mobile_model,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "is_share", required = false, defaultValue="0") int is_share,
			@RequestParam(value = "share_title", required = false, defaultValue="") String share_title,
			@RequestParam(value = "share_content", required = false, defaultValue="") String share_content,
			@RequestParam(value = "share_weixin_pic", required = false, defaultValue="") String share_weixin_pic,
			@RequestParam(value = "share_weibo_pic", required = false, defaultValue="") String share_weibo_pic,
			@RequestParam(value = "app", required = false, defaultValue="0") int app,
			@RequestParam(value = "anchor_uid", required = false, defaultValue="0") long anchor_uid,
			@RequestParam(value = "video", required = false, defaultValue="") String video,
			@RequestParam(value = "channel", required = false, defaultValue="") String channel,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return index_RecommendService.saveRecommend(id, title, pic, style, position, jumpstyle, href, s_time, e_time, platforms, version, stayend, mobile_model, is_online, is_share, share_title, share_content, share_weixin_pic, share_weibo_pic, app, anchor_uid, video, channel, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：推荐位删除</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:19:55</p>
	 *
	 * @param w_name 编辑人员
	 * @param name
	 * @param id 推荐位id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend{name}/delete", method = RequestMethod.POST)
	public int delete(@CookieValue(value = "w_name", required = true, defaultValue="") String w_name,
			@RequestParam(value = "id", required = true, defaultValue="-1") int id,
			@PathVariable String name,
			HttpServletRequest request) {	
		return index_RecommendService.delete(w_name, name, id, request);
	}
	/**
	 * 
	 * <p>功能描述：保存推荐位排序</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:18:47</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 推荐位id
	 * @param position 排序
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/recommend/updateposition",method=RequestMethod.POST)
	public int updateRecommendPosition(@CookieValue(value="w_name",required=true,defaultValue="")String w_name,
			@RequestParam(value="id",required = false, defaultValue="0")int id,
			@RequestParam(value="position", required = false, defaultValue="0")int position,
			HttpServletRequest request){
		return index_RecommendService.updateRecommendPosition(w_name, id, position, request);
	}
}

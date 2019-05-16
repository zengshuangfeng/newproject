package com.meisui.manage.controller;

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

import com.meisui.manage.service.CommunityService;

@RequestMapping("/community")
@Controller
public class CommunityController {
	@Autowired
	private CommunityService communityservice;

	/**
	 * 热门主播
	 */
	@RequestMapping(value = "/popularanchor", method = RequestMethod.GET)
	public String getPopularAnchor(@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getPopularAnchor(uid, nickname, start_time, end_time, page, model);
	}

	/**
	 * 添加热门主播页面
	 */
	@RequestMapping(value = "/popularanchor/add", method = RequestMethod.GET)
	public String PopularAnchorAdd(Model model) {
		return communityservice.addPopularAnchor(model);
	}

	/**
	 * 编辑热门主播页面
	 */
	@RequestMapping(value = "/popularanchor/edit", method = RequestMethod.GET)
	public String PopularAnchorEdit(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			Model model) {
		return communityservice.editPopularAnchor(id, model);
	}

	/**
	 * 热门主播添加和编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/popularanchor/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String savePopularAnchor(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "pic", required = false, defaultValue = "") String pic,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "sort", required = false, defaultValue = "0") int sort,
			@RequestParam(value = "is_online", required = false, defaultValue = "0") int is_online,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.savePopularAnchor(id, uid, title, pic, start_time, end_time, sort, is_online, w_name,
				request);
	}

	/**
	 * 热门主播删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/popularanchor/delete", method = RequestMethod.POST)
	public int PopularAnchordelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PopularAnchordelete(w_name, id, request);
	}

	/**
	 * 热门直播
	 */
	@RequestMapping(value = "/hotlive", method = RequestMethod.GET)
	public String getHotLive(@RequestParam(value = "f_uuid", required = false, defaultValue = "0") long f_uuid,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.gethotlive(f_uuid, start_time, end_time, page, model);
	}

	/**
	 * 热门直播删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/hotlive/delete", method = RequestMethod.POST)
	public int Hotlivedelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.Hotlivedelete(w_name, id, request);
	}

	/**
	 * 热门直播添加页面
	 */
	@RequestMapping(value = "/hotlive/add", method = RequestMethod.GET)
	public String HotliveAdd(Model model) {
		return communityservice.HotliveAdd(model);
	}

	/**
	 * 热门直播添加和编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/hotlive/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveHotLive(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "f_uuid", required = false, defaultValue = "0") long f_uuid,
			@RequestParam(value = "pic", required = false, defaultValue = "") String pic,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "sort", required = false, defaultValue = "0") int sort,
			@RequestParam(value = "is_online", required = false, defaultValue = "0") int is_online,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.saveHotLive(id, f_uuid, pic, start_time, end_time, sort, is_online, w_name, request);
	}

	/**
	 * 热门直播编辑页面
	 */
	@RequestMapping(value = "/hotlive/edit", method = RequestMethod.GET)
	public String HotLiveEdit(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
		return communityservice.HotLiveEdit(id, model);
	}

	/**
	 * 照片管理列表
	 */
	@RequestMapping(value = "/picmanager", method = RequestMethod.GET)
	public String getPicManager(@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getPicManager(uid, start_time, end_time, title, page, model);
	}

	/**
	 * 照片管理添加页面
	 */
	@RequestMapping(value = "/picmanager/add", method = RequestMethod.GET)
	public String PicManagerAdd(Model model) {
		return communityservice.PicManagerAdd(model);
	}

	/**
	 * 照片管理添加和编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String savePicManager(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "zan_count", required = false, defaultValue = "0") int zan_count,
			@RequestParam(value = "share_count", required = false, defaultValue = "0") int share_count,
			@RequestParam(value = "pic", required = false, defaultValue = "") String pic,
			@RequestParam(value = "is_online", required = false, defaultValue = "1") int is_online,
			@RequestParam(value = "tag_id", required = false, defaultValue = "0") int tag_id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.savePicManager(id, uid, title, zan_count, share_count, pic, is_online, tag_id,w_name, request);
	}

	/**
	 * 照片管理编辑页面
	 */
	@RequestMapping(value = "/picmanager/edit", method = RequestMethod.GET)
	public String PicManagerEdit(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			Model model) {
		return communityservice.PicManagerEdit(id, model);
	}

	@ResponseBody
	@RequestMapping("/delpic")
	public int deletePic(int id, HttpServletRequest request) {
		return communityservice.delPic(id, request);
	}

	/**
	 * 照片管理==》上线
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/postonline", method = RequestMethod.POST)
	public int PostOnline(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PostOnline(w_name, id, request);
	}

	/**
	 * 照片管理==》下线
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/postdownline", method = RequestMethod.POST)
	public int PostDownline(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PostDownline(w_name, id, request);
	}

	/**
	 * 照片管理删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/postdelete", method = RequestMethod.POST)
	public int PostDelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PostDelete(w_name, id, request);
	}

	/**
	 * 照片管理==评论
	 */
	@RequestMapping(value = "/picmanager/comment", method = RequestMethod.GET)
	public String getPostComment(@RequestParam(value = "post_id", required = false, defaultValue = "0") long post_id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getPostComment(post_id, uid, nickname, comment, start_time, end_time, page, model);
	}

	/**
	 * 照片管理==评论删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/commentdelete", method = RequestMethod.POST)
	public int CommentDelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.CommentDelete(w_name, id, request);
	}

	/**
	 * 照片管理==》评论置顶
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/topcomment", method = RequestMethod.POST)
	public int topComment(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "is_top", required = false, defaultValue = "0") int is_top,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.topComment(w_name, id, is_top, request);
	}

	/**
	 * 添加评论
	 */
	@ResponseBody
	@RequestMapping(value = "/picmanager/addcomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addPicmanagerComment(
			@RequestParam(value = "post_id", required = false, defaultValue = "0") int post_id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "zan_count", required = false, defaultValue = "0") int zan_count,
			@RequestParam(value = "is_top", required = false, defaultValue = "0") int is_top,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.addPicmanagerComment(post_id, uid, zan_count, is_top, comment, w_name, request);
	}

	/**
	 * 视频管理列表
	 */
	@RequestMapping(value = "/videomanager", method = RequestMethod.GET)
	public String getVideoManager(@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "f_uuid", required = false, defaultValue = "0") long f_uuid,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getVideoManager(uid, f_uuid, start_time, end_time, title, page, model);
	}

	/**
	 * 视频管理==》上线
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/postonline", method = RequestMethod.POST)
	public int videoOnline(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PostOnline(w_name, id, request);
	}

	/**
	 * 视频管理==》下线
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/postdownline", method = RequestMethod.POST)
	public int videoDownline(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PostDownline(w_name, id, request);
	}

	/**
	 * 视频管理删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/postdelete", method = RequestMethod.POST)
	public int videoManagerDelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.PostDelete(w_name, id, request);
	}

	/**
	 * 视频管理添加页面
	 */
	@RequestMapping(value = "/videomanager/add", method = RequestMethod.GET)
	public String videoManagerAdd(Model model) {
		return communityservice.videoManagerAdd(model);
	}

	/**
	 * 视频管理编辑页面
	 */
	@RequestMapping(value = "/videomanager/edit", method = RequestMethod.GET)
	public String videoManagerEdit(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			Model model) {
		return communityservice.videoManagerEdit(id, model);
	}

	/**
	 * 视频管理添加和编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveVideoManager(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "zan_count", required = false, defaultValue = "0") int zan_count,
			@RequestParam(value = "share_count", required = false, defaultValue = "0") int share_count,
			@RequestParam(value = "video", required = false, defaultValue = "") String video,
			@RequestParam(value = "is_online", required = false, defaultValue = "1") int is_online,
			@RequestParam(value = "tag_id", required = false, defaultValue = "0") int tag_id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.saveVideoManager(id, uid, title, zan_count, share_count, video, is_online,tag_id, w_name,
				request);
	}

	/**
	 * 视频管理==评论
	 */
	@RequestMapping(value = "/videomanager/comment", method = RequestMethod.GET)
	public String getvideoComment(@RequestParam(value = "post_id", required = false, defaultValue = "0") long post_id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getVideoComment(post_id, uid, nickname, comment, start_time, end_time, page, model);
	}

	/**
	 * 视频管理==评论删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/commentdelete", method = RequestMethod.POST)
	public int videoCommentDelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.CommentDelete(w_name, id, request);
	}

	/**
	 * 视频管理==》评论置顶
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/topcomment", method = RequestMethod.POST)
	public int videotopComment(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "is_top", required = false, defaultValue = "0") int is_top,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.topComment(w_name, id, is_top, request);
	}

	/**
	 * 视频评论添加
	 */
	@ResponseBody
	@RequestMapping(value = "/videomanager/addcomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addvideomanagerComment(
			@RequestParam(value = "post_id", required = false, defaultValue = "0") int post_id,
			@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "zan_count", required = false, defaultValue = "0") int zan_count,
			@RequestParam(value = "is_top", required = false, defaultValue = "0") int is_top,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.addPicmanagerComment(post_id, uid, zan_count, is_top, comment, w_name, request);
	}

	@ResponseBody
	@RequestMapping(value = "/getuidexist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getPopularAnchorExist(@RequestParam(value = "uid", required = false, defaultValue = "0") long uid) {
		return communityservice.getPopularAnchorExist(uid);
	}

	@ResponseBody
	@RequestMapping(value = "/getuidexist2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getPopularAnchorExist2(@RequestParam(value = "uid", required = false, defaultValue = "0") long uid) {
		return communityservice.getPopularAnchorExist2(uid);
	}

	@ResponseBody
	@RequestMapping(value = "/getfuuidexist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getHotLiveExist(@RequestParam(value = "f_uuid", required = false, defaultValue = "0") long f_uuid) {
		return communityservice.getFuuidExist(f_uuid);
	}

	/**
	 * 评论管理
	 */
	@RequestMapping(value = "/commentmanager", method = RequestMethod.GET)
	public String getCommentManager(@RequestParam(value = "uid", required = false, defaultValue = "0") long uid,
			@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_time,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getCommentManager(uid, nickname, comment, start_time, end_time, page, model);
	}
	
	/**
	 * 标签管理
	 */
	@RequestMapping(value = "/tagmanager", method = RequestMethod.GET)
	public String getTagManager(
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		return communityservice.getTagManager( name,  page, model);
	}
	
	/**
	 * 帖子标签添加和编辑
	 */
	@ResponseBody
	@RequestMapping(value = "/tagmanager/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveTagManager(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "tagname", required = false, defaultValue = "") String name,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request) {
		return communityservice.saveTagManager(id, name, w_name,request);
	}

	/**
	 * 标签删除操作
	 */
	@ResponseBody
	@RequestMapping(value = "/tagmanager/delete", method = RequestMethod.POST)
	public int TagManagerdelete(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.TagManagerdelete(w_name, id, request);
	}
	
	/**
	 * 帖子置顶
	 */
	@ResponseBody
	@RequestMapping(value = "/toppost", method = RequestMethod.POST)
	public int topPost(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "is_top", required = false, defaultValue = "0") int is_top,
			@CookieValue(value = "w_name", defaultValue = "") String w_name, HttpServletRequest request, Model model) {
		return communityservice.topPost(w_name, id, is_top, request);
	}
}

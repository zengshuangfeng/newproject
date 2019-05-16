package com.meisui.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.User_OnlineService;

/**
 * <p>文件名称：User_Online.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月14日 下午4:27:15
 */
@Controller
@RequestMapping("/useronline")
public class User_OnlineController {
	@Autowired
	private User_OnlineService user_OnlineService;
	/**
	 * 
	 * <p>功能描述：用户在线列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月14日 下午4:26:22</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 用户昵称
	 * @param page 页码
	 * @param model
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUserAnchorList(@RequestParam(value = "uid", required = false,defaultValue="")String uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="")String f_uuid,
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return user_OnlineService.getUserOnlineList(uid, f_uuid, nickname, page, model);
	}
}

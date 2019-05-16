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

import com.meisui.manage.service.User_VestService;

/**
 * <p>文件名称：User_VestController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月10日 上午10:27:28
 */
@RequestMapping("/uservest")
@Controller
public class User_VestController {
	@Autowired
	private User_VestService user_VestService;
	/**
	 * 
	 * <p>功能描述：马甲列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午10:07:33</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "uid", required = false,defaultValue="0")Long uid,
			@RequestParam(value = "nickname", required = false,defaultValue="")String nickname,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {
		return user_VestService.getList(uid, nickname, page, model);
	}
	/**
	 * 
	 * <p>功能描述：马甲添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午11:16:14</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return user_VestService.add(model);
	}

	/**
	 * 
	 * <p>功能描述：获取地区列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年5月5日 上午10:15:29</p>
	 *
	 * @param f_id 父id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getarealist", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getAreaList(@RequestParam(value = "f_id", required = false,defaultValue="0")int f_id,
			Model model) {
		return user_VestService.getAreaList(f_id);
	}
	/**
	 * 
	 * <p>功能描述：马甲编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午11:16:14</p>
	 *
	 * @param id 用户id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0")Long id,
			Model model) {
		return user_VestService.edit(id, model);
	}
	/**
	 * 
	 * <p>功能描述：添加视频页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 下午2:01:33</p>
	 *
	 * @param uid 用户uid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addvideo", method = RequestMethod.GET)
	public String addVideo(@RequestParam(value = "uid", required = false,defaultValue="0")Long uid,
			@RequestParam(value = "f_uuid", required = false,defaultValue="0")Long f_uuid,
			Model model) {
		model.addAttribute("uid", uid);
		model.addAttribute("f_uuid", f_uuid);
		model.addAttribute("activeUrl", "uservest");
		return "uservest/addvideo";
	}
	/**
	 * 
	 * <p>功能描述：保存马甲信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 上午10:26:45</p>
	 *
	 * @param id 用户id
	 * @param nickname 昵称
	 * @param sex 性别
	 * @param head 头像
	 * @param level 等级
	 * @param area 地区
	 * @param age 年龄
	 * @param province 省份
	 * @param city 城市
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveinfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveInfo(@RequestParam(value="id", required=false,defaultValue="0")long id,
			@RequestParam(value="nickname", required=false,defaultValue="") String nickname,
			@RequestParam(value="head", required=false,defaultValue="") String head,
			@RequestParam(value="sex", required=false,defaultValue="0")int sex,
			@RequestParam(value="level", required=false,defaultValue="1")int level,
			@RequestParam(value="area", required=false,defaultValue="") String area,
			@RequestParam(value="province", required=false,defaultValue="0") int province,
			@RequestParam(value="city", required=false,defaultValue="0") int city,
			@RequestParam(value="age", required=false,defaultValue="0") int age,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_VestService.saveInfo(id, nickname, sex, head, level, area, province, city, age, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：保存马甲视频信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月10日 下午2:13:22</p>
	 *
	 * @param uid 用户uid
	 * @param f_uuid 用户房间号
	 * @param cover 视频封面
	 * @param video 视频
	 * @param seconds 视频秒数
	 * @param source 来源 0用户发布 1美拍
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savevideo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveVideo(@RequestParam(value="uid", required=false,defaultValue="0")long uid,
			@RequestParam(value="f_uuid", required=false,defaultValue="0")long f_uuid,
			@RequestParam(value="cover", required=false,defaultValue="") String cover,
			@RequestParam(value="video", required=false,defaultValue="") String video,
			@RequestParam(value="seconds", required=false,defaultValue="0")int seconds,
			@RequestParam(value="source", required=false,defaultValue="0")int source,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_VestService.saveVideo(uid, f_uuid, cover, video, seconds, source, w_name, request);
	}
	@ResponseBody
	@RequestMapping(value = "/getnicknameexist", method = RequestMethod.GET)
	public int getNicknameExist(@RequestParam(value="uid", required=false,defaultValue="0")long uid, String nickname) {

		return user_VestService.getNicknameExist(uid, nickname);
	}
	/**
	 * 
	 * <p>功能描述：马甲删除，做封号处理</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月5日 下午3:00:40</p>
	 *
	 * @param id 用户id
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value="id", required=false,defaultValue="0")long id, 
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return user_VestService.deleteUserVest(id, w_name, request);
	}
}

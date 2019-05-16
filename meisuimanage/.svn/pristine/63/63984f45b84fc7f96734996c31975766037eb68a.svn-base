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

import com.meisui.manage.service.Anchor_Invite_ConfigService;

/**
 * <p>文件名称：Anchor_Invite_ConfigController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@RequestMapping("/anchorinvite")
@Controller
public class Anchor_Invite_ConfigController {
	@Autowired
	private Anchor_Invite_ConfigService anchor_Invite_ConfigService;
	/**
	 * 
	 * <p>功能描述：主播邀请配置列表</p>
	 *
	 * @param uid 主播uid
	 * @param nickname 昵称
	 * @param is_online 是否开启 1是 0否
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "uid", required = false, defaultValue="0") long uid,
			@RequestParam(value = "nickname", required = false, defaultValue="") String nickname,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return anchor_Invite_ConfigService.getAnchorInviteConfigList(uid, nickname, is_online, page, model);
	}
	/**
	 * 
	 * <p>功能描述：主播邀请配置添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:22:43</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		return anchor_Invite_ConfigService.addAnchorInviteConfig(model);
	}
	/**
	 * 
	 * <p>功能描述：主播邀请配置编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 主播邀请配置id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			Model model) {	
		return anchor_Invite_ConfigService.editAnchorInviteConfig(id, model);
	}	
	/**
	 * 
	 * <p>功能描述：保存主播邀请配置信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 下午3:59:49</p>
	 *
	 * @param id 主播邀请配置表id
	 * @param uid 主播uid
	 * @param max_reward 最大随机钻石数
	 * @param min_reward 最小随机钻石数
	 * @param is_online 是否开启 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "uid", required = false, defaultValue="0") long uid,
			@RequestParam(value = "max_reward", required = false, defaultValue="0") int max_reward,
			@RequestParam(value = "min_reward", required = false, defaultValue="0") int min_reward,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return anchor_Invite_ConfigService.saveAnchorInviteConfig(id, uid, max_reward, min_reward, is_online, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除主播邀请配置</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 主播邀请配置id
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request) {	

		return anchor_Invite_ConfigService.deleteAnchorInviteConfig(w_name, id, request);
	}
	/**
	 * 
	 * <p>功能描述：获取主播邀请配置是否存在</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 下午4:03:19</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getexist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getExist(@RequestParam(value = "uid", required = false,defaultValue="0") long uid) {	
		return anchor_Invite_ConfigService.getAnchorInviteConfigExist(uid);
	}
}

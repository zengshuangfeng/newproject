package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.Version_UpdateService;

/**
 * <p>文件名称：Version_UpdateController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月18日 上午10:30:42
 */
@Controller
@RequestMapping("/versionupdate")
public class Version_UpdateController {
	@Autowired
	private Version_UpdateService version_UpdateService;	
	/**
	 * 
	 * <p>功能描述：版本更新列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:22:23</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(value="page", required=false, defaultValue="1")int page,
			Model model){
		return version_UpdateService.getVersionUpdateList(page, model);		
	}
	/**
	 * 
	 * <p>功能描述：版本更新添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:38:14</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addActivity(Model model)
	{
		model.addAttribute("activeUrl", "versionupdate");
		return "versionupdate/add";
	}
	/**
	 * 
	 * <p>功能描述：保存版本更新信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:26:59</p>
	 *
	 * @param version 版本
	 * @param content 文案
	 * @param effect_time 生效时间
	 * @param platform 平台
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveVersionUpdate(@RequestParam(value="version", required=false,defaultValue="") String version,
			@RequestParam(value="content", required=false,defaultValue="") String content,
			@RequestParam(value="platform", required=false) String platform,
			@RequestParam(value="effect_time", required=false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date effect_time,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			@RequestParam(value="force", required=false,defaultValue="0") int force,
			@RequestParam(value = "url", required = false, defaultValue = "")String url,
			HttpServletRequest request) {

		return version_UpdateService.saveVersionUpdate(version, content, effect_time, platform, force, w_name, request, url);
	}
	/**
	 * 
	 * <p>功能描述：更新版本更新状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:29:52</p>
	 *
	 * @param id 版本更新id
	 * @param is_online 状态 1正常 0关闭
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateonline", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public int updateVersionUpdateOnline(@RequestParam(value="id", required=false,defaultValue="0") int id,
			@RequestParam(value="is_online", required=false,defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return version_UpdateService.updateVersionUpdateOnline(id, is_online, w_name, request);
	}
}

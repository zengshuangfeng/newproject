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

import com.forman.foundation.library.ThreeDESUtil;
import com.meisui.manage.entity.AddressChange;
import com.meisui.manage.service.ActivityService;
import com.meisui.manage.service.AddressChangeService;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：AddressChangeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@RequestMapping("/addressChange")
@Controller
public class AddressChangeController {
	@Autowired
	private AddressChangeService addressChangeService;
	/**
	 * <p>功能描述：列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model)
	{
		return addressChangeService.getAlladdressChangeList(page, model);
	}
	/**
	 * <p>功能描述：跳转页面表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add()
	{
		return "addresschange/add";
	}
	/**
	 * <p>功能描述：根据用户fuuid获取代理信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAgentUserBYFuid",method=RequestMethod.POST)
	public AddressChange getAgentUserBYFuid(String fuuid)
	{
		AddressChange a=addressChangeService.getAgentUserBYFuid(fuuid);
		return addressChangeService.getAgentUserBYFuid(fuuid);
	}
	/**
	 * <p>功能描述：根据用户fuuid获取信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getUserBYFuid",method=RequestMethod.POST)
	public AddressChange getUserBYFuid(String fuuid) {
		AddressChange a=addressChangeService.getUserBYFuid(fuuid);
		return addressChangeService.getUserBYFuid(fuuid);
	}
	/**
	 * <p>功能描述：添加变更记录 修改用户变更</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月21日 上午9:21:27</p>
	 *
	 * @param page
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveAddressChange",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void saveAddressChange(String fuuid,String nickname ,String old_center_name,String agentfuuid,String new_center_name,
					String agentname ,@CookieValue(value = "admin", required = true) String cookie,String agent_id,
					String operate_center_id,String agent_promoter_id) throws Exception
	{
		String admins = ThreeDESUtil.decode(cookie, PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));	
		String[] adminStrings = admins.split(",");
		AddressChange addresschange = new AddressChange();
		addresschange.setF_uuid(fuuid);
		addresschange.setNickname(nickname);
		addresschange.setOld_center_name(old_center_name);
		addresschange.setAgentfuuid(agentfuuid);
		addresschange.setNew_center_name(new_center_name);
		addresschange.setAgentname(agentname);
		addresschange.setCreate_name(adminStrings[0]);
		addresschange.setAgent_id(agent_id);
		addresschange.setOperate_center_id(operate_center_id);
		addresschange.setAgent_promoter_id(agent_promoter_id);
		addressChangeService.saveAddressChange(addresschange);
		addressChangeService.updateUser(addresschange);
	}
}

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

import com.meisui.manage.service.Anchor_UnionService;

/**
 * <p>文件名称：Anchor_UnionController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月7日 下午3:22:20
 */
@RequestMapping("/anchorunion")
@Controller
public class Anchor_UnionController {
	@Autowired
	private Anchor_UnionService anchor_UnionService;
	/**
	 * 
	 * <p>功能描述：工会列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:02:59</p>
	 *
	 * @param page 页码
	 * @param name 工会名称
	 * @param remark 工会备注
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getAnchorUnionList(@RequestParam(value = "name", required = false,defaultValue="") String name,
			@RequestParam(value = "remark", required = false,defaultValue="") String remark,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model) {

		return anchor_UnionService.getAnchorUnionList(page, name, remark, model);
	}
	/**
	 * 
	 * <p>功能描述：添加主播工会信息页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:17:29</p>
	 *
	 * @param model
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAnchorUnion(Model model) {

		anchor_UnionService.addAnchorUnion(model);
		return "anchorunion/add";
	}
	/**
	 * 
	 * <p>功能描述：编辑主播工会信息页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:17:29</p>
	 *
	 * @param id 主播工会id
	 * @param model
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editAnchorUnion(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			Model model) {
		anchor_UnionService.editAnchorUnion(id, model);
		return "anchorunion/edit";
	}
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveAnchorUnion(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			@RequestParam(value = "name", required = false,defaultValue="") String name, 
			@RequestParam(value = "remark", required = false,defaultValue="") String remark, 
			@RequestParam(value = "divide_proportion", required = false,defaultValue="0") int divide_proportion, 
			@RequestParam(value = "one_divide_proportion", required = false,defaultValue="0") int one_divide_proportion,
			@RequestParam(value = "type", required = false,defaultValue="0") int type, 
			@RequestParam(value = "username", required = false,defaultValue="") String username, 
			@RequestParam(value = "password", required = false,defaultValue="") String password, 
			@RequestParam(value = "commander", required=false, defaultValue="") String commander,
			@RequestParam(value = "weixin", required=false, defaultValue="") String weixin,
			@RequestParam(value = "qq", required=false, defaultValue="") String qq,
			@RequestParam(value = "tel", required=false, defaultValue="") String tel,
			@RequestParam(value = "email", required=false, defaultValue="") String email,
			@RequestParam(value = "card_holder", required=false, defaultValue="") String card_holder,
			@RequestParam(value = "bank_card_no", required=false, defaultValue="") String bank_card_no,
			@RequestParam(value = "province", required=false, defaultValue="0") int province,
			@RequestParam(value = "city", required=false, defaultValue="0") int city,
			@RequestParam(value = "bank_name", required=false, defaultValue="") String bank_name,
			@RequestParam(value = "company", required=false, defaultValue="") String company,
			@RequestParam(value = "business_license", required=false, defaultValue="") String business_license,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request,
			Model model) {	
		return anchor_UnionService.saveAnchorUnion(id, name, remark, divide_proportion, one_divide_proportion, type, username, password, commander, weixin, qq, tel, email, card_holder, bank_card_no, province, city, bank_name, company, business_license, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除工会</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 上午11:01:17</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 工会id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return anchor_UnionService.delete(w_name, id, request);
	}
	@ResponseBody
	@RequestMapping(value = "/getusernameexist", method = RequestMethod.GET)
	public int getUsernameExist(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "username", required = false,defaultValue="")String username,
			HttpServletRequest request) {

		return anchor_UnionService.getUsernameExist(username, id);
	}
	@ResponseBody
	@RequestMapping(value = "/getarealist", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getAreaList(@RequestParam(value = "f_id", required = false,defaultValue="0")int f_id,
			Model model) {	
		return anchor_UnionService.getAreaList(f_id);
	}
}

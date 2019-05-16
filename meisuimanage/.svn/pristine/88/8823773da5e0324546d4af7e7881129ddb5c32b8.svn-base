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

import com.meisui.manage.service.ProductService;

/**
 * <p>文件名称：ProductController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 上午10:39:12
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	/**
	 * 
	 * <p>功能描述：商品列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 上午10:28:00</p>
	 *
	 * @param is_online 1上架 0下架
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "is_online", required = false,defaultValue="1") int is_online,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return productService.getList(is_online, page, model);
	}
	/**
	 * 
	 * <p>功能描述：商品添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 上午10:28:56</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {	
		return productService.add(model);
	}
	/**
	 * 
	 * <p>功能描述：商品编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 上午10:28:56</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			Model model) {	
		return productService.edit(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存商品信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 上午10:37:48</p>
	 *
	 * @param id 商品id
	 * @param name 商品名称
	 * @param description 商品介绍
	 * @param pic 商品图片
	 * @param type 类型 0钥匙兑换奖品 1现金购买商品
	 * @param price 钥匙数或现金数
	 * @param stock 商品库存
	 * @param issue 发放
	 * @param tips 领奖提示
	 * @param is_online 状态 1上架 0下架
	 * @param sort 排序
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "description", required = false, defaultValue="") String description,
			@RequestParam(value = "pic", required = false, defaultValue="") String pic,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "price", required = false, defaultValue="0") int price,
			@RequestParam(value = "stock", required = false, defaultValue="0") int stock,
			@RequestParam(value = "issue", required = false, defaultValue="") String issue,
			@RequestParam(value = "tips", required = false, defaultValue="") String tips,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return productService.save(id, name, description, pic, type, price, stock, issue, tips, is_online, sort, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：商品信息删除</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:14:06</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 商品id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return productService.delete(w_name, id, request);
	}
	/**
	 * 
	 * <p>功能描述：更新商品是否上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 商品id
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateisonline", method = RequestMethod.POST)
	public int updateIsOnline(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@RequestParam(value = "is_online", required = false,defaultValue="0") int is_online,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {

		return productService.updateIsOnline(w_name, id, is_online, request);
	}
}

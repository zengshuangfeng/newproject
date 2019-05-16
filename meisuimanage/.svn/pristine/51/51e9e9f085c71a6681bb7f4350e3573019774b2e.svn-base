package com.meisui.manage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.MenuService;



/**
 * <p>文件名称：MenuController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年4月5日 下午2:00:10
 */
@RequestMapping("/menu")
@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * <p>功能描述：菜单列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月7日 下午4:18:45</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model){
		return menuService.getMenuList(page, model);

	}

	/**
	 * 
	 * <p>功能描述：保存菜单信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月7日 下午4:22:25</p>
	 *
	 * @param id
	 * @param name 菜单名称
	 * @param name_en 菜单英文名称
	 * @param url 地址url
	 * @param has_submenu 是否有子菜单 1:是 0:否
	 * @param class_name 样式名称
	 * @param sort 排序
	 * @param f_id 父ID
	 * @param isshow 是否显示 1:是 0:否
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveProductPic(
			@RequestParam(value="id", required = false,defaultValue="0") int id,
			@RequestParam(value="name", required = false,defaultValue="") String name,
			@RequestParam(value="name_en",required=false,defaultValue="")String name_en,
			@RequestParam(value="url",required=false,defaultValue="")String url,
			@RequestParam(value="has_submenu",required=false,defaultValue="0")int has_submenu,
			@RequestParam(value="class_name",required=false,defaultValue="")String class_name,
			@RequestParam(value="sort",required=false,defaultValue="0")int sort,
			@RequestParam(value="f_id",required=false,defaultValue="0")int f_id,
			@RequestParam(value="isshow",required=false,defaultValue="0")int isshow) {

		return menuService.save(id,name,name_en,url,has_submenu,class_name,sort,f_id,isshow);
	}
	/**
	 * <p>功能描述：添加菜单</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月7日 下午4:24:17</p>
	 *
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return  menuService.add(model);	
	}
	/**
	 * <p>功能描述：编辑菜单</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月7日 下午4:24:43</p>
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			Model model
			){
		return menuService.getEdit(id,model);
	}
}

package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.ImenuDao;
import com.meisui.manage.entity.Menu;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.PageUtil;


/**
 * <p>文件名称：Product_Category_Service.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年4月5日 下午2:01:15
 */
@Service
public class MenuService {

	private static Logger log=Logger.getLogger(MenuService.class.getClass());
	@Autowired
	private ImenuDao  imenuDao;


	public String getMenuList(int page, Model model) {
		try {
			List<Menu> menuList = imenuDao.getMenuList((page - 1) * 20, 20);
			int totalRecord = imenuDao.getMenuCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("menu/list");
			model.addAttribute("_menuList", menuList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "menu");
		} catch (Exception e) {
			log.error("菜单列表异常",e);
		}
		return "menu/list";
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
	 * @param w_name
	 * @return
	 */
	public String save(int id, String name, String name_en, String url,int has_submenu, String class_name, int sort, int f_id, int isshow) {
		try {
			Menu menu=new Menu();
			List<Map<String, String>> mapList = validMenu(name,name_en);
			menu.setClass_name(class_name);
			menu.setF_id(f_id);
			menu.setHas_submenu(has_submenu);
			menu.setIsshow(isshow);
			menu.setName(name);
			menu.setName_en(name_en);
			menu.setSort(sort);
			menu.setUrl(url);
			JSONArray jsonArray = JSONArray.fromObject(mapList);
			if(mapList.size()>0)
			{
				return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
			}
			int result = 0;
			if(id > 0)
			{
				menu.setId(id);
				result = imenuDao.updateMenu(menu);
			}
			else {
				result = imenuDao.insertMenu(menu);
				if (result > 0) {
					imenuDao.insertPermission(menu.getId());
				}
			}
			if(result > 0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error("保存菜单信息异常",ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";

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
	public String  getEdit(int id, Model model) {
		try {
			Menu menu = imenuDao.getMenuById(id);
			List<Menu> menuList=imenuDao.getMenuNameList();
			model.addAttribute("_menuList", menuList);
			model.addAttribute("menu", menu);
			model.addAttribute("activeUrl", "menu");
		} catch (Exception e) {
			log.error("编辑菜单页面异常",e);
		}
		return "menu/edit";
	}
	/**
	 * <p>功能描述验证数据</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月7日 下午4:48:45</p>
	 *
	 * @param name 菜单名称
	 * @param name_en 菜单英文名称
	 * @return
	 */
	private List<Map<String, String>> validMenu(String name, String name_en) {
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		if (StringUtils.isBlank(name)) {
			mapList.add(AuthUtil.getMap("name", "菜单名称不能为空"));
		}
		if (StringUtils.isBlank(name_en)) {
			mapList.add(AuthUtil.getMap("name_en", "菜单英文名称不能为空"));
		}
		return mapList;
	}
    /**
     * <p>功能描述：添加菜单</p>
     * <p>创建人：</p>
     * <p>创建日期：2016年6月7日 下午5:13:39</p>
     *
     * @param model
     * @return
     */
	public String add(Model model) {
		try {
			List<Menu> menuList=imenuDao.getMenuNameList();
			model.addAttribute("_menuList", menuList);
			model.addAttribute("activeUrl", "menu");
		} catch (Exception e) {
			log.error("添加菜单异常");
		}
		return "menu/add";
	}
}

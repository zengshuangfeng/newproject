package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IproductDao;
import com.meisui.manage.entity.Product;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：ProductService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 上午10:19:02
 */
@Service
public class ProductService {
	private static Logger log = Logger.getLogger(ProductService.class.getClass());
	@Autowired
	private IproductDao iproductDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
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
	public String getList(int is_online, int page, Model model)
	{
		try {
			List<Product> productList = iproductDao.getProductList(is_online, (page-1)*20, 20);
			int totalRecord = iproductDao.getProductCount(is_online);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("product/list");
			model.addAttribute("productList", productList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "product");
			model.addAttribute("is_online", is_online);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("商品列表", e);
		}
		return "product/list";
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
	public String add(Model model)
	{
		model.addAttribute("activeUrl", "product");
		return "product/add";
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
	public String edit(int id, Model model)
	{
		try {
			Product product = iproductDao.getProduct(id);
			model.addAttribute("product", product);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "product");
		} catch (Exception e) {
			log.error("商品编辑页面", e);
		}
		return "product/edit";
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
	public String save(int id, String name, String description, String pic, int type, int price, int stock, String issue, String tips, int is_online, int sort, String w_name, HttpServletRequest request)
	{
		try {
			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setPic(pic);
			product.setType(type);
			product.setPrice(price);
			product.setStock(stock);
			product.setIssue(issue);
			product.setTips(tips);
			product.setIs_online(is_online);
			product.setSort(sort);
			w_name = String.valueOf(request.getAttribute("w_name"));
			product.setW_name(w_name);
			Date date = new Date();
			product.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				product.setId(id);
				result = iproductDao.updateProduct(product);
				imanage_RecordDao.insertManageRecord(w_name, "更新商品信息", "product", product.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				product.setCreate_time(date);
				result = iproductDao.insertProduct(product);
				imanage_RecordDao.insertManageRecord(w_name, "添加商品信息", "product", product.getId(), IPUtil.getIp(request), date);
			}		
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			log.error("保存商品信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
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
	public int delete(String w_name, int id, HttpServletRequest request) {	
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除商品信息", "t_product", id, IPUtil.getIp(request), new Date());
			return iproductDao.deleteProduct(id, w_name, date);
		} catch (Exception e) {
			log.error("商品信息删除", e);
			return 0;
		}
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
	public int updateIsOnline(String w_name, int id, int is_online, HttpServletRequest request) {	
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "更新商品上线状态", "t_product", id, IPUtil.getIp(request), new Date());
			return iproductDao.updateProductOnline(id, is_online, w_name, date);
		} catch (Exception e) {
			log.error("更新商品是否上线状态", e);
			return 0;
		}
	}
}

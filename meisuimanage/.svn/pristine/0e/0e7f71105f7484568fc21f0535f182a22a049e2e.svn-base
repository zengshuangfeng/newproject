package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Product;

/**
 * <p>文件名称：IproductDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 上午10:15:42
 */
@Repository
public interface IproductDao {
	List<Product> getProductList(@Param("is_online")Integer is_online, @Param("offset")int offset, @Param("rows")int rows);
	Integer getProductCount(@Param("is_online")Integer is_online);
	Integer insertProduct(Product entity);
	Integer updateProduct(Product entity);
	Integer deleteProduct(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Product getProduct(@Param("id")Integer id);
	Integer updateProductOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

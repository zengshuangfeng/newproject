package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User_Exchange;

/**
 * <p>文件名称：Iuser_ExchangeDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 下午3:03:08
 */
@Repository
public interface Iuser_ExchangeDao {
	List<User_Exchange> getUserExchangeList(@Param("status")Integer status, @Param("type")Integer type, @Param("p_name")String p_name, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")int offset, @Param("rows")int rows);
	Integer getUserExchangeCount(@Param("status")Integer status, @Param("type")Integer type, @Param("p_name")String p_name, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	User_Exchange getUserExchange(@Param("id")Integer id);
	Integer updateUserExchangeStatus(@Param("id")Integer id, @Param("status")Integer status, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Integer updateUserExchangeLogistics(@Param("id")Integer id, @Param("logistics_name")String logistics_name, @Param("logistics_code")String logistics_code, @Param("remark")String remark, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

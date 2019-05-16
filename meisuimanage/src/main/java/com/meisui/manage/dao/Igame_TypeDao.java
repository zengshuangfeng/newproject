package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Game_Type;

/**
 * <p>文件名称：Igame_TypeDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月15日 上午10:11:56
 */
@Repository
public interface Igame_TypeDao {
	List<Game_Type> getGameTypeList();
	Integer updateGameTypeOpen(@Param("id")Integer id, @Param("is_open")Integer is_open, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Integer updateGameTypeSort(@Param("id")Integer id, @Param("sort")Integer sort, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

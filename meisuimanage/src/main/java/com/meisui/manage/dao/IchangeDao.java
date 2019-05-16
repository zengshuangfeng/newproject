package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Change;

/**
 * <p>文件名称：IchangeDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午2:59:53
 */
@Repository
public interface IchangeDao {
	List<Change> getChangeList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getChangeCount();
	Integer insertChange(Change entity);
	Integer updateChange(Change entity);
	Change getChange(@Param("id")Integer id);
	Integer deleteChange(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date date);
	Integer updateChangeOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date date);
	List<Change> getAllChangeList();
	int updateChangeVirtual(@Param("id")Integer id, @Param("is_virtual")Integer is_virtual, @Param("w_name")String w_name, @Param("update_time")Date date);
}

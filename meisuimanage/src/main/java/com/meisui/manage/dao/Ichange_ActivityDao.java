package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Change_Activity;

/**
 * <p>文件名称：Ichange_ActivityDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午3:00:04
 */
@Repository
public interface Ichange_ActivityDao {
	List<Change_Activity> getChangeActivityList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getChangeActivityCount();
	Integer insertChangeActivity(Change_Activity entity);
	Integer updateChangeActivity(Change_Activity entity);
	Change_Activity getChangeActivity(@Param("id")Integer id);
	Integer deleteChangeActivity(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

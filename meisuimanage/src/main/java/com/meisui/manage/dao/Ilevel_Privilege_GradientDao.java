package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Level_Privilege_Gradient;

/**
 * <p>文件名称：Ilevel_Privilege_GradientDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午2:15:40
 */
@Repository
public interface Ilevel_Privilege_GradientDao {
	List<Level_Privilege_Gradient> getLevelPrivilegeGradientList(@Param("p_id")Integer p_id, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getLevelPrivilegeGradientCount(@Param("p_id")Integer p_id);
	Level_Privilege_Gradient getLevelPrivilegeGradient(@Param("id")Integer id);
	Integer insertLevelPrivilegeGradient(Level_Privilege_Gradient entity);
	Integer updateLevelPrivilegeGradient(Level_Privilege_Gradient entity);
	Integer deleteLevelPrivilegeGradient(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

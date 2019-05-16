package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Level_Privilege;

/**
 * <p>文件名称：Ilevel_PrivilegeDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午5:34:59
 */
@Repository
public interface Ilevel_PrivilegeDao {
	List<Level_Privilege> getLevelPrivilegeList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getLevelPrivilegeCount();
	Integer updateLevelPrivilege(Level_Privilege entity);
	Level_Privilege getLevelPrivilege(@Param("id")Integer id);
}

package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Admin_Group;


/**
 * <p>文件名称：Iadmin_GroupDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年6月21日 上午9:22:11
 */
@Repository
public interface Iadmin_GroupDao {
	List<Admin_Group> getGroupList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getGroupCount();
	Integer insertGroup(Admin_Group entity);
	Integer updateGroup(Admin_Group entity);
	Admin_Group getGrou(@Param("id")Integer  id);
	List<Admin_Group> getAllGrouList();
	Integer deleteGroup(@Param("id")Integer id,@Param("w_name")String  w_name,@Param("update_time")Date  update_time);
}

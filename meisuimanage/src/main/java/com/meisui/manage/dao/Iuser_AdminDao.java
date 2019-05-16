package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User_Admin;

/**
 * <p>文件名称：Iuser_AdminDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Repository
public interface Iuser_AdminDao {
	List<User_Admin> getUserAdminList(@Param("f_uuid")Long f_uuid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserAdminCount(@Param("f_uuid")Long f_uuid);
	Integer deleteUserAdmin(@Param("id")Integer id, @Param("update_time")Date update_time);
	Integer insertUserAdmin(@Param("f_uuid")Long f_uuid, @Param("uid")Long uid, @Param("create_time")Date create_time, @Param("update_time")Date update_time);
	List<User_Admin> getUserControlList(@Param("f_uuid")Long f_uuid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer deleteUserControl(@Param("id")Integer id, @Param("update_time")Date update_time);
}

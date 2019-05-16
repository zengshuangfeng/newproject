package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Admin;
import com.meisui.manage.entity.Manage_Menu;

@Repository
public interface Imanage_MenuDao {
	List<Manage_Menu> getMenuList(@Param("admin_id") Integer admin_id); 
	List<Manage_Menu> getFMenuList();
	List<Manage_Menu> getMenuListByAdminId(@Param("admin_id") Integer admin_id);
	List<Manage_Menu> getMenuListByAdminId2(@Param("admin_id") Integer admin_id);
	List<Manage_Menu> getMenuListByAdminId3(@Param("admin_id") Integer admin_id);
	Admin getGroup(@Param("admin_id") Integer admin_id);
	List<Manage_Menu> getMangeFid();
}

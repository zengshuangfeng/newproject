package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Menu;
@Repository
public interface ImenuDao {
	List<Menu> getMenuList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getMenuCount();
	Integer updateMenu(Menu entity);
	Integer insertMenu(Menu entity);
	Menu getMenuById(@Param("id")Integer id);
	Integer insertPermission(@Param("menu_id")Integer menu_id);
	List<Menu> getMenuNameList();
}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Admin;

@Repository
public interface IadminDao {
	Admin getAdminByName(@Param("username") String username);
	List<Admin> getAdminList(@Param("keyword") String keyword,@Param("id")Integer id);
	Admin getAdminById(@Param("id") Integer id);
	Integer insertAdmin(Admin entity);
	Integer updateAdmin(Admin entity);
	Integer insertAdminPermission(@Param("admin_id") Integer admin_id, @Param("list") List<String> list);
	Integer deleteAdmin(@Param("id") Integer id);
	Integer deleteAdminPermission(@Param("admin_id") Integer admin_id);
	Integer resetPwd(Admin entity);
	Integer getAdminByNickname(@Param("nickname") String nickname);
	Integer getAdminByUsername(@Param("username") String username);
	List<Integer> getMenuId(@Param("admin_id") Integer admin_id);
	List<Admin> getVideoAdminList(@Param("id")Integer id, @Param("nickname")String nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getVideoAdminCount(@Param("id")Integer id, @Param("nickname")String nickname);
	Integer updateAdminClose(@Param("id")Integer id, @Param("is_close")Integer is_close, @Param("w_name")String w_name);
	Integer updateAdminVideoCount(@Param("id")Integer id, @Param("add")Integer add);
	List<String> getALLAdminList();
}

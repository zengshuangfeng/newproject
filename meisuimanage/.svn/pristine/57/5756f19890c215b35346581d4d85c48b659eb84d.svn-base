package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.AuditRecords;
import com.meisui.manage.entity.Authentication;


@Repository
public interface AuthenticationDao {
	List<Authentication> getAuthenticationList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAuthenticationCount();
	int checkPass(int id,int is_status);
	Authentication getAuthentication(int authentication_id);
	
}

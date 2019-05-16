package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.UserVest;

@Repository
public interface UserVestDao {

	List<UserVest> getUserVestList(@Param("is_import")int is_import);
	
	int updateUserVestListByIds(List<UserVest> list);
}

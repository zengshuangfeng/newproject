package com.meisui.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Iuser_deviceDao {
	
	Long getIdByUid(@Param("uid")Long uid);

}

package com.meisui.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Profit;

@Repository
public interface testDao {
	@Select("select * from t_profit where id=#{id}")
	Profit list(@Param("id")int id);
	
	
}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.H5;

@Repository
public interface Ih5Dao {
	List<H5> getH5List(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getH5Count();
	Integer insertH5(H5 entity);
	Integer updateH5(H5 entity);
	H5 getH5(@Param("id")Integer id);
}

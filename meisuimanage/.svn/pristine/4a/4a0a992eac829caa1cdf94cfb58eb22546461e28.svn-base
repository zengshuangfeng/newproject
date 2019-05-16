package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Thanksgiving;

@Repository
public interface ThanksgivingDao {

	List<Thanksgiving> list(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int listCount(@Param("uid")long uid);

	void delete(@Param("id")long id);

	void addGreatCount(@Param("id")long id, @Param("num")int num);

}

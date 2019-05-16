package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Area;

@Repository
public interface IareaDao {
	List<Area> getAreaList();
	List<Area> getAreaListWithFid(@Param("f_id")Integer f_id);
	List<Area> getProvinceList();
}

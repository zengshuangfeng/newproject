package com.meisui.manage.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Iflow_RecordDao {

	String getRemark(@Param("b_time")String b_time, @Param("e_time")String e_time);

	void addRemark(@Param("begin_time")String begin_time, @Param("end_time")String end_time, @Param("remark")String remark, @Param("create_time")Date create_time);

}

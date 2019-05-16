package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.SquareFeeRecord;

@Repository
public interface IsquareFeeRecordDao{

	Integer insertSquareFeeRecord(SquareFeeRecord entity);
	
	List<SquareFeeRecord> getSquareFeeRecordList(@Param("uid")Long uid,@Param("nickname")String nickname,@Param("start_date")Date start_date,@Param("end_date")Date end_date,@Param("offset")int offset,@Param("rows")int rows);
	
	Integer getSquareFeeRecordCount(@Param("uid")Long uid,@Param("nickname")String nickname,@Param("start_date")Date start_date,@Param("end_date")Date end_date);
}

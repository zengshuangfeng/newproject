package com.meisui.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IuserStatisticsDao {

	List<Map<String, Object>> getUserInfo(@Param("uid")long uid, @Param("fuuid")long fuuid, @Param("nickName")String nickName, @Param("startLevel")int startLevel, @Param("endLevel")int endLevel,@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("page") int page, @Param("rows")int rows);

	List<Map<String, Object>> getChannel();

	Long getUserStake(@Param("uid")long uid, @Param("startTime")Long startTime, @Param("endTime")Long endTime);

	int getUserInfoCount(@Param("uid")long uid, @Param("fuuid")long fuuid, @Param("nickName")String nickName, @Param("startLevel")int startLevel, @Param("endLevel")int endLevel,@Param("startTime")String startTime, @Param("endTime")String endTime);

	Long getSumVirtual(@Param("uid")long uid, @Param("startTime")String startTime, @Param("endTime")String endTime);

	List<Map<String, Object>> getExportExcelList(@Param("uid")long uid, @Param("fuuid")long fuuid, @Param("nickName")String nickName, @Param("startLevel")int startLevel, @Param("endLevel")int endLevel,@Param("startTime")String startTime, @Param("endTime")String endTime);

	List<Map<String, Object>> getUserInfo2(@Param("uid")long uid, @Param("fuuid")long fuuid, @Param("nickName")String nickName, @Param("startLevel")int startLevel, @Param("endLevel")int endLevel,@Param("page") int page, @Param("rows")int rows);
	
	int getUserInfoCount2(@Param("uid")long uid, @Param("fuuid")long fuuid, @Param("nickName")String nickName, @Param("startLevel")int startLevel, @Param("endLevel")int endLevel);


}

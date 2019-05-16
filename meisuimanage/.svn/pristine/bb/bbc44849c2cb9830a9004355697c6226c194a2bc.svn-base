package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.New_Activity;

@Repository
public interface InewActivityDao {

	List<New_Activity> getNewActivityList(@Param("is_online")Integer is_online, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getNewActivityCount(@Param("is_online")Integer is_online);

	int updateNewActivity(New_Activity newactivity);

	int insertNewActivity(New_Activity newactivity);

	New_Activity getNewActivity(@Param("id")Integer id);

	int updateNewActivityOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date update_time);

}

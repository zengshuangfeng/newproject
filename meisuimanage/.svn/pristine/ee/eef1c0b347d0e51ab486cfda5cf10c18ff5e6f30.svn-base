package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Week_Gift;

@Repository
public interface IWeekGiftDao {

	List<Week_Gift> getWeekGiftList(@Param("gift_id")int gift_id, @Param("gift_name")String gift_name, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getWeekGiftCount(@Param("gift_id")int gift_id, @Param("gift_name")String gift_name);

	int deleteWeekGift(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);

	Week_Gift getWeekGift(@Param("id")Integer id);

	int updateweekgift(Week_Gift weekgift);
	
	int inserweekgift(Week_Gift weekgift);
	

}

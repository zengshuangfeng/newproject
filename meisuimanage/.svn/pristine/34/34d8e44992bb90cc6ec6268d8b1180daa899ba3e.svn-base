package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Christmas_Prize;
import com.meisui.manage.entity.Christmas_User;

@Repository
public interface IChristmasDao {

	List<Christmas_User> getChristmasList(@Param("f_uuid")String f_uuid, @Param("uid")Integer uid, @Param("type")Integer type, 
			@Param("b_time")String b_time, @Param("e_time")String e_time,@Param("offset") Integer offset, @Param("rows") Integer rows);

	int getChristmasRecordCount(@Param("f_uuid")String f_uuid, @Param("uid")Integer uid, @Param("type")Integer type, 
			@Param("b_time")String b_time, @Param("e_time")String e_time);

	Christmas_Prize getChristmasPrize(@Param("prize_id")Integer prize_id);

}

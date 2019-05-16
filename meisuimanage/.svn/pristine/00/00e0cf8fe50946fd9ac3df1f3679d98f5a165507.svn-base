package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Dial_Prize;
import com.meisui.manage.entity.Dial_User;

@Repository
public interface IdialDao {

	List<Dial_Prize> getDialList(@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getDialListCount();

	int updatedialprize(Dial_Prize dialprize);

	int insertdialprize(Dial_Prize dialprize);

	Dial_Prize getDialPrize(@Param("id")Integer id);

	List<Dial_User> getDialUserList(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getDialUserListCount(@Param("uid")Long uid);

	void updatedialpic(@Param("pic")String pic,@Param("gift_id") int gift_id);
	


}

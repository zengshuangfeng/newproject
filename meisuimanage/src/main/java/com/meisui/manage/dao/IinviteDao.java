package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Invite;

@Repository
public interface IinviteDao {
	List<Invite> getInviteList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getInviteCount();
	Invite getInvite(@Param("id")Integer id);
	Integer insertInvite(Invite entity);
	Integer updateInvite(Invite entity);
	Integer updateInviteOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Guard_Manager;

@Repository
public interface Ianchor_Guard_ManageDao {

	List<Anchor_Guard_Manager> getAnchorGuardManageList(@Param("uid")long uid, @Param("f_uuid")String f_uuid, @Param("anchor_f_uuid")String anchor_f_uuid, @Param("type")Integer type,
			@Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getAnchorGuardManageCount(@Param("uid")long uid, @Param("f_uuid")String f_uuid, @Param("anchor_f_uuid")String anchor_f_uuid, @Param("type")Integer type,
			@Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id);

}

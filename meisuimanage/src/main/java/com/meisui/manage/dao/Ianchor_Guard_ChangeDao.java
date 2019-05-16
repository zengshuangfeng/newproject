package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Guard_Change;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.Profit;

@Repository
public interface Ianchor_Guard_ChangeDao {

	List<Anchor_Guard_Change> getGuardChangeList(@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getGuardChangeCount();

	int updateGuardChangeOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date date);

	int deleteGuardChange(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date date);

	int updateAnchorGuardChange(Anchor_Guard_Change anchorguard);

	int inserAnchorGuardChange(Anchor_Guard_Change anchorguard);

	Anchor_Guard_Change getAnchorGuardByid(@Param("id")int id);

	List<Anchor_Guard_Change2> getGuardChangeNewList(@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getGuardChangeNewCount();

	int updateAnchorGuardChangeNew(Anchor_Guard_Change2 anchorguard);

	int inserAnchorGuardChangeNew(Anchor_Guard_Change2 anchorguard);

	int updateGuardChangeOnlineNew(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date date);

	Anchor_Guard_Change2 getAnchorGuardNewByid(@Param("id")int id);

	List<Profit> getGuardRecord(@Param("send_uid")Long send_uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id,
			@Param("type") Integer type,@Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getGuardRecordCount(@Param("send_uid")Long send_uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id,
			@Param("type") Integer type,@Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	
}

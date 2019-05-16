package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Guard_Recharge;

@Repository
public interface Ianchor_Guard_RechargeDao {

	List<Anchor_Guard_Recharge> getGuardRecharge(@Param("uid")long uid,@Param("offset")Integer offset, @Param("rows")Integer rows);

	List<Anchor_Guard_Recharge> getAnchorGuardRecharge(@Param("anchor_f_uuid")String anchor_f_uuid,@Param("offset")Integer offset, @Param("rows")Integer rows);
	
	List<Anchor_Guard_Recharge> getAnchorGuardRechargeList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("pay_type")Integer pay_type, @Param("nickname")String nickname, @Param("o_id")String o_id, @Param("platform")String platform, @Param("channel")String channel, @Param("is_pay")Integer is_pay, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_centerid")Integer operate_centerid, @Param("agent_id")Integer agent_id,@Param("offset")int offset, @Param("rows")int rows);
	
	Integer getAnchorGuardRechargeCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("pay_type")Integer pay_type, @Param("nickname")String nickname, @Param("o_id")String o_id, @Param("platform")String platform, @Param("channel")String channel, @Param("is_pay")Integer is_pay, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_centerid")Integer operate_centerid, @Param("agent_id")Integer agent_id);

	Long getAnchorGuardRechargeSum(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("pay_type")Integer pay_type, @Param("nickname")String nickname, @Param("o_id")String o_id, @Param("platform")String platform, @Param("channel")String channel, @Param("is_pay")Integer is_pay, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_centerid")Integer operate_centerid, @Param("agent_id")Integer agent_id);

	int getGuardRechargeCount(@Param("id")long id);

	int getAnchorGuardRechargeCount2(@Param("id")long id);
}

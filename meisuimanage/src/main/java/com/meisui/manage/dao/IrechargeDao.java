package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Balance_virtualrecord;
import com.meisui.manage.entity.Recharge;
import com.meisui.manage.entity.RechargeStatistics;

/**
 * <p>文件名称：IrechargeDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 */
@Repository
public interface IrechargeDao {
	List<Recharge> getRechargeList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("pay_type")Integer pay_type, @Param("nickname")String nickname, @Param("o_id")String o_id, @Param("platform")String platform, @Param("channel")String channel, @Param("is_pay")Integer is_pay, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_centerid")Integer operate_centerid, @Param("agent_id")Integer agent_id,@Param("pay_virtual")Integer pay_virtual,@Param("offset")int offset, @Param("rows")int rows);
	Integer getRechargeCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("pay_type")Integer pay_type, @Param("nickname")String nickname, @Param("o_id")String o_id, @Param("platform")String platform, @Param("channel") String channel, @Param("is_pay")Integer is_pay, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_centerid")Integer operate_centerid,@Param("agent_id")Integer agent_id,@Param("pay_virtual")Integer pay_virtual);
	Integer getRechargeSumWithUid(@Param("uid")Long uid);
    List<Balance_virtualrecord>	getBalance_virtualrecordList(@Param("uid")Long uid,@Param("s_time")String s_time,@Param("e_time")String e_time,@Param("diamondsa")String diamondsa,@Param("diamondsb")String diamondsb,@Param("nickname")String nickname,@Param("offset")int offset, @Param("rows")int rows);
    Integer   getBalance_virtualrecordCount(@Param("uid")Long uid,@Param("s_time")String s_time,@Param("e_time")String e_time,@Param("diamondsa")String diamondsa,@Param("diamondsb")String diamondsb,@Param("nickname")String nickname);  
    List<RechargeStatistics> getStatisticsList(@Param("start_time")Date start_time,@Param("end_time")Date end_time,@Param("statistics_type")Integer statistics_type,@Param("channel")String channel,@Param("platform")Integer platform,@Param("recharge_type")Integer recharge_type,@Param("offset")int offset, @Param("rows")int rows);
    Integer getStatisticsCount(@Param("start_time")Date start_time,@Param("end_time")Date end_time,@Param("statistics_type")Integer statistics_type,@Param("channel")String channel,@Param("platform")Integer platform,@Param("recharge_type")Integer recharge_type);
    RechargeStatistics getStatisticsSummary(@Param("start_time")Date start_time,@Param("end_time")Date end_time,@Param("statistics_type")Integer statistics_type,@Param("channel")String channel,@Param("platform")Integer platform,@Param("recharge_type")Integer recharge_type);
	Long getRechargeByCenterId(@Param("centerId")int centerId, @Param("sTime")String sTime, @Param("eTime")String eTime);
	int getRechargeRmbSumWithAgentId(@Param("agentId")long agentId);
	Integer getRechargeSumWithPromoterid(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	int getRechargeRmbSumWithUid(@Param("uid")long uid,@Param("b_time")String b_time, @Param("e_time")String e_time);
	Long getRechargeByUid(@Param("uid")long uid, @Param("sTime")String sTime, @Param("eTime")String eTime);
	long totalRecharge(@Param("b_time")String b_time, @Param("e_time")String e_time);
	Integer getAllRechargeSumWithPromoterid(@Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	Integer getAllRechargeSumWithPromoterid2(@Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid,@Param("b_time")String b_time, @Param("e_time")String e_time);
	int getRechargeRmbSumWithAgentId2(@Param("agentId")int agentId, @Param("b_time") String b_time,@Param("e_time")String e_time);
	Integer getRechargeSumWithPromoterid2(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid,@Param("b_time") String b_time,@Param("e_time")String e_time);
	int getRechargeRmbSumWithUid2(@Param("uid")long uid);
	List<Recharge> getSumRecharge(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("pay_type")Integer pay_type, @Param("nickname")String nickname, @Param("o_id")String o_id, @Param("platform")String platform, @Param("channel")String channel, @Param("is_pay")Integer is_pay, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("operate_centerid")Integer operate_centerid, @Param("agent_id")Integer agent_id,@Param("pay_virtual")Integer pay_virtual);
}

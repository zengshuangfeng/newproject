package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.GiftSpendingStatistics;
import com.meisui.manage.entity.Profit_Virtual;
import com.meisui.manage.entity.Spending;

/**
 * <p>文件名称：IspendingDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午4:18:00
 */
@Repository
public interface IspendingDao {
	List<Spending> getSpendingList(@Param("gift_id")Integer gift_id, @Param("gift_name")String gift_name, @Param("is_online")Integer is_online, @Param("uid")Long uid, @Param("spending_uid")Long spending_uid,@Param("operate_center_id")Integer operate_center_id,@Param("agent_id")Integer agent_id, @Param("type") Integer type,@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getSpendingCount(@Param("gift_id")Integer gift_id,  @Param("gift_name")String gift_name,@Param("is_online")Integer is_online, @Param("uid")Long uid, @Param("spending_uid")Long spending_uid,@Param("operate_center_id")Integer operate_center_id,@Param("agent_id")Integer agent_id, @Param("type") Integer type, @Param("s_time")Date s_time, @Param("e_time")Date e_time);
	Long getSpendingSum(@Param("spending_uid")Long spending_uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getSpendingSumAll(@Param("spending_uid")Long spending_uid);
	List<Spending> getSpendingListWithSpending_uid(@Param("spending_uid")Long spending_uid, @Param("date") String date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getSpendingCountWithSpending_uid(@Param("spending_uid")Long spending_uid, @Param("date") String date);
	Long getSpendingSumAllWithUid(@Param("uid")Long uid);
	List<GiftSpendingStatistics>  getSpendingStatisticsList(@Param("start_date")Date start_date,@Param("end_date")Date end_date,@Param("type")Integer type,@Param("offset")Integer offset,@Param("rows")Integer rows);
	Integer getGiftSpendingStatisticsCount(@Param("start_date")Date start_date,@Param("end_date")Date end_date,@Param("type")Integer type);
	Long getSpendingStatisticsSummary(@Param("start_date")Date start_time,@Param("end_date")Date end_date,@Param("type")Integer type);
	List<Spending> getSpendingBysid(@Param("id")Long id, @Param("offset")Integer offset, @Param("rows")Integer rows);
	long getTotalSpendingByCenterId(@Param("centerId")int centerId, @Param("start_time")String start_time, @Param("end_time")String end_time);
	Long getSpendingSumWithPromoterid(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	Long getSpendingSum2(@Param("uid")long uid,@Param("b_time")String b_time, @Param("e_time")String e_time);
	long totalGive(@Param("b_time")String b_time, @Param("e_time")String e_time);
	int getTotalSpendingCount(@Param("id") long id);
	Long getAllSpendingSumWithPromoterid(@Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	long totalSpending_virtual(@Param("b_time")String b_time, @Param("e_time")String e_time);
	Long getAllSpendingSumWithPromoterid2(@Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid,@Param("b_time")String b_time, @Param("e_time")String e_time);
	Long getSpendingSumWithPromoterid2( @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid,@Param("b_time") String b_time,@Param("e_time")String e_time);
	List<Spending> getSpendingBysid2(@Param("agent_promoter_id")Integer agent_promoter_id, @Param("offset")Integer offset, @Param("rows")Integer rows);
	int getTotalSpendingCount2(@Param("agent_promoter_id")Integer agent_promoter_id);
	List<Profit_Virtual> getVirtualList(@Param("gift_id")Integer gift_id, @Param("gift_name")String gift_name, @Param("uid")Long uid, @Param("send_uid")Long send_uid,@Param("re_operate_center_id")int re_operate_center_id, @Param("re_agent_id")int re_agent_id,@Param("send_operate_center_id")int send_operate_center_id,@Param("send_agent_id")int send_agent_id,@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	int getVirtualCount(@Param("gift_id")Integer gift_id,  @Param("gift_name")String gift_name,@Param("uid")Long uid, @Param("send_uid")Long send_uid, @Param("re_operate_center_id")int re_operate_center_id, @Param("re_agent_id")int re_agent_id,@Param("send_operate_center_id")int send_operate_center_id,@Param("send_agent_id")int send_agent_id,@Param("s_time")Date s_time, @Param("e_time")Date e_time);
	List<Spending> getGuardRecord(@Param("send_uid")Long send_uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id,
			@Param("type") Integer type,@Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time,@Param("offset")Integer offset, @Param("rows")Integer rows);
	int getGuardRecordCount(@Param("send_uid")Long send_uid, @Param("f_uuid")Long f_uuid, @Param("anchor_f_uuid")Long anchor_f_uuid, @Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id,
			@Param("type") Integer type,@Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Spending> getGuardRecharge(@Param("uid")long uid,@Param("offset")Integer offset, @Param("rows")Integer rows);
	List<Spending> getGuardRecharge2(@Param("uid")long uid,@Param("offset")Integer offset, @Param("rows")Integer rows);
	int getGuardRechargeCount(@Param("uid")long uid);
	int getGuardRechargeCount2(@Param("uid")long uid);
}

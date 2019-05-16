package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Virtual_Total;
import com.meisui.manage.entity.Profit;
import com.meisui.manage.entity.SenderStatistics;

/**
 * <p>文件名称：IprofitDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月3日 下午2:58:23
 */
@Repository
public interface IprofitDao {
	List<Profit> getProfitList(@Param("uid")Long uid, @Param("type")Integer type, @Param("date")String date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getProfitCount(@Param("uid")Long uid, @Param("type")Integer type, @Param("date")String date);
	Integer insertProfit(Profit entity);
	List<Profit> getProfitListWithTime(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getProfitSum(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getSecretAnchorProfitSum(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Anchor_Virtual_Total> getProfitTotalListWithUid(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getGameProfit(@Param("uid")Long uid,@Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<SenderStatistics> getSenderStatisticsList(@Param("uid")Long uid,@Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer getProfitTotalCountWithUid(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getOneProfitSum(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getProfitSumByType(@Param("centerId")int centerId, @Param("type")int type, @Param("b_time")String b_time, @Param("e_time")String e_time);
	List<Profit> getProfitListWithAgentId(@Param("agent_id")int agent_id, @Param("type")Integer type, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getProfitCountWithAgentId(@Param("agent_id")int agent_id, @Param("type")Integer type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getAllProfitSum(@Param("operate_center_id")Integer operate_center_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	long getProfitSumWithAgentId(@Param("agent_id")int agent_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Profit> getCenterProfitList(@Param("type")int type, @Param("b_time")String b_time, @Param("e_time")String e_time, @Param("offset")int offset, @Param("rows")int rows, @Param("centerId")int centerId);
	int getCenterProfitListCount(@Param("type")int type, @Param("b_time")String b_time, @Param("e_time")String e_time, @Param("centerId")int centerId);
	int getProfitSumByTypeAndAgentId(@Param("agentId")int agentId, @Param("type")int type);
	List<Profit> getProfitListWithOperateCenterId(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("type")Integer type, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getProfitCountWithOperateCenterId(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("type")Integer type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Profit> getGiftProfitList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")int agent_id, @Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")int offset, @Param("rows")int rows);
	Integer getGfitProfitListCount(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")int agent_id, @Param("s_time")Date s_time, @Param("e_time")Date e_time);
	long getProfitSumByType2(@Param("type")int type, @Param("b_time")String b_time, @Param("e_time")String e_time);
	int getProfitSumByTypeAndAgentId2(@Param("agentId")int agentId, @Param("type")int type,@Param("b_time")String b_time, @Param("e_time")String e_time);
	Long getProfitByPromoter(@Param("uid")Long uid, @Param("start_time")Date start_time,  @Param("end_time")Date end_time);
}

package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Operate_Center_Settlement;

@Repository
public interface Ioperate_Center_SettlementDao {
	List<Operate_Center_Settlement> getSettlementList(@Param("operate_center_id")Integer operate_center_id, @Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getSettlementCount(@Param("operate_center_id")Integer operate_center_id, @Param("s_time")Date s_time, @Param("e_time")Date e_time);
	Integer getHasSettlement(@Param("operate_center_id")Integer operate_center_id, @Param("settlement_type")Integer settlement_type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Integer> getHasSettlementList(@Param("operate_center_id")int operate_center_id, @Param("type")int type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	void add(@Param("operate_center_id")int operate_center_id, @Param("settlement_type")int settlement_type, @Param("surplus_anchor_virtual")long surplus_anchor_virtual, @Param("divide")int divide, @Param("money_count")double money_count,  @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("date")Date date, @Param("w_name")String w_name);
	Date getLastSettlementTime(@Param("operate_center_id")int operate_center_id, @Param("settlement_type")int settlement_type);
	List<Integer> getHasSettlementOperateCenterId(@Param("settlement_type")Integer settlement_type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
}

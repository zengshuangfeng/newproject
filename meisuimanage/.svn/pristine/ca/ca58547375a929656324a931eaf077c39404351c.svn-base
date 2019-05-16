package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Agent_Settlement;

@Repository
public interface Iagent_SettlementDao {

	List<Integer> getAgentIdList(@Param("type")int type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);

	void add(@Param("agent_id")int agent_id, @Param("operate_center_id")int operate_center_id, @Param("settlement_type")int settlement_type, @Param("virtual_count")int virtual_count, @Param("divide")int divide, @Param("money_count")double money_count,
			@Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("create_time")Date create_time, @Param("w_name")String w_name);

	List<Agent_Settlement> getSettlementList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")int agent_id, @Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")int offset, @Param("rows")int rows, @Param("type")int type);

	int getSettlementCount(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")int agent_id, @Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("type")int type);

	Integer insertSettlement(Agent_Settlement entity);
	
	Date getLastSettlementTime(@Param("agent_id")int agent_id, @Param("settlement_type")int settlement_type);
}

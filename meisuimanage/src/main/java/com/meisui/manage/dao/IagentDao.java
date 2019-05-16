package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Agent;

@Repository
public interface IagentDao {

	List<Integer> getAgenIdstByOperateCenterId(@Param("id")int id);

	Agent getAgentNameByagentid(int agentid);

	String getAgentNameById(@Param("agent_id")int agent_id);

	List<Agent> getAgentListByOperateCenterId(@Param("centerId")int centerId, @Param("agentName")String agentName, @Param("agentId")int agentId, @Param("offset")int offset, @Param("rows")int rows);
	List<Agent> getAllAgentListByOperateCenterId(@Param("agentName")String agentName, @Param("agentId")int agentId,  @Param("operate_center_id")int operate_center_id,@Param("offset")int offset, @Param("rows")int rows);

	
	
	int getAgentListCountByOperateCenterId(@Param("centerId")int centerId, @Param("agentName")String agentName, @Param("agentId")int agentId);
	int getAllAgentListCountByOperateCenterId(@Param("agentName")String agentName, @Param("agentId")int agentId,@Param("operate_center_id")int operate_center_id);
	Agent getAgentByagentid(int agent_id);

	Agent getAgent(@Param("id")int id);

	void insertAgent(Agent agent);

	void updateAgent(Agent agent);

	long getAgentVirtualSum(@Param("operate_center_id")int operate_center_id);
	
	Long getAgentSurplusVirtualSum(@Param("operate_center_id")Integer operate_center_id);

	List<Agent> getAgentSettlementList(@Param("centerIds")List<Integer> centerIds, @Param("agent_id")int agent_id, @Param("offset")int offset, @Param("rows")int rows);

	int getAgentSettlementListCount(@Param("centerIds")List<Integer> centerIds, @Param("agent_id")int agent_id);

	List<Agent> getAgentSettlementList2(@Param("agentIds")List<Integer> agentIds, @Param("type")int type, @Param("agent_id")int agent_id);

	void updateIsForBid(@Param("id")int id, @Param("is_forbid")int is_forbid, @Param("w_name")String w_name);
	Integer getAgentUsernameExist(@Param("username")String username, @Param("id")Integer id);

	List<Agent> getAgentListByCenterId(@Param("centerId")int centerId);
	
	List<Agent> getAgentList(@Param("centerId")int centerId, @Param("agentName")String agentName, @Param("agentId")int agentId);
	
	Integer clearAgent(@Param("id")Integer id, @Param("sub")Long sub);
}

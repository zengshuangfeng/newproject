package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Agent_Promoter;

@Repository
public interface Iagent_PromoterDao {

	Agent_Promoter getProNameByid(int agent_promoter_id);

	int getProIdByUid(long id);

	List<Long> getPromoterIdByCenterId(@Param("centerId")int centerId);

	List<Long> getPromoteIdByAgentId(@Param("agentId")int agentId);
	List<Agent_Promoter> getAgentPromoterList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("uid")Long uid, @Param("remark")String remark, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAgentPromoterCount(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("uid")Long uid, @Param("remark")String remark);

	long getUId(@Param("id")int id);

	int getpromoterByUid(long uid);

	List<Agent_Promoter> getAllAgentPromoterList(@Param("uid")long uid,@Param("n_uid")long n_uid,@Param("operate_center_id")Integer operate_center_id,@Param("agent_id")Integer agent_id, @Param("invite_code") String invite_code,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getAllAgentPromoterCount(@Param("uid")long uid,@Param("n_uid")long n_uid,@Param("operate_center_id")Integer operate_center_id,@Param("agent_id")Integer agent_id, @Param("invite_code") String invite_code);

	List<Agent_Promoter> getAllAgentPromoter();

	List<Agent_Promoter> getAgentList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("uid")Long uid, @Param("remark")String remark);

	List<Agent_Promoter> getAgentPromoterFindUid();

}

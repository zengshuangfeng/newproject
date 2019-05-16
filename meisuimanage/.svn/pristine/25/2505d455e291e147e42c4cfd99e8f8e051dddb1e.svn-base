package com.meisui.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Agent;

@Repository
public interface Iagent_AdminDao {

	void updateAgent(Agent agent);

	int getAdminByUsername(@Param("username")String username);

	void deleteUserBinding(@Param("agent_admin_id")int agent_admin_id);

}

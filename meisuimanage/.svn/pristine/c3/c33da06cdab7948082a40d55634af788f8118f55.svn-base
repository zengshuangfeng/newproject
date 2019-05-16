package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Exchange_Virtual;

@Repository
public interface IexchangeVirtualDao {


	int getExchangeVirtual(@Param("operate_center_id")int operate_center_id, @Param("agent_id")int agent_id, @Param("start_time")Date start_time,@Param("end_time") Date end_time);

	List<Exchange_Virtual> getExchangeVirtualList(@Param("operate_center_id")Integer operate_center_id,@Param("agent_id")Integer agent_id,@Param("f_uuid")Long f_uuid, @Param("nickname")String nickname,@Param("start_time") Date start_time, @Param("end_time")Date end_time,
			 @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getExchangeVirtualCount(@Param("operate_center_id")Integer operate_center_id,@Param("agent_id")Integer agent_id,@Param("f_uuid")Long f_uuid, @Param("nickname")String nickname,@Param("start_time") Date start_time, @Param("end_time")Date end_time);

	Long getExchangeVirtualByUid(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("f_uuid")Long f_uuid,@Param("start_time")Date start_time,@Param("end_time") Date end_time);

}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Recharge_Virtual;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Virtual_Record;


@Repository
public interface Ivirtual_AccountDao {

	List<Recharge_Virtual> getRechargeVirtualList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid,@Param("operate_centerid")Integer operate_centerid,  @Param("b_time")String b_time, @Param("e_time")String e_time,  @Param("offset")int offset, @Param("rows")int rows);

	int getRechargeVirtualListCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid,@Param("operate_centerid")Integer operate_centerid, @Param("b_time")String b_time, @Param("e_time")String e_time);

	List<User_Virtual_Record> getUserVirtualRecordAll( @Param("b_time") String b_time,@Param("e_time")String e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getUserVirtualRecordAllCount( @Param("b_time") String b_time,@Param("e_time")String e_time);

	List<Operate_Center> getOperateCenterList(@Param("operate_center_id")int operate_center_id, @Param("operate_name")String operate_name, @Param("offset")int offset, @Param("rows")int rows);

	int getOperateCenterCount(@Param("operate_center_id")int operate_center_id, @Param("operate_name")String operate_name);
	
}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Recharge_Quota_Virtual;

@Repository
public interface IRechargeQuotaDao {

	void insertRecord(Recharge_Quota_Virtual recharge_quota_virtual);

	Long totalVirtualCount(int centerid);

	List<Recharge_Quota_Virtual> getRechargeQuotaVirtualList( @Param("b_time")String b_time, @Param("e_time")String e_time, @Param("offset")int offset, @Param("rows")int rows, @Param("centerId")int centerId);

	int getRechargeQuotaVirtualListCount( @Param("b_time")String b_time, @Param("e_time")String e_time, @Param("centerId")int centerId);


}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Province_Center;

@Repository
public interface Ioperate_CenterDao {

	List<Operate_Center> getList(@Param("province_center_id")Integer province_center_id,@Param("offset")int offset, @Param("rows")int rows);

	Operate_Center getoperateName(int operateid);
	
	int getListCount();

	int save(Operate_Center center);

	Operate_Center getOperateCenterById(@Param("centerId")int centerId);

	List<Operate_Center> getOperateCenter();
	
	void update(Operate_Center center);

	String getPasswordByCenterId(@Param("centerId")int centerId);

	List<Operate_Center> getListAll();

	void updateIsForbid(@Param("id")int id, @Param("is_forbid")int is_forbid);

	List<Operate_Center> getList2(@Param("centerId")int centerId, @Param("type")int type, @Param("settlement_id_list")List<Integer> settlement_id_list, @Param("offset")int offset, @Param("rows")int rows);

	int getListCount2(@Param("centerId")int centerId, @Param("type")int type, @Param("settlement_id_list")List<Integer> settlement_id_list);

	List<Operate_Center> getList3(@Param("centerId")int centerId, @Param("type")int type, @Param("settlement_id_list")List<Integer> settlement_id_list);
	
	List<Integer> getIdList(@Param("settlement_type")int settlement_type, @Param("id")Integer id);

	Integer getOperateUsernameExist(@Param("username")String username, @Param("id")Integer id);
	
	Integer updateRechargeQuota(@Param("id")Integer id, @Param("add")Long add);

	Province_Center getProviceCenter(@Param("province_center_id")Integer province_center_id);

	List<Province_Center> getProvinceListAll();

	List<Operate_Center> getListAllByprovinceid(@Param("province_center_id")Integer province_center_id);
}

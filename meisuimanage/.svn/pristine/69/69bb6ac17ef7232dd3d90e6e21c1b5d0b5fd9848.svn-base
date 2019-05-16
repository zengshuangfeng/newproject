package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Province_Center;

@Repository
public interface IProvince_CenterDao {

	List<Province_Center> getProviceCenterList(@Param("province_center_name")String province_center_name, @Param("province_center_id")Integer province_center_id, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getProviceCenterCount(@Param("province_center_name")String province_center_name, @Param("province_center_id")Integer province_center_id);

	Integer getOperateUsernameExist(@Param("username")String username, @Param("id")Integer id);

	Province_Center getOperateCenterById(@Param("province_center_id")Integer province_center_id);

	String getPasswordByCenterId(@Param("id")Integer id);

	void update(Province_Center center);

	void save(Province_Center center);

	void updateProviceIsonline(@Param("id")Integer id, @Param("is_forbid")Integer is_forbid);

	List<Province_Center> getProvinceCenterList();

}

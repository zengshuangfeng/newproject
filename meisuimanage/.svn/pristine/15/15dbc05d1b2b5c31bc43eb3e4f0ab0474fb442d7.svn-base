package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Vehicle;

@Repository
public interface VehicleDao {

	List<Vehicle> getVehicleList(@Param("id")Integer id,@Param("name") String name,@Param("is_online") Integer is_online,@Param("level") Integer level,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getVehicleCount(@Param("id")Integer id,@Param("name") String name,@Param("is_online") Integer is_online,@Param("level") Integer level);

	int updateVehicle(Vehicle vehicle);

	int insertVehiclen(Vehicle vehicle);

	Vehicle getVehicleById(@Param("id")Integer id);

	int deleteVehicle(@Param("id")int id);

}

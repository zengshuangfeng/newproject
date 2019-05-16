package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User;

@Repository
public interface IRechargeBalanceVirtualDao {
	List<User> getBalanceVirtualUserList(@Param("uid")long uid, @Param("f_uuid")String f_uuid, @Param("nickname")String nickname,@Param("offset")Integer offset, @Param("rows")Integer rows);
	int getBalanceVirtualUserCount(@Param("uid")long uid, @Param("f_uuid")String f_uuid, @Param("nickname")String nickname);
	int updaterechargebalance_virtual(@Param("balance_virtual")int balance_virtual,@Param("id") long id);
	User getUserByid(@Param("uid")long uid);

}

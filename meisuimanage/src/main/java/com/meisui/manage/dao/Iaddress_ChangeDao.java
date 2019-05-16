package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.AddressChange;

@Repository
public interface Iaddress_ChangeDao {
	/**
	 * 查询all归属变更记录
	 * */
	List<AddressChange> getAlladdressChangeList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getaddressChangeCount();
	AddressChange getUserBYFuid(String fuuid);
	AddressChange getAgentUserBYFuid(String fuuid);
	void saveAddressChange(AddressChange addresschange);
	void updateUser(AddressChange addresschange);
}

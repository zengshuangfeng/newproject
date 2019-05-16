package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.MengYan;

@Repository
public interface ImengYanDao {

	List<MengYan> getMengYanList(@Param("s_time")Date s_time,@Param("e_time") Date e_time,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getMengYanListCount(@Param("s_time")Date s_time,@Param("e_time") Date e_time);

	Integer updatemengyan(MengYan mengyan);

	Integer insertmengyan(MengYan mengyan);

	MengYan getMengYan(@Param("id")int id);

	int deleteMengyan(@Param("id")int id);

}

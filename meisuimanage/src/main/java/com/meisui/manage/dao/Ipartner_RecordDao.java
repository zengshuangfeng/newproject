package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Partner_Record;

@Repository
public interface Ipartner_RecordDao {
	Integer getWaringCount(@Param("uid")Long uid, @Param("time") Integer time);
	Integer insertPartnerRecord(Partner_Record entity);
	Integer updatePartnerRecord(Partner_Record entity);
	Partner_Record getLastWaring(@Param("uid")Long uid, @Param("f_uuid") Long f_uuid);
	List<Partner_Record> getPartnerRecordList(@Param("type")Integer type, @Param("uid")Long uid, @Param("f_uuid") Long f_uuid, @Param("w_name")String w_name, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getPartnerRecordCount(@Param("type")Integer type, @Param("uid")Long uid, @Param("f_uuid") Long f_uuid, @Param("w_name")String w_name);
}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.AuditRecords;

@Repository
public interface IauditRecordDao {

	int saveAuditRecord(AuditRecords auditRecord);
	
	List<AuditRecords> getAuditRecordList(@Param("authentication_id") int authentication_id,@Param("offset")Integer offset, @Param("rows")Integer rows);
	
	Integer getAuditRecordsCount(int authentication_id);

	AuditRecords getAuditRecod(@Param("authentication_id")Integer authentication_id);
}

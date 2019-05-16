package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Virtual_Change_Record;

@Repository
public interface Ianchor_Virtual_Change_RecordDao {
	List<Anchor_Virtual_Change_Record> getAnchorVirtualChangeRecordList(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorVirtualChangeRecordCount(@Param("uid")Long uid);
	Integer insertAnchorVirtualChangeRecord(Anchor_Virtual_Change_Record entity);
	Long getAnchorVirtualChangeSum(@Param("uid")Long uid);
	List<Anchor_Virtual_Change_Record> getAnchorVirtualChangeRecordList2(@Param("create_time")Date create_time);
	String getLastDate();
}

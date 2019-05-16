package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Manage_Record;

/**
 * <p>文件名称：IactionDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年6月7日 下午6:04:22
 */
@Repository
public interface Imanage_RecordDao {
    Integer insertManageRecord(@Param("w_name")String w_name,@Param("action")String action,@Param("table_name")String table_name,@Param("t_id")Integer t_id,@Param("ip")String ip,@Param("create_time")Date create_time);
	List<Manage_Record> getManageRecordList(@Param("s")String s, @Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getManageRecordCount(@Param("s")String s, @Param("s_time")Date s_time, @Param("e_time")Date e_time);
}

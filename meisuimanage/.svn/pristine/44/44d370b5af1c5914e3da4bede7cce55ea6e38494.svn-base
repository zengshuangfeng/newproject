package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Gameroom_Record;
import com.meisui.manage.entity.Gameroom_Record_Total;

/**
 * <p>文件名称：Igameroom_RecordDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午2:34:09
 */
@Repository
public interface Igameroom_RecordDao {
	List<Gameroom_Record_Total> getGameroomRecordTotalList(@Param("start_time")Date start_time, @Param("anchor_state")Integer anchor_state, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getGameroomRecordTotalCount(@Param("start_time")Date start_time, @Param("anchor_state")Integer anchor_state);
	Integer updateGameroomRecordTotalIntervene(@Param("id")Integer id);
	List<Gameroom_Record> getGameroomRecordListWithF_uuid(@Param("f_uuid")Long f_uuid, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getGameroomRecordCountWithF_uuid(@Param("f_uuid")Long f_uuid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getTotalBetCountSum(@Param("create_time")Date create_time);
	Long getCurrentVirtualCountSum();
	Long getTotalBetCountSumWithF_uuid(@Param("f_uuid")Long f_uuid, @Param("create_time")Date create_time);
	Long getCurrentVirtualCountSumWithF_uuid(@Param("f_uuid")Long f_uuid, @Param("create_time")Date create_time);
}

package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.AdmissionTicket;
import com.meisui.manage.entity.Anchor_Time;

/**
 * <p>文件名称：Ianchor_TimeDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月14日 下午4:41:14
 */
@Repository
public interface Ianchor_TimeDao {
	List<Anchor_Time> getAnchorTimeList(@Param("uid")Long uid, @Param("type")Integer type, @Param("start_date")Date start_date, @Param("end_date")Date end_date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorTimeCount(@Param("uid")Long uid, @Param("type")Integer type, @Param("date")Date date);
	List<Anchor_Time> getAnchorTimeListWithTime(@Param("uid")Long uid, @Param("start_date")Date start_date, @Param("end_date")Date end_date);
	Date getAnchorTimeWithTime(@Param("uid")Long uid, @Param("end_date")Date end_date);
	List<Anchor_Time> getAnchorTimeListWithDate(@Param("start_date")Date start_date, @Param("end_date")Date end_date);
	List<Anchor_Time> getAnchorTimeList2(@Param("uid")Long uid, @Param("type")Integer type, @Param("start_date")Date start_date, @Param("end_date")Date end_date);
	Anchor_Time getFirstAnchorTime(@Param("uid")Long uid, @Param("type")Integer type);
    List<AdmissionTicket> getAnchorsAdmissionTicketList(@Param("nickname")String nickname,@Param("uid")Long uid,@Param("start_time")Date start_time,@Param("end_time")Date end_time,@Param("offset")Integer offset,@Param("rows")Integer rows);
    Integer getAnchorsAdmissionTicketCount(@Param("nickname")String nickname,@Param("uid")Long uid,@Param("start_time")Date start_time,@Param("end_time")Date end_time);
    Integer updateFee_count(@Param("uid")Long uid,@Param("fee_count")int fee_count);
}

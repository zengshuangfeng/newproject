package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Report;

/**
 * <p>文件名称：IreportDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 下午2:45:46
 */
@Repository
public interface IreportDao {
	List<Report> getAnchorReportList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("o_uid")Long o_uid, @Param("o_nickname")String o_nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorReportCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("o_uid")Long o_uid, @Param("o_nickname")String o_nickname);
	List<Report> getUserReportList(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserReportCount(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer updateReportIsLook(@Param("id")Integer id, @Param("islook")Integer islook, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

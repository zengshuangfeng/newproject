package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Notice;

@Repository
public interface InoticeDao {

	List<Notice> list(@Param("begin_time")String begin_time, @Param("end_time")String end_time, @Param("type")int type, @Param("state")int state, @Param("platform")String platform, @Param("offset")int offset, @Param("rows")int rows, @Param("now")Date now);

	int listCount(@Param("begin_time")String begin_time, @Param("end_time")String end_time, @Param("type")int type, @Param("state")int state, @Param("platform")String platform, @Param("now")Date now);

	Notice getNoticeById(@Param("id")int id);

	void save(Notice notice);

	void update(Notice notice);

	int delete(@Param("id")int id);

}

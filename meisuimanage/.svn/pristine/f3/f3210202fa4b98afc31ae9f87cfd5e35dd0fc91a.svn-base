package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Gift_Box;
import com.meisui.manage.entity.Gift_Box_Record;
import com.meisui.manage.entity.Gift_Info;


@Repository
public interface Igift_BoxDao {

	List<Gift_Box> getGiftBoxList(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int deleteGiftBox(@Param("id")Integer id,@Param("count")Integer count,@Param("w_name")String w_name,@Param("update_time")Date  update_time);

	List<Gift_Box_Record> getGiftBoxRecordList(@Param("uid")Integer uid, @Param("gift_id")Integer gift_id,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getGiftBoxRecordCount(@Param("uid")Integer uid, @Param("gift_id")Integer gift_id);

	int deleteGiftBoxList(@Param("id")Integer id);

	Gift_Box getGiftBoxByid(@Param("id")Integer id);

	void insertGiftBoxRecord(@Param("uid")Integer uid, @Param("count")Integer count, @Param("gift_id")Integer gift_id,@Param("box_type")Integer box_type ,@Param("w_name")String w_name);

	List<Gift_Box_Record> getOperateLogList( @Param("b_time") String b_time,@Param("e_time")String e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getOperateLogListCount( @Param("b_time") String b_time,@Param("e_time")String e_time);

	List<Gift_Info> getAllGiftInfo();

	Gift_Box getGiftBoxByUidAndGiftid(@Param("uid")Integer uid,@Param("gift_id")Integer gift_id);

	int updateGiftBox(Gift_Box gift_box);

	int insertGiftBox(Gift_Box gift_box);

	void inserGiftBoxRecord(Gift_Box_Record gift_box_record);

	int getGiftBoxListCount(@Param("uid")Long uid);

	
	
	
	
}

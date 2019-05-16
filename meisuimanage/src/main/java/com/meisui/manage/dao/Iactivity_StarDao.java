package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Activity_Star_Anchor;
import com.meisui.manage.entity.Activity_Star_Competition;
import com.meisui.manage.entity.Activity_Star_Grading_Rule;
import com.meisui.manage.entity.Activity_Star_Manage_Record;

@Repository
public interface Iactivity_StarDao {

	List<Activity_Star_Anchor> getActivityStartList(@Param("uid")Integer uid, @Param("nickname")String nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getActivityStartCount(@Param("uid")Integer uid, @Param("nickname")String nickname);

	int updateactivitystaranchor(@Param("id")Integer id, @Param("addscore")Integer addscore,@Param("grading")Integer grading);

	void insertstarrecord(Activity_Star_Manage_Record activitystarrecord);

	List<Activity_Star_Competition> getPKRecord(@Param("uid")Integer uid,  @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getPKRecordCount(@Param("uid")Integer uid);

	Activity_Star_Anchor getstaranchorbyid(@Param("id")Integer id);

	int updateCancel(@Param("id")Integer id,@Param("w_name")String w_name,@Param("date")Date date);

	Integer getStarCompetition(@Param("uid")Integer uid,@Param("date") Date date);

	int updateStartAnchorCancel(@Param("id")Integer id);

	void updateAnchorScore(@Param("id")Integer id,@Param("w_name")String w_name,@Param("date")Date date);

	int updatecompetitiontime(@Param("id")Integer id, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("update_time")Date date, @Param("w_name")String w_name);

	Activity_Star_Grading_Rule getactivitygrading(@Param("grading")Integer grading);

	int getactivityscore(@Param("grading") int grading);

	List<Activity_Star_Grading_Rule> getactivitygradingrule();

}

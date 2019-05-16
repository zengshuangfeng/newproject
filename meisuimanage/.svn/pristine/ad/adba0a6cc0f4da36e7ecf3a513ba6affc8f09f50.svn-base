package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Activity_Annual;
import com.meisui.manage.entity.Activity_Annual_Discount;
import com.meisui.manage.entity.Activity_Annual_Login;
import com.meisui.manage.entity.Activity_Annual_Prize;
import com.meisui.manage.entity.Activity_Annual_Prize_User;
import com.meisui.manage.entity.Balance_virtualrecord;
import com.meisui.manage.entity.Post;
import com.meisui.manage.entity.Recharge;
@Repository
public interface Iactivity_AnnualrDao {

	List<Activity_Annual_Login> getActivityAnnualList(@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getActivityAnnualCount(@Param("s_time")Date s_time, @Param("e_time")Date e_time);

	List<Activity_Annual_Prize_User> getActivityPrizeUser(@Param("s_time")Date s_time, @Param("e_time")Date e_time,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getActivityPrizeUserCount(@Param("s_time")Date s_time, @Param("e_time")Date e_time);

	Activity_Annual_Prize getActivityAnnualPrize(@Param("prize_id")Integer prize_id);

	int getAllRecharge(@Param("s_time")String statetime, @Param("e_time")String endtime);

	int getAllBalanceVirtual(@Param("s_time")String statetime, @Param("e_time")String endtime);

	int getAllPrizecount(@Param("s_time")String statetime, @Param("e_time")String endtime,@Param("prize_id")Integer prize_id);

	List<Activity_Annual> getActivityAnnual(@Param("activity_time")String activity_time);

	Activity_Annual getActivityEndtime(@Param("wheel")Integer wheel,@Param("activity_time")Date activity_time);

	List<Activity_Annual_Prize_User> getAnnual_Prize_User(@Param("activity_id")int activity_id,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getgetAnnual_Prize_UserCount(@Param("activity_id")int activity_id);

	int saveRemak(@Param("id")Integer id, @Param("remark")String remark,@Param("w_name")String w_name);

	Activity_Annual_Discount getActivityAnnualDiscount(@Param("uid")Integer uid);

	Recharge getRechargeLimitone(@Param("create_time")Date create_time, @Param("uid")Integer uid);

	int updatePrizeUser(@Param("id")Integer id, @Param("is_take")Integer is_take,@Param("w_name")String w_name);

	void updateIstake(@Param("is_take")Integer is_take,@Param("id") Integer id);

	Activity_Annual getActivityByid(@Param("activity_id")Integer activity_id);


}

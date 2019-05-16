package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Apply;
import com.meisui.manage.entity.User_Real;

/**
 * <p>文件名称：IapplyDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午2:14:38
 */
@Repository
public interface IapplyDao {
	List<Apply> getApplyList(@Param("is_contact")Integer is_contact, @Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("qq")String qq, @Param("phone")String phone, @Param("remark")String remark, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getApplyCount(@Param("is_contact")Integer is_contact, @Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("qq")String qq, @Param("phone")String phone, @Param("remark")String remark, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer updateApply(Apply entity);
	String getApplyHead(@Param("id")int id);
	User_Real getUserReal(@Param("uid")Long uid);
	List<Apply> getApplyList2(@Param("is_contact")Integer is_contact, @Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("qq")String qq, @Param("phone")String phone, @Param("remark")String remark, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	String getTel(@Param("uid")long uid);
	String getRealName(@Param("uid")long uid);
}

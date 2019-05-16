package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User_Push;

/**
 * <p>文件名称：Iuser_PushDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月17日 下午4:30:55
 */
@Repository
public interface Iuser_PushDao {
	Integer insertUser_Push(User_Push entity);
	Integer updateUserPush(User_Push entity);
	Integer deleteUserPush(@Param("id")Integer id, @Param("w_name")String w_name);
	User_Push getUserPush(@Param("id")Integer id);	
	List<User_Push> getUserPushList( @Param("platform")String platform,@Param("s_time")Date s_time, @Param("e_time")Date e_time,  @Param("offset")int offset, @Param("rows")int rows);
	Integer getUserPushCount( @Param("platform")String platform,@Param("s_time")Date s_time, @Param("e_time")Date e_time);
	Integer insertUserPushBox(@Param("p_id")Integer p_id, @Param("push_time")Date push_time, @Param("islook")Integer islook, @Param("token")String token);
	int repushUserPush(@Param("id")Integer id,@Param("is_push")Integer is_push, @Param("w_name")String w_name);
}

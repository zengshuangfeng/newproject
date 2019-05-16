package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User_Task;

/**
 * <p>文件名称：Iuser_TaskDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午10:27:22
 */
@Repository
public interface Iuser_TaskDao {
	List<User_Task> getUserTaskList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserTaskCount();
	Integer insertUserTask(User_Task entity);
	Integer updateUserTask(User_Task entity);
	User_Task getUserTask(@Param("id")Integer id);
	Integer deleteUserTask(@Param("id")Integer id, @Param("w_name")String w_name);
}

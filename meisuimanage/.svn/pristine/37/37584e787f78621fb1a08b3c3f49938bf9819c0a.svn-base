package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User_Forbid;

/**
 * <p>文件名称：Iuser_ForbidDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午6:06:46
 */
@Repository
public interface Iuser_ForbidDao {
	List<User_Forbid> getUserForbidList(@Param("f_uuid")Long f_uuid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserForbidCount(@Param("f_uuid")Long f_uuid);
}

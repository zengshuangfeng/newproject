package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Top_Guard;

/**
 * <p>文件名称：Itop_GuardDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月29日 上午9:52:33
 */
@Repository
public interface Itop_GuardDao {
	List<Top_Guard> getTopGuardList(@Param("f_uuid")Long f_uuid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getTopGuardCount(@Param("f_uuid")Long f_uuid);
}

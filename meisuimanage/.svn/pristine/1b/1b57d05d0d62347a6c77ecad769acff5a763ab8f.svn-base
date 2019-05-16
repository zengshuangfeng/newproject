package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Version_Update;

/**
 * <p>文件名称：Iversion_UpdateDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月18日 上午10:04:14
 */
@Repository
public interface Iversion_UpdateDao {
	List<Version_Update> getVersionUpdateList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getVersionUpdateCount();
	Integer insertVersionUpdate(Version_Update entity);
	Integer updateVersionUpdateOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date update_time);
}

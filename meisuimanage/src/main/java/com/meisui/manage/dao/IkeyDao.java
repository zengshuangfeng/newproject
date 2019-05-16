package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Key;

/**
 * <p>文件名称：IkeyDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月15日 下午4:38:26
 */
@Repository
public interface IkeyDao {
	Integer insertKey(@Param("uid")Long uid, @Param("type")Integer type, @Param("key_count")Integer key_count, @Param("create_time")Date create_time, @Param("remark")String remark);
	List<Key> getKeyList(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getKeyCount(@Param("uid")Long uid);
}

package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Attention;

/**
 * <p>文件名称：IattentionDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月29日 上午9:53:39
 */
@Repository
public interface IattentionDao {
	List<Attention> getAttentionList(@Param("attention_uid")Long attention_uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAttentionCount(@Param("attention_uid")Long attention_uid);
	List<Attention> getAttentionListWithUid(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAttentionCountWithUid(@Param("uid")Long uid);
}

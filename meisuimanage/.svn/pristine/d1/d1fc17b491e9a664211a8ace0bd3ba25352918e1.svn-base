package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Total_Flowing;

/**
 * <p>文件名称：Itotal_FlowingDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月9日 下午4:30:47
 */
@Repository
public interface Itotal_FlowingDao {
	List<Total_Flowing> getTotalFlowingList(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getTotalFlowingCount(@Param("uid")Long uid);
}

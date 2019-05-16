package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Union;

/**
 * <p>文件名称：Ianchor_UnionDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月7日 下午2:35:01
 */
@Repository
public interface Ianchor_UnionDao {
	List<Anchor_Union> getAnchorUnionList(@Param("name")String name, @Param("remark")String remark, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorUnionCount(@Param("name")String name, @Param("remark")String remark);
	Integer insertAnchorUnion(Anchor_Union entity);
	Integer updateAnchorUnion(Anchor_Union entity);
	Anchor_Union getAnchorUnion(@Param("id")Integer id);
	Integer getAnchorUnionDivideProportion(@Param("id")Integer id);
	List<Anchor_Union> getAllAnchorUnionList();//获取所有工会列表
	List<Anchor_Union> getWeekSettleAnchorUnionList();//获取周结算工会列表
	List<Anchor_Union> getDaySettleAnchorUnionList();//获取日结算工会列表
	Integer deleteAnchorUnion(@Param("id")Integer id, @Param("update_time")Date update_time, @Param("w_name")String w_name);
	Integer getAnchorUnionId(@Param("id")Integer id, @Param("username")String username);
	Integer getAnchorUnionOneDivideProportion(@Param("id")Integer id);
}

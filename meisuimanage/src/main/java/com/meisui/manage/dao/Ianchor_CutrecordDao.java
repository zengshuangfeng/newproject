package com.meisui.manage.dao;

import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Cutrecord;

/**
 * <p>文件名称：Ianchor_CutrecordDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月9日 下午3:55:25
 */
@Repository
public interface Ianchor_CutrecordDao {
	Integer insertAnchorCutrecord(Anchor_Cutrecord entity);
}

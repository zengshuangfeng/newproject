package com.meisui.manage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Game_Capital;

/**
 * <p>文件名称：Igame_CapitalDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 上午11:20:51
 */
@Repository
public interface Igame_CapitalDao {
	List<Game_Capital> getGameCapitalList();
	Game_Capital getGameCapital(@Param("sign")Integer sign);
	Integer insertGameCapital(Game_Capital entity);
	Integer updateGameCapital(Game_Capital entity);
}

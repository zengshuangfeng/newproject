package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Level_Score;

/**
 * <p>文件名称：Ilevel_ScoreDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午4:47:39
 */
@Repository
public interface Ilevel_ScoreDao {
	List<Level_Score> getLevelScoreList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getLevelScoreCount();
	Integer insertLevelScore(Level_Score entity);
	Integer updateLevelScore(Level_Score entity);
	Integer deleteLevelScore(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Level_Score getLevelScore(@Param("id")Integer id);
}

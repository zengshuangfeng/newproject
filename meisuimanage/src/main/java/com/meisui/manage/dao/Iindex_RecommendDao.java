package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Index_Recommend;


/**
 * <p>文件名称：Iindex_RecommendDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月12日 下午4:42:20
 */
@Repository
public interface Iindex_RecommendDao {
	List<Index_Recommend> getIndexRecommendList(@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("style")int style, @Param("style2")int style2, @Param("url")String url, @Param("status")int status, @Param("platform")String platform, @Param("s")String s, @Param("offset")int offset, @Param("rows")int rows);
	Integer getIndexRecommendCount(@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("style")int style, @Param("style2")int style2, @Param("url")String url, @Param("status")int status, @Param("platform")String platform, @Param("s")String s);
	Index_Recommend getIndexRecommend(@Param("id")int id);
	Integer updateIndexRecommend(Index_Recommend iiIndex_recommend);
	Integer insertIndexRecommend(Index_Recommend entity);
	Integer deleteIndexRecommend(@Param("id")int id, @Param("w_name")String w_name); 
	Integer updateIndexRecommendPosition(@Param("id")int id,@Param("position")int position, @Param("w_name")String w_name,@Param("update_time")Date update_time);
}

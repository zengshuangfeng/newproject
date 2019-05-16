package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Recommend;

@Repository
public interface Ianchor_RecommendDao {

	List<Anchor_Recommend> getAnchorRecommendList(@Param("f_uuid")String f_uuid, @Param("nickname")String nickname,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getAnchorRecommendCount(@Param("f_uuid")String f_uuid, @Param("nickname")String nickname);

	int deleteAnchorRecommend(@Param("id")Integer id);

	Integer getAnchorRecommendbyuid(@Param("f_uuid")String f_uuid);

	int updateAnchorRecommend(Anchor_Recommend anchorrecommend);

	int insertAnchorRecommend(Anchor_Recommend anchorrecommend);

	Anchor_Recommend getAnchorRecommend(@Param("id")Integer id);


}

package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Community_Recommend;
import com.meisui.manage.entity.Post;
import com.meisui.manage.entity.Post_Comment;
import com.meisui.manage.entity.Post_Pic;
import com.meisui.manage.entity.Post_Tag;

@Repository
public interface IcommunityDao {

	List<Community_Recommend> getCommunityList(@Param("uid") int uid, @Param("nickname") String nickname,
			@Param("type") int type, @Param("start_time") String start_time, @Param("end_time") String end_time,
			@Param("offset") Integer offset, @Param("rows") Integer rows);

	int getCommunityRecommendRecordCount(@Param("uid") int uid, @Param("nickname") String nickname,
			@Param("type") int type, @Param("start_time") String start_time, @Param("end_time") String end_time);

	int deletePopularAnchor(@Param("id") Integer id, @Param("w_name") String w_name,
			@Param("update_time") Date update_time);

	int updatePopularAnchor(Community_Recommend communityrecommend);

	int insertPopularAnchor(Community_Recommend communityrecommend);

	Community_Recommend getCommunityByid(@Param("id") int id);

	int deleteHotlive(@Param("id") Integer id, @Param("w_name") String w_name, @Param("update_time") Date update_time);

	int updatHotLive(Community_Recommend communityrecommend);

	int insertHotLive(Community_Recommend communityrecommend);

	List<Post> getPostList(@Param("uid") long uid, @Param("f_uuid") long f_uuid, @Param("type") Integer type,
			@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("title") String title,
			@Param("offset") Integer offset, @Param("rows") Integer rows);

	int getPostRecordCount(@Param("uid") long uid, @Param("f_uuid") long f_uuid, @Param("type") Integer type,
			@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("title") String title);

	List<Post_Pic> getPostListBypostid(@Param("post_id") Integer post_id);

	int updatePicManager(Post post);

	int insertPicManager(Post post);

	Post getPostByid(@Param("id") Integer id);

	List<String> selectPicNameList(@Param("post_id") Integer post_id);

	void addPostPic(@Param("post_id") Integer post_id, @Param("p_name") String p_name, @Param("sort") Integer sort,
			@Param("width") Integer width, @Param("height") Integer height);

	List<Post_Pic> getPostPicListBySpId(@Param("post_id") Integer post_id);

	void deletePic(@Param("id") Integer id);

	int PostIsOnline(@Param("id") Integer id, @Param("is_online") Integer is_online, @Param("w_name") String w_name,
			@Param("update_time") Date update_time);

	int PostDelete(@Param("id") Integer id, @Param("w_name") String w_name, @Param("update_time") Date update_time);

	List<Post_Comment> getPostCommentList(@Param("post_id") long post_id, @Param("uid") long uid,
			@Param("nickname") String nickname, @Param("comment") String comment,
			@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("offset") Integer offset,
			@Param("rows") Integer rows);

	int getPostCommentRecordCount(@Param("post_id") long post_id, @Param("uid") long uid,
			@Param("nickname") String nickname, @Param("comment") String comment,
			@Param("start_time") String start_time, @Param("end_time") String end_time);

	int CommentDelete(@Param("id") Integer id, @Param("w_name") String w_name, @Param("update_time") Date update_time);

	int topComment(@Param("id") Integer id, @Param("is_top") Integer is_top, @Param("w_name") String w_name,
			@Param("update_time") Date update_time);

	int insertAddPicmanagerComment(Post_Comment post_comment);

	int updateVideoManager(Post post);

	int insertVideoManager(Post post);

	Post_Pic getPostPicSortBypostId(@Param("post_id") Integer post_id);

	Integer getcommunityrecommendbyuid(@Param("uid") long uid, @Param("type") int type);

	List<Post_Comment> getPostCommentManagerList(@Param("uid") long uid, @Param("nickname") String nickname,
			@Param("comment") String comment, @Param("start_time") String start_time,
			@Param("end_time") String end_time, @Param("offset") Integer offset, @Param("rows") Integer rows);

	int getPostCommentManagerRecordCount(@Param("uid") long uid, @Param("nickname") String nickname,
			@Param("comment") String comment, @Param("start_time") String start_time,
			@Param("end_time") String end_time);

	Integer getIstopexist(@Param("post_id") Integer post_id);

	Integer updatePicSort(@Param("post_id") Integer post_id, @Param("pic") String pic, @Param("sort") Integer sort);

	void updateCommentCount(@Param("post_id")Integer post_id,@Param("count")Integer count);

	int getpostcomentByid(@Param("id")Integer id);

	List<Post_Tag> getTagList(@Param("name")String name,  @Param("offset") Integer offset, @Param("rows") Integer rows);

	int getTagRecordCount(@Param("name")String name);

	Post_Tag getPostTagByid(@Param("id")Integer id);

	int updateTagManager(Post_Tag post_tag);

	int insertTagManager(Post_Tag post_tag);

	int deleteTag(@Param("id")Integer id,@Param("w_name") String w_name, @Param("update_time")Date update_time);

	List<Post_Tag> getPostTag();

	int getPostCommentCount(@Param("post_id")Integer post_id);

	int getPostCount(@Param("uid")Integer uid);

	int topPost(@Param("id")int id, @Param("is_top")Integer is_top, @Param("w_name")String w_name);

}

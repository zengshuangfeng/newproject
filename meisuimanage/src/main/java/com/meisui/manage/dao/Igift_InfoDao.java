package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.Short_Gift;

/**
 * <p>文件名称：Igift_InfoDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午2:57:08
 */
@Repository
public interface Igift_InfoDao {
	List<Gift_Info> getGiftInfoList(@Param("gift_id")Integer gift_id, @Param("gift_name")String gift_name, @Param("is_online")Integer is_online, @Param("is_private")Integer is_private, @Param("is_box")Integer is_box,@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getGiftInfoCount(@Param("gift_id")Integer gift_id, @Param("gift_name")String gift_name, @Param("is_online")Integer is_online, @Param("is_private")Integer is_private,@Param("is_box")Integer is_box);
	Integer insertGiftInfo(Gift_Info entity);
	Integer updateGiftInfo(Gift_Info entity);
	Integer deleteGiftInfo(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Gift_Info getGiftInfo(@Param("id")Integer id);
	Integer updateGiftInfoOnline(@Param("id")Integer id, @Param("is_online")Integer is_online, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Long getUidMake(@Param("uid_makeamends")Long uid_makeamends);
	Integer insertUidMake(@Param("uid_makeamends")Long uid_makeamends);
	List<Gift_Info> getGiftInfoWithIsOnline();
	List<Gift_Info> getAllGiftInfo();
	List<Gift_Info> getGiftInfoList2(@Param("gift_id")Integer gift_id, @Param("gift_name")String gift_name, @Param("is_online")Integer is_online, @Param("is_private")Integer is_private,@Param("type") Integer type, @Param("is_box")Integer is_box,@Param("offset")Integer offset, @Param("rows")Integer rows);
	int getGiftInfoCount2(@Param("gift_id")Integer gift_id, @Param("gift_name")String gift_name, @Param("is_online")Integer is_online, @Param("is_private")Integer is_private,@Param("type") Integer type,@Param("is_box")Integer is_box);
	Short_Gift getShortGiftList();
	Short_Gift getShortGiftByid(@Param("id")Integer id);
	int updateShortGift(Short_Gift short_gift);
}

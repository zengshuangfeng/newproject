package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Invite_Config;

/**
 * <p>文件名称：Ianchor_Invite_ConfigDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月18日 下午3:48:10
 */
@Repository
public interface Ianchor_Invite_ConfigDao {
	List<Anchor_Invite_Config> getAnchorInviteConfigList(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("is_online")Integer is_online, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorInviteConfigCount(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("is_online")Integer is_online);
	Integer insertAnchorInviteConfig(Anchor_Invite_Config entity);
	Integer updateAnchorInviteConfig(Anchor_Invite_Config entity);
	Anchor_Invite_Config getAnchorInviteConfig(@Param("id")Integer id);
	Integer deleteAnchorInviteConfig(@Param("id")Integer id, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Integer getAnchorInviteConfigIdWithUid(@Param("uid")Long uid);
	Integer getAnchorRewardCount(@Param("uid")Long uid);
}

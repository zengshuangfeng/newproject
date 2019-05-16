package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Record;
import com.meisui.manage.entity.Anchor_Virtual_Rule;
import com.meisui.manage.entity.RegisterLoginStatistics;
import com.meisui.manage.entity.Uid_Make;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.entity.User_Device;
import com.meisui.manage.entity.User_Extra;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.entity.User_Info_Extra;
import com.meisui.manage.entity.User_Real;

/**
 * <p>文件名称：IuserDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月13日 下午3:10:35
 */
@Repository
public interface IuserDao {
	List<User_Anchor> getUserAnchorList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("operate_centerid")Integer operate_centerid,  @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserAnchorCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("operate_centerid")Integer operate_centerid);
	Integer deleteUserAnchor(@Param("uid")Long uid);
	Integer updateUserAnchorState(@Param("anchor_state")Integer anchor_state, @Param("anchor_type")Integer anchor_type, @Param("uid")String uid);
	Integer getUserAnchorDivideProportion(@Param("uid")Long uid);
	Integer updateUserAnchorDivideProportion(@Param("divide_proportion")Integer divide_proportion, @Param("uid")Long uid);
	User_Info getUserAnchorWithdraw(@Param("uid")Long uid);
	Integer updateUserAnchorWithdraw(@Param("withdraw_type")Integer withdraw_type, @Param("withdraw_pass")String withdraw_pass, @Param("withdraw_name")String withdraw_name, @Param("uid")Long uid);
	User getUser(@Param("uid")Long uid);
	User_Anchor getUserAnchor(@Param("uid")Long uid);
	Integer updateUserAnchor(User_Anchor entity);
	Integer insertUserAnchor(User_Anchor entity);
	Integer insertUserInfo(User_Info entity);
	Integer updateUserIsAnchor(@Param("is_anchor")Integer is_anchor,@Param("uid")Long uid);
	String getUserInfoExtraToken(@Param("uid")Long uid);
	Integer updateUserSurplusAnchorVirtual(@Param("sub")Long sub, @Param("uid")Long uid);
	User_Info getUserInfo(@Param("uid")Long uid);
	List<User> getUserList(@Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("remark")String remark,@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("start_level")Integer start_level,@Param("end_level")Integer end_level,  @Param("sort")Integer sort, @Param("t_uid") long t_uid, @Param("uid")long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserCount(@Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("remark")String remark,@Param("s_time")Date s_time, @Param("e_time")Date e_time, @Param("start_level")Integer start_level,@Param("end_level")Integer end_level,  @Param("sort")Integer sort, @Param("t_uid") long t_uid, @Param("uid")long uid);
	List<String> getUserT1ByUid(@Param("uid")Long uid);
	List<String> getUserInfoT1ByUid(@Param("uid")Long uid);
	Integer saveRemak(@Param("uid")Long uid, @Param("remark")String remark);
	List<User_Extra> getExtraList(@Param("t1List")List<String> t1List, @Param("offset")Integer offset, @Param("rows")Integer rows);
	List<User_Device> getDeviceList(@Param("t1List")List<String> t1List, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getDeviceCount(@Param("t1List")List<String> t1List);
	String getUserPhone(@Param("uid")Long uid);
	Integer getUserLoginType(@Param("uid")Long uid);
	Date getUserLastLoginTime(@Param("uid")Long uid);
	List<User> getUserForbidList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("is_forbid")Integer is_forbid, @Param("date")Date date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserForbidCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid,  @Param("nickname")String nickname, @Param("is_forbid")Integer is_forbid, @Param("date")Date date);
	User getUserForbid(@Param("uid")Long uid);
	Integer saveUserForbid(@Param("uid")Long uid, @Param("forbid_start_time")Date forbid_start_time, @Param("forbid_hour")Integer forbid_hour, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Integer cancelUserForbid(@Param("uid")Long uid, @Param("w_name")String w_name, @Param("update_time")Date update_time);
	Integer updateUserIsTest(@Param("istest")Integer istest, @Param("id")Long id);
	Integer updateUserIsVirtual(@Param("is_virtual")Integer is_virtual, @Param("id")Long id);
	Integer updateUserBalanceVirtual(@Param("add")Integer add, @Param("id")Long id);
	List<Uid_Make> getUIdMakeList(@Param("id")Long id);
	List<User_Anchor> getUserAnchorList2(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname,  @Param("is_trial")Integer is_trial, @Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserAnchorCount2(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname,  @Param("is_trial")Integer is_trial, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Anchor_Record> getUserAnchorRecordList(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Anchor_Virtual_Rule> getAnchorVirtualRuleList();
	Long getUserIdWithToken(@Param("token")String token);
	Integer updateUserTotalAnchorVirtual(@Param("sub")Long sub, @Param("uid")Long uid);
	Long getUserIdWithF_uuid(@Param("f_uuid")String f_uuid);
	User_Real getUserReal(@Param("uid")Long uid);
	Integer updateUserHeadLock(@Param("id")Long id, @Param("head_lock")Integer head_lock);
	Integer updateUserNicknameLock(@Param("id")Long id, @Param("nickname_lock")Integer nickname_lock);
	Integer updateUserKeyCount(@Param("id")Long id, @Param("add")Integer add);
	Integer updateUserIsBlocked(@Param("id")Long id, @Param("is_blocked")Integer is_blocked);
	List<Long> getUIdListWithNickanme(@Param("nickname")String nickname);
    List<User> getUserVestList(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);
    Integer getUserVestCount(@Param("uid")Long uid, @Param("nickname")String nickname);
    Integer insertUser(User entity);
    Integer updatetUser(User entity);
    Integer insertUserInfoExtra(User_Info_Extra entity);
    Integer updateUserSurplusVideoVirtual(@Param("add")Integer add, @Param("uid")Long uid);
    Long getUserNicknameExist(@Param("nickname")String nickname);
    Integer updatebalance_virtual(@Param("balance_virtual") Integer balance_virtual, @Param("id")Long id);
    Long getUserScore(@Param("uid")Long uid);
	Integer updateUserScore(@Param("score")Long score, @Param("uid")Long uid);
	Integer getUserCanLevel(@Param("score")Long score);
	Integer updateUserLevel(@Param("level")Integer level, @Param("uid")Long uid);
	//settle_type: 0周结 1日结
	List<User_Anchor> getUserAnchorPayList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("start_time")Date start_time,@Param("settle_type")Integer settle_type,@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserAnchorPayCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("start_time")Date start_time,@Param("settle_type")Integer settle_type);
	Long getUserTotalStake(@Param("f_uuid")Long f_uuid);
	Integer getSecretAnchorCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname,@Param("union_id")Integer union_id);
	Integer updateUserSurplusSecretVirtual(@Param("sub")Long sub, @Param("uid")Long uid);
	String getUserNicknameWithF_uuid(@Param("f_uuid")Long f_uuid);
	List<Long> getUIdListWithUnionId(@Param("union_id")Integer union_id);
	List<User_Info> getUserInfoList( @Param("surplus_anchor_virtual")Long surplus_anchor_virtual);
	Integer updateUserVIP(@Param("id")Long id, @Param("vip_time")Date vip_time);
	List<RegisterLoginStatistics> getRegisterLoginStatisticsList(@Param("start_time")Date start_time,@Param("end_time")Date end_time,@Param("channel")String channel,@Param("platform")Integer platform,@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getRegisterLoginStatisticsCount(@Param("start_time")Date start_time,@Param("end_time")Date end_time);	
	RegisterLoginStatistics getRegisterLoginStatisticsSummary(@Param("start_time")Date start_time,@Param("end_time")Date end_time,@Param("channel")String channel,@Param("platform")Integer platform);
//	Long getUserTotalGiftVirtual(@Param("uid")long uid);
	List<User_Anchor> getUserAnchorOneList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("is_trial")Integer is_trial, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserAnchorOneCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("is_trial")Integer is_trial);
	Integer updateUserSurplusOneVirtual(@Param("sub")Long sub, @Param("uid")Long uid);
	Integer updateUserAnchorOneDisturb(@Param("uid")Long uid, @Param("one_disturb")Integer one_disturb);
	Integer updateUserAnchorOneDivideProportion(@Param("one_divide_proportion")Integer one_divide_proportion, @Param("uid")Long uid);
	Integer getUserAnchorOneDivideProportion(@Param("uid")Long uid);
	Integer updateUserOneHead(@Param("one_head")String one_head, @Param("id")Long id);
	int getInviteUidListByCenterId(@Param("operate_center_id")int operate_center_id);
	String getUserNickNameByapuid(long apuid);
	Integer updateUserHead(@Param("head")String head, @Param("id")Long id);
	List<User> getUseridByagentid(int agent_promoter_id);
	User getUserByUid(long recharge_uid);
	List<Long> getAnchorUidListByCenterId(@Param("centerId")int centerId);
	int getUidListByAgentId(@Param("agentId")int agentId);
	List<Long> getAnchorUidListByAgentId(@Param("agentId")int agentId);
	List<User> getInviteUsersByCenterId(@Param("centerId")int centerId, @Param("uid")long uid, @Param("offset")int offset, @Param("rows")int rows);
	int getInviteUsersCountByCenterId(@Param("centerId")int centerId, @Param("uid")long uid);
	Integer getInviteUserCountWithPromoter(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	List<User> getInviteUserList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid, @Param("agent_promoter_uid")Long agent_promoter_uid,@Param("b_time") String b_time,@Param("e_time")String e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);;
	Integer getInviteUserCount2(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid, @Param("agent_promoter_uid")Long agent_promoter_uid);
	List<User_Anchor> getUserAnchorList3(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("operate_centerid")Integer operate_centerid,  @Param("offset")Integer offset, @Param("rows")Integer rows,@Param("start_date") Date start_date,@Param("end_date") Date end_data);
	String getTel(@Param("uid")long uid);
	int totalInvite();
	Long getUidByTelOnUserPhoneTable(@Param("tel")String tel);
	Long getUidByTelOnUserRealTable(@Param("tel")String tel);
	String getTelByUserRealTable(@Param("uid")long uid);
	String getRealName(@Param("uid")long uid);
	Integer getUserAnchorIsDel(@Param("uid")long uid);
	Integer updateUserAnchorIsDel(@Param("uid")long uid);
	Integer getAllInviteUserCountWithPromoter(@Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	int totalInvite2(@Param("b_time") String b_time,@Param("e_time")String e_time);
	Integer getAllInviteUserCountWithPromoter2(@Param("operate_center_id")Integer operate_center_id,@Param("agentId")Integer agentId, @Param("uid")Long uid, @Param("b_time") String b_time,@Param("e_time")String e_time);
	List<User> getuidbyAgentpromoterid(@Param("agent_promoter_id")Integer agent_promoter_id);
	int getInviteUidListByCenterId2(@Param("operate_center_id")int operate_center_id, @Param("b_time") String b_time,@Param("e_time")String e_time);
	int getUidListByAgentId2(@Param("centerId")int centerId,@Param("agentId")int agentId, @Param("b_time") String b_time,@Param("e_time")String e_time);
	Integer getInviteUserCountWithPromoter2(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid,@Param("b_time") String b_time,@Param("e_time")String e_time);
	List<User> getuidbyagentid(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid);
	List<User> getAllInviteUserList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_id")Integer agent_promoter_id, @Param("uid")Long uid, @Param("agent_promoter_uid")Long agent_promoter_uid,@Param("b_time") String b_time,@Param("e_time")String e_time);
	List<User> getVirtualUserList(@Param("operate_center_id")int operate_center_id,@Param("agent_id")int agent_id,@Param("uid")Long uid, @Param("f_uuid")long f_uuid, @Param("nickname")String nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);
	int getVirtualUserListCount(@Param("operate_center_id")int operate_center_id,@Param("agent_id")int agent_id,@Param("uid")Long uid, @Param("f_uuid")long f_uuid, @Param("nickname")String nickname);
	int updatevirtual(@Param("f_uuid")Long f_uuid);
	User getUserByF_uuid(@Param("f_uuid")Long f_uuid);
	Long getUserIdByNickname(@Param("nickname")String nickname);
	long getUserFuuidByuid(@Param("apuid")long apuid);
	void insertUserVirtualRecord(@Param("uid")long uid, @Param("w_name")String w_name, @Param("date")Date date, @Param("type")Integer type);
	List<User_Info> getSettlementUserList(@Param("agent_id")Integer agent_id);
	List<User> getUserByIsvest();
	void updatepostcountByuid(@Param("uid")long uid,@Param("post_count")Integer post_count);
	User getUserByF_uuidAndisanchor(@Param("f_uuid")long f_uuid);
	User getUserByisAnchor(@Param("uid")long uid);
	User getIsAnchorByFuuid(@Param("anchor_f_uuid")String anchor_f_uuid);
	User getUserIsexist(@Param("uid")Integer uid);
	List<User> getUserByAnchorList(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_f_uuid")Integer agent_promoter_f_uuid, @Param("type")Integer type, @Param("s_time") Date s_time,@Param("e_time") Date e_time, @Param("offset")Integer offset, @Param("rows")Integer rows);
	int getUserByAnchorCount(@Param("operate_center_id")Integer operate_center_id, @Param("agent_id")Integer agent_id, @Param("agent_promoter_f_uuid")Integer agent_promoter_f_uuid, @Param("type")Integer type, @Param("s_time") Date s_time,@Param("e_time") Date e_time);
}

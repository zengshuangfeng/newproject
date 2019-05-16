package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Pay;
import com.meisui.manage.entity.Anchor_Pay_Illegal;
import com.meisui.manage.entity.Anchor_Pay_Rule;
import com.meisui.manage.entity.Anchor_Union_Pay;

@Repository
public interface Ianchor_PayDao {
	List<Anchor_Pay_Rule> getAnchorPayRuleList();
	List<Anchor_Pay> getAnchorPayList(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time,@Param("settle_type")Integer setttle_type,@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorPayCount(@Param("uid")Long uid, @Param("f_uuid")Long f_uuid, @Param("nickname")String nickname, @Param("union_id")Integer union_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time,@Param("settle_type")Integer setttle_type);
	Anchor_Pay getAnchorPay(@Param("uid")Long uid,@Param("union_id")Integer union_id,  @Param("start_time")Date start_time, @Param("end_time")Date end_time,@Param("settle_type")Integer setttle_type);
	Integer insertAnchorPayIllegal(Anchor_Pay_Illegal entity);
	List<Anchor_Pay_Illegal> getAnchorPayIllegalList(@Param("uid")Long uid, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getAnchorPayIllegalCount(@Param("uid")Long uid);
	Integer insertAnchorPay(Anchor_Pay entity);
	Double getAnchorPayIllegalSum(@Param("uid")Long uid, @Param("type")Integer type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Double getAnchorPayIllegalSum2(@Param("uid")Long uid, @Param("type")Integer type, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer insertAnchorUnionPay(Anchor_Union_Pay entity);
	Integer getAnchorUnionPayExist(@Param("union_id")Integer union_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Long getUserTotalStake(@Param("f_uuid")Long f_uuid, @Param("start_time")Long start_time, @Param("end_time")Long end_time);
	Long getSecretAnchorTotalStake(@Param("f_uuid")Long f_uuid, @Param("start_time")Long start_time, @Param("end_time")Long end_time);
	Date getAnchorLastPayDate(@Param("uid")Long uid);
	List<Anchor_Pay_Illegal> getAnchorPayIllegalListWithTime(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer getDayAnchorUnionPayExist(@Param("union_id")Integer union_id, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<Integer> getAnchorUnionPayExistList(@Param("start_time")Date start_time, @Param("end_time")Date end_time);
//	Long getUserTotalGiftVirtual(@Param("uid")Long uid, @Param("start_time")Date start_time, @Param("end_time")Date end_time);
}

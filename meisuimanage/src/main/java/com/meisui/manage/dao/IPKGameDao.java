package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.PK_Result;

@Repository
public interface IPKGameDao {

	List<PK_Result> getPKFuuidList(@Param("uid")Integer uid, @Param("nickname")String nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);

	PK_Result getPKresultbyfuuid(@Param("anchor_f_uuid")Integer anchor_f_uuid, @Param("isuccess")Integer isuccess);

	int getPKGameCount(@Param("uid")Integer uid, @Param("nickname")String nickname);

	List<PK_Result> getPKGameRecordList( @Param("anchor_f_uuid")Integer anchor_f_uuid,  @Param("offset")Integer offset, @Param("rows")Integer rows);

	int getPKGameRecordCount( @Param("anchor_f_uuid")Integer anchor_f_uuid);

	PK_Result getpkResultByGameuuid( @Param("gameuuid")String gameuuid,@Param("anchor_f_uuid")Integer anchor_f_uuid);

}

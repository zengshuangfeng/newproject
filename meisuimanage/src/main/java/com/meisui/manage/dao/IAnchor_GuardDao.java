package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Guard;
import com.meisui.manage.entity.Anchor_Guard_Change;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.Anchor_Guard_Manager;

@Repository
public interface IAnchor_GuardDao {

	List<Anchor_Guard> getGuardList(@Param("anchor_f_uuid")String anchor_f_uuid, @Param("uid")Integer uid, @Param("type")Integer type,@Param("offset")Integer offset, @Param("rows")Integer rows);

	int getGuardListCount(@Param("anchor_f_uuid")String anchor_f_uuid, @Param("uid")Integer uid, @Param("type")Integer type);

	List<Anchor_Guard_Change> getGuardChange();

	Anchor_Guard getGuardByuid(@Param("anchor_f_uuid")String anchor_f_uuid,@Param("uid") Integer uid);

	int insertAnchorGuard(Anchor_Guard anchor_guard);

	void inserAnchorGuardManager(Anchor_Guard_Manager guard_manager);

	int updateAnchorGuard(Anchor_Guard anchor_guard);

	Anchor_Guard getGuardWithuid(@Param("uid")Integer uid, @Param("anchor_f_uuid")String anchor_f_uuid);

	List<Anchor_Guard_Change2> getGuardChangeNew();

}

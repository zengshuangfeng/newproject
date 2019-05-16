package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Square;

/**
 * <p>文件名称：IsquareDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月18日 上午10:41:18
 */
@Repository
public interface IsquareDao {
	List<Square> getSquareList(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("type")Integer type, @Param("date")Date date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getSquareCount(@Param("uid")Long uid, @Param("nickname")String nickname, @Param("type")Integer type);
	Integer updateSquareVirtualHots(@Param("id")Integer id, @Param("virtual_add_hots")Integer virtual_add_hots, @Param("virtual_time")Date virtual_time, @Param("w_name")String w_name);
	Integer updateSquareVIP(@Param("uid")Long uid,@Param("is_vip")Integer is_vip);
	List<Square> getSquareList2(@Param("f_uuid")Long f_uuid, @Param("date")Date date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	List<Square> getAllSquareList();
	Integer getSquareCount2(@Param("f_uuid")Long f_uuid);
	String getSquareDomain(@Param("f_uuid")String f_uuid);
	Integer getTotalWatchCount();
	Integer updateFee_count(@Param("uid")Long uid,@Param("fee_count")int fee_count,@Param("is_fee")int is_fee,@Param("isupdate")int isupdate);
	Square getSquare(@Param("f_uuid")Long f_uuid, @Param("uid")Long uid);
	Integer deleteSquareFee(@Param("anchor_f_uuid")Long anchor_f_uuid);
}

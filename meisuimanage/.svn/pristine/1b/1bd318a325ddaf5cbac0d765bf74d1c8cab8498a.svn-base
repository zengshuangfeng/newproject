package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Withdraw;

/**
 * <p>文件名称：IwithdrawDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午3:31:56
 */
@Repository
public interface IwithdrawDao {
	List<Withdraw> getWithdrawList(@Param("is_pay")Integer is_pay, @Param("uid")Long uid, @Param("nickname")String nickname, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getWithdrawCount(@Param("is_pay")Integer is_pay, @Param("uid")Long uid, @Param("nickname")String nickname);
	Integer updateWithdraw(Withdraw entity);
	Withdraw getWithdrawVirtual(@Param("id")Integer id);
	List<Withdraw> getWithdrawListWithUid(@Param("uid")Long uid, @Param("date") String date, @Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getWithdrawCountWithUid(@Param("uid")Long uid, @Param("date") String date);
	Integer insertWithdraw(Withdraw entity);
}

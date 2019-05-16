package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.User_Check_Rule;

/**
 * <p>文件名称：Iuser_Check_RuleDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午11:12:03
 */
@Repository
public interface Iuser_Check_RuleDao {
	List<User_Check_Rule> getUserCheckRuleList(@Param("offset")Integer offset, @Param("rows")Integer rows);
	Integer getUserCheckRuleCount();
	Integer updateUserCheckRule(User_Check_Rule entity);
	User_Check_Rule getUserCheckRule(@Param("id")Integer id);
}

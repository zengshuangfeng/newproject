package com.meisui.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Recharge_Channel;

/**
 * 
 * <p>文件名称：Irecharge_ChannelMapper.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年10月12日上午10:05:21 
 */
@Repository
public interface Irecharge_ChannelDao {
	
	List<Recharge_Channel> getRecharge_ChannelList();

}

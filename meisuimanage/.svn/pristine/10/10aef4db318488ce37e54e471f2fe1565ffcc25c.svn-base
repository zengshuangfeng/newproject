package com.meisui.manage.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.VIPChange;

/**
 * 
 * <p>文件名称：IVIP_Room.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年9月26日下午4:54:18 
 */
@Repository(value="ivip_Room")
public interface IVIP_Room {
	
	public List<VIPChange> getVIPChangeList(@Param(value="offset")int offset,@Param(value="rows")int rows);
	public Integer getVIPChangeCount();
	public VIPChange getVIPChange(@Param(value="id")int id);
	public Integer updateVIPChange(VIPChange vipChange);
	public Integer insertVIPChange(VIPChange vipChange);
}

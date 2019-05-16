package com.meisui.manage.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>文件名称：DynamicDataSource.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司： Vmei </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:zhangyang@vmei.me">zhangyang</a>
 * @since 2015年11月9日 上午11:16:32
 */

public class DynamicDataSource extends AbstractRoutingDataSource  {
     
    @Override    
    protected Object determineCurrentLookupKey() {    
        return DataSourceContextHolder.getDbType();    
    }    
}

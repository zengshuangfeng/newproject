package com.meisui.manage.mybatis;

/**
 * <p>文件名称：DataSourceContextHolder.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司： Vmei </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:zhangyang@vmei.me">zhangyang</a>
 * @since 2015年11月9日 上午11:18:08
 */

public class DataSourceContextHolder {
 private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();    
    
    public static void setDbType(String dbType) {    
        contextHolder.set(dbType);    
    }    
    
    public static String getDbType() {    
        return ((String) contextHolder.get());    
    }    
    
    public static void clearDbType() {    
        contextHolder.remove();    
    }    
}

package com.meisui.manage.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.meisui.manage.util.BeanUtils.BeanInitUtils;
import com.meisui.manage.util.BeanUtils.TxHandel;


/**
 * <p>文件名称：ContextStartupListener.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司： Vmei </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2015年11月23日 上午10:47:38
 */

public class ContextStartupListener implements ServletContextListener {

    public ContextStartupListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    	Thread thread = new Thread(new TxHandel());
    	thread.setName("七牛监听");
    	thread.start();
    	
        BeanInitUtils beanInitUtils = new BeanInitUtils();
        try {
            beanInitUtils.InitRedisBean();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

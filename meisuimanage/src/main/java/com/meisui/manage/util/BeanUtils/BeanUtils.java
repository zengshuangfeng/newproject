package com.meisui.manage.util.BeanUtils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value="beanUtils")
public class BeanUtils implements ApplicationContextAware  {

	private static ApplicationContext appContexts;
     
	public BeanUtils() {
	}

	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContexts = applicationContext;
	}

	/**
	 * 根据biz名称取业务对象
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
	    if(appContexts==null)
	    {
	        return null;
	    }
		return appContexts.getBean(beanId);
	}

	public static ApplicationContext getApplicationContext() {
		return appContexts;
	}

}

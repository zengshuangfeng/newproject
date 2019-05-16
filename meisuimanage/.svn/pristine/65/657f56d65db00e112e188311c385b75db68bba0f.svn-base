package com.meisui.manage.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.forman.foundation.library.RedisUtil;



public class PropertyUtil extends PropertyPlaceholderConfigurer{

	public static final Logger logger = Logger.getLogger(PropertyUtil.class);

	private static Map<String, String> propertyMap;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		propertyMap = new HashMap<String, String>();
		for (Object o : props.keySet()){ 

			propertyMap.put(o.toString(), props.get(o).toString());
		}
	}

	public static String getValue(String key){
		if (null != propertyMap){
			String value = propertyMap.get(key);
			return value;
		}
		return "";
	}
	public static LinkedHashMap<String, String> getJumpType() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(); 
		try {
			map =  (LinkedHashMap<String, String>)BeanUtil.getBean("jumptype"); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(map==null||map.size()==0)
		{
			Map<String, String> routeMap = RedisUtil.GetHincrByJedis(0, "peipeiroute");
			for (Map.Entry<String, String> entry : routeMap.entrySet()) { 
				map.put(entry.getKey(), entry.getValue());
			}
		}
		if(map == null)
			map = new LinkedHashMap<String, String>();
		return  map;
	}
	
	public static LinkedHashMap<String, String> getJumpType2() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(); 
		try {
			map =  (LinkedHashMap<String, String>)BeanUtil.getBean("jumptype2"); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(map == null)
			map = new LinkedHashMap<String, String>();
		return  map;
	}
}

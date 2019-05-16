package com.meisui.manage.util.BeanUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.forman.foundation.library.RedisUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * <p>文件名称：BeanInitUtils.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司： Vmei </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年6月16日 上午11:38:15
 */
@Component(value="beanInitUtils")
public class BeanInitUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanInitUtils.class);

    public void InitRedisBean() throws InterruptedException {
        int sign=0;
        while (true) {
         if(BeanUtils.getBean("jedisPool")!=null)
         {
             break;
         }
            Thread.sleep(1000);
            sign++;
            if(sign==60)
            {
                break;
            }
        } 
       try {
            if (RedisUtil.jedisPool == null) {
                RedisUtil.jedisPool = (JedisPool) BeanUtils.getBean("jedisPool");
            }

        } catch (JedisException e) {
            logger.error("初始化Redis失败", e);
        }
 
    }

}

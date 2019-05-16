package com.meisui.manage.utils;

import java.util.Set;

import com.forman.foundation.library.RedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 
 * <p>文件名称：RedisUtilWrapper.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年11月14日下午2:40:00 
 */
public class RedisUtilWrapper extends RedisUtil{
	
	public static Set<String> GetZrangeByScoreJedis(int db, String key, double min, double max) {
		Set setlist = null;
		Jedis jedis = getJedis();
		jedis.select(db);
		boolean isBroken = false;

		try {
			setlist = jedis.zrangeByScore(key, min, max);
		} catch (Exception arg12) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return setlist;
	}

}

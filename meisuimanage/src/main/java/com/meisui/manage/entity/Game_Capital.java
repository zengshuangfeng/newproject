package com.meisui.manage.entity;

/**
 * <p>文件名称：Game_Capital.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 上午11:20:40
 */

public class Game_Capital {
	private int id;
	private long capital_pool;//资金池
	private float upper_limit;//上限
	private float lower_limit;//下限
	private int sign;//0总资金池1房间资金池
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getCapital_pool() {
		return capital_pool;
	}
	public void setCapital_pool(long capital_pool) {
		this.capital_pool = capital_pool;
	}
	public float getUpper_limit() {
		return upper_limit;
	}
	public void setUpper_limit(float upper_limit) {
		this.upper_limit = upper_limit;
	}
	public float getLower_limit() {
		return lower_limit;
	}
	public void setLower_limit(float lower_limit) {
		this.lower_limit = lower_limit;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
}

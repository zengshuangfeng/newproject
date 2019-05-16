package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Check_Rule.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午11:12:18
 */

public class User_Check_Rule {
	private int id;//每日签到id
	private int days;//每日签到第几天
	private int reward_count;//奖励钻石数
	private int min_rand;//最小随机数
	private int max_rand;//最大随机数
	private String w_name;//编辑人员
	private String create_time;//生效时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getReward_count() {
		return reward_count;
	}
	public void setReward_count(int reward_count) {
		this.reward_count = reward_count;
	}
	public int getMin_rand() {
		return min_rand;
	}
	public void setMin_rand(int min_rand) {
		this.min_rand = min_rand;
	}
	public int getMax_rand() {
		return max_rand;
	}
	public void setMax_rand(int max_rand) {
		this.max_rand = max_rand;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
}

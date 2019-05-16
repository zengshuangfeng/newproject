package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Game_Profit.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午1:49:15
 */

public class Game_Profit {
	private long total_bet_count;//当日总押注
	private long total_user_count;//当日总押注人数
	private long max_bet_count;//当日最大押注
	private double percent;//当日资金池比例
	private long virtual_count;//当日资金池
	private String create_time;//开播时间
	public long getTotal_bet_count() {
		return total_bet_count;
	}
	public void setTotal_bet_count(long total_bet_count) {
		this.total_bet_count = total_bet_count;
	}
	public long getTotal_user_count() {
		return total_user_count;
	}
	public void setTotal_user_count(long total_user_count) {
		this.total_user_count = total_user_count;
	}
	public long getMax_bet_count() {
		return max_bet_count;
	}
	public void setMax_bet_count(long max_bet_count) {
		this.max_bet_count = max_bet_count;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public long getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(long virtual_count) {
		this.virtual_count = virtual_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time, "yyyy-MM-dd");
	}
}

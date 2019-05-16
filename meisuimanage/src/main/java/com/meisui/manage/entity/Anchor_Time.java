package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;


/**
 * <p>文件名称：Anchor_Time.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月14日 下午4:36:35
 */

public class Anchor_Time {
	private Date start_time;//开播时间
	private Date end_time;//结束时间
	private String single_total_time;//当次播出时长
	private String uid;//主播uid
	private long total_virtual;//获取虚拟币（魅力）总量
	private long total_stake;//游戏流水
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSingle_total_time() {
		return single_total_time;
	}
	public void setSingle_total_time(String single_total_time) {
		this.single_total_time = single_total_time;
	}
	public long getTotal_virtual() {
		return total_virtual;
	}
	public void setTotal_virtual(long total_virtual) {
		this.total_virtual = total_virtual;
	}
	public long getTotal_stake() {
		return total_stake;
	}
	public void setTotal_stake(long total_stake) {
		this.total_stake = total_stake;
	}
}

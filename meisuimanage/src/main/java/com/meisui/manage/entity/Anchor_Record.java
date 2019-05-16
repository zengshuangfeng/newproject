package com.meisui.manage.entity;


/**
 * <p>文件名称：AnchorRecord.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月13日 下午5:12:38
 */

public class Anchor_Record {
	private long uid;//用户id
	private long virtual_count;//周魅力值
	private long effective_time;//有效直播总时长
	private int is_attend;//是否有出席 1是 0否
	private int is_effective;//是否是有效天数 1是 0否
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(long virtual_count) {
		this.virtual_count = virtual_count;
	}
	public int getIs_attend() {
		return is_attend;
	}
	public void setIs_attend(int is_attend) {
		this.is_attend = is_attend;
	}
	public int getIs_effective() {
		return is_effective;
	}
	public void setIs_effective(int is_effective) {
		this.is_effective = is_effective;
	}
	public long getEffective_time() {
		return effective_time;
	}
	public void setEffective_time(long effective_time) {
		this.effective_time = effective_time;
	}
}

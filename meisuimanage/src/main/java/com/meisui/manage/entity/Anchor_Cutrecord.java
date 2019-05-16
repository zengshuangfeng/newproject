package com.meisui.manage.entity;

import java.util.Date;

/**
 * <p>文件名称：Anchor_Cutrecord.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月9日 下午3:53:57
 */

public class Anchor_Cutrecord {
	private long uid;//用户uid
	private double start_handle;//操作前
	private double end_handle;//操作后
	private Date startup;//生效时间
	private String w_name;//编辑人员
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public double getStart_handle() {
		return start_handle;
	}
	public void setStart_handle(double start_handle) {
		this.start_handle = start_handle;
	}
	public double getEnd_handle() {
		return end_handle;
	}
	public void setEnd_handle(double end_handle) {
		this.end_handle = end_handle;
	}
	public Date getStartup() {
		return startup;
	}
	public void setStartup(Date startup) {
		this.startup = startup;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
}

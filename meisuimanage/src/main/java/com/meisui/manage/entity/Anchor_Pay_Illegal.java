package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

public class Anchor_Pay_Illegal {
	private int id;
	private long uid;
	private double illegal_money;
	private String illegal_time;
	private String remark;
	private Date create_time;
	private String w_name;
	private int type;//
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public double getIllegal_money() {
		return illegal_money;
	}
	public void setIllegal_money(double illegal_money) {
		this.illegal_money = illegal_money;
	}
	public String getIllegal_time() {
		return illegal_time;
	}
	public void setIllegal_time(Date illegal_time) {
		this.illegal_time = AuthUtil.formatDateToString(illegal_time, "yyyy-MM-dd");
	}
	public String getRemark() {
		return StringEscapeUtils.escapeHtml4(remark);
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}

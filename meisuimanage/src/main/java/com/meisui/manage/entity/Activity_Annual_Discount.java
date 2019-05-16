package com.meisui.manage.entity;

import java.util.Date;

public class Activity_Annual_Discount {
	private int id;
	private int uid;
	private double discount;//折扣
	private Date create_time;
	private int is_use;//是否使用了折扣卡
	private double recharge_rmb;//折后充值金额
	private Date recharge_time;//折后充值时间
	
	public double getRecharge_rmb() {
		return recharge_rmb;
	}
	public void setRecharge_rmb(double recharge_rmb) {
		this.recharge_rmb = recharge_rmb;
	}
	public Date getRecharge_time() {
		return recharge_time;
	}
	public void setRecharge_time(Date recharge_time) {
		this.recharge_time = recharge_time;
	}
	public int getIs_use() {
		return is_use;
	}
	public void setIs_use(int is_use) {
		this.is_use = is_use;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}

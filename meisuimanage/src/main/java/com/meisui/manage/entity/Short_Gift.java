package com.meisui.manage.entity;

import java.util.Date;

public class Short_Gift {
	private int id;
	private int gift_id;//对应礼物的id
	private String gift_pic;//快捷礼物图标
	private Date create_date;
	private Date update_date;
	private String w_name;
	private String remark;
	private Gift_Info gift_info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGift_id() {
		return gift_id;
	}
	public void setGift_id(int gift_id) {
		this.gift_id = gift_id;
	}
	public String getGift_pic() {
		return gift_pic;
	}
	public void setGift_pic(String gift_pic) {
		this.gift_pic = gift_pic;
	}

	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Gift_Info getGift_info() {
		return gift_info;
	}
	public void setGift_info(Gift_Info gift_info) {
		this.gift_info = gift_info;
	}

}

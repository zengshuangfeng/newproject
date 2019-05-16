package com.meisui.manage.entity;

import java.util.Date;

public class Province_Center {
	private int id;
	private String name;//省代名称
	private String remark;//备注
	private String username;//省代后台用户名
	private String password;//省代后台密码
	private Date create_time;
	private Date update_time;
	private String w_name;
	private int is_forbid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public int getIs_forbid() {
		return is_forbid;
	}
	public void setIs_forbid(int is_forbid) {
		this.is_forbid = is_forbid;
	}

	

}

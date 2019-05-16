package com.meisui.manage.entity;

import java.util.Date;

public class Activity_Annual_Login {
	private int id;
	private int uid;
	private Date login_time;
	private String nickname;
	private String f_uuid;
	private Activity_Annual_Discount annualdiscount;
	
	
	public Activity_Annual_Discount getAnnualdiscount() {
		return annualdiscount;
	}
	public void setAnnualdiscount(Activity_Annual_Discount annualdiscount) {
		this.annualdiscount = annualdiscount;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	
}

package com.meisui.manage.entity;

import java.util.Date;

public class Anchor_Guard {
	private int id;
	private String anchor_f_uuid;//主播房间号
	private int uid;//守护者UID
	private Date start_time;//开始时间
	private Date end_time;//结束时间
	private int type;//类型 0包月守护 1包季守护 2包年守护
	private String anchor_nickname;//主播昵称
	private String nickname;//守护者昵称
	private int status;//守护状态 1正常 0过期
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAnchor_nickname() {
		return anchor_nickname;
	}
	public void setAnchor_nickname(String anchor_nickname) {
		this.anchor_nickname = anchor_nickname;
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
	public String getAnchor_f_uuid() {
		return anchor_f_uuid;
	}
	public void setAnchor_f_uuid(String anchor_f_uuid) {
		this.anchor_f_uuid = anchor_f_uuid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}

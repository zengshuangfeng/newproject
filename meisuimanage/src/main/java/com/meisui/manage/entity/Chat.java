package com.meisui.manage.entity;

public class Chat {

	private long uid;//用户id
	private String nickname;//用户昵称
	private long anchor_f_uuid; //主播房间号
	private String anchor_name;//主播昵称
	private String msg;
	private String time;
	private boolean sleep=true;
	private boolean is_forbid;
	
	public boolean isIs_forbid() {
		return is_forbid;
	}
	public void setIs_forbid(boolean is_forbid) {
		this.is_forbid = is_forbid;
	}
	public boolean isSleep() {
		return sleep;
	}
	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getAnchor_f_uuid() {
		return anchor_f_uuid;
	}
	public void setAnchor_f_uuid(long anchor_f_uuid) {
		this.anchor_f_uuid = anchor_f_uuid;
	}
	public String getAnchor_name() {
		return anchor_name;
	}
	public void setAnchor_name(String anchor_name) {
		this.anchor_name = anchor_name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}

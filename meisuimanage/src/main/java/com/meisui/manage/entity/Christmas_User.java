package com.meisui.manage.entity;

import java.util.Date;

public class Christmas_User {
	private int id;
	private int action_id;
	private int prize_id;
	private int uid;
	private Date create_time;
	private String f_uuid;//用户房间号
	private Christmas_Prize christmas_prize;
	private String gift_name;
	private String nickname;//用户昵称	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGift_name() {
		return gift_name;
	}
	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}
	public Christmas_Prize getChristmas_prize() {
		return christmas_prize;
	}
	public void setChristmas_prize(Christmas_Prize christmas_prize) {
		this.christmas_prize = christmas_prize;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAction_id() {
		return action_id;
	}
	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}
	public int getPrize_id() {
		return prize_id;
	}
	public void setPrize_id(int prize_id) {
		this.prize_id = prize_id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}

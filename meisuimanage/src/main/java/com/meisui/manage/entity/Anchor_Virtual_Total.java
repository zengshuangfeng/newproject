package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

public class Anchor_Virtual_Total {
	private long uid;
	private long f_uuid;
	private String nickname;
	private String union_name;
	private long total_anchor_virtual;
	private long game_profit;
	private int level_one_virtual;
	private int level_one_num;
	private int level_two_virtual;
	private int level_two_num;	
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUnion_name() {
		return StringEscapeUtils.escapeHtml4(union_name);
	}
	public void setUnion_name(String union_name) {
		this.union_name = union_name;
	}
	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}
	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}
	public long getGame_profit() {
		return game_profit;
	}
	public void setGame_profit(long game_profit) {
		this.game_profit = game_profit;
	}

	public int getLevel_one_virtual() {
		return level_one_virtual;
	}

	public void setLevel_one_virtual(int level_one_virtual) {
		this.level_one_virtual = level_one_virtual;
	}

	public int getLevel_one_num() {
		return level_one_num;
	}

	public void setLevel_one_num(int level_one_num) {
		this.level_one_num = level_one_num;
	}

	public int getLevel_two_virtual() {
		return level_two_virtual;
	}

	public void setLevel_two_virtual(int level_two_virtual) {
		this.level_two_virtual = level_two_virtual;
	}

	public int getLevel_two_num() {
		return level_two_num;
	}
	public void setLevel_two_num(int level_two_num) {
		this.level_two_num = level_two_num;
	}
}

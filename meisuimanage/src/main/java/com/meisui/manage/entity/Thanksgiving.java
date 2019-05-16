package com.meisui.manage.entity;

import java.util.Date;

public class Thanksgiving {

	private long id;
	private long uid;
	private String fuuid;
	private int level;
	private String nickname;
	private String content;// 留言内容
	private long greatcount;// 总赞数
	private Date createtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getFuuid() {
		return fuuid;
	}

	public void setFuuid(String fuuid) {
		this.fuuid = fuuid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getGreatcount() {
		return greatcount;
	}

	public void setGreatcount(long greatcount) {
		this.greatcount = greatcount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}

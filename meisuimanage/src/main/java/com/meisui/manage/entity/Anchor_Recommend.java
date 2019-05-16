package com.meisui.manage.entity;

import java.util.Date;

public class Anchor_Recommend {
	private int id;
	private String f_uuid;//主播房间号
	private String pic;
	private int sort;//排序
	private Date create_time;
	private String w_name;
	private Date update_time;
	private int is_del;
	private String nickname;//主播昵称
	
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
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}

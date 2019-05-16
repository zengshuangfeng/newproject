package com.meisui.manage.entity;

import java.util.Date;

public class Anchor_Guard_Change {
	private int id;
	private String name;//充值名称
	private int change_rmb;//守护套餐金额
	private String guard_head;//特权头像装饰
	private String entrance_pic;//特权入场特效
	private int type;//类型 0包月守护 1包季守护 2包年守护
	private int is_online;//1上线，0下线
	private int is_del;
	private String w_name;
	private Date create_time;
	private Date update_time;
	
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
	public int getChange_rmb() {
		return change_rmb;
	}
	public void setChange_rmb(int change_rmb) {
		this.change_rmb = change_rmb;
	}

	public String getGuard_head() {
		return guard_head;
	}
	public void setGuard_head(String guard_head) {
		this.guard_head = guard_head;
	}
	public String getEntrance_pic() {
		return entrance_pic;
	}
	public void setEntrance_pic(String entrance_pic) {
		this.entrance_pic = entrance_pic;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
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
	

}

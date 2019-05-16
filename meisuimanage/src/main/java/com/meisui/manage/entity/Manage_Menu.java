package com.meisui.manage.entity;

public class Manage_Menu {
	private int id;
	private String name;//菜单名称
	private String name_en;//菜单英文名称
	private String url;//菜单的URL
	private boolean has_submenu;//是否有子菜单 1是 0 否
	private int f_id;//上级菜单ID
	private String f_name;//父菜单名称
	private int admin_id;//人员ID
	private String class_name;//样式名称
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
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isHas_submenu() {
		return has_submenu;
	}
	public void setHas_submenu(boolean has_submenu) {
		this.has_submenu = has_submenu;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
}

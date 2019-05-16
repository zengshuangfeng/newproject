package com.meisui.manage.entity;

/**
 * <p>文件名称：Menu.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年6月7日 下午4:13:01
 */

public class Menu {
	private int id;
	private String name;//菜单名称
	private int  has_submenu;//是否有子菜单1：是 0 否
	private String name_en;//菜单英文名称
	private int sort;//排序
	private String url;//url地址
	private int f_id;//父菜单ID
	private String f_name;//父菜单名称
	private String class_name;//样式名称
	private int isshow;//是否先 1：是  0否
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
	public int getHas_submenu() {
		return has_submenu;
	}
	public void setHas_submenu(int has_submenu) {
		this.has_submenu = has_submenu;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getIsshow() {
		return isshow;
	}
	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
}

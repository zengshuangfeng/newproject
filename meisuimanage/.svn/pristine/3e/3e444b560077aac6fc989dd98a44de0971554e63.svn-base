package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

public class Invite {
	private int id;
	private String content;//文案
	private int virtual_count;//邀请者获钻石
	private int to_virtual_count;//被邀请者获钻石
	private String effect_time;//生效时间
	private int is_online;//是否上线 1是 0否
	private String w_name;//编辑人员
	private Date create_time;//创建时间
	private Date update_time;//更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return StringEscapeUtils.escapeHtml4(content);
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(int virtual_count) {
		this.virtual_count = virtual_count;
	}
	public int getTo_virtual_count() {
		return to_virtual_count;
	}
	public void setTo_virtual_count(int to_virtual_count) {
		this.to_virtual_count = to_virtual_count;
	}
	public String getEffect_time() {
		return effect_time;
	}
	public void setEffect_time(Date effect_time) {
		this.effect_time = AuthUtil.formatDateToString(effect_time);
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
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

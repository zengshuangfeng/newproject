package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

public class Anchor_Union_Pay {
	private int union_id;
	private Date start_time;
	private Date end_time;
	private Date create_time;
	private String w_name;
	private int is_commit;
	public int getUnion_id() {
		return union_id;
	}
	public void setUnion_id(int union_id) {
		this.union_id = union_id;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public int getIs_commit() {
		return is_commit;
	}
	public void setIs_commit(int is_commit) {
		this.is_commit = is_commit;
	}
}

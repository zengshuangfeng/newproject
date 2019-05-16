package com.meisui.manage.entity;

import java.util.Date;

public class Activity_Annual {
	private int id;
	private Date activity_time;//活动时间
	private int wheel;//第几轮
	private Date end_time;//结束时间
	private int wheelsize;//现在共有几轮
	private Date start_time;//开始时间
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public int getWheelsize() {
		return wheelsize;
	}
	public void setWheelsize(int wheelsize) {
		this.wheelsize = wheelsize;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getActivity_time() {
		return activity_time;
	}
	public void setActivity_time(Date activity_time) {
		this.activity_time = activity_time;
	}
	public int getWheel() {
		return wheel;
	}
	public void setWheel(int wheel) {
		this.wheel = wheel;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	

}

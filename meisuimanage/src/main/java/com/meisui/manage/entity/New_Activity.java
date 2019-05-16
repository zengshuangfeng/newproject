package com.meisui.manage.entity;

import java.util.Date;

public class New_Activity {
	private int id;
	private String name;//名字
	private String url;
	private int type;//0为跳转 1为不跳转
	private String pic;//封面图片
	private Date state_time;//开始时间
	private Date end_time;//结束时间
	private int time_type;//时间类型 0为时间段  1为时间段内的某个时间点后开启
	private int position;//该活动显示位置 0为首页banner
	private int order;//排序
	private int is_share;//是否可分享 0为可分享  1为不可分享
	private String platform;//显示平台 0为全平台 1为ios 2为安卓
	private int is_online;//是否上线状态 0为未上线状态  1为上线状态
	private Date create_date;//创建时间
	private String w_name;
	private Date update_time;
	private int is_del;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getState_time() {
		return state_time;
	}
	public void setState_time(Date state_time) {
		this.state_time = state_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getTime_type() {
		return time_type;
	}
	public void setTime_type(int time_type) {
		this.time_type = time_type;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getIs_share() {
		return is_share;
	}
	public void setIs_share(int is_share) {
		this.is_share = is_share;
	}

	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
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

package com.meisui.manage.entity;

import java.util.Date;

public class Community_Recommend {
	private int id;
	private int type;//类型：0热门主播1热门直播
	private int uid;//主播uid
	private String title;//标题
	private String pic;//图片
	private String start_time;//开始时间
	private String end_time;//结束时间
	private int sort;//排序 数字越大越靠前
	private int is_del;//是否删除
	private int is_online;//是否上线
	private Date create_time;//创建时间 
	private String nickname;//主播昵称、
	private String head;//主播头像
	private Date update_time;//更新时间
	private String w_name;//编辑人员
	private long f_uuid;//房间号
	private int status;//0未开始，1超过时间（下线）,2表示当前时间在start_time 和 end_time之间

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
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

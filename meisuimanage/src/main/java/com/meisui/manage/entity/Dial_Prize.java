package com.meisui.manage.entity;

import java.util.Date;

public class Dial_Prize {
	private int id;
	private int type;//类型 0礼物 1钻石 2积分
	private int gift_id;//礼物ID
	private String pic;//礼物图片
	private int prize_count;//数量
	private double probability;//中奖概率
	private int inventory;//库存
	private Date start_time;//上线时间
	private Date end_time;//下线时间
	private int is_del;//是否删除 1是0否
	private String w_name;//编辑人员
	private Date create_time;//创建时间
	private Date update_time;//更新时间
	private String prize_name;//礼物名称
	private int state;//上下架状态；0下架，1上架
	
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
	public int getGift_id() {
		return gift_id;
	}
	public void setGift_id(int gift_id) {
		this.gift_id = gift_id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getPrize_count() {
		return prize_count;
	}
	public void setPrize_count(int prize_count) {
		this.prize_count = prize_count;
	}

	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
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
	public String getPrize_name() {
		return prize_name;
	}
	public void setPrize_name(String prize_name) {
		this.prize_name = prize_name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	

}

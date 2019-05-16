package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

public class Agent_Settlement {
	private int id;
	private String operate_center_name;
	private int operate_center_id;
	private int agent_id;
	private String name;
	private int settlement_type;
	private int virtual_count;
	private int divide;
	private double money_count;
	private String create_time;
	private long total_anchor_virtual;
	private long surplus_anchor_virtual;
	private Date start_time;
	private Date end_time;
	private String w_name;
	private long total_exchange_virtual;//已兑换魅力值
	
	
	public long getTotal_exchange_virtual() {
		return total_exchange_virtual;
	}

	public void setTotal_exchange_virtual(long total_exchange_virtual) {
		this.total_exchange_virtual = total_exchange_virtual;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperate_center_name() {
		return operate_center_name;
	}
	public void setOperate_center_name(String operate_center_name) {
		this.operate_center_name = operate_center_name;
	}
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public int getOperate_center_id() {
		return operate_center_id;
	}
	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSettlement_type() {
		return settlement_type;
	}
	public void setSettlement_type(int settlement_type) {
		this.settlement_type = settlement_type;
	}
	public int getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(int virtual_count) {
		this.virtual_count = virtual_count;
	}
	public int getDivide() {
		return divide;
	}
	public void setDivide(int divide) {
		this.divide = divide;
	}
	public double getMoney_count() {
		return money_count;
	}
	public void setMoney_count(double money_count) {
		this.money_count = money_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}
	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}
	public long getSurplus_anchor_virtual() {
		return surplus_anchor_virtual;
	}
	public void setSurplus_anchor_virtual(long surplus_anchor_virtual) {
		this.surplus_anchor_virtual = surplus_anchor_virtual;
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
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
}

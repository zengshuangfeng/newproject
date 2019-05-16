package com.meisui.manage.entity;

import java.util.Date;

public class Agent_Promoter {
	private int id;
	private int operate_center_id;// 运营中心id
	private int agent_id;// 代理表id
	private long uid;// 用户id
	private int divide;// 分成比例 70表示70%
	private String remark;// 备注
	private long total_anchor_virtual;// 累积收到魅力值
	private long surplus_anchor_virtual;// 剩余魅力值
	private Date create_time;// 创建时间;
	private Date update_time;// 更新时间
	private String w_name;// 编辑人员
	private int is_del;// 是否删除 1是 0否
	private String nickname;
	private Integer invite_recharge_sum;
	private Long invite_spending_sum;
	private Integer invite_count;
	private String invite_code;
	private String f_uuid;//房间号
	private String operate_center_name;//所属运营中心名称
	private String agent_name;//所属代理名称
	private int is_virtual;//是否为虚拟号
	
	
	public int getIs_virtual() {
		return is_virtual;
	}

	public void setIs_virtual(int is_virtual) {
		this.is_virtual = is_virtual;
	}

	public String getOperate_center_name() {
		return operate_center_name;
	}

	public void setOperate_center_name(String operate_center_name) {
		this.operate_center_name = operate_center_name;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getF_uuid() {
		return f_uuid;
	}

	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperate_center_id() {
		return operate_center_id;
	}

	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
	}

	public int getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getDivide() {
		return divide;
	}

	public void setDivide(int divide) {
		this.divide = divide;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getW_name() {
		return w_name;
	}

	public void setW_name(String w_name) {
		this.w_name = w_name;
	}

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getInvite_recharge_sum() {
		return invite_recharge_sum;
	}

	public void setInvite_recharge_sum(Integer invite_recharge_sum) {
		this.invite_recharge_sum = invite_recharge_sum;
	}

	public Long getInvite_spending_sum() {
		return invite_spending_sum;
	}

	public void setInvite_spending_sum(Long invite_spending_sum) {
		this.invite_spending_sum = invite_spending_sum;
	}

	public Integer getInvite_count() {
		return invite_count;
	}

	public void setInvite_count(Integer invite_count) {
		this.invite_count = invite_count;
	}

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}	
}

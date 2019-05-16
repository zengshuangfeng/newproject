package com.meisui.manage.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 归属变更
 * */
public class AddressChange {
	private int id;
	private String f_uuid;//用户房间号
	private String nickname;//用户昵称
	private String old_center_name;//原所属运营中心
	private String new_center_name;//现所属运营中心
	private String agentname;//所属代理
	private String agentfuuid;//推广员房间号
	private String create_name;//操作人
	private String  create_time;//操作时间
	private String agent_id;//代理表ID
	private String operate_center_id; //省代表ID;
	private String agent_promoter_id; //代理表D;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOld_center_name() {
		return old_center_name;
	}
	public void setOld_center_name(String old_center_name) {
		this.old_center_name = old_center_name;
	}
	public String getNew_center_name() {
		return new_center_name;
	}
	public void setNew_center_name(String new_center_name) {
		this.new_center_name = new_center_name;
	}
	public String getAgentname() {
		return agentname;
	}
	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}
	public String getAgentfuuid() {
		return agentfuuid;
	}
	public void setAgentfuuid(String agentfuuid) {
		this.agentfuuid = agentfuuid;
	}
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getOperate_center_id() {
		return operate_center_id;
	}
	public void setOperate_center_id(String operate_center_id) {
		this.operate_center_id = operate_center_id;
	}
	public String getAgent_promoter_id() {
		return agent_promoter_id;
	}
	public void setAgent_promoter_id(String agent_promoter_id) {
		this.agent_promoter_id = agent_promoter_id;
	}
	
	
}

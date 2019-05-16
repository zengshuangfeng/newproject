package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：Anchor_Record_Total.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月14日 上午11:42:38
 */

public class Anchor_Record_Total {
	private long uid;//用户id
	private long f_uuid;//房间号
	private String nickname;//昵称
	private int divide_proportion;//分成比例 例：50 表示50%
	private int union_id;//工会表id
	private String union_name;//工会名称
	private int is_trial;//是否试播 1是 0否
	private long virtual_count;//魅力值
	private int attend_days;//出席天数
	private int effective_days;//有效天数
	private String effective_time;//有效直播总时长
	private double total_anchor_earning;//总收益
	private String operate_center_name;
	private String agent_name;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getDivide_proportion() {
		return divide_proportion;
	}
	public void setDivide_proportion(int divide_proportion) {
		this.divide_proportion = divide_proportion;
	}
	public int getUnion_id() {
		return union_id;
	}
	public void setUnion_id(int union_id) {
		this.union_id = union_id;
	}
	public String getUnion_name() {
		return StringEscapeUtils.escapeHtml4(union_name);
	}
	public void setUnion_name(String union_name) {
		this.union_name = union_name;
	}
	public int getIs_trial() {
		return is_trial;
	}
	public void setIs_trial(int is_trial) {
		this.is_trial = is_trial;
	}
	public long getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(long virtual_count) {
		this.virtual_count = virtual_count;
	}
	public int getAttend_days() {
		return attend_days;
	}
	public void setAttend_days(int attend_days) {
		this.attend_days = attend_days;
	}
	public int getEffective_days() {
		return effective_days;
	}
	public void setEffective_days(int effective_days) {
		this.effective_days = effective_days;
	}
	public String getEffective_time() {
		return effective_time;
	}
	public void setEffective_time(String effective_time) {
		this.effective_time = effective_time;
	}
	public double getTotal_anchor_earning() {
		return total_anchor_earning;
	}
	public void setTotal_anchor_earning(double total_anchor_earning) {
		this.total_anchor_earning = total_anchor_earning;
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
}

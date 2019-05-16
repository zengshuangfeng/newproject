package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Video_Withdraw.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月6日 上午9:14:19
 */

public class Video_Withdraw {
	private int id;
	private long uid;
	private String o_id;
	private double withdraw_money;
	private int withdraw_type;
	private String withdraw_pass;
	private int status;
	private String create_time;
	private String audit_time;
	private String pay_time;
	private String audit_w_name;
	private String pay_w_name;
	private String remark;
	private String nickname;
	private int user_is_blocked;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public double getWithdraw_money() {
		return withdraw_money;
	}
	public void setWithdraw_money(double withdraw_money) {
		this.withdraw_money = withdraw_money;
	}
	public int getWithdraw_type() {
		return withdraw_type;
	}
	public void setWithdraw_type(int withdraw_type) {
		this.withdraw_type = withdraw_type;
	}
	public String getWithdraw_pass() {
		return withdraw_pass;
	}
	public void setWithdraw_pass(String withdraw_pass) {
		this.withdraw_pass = withdraw_pass;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public String getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = AuthUtil.formatDateToString(audit_time);
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = AuthUtil.formatDateToString(pay_time);
	}
	public String getAudit_w_name() {
		return audit_w_name;
	}
	public void setAudit_w_name(String audit_w_name) {
		this.audit_w_name = audit_w_name;
	}
	public String getPay_w_name() {
		return pay_w_name;
	}
	public void setPay_w_name(String pay_w_name) {
		this.pay_w_name = pay_w_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getUser_is_blocked() {
		return user_is_blocked;
	}
	public void setUser_is_blocked(int user_is_blocked) {
		this.user_is_blocked = user_is_blocked;
	}
}

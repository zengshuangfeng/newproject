package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：Withdraw.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月14日 上午9:11:55
 */

public class Withdraw {
	private int id;
	private int withdraw_type;//0支付宝
	private String withdraw_pass;//提现账户
	private long uid;//用户uid
	private String nickname;//昵称
	private Date create_time;//创建时间
	private String name;//账户名
	private long withdraw_virtual;//提现虚拟币
	private double withdraw_rmb;//提现人民币
	private int is_pay;//是否打款 1是 0否
	private String w_name;//编辑人员
	private Date update_time;//编辑时间
	private String remark;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWithdraw_type() {
		return withdraw_type;
	}
	public void setWithdraw_type(int withdraw_type) {
		this.withdraw_type = withdraw_type;
	}
	public String getWithdraw_pass() {
		return StringEscapeUtils.escapeHtml4( withdraw_pass);
	}
	public void setWithdraw_pass(String withdraw_pass) {
		this.withdraw_pass = withdraw_pass;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getWithdraw_virtual() {
		return withdraw_virtual;
	}
	public void setWithdraw_virtual(long withdraw_virtual) {
		this.withdraw_virtual = withdraw_virtual;
	}
	public double getWithdraw_rmb() {
		return withdraw_rmb;
	}
	public void setWithdraw_rmb(double withdraw_rmb) {
		this.withdraw_rmb = withdraw_rmb;
	}
	public int getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(int is_pay) {
		this.is_pay = is_pay;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
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
	public String getRemark() {
		return StringEscapeUtils.escapeHtml4(remark);
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Total_FLowing.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月9日 下午4:26:33
 */

public class Total_Flowing {
	private String type;//类型名称
	private int balance_pay;//收支0表示收1表示支
	private String create_time;//创建时间
	private String remark;//备注
	private int spending_virtual;//钻石数
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBalance_pay() {
		return balance_pay;
	}
	public void setBalance_pay(int balance_pay) {
		this.balance_pay = balance_pay;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSpending_virtual() {
		return spending_virtual;
	}
	public void setSpending_virtual(int spending_virtual) {
		this.spending_virtual = spending_virtual;
	}
}

package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：User_Real.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月3日 上午10:25:14
 */

public class User_Real {
	private String name;//姓名
	private String card;//身份证号
	private String tel;//手机号码
	private String address;//收货地址
	private String qq;//QQ账号
	public String getName() {
		return StringEscapeUtils.escapeHtml4(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard() {
		return StringEscapeUtils.escapeHtml4(card);
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getTel() {
		return StringEscapeUtils.escapeHtml4(tel);
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return StringEscapeUtils.escapeHtml4(address);
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

}


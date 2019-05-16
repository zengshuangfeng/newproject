package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Exchange.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 下午3:03:50
 */

public class User_Exchange {
	private int id;
	private String o_id;//订单号
	private long uid;//用户uid
	private String nickname;//昵称
	private int p_id;//商品id
	private String p_name;//商品名称
	private String p_pic;//商品图片
	private int type;//类型 0钥匙兑换 1现金购买
	private int price;//钥匙数或现金数
	private String create_time;//下单时间
	private int status;//状态 0待付款 1待发货 2已发货 3已失效
	private String logistics_name;//物流名称
	private String logistics_code;//物流单号
	private String name;//姓名
	private String tel;//手机号
	private String address;//收货地址
	private String pay_trade_no;//第三方交易单号
	private String pay_account;//付款账号
	private int pay_type;//付款方式 0:支付宝 1:微信 2苹果支付
	private String remark;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_pic() {
		return p_pic;
	}
	public void setP_pic(String p_pic) {
		this.p_pic = p_pic;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLogistics_name() {
		return logistics_name;
	}
	public void setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
	}
	public String getLogistics_code() {
		return logistics_code;
	}
	public void setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
	}
	public String getName() {
		return StringEscapeUtils.escapeHtml4(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPay_trade_no() {
		return pay_trade_no;
	}
	public void setPay_trade_no(String pay_trade_no) {
		this.pay_trade_no = pay_trade_no;
	}
	public String getPay_account() {
		return pay_account;
	}
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

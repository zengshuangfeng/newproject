package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Recharge.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午4:33:23
 */

public class Recharge {
	private int uid;//用户id
	private long f_uuid;//房间号
	private String nickname;//昵称
	private int recharge_rmb;//充值金额
	private int change_virtual;//兑换虚拟币
	private String o_id;//订单号
	private String pay_trade_no;//第三方交易单号
	private int pay_type;//付款方式 0:支付宝 1:微信
	private String create_time;//充值时间
	private String channel;//渠道号
	private String platform;//平台
	private int is_pay;//是否已支付 1是 0否
	private String operateName;//所属运营中心名称
	private String agentName;//所属代理平台名称
	private String proName;//所属推广员名称
	private int change_id;
	private int pay_virtual;//是否会员号购买:0否，1是
	
	public int getChange_id() {
		return change_id;
	}
	public void setChange_id(int change_id) {
		this.change_id = change_id;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRecharge_rmb() {
		return recharge_rmb;
	}
	public void setRecharge_rmb(int recharge_rmb) {
		this.recharge_rmb = recharge_rmb;
	}
	public int getChange_virtual() {
		return change_virtual;
	}
	public void setChange_virtual(int change_virtual) {
		this.change_virtual = change_virtual;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public String getPay_trade_no() {
		return pay_trade_no;
	}
	public void setPay_trade_no(String pay_trade_no) {
		this.pay_trade_no = pay_trade_no;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(int is_pay) {
		this.is_pay = is_pay;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public int getPay_virtual() {
		return pay_virtual;
	}
	public void setPay_virtual(int pay_virtual) {
		this.pay_virtual = pay_virtual;
	}
	
}

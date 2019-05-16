package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：Balance_virtualrecord.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司：Forman </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2017年5月28日 上午11:22:23
 */

public class Balance_virtualrecord {
	private int  id;// int(11) NOT NULL AUTO_INCREMENT,
	private long  uid;// bigint(20) DEFAULT NULL COMMENT '用户ID',
	private int    balance_virtual ;//int(11) DEFAULT NULL COMMENT '充值钻石',
	private int   diamondsb;// smallint(6) NOT NULL DEFAULT '-1' COMMENT '充值方式0支付宝1微信',
	private int  level; // 等级
	private int  hasDeviceInfo; // 是否有设备信息
	private String   diamondsa;// varchar(10) DEFAULT NULL COMMENT '充值类型zftype1用户充值zftype2家族充值zftype3奖励充值',
	private String    family;// varchar(100) DEFAULT NULL COMMENT '所属家族',
	private String    remark;// varchar(2000) DEFAULT NULL COMMENT '充值备注',
	private String   w_name ;//varchar(100) DEFAULT NULL,
	private String   create_date;// datetime DEFAULT NULL,
	private String    nickname ;//varchar(100) DEFAULT NULL,
	private String f_uuid;//房间号
	private int balance_money;//充值金额
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
	public int getBalance_virtual() {
		return balance_virtual;
	}
	public void setBalance_virtual(int balance_virtual) {
		this.balance_virtual = balance_virtual;
	}
	public int getDiamondsb() {
		return diamondsb;
	}
	public void setDiamondsb(int diamondsb) {
		this.diamondsb = diamondsb;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHasDeviceInfo() {
		return hasDeviceInfo;
	}
	public void setHasDeviceInfo(int hasDeviceInfo) {
		this.hasDeviceInfo = hasDeviceInfo;
	}
	public String getDiamondsa() {
		return diamondsa;
	}
	public void setDiamondsa(String diamondsa) {
		this.diamondsa = diamondsa;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public int getBalance_money() {
		return balance_money;
	}
	public void setBalance_money(int balance_money) {
		this.balance_money = balance_money;
	}
}

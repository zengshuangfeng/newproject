package com.meisui.manage.entity;

import java.util.Date;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

public class Operate_Center {
	private int id;
	private String name;// 运营中心名称
	private int divide;// 分成比例 80 表示80%
	private String remark;// 备注
	private String username;// 运营中心后台用户名
	private String password;// 运营中心后台密码
	private String nickname;// 运营中心昵称
	private String contact;// 联系方式
	private String card_name;// 持卡人
	private String card_no;// 银行卡号
	private String card_bank;// 开户银行名称
	private String province;// 省份
	private String city;// 城市
	private String company;// 公司名称
	private int settlement_type;// 结算类型 0周结 1日结
	private Date create_time;// 创建时间
	private Date update_time;// 更新时间
	private String w_name;// 编辑人员
	private int is_forbid;// 是否禁用 1是 0否
	private int count;
	private long total_anchor_virtual;
	private double money_count;
	private long surplus_anchor_virtual;
	private PublicEntity publicEntity;
	private long recharge_quota;//每月充值额度
	private long totalvirtualcount;//剩余额度
	private int province_center_id;//省代id
	private String province_center_name;//省代名称
	private long total_exchange_virtual;//已兑换魅力值
	
	
	public long getTotal_exchange_virtual() {
		return total_exchange_virtual;
	}

	public void setTotal_exchange_virtual(long total_exchange_virtual) {
		this.total_exchange_virtual = total_exchange_virtual;
	}

	public String getProvince_center_name() {
		return province_center_name;
	}

	public void setProvince_center_name(String province_center_name) {
		this.province_center_name = province_center_name;
	}

	public int getProvince_center_id() {
		return province_center_id;
	}

	public void setProvince_center_id(int province_center_id) {
		this.province_center_id = province_center_id;
	}

	public long getTotalvirtualcount() {
		return totalvirtualcount;
	}

	public void setTotalvirtualcount(long totalvirtualcount) {
		this.totalvirtualcount = totalvirtualcount;
	}

	public long getRecharge_quota() {
		return recharge_quota;
	}

	public void setRecharge_quota(long recharge_quota) {
		this.recharge_quota = recharge_quota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_bank() {
		return card_bank;
	}

	public void setCard_bank(String card_bank) {
		this.card_bank = card_bank;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getSettlement_type() {
		return settlement_type;
	}

	public void setSettlement_type(int settlement_type) {
		this.settlement_type = settlement_type;
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

	public int getIs_forbid() {
		return is_forbid;
	}

	public void setIs_forbid(int is_forbid) {
		this.is_forbid = is_forbid;
	}

	public PublicEntity getPublicEntity() {
		return publicEntity;
	}

	public void setPublicEntity(PublicEntity publicEntity) {
		this.publicEntity = publicEntity;
	}

	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}

	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}

	public double getMoney_count() {
		return money_count;
	}

	public void setMoney_count(double money_count) {
		this.money_count = money_count;
	}

	public long getSurplus_anchor_virtual() {
		return surplus_anchor_virtual;
	}

	public void setSurplus_anchor_virtual(long surplus_anchor_virtual) {
		this.surplus_anchor_virtual = surplus_anchor_virtual;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

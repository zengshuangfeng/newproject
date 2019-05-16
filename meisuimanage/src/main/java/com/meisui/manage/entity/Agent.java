package com.meisui.manage.entity;

import java.util.Date;

public class Agent {
	private int id;
	private int operate_center_id;// 运营中心id
	private String operate_center_name;// 运营中心名称
	private String name;// 名称
	private int divide;// 分成比例 80 表示80%
	private String remark;// 备注
	private String username;// 代理后台用户名
	private String password;// 代理后台密码
	private String nickname;// 代理后台昵称
	private String contact;// 联系方式
	private String card_name;// 持卡人
	private String card_no;// 银行卡号
	private String card_bank;// 开户银行名称
	private int province;// 省份
	private int city;// 城市
	private String company;// 公司名称
	private Date create_time;// 创建时间
	private Date update_time;// 更新时间
	private String w_name;// 编辑人员
	private int is_del;// 是否删除 1是 0否
	private int is_forbid;

	private int totalInvite;// 邀请人数
	private int totalRecharge;// 邀请人总充值
	private int totalAnchor;// 直播总魅力值
	private int totalPromoter;// 推广总魅力值
	
	private int settlement_type;//结算类型 0周结 1日结
	private long surplus_anchor_virtual;
	private int virtual_count;
	private double money_count;
	private long total_anchor_virtual;
	private long total_exchange_virtual;
	
	
	public long getTotal_exchange_virtual() {
		return total_exchange_virtual;
	}

	public void setTotal_exchange_virtual(long total_exchange_virtual) {
		this.total_exchange_virtual = total_exchange_virtual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperate_center_id() {
		return operate_center_id;
	}

	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
	}

	public String getOperate_center_name() {
		return operate_center_name;
	}

	public void setOperate_center_name(String operate_center_name) {
		this.operate_center_name = operate_center_name;
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

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public int getTotalInvite() {
		return totalInvite;
	}

	public void setTotalInvite(int totalInvite) {
		this.totalInvite = totalInvite;
	}

	public int getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(int totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public int getTotalAnchor() {
		return totalAnchor;
	}

	public void setTotalAnchor(int totalAnchor) {
		this.totalAnchor = totalAnchor;
	}

	public int getTotalPromoter() {
		return totalPromoter;
	}

	public void setTotalPromoter(int totalPromoter) {
		this.totalPromoter = totalPromoter;
	}

	public int getSettlement_type() {
		return settlement_type;
	}

	public void setSettlement_type(int settlement_type) {
		this.settlement_type = settlement_type;
	}

	public long getSurplus_anchor_virtual() {
		return surplus_anchor_virtual;
	}

	public void setSurplus_anchor_virtual(long surplus_anchor_virtual) {
		this.surplus_anchor_virtual = surplus_anchor_virtual;
	}

	public int getVirtual_count() {
		return virtual_count;
	}

	public void setVirtual_count(int virtual_count) {
		this.virtual_count = virtual_count;
	}

	public double getMoney_count() {
		return money_count;
	}

	public void setMoney_count(double money_count) {
		this.money_count = money_count;
	}

	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}

	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}

	public int getIs_forbid() {
		return is_forbid;
	}

	public void setIs_forbid(int is_forbid) {
		this.is_forbid = is_forbid;
	}

}

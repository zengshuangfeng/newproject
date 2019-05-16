package com.meisui.manage.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 实名认证实体类
 * */
public class Authentication {

	private int id;
	private int province_id;//省代ID
	private int operate_center_id;//营运中心ID
	private int agent_id;//代理ID
	private String name;//姓名
	private String phone;//手机
	private String email;//邮箱
	private String company;//公司名称
	private String company_address;//公司联系地址
	private String company_reg_address;//注册地址
	private String business_number;//营业执照注册号
	private String business_license_deadtime;//营业执照有效期
	private String account_open_number;//开户许可证核准号
	private String account_name;//结算账户户名
	private String bank_card;//结算银行卡号
	private String bank_of_deposit;//开户银行
	private String account_opening_branch;//开户支行
	private String business_license;//营业执照
	private String account_opening_license;//开户许可证
	private String corporate_id_card;//法人身份证
	private String other;//其他
	private int is_status;//审核状态：0审核中 1初审通过 2初审未通过 3复审通过 4复审未通过
	private Date create_time;//实名通过认证时间
	private String update_time;//复审时间
	private String agent_name;
	private String center_name;
	private String province_name;
	private String pay_type;
	private int stauts;
	private Date checktime;
	

	public Date getChecktime() {
		return checktime;
	}
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	public int getStauts() {
		return stauts;
	}
	public void setStauts(int stauts) {
		this.stauts = stauts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public int getOperate_center_id() {
		return operate_center_id;
	}
	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
	}
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_reg_address() {
		return company_reg_address;
	}
	public void setCompany_reg_address(String company_reg_address) {
		this.company_reg_address = company_reg_address;
	}
	public String getBusiness_number() {
		return business_number;
	}
	public void setBusiness_number(String business_number) {
		this.business_number = business_number;
	}
	public String getBusiness_license_deadtime() {
		return business_license_deadtime;
	}
	public void setBusiness_license_deadtime(String business_license_deadtime) {
		this.business_license_deadtime = business_license_deadtime;
	}
	public String getAccount_open_number() {
		return account_open_number;
	}
	public void setAccount_open_number(String account_open_number) {
		this.account_open_number = account_open_number;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getBank_card() {
		return bank_card;
	}
	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}
	public String getBank_of_deposit() {
		return bank_of_deposit;
	}
	public void setBank_of_deposit(String bank_of_deposit) {
		this.bank_of_deposit = bank_of_deposit;
	}
	public String getAccount_opening_branch() {
		return account_opening_branch;
	}
	public void setAccount_opening_branch(String account_opening_branch) {
		this.account_opening_branch = account_opening_branch;
	}
	public String getBusiness_license() {
		return business_license;
	}
	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}
	public String getAccount_opening_license() {
		return account_opening_license;
	}
	public void setAccount_opening_license(String account_opening_license) {
		this.account_opening_license = account_opening_license;
	}
	public String getCorporate_id_card() {
		return corporate_id_card;
	}
	public void setCorporate_id_card(String corporate_id_card) {
		this.corporate_id_card = corporate_id_card;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public int getIs_status() {
		return is_status;
	}
	public void setIs_status(int is_status) {
		this.is_status = is_status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getCenter_name() {
		return center_name;
	}
	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	
}

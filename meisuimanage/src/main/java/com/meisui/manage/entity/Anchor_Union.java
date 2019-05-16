package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Anchor_Union.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月7日 下午2:34:39
 */

public class Anchor_Union {
	private int id;//
	private String name;//工会名称
	private int divide_proportion;//分成比例
	private int one_divide_proportion;//一对一分成比例
	private String remark;//备注
	private String username;//工会后台用户名
	private String password;//工会后台密码
	private String create_time;//创建时间
	private Date update_time;//更新时间
	private String w_name;//编辑人员
	private int is_pay;//是否已结算
	private int type;//结算类型 0周结  1日结
	private String commander;
	private String weixin;
	private String qq;
	private String tel;
	private String email;
	private String card_holder;
	private String bank_card_no;
	private int province;
	private int city;
	private String bank_name;
	private String company;
	private String business_license;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return StringEscapeUtils.escapeHtml4(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDivide_proportion() {
		return divide_proportion;
	}
	public void setDivide_proportion(int divide_proportion) {
		this.divide_proportion = divide_proportion;
	}
	public int getOne_divide_proportion() {
		return one_divide_proportion;
	}
	public void setOne_divide_proportion(int one_divide_proportion) {
		this.one_divide_proportion = one_divide_proportion;
	}
	public String getRemark() {
		return StringEscapeUtils.escapeHtml4(remark);
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public int getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(int is_pay) {
		this.is_pay = is_pay;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCommander() {
		return commander;
	}
	public void setCommander(String commander) {
		this.commander = commander;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
	public String getBank_card_no() {
		return bank_card_no;
	}
	public void setBank_card_no(String bank_card_no) {
		this.bank_card_no = bank_card_no;
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
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBusiness_license() {
		return business_license;
	}
	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}
}

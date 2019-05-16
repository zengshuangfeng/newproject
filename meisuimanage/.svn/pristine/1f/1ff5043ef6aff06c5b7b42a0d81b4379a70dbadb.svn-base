package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Apply.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午2:11:57
 */

public class Apply {
	private int id;//申请id
	private String head;//头像 多张,隔开
	private String phone;//手机
	private String qq;//qq
	private long uid;//用户uid
	private long f_uuid;//房间号
	private String nickname;//昵称
	private String create_time;//申请时间
	private int is_contact;//是否联系 1是 0否
	private String remark;//备注
	private String w_name;//编辑人员
	private int is_anchor;//是否是主播 1是 0否
	private int ispromoter;//0 不是推广员，1是推广员
	
	
	public int getIspromoter() {
		return ispromoter;
	}
	public void setIspromoter(int ispromoter) {
		this.ispromoter = ispromoter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHead() {
		return StringEscapeUtils.escapeHtml4(head);
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getPhone() {
		return StringEscapeUtils.escapeHtml4(phone);
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return StringEscapeUtils.escapeHtml4(qq);
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public int getIs_contact() {
		return is_contact;
	}
	public void setIs_contact(int is_contact) {
		this.is_contact = is_contact;
	}
	public String getRemark() {
		return StringEscapeUtils.escapeHtml4(remark);
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
	public int getIs_anchor() {
		return is_anchor;
	}
	public void setIs_anchor(int is_anchor) {
		this.is_anchor = is_anchor;
	}
}

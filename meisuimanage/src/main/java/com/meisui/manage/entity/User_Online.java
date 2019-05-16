package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：User_Online.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月14日 下午4:13:10
 */

public class User_Online {
	private String uid;//用户uid
	private String f_uuid;//用户房间号
	private String nickname;//用户昵称
	private String anchor_nickname;//主播昵称
	private String level;//等级
	private String sex;//性别 0男1女 2未知
	private String is_anchor;//是否是主播 1是 0否
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAnchor_nickname() {
		return StringEscapeUtils.escapeHtml4(anchor_nickname);
	}
	public void setAnchor_nickname(String anchor_nickname) {
		this.anchor_nickname = anchor_nickname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIs_anchor() {
		return is_anchor;
	}
	public void setIs_anchor(String is_anchor) {
		this.is_anchor = is_anchor;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
}

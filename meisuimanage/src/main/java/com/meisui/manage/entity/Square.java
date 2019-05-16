package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Square.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月18日 上午10:37:44
 */

public class Square {
	private int id;//
	private String t;//主播token
	private String uid;//主播uid;
	private String nickname;//主播昵称
	private String anchor_notice;//主播房间公告
	private int watch_count;//观看人数
	private int type;//类型 0直播 1游戏
	private int virtual_add_hots;//增加的虚拟热度值
	private int hots;//热度值
	private String virtual_time;//虚拟热度持续至时间
	private String w_name;//操作人员
	private long f_uuid;//主播房间号
	private long times;//开播时间戳
	private String create_time;//开播时间
	private int real_count;//真实人数
	private int is_trial;//是否是试播 1是 0否
	private String union_name;//工会名称
	private double kbs;//直播流 kbs
	private String anchor_cover;
	private int is_vip;//是否是VIP 1是  0否
	private int is_fee;//是否付费播 1是 0否
	private int fee_count;//付费钻石数
	private int is_update;//修改付费票价 0主播 1后台管理员
	private String serverIP;//IM服务器IP
	private int is_secret;//是否私密播  0否  1是
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
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
	public String getAnchor_notice() {
		return StringEscapeUtils.escapeHtml4(anchor_notice);
	}
	public void setAnchor_notice(String anchor_notice) {
		this.anchor_notice = anchor_notice;
	}
	public int getWatch_count() {
		return watch_count;
	}
	public void setWatch_count(int watch_count) {
		this.watch_count = watch_count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getVirtual_add_hots() {
		return virtual_add_hots;
	}
	public void setVirtual_add_hots(int virtual_add_hots) {
		this.virtual_add_hots = virtual_add_hots;
	}
	public int getHots() {
		return hots;
	}
	public void setHots(int hots) {
		this.hots = hots;
	}
	public String getVirtual_time() {
		return virtual_time;
	}
	public void setVirtual_time(Date virtual_time) {
		if(virtual_time!=null)
			this.virtual_time = AuthUtil.formatDateToString(virtual_time);
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getReal_count() {
		return real_count;
	}
	public void setReal_count(int real_count) {
		this.real_count = real_count;
	}
	public int getIs_trial() {
		return is_trial;
	}
	public void setIs_trial(int is_trial) {
		this.is_trial = is_trial;
	}
	public String getUnion_name() {
		return union_name;
	}
	public void setUnion_name(String union_name) {
		this.union_name = union_name;
	}
	public double getKbs() {
		return kbs;
	}
	public void setKbs(double kbs) {
		this.kbs = kbs;
	}
	public String getAnchor_cover() {
		return anchor_cover;
	}
	public void setAnchor_cover(String anchor_cover) {
		this.anchor_cover = anchor_cover;
	}
	public int getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(int is_vip) {
		this.is_vip = is_vip;
	}
	public int getIs_fee() {
		return is_fee;
	}
	public void setIs_fee(int is_fee) {
		this.is_fee = is_fee;
	}
	public int getFee_count() {
		return fee_count;
	}
	public void setFee_count(int fee_count) {
		this.fee_count = fee_count;
	}
	public int getIs_update() {
		return is_update;
	}
	public void setIs_update(int is_update) {
		this.is_update = is_update;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public int getIs_secret() {
		return is_secret;
	}
	public void setIs_secret(int is_secret) {
		this.is_secret = is_secret;
	}			
}

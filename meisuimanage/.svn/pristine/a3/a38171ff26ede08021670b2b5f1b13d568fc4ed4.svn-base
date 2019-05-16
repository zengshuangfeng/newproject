package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Report.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 下午2:46:09
 */

public class Report {
	private int id;//举报id
	private long uid;//被举报人uid
	private long f_uuid;//被举报人房间号
	private String nickname;//被举报人昵称
	private String pic;//截图
	private long o_uid;//举报人uid
	private String o_nickname;//举报人昵称
	private String create_time;//举报时间
	private int islook;//是否处理 1是 0否
	private int is_anchor;//被举报的人是否是主播 1是 0否
	private String w_name;//编辑人员
	private String update_time;//编辑时间
	private Date forbid_start_time;//禁言开始时间
	private int forbid_hour;//禁言小时数 0表示永久性
	private int is_forbid;//是否禁言 1是 0否
	private int level;//用户等级
	private int sex;//用户性别
	private long balance_virtual;//用户钻石数
	private long total_anchor_virtual;//用户魅力数
	private int video_count;//用户视频数
	private int report_count;//举报次数
	private int key_count;//钥匙数
	private int head_lock;//头像锁定 1是 0否
	private int nickname_lock;//昵称锁定 1是 0否
	private String head;//用户头像
	private int follower_count;//粉丝数
	private int is_blocked;//是否封号 1是0否
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
	public String getPic() {
		return StringEscapeUtils.escapeHtml4(pic);
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public long getO_uid() {
		return o_uid;
	}
	public void setO_uid(long o_uid) {
		this.o_uid = o_uid;
	}
	public String getO_nickname() {
		return StringEscapeUtils.escapeHtml4(o_nickname);
	}
	public void setO_nickname(String o_nickname) {
		this.o_nickname = o_nickname;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public int getIslook() {
		return islook;
	}
	public void setIslook(int islook) {
		this.islook = islook;
	}
	public int getIs_anchor() {
		return is_anchor;
	}
	public void setIs_anchor(int is_anchor) {
		this.is_anchor = is_anchor;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = AuthUtil.formatDateToString(update_time);
	}public Date getForbid_start_time() {
		return forbid_start_time;
	}
	public void setForbid_start_time(Date forbid_start_time) {
		this.forbid_start_time = forbid_start_time;
	}
	public int getForbid_hour() {
		return forbid_hour;
	}
	public void setForbid_hour(int forbid_hour) {
		this.forbid_hour = forbid_hour;
	}
	public int getIs_forbid() {
		return is_forbid;
	}
	public void setIs_forbid(int is_forbid) {
		this.is_forbid = is_forbid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public long getBalance_virtual() {
		return balance_virtual;
	}
	public void setBalance_virtual(long balance_virtual) {
		this.balance_virtual = balance_virtual;
	}
	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}
	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}
	public int getVideo_count() {
		return video_count;
	}
	public void setVideo_count(int video_count) {
		this.video_count = video_count;
	}
	public int getReport_count() {
		return report_count;
	}
	public void setReport_count(int report_count) {
		this.report_count = report_count;
	}
	public int getKey_count() {
		return key_count;
	}
	public void setKey_count(int key_count) {
		this.key_count = key_count;
	}
	public int getHead_lock() {
		return head_lock;
	}
	public void setHead_lock(int head_lock) {
		this.head_lock = head_lock;
	}
	public int getNickname_lock() {
		return nickname_lock;
	}
	public void setNickname_lock(int nickname_lock) {
		this.nickname_lock = nickname_lock;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}
	public int getIs_blocked() {
		return is_blocked;
	}
	public void setIs_blocked(int is_blocked) {
		this.is_blocked = is_blocked;
	}
}

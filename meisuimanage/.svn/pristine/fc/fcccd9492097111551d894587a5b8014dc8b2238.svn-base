package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Anchor_Invite_Config.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月18日 下午3:45:25
 */

public class Anchor_Invite_Config {
	private int id;
	private long uid;//主播uid
	private long f_uuid;//主播房间号
	private String nickname;//主播昵称
	private int reward_count;//已送出钻石数
	private int max_reward;//最大随机钻石数
	private int min_reward;//最小随机钻石数
	private Date create_time;//创建时间
	private String update_time;//更新时间
	private int is_online;//是否开启 1是 0否
	private String w_name;//编辑人员
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
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getReward_count() {
		return reward_count;
	}
	public void setReward_count(int reward_count) {
		this.reward_count = reward_count;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public int getMax_reward() {
		return max_reward;
	}
	public void setMax_reward(int max_reward) {
		this.max_reward = max_reward;
	}
	public int getMin_reward() {
		return min_reward;
	}
	public void setMin_reward(int min_reward) {
		this.min_reward = min_reward;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = AuthUtil.formatDateToString(update_time);
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
}

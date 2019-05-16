package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Gameroom_Record.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午2:15:45
 */

public class Gameroom_Record {
	private int id;
	private long uid;//主播uid
	private long f_uuid;//主播房间号
	private String t;//主播token
	private String nickname;//主播昵称
	private long current_bet_count;//当前押注
	private long current_user_count;//当前押注人数
	private long max_bet_count;//最大押注
	private double current_percent;//房间当前比例
	private long current_virtual_count;//当前房间资金池
	private String create_time;//开播时间
	private String end_time;//开播结束时间
	private int is_intervene;//是否干预 1是 0否
	private int s_id;//广场表id
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
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getCurrent_bet_count() {
		return current_bet_count;
	}
	public void setCurrent_bet_count(long current_bet_count) {
		this.current_bet_count = current_bet_count;
	}
	public long getCurrent_user_count() {
		return current_user_count;
	}
	public void setCurrent_user_count(long current_user_count) {
		this.current_user_count = current_user_count;
	}
	public long getMax_bet_count() {
		return max_bet_count;
	}
	public void setMax_bet_count(long max_bet_count) {
		this.max_bet_count = max_bet_count;
	}
	public double getCurrent_percent() {
		return current_percent;
	}
	public void setCurrent_percent(double current_percent) {
		this.current_percent = current_percent;
	}
	public long getCurrent_virtual_count() {
		return current_virtual_count;
	}
	public void setCurrent_virtual_count(long current_virtual_count) {
		this.current_virtual_count = current_virtual_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time,"yyyy-MM-dd");
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = AuthUtil.formatDateToString(end_time);
	}
	public int getIs_intervene() {
		return is_intervene;
	}
	public void setIs_intervene(int is_intervene) {
		this.is_intervene = is_intervene;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
}

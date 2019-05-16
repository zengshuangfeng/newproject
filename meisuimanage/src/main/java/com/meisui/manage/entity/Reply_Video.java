package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Reply_Video.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月5日 下午3:30:14
 */

public class Reply_Video {
	private int id;//回复视频id
	private String cover;//视频封面
	private String video;//视频文件
	private int seconds;//秒数
	private long s_uid;//发送者的uid
	private String s_token;//发送者token
	private String s_nickname;//发送者昵称
	private long r_uid;//接收者的uid
	private String r_token;//接收者token
	private String r_nickname;//接收者昵称
	private String create_time;//回复时间
	private String sex;//发送者性别
	private int user_is_blocked;//是否已封号 1是 0否
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public long getS_uid() {
		return s_uid;
	}
	public void setS_uid(long s_uid) {
		this.s_uid = s_uid;
	}
	public String getS_token() {
		return s_token;
	}
	public void setS_token(String s_token) {
		this.s_token = s_token;
	}
	public String getS_nickname() {
		return s_nickname;
	}
	public void setS_nickname(String s_nickname) {
		this.s_nickname = s_nickname;
	}
	public long getR_uid() {
		return r_uid;
	}
	public void setR_uid(long r_uid) {
		this.r_uid = r_uid;
	}
	public String getR_token() {
		return r_token;
	}
	public void setR_token(String r_token) {
		this.r_token = r_token;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public String getR_nickname() {
		return r_nickname;
	}
	public void setR_nickname(String r_nickname) {
		this.r_nickname = r_nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getUser_is_blocked() {
		return user_is_blocked;
	}
	public void setUser_is_blocked(int user_is_blocked) {
		this.user_is_blocked = user_is_blocked;
	}
}

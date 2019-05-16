package com.meisui.manage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Video.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月5日 下午3:11:20
 */

public class User_Video {
	private int id;//视频id
	private String cover;//封面
	private String video;//视频文件
	private int seconds;//秒数
	private int is_recommend;//是否推荐 1是 0否
	private int status;//状态 0未审核 1通过 2屏蔽
	private String create_time;//发布时间
	private String token;//用户token
	private String nickname;//用户昵称
	private String head;//用户头像
	private String sex;//用户性别
	private long uid;//用户uid
	private long f_uuid;//用户房间号
	private int user_is_blocked;//用户是否封号 1是 0佛
	private int source;//来源 0用户发布 1美拍
	private List<String> picList = new ArrayList<String>();//截帧
	private String w_name;//操作人
	private double size;//文件大小 M
	private int isvest;// -1 全部 0普通用户 1马甲用户
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
	public int getIs_recommend() {
		return is_recommend;
	}
	public void setIs_recommend(int is_recommend) {
		this.is_recommend = is_recommend;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHead() {
		return StringEscapeUtils.escapeHtml4(head);
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public int getUser_is_blocked() {
		return user_is_blocked;
	}
	public void setUser_is_blocked(int user_is_blocked) {
		this.user_is_blocked = user_is_blocked;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public List<String> getPicList() {
		return picList;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public int getIsvest() {
		return isvest;
	}
	public void setIsvest(int isvest) {
		this.isvest = isvest;
	}
}

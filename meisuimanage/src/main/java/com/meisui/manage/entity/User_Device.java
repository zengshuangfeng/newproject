package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Device.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月3日 下午6:10:47
 */

public class User_Device {
	private int id;
	private String t1;
	private String token;
	private int uid;
	private String udid;//设备udid
	private long ip;//IP
	private String longitude;//经度
	private String latitude;//纬度
	private String apn;//移动商
	private int province;//省
	private int city;//市
	private String network;//网络
	private String login_time;//登录时间
	private String ip_string;
	private String ip_address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUdid() {
		return udid;
	}
	public long getIp() {
		return ip;
	}
	public void setIp(long ip) {
		this.ip = ip;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getApn() {
		return StringEscapeUtils.escapeHtml4(apn);
	}
	public void setApn(String apn) {
		this.apn = apn;
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
	public String getNetwork() {
		return StringEscapeUtils.escapeHtml4(network);
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = AuthUtil.formatDateToString(login_time);
	}
	public String getIp_string() {
		return ip_string;
	}
	public void setIp_string(String ip_string) {
		this.ip_string = ip_string;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
}

package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Extra.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月3日 下午6:08:44
 */

public class User_Extra {
	private int id;
	private int uid;
	private String driveid;//设备ID
	private String t1;
	private String mobile_model;//机型
	private String mobile_brand;//手机品牌
	private String channel;//渠道
	private String system_version;//系统版本
	private String software_version;//软件版本
	private String first_time;//首次登录时间
	private int platform;//类型 0 ios 1 android
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getDriveid() {
		return driveid;
	}
	public void setDriveid(String driveid) {
		this.driveid = driveid;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getMobile_model() {
		return StringEscapeUtils.escapeHtml4(mobile_model);
	}
	public void setMobile_model(String mobile_model) {
		this.mobile_model = mobile_model;
	}
	public String getMobile_brand() {
		return StringEscapeUtils.escapeHtml4(mobile_brand);
	}
	public void setMobile_brand(String mobile_brand) {
		this.mobile_brand = mobile_brand;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSystem_version() {
		return StringEscapeUtils.escapeHtml4(system_version);
	}
	public void setSystem_version(String system_version) {
		this.system_version = system_version;
	}
	public String getSoftware_version() {
		return StringEscapeUtils.escapeHtml4(software_version);
	}
	public void setSoftware_version(String software_version) {
		this.software_version = software_version;
	}
	public String getFirst_time() {
		return first_time;
	}
	public void setFirst_time(Date first_time) {
		this.first_time =AuthUtil.formatDateToString(first_time);
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
}

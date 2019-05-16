package com.meisui.manage.entity;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Forbid.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午5:58:35
 */

public class User_Forbid {
	private int id;
	private long uid;//用户uid
	private String f_uuid;//用户房间号
	private String nickname;//昵称
	private String start_time;//禁言开始时间
	private String end_time;//禁言结束时间
	private String update_time;//禁言最后处理时间
	private Date create_time;//禁言创建时间
	private String w_name;//操作人员
	private String status_name;//状态名称
	private int is_from;//是否来自全局禁言
	public int getIs_from() {
		return is_from;
	}
	public void setIs_from(int is_from) {
		this.is_from = is_from;
	}
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
	public String getF_uuid() {
		return StringEscapeUtils.escapeHtml4(f_uuid);
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = AuthUtil.formatDateToString(start_time);
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = AuthUtil.formatDateToString(end_time);
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = AuthUtil.formatDateToString(update_time);
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getStatus_name() throws ParseException {
		if(is_from!=1)//不是来自全局禁言
		{
			Date current_time = new Date();
			if(AuthUtil.formatStringToDate(end_time).after(current_time))
			{
				status_name = "禁言中";
			}
			if(AuthUtil.formatStringToDate(end_time).before(current_time))
			{
				status_name = "可发言";
			}
		}
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
}

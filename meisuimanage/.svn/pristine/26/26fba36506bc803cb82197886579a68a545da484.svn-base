package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.forman.foundation.library.DateUtils;
import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Live_Time.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月5日 上午11:22:26
 */

public class Live_Time {
	private int id;//
	private String s_time;//开始时间
	private String e_time;//结束时间
	private String w_name;//编辑人员
	private Date create_time;//创建时间
	private String update_time;//更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(Date s_time) {
		this.s_time = AuthUtil.formatDateToString(s_time, "HH:mm:ss");
	}
	public String getE_time() {
		return e_time;
	}
	public void setE_time(Date e_time) {
		this.e_time = AuthUtil.formatDateToString(e_time, "HH:mm:ss");
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
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
		this.update_time = DateUtils.sdf2.format(update_time);
	}
}

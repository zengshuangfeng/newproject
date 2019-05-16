package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Change_Activity.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月11日 下午2:59:53
 */

public class Change_Activity {
	private int id;//充值活动表id
	private int change_id;//充值套餐表id
	private int change_rmb;//原价
	private int activity_rmb;//活动价格
	private int virtual_count;//钻石数
	private int is_first;//是否首充 1是 0否
	private String start_time;//开始时间
	private String end_time;//结束时间
	private String w_name;//编辑人员
	private int is_online;//状态 1正常 0 关闭 
	private Date create_time;//创建时间
	private String update_time;//更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChange_id() {
		return change_id;
	}
	public void setChange_id(int change_id) {
		this.change_id = change_id;
	}
	public int getActivity_rmb() {
		return activity_rmb;
	}
	public void setActivity_rmb(int activity_rmb) {
		this.activity_rmb = activity_rmb;
	}
	public int getChange_rmb() {
		return change_rmb;
	}
	public void setChange_rmb(int change_rmb) {
		this.change_rmb = change_rmb;
	}
	public int getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(int virtual_count) {
		this.virtual_count = virtual_count;
	}
	public int getIs_first() {
		return is_first;
	}
	public void setIs_first(int is_first) {
		this.is_first = is_first;
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
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
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
}

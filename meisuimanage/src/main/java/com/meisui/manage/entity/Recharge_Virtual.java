package com.meisui.manage.entity;

import java.util.Date;

/**
 * <p>文件名称：Recharge.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午4:33:23
 */

public class Recharge_Virtual {
	private int id;
	private int operate_center_id;//运营中心id
	private int uid;//用户id
	private long f_uuid;//房间号
	private long virtual_count;//钻石数
	private Date create_time;//充值时间
	private String w_name;//赠送人员
	private String nickname;//用户昵称
	private String operate_name;//所属运营中心昵称
	
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOperate_name() {
		return operate_name;
	}
	public void setOperate_name(String operate_name) {
		this.operate_name = operate_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperate_center_id() {
		return operate_center_id;
	}
	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public long getVirtual_count() {
		return virtual_count;
	}
	public void setVirtual_count(long virtual_count) {
		this.virtual_count = virtual_count;
	}

	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	
	
	
}

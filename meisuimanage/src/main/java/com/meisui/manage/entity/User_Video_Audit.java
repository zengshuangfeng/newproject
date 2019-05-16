package com.meisui.manage.entity;

import java.util.Date;

/**
 * <p>文件名称：User_Video_Audit.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月28日 上午10:09:48
 */

public class User_Video_Audit {
	private int admin_id;//后台管理员id
	private int video_id;//视频id
	private Date get_time;//拉取时间
	private int is_audit;//是否已审核 0否 1是
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	public Date getGet_time() {
		return get_time;
	}
	public void setGet_time(Date get_time) {
		this.get_time = get_time;
	}
	public int getIs_audit() {
		return is_audit;
	}
	public void setIs_audit(int is_audit) {
		this.is_audit = is_audit;
	}
}	

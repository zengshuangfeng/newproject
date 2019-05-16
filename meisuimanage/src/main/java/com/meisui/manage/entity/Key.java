package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Key.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月15日 下午6:42:10
 */

public class Key {
	private int type;//类型 0送礼 1兑换 2奖励
	private int key_count;//钥匙数
	private String create_time;//创建时间
	private String remark;//备注 例：收礼人：薄荷糖糖  礼物：超级蛋（888钻）
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getKey_count() {
		return key_count;
	}
	public void setKey_count(int key_count) {
		this.key_count = key_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

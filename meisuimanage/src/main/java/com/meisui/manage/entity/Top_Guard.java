package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：Top_Guard.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月29日 上午9:50:03
 */

public class Top_Guard {
	private long uid;//用户uid
	private String nickname;//昵称
	private long give_count;//贡献魅力值
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getGive_count() {
		return give_count;
	}
	public void setGive_count(long give_count) {
		this.give_count = give_count;
	}
}

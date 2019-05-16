package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Attention.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月29日 上午9:46:11
 */

public class Attention {
	private long uid;//用户uid
	private String nickname;//昵称
	private String create_time;//关注时间
	private long attention_uid;//关注对象UID
	private String attention_nickname;//关注对象昵称
	private int is_attention;//是否关注0关注1取消关注
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public long getAttention_uid() {
		return attention_uid;
	}
	public void setAttention_uid(long attention_uid) {
		this.attention_uid = attention_uid;
	}
	public String getAttention_nickname() {
		return StringEscapeUtils.escapeHtml4(attention_nickname);
	}
	public void setAttention_nickname(String attention_nickname) {
		this.attention_nickname = attention_nickname;
	}
	public int getIs_attention() {
		return is_attention;
	}
	public void setIs_attention(int is_attention) {
		this.is_attention = is_attention;
	}
}

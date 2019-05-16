package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：User_Info.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月13日 下午2:10:46
 */

public class User_Info {
	private int id;
	private long uid;//用户id
	private int attention_count;//关注数
	private int follower_count;//粉丝数
	private long total_gift;//累积收到的礼物总数
	private long total_anchor_virtual;//累积收到直播虚拟币总数
	private long surplus_anchor_virtual;//实际直播虚拟币数
	private int wealth_level;//财富等级
	private int divide_proportion;//分成比例 例：50 表示50%
	private int one_divide_proportion;//一对一分成比例 例：50 表示50%
	private int withdraw_type;//0支付宝
	private String withdraw_pass;//提现账户
	private String withdraw_name;//账户名
	private long score;//积分
	private long total_video_virtual;//配配视频魅力值
	private long surplus_video_virtual;//实际陪陪视频虚拟币数（剩余）
	private long total_secret_virtual;//私密累积魅力值
	private long surplus_secret_virtual;//私密剩余魅力值
	private long total_one_virtual;//累积收到一对一虚拟币总数
	private long surplus_one_virtual;//实际收到一对一虚拟币总数
	private int total_live_virtual;//直播累计所得魅力值
	private int total_promote_virtual;//推广累计所得魅力值
	private int post_count;//帖子数
	private int grading;//段位
	private int grading_score;//段位积分
	
	public int getGrading() {
		return grading;
	}
	public void setGrading(int grading) {
		this.grading = grading;
	}
	public int getGrading_score() {
		return grading_score;
	}
	public void setGrading_score(int grading_score) {
		this.grading_score = grading_score;
	}
	public int getPost_count() {
		return post_count;
	}
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}
	public int getTotal_live_virtual() {
		return total_live_virtual;
	}
	public void setTotal_live_virtual(int total_live_virtual) {
		this.total_live_virtual = total_live_virtual;
	}
	public int getTotal_promote_virtual() {
		return total_promote_virtual;
	}
	public void setTotal_promote_virtual(int total_promote_virtual) {
		this.total_promote_virtual = total_promote_virtual;
	}
	public void setSurplus_anchor_virtual(long surplus_anchor_virtual) {
		this.surplus_anchor_virtual = surplus_anchor_virtual;
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
	public int getAttention_count() {
		return attention_count;
	}
	public void setAttention_count(int attention_count) {
		this.attention_count = attention_count;
	}
	public int getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}
	public long getTotal_gift() {
		return total_gift;
	}
	public void setTotal_gift(long total_gift) {
		this.total_gift = total_gift;
	}
	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}
	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}
	public long getSurplus_anchor_virtual() {
		return surplus_anchor_virtual;
	}
	public void setSurplus_anchor_virtual(int surplus_anchor_virtual) {
		this.surplus_anchor_virtual = surplus_anchor_virtual;
	}
	public int getWealth_level() {
		return wealth_level;
	}
	public void setWealth_level(int wealth_level) {
		this.wealth_level = wealth_level;
	}
	public int getDivide_proportion() {
		return divide_proportion;
	}
	public void setDivide_proportion(int divide_proportion) {
		this.divide_proportion = divide_proportion;
	}
	public int getOne_divide_proportion() {
		return one_divide_proportion;
	}
	public void setOne_divide_proportion(int one_divide_proportion) {
		this.one_divide_proportion = one_divide_proportion;
	}
	public int getWithdraw_type() {
		return withdraw_type;
	}
	public void setWithdraw_type(int withdraw_type) {
		this.withdraw_type = withdraw_type;
	}
	public String getWithdraw_pass() {
		return StringEscapeUtils.escapeHtml4(withdraw_pass);
	}
	public void setWithdraw_pass(String withdraw_pass) {
		this.withdraw_pass = withdraw_pass;
	}
	public String getWithdraw_name() {
		return StringEscapeUtils.escapeHtml4(withdraw_name);
	}
	public void setWithdraw_name(String withdraw_name) {
		this.withdraw_name = withdraw_name;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public long getTotal_video_virtual() {
		return total_video_virtual;
	}
	public void setTotal_video_virtual(long total_video_virtual) {
		this.total_video_virtual = total_video_virtual;
	}
	public long getSurplus_video_virtual() {
		return surplus_video_virtual;
	}
	public void setSurplus_video_virtual(long surplus_video_virtual) {
		this.surplus_video_virtual = surplus_video_virtual;
	}
	public long getTotal_secret_virtual() {
		return total_secret_virtual;
	}
	public void setTotal_secret_virtual(long total_secret_virtual) {
		this.total_secret_virtual = total_secret_virtual;
	}
	public long getSurplus_secret_virtual() {
		return surplus_secret_virtual;
	}
	public void setSurplus_secret_virtual(long surplus_secret_virtual) {
		this.surplus_secret_virtual = surplus_secret_virtual;
	}
	public long getTotal_one_virtual() {
		return total_one_virtual;
	}
	public void setTotal_one_virtual(long total_one_virtual) {
		this.total_one_virtual = total_one_virtual;
	}
	public long getSurplus_one_virtual() {
		return surplus_one_virtual;
	}
	public void setSurplus_one_virtual(long surplus_one_virtual) {
		this.surplus_one_virtual = surplus_one_virtual;
	}
}

package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：Spending.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午4:18:28
 */

public class Spending {
	private String create_time;//送礼时间
	private int gift_id;//礼物id
	private String gift_name;//礼物名称
	private int spending_virtual;//花费钻石
	private long spending_uid;//收礼者uid
	private long rev_f_uuid;//收礼方房间号
	private long uid;//送礼者uid
	private long send_f_uuid;//送礼方房间号
	private String spending_nickname;//收礼者昵称
	private String nickname;//昵称
	private int type;//类型 0礼物（普通直播）  1付费播  2私密播礼物  3一对一赠送
	private int send_level;//赠送者等级
	private int giftprize;//礼物价值
	private Date gifttime;//送礼时间
	private String f_uuid;//房间号
	private int level;//送礼方等级
	private int anchor_divide;//主播魅力值分成占比
	private int promoter_divide;//推广魅力值分成占比
	private int is_box;//是否来源宝箱 1是 0否
	private int operate_center_id;//赠送者运营中心id
	private int agent_id;//赠送者代理id
	private int is_type;
	private String operate_center_name;
	private String agent_name;
	private int anchor_uid;//主播uid
	private String anchor_name;//主播昵称
	private Date start_time;
	private Date end_time;
	private int status;//守护状态 1正常 0过期
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getAnchor_uid() {
		return anchor_uid;
	}
	public void setAnchor_uid(int anchor_uid) {
		this.anchor_uid = anchor_uid;
	}
	public String getAnchor_name() {
		return anchor_name;
	}
	public void setAnchor_name(String anchor_name) {
		this.anchor_name = anchor_name;
	}
	public String getOperate_center_name() {
		return operate_center_name;
	}
	public void setOperate_center_name(String operate_center_name) {
		this.operate_center_name = operate_center_name;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public int getOperate_center_id() {
		return operate_center_id;
	}
	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
	}
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public int getIs_box() {
		return is_box;
	}
	public void setIs_box(int is_box) {
		this.is_box = is_box;
	}
	public int getPromoter_divide() {
		return promoter_divide;
	}
	public void setPromoter_divide(int promoter_divide) {
		this.promoter_divide = promoter_divide;
	}
	public int getAnchor_divide() {
		return anchor_divide;
	}
	public void setAnchor_divide(int anchor_divide) {
		this.anchor_divide = anchor_divide;
	}
	public int getGiftprize() {
		return giftprize;
	}
	public void setGiftprize(int giftprize) {
		this.giftprize = giftprize;
	}
	public Date getGifttime() {
		return gifttime;
	}
	public void setGifttime(Date gifttime) {
		this.gifttime = gifttime;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public int getGift_id() {
		return gift_id;
	}
	public void setGift_id(int gift_id) {
		this.gift_id = gift_id;
	}
	public String getGift_name() {
		return StringEscapeUtils.escapeHtml4(gift_name);
	}
	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}
	public int getSpending_virtual() {
		return spending_virtual;
	}
	public void setSpending_virtual(int spending_virtual) {
		this.spending_virtual = spending_virtual;
	}
	public long getSpending_uid() {
		return spending_uid;
	}
	public void setSpending_uid(long spending_uid) {
		this.spending_uid = spending_uid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getSpending_nickname() {
		return StringEscapeUtils.escapeHtml4(spending_nickname);
	}
	public void setSpending_nickname(String spending_nickname) {
		this.spending_nickname = spending_nickname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getRev_f_uuid() {
		return rev_f_uuid;
	}
	public void setRev_f_uuid(long rev_f_uuid) {
		this.rev_f_uuid = rev_f_uuid;
	}
	public long getSend_f_uuid() {
		return send_f_uuid;
	}
	public void setSend_f_uuid(long send_f_uuid) {
		this.send_f_uuid = send_f_uuid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSend_level() {
		return send_level;
	}
	public void setSend_level(int send_level) {
		this.send_level = send_level;
	}
	public int getIs_type() {
		return is_type;
	}
	public void setIs_type(int is_type) {
		this.is_type = is_type;
	}	
	
}

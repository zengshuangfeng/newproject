package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.forman.foundation.library.DateUtils;

/**
 * <p>文件名称：User_Anchor.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月13日 下午2:08:10
 */

public class User_Anchor {
	private int id;//主播表id
	private String anchor_cover;//正式封面
	private String anchor_notice;//主播公告
	private int anchor_state;//主播状态0休息1直播2禁播
	private long uid;//用户id
	private String remark;//备注
	private String nickname;//昵称
	private int divide_proportion;//分成比例 例：50 表示50%
	private String withdraw_pass;//提现账号
	private long total_anchor_virtual;//累积收到直播虚拟币总数
	private long surplus_anchor_virtual;//实际收到直播虚拟币总数
	private long follower_count;//粉丝数
	private Date create_time;//创建时间
	private String update_time;//更新时间
	private String w_name;//编辑人员
	private String last_anchor_time;//最近直播时间
	private long f_uuid;//主播房间号id
	private int anchor_type;//0无权限 1只能是秀场 1只能是游戏 2全部
	private long balance_virtual;//余额
	private int union_id;//工会表id
	private String union_name;//工会名称
	private int is_trial;//是否试播 1是 0否
	private int key_count;//钥匙数
	private String birthday_pic;//生日卡片图
	private long spending_virtual;
	private int type;//类型 1:A/才艺累 2:B类
	private long total_stake;//游戏流水
	private long get_stake;//抽水？
	private int is_vip; //是否是VIP 1是0否
	private long total_one_virtual;//累积收到一对一虚拟币总数
	private long surplus_one_virtual;//实际收到一对一虚拟币总数
	private int one_disturb;//是否开启免打扰 1是 0否
	private int one_divide_proportion;//一对一分成比例 例：50 表示50%
	private int total_live_virtual;//直播累计所得魅力值
	private int total_promote_virtual;//推广累计所得魅力值
	private int operate_center_id;//运营中心id
	private String operateName;//运营中心名称
	private int operateid;//运营中心id
	private int operate_divide;//运营中心分成占比
	private int agent_divide;//代理分成占比
	private int agent_id;//代理id
	private String agentName;//代理名称
	private int ispromoter;//0 不是推广员，1是推广员
	private int is_virtual;//是否为虚拟号
	
	
	public int getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(int is_virtual) {
		this.is_virtual = is_virtual;
	}
	public int getIspromoter() {
		return ispromoter;
	}
	public void setIspromoter(int ispromoter) {
		this.ispromoter = ispromoter;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public int getOperate_divide() {
		return operate_divide;
	}
	public void setOperate_divide(int operate_divide) {
		this.operate_divide = operate_divide;
	}
	public int getAgent_divide() {
		return agent_divide;
	}
	public void setAgent_divide(int agent_divide) {
		this.agent_divide = agent_divide;
	}
	public int getOperateid() {
		return operateid;
	}
	public void setOperateid(int operateid) {
		this.operateid = operateid;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public int getOperate_center_id() {
		return operate_center_id;
	}
	public void setOperate_center_id(int operate_center_id) {
		this.operate_center_id = operate_center_id;
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
	public long getSpending_virtual() {
        return spending_virtual;
    }
    public void setSpending_virtual(long spending_virtual) {
        this.spending_virtual = spending_virtual;
    }
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnchor_cover() {
		return anchor_cover;
	}
	public void setAnchor_cover(String anchor_cover) {
		this.anchor_cover = anchor_cover;
	}
	public String getAnchor_notice() {
		return anchor_notice;
	}
	public void setAnchor_notice(String anchor_notice) {
		this.anchor_notice = anchor_notice;
	}
	public int getAnchor_state() {
		return anchor_state;
	}
	public void setAnchor_state(int anchor_state) {
		this.anchor_state = anchor_state;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getRemark() {
		return StringEscapeUtils.escapeHtml4(remark);
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getDivide_proportion() {
		return divide_proportion;
	}
	public void setDivide_proportion(int divide_proportion) {
		this.divide_proportion = divide_proportion;
	}
	public String getWithdraw_pass() {
		return withdraw_pass;
	}
	public void setWithdraw_pass(String withdraw_pass) {
		this.withdraw_pass = withdraw_pass;
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
	public void setSurplus_anchor_virtual(long surplus_anchor_virtual) {
		this.surplus_anchor_virtual = surplus_anchor_virtual;
	}
	public long getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(long follower_count) {
		this.follower_count = follower_count;
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
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = StringEscapeUtils.escapeHtml4(w_name);
	}
	public String getLast_anchor_time() {
		return last_anchor_time;
	}
	public void setLast_anchor_time(String last_anchor_time) {
		this.last_anchor_time = last_anchor_time;
	}
	public long getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(long f_uuid) {
		this.f_uuid = f_uuid;
	}
	public int getAnchor_type() {
		return anchor_type;
	}
	public void setAnchor_type(int anchor_type) {
		this.anchor_type = anchor_type;
	}
	public long getBalance_virtual() {
		return balance_virtual;
	}
	public void setBalance_virtual(long balance_virtual) {
		this.balance_virtual = balance_virtual;
	}
	public int getUnion_id() {
		return union_id;
	}
	public void setUnion_id(int union_id) {
		this.union_id = union_id;
	}
	public String getUnion_name() {
		return StringEscapeUtils.escapeHtml4(union_name);
	}
	public void setUnion_name(String union_name) {
		this.union_name = union_name;
	}
	public int getIs_trial() {
		return is_trial;
	}
	public void setIs_trial(int is_trial) {
		this.is_trial = is_trial;
	}
	public int getKey_count() {
		return key_count;
	}
	public void setKey_count(int key_count) {
		this.key_count = key_count;
	}
	public String getBirthday_pic() {
		return birthday_pic;
	}
	public void setBirthday_pic(String birthday_pic) {
		this.birthday_pic = birthday_pic;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getTotal_stake() {
		return total_stake;
	}
	public void setTotal_stake(long total_stake) {
		this.total_stake = total_stake;
	}
	public long getGet_stake() {
		return get_stake;
	}
	public void setGet_stake(long get_stake) {
		this.get_stake = get_stake;
	}
	public int getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(int is_vip) {
		this.is_vip = is_vip;
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
	public int getOne_disturb() {
		return one_disturb;
	}
	public void setOne_disturb(int one_disturb) {
		this.one_disturb = one_disturb;
	}
	public int getOne_divide_proportion() {
		return one_divide_proportion;
	}
	public void setOne_divide_proportion(int one_divide_proportion) {
		this.one_divide_proportion = one_divide_proportion;
	}	
}

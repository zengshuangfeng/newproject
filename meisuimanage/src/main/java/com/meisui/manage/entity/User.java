package com.meisui.manage.entity;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.forman.foundation.library.DateUtils;
import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：T_User.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月12日 下午2:47:40
 */

public class User {
	private long id;//用户id
	private String nickname;//昵称
	private String head;//图像
	private int sex;//性别
	private String birthday;//生日
	private String constellation;//星座
	private String f_uuid;//平台id
	private String character_autograph;//个性签名
	private int province;//省份
	private int city;//城市
	private int sexuality;//性取向
	private int is_anchor;//是否主播0不是1是
	private String profession;//职业
	private String video;//短视频秀逗号隔开
	private int is_blocked;//否封号0无1封号
	private int istest;//0不是测试号1是测试号
	private int isvest;//是否是马甲号 1:是 0:否'
	private Date vip_time;//VIP到期时间
	private String register_time;//注册时间
	private long balance_virtual;//钻石余额
	private long total_balance_virtual;//总充值
	private String last_use_time;//最近使用时间
	private int level;//等级
	private String last_login_time;//最近登录时间
	private int login_type;//登录方式 0微博,1qq, 2微信,3支付宝,4手机
	private String remark;//备注
	private Date forbid_start_time;//禁言开始时间
	private int forbid_hour;//禁言小时数 0表示永久性
	private int is_forbid;//是否禁言 1是 0否
	private String w_name;//禁言操作者
	private Date update_time;//禁言更新时间
	private int key_count;//钥匙数
	private int head_lock;//头像锁定 1是 0否
	private int nickname_lock;//昵称锁定 1是0否
	private int video_count;//视频数
	private int follower_count;//粉丝数
	private int reply_count;//视频回复数
	private String last_video_time;//最后发布视频时间
	private long total_anchor_virtual;//魅力值
	private long total_video_virtual;//配配视频魅力值
	private String area;//地区
	private long total_spending;//累计消费钻石
	private String one_head;//一对一头像
	private String tel;
	private String realname;
	private int operate_center_id;
	private int agent_id;
	private int agent_promoter_id;
	private String operateName;
	private int sum_recharge;//总充值
	private long sum_give;//总送礼
	private String invite_code;
	private int is_virtual;//是否开启为虚拟账号 0不是 1是
	private String agentName;//代理名称
	private int is_promote;//是否推广员 0不是，1是
	private int divide;
	private Date end_time;
	private String operate_center_name;
	private int settlement_type;
	private Date start_time;
	private Long surplus_virtual;//本次结算魅力值
	private String promoter_f_uuid;
	

	public String getPromoter_f_uuid() {
		return promoter_f_uuid;
	}
	public void setPromoter_f_uuid(String promoter_f_uuid) {
		this.promoter_f_uuid = promoter_f_uuid;
	}
	public Long getSurplus_virtual() {
		return surplus_virtual;
	}
	public void setSurplus_virtual(Long surplus_virtual) {
		this.surplus_virtual = surplus_virtual;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public int getDivide() {
		return divide;
	}
	public void setDivide(int divide) {
		this.divide = divide;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getOperate_center_name() {
		return operate_center_name;
	}
	public void setOperate_center_name(String operate_center_name) {
		this.operate_center_name = operate_center_name;
	}
	public int getSettlement_type() {
		return settlement_type;
	}
	public void setSettlement_type(int settlement_type) {
		this.settlement_type = settlement_type;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	
	public int getIs_promote() {
		return is_promote;
	}
	public void setIs_promote(int is_promote) {
		this.is_promote = is_promote;
	}
	public int getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(int is_virtual) {
		this.is_virtual = is_virtual;
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
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public int getAgent_promoter_id() {
		return agent_promoter_id;
	}
	public void setAgent_promoter_id(int agent_promoter_id) {
		this.agent_promoter_id = agent_promoter_id;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return StringEscapeUtils.escapeHtml4(nickname);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHead() {
		return StringEscapeUtils.escapeHtml4(head);
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = DateUtils.sdf.format(birthday);
	}
	public String getConstellation() {
		return StringEscapeUtils.escapeHtml4(constellation);
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getF_uuid() {
		return f_uuid;
	}
	public void setF_uuid(String f_uuid) {
		this.f_uuid = f_uuid;
	}
	public String getCharacter_autograph() {
		return StringEscapeUtils.escapeHtml4(character_autograph);
	}
	public void setCharacter_autograph(String character_autograph) {
		this.character_autograph = character_autograph;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getSexuality() {
		return sexuality;
	}
	public void setSexuality(int sexuality) {
		this.sexuality = sexuality;
	}
	public int getIs_anchor() {
		return is_anchor;
	}
	public void setIs_anchor(int is_anchor) {
		this.is_anchor = is_anchor;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public int getIs_blocked() {
		return is_blocked;
	}
	public void setIs_blocked(int is_blocked) {
		this.is_blocked = is_blocked;
	}
	public int getIstest() {
		return istest;
	}
	public void setIstest(int istest) {
		this.istest = istest;
	}
	public int getIsvest() {
		return isvest;
	}
	public void setIsvest(int isvest) {
		this.isvest = isvest;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = DateUtils.sdf2.format(register_time);
	}
	public long getBalance_virtual() {
		return balance_virtual;
	}
	public void setBalance_virtual(long balance_virtual) {
		this.balance_virtual = balance_virtual;
	}
	public long getTotal_balance_virtual() {
		return total_balance_virtual;
	}
	public void setTotal_balance_virtual(long total_balance_virtual) {
		this.total_balance_virtual = total_balance_virtual;
	}
	public String getLast_use_time() {
		return last_use_time;
	}
	public void setLast_use_time(Date last_use_time) {
		this.last_use_time = AuthUtil.formatDateToString(last_use_time);
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = AuthUtil.formatDateToString(last_login_time);
	}
	public int getLogin_type() {
		return login_type;
	}
	public void setLogin_type(int login_type) {
		this.login_type = login_type;
	}
	public String getRemark() {
		return StringEscapeUtils.escapeHtml4(remark);
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getForbid_start_time() {
		return forbid_start_time;
	}
	public void setForbid_start_time(Date forbid_start_time) {
		this.forbid_start_time = forbid_start_time;
	}
	public int getForbid_hour() {
		return forbid_hour;
	}
	public void setForbid_hour(int forbid_hour) {
		this.forbid_hour = forbid_hour;
	}
	public int getIs_forbid() {
		return is_forbid;
	}
	public void setIs_forbid(int is_forbid) {
		this.is_forbid = is_forbid;
	}
	public String getW_name() {
		return StringEscapeUtils.escapeHtml4(w_name);
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getKey_count() {
		return key_count;
	}
	public void setKey_count(int key_count) {
		this.key_count = key_count;
	}
	public int getHead_lock() {
		return head_lock;
	}
	public void setHead_lock(int head_lock) {
		this.head_lock = head_lock;
	}
	public int getNickname_lock() {
		return nickname_lock;
	}
	public void setNickname_lock(int nickname_lock) {
		this.nickname_lock = nickname_lock;
	}
	public int getVideo_count() {
		return video_count;
	}
	public void setVideo_count(int video_count) {
		this.video_count = video_count;
	}
	public int getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public String getLast_video_time() {
		return last_video_time;
	}
	public void setLast_video_time(Date last_video_time) {
		this.last_video_time = AuthUtil.formatDateToString(last_video_time);
	}
	public long getTotal_anchor_virtual() {
		return total_anchor_virtual;
	}
	public void setTotal_anchor_virtual(long total_anchor_virtual) {
		this.total_anchor_virtual = total_anchor_virtual;
	}
	public long getTotal_video_virtual() {
		return total_video_virtual;
	}
	public void setTotal_video_virtual(long total_video_virtual) {
		this.total_video_virtual = total_video_virtual;
	}
	public String getArea() {
		return StringEscapeUtils.escapeHtml4(area);
	}
	public void setArea(String area) {
		this.area = area;
	}
	public long getTotal_spending() {
		return total_spending;
	}
	public void setTotal_spending(long total_spending) {
		this.total_spending = total_spending;
	}
	public Date getVip_time() {
		return vip_time;
	}
	public void setVip_time(Date vip_time) {
		this.vip_time = vip_time;
	}
	public String getOne_head() {
		return one_head;
	}
	public void setOne_head(String one_head) {
		this.one_head = one_head;
	}
	public int getSum_recharge() {
		return sum_recharge;
	}
	public void setSum_recharge(int sum_recharge) {
		this.sum_recharge = sum_recharge;
	}
	public long getSum_give() {
		return sum_give;
	}
	public void setSum_give(long sum_give) {
		this.sum_give = sum_give;
	}
	public String getInvite_code() {
		return invite_code;
	}
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}	
}

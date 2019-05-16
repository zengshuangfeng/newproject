package com.meisui.manage.entity;

import java.util.Date;

/**
 * <p>文件名称：Gift_Info.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午2:48:21
 */

public class Gift_Info {
	private int id;
	private String gift_head;//礼物头像
	private String gift_name;//礼物名称
	private String gift_description;//礼物文案
	private int gift_virtual;//礼物所需虚拟币
	private int gift_level;//礼物所需等级
	private int is_online;//是否上线 1是 0否
	private int sort;//排序值
	private Date create_time;//创建时间
	private String w_name;//编辑人员
	private Date update_time;//更新时间	
	private int anchor_virtual;//主播可获得魅力值
	private int gift_type;//礼物类型 0连击 1特效连击 2飘屏
	private int screen_type;//飘屏客户端礼物 1法拉利 2宝马车
	private String version;//版本
	private int gift_key;//用户可获得钥匙数
	private String quantifier;//飘屏量词
	private int module;//模块 0 全部 1直播 2社交
	private int is_private;//是否是一对一礼物 1是 0否
	private int anchor_divide;//主播分成占比
	private int promoter_divide;//推广员分成占比
	private int is_box;//是否是保箱礼物 1是 0否
	private String gift_webp;//星座webp动图
	private int gift_time;
	private String gift_head2;//星座动图小图
	private int old_gift_id;
	private int type;//0 普通礼物 1特殊礼物
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGift_time() {
		return gift_time;
	}
	public void setGift_time(int gift_time) {
		this.gift_time = gift_time;
	}
	public String getGift_webp() {
		return gift_webp;
	}
	public void setGift_webp(String gift_webp) {
		this.gift_webp = gift_webp;
	}
	public int getIs_box() {
		return is_box;
	}
	public void setIs_box(int is_box) {
		this.is_box = is_box;
	}
	public int getAnchor_divide() {
		return anchor_divide;
	}
	public void setAnchor_divide(int anchor_divide) {
		this.anchor_divide = anchor_divide;
	}
	public int getPromoter_divide() {
		return promoter_divide;
	}
	public void setPromoter_divide(int promoter_divide) {
		this.promoter_divide = promoter_divide;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGift_head() {
		return gift_head;
	}
	public void setGift_head(String gift_head) {
		this.gift_head = gift_head;
	}
	public String getGift_name() {
		return gift_name;
	}
	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}
	public String getGift_description() {
		return gift_description;
	}
	public void setGift_description(String gift_description) {
		this.gift_description = gift_description;
	}
	public int getGift_virtual() {
		return gift_virtual;
	}
	public void setGift_virtual(int gift_virtual) {
		this.gift_virtual = gift_virtual;
	}
	public int getGift_level() {
		return gift_level;
	}
	public void setGift_level(int gift_level) {
		this.gift_level = gift_level;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getAnchor_virtual() {
		return anchor_virtual;
	}
	public void setAnchor_virtual(int anchor_virtual) {
		this.anchor_virtual = anchor_virtual;
	}
	public int getGift_type() {
		return gift_type;
	}
	public void setGift_type(int gift_type) {
		this.gift_type = gift_type;
	}
	public int getScreen_type() {
		return screen_type;
	}
	public void setScreen_type(int screen_type) {
		this.screen_type = screen_type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getGift_key() {
		return gift_key;
	}
	public void setGift_key(int gift_key) {
		this.gift_key = gift_key;
	}
	public String getQuantifier() {
		return quantifier;
	}
	public void setQuantifier(String quantifier) {
		this.quantifier = quantifier;
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public int getIs_private() {
		return is_private;
	}
	public void setIs_private(int is_private) {
		this.is_private = is_private;
	}
	public String getGift_head2() {
		return gift_head2;
	}
	public void setGift_head2(String gift_head2) {
		this.gift_head2 = gift_head2;
	}
	public int getOld_gift_id() {
		return old_gift_id;
	}
	public void setOld_gift_id(int old_gift_id) {
		this.old_gift_id = old_gift_id;
	}
}

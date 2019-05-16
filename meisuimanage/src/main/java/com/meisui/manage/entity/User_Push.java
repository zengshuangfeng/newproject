package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.RouteUtil;


/**
 * <p>文件名称：User_Push.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月17日 下午4:29:43
 */

public class User_Push {
	private int id;
	private String title;//推送标题
	private String p_content;//推送内容
	private String pic;//图片
	private String start_time;//推送开始时间
	private String end_time;//推送结束时间
	private int p_range;//推送用户范围0单个用户1多用户
	private String p_where;//推送json
	private int is_push;//是否开始推送0未推送1推送中2推送结束
	private String w_name;//编辑人员
	private String update_time;//编辑时间
	private int style2;//0不跳转  1内链 2外链
	private String href;//跳转内容
	private String icon;//通知列表左边icon
	private String headtitle;//通知类型和type对应文字说明例如每美系统通知
	private int lt;///消息类型0专题1商品列表2系统商品状态3优惠券
	private int isblank;//是否跳转列表页0跳转列表页1直接走路由跳转
	private String subtitle;//副标题
	private String url;//url
	private int islist;//0不显示列表1显示列表
	private String token;
	private String jumpstyle;
	private String version;
	private String platform;
	private String channel;
	private int type;//0:系统发送 1:人工发送
	private String jumpname;//路由名称
	
	
	public String getJumpname() {
		return jumpname;
	}
	public void setJumpname(String jumpname) {
		this.jumpname = jumpname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time =AuthUtil.formatDateToString(start_time);
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = AuthUtil.formatDateToString(end_time);
	}
	public int getP_range() {
		return p_range;
	}
	public void setP_range(int p_range) {
		this.p_range = p_range;
	}
	public String getP_where() {
		return p_where;
	}
	public void setP_where(String p_where) {
		this.p_where = p_where;
	}
	public int getIs_push() {
		return is_push;
	}
	public void setIs_push(int is_push) {
		this.is_push = is_push;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = AuthUtil.formatDateToString(update_time);
	}
	public int getStyle2() {
		return style2;
	}
	public void setStyle2(int style2) {
		this.style2 = style2;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHeadtitle() {
		return headtitle;
	}
	public void setHeadtitle(String headtitle) {
		this.headtitle = headtitle;
	}
	public int getLt() {
		return lt;
	}
	public void setLt(int lt) {
		this.lt = lt;
	}
	public int getIsblank() {
		return isblank;
	}
	public void setIsblank(int isblank) {
		this.isblank = isblank;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIslist() {
		return islist;
	}
	public void setIslist(int islist) {
		this.islist = islist;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getJumpstyle() {
		//jumpstyle = RouteUtil.getUrl(url, style2);	
		return jumpstyle;
	}
	public void setJumpstyle(String jumpstyle) {
	/*	String tempHref = href;
		href = RouteUtil.setHref(jumpstyle, href);
		style2 = RouteUtil.setStyle2(jumpstyle);
		url = RouteUtil.setUrl(jumpstyle, tempHref);*/
		this.jumpstyle = jumpstyle;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}

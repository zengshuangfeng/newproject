package com.meisui.manage.entity;

import java.text.ParseException;
import java.util.Date;

import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.RouteUtil;

/**
 * 
 * <p>文件名称：index_recommend.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年7月4日 下午4:41:55
 */
public class Index_Recommend {
	private int id;
	private String title;//标题
	private String pic;//图片地址
	private String url;//url
	private int style;//类型  0大厅banner
	private int style2;//0不跳转
	private String href;//跳转内容
	private String platform;//平台 0 全平台 1 IOS 2 android
 	private int position;//位置
	private String version;//版本
	private int stayend;//停留秒数 开屏
	private String s_time;//展示开始时间
	private String e_time;//结束时间
	private String w_name;//编辑人员
	private String update_time;//编辑时间
	private String icon;//图标
	private String status_name;
	private String mobile_model;//机型
	private String jumpstyle;
	private String create_time;//创建时间
	private String title_en;//英文标题
	private int is_online;//是否上线 1 是 0否
	private int is_share;//是否可分享 1 是 0否
	private String share_title;//分享标题
	private String share_content;//分享内容
	private String share_weixin_pic;//微信分享图片
	private String share_weibo_pic;//微博分享图片
	private int app;//应用类型 0通用包 1配配
	private long anchor_uid;//生日主播uid
	private String video;
	private String channel;
	public String getTitle_en() {
		return title_en;
	}
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getStayend() {
		return stayend;
	}
	public void setStayend(int stayend) {
		this.stayend = stayend;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(Date s_time) {
		this.s_time = AuthUtil.formatDateToString(s_time);
	}
	public String getE_time() {
		return e_time;
	}
	public void setE_time(Date e_time) {
		this.e_time = AuthUtil.formatDateToString(e_time);
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
		this.update_time =AuthUtil.formatDateToString(update_time);
	}
	public int getStyle2() {
		return style2;
	}
	public void setStyle2(int style2) {
		this.style2 = style2;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}		
	public String getStatus_name() throws ParseException {
		Date current_time = new Date();

		if(AuthUtil.formatStringToDate(s_time).after(current_time))
		{
			status_name = "未开始";
		}
		if(AuthUtil.formatStringToDate(s_time).before(current_time)&&AuthUtil.formatStringToDate(e_time).after(current_time))
		{
			status_name = "进行中";
		}
		if(AuthUtil.formatStringToDate(e_time).before(current_time))
		{
			status_name = "已结束";
		}
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getMobile_model() {
		return mobile_model;
	}
	public void setMobile_model(String mobile_model) {
		this.mobile_model = mobile_model;
	}
	public String getJumpstyle() {
		jumpstyle = RouteUtil.getUrl(url, style2);	
		return jumpstyle;
	}
	public void setJumpstyle(String jumpstyle) {
		String tempHref = href;
		href = RouteUtil.setHref(jumpstyle, href);
		style2 = RouteUtil.setStyle2(jumpstyle);
		url = RouteUtil.setUrl(jumpstyle, tempHref);
		this.jumpstyle = jumpstyle;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
	public int getIs_share() {
		return is_share;
	}
	public void setIs_share(int is_share) {
		this.is_share = is_share;
	}
	public String getShare_title() {
		return share_title;
	}
	public void setShare_title(String share_title) {
		this.share_title = share_title;
	}
	public String getShare_content() {
		return share_content;
	}
	public void setShare_content(String share_content) {
		this.share_content = share_content;
	}
	public String getShare_weixin_pic() {
		return share_weixin_pic;
	}
	public void setShare_weixin_pic(String share_weixin_pic) {
		this.share_weixin_pic = share_weixin_pic;
	}
	public String getShare_weibo_pic() {
		return share_weibo_pic;
	}
	public void setShare_weibo_pic(String share_weibo_pic) {
		this.share_weibo_pic = share_weibo_pic;
	}
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public long getAnchor_uid() {
		return anchor_uid;
	}
	public void setAnchor_uid(long anchor_uid) {
		this.anchor_uid = anchor_uid;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}

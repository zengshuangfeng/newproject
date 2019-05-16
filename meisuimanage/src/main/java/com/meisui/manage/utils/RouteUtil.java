package com.meisui.manage.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>文件名称：RouteUtil.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:@vmei.me"></a>
 * @since 2016年6月8日 上午11:30:05
 */

public class RouteUtil {
	public static String setUrl(String url, String href)
	{
		switch (url) {
		case "fm://nojump"://不跳转
			return "";
		case "fm://inside"://内嵌网页
			return href;
		case "fm://outside"://外链
			return href;
		default:
			return url;
		}
	}
	public static String getUrl(String url, int style2)
	{
		switch (style2) {
		case 0://不跳转
			return "fm://nojump";
		case 1://内嵌网页
			return "fm://inside";
		case 2://外链
			return "fm://outside";
		default:
			return url;
		}
	}
	public static String getSearchUrl(String url, int style2)
	{
		switch (style2) {
		case 0://不跳转
		case 1://内嵌网页
		case 2://外链
			return "";
		default:
			return url;
		}
	}
	public static String setHref(String url, String href)
	{
		switch (url) {
		case "fm://nojump"://不跳转
		case "fm://inside"://内嵌网页
		case "fm://outside"://外链
			return "";
		default:
			return href;
		}
	}
	public static String getHref(String url, int style2, String href)
	{
		switch (style2) {
		case 0://不跳转
			return "";
		case 1://内嵌网页
			return url;
		case 2://外链
			return url;
		default:
			return href;
		}
	}
	public static int setStyle2(String url)
	{
		switch (url) {
		case "fm://nojump"://不跳转
			return 0;
		case "fm://inside"://内嵌网页
			return 1;
		case "fm://outside"://外链
			return 2;
		default:
			return StringUtils.isBlank(url)?-1:3;
		}
	}
}

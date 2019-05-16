package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IconfigDao;
import com.meisui.manage.dao.Iindex_RecommendDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Index_Recommend;
import com.meisui.manage.entity.Package_Extra;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.meisui.manage.utils.RouteUtil;

/***
 * 
 * <p>文件名称：Index_Recommend_Service.java</p>
 * <p>文件描述：首页推荐相关操作</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:@vmei.me"></a>
 * @since 2015年12月30日 下午2:51:48
 */
@Service
public class Index_RecommendService {
	private static Logger log = Logger.getLogger(Index_RecommendService.class.getClass());
	@Autowired
	private Iindex_RecommendDao iindex_RecommendDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IconfigDao iconfigDao;

	/***
	 * 
	 * <p>功能描述：推荐位banner列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param style 0banner
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param url 跳转路由
	 * @param status 0:未开始 1:进行中 2:已结束
	 * @param platform 00:全部   10:IOS 20:安卓
	 * @param page
	 * @param s
	 * @param name
	 * @param model
	 * @return
	 */
	public String getBannerRecommendList(int style, Date s_time, Date e_time, String jumpstyle, int status, String platform, int page, String s, Model model) {
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			int style2 = RouteUtil.setStyle2(jumpstyle);
			String searchUrl = RouteUtil.getSearchUrl(jumpstyle, style2);
			List<Index_Recommend> recommendList = iindex_RecommendDao.getIndexRecommendList(s_time, e_time, style, style2, searchUrl, status, platform, s, (page-1)*20, 20);
			int totalRecord = iindex_RecommendDao.getIndexRecommendCount(s_time, e_time, style, style2, searchUrl, status, platform, s);
			Map<String, String> jumpMapList = PropertyUtil.getJumpType();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("recommendbanner/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("s_time", s_time != null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("s", s);
			model.addAttribute("style", style);
			model.addAttribute("style2", style2);
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("name", "banner");
			model.addAttribute("styleName", "大厅banner");
			model.addAttribute("jumpstyle", jumpstyle);
			model.addAttribute("status", status);
			model.addAttribute("platform", platform);
			model.addAttribute("recommendList", recommendList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "recommendbanner");
		} catch (Exception e) {
			log.error("推荐位banner列表", e);
		}
		return "recommend/banner/list";
	}
	/***
	 * 
	 * <p>功能描述：推荐位banner添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param name
	 * @param model
	 * @return
	 */
	public String addBannerRecommend(Model model) {
		try
		{
			Index_Recommend index_Recommend = new Index_Recommend();
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType();
			List<Package_Extra> package_ExtraList = iconfigDao.getPackAgeExtraChannelList();
			model.addAttribute("package_ExtraList", package_ExtraList);
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("style", 0);
			model.addAttribute("name", "banner");
			model.addAttribute("styleName", "大厅banner");
			model.addAttribute("indexRecommend", index_Recommend);
			model.addAttribute("activeUrl", "recommendbanner");
		} catch (Exception e) {
			log.error("推荐位banner添加页面", e);
		}
		return "recommend/banner/add";
	}	
	/***
	 * 
	 * <p>功能描述：推荐位banner编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:53:56</p>
	 *
	 * @param id 首推id
	 * @param name
	 * @param model
	 * @return
	 */
	public String editBannerRecommend(int id, Model model) {
		try
		{
			Index_Recommend recommend = iindex_RecommendDao.getIndexRecommend(id);
			if(recommend.getUrl().contains("birthday"))
			{
				long anchor_uid = 0L;
				if(recommend.getUrl().contains("?f_uuid="))
				{
					String f_uuid = recommend.getUrl().substring(recommend.getUrl().lastIndexOf("?"));
					f_uuid = f_uuid.replace("?f_uuid=", "");
					try {
						anchor_uid = iuserDao.getUserIdWithF_uuid(f_uuid);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				recommend.setUrl("fm://birthday");
				recommend.setAnchor_uid(anchor_uid);
				recommend.setHref("");
				recommend.setStyle2(3);
			}
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType();
			List<Package_Extra> package_ExtraList = iconfigDao.getPackAgeExtraChannelList();
			model.addAttribute("package_ExtraList", package_ExtraList);
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("recommend", recommend);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("style", 0);
			model.addAttribute("name", "banner");
			model.addAttribute("styleName", "大厅banner");
			model.addAttribute("activeUrl", "recommendbanner");
		} catch (Exception e) {
			log.error("推荐位banner编辑页面", e);
		}
		return "recommend/banner/edit";
	}	
	/***
	 * 
	 * <p>功能描述：开屏列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param url 跳转路由
	 * @param status 0:未开始 1:进行中 2:已结束
	 * @param platform 00:全部   10:IOS 20:安卓
	 * @param page
	 * @param s
	 * @param name
	 * @param model
	 * @return
	 */
	public String getOpenList(Date s_time, Date e_time, String jumpstyle, int status, String platform, int page, String s, Model model) {
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			int style2 = RouteUtil.setStyle2(jumpstyle);
			String searchUrl = RouteUtil.getSearchUrl(jumpstyle, style2);
			List<Index_Recommend> recommendList = iindex_RecommendDao.getIndexRecommendList(s_time, e_time, 1, style2, searchUrl, status, platform, s, (page-1)*20, 20);
			int totalRecord = iindex_RecommendDao.getIndexRecommendCount(s_time, e_time, 1, style2, searchUrl, status, platform, s);
			Map<String, String> jumpMapList = PropertyUtil.getJumpType();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("recommendopen/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("s_time", s_time != null?AuthUtil.formatDateToString(s_time, "yyyy-MM-dd"):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("s", s);
			model.addAttribute("style2", style2);
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("name", "open");
			model.addAttribute("styleName", "开屏");
			model.addAttribute("jumpstyle", jumpstyle);
			model.addAttribute("status", status);
			model.addAttribute("platform", platform);
			model.addAttribute("recommendList", recommendList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "recommendopen");
		} catch (Exception e) {
			log.error("开屏列表", e);
		}
		return "recommend/open/list";
	}
	/***
	 * 
	 * <p>功能描述：开屏添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:52:11</p>
	 *
	 * @param model
	 * @return
	 */
	public String addOpenRecommend(Model model) {
		try
		{
			Index_Recommend index_Recommend = new Index_Recommend();
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType();
			List<Package_Extra> package_ExtraList = iconfigDao.getPackAgeExtraChannelList();
			model.addAttribute("package_ExtraList", package_ExtraList);
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("style", 0);
			model.addAttribute("name", "banner");
			model.addAttribute("styleName", "大厅banner");
			model.addAttribute("indexRecommend", index_Recommend);
			model.addAttribute("activeUrl", "recommendopen");
		} catch (Exception e) {
			log.error("推荐位banner添加页面", e);
		}
		return "recommend/open/add";
	}	
	/***
	 * 
	 * <p>功能描述：开屏编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:53:56</p>
	 *
	 * @param id 开屏id
	 * @param model
	 * @return
	 */
	public String editOpenRecommend(int id, Model model) {
		try
		{
			Index_Recommend recommend = iindex_RecommendDao.getIndexRecommend(id);
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType();
			List<Package_Extra> package_ExtraList = iconfigDao.getPackAgeExtraChannelList();
			model.addAttribute("package_ExtraList", package_ExtraList);
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("recommend", recommend);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("style", 0);
			model.addAttribute("styleName", "开屏");
			model.addAttribute("activeUrl", "recommendopen");
		} catch (Exception e) {
			log.error("开屏编辑页面", e);
		}
		return "recommend/open/edit";
	}	
	/**
	 * 
	 * <p>功能描述：保存推荐位信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:23:28</p>
	 *
	 * @param id 推荐位id
	 * @param title 标题
	 * @param title_en 英文标题
	 * @param pic 图片
	 * @param stayend 停留秒数
	 * @param style 0大厅banner
	 * @param position 位置
	 * @param jumpstyle 跳转类型
	 * @param href 跳转参数
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param platforms 平台
	 * @param version 版本
	 * @param stayend 停留秒数
	 * @param mobile_model 机型
	 * @param is_online 是否上线
	 * @param is_share 是否可分享 1是 0否
	 * @param share_title 分享标题
	 * @param share_content 分享内容
	 * @param share_weixin_pic 微信分享图片
	 * @param share_weibo_pic 微博分享图片
	 * @param app 应用类型 0通用包 1配配
	 * @param anchor_uid 生日主播uid
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveRecommend(int id, String title, String pic, int style, int position, String jumpstyle, String href,
			Date s_time, Date e_time, String platforms, String version, Integer stayend, String mobile_model, int is_online, int is_share, String share_title, String share_content, String share_weixin_pic, String share_weibo_pic, int app, long anchor_uid, String video, String channel, String w_name, HttpServletRequest request) {
		try
		{
			Date date = new Date();
			Index_Recommend index_Recommend = new Index_Recommend();
			index_Recommend.setStyle(style);
			index_Recommend.setIs_online(is_online);
			index_Recommend.setTitle(title);
			index_Recommend.setPosition(position);	
			if(e_time!=null)
				index_Recommend.setE_time(e_time);
			index_Recommend.setHref(href.trim());
			if(s_time!=null)
				index_Recommend.setS_time(s_time);
			if(!StringUtils.isBlank(pic) && pic.startsWith(","))
				pic = pic.substring(1);
			index_Recommend.setPic(pic);
			String platform = "00";
			if(platforms.equals("10"))
				platform = "10";
			if(platforms.equals("20"))
				platform = "20";
			index_Recommend.setHref(href);
			index_Recommend.setAnchor_uid(anchor_uid);
			if(jumpstyle.equals("fm://birthday"))//兼容旧版
			{
				User user = iuserDao.getUser(anchor_uid);
				index_Recommend.setUrl("http://fx.nanrenbang.in/peipeishare/common/birthday?f_uuid="+user.getF_uuid());
				index_Recommend.setHref("");
				index_Recommend.setStyle2(1);
			}
			else {
				index_Recommend.setJumpstyle(jumpstyle);
			}
			index_Recommend.setPlatform(platform);
			index_Recommend.setVersion(version);
			w_name = String.valueOf(request.getAttribute("w_name"));
			index_Recommend.setW_name(w_name);
			index_Recommend.setUpdate_time(date);	
			index_Recommend.setStyle(style);
			index_Recommend.setMobile_model(mobile_model);
			index_Recommend.setStayend(stayend);
			index_Recommend.setIs_share(is_share);
			index_Recommend.setShare_title(share_title);
			index_Recommend.setShare_content(share_content);
			index_Recommend.setShare_weibo_pic(share_weibo_pic);
			index_Recommend.setShare_weixin_pic(share_weixin_pic);
			index_Recommend.setVideo(video);
			index_Recommend.setApp(app);
			index_Recommend.setChannel(channel);
			List<Map<String, String>> mapList = validData(index_Recommend, s_time, e_time);
			JSONArray jsonArray = JSONArray.fromObject(mapList);
			if(mapList.size()>0)
			{
				return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
			}
			int result = 0;
			if(id > 0)
			{
				index_Recommend.setId(id);
				result = iindex_RecommendDao.updateIndexRecommend(index_Recommend);
				imanage_RecordDao.insertManageRecord(w_name, "更新banner信息", "index_recommend", id, IPUtil.getIp(request), date);
			}
			else {
				index_Recommend.setCreate_time(date);
				result = iindex_RecommendDao.insertIndexRecommend(index_Recommend);
				imanage_RecordDao.insertManageRecord(w_name, "添加banner信息", "index_recommend", index_Recommend.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存推荐位信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：推荐位删除</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:19:55</p>
	 *
	 * @param w_name 编辑人员
	 * @param name
	 * @param id 推荐位id
	 * @param request
	 * @return
	 */
	public int delete(String w_name, String name, int id, HttpServletRequest request) {	
		try{
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除"+getStyleName(name)+"信息", "index_recommend", id, IPUtil.getIp(request), new Date());
			return iindex_RecommendDao.deleteIndexRecommend(id, w_name);
		} catch (Exception e) {
			log.error("推荐位删除", e);
			return 0;
		}
	}
	/**
	 * 
	 * <p>功能描述：保存推荐位排序</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:18:47</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 推荐位id
	 * @param position 排序
	 * @param request
	 * @return
	 */
	public int updateRecommendPosition(String w_name, int id, int position, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iindex_RecommendDao.updateIndexRecommendPosition(id, position, w_name,new Date());
				imanage_RecordDao.insertManageRecord(w_name, "更新"+"“"+"ID："+id+"”"+"推荐位的排序", "index_recommend", id, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("保存推荐位排序异常",e);
		}
		return result;
	}
	/***
	 * 
	 * <p>功能描述：验证首推信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月12日 下午5:55:49</p>
	 *
	 * @param index_Recommend
	 * @param s_time
	 * @param e_time
	 * @return
	 */
	private List<Map<String, String>> validData(Index_Recommend index_Recommend, Date s_time, Date e_time)
	{			
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		if(StringUtils.isBlank(index_Recommend.getS_time()))
			mapList.add(AuthUtil.getMap("s_time", "上线时间不能为空"));
		if(StringUtils.isBlank(index_Recommend.getE_time()) && index_Recommend.getStyle()>0)
			mapList.add(AuthUtil.getMap("e_time", "下线时间不能为空"));
		if(index_Recommend.getStyle()>0 && s_time.after(e_time))	
			mapList.add(AuthUtil.getMap("e_time", "上线时间不能大于下线时间"));	
		if(StringUtils.isBlank(index_Recommend.getJumpstyle()))
			mapList.add(AuthUtil.getMap("jumpstyle", "请选择跳转类型"));
		return mapList;
	}
	private String getStyleName(String name)
	{
		switch (name) {
		case "banner":
			return "大厅banner";
		default:
			return "";
		}
	}	
}
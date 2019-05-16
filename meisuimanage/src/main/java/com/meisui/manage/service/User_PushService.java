package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.DateUtils;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_PushDao;
import com.meisui.manage.entity.Post;
import com.meisui.manage.entity.User_Push;
import com.meisui.manage.service.User_PushService;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：User_PushService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月17日 下午4:28:36
 */
@Service
public class User_PushService {
	private static Logger log = Logger.getLogger(User_PushService.class.getClass());
	@Autowired
	private Iuser_PushDao iuser_PushDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：获取消息推送管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午9:12:30</p>
	 *
	 * @param page 页码
	 * @param status 状态
	 * @param s_time 推送开始时间
	 * @param e_time 推送结束时间
	 * @param title 标题
	 * @param model
	 * @return
	 */
	public String getUserPushList(int page, String platform, Date s_time, Date e_time,Model model) {
		try {
			String e_timeString = e_time != null?AuthUtil.formatDateToString(e_time, "yyyy-MM-dd"):"";
			if(e_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(e_time); 
				calendar.add(Calendar.DATE,1);
				e_time=calendar.getTime();
			}
			List<User_Push> user_PushList = iuser_PushDao.getUserPushList(platform,s_time, e_time, (page-1)*20, 20);
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType2();
			for(User_Push userpush:user_PushList){
				userpush.setJumpname(jumpMapList.get(userpush.getUrl()));//用url获取它的路由名称		
			}
			int totalRecord = iuser_PushDao.getUserPushCount(platform,s_time, e_time);
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("userpush/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("s_time", s_time != null?DateUtils.sdf.format(s_time):"");
			model.addAttribute("e_time", e_timeString);
			model.addAttribute("platform", platform);
			model.addAttribute("user_PushList", user_PushList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "userpush");
		} catch (Exception e) {
			log.error("消息推送管理列表异常", e);
		}
	
		return "userpush/list";
	}
	
	public String addUserPush(Model model) {
		try
		{		
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType2();
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("activeUrl", "userpush");
		} catch (Exception e) {
			log.error("消息推送添加异常", e);
		}
		return "userpush/add";
	}
	
	/***
	 * 
	 * <p>功能描述：保存消息推送管理信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午9:32:50</p>
	 *	
	 * @param id 消息推送id
	 * @param title 标题
	 * @param pic 图片
	 * @param p_content 推送内容
	 * @param jumpstyle 
	 * @param start_time 推送开始时间
	 * @param end_time 推送结束时间
	 * @param href 跳转内容
	 * @param p_range 推送用户范围0单个用户1多用户
	 * @param userid 用户ID
	 * @param version 版本
	 * @param platform 0 全平台 1 IOS 2 android
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveUserPush(int id, String title,String p_content,String jumpstyle,
			Date start_time,Date end_time,String href,int p_range,String userid,String version,String platform,String w_name,
			HttpServletRequest request)
	{
		try
		{
			Map<Integer, String> map = new HashMap<>();
			w_name = String.valueOf(request.getAttribute("w_name"));
			User_Push userpush = new User_Push();
			userpush.setId(id);
			userpush.setTitle(title);		
			userpush.setP_content(p_content);
			userpush.setStart_time(start_time);
			userpush.setEnd_time(end_time);
			if("ms://anchor".equals(jumpstyle)){//若是直播间，房间号放在href字段中
				userpush.setHref(href);
				userpush.setUrl(jumpstyle);
			}else if("ms://outside".equals(jumpstyle)){
				userpush.setUrl(href);			
			}else{
				userpush.setUrl(jumpstyle);
			}
			
			userpush.setVersion(version);
			userpush.setPlatform(platform);
			userpush.setW_name(w_name);
			userpush.setP_range(p_range);//推送用户范围 0单个用户 1所有用户
			if(p_range == 0){//单个用户时，判断用户存在与否并赋值
				List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
				String user_id="";
				if (userid!=null) {  //去除换行和空格号
		            Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
		            Matcher m = p.matcher(userid);  
		            user_id = m.replaceAll("");  
		        } 
				String[] result=user_id.split(",");
				 ArrayList<String> list=new ArrayList<String>(); 
				 //用户ID去重
				 for(int i=0;i<result.length;i++)   
			       { 
			            if(!list.contains(result[i])) 
			            list.add(result[i]);       
			        } 
				for(int i=0;i<list.size();i++){
					long uid=Long.parseLong((String) list.get(i));
					String token = iuserDao.getUserInfoExtraToken(uid);
					if(uid == 0 || StringUtils.isBlank(token)){
						mapList.add(AuthUtil.getMap("userid", "该用户不存在或usertoken不存在"));
						JSONArray jsonArray = JSONArray.fromObject(mapList);
						return "{\"code\":-3,\"msg\":"+jsonArray.toString()+"}";
					}					
					map.put(i, token);//将指定的多个token放在map中							
				}
			
			}
			userpush.setJumpstyle(jumpstyle);
			if("".equals(jumpstyle)){
				userpush.setStyle2(0);	
			}else if("ms://index".equals(jumpstyle) || "ms://community".equals(jumpstyle) || "ms://anchor".equals(jumpstyle)){
				userpush.setStyle2(1);
			}else if("ms://outside".equals(jumpstyle)){
				userpush.setStyle2(2);
			}			
			userpush.setType(1);//0:系统发送 1:人工发送
			if(userpush.getPlatform().equals("10,20"))
				userpush.setPlatform("00");			
			int result = 0;
			Date date =  new Date();
			if(userpush.getId() > 0)
			{    
				userpush.setUpdate_time(date);
				Iterator<Entry<Integer, String>> iter = map.entrySet().iterator();
				if(p_range==0){
					while(iter.hasNext()){//map中的value值便是token值，遍历map，多个插入
						Entry<Integer, String> entry = iter.next();			
						userpush.setToken(	entry.getValue());
						result = iuser_PushDao.updateUserPush(userpush);
					}					
				}else if(p_range==1){
					result = iuser_PushDao.updateUserPush(userpush);
				}
				
				imanage_RecordDao.insertManageRecord(userpush.getW_name(), "更新"+"“"+userpush.getTitle()+"”"+"消息推送管理", "user_push", userpush.getId(), IPUtil.getIp(request), date);
			}
			else {
				userpush.setUpdate_time(date);
				userpush.setLt(1);			
				if(p_range==0){
					Iterator<Entry<Integer, String>> iter = map.entrySet().iterator();
					while(iter.hasNext()){//map中的value值便是token值，遍历map，多个插入
						Entry<Integer, String> entry = iter.next();			
						userpush.setToken(	entry.getValue());
						result = iuser_PushDao.insertUser_Push(userpush);
					}	
				}else if(p_range==1){
					result = iuser_PushDao.insertUser_Push(userpush);
				}
						
				imanage_RecordDao.insertManageRecord(userpush.getW_name(), "添加"+"“"+userpush.getTitle()+"”"+"消息推送管理", "user_push", userpush.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error("消息推送管理异常", ex);
			ex.printStackTrace();
		}	
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
	/***
	 * 
	 * <p>功能描述：编辑消息推送管理页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年8月12日 上午10:231:15</p>
	 *
	 * @param id 消息推送管理id
	 * @param model
	 * @return
	 */
	public String editUserPush(int id, Model model) {
		try
		{
			User_Push userpush = iuser_PushDao.getUserPush(id);
			LinkedHashMap<String, String> jumpMapList = PropertyUtil.getJumpType2();
			model.addAttribute("jumpMapList", jumpMapList);
			model.addAttribute("userpush", userpush);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "userpush");
			if(StringUtils.isNotBlank(userpush.getToken())){
				long userid = iuserDao.getUserIdWithToken(userpush.getToken());
				model.addAttribute("userid", userid);
			}
		} catch (Exception e) {
			log.error("消息推送管理编辑异常", e);
		}
		return "userpush/edit";
	}
	
	/***
	 * 
	 * <p>功能描述：删除消息推送</p>
	 * <p>创建人：</p>
     * <p>创建日期：2016年8月12日 上午10:29:15</p>
	 *
	 * @param id 消息推送id
	 * @param w_name 操作人员
	 * @return
	 */
	public int deleteUserPush(int id, String w_name,HttpServletRequest request)
	{
		try{
			User_Push userpush = iuser_PushDao.getUserPush(id);
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除"+"“"+userpush.getTitle()+"”"+"消息推送管理", "user_push", userpush.getId(), IPUtil.getIp(request), new Date());
			return iuser_PushDao.deleteUserPush(id, w_name);
		} catch (Exception e) {
			log.error("消息推送删除异常", e);
			return 0;
		}
	}

	public int repushUserPush(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iuser_PushDao.repushUserPush(id, 0, w_name);
				if(result>0){				
					imanage_RecordDao.insertManageRecord(w_name, "消息重新推送", "user_push", id, IPUtil.getIp(request), date);
				}
				
			}
		} catch (Exception e) {
			log.error("消息重新推送", e);
		}
		return result;
	}
}

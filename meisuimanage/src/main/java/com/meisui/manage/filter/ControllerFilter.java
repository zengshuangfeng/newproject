package com.meisui.manage.filter;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.forman.foundation.library.ThreeDESUtil;
import com.meisui.manage.entity.Admin;
import com.meisui.manage.entity.Manage_Menu;
import com.meisui.manage.service.AdminService;
import com.meisui.manage.service.Manage_RecordService;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.BeanUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.IPUtil2;
import com.meisui.manage.utils.PropertyUtil;

/***
 * 
 * <p>文件名称：ControllerFilter.java</p>
 * <p>文件描述：拦截器</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:@vmei.me"></a>
 * @since 2016年1月4日 下午3:39:08
 */
public class ControllerFilter extends HandlerInterceptorAdapter {
	private static Logger log = Logger.getLogger(ControllerFilter.class.getClass());

	private AdminService adminService;
	private Manage_RecordService manage_RecordService;
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)    
			throws Exception { 
		//不过滤的url
		String[] notFilter = new String[] {"robot","login","resetpwd","res","logout","upload","common","editorupload","main","404","500"};  

		// 请求的uri  
		String uri = request.getRequestURI();  
		// 是否过滤  
		boolean doFilter = true;  
		for (String s : notFilter) {  
			if (uri.indexOf(s) != -1) {  
				// 如果uri中包含不过滤的uri，则不进行过滤  
				doFilter = false;  
				break;  
			}  
		}  
		if(uri.equals("/"))
			doFilter = false; 
		if (doFilter) {  
			// 执行过滤  
			// 从cookie中获取登录者实体  
			Cookie[] cookies = request.getCookies();
			boolean hasLogin = false;
			boolean hasPermission = false;
			boolean hasClose = false;
			boolean hasIp=true;
			adminService = (AdminService)BeanUtil.getBean("adminService");
			manage_RecordService=(Manage_RecordService)BeanUtil.getBean("manage_RecordService");
			if(cookies!=null)
			{

				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("w_name"))
					{
						String w_name = ThreeDESUtil.decode(cookie.getValue(), PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
						
						w_name = URLDecoder.decode(w_name,"UTF-8");
						request.setAttribute("w_name", w_name);
						break;
					}
				}
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("admin"))
					{
						String admins = ThreeDESUtil.decode(cookie.getValue(), PropertyUtil.getValue("login_secret_key"), PropertyUtil.getValue("login_secret_iv"));
						String[] adminStrings =admins.split(",");
						//后期可直接从redis取
						Admin admin  = adminService.getAdminByName(adminStrings[0]);
						if(admin !=null && admin.getPassword().equals(adminStrings[1]))
						{
							if(admin.getIs_close()==1)
								hasClose = true;
							hasLogin = true;
							//后期可放在缓存内

							/* String ip=IPUtil2.getIp(request);
								IPUtil2 iputil=IPUtil2.getInstance();
				        		String address=iputil.getAddress(ip);
				        		System.out.println(ip);
				        		if(ip.indexOf("120.26.193.75")!=-1 ||ip.indexOf("120.41.45.197")!=-1){
				        			hasIp=true;
				        		}*/
							List<Manage_Menu> menuList = adminService.getMenuListByAdminId(admin.getId());
							String viewName = uri.substring(1);
							if(viewName.contains("/"))
								viewName = viewName.substring(0,viewName.indexOf("/"));
							for (Manage_Menu manage_Menu : menuList) {
								if(manage_Menu.getName_en().equals(viewName)  ||viewName.equals("blank"))
								{
									hasPermission = true;
									if(hasLogin && hasPermission)
									{
										manage_RecordService.addManageRecord(admin.getNickname(), "url:"+uri + "?" +request.getQueryString(), "", 1, IPUtil.getIp(request), new Date(), request);
									}
									break;
								}
							}
							break;
						}
						break;
					}
				}
			}

			if (!hasLogin||!hasPermission||hasClose || !hasIp) {  
				// 如果cookie中不存在登录者实体，则弹出框提示重新登录  
				// 设置request和response的字符集，防止乱码  
				request.setCharacterEncoding("UTF-8");  
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();  
				StringBuilder builder = new StringBuilder();  
				builder.append("<script type=\"text/javascript\">"); 
				if(!hasLogin || !hasIp)
				{
					builder.append("alert('网页过期，请重新登录！');");  
					builder.append("window.top.location.href='");  
					builder.append("/login"); 
					builder.append("';");  
				}
				else if(hasClose)
				{
					builder.append("alert('您的账号已被关闭权限！');");  
					builder.append("window.top.location.href='");  
					builder.append("/login"); 
					builder.append("';");  
					AuthUtil.deleteCookie("admin", response);
				}
				else {

					builder.append("alert('无相关权限！');history.go(-1);"); 
				} 
				builder.append("</script>");  

				out.print(builder.toString()); 
			} else {  
				// 如果cookie中存在登录者实体，则继续  

				return true; 
			}  
		} else {  
			// 如果不执行过滤，则继续  
			return true;  
		}
		return false;      
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String method =  request.getMethod();
		if(method.equals("POST"))
		{
			Map<String, String[]>  parameMap =  request.getParameterMap();
			JSONObject jsonObject = JSONObject.fromObject(parameMap);
			String w_name = AuthUtil.getCookie(request, response, "w_name");
			jsonObject.put("w_name", w_name);
			jsonObject.put("uri", request.getRequestURI());
			request.setAttribute("w_name", w_name);
			//log.info(jsonObject.toString());
		}
	}
}

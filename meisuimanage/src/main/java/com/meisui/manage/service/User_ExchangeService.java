package com.meisui.manage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_ExchangeDao;
import com.meisui.manage.dao.Iuser_PushDao;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Exchange;
import com.meisui.manage.entity.User_Push;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：User_ExchangeService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月2日 下午3:14:10
 */
@Service
public class User_ExchangeService {
	private static Logger log = Logger.getLogger(User_ExchangeService.class.getClass());
	@Autowired
	private Iuser_ExchangeDao iuser_ExchangeDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Iuser_PushDao iuser_PushDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：订单列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 下午3:31:08</p>
	 *
	 * @param status 状态
	 * @param type 类型
	 * @param p_name 商品名称
	 * @param start_time 下单时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getList(int status, int type, String p_name, Date start_time, int page, Model model)
	{
		try {
			Date end_time = null;
			if(start_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(start_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<User_Exchange> user_ExchangeList = iuser_ExchangeDao.getUserExchangeList(status, type, p_name, start_time, end_time, (page-1)*20, 20);
			for (User_Exchange user_Exchange : user_ExchangeList) {
				User user = iuserDao.getUser(user_Exchange.getUid());
				user_Exchange.setNickname(user.getNickname());
			}
			int totalRecord = iuser_ExchangeDao.getUserExchangeCount(status, type, p_name, start_time, end_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("userexchange/list");
			model.addAttribute("userExchangeList", user_ExchangeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "userexchange");
			model.addAttribute("status", status);
			model.addAttribute("type", type);
			model.addAttribute("p_name", p_name);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("订单列表", e);
		}
		return "userexchange/list";
	}
	/**
	 * 
	 * <p>功能描述：订单详细页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 上午10:28:56</p>
	 *
	 * @param id 订单id
	 * @param model
	 * @return
	 */
	public String detail(int id, Model model)
	{
		try {
			User_Exchange user_Exchange = iuser_ExchangeDao.getUserExchange(id);
			User user = iuserDao.getUser(user_Exchange.getUid());
			user_Exchange.setNickname(user.getNickname());
			model.addAttribute("userExchange", user_Exchange);
			model.addAttribute("activeUrl", "userexchange");
		} catch (Exception e) {
			log.error("订单详细页面", e);
		}
		return "userexchange/detail";
	}
	/**
	 * 
	 * <p>功能描述：订单json</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月2日 下午3:43:01</p>
	 *
	 * @param id 订单id
	 * @return
	 */
	public String getJson(int id)
	{
		try {
			User_Exchange user_Exchange = iuser_ExchangeDao.getUserExchange(id);
			return JSONObject.fromObject(user_Exchange).toString();
		}catch (Exception e) {
			log.error("订单json", e);
		}
		return "";
	}
	/**
	 * 
	 * <p>功能描述：更新订单状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 商品id
	 * @param status 订单状态
	 * @param request
	 * @return
	 */
	public int updateStatus(String w_name, int id, int status, HttpServletRequest request) {	
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "更新订单状态", "t_user_exchange", id, IPUtil.getIp(request), new Date());
			int result = iuser_ExchangeDao.updateUserExchangeStatus(id, status, w_name, date);
			if(result>0&&status==3)
			{
				User_Exchange user_Exchange = iuser_ExchangeDao.getUserExchange(id);
				String token = iuserDao.getUserInfoExtraToken(user_Exchange.getUid());
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				_user_push.setTitle(String.format("%s订单失效，若有疑问请联系官方QQ：2957088434",user_Exchange.getP_name()));
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(1);
				_user_push.setHref("");
				_user_push.setUrl("fm://userexchange");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
			}
			return result;
		} catch (Exception e) {
			log.error("更新订单状态", e);
			return 0;
		}
	}
	/**
	 * 
	 * <p>功能描述：更新订单物流</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 商品id
	 * @param status 订单状态
	 * @param remark 备注
	 * @param request
	 * @return
	 */
	public int updateLogistics(String w_name, int id, int status, String logistics_name, String logistics_code, String remark, HttpServletRequest request) {	
		try{
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			imanage_RecordDao.insertManageRecord(w_name, "更新订单状态", "t_user_exchange", id, IPUtil.getIp(request), new Date());
			iuser_ExchangeDao.updateUserExchangeStatus(id, status, w_name, date);
			int result = iuser_ExchangeDao.updateUserExchangeLogistics(id, logistics_name, logistics_code, remark, w_name, date);
			if(result>0)
			{
				User_Exchange user_Exchange = iuser_ExchangeDao.getUserExchange(id);
				String token = iuserDao.getUserInfoExtraToken(user_Exchange.getUid());
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.HOUR,1);
				User_Push _user_push = new User_Push();
				if(StringUtils.isBlank(logistics_name))
				{
					_user_push.setTitle(String.format("%s已经发货了~请注意查收~",user_Exchange.getP_name()));
				}
				else {
					_user_push.setTitle(String.format("%s已经发货了~快递信息%s %s",user_Exchange.getP_name(), logistics_name, logistics_code));
				}
				_user_push.setP_content("");
				_user_push.setStart_time(date);
				_user_push.setEnd_time(calendar.getTime());
				_user_push.setUpdate_time(date);
				_user_push.setLt(1);
				_user_push.setIsblank(0);
				_user_push.setP_range(0);
				_user_push.setStyle2(1);
				_user_push.setHref("");
				_user_push.setUrl("fm://userexchange");
				_user_push.setVersion("1.0");
				_user_push.setPlatform("00");
				_user_push.setIslist(0);
				_user_push.setToken(token);
				_user_push.setW_name(w_name);
				_user_push.setIs_push(2);
				iuser_PushDao.insertUser_Push(_user_push);
				iuser_PushDao.insertUserPushBox(_user_push.getId(), date, 0, token);
			}
			return result;
		} catch (Exception e) {
			log.error("更新订单物流", e);
			return 0;
		}
	}
}

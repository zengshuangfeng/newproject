package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IconfigDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.Iuser_PushDao;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Push;
import com.meisui.manage.entity.Video_Withdraw;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Video_WithdrawService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月6日 下午3:12:01
 */
@Service
public class Video_WithdrawService {
	private static Logger log = Logger.getLogger(Video_WithdrawService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IconfigDao iconfigDao;
	@Autowired
	private Iuser_PushDao iuser_PushDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：视频提现申请列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午3:41:13</p>
	 *
	 * @param o_id 订单号
	 * @param nickname 昵称
	 * @param status 状态
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getApplyList(String o_id, String nickname, int status, int page, Model model)
	{
		try {
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<Video_Withdraw> video_WithdrawList = null;
			for (Video_Withdraw video_Withdraw : video_WithdrawList) {
				User user = iuserDao.getUser(video_Withdraw.getUid());
				video_Withdraw.setNickname(user.getNickname());
				video_Withdraw.setUser_is_blocked(user.getIs_blocked());
			}
			int totalRecord = 0;
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("withdrawapply/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("o_id", o_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("status", status);
			model.addAttribute("videoWithdrawList", video_WithdrawList);
			model.addAttribute("activeUrl", "withdrawapply");
		} catch (Exception e) {
			log.error("视频提现申请列表", e);
		}
		return "videowithdraw/applylist";
	}
	/**
	 * 
	 * <p>功能描述：视频提现待支付列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午3:41:13</p>
	 *
	 * @param o_id 订单号
	 * @param nickname 昵称
	 * @param status 状态
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getPayList(String o_id, String nickname, int status, int page, Model model)
	{
		try {
			List<Long> uidList = new ArrayList<Long>();
			if(!StringUtils.isBlank(nickname))
				uidList = iuserDao.getUIdListWithNickanme(nickname);
			List<Video_Withdraw> video_WithdrawList = null;
			for (Video_Withdraw video_Withdraw : video_WithdrawList) {
				User user = iuserDao.getUser(video_Withdraw.getUid());
				video_Withdraw.setNickname(user.getNickname());
				video_Withdraw.setUser_is_blocked(user.getIs_blocked());
			}
			int totalRecord = 0;
			PageUtil pageUtil = new PageUtil(12, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("withdrawpay/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("o_id", o_id);
			model.addAttribute("nickname", nickname);
			model.addAttribute("status", status);
			model.addAttribute("videoWithdrawList", video_WithdrawList);
			model.addAttribute("activeUrl", "withdrawpay");
		} catch (Exception e) {
			log.error("视频提现待支付列表", e);
		}
		return "videowithdraw/paylist";
	}
	/**
	 * 
	 * <p>功能描述：更新视频提现申请状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年4月6日 下午4:40:01</p>
	 *
	 * @param id 视频提现表id
	 * @param status 状态 0待审核 1待支付 2已完成 3已拒绝
	 * @param is_from 0申请列表 1待支付列表
	 * @param remark 备注
	 * @param w_name 操作人员
	 * @param request
	 * @return
	 */
	public int updateStatus(int id, int status, int is_from, String remark, String w_name, HttpServletRequest request)
	{		
		int result=0;
		try {

			w_name = String.valueOf(request.getAttribute("w_name"));
			Video_Withdraw video_Withdraw = null;
			/*if(status==1)
			{
				//判断是否有足够魅力提现， 防止用户重复提交申请
				
				User_Info user_Info = iuserDao.getUserInfo(video_Withdraw.getUid());
				String peipei_percent_str = iconfigDao.getConfigValue("peipei_percent");
				double peipei_percent = 0L;
				if(!StringUtils.isBlank(peipei_percent_str))
					peipei_percent = Double.parseDouble(peipei_percent_str);		
				double money = DoubleUtil.div(video_Withdraw.getWithdraw_money(), peipei_percent, 2);
				double surplus_video_virtual =  DoubleUtil.mul(money, 100);
				if(user_Info.getSurplus_video_virtual()<surplus_video_virtual)
					return -2;
				else
				{
					iuserDao.updateUserSurplusVideoVirtual((int)Math.ceil(surplus_video_virtual), video_Withdraw.getUid());
				}
			}*/
			String token =  iuserDao.getUserInfoExtraToken(video_Withdraw.getUid());
			Date date = new Date();
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(date); 
			calendar.add(Calendar.HOUR,1);
			User_Push _user_push = new User_Push();
			switch (status) {
				case 1:
					_user_push.setTitle("您的提现申请已通过");
					_user_push.setP_content("您的提现申请已通过，我们将在3个工作日内存入您的支付宝账号，请注意查收");
				break;
				case 2:
					_user_push.setTitle(String.format("%s元已转入您的账户",video_Withdraw.getWithdraw_money()));
					_user_push.setP_content(String.format("%s元已转入您的账户，请注意查收",video_Withdraw.getWithdraw_money()));
				break;
				case 3:
					if(is_from==0)
					{
						_user_push.setTitle("您的提现申请未通过");
						_user_push.setP_content(String.format("您的提现申请未通过原因：%s", remark));
					}
					else
					{
						_user_push.setTitle(String.format("您的%s元提现未能到账。",video_Withdraw.getWithdraw_money()));
						_user_push.setP_content(String.format("您的%s元提现未能到账原因：%s",video_Withdraw.getWithdraw_money(), remark));
					}
					break;
			}
			_user_push.setStart_time(date);
			_user_push.setEnd_time(calendar.getTime());
			_user_push.setUpdate_time(date);
			_user_push.setLt(2);
			_user_push.setIsblank(0);
			_user_push.setP_range(0);
			_user_push.setStyle2(1);
			_user_push.setHref("");
			_user_push.setUrl("");
			_user_push.setVersion("1.0");
			_user_push.setPlatform("00");
			_user_push.setIslist(0);
			_user_push.setToken(token);
			_user_push.setW_name(w_name);
			iuser_PushDao.insertUser_Push(_user_push);
				if(result>0)
			{
				imanage_RecordDao.insertManageRecord(w_name, "更新视频提现申请状态", "t_video_withdraw", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("更新视频提现申请状态",e);
		}
		return result;
	}
}

package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.DoubleUtil;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.IwithdrawDao;
import com.meisui.manage.entity.Profit;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Info;
import com.meisui.manage.entity.Withdraw;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：WithdrawService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月28日 下午3:34:08
 */
@Service
public class WithdrawService {
	private static Logger log = Logger.getLogger(WithdrawService.class.getClass());
	@Autowired
	private IwithdrawDao iwithdrawDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：获取提现列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午3:36:35</p>
	 *
	 * @param is_pay 是否打款 1是 0否
	 * @param uid 用户id
	 * @param nickname 昵称
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getWithdrawList(int is_pay, long uid, String nickname, int page, Model model)
	{
		try {
			List<Withdraw> withdrawList = iwithdrawDao.getWithdrawList(is_pay, uid, nickname, (page-1)*20, 20);
			int totalRecord = iwithdrawDao.getWithdrawCount(is_pay, uid, nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("withdraw/list");
			model.addAttribute("withdrawList", withdrawList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "withdraw");
			model.addAttribute("is_pay", is_pay);
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
		} catch (Exception e) {
			log.error("提现列表", e);
		}
		return "withdraw/list";
	}
	/**
	 * 
	 * <p>功能描述：更新提现信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月28日 下午3:37:06</p>
	 *
	 * @param id 提现信息id
	 * @param is_pay 是否打款 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String updateWithdraw(int id, int is_pay, String w_name, HttpServletRequest request)
	{
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Withdraw withdraw = new Withdraw();
			withdraw.setId(id);
			withdraw.setIs_pay(is_pay);
			withdraw.setW_name(w_name);
			Date date = new Date();
			withdraw.setUpdate_time(date);
			int result = iwithdrawDao.updateWithdraw(withdraw);
			imanage_RecordDao.insertManageRecord(w_name, "更新提现信息", "t_withdraw", id, IPUtil.getIp(request), date);
			if(result > 0){
				withdraw = iwithdrawDao.getWithdrawVirtual(id);
				Profit profit = new Profit();
				profit.setUid(withdraw.getUid());
				profit.setGift_virtual((int)withdraw.getWithdraw_virtual());
				profit.setSend_uid(0);
				profit.setW_name(w_name);
				profit.setCreate_time(date);
				profit.setType(6);
				iprofitDao.insertProfit(profit);
				iuserDao.updateUserSurplusAnchorVirtual(withdraw.getWithdraw_virtual(), withdraw.getUid());
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			log.error("更新提现信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：添加提现信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月3日 下午4:14:29</p>
	 *
	 * @param uid 用户id
	 * @param withdraw_type 账号类型 0支付宝
	 * @param withdraw_pass 提现账号
	 * @param withdraw_name 账号名
	 * @param withdraw_virtual 提现虚拟币
	 * @param withdraw_rmb 提现人民币
	 * @param remark 备注
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveWithdrawInfo(long uid, int withdraw_type, String withdraw_pass, String withdraw_name, long withdraw_virtual, double withdraw_rmb, String remark, String w_name, HttpServletRequest request)
	{
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Integer divide_proportion = iuserDao.getUserAnchorDivideProportion(uid);
			if(divide_proportion==null)
				divide_proportion = 0;
			withdraw_rmb = DoubleUtil.mul(DoubleUtil.div(withdraw_virtual, 100, 2), ((double)divide_proportion)/100);
			Date date = new Date();
			Withdraw withdraw = new Withdraw();
			withdraw.setUid(uid);
			withdraw.setWithdraw_type(withdraw_type);
			withdraw.setWithdraw_pass(withdraw_pass);
			withdraw.setWithdraw_virtual(withdraw_virtual);
			withdraw.setWithdraw_rmb(withdraw_rmb);
			withdraw.setName(withdraw_name);
			withdraw.setCreate_time(date);
			withdraw.setIs_pay(0);
			withdraw.setW_name(w_name);
			withdraw.setUpdate_time(date);
			withdraw.setRemark(remark);
			int result = iwithdrawDao.insertWithdraw(withdraw);
			imanage_RecordDao.insertManageRecord(w_name, "添加提现信息", "t_withdraw", withdraw.getId(), IPUtil.getIp(request), date);
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			log.error("添加提现信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：主播提现流水列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 下午3:27:24</p>
	 *
	 * @param uid 主播uid
	 * @param date 日期
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getWithdrawList(long uid, String date, int page, Model model)
	{
		try {
			if(StringUtils.isBlank(date))
				date = AuthUtil.formatDateToString(new Date(), "yyyy-MM");
			model.addAttribute("date", date);
			date = date + "-01";
			List<Withdraw> withdrawList = iwithdrawDao.getWithdrawListWithUid(uid, date, (page-1)*20, 20);
			int totalRecord = iwithdrawDao.getWithdrawCountWithUid(uid, date);
			User user = iuserDao.getUser(uid);
			User_Info user_Info = iuserDao.getUserInfo(uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/withdraw/waterlist");
			model.addAttribute("withdrawList", withdrawList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "useranchor");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", user.getNickname());
			model.addAttribute("userInfo", user_Info);
		} catch (Exception e) {
			log.error("主播提现流水列表", e);
		}
		return "withdraw/waterlist";
	}
}

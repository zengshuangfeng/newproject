package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IRechargeBalanceVirtualDao;
import com.meisui.manage.dao.Ibalance_VirtualrecordDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class RechargeBalanceVirtualService {
	private static Logger log = Logger.getLogger(RechargeBalanceVirtualService.class.getClass());
	@Autowired
	private IRechargeBalanceVirtualDao irechargebalancevirtualdao;
	@Autowired
	private Ibalance_VirtualrecordDao ibalance_VirtualrecordDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	public String rechargebalancevirtual(long uid, String f_uuid, String nickname, HttpServletRequest request,int page,Model model) {
		try {

			
			List<User> userList = new ArrayList<>();
			userList = irechargebalancevirtualdao.getBalanceVirtualUserList(uid,f_uuid, nickname, (page - 1) * 20, 20);
			
			int	totalRecord = irechargebalancevirtualdao.getBalanceVirtualUserCount(uid,f_uuid, nickname);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("rechargebalancevirtual/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userList", userList);	
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);		
			model.addAttribute("activeUrl", "rechargebalancevirtual");
			model.addAttribute("uid", uid);
		} catch (Exception e) {
			log.error("钻石充值", e);
		}
		return "userinfo/rechargebalancevirtual";
	}
	
	public int updatebalance_virtual(long id, int balance_virtual, int money, String remark, String diamondsa, String diamondsb, String w_name, HttpServletRequest request) {
		int result = 0;
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			result = irechargebalancevirtualdao.updaterechargebalance_virtual(balance_virtual, id);

			if (result > 0) {
				
				if (diamondsa.equals("zftype1")) {

					if (diamondsb.equals("zhifu1")) {
						diamondsb = "0";
					} else {
						diamondsb = "1";
					}
				}
				if (diamondsa.equals("zftype2") || diamondsa.equals("zftype3")) {
					diamondsb = "-1";
				}
				User _user = irechargebalancevirtualdao.getUserByid(id);
				ibalance_VirtualrecordDao.insertBalance_Virtualrecord(id, balance_virtual, money, Integer.valueOf(diamondsb), diamondsa, remark, w_name, AuthUtil.formatDateToString(new Date()), _user.getNickname());
				imanage_RecordDao.insertManageRecord(w_name, "奖励钻石数("+balance_virtual+"),uid：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
			}
		} catch (Exception e) {
			log.error("修改用户钻石数", e);
		}

		return result;
	}
}

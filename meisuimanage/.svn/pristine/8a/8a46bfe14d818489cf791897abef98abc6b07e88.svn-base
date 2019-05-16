package com.meisui.manage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Iaddress_ChangeDao;
import com.meisui.manage.entity.AddressChange;
import com.meisui.manage.utils.PageUtil;

@Service
public class AddressChangeService {
	private static Logger log = Logger.getLogger(AddressChangeService.class.getClass());
	@Autowired
	private Iaddress_ChangeDao addressChangeDao;
	
	
	/***
	 * 获取归属变更记录List
	 * */
	public String getAlladdressChangeList(int page, Model model) {
		try {
			List<AddressChange> getAlladdressChangeList=addressChangeDao.getAlladdressChangeList((page-1)*20, 20);
			int totalRecord = addressChangeDao.getaddressChangeCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("addressChange/list");
			model.addAttribute("getAlladdressChangeList", getAlladdressChangeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "addressChange");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addresschange/list";
	}
	
	public AddressChange getUserBYFuid(String fuuid) {
		AddressChange getUserBYFuid=addressChangeDao.getUserBYFuid(fuuid);
		return getUserBYFuid;
	}
	
	public AddressChange getAgentUserBYFuid(String fuuid) {
		AddressChange getAgentUserBYFuid=addressChangeDao.getAgentUserBYFuid(fuuid);
		return getAgentUserBYFuid;
	}
	public void saveAddressChange(AddressChange addresschange) {
		 addressChangeDao.saveAddressChange(addresschange);
	}
	public void updateUser(AddressChange addresschange) {
		 addressChangeDao.updateUser(addresschange);
	}
}

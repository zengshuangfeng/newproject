package com.meisui.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.AuthenticationDao;
import com.meisui.manage.dao.IaccountReceivableDao;
import com.meisui.manage.entity.AccountReceivable;
import com.meisui.manage.entity.Authentication;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class AccountReceivableService {
	
	@Autowired
	private IaccountReceivableDao accountReceivableDao;
	@Autowired
	private AuthenticationDao authenticationDao;
	public String getAccountReceivable(int authentication_id,Model model) {
		AccountReceivable account=accountReceivableDao.getAccountReceivable(authentication_id);
		Authentication authentication=authenticationDao.getAuthentication(authentication_id);
		if(account!=null) {
			model.addAttribute("account",account);
			model.addAttribute("status",authentication.getIs_status());
		}
		return "authentication/account_receivable";
	}
	public String chekpaynumber(int authentication_id,String pay_number) {
		AccountReceivable account=accountReceivableDao.getAccountReceivable(authentication_id);
		if(pay_number.equals(account.getVerification_amount())) {
			return "0";
		}
		return "1";
	}
	public void updateAccountReceivable (AccountReceivable accountReceivable) {
		accountReceivableDao.updateAccountReceivable(accountReceivable);
	}
}

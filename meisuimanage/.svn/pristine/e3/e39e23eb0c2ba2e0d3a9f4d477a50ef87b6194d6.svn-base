package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.forman.foundation.library.RedisUtil;
import com.forman.log4j.Log4jHandel;
import com.meisui.manage.dao.IsquareDao;
import com.meisui.manage.entity.Im_Domain;
import com.meisui.manage.interfaces.IWebCommunication;
import com.meisui.manage.utils.PropertyUtil;


@Service
public class HessianService {
	@Autowired
	private IsquareDao isquareDao;
	public String getDomain(String f_uuid)
	{
		try {
			String domain = isquareDao.getSquareDomain(f_uuid);
			return domain;
		} catch (Exception e) {
			Log4jHandel.myerror("获取主播domain",e);
		}
		return "";
	}

	public void gaguid(String token, int hour)
	{
		try {
			String f_uuid = RedisUtil.Gethget(0, token, "come_fuuid");
			if(!StringUtils.isBlank(f_uuid)&&!f_uuid.equals("null"))
			{
				String domain = getDomain(f_uuid);
				if(!StringUtils.isBlank(domain)&&!domain.equals("null"))
				{
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					String im_url = PropertyUtil.getValue("im_url");
					im_url = im_url.replace("domain", domain);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, im_url);
					iWebCommunication.gaguid(token, String.valueOf(hour));
				}
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
	}
	public void closeroom(String f_uuid)
	{
		try {
			String domain = getDomain(f_uuid);
			if(!StringUtils.isBlank(domain)&&!domain.equals("null"))
			{
				HessianProxyFactory factory = new HessianProxyFactory();
				factory.setOverloadEnabled(true);
				String im_url = PropertyUtil.getValue("im_url");
				im_url = im_url.replace("domain", domain);
				IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, im_url);
				iWebCommunication.closeroom(f_uuid);
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
	}
	public void isjin(String usertoken,String f_uuid,boolean sign,boolean ifjin)
	{
		try {
			String anchor_f_uuid = RedisUtil.Gethget(0, usertoken, "come_fuuid");
			if(!StringUtils.isBlank(anchor_f_uuid)&&!anchor_f_uuid.equals("null"))
			{
				String domain = getDomain(anchor_f_uuid);
				if(!StringUtils.isBlank(domain)&&!domain.equals("null"))
				{
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					String im_url = PropertyUtil.getValue("im_url");
					im_url = im_url.replace("domain", domain);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, im_url);
					iWebCommunication.isjin(usertoken, f_uuid, sign, ifjin);
				}
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
	}
	public void updatelevel(String token)
	{
		try {
			String f_uuid = RedisUtil.Gethget(0, token, "come_fuuid");
			if(!StringUtils.isBlank(f_uuid)&&!f_uuid.equals("null"))
			{
				String domain = getDomain(f_uuid);
				if(!StringUtils.isBlank(domain)&&!domain.equals("null"))
				{
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					String im_url = PropertyUtil.getValue("im_url");
					im_url = im_url.replace("domain", domain);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, im_url);
					iWebCommunication.updatelevel(token);
				}
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
	}
	public  void adminJinGao(String f_uuid,String token,String title,String msg,String time)
	{

		try {
			String domain = getDomain(f_uuid);
			if(!StringUtils.isBlank(domain)&&!domain.equals("null"))
			{
				HessianProxyFactory factory = new HessianProxyFactory();
				factory.setOverloadEnabled(true);
				String im_url = PropertyUtil.getValue("im_url");
				im_url = im_url.replace("domain", domain);
				IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, im_url);
				iWebCommunication.adminJinGao(f_uuid, token, title, msg, time);
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
	}
	public  void message(String msg)
	{
		try {
			Set<String> domainList = RedisUtil.GetWhereKeys(2, "*");
			for (String domainModel : domainList) {
				try
				{
					HessianProxyFactory factory = new HessianProxyFactory();
					factory.setOverloadEnabled(true);
					String im_url = PropertyUtil.getValue("im_url");
					im_url = im_url.replace("domain", domainModel);
					IWebCommunication iWebCommunication = (IWebCommunication)factory.create(IWebCommunication.class, im_url);
					iWebCommunication.message(msg);
				}
				catch (Exception e) {
					Log4jHandel.myerror("通知im公告异常",e);
				}
			}
		} catch (Exception e) {
			Log4jHandel.myerror("通知im异常",e);
		}
	}
}

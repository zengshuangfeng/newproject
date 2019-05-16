package com.meisui.manage.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IwxshareDao;
import com.meisui.manage.entity.WxShare;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class WxShareService {
	
	private static Logger log = Logger.getLogger(WxShareService.class.getClass());
	
	@Autowired
	private IwxshareDao wDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;

	public String edit(Model model) {
		WxShare ws = wDao.getShareMsg();
		model.addAttribute("wxshare", ws);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "wxshare");
		return "wxshare/edit";
	}

	public String save(int id, String name, String title, String icon, String url, HttpServletRequest request) {
		try{
			int result = -1;
			Date date = new Date();
			String w_name = request.getAttribute("w_name").toString();
			if(id == 0){
				result = wDao.save(name, title, icon, url, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "添加微信分享", "t_share_url", null, IPUtil.getIp(request), date);
			}else{
				result = wDao.update(id, name, title, icon, url, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "更新微信分享", "t_share_url", id, IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("微信分享");
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
}

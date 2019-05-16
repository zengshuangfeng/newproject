package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IsquareDao;
import com.meisui.manage.entity.Square;
import com.meisui.manage.qiniu.api.Client;
import com.meisui.manage.qiniu.api.Hub;
import com.meisui.manage.qiniu.api.Stream;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class QiniuService {
	private static Logger log = Logger.getLogger(QiniuService.class.getClass());
	@Autowired
	private IsquareDao isquareDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	private static Hub hubv2;
	private void setHub()
	{
		if(hubv2==null)
		{
			Client cli = new Client(PropertyUtil.getValue("access_key"), PropertyUtil.getValue("secret_key"));
			hubv2 = cli.newHub(PropertyUtil.getValue("hub_name"));
		}
	}
	public String getList(Model model)
	{
		try {
			setHub();
			Hub.ListRet list = hubv2.listLive("", 100, "");
			List<String> keyList = new ArrayList<String>();
			List<Square> squareList = isquareDao.getSquareList2(0L, null, 0, 1000);
			for(String string : list.keys)
			{
				boolean isContain = false;
				for (Square square : squareList) {
					if(string.equals(String.valueOf(square.getF_uuid())))
					{
						isContain = true;
						break;
					}
				}
				if(!isContain)
					keyList.add(string);
			}
			model.addAttribute("keyList", keyList);
			model.addAttribute("rtmpPath", PropertyUtil.getValue("rtmpPath"));
			model.addAttribute("activeUrl", "qiniu");
		} catch (Exception e) {
			log.error("七牛流列表", e);
		}
		return "qiniu/list";
	}
	public int disable(String f_uuid, HttpServletRequest request)
	{
		try {
			if(!StringUtils.isBlank(f_uuid))
			{
				setHub();
				Stream stream = hubv2.get(f_uuid);
				stream.disable();
				String w_name = String.valueOf(request.getAttribute("w_name"));
				imanage_RecordDao.insertManageRecord(w_name, "禁播七牛直播流，流："+f_uuid, "", 0, IPUtil.getIp(request), new Date());
			}
			return 1;
		}
		catch (Exception e) {
			log.error("七牛流列表", e);
		}
		return 0;
	}
}

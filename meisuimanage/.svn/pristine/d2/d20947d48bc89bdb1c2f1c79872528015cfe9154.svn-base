package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ih5Dao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.H5;
import com.meisui.manage.entity.H5_Content;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class H5Service {
	private static Logger log = Logger.getLogger(H5Service.class.getClass());
	@Autowired
	private Ih5Dao ih5Dao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	public String getList(int page, Model model)
	{
		try {
			List<H5> h5List = ih5Dao.getH5List((page-1)*20, 20);
			int totalRecord = ih5Dao.getH5Count();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("h5/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("h5List", h5List);
			model.addAttribute("activeUrl", "h5");
			model.addAttribute("share_url", PropertyUtil.getValue("share_url"));
		} catch (Exception e) {
			log.error("H5信息列表", e);
		}
		return "h5/list";
	}
	public String edit(int id, Model model)
	{
		try {
			H5 h5 = ih5Dao.getH5(id);
			List<H5_Content> h5_ContentList = new ArrayList<H5_Content>();
			List<Integer> iList = new ArrayList<Integer>();
			if(!StringUtils.isBlank(h5.getContent()))
			{
				JSONArray jsonArray = JSONArray.fromObject(h5.getContent());
				for(int i=0 ;i<jsonArray.size();i++)
				{
					H5_Content h5_Content = new H5_Content();
					h5_Content.setContent(jsonArray.getJSONObject(i).getString("content"));
					h5_Content.setType(jsonArray.getJSONObject(i).getInt("type"));
					h5_ContentList.add(h5_Content);
					if(h5_Content.getType()==1)
						iList.add(i);
				}
			}
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("h5", h5);
			model.addAttribute("h5ContentList", h5_ContentList);
			model.addAttribute("array", StringUtils.join(iList,","));
			model.addAttribute("activeUrl", "h5");
		} catch (Exception e) {
			log.error("H5信息编辑页面", e);
		}
		return "h5/edit";
	}
	public String save(int id, String title, String author, String banner, String content, String w_name, HttpServletRequest request)
	{
		try{	
			H5 h5 = new H5();
			h5.setTitle(title);
			h5.setBanner(banner);
			h5.setContent(content);
			h5.setAuthor(author);
			w_name = String.valueOf(request.getAttribute("w_name"));
			h5.setW_name(w_name);
			Date date = new Date();
			h5.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				h5.setId(id);
				result = ih5Dao.updateH5(h5);
				imanage_RecordDao.insertManageRecord(w_name, "更新H5信息", "t_h5", h5.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				h5.setCreate_time(date);
				result = ih5Dao.insertH5(h5);
				imanage_RecordDao.insertManageRecord(w_name, "添加H5信息", "t_h5", h5.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存H5信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
}

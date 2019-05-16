package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IProvince_CenterDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Area;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Province_Center;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class ProvinceCenterService {
	private static Logger log = Logger.getLogger(ProvinceCenterService.class.getClass());
	@Autowired
	private  IProvince_CenterDao iprovince_centerDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	public String getProvinceCenterList(String province_center_name, int province_center_id, int page, Model model) {
		try {
			List<Province_Center> provincelist = iprovince_centerDao.getProviceCenterList(province_center_name,province_center_id,(page-1)*20, 20);
			int totalRecord = iprovince_centerDao.getProviceCenterCount(province_center_name,province_center_id);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("provincecenter/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("provincelist", provincelist);
			model.addAttribute("activeUrl", "provincecenter");
			model.addAttribute("province_center_name", province_center_name);
			model.addAttribute("province_center_id", province_center_id);
		} catch (Exception e) {
			log.error("省代列表", e);
		}
		return "operatecenter/provincecenter";
	}


	public String addProvinceCenter(Model model) {
		model.addAttribute("activeUrl", "provincecenter");
		return "operatecenter/addprovincecenter";
	}


	public int getUsernameExist(String username, int id) {
		try
		{
			Integer agent_id = iprovince_centerDao.getOperateUsernameExist(username, id);
			if(agent_id!=null)
				return 1;
		}
		catch (Exception e) {
			log.error("获取省代后台用户名是否存在", e);
		}
		return 0;
	}


	public String editProvinceCenter(int province_center_id, Model model) {
		try {
			Province_Center center = iprovince_centerDao.getOperateCenterById(province_center_id);
			model.addAttribute("center", center);
			model.addAttribute("activeUrl", "operate");
		}catch (Exception e) {
			log.error("省代运营中心编辑页错误", e);
		}
		return "operatecenter/addprovincecenter";
	}


	public String save(int id, String name, String username, String password, String remark, 
			HttpServletRequest request) {
		try {
			String w_name = String.valueOf(request.getAttribute("w_name"));

			Date date = new Date();
			Province_Center center = new Province_Center();
			center.setName(name);
			center.setUsername(username);
			center.setIs_forbid(0);
			center.setRemark(remark);
			center.setW_name(w_name);
			if(id > 0) {
				center.setId(id);
				if(StringUtils.isNotBlank(password)) {
					String pass = iprovince_centerDao.getPasswordByCenterId(id);
					if(!pass.equals(password)) {
						center.setPassword(AuthUtil.MD5(password));
					}
				}
				center.setUpdate_time(date);
				iprovince_centerDao.update(center);
			}else {			
				center.setCreate_time(date);
				center.setPassword(AuthUtil.MD5(password));
				iprovince_centerDao.save(center);
				imanage_RecordDao.insertManageRecord(w_name, "保存省代运营中心", "t_province_center", center.getId(), IPUtil.getIp(request), date);
			}
			return "{\"code\":0,\"msg\":\"保存成功\"}";		

		}catch (Exception e) {
			log.error("运营中心保存失败", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}


	public void updateProvinceIsonline(int id, int is_forbid, HttpServletRequest request) {
		try {
			String w_name = String.valueOf(request.getAttribute("w_name"));
			iprovince_centerDao.updateProviceIsonline(id, is_forbid);
			imanage_RecordDao.insertManageRecord(w_name, "禁用或解禁省代运营中心", "t_province_center", id, IPUtil.getIp(request), new Date());
		}catch (Exception e) {
			log.error("禁用或解禁错误", e);
		}
		
	}
}

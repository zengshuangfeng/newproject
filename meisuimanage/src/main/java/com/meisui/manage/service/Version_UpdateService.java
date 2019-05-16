package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Iversion_UpdateDao;
import com.meisui.manage.entity.Version_Update;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Version_UpdateService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月18日 上午10:09:02
 */
@Service
public class Version_UpdateService {
	private static Logger log = Logger.getLogger(Version_UpdateService.class.getClass());
	@Autowired
	private Iversion_UpdateDao iversion_UpdateDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：版本更新列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:22:23</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getVersionUpdateList(int page, Model model) {
		try {
			List<Version_Update> version_UpdateList = iversion_UpdateDao.getVersionUpdateList((page-1)*20, 20);
			int totalRecord = iversion_UpdateDao.getVersionUpdateCount();

			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("versionupdate/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("versionUpdateList", version_UpdateList);
			model.addAttribute("activeUrl", "versionupdate");
		} catch (Exception e) {
			log.error("版本更新列表", e);
		}

		return "versionupdate/list";
	}
	/**
	 * 
	 * <p>功能描述：保存版本更新信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:26:59</p>
	 *
	 * @param version 版本
	 * @param content 文案
	 * @param effect_time 生效时间
	 * @param platform 平台
	 * @param w_name 编辑人员
	 * @param request
	 * @param url 
	 * @return
	 */
	public String saveVersionUpdate(String version, String content, Date effect_time, String platform, int force, String w_name, HttpServletRequest request, String url)
	{
		try
		{
			w_name = String.valueOf(request.getAttribute("w_name"));
			Version_Update version_Update = new Version_Update();
			version_Update.setVersion(version);
			version_Update.setPlatform(platform);
			version_Update.setW_name(w_name);
			version_Update.setContent(content);
			version_Update.setEffect_time(effect_time);
			version_Update.setIs_online(1);
			version_Update.setUrl(url);
			if(version_Update.getPlatform().equals("10,20"))
				version_Update.setPlatform("00");			
			version_Update.setForce(force);
			int result = 0;
			Date date =  new Date();
			version_Update.setUpdate_time(date);
			version_Update.setCreate_time(date);
			result = iversion_UpdateDao.insertVersionUpdate(version_Update);
			imanage_RecordDao.insertManageRecord(version_Update.getW_name(), "添加版本更新", "t_version_update", version_Update.getId(), IPUtil.getIp(request), date);
			if(result > 0)
				return "{\"code\":0,\"msg\":\"保存成功\"}";
		}
		catch(Exception ex)
		{
			log.error("保存版本更新信息", ex);
			ex.printStackTrace();
		}	
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：更新版本更新状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月18日 上午10:29:52</p>
	 *
	 * @param id 版本更新id
	 * @param is_online 状态 1正常 0关闭
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateVersionUpdateOnline(int id, int is_online, String w_name, HttpServletRequest request)
	{
		try
		{		
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			int result = iversion_UpdateDao.updateVersionUpdateOnline(id, is_online, w_name, date);
			imanage_RecordDao.insertManageRecord(w_name, "更新版本更新状态", "t_version_update", id, IPUtil.getIp(request), date);
			if(result > 0)
				return 1;
		}
		catch(Exception ex)
		{
			log.error("更新版本更新状态", ex);
			ex.printStackTrace();
		}	
		return 0;
	}
}

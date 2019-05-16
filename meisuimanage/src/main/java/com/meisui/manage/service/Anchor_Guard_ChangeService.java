package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Anchor_Guard_Change;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class Anchor_Guard_ChangeService {
	private static Logger log = Logger.getLogger(Anchor_Guard_ChangeService.class.getClass());
	@Autowired
	private Ianchor_Guard_ChangeDao ianchor_guard_changedao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;

	public String getGuardChangeList(int page, Model model) {
		try {
			List<Anchor_Guard_Change> guardchangeList = ianchor_guard_changedao.getGuardChangeList((page-1)*20, 20);
			int totalRecord = ianchor_guard_changedao.getGuardChangeCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("guardchange/list");
			model.addAttribute("guardchangeList", guardchangeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "guardchange");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("守护配置列表", e);
		}
		return "guardchange/list";
	}

	public int updateIsOnline(String w_name, int id, int is_online, HttpServletRequest request) {
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "更新守护配置上线状态", "t_anchor_guard_change", id, IPUtil.getIp(request), new Date());
			return ianchor_guard_changedao.updateGuardChangeOnline(id, is_online, w_name, date);
		} catch (Exception e) {
			log.error("更新守护配置是否上线状态", e);
			return 0;
		}
	}


	public String addAnchorGuardChange(Model model) {
		model.addAttribute("activeUrl", "guardchange");
		return "guardchange/add";
	}
	public String editAnchorGuardChange(int id, Model model) {
		Anchor_Guard_Change anchorguard = ianchor_guard_changedao.getAnchorGuardByid(id);	
		model.addAttribute("anchorguard", anchorguard);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "guardchange");
		return "guardchange/edit";
	}

	public String saveAnchorGuard(int id, String name, int change_rmb, String guard_head, String entrance_pic, int type,
			String w_name, HttpServletRequest request) {
		try {
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			Anchor_Guard_Change anchorguard = new Anchor_Guard_Change();
			anchorguard.setName(name);
			anchorguard.setType(type);
			anchorguard.setGuard_head(guard_head);
			anchorguard.setChange_rmb(change_rmb);
			anchorguard.setEntrance_pic(entrance_pic);
			anchorguard.setW_name(w_name);
			int result = 0;
			if (id > 0) {
				anchorguard.setId(id);
				anchorguard.setUpdate_time(date);
				result = ianchor_guard_changedao.updateAnchorGuardChange(anchorguard);
				
				if(result>0){
					imanage_RecordDao.insertManageRecord(w_name, "更新守护配置信息", "t_community_recommend",anchorguard.getId(), IPUtil.getIp(request), date);
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}
			} else {
				anchorguard.setCreate_time(date);
				anchorguard.setUpdate_time(date);
				result = ianchor_guard_changedao.inserAnchorGuardChange(anchorguard);
				if(result>0){
					imanage_RecordDao.insertManageRecord(w_name, "添加守护配置", "t_community_recommend",anchorguard.getId(), IPUtil.getIp(request), date);
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}
				
			}
		
		} catch (Exception ex) {
			log.error("保存守护配置", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	
}

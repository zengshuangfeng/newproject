package com.meisui.manage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IinviteDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Invite;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

import repackage.EditBuildScript;

@Service
public class InviteService {
	private static Logger log = Logger.getLogger(Game_TypeService.class.getClass());
	@Autowired
	private IinviteDao iinviteDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 邀请有礼列表
	 * @param page
	 * @param model
	 * @return
	 */
	public String list(int page, Model model)
	{
		try {
			
			List<Invite> inviteList = iinviteDao.getInviteList((page-1)*20, 20);
			int totalRecord = iinviteDao.getInviteCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("recommendbanner/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("inviteList", inviteList);
			model.addAttribute("activeUrl", "invite");
		} catch (Exception e) {
			log.error("邀请有礼列表", e);
		}
		return "invite/list";
	}
	/**
	 * 编辑邀请有礼页面
	 * @param id
	 * @param model
	 * @return
	 */
	public String edit(int id, Model model)
	{
		Invite invite = iinviteDao.getInvite(id);
		model.addAttribute("invite", invite);
		model.addAttribute("activeUrl", "invite");
		return "invite/edit";
	}
	/**
	 * 添加邀请有礼信息
	 * @param id
	 * @param content 文案
	 * @param virtual_count 邀请者获得钻石
	 * @param to_virtual_count 被邀请者获得钻石
	 * @param effect_time 生效时间
	 * @param is_online 是否上线 1是0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String save(int id, String content, int virtual_count, int to_virtual_count, Date effect_time, int is_online, String w_name, HttpServletRequest request)
	{
		try{	
			Invite invite = new Invite();
			invite.setContent(content);
			invite.setVirtual_count(virtual_count);
			invite.setTo_virtual_count(to_virtual_count);
			invite.setIs_online(is_online);
			w_name = String.valueOf(request.getAttribute("w_name"));
			invite.setW_name(w_name);
			Date date = new Date();
			invite.setUpdate_time(date);
			invite.setEffect_time(effect_time);
			int result = 0;
			if(id>0)
			{
				invite.setId(id);
				result = iinviteDao.updateInvite(invite);
				imanage_RecordDao.insertManageRecord(w_name, "更新邀请有礼", "t_invite", invite.getId(), IPUtil.getIp(request), date);
			}
			else {
				invite.setCreate_time(date);
				result = iinviteDao.insertInvite(invite);
				imanage_RecordDao.insertManageRecord(w_name, "添加邀请有礼", "t_invite", invite.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存邀请有礼信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/***
	 * 更新邀请有礼上线状态
	 * @param w_name 编辑人员
	 * @param id 邀请有礼ID
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	public int updateOnline(String w_name, int id, int is_online, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iinviteDao.updateInviteOnline(id, is_online, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "更新邀请有礼上线状态", "t_invite", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("更新邀请有礼状态",e);
		}
		return result;
	}
}

package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ianchor_Invite_ConfigDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Invite_Config;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Anchor_Invite_ConfigService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月18日 下午3:51:05
 */
@Service
public class Anchor_Invite_ConfigService {
	private static Logger log = Logger.getLogger(Anchor_Invite_ConfigService.class.getClass());
	@Autowired
	private Ianchor_Invite_ConfigDao ianchor_Invite_ConfigDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：主播邀请配置列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 下午4:03:38</p>
	 *
	 * @param uid 主播uid
	 * @param nickname 昵称
	 * @param is_online 是否开启 1是 0否
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getAnchorInviteConfigList(long uid, String nickname, int is_online, int page, Model model)
	{
		try {
			List<Anchor_Invite_Config> anchor_Invite_ConfigList = ianchor_Invite_ConfigDao.getAnchorInviteConfigList(uid, nickname, is_online, (page-1)*20, 20);
			Integer reward_count = 0;
			for (Anchor_Invite_Config anchor_Invite_Config : anchor_Invite_ConfigList) {
				reward_count = ianchor_Invite_ConfigDao.getAnchorRewardCount(anchor_Invite_Config.getUid());
				if(reward_count!=null)
					anchor_Invite_Config.setReward_count(reward_count);
			}			
			int totalRecord = ianchor_Invite_ConfigDao.getAnchorInviteConfigCount(uid, nickname, is_online);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorinvite/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorInviteConfigList", anchor_Invite_ConfigList);
			model.addAttribute("activeUrl", "anchorinvite");
			model.addAttribute("uid", uid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("is_online", is_online);
		} catch (Exception e) {
			log.error("主播邀请配置列表", e);
		}
		return "anchorinvite/list";
	}
	/**
	 * 
	 * <p>功能描述：主播邀请配置添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:22:43</p>
	 *
	 * @param model
	 * @return
	 */
	public String addAnchorInviteConfig(Model model)
	{
		model.addAttribute("activeUrl", "anchorinvite");
		return "anchorinvite/add";
	}
	/**
	 * 
	 * <p>功能描述：主播邀请配置编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 主播邀请配置id
	 * @param model
	 * @return
	 */
	public String editAnchorInviteConfig(int id, Model model)
	{
		Anchor_Invite_Config anchor_Invite_Config = ianchor_Invite_ConfigDao.getAnchorInviteConfig(id);
		User user = iuserDao.getUser(anchor_Invite_Config.getUid());
		anchor_Invite_Config.setNickname(user.getNickname());
		model.addAttribute("anchorInviteConfig", anchor_Invite_Config);
		model.addAttribute("activeUrl", "anchorinvite");
		return "anchorinvite/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存主播邀请配置信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 下午3:59:49</p>
	 *
	 * @param id 主播邀请配置表id
	 * @param uid 主播uid
	 * @param max_reward 最大随机钻石数
	 * @param min_reward 最小随机钻石数
	 * @param is_online 是否开启 1是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveAnchorInviteConfig(int id, long uid, int max_reward, int min_reward, int is_online, String w_name, HttpServletRequest request)
	{
		try{	
			User _user = iuserDao.getUser(uid);
			Anchor_Invite_Config anchor_Invite_Config = new Anchor_Invite_Config();
			anchor_Invite_Config.setUid(uid);
			anchor_Invite_Config.setF_uuid(Long.parseLong(_user.getF_uuid()));
			anchor_Invite_Config.setMax_reward(max_reward);
			anchor_Invite_Config.setMin_reward(min_reward);
			anchor_Invite_Config.setIs_online(is_online);
			w_name = String.valueOf(request.getAttribute("w_name"));
			anchor_Invite_Config.setW_name(w_name);
			Date date = new Date();
			anchor_Invite_Config.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				anchor_Invite_Config.setId(id);
				result = ianchor_Invite_ConfigDao.updateAnchorInviteConfig(anchor_Invite_Config);
				imanage_RecordDao.insertManageRecord(w_name, "更新主播邀请配置信息", "t_anchor_invite_config", anchor_Invite_Config.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				anchor_Invite_Config.setCreate_time(date);
				result = ianchor_Invite_ConfigDao.insertAnchorInviteConfig(anchor_Invite_Config);
				imanage_RecordDao.insertManageRecord(w_name, "添加主播邀请配置信息", "t_anchor_invite_config", anchor_Invite_Config.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存主播邀请配置信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除主播邀请配置</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 主播邀请配置id
	 * @param request
	 * @return
	 */
	public int deleteAnchorInviteConfig(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = ianchor_Invite_ConfigDao.deleteAnchorInviteConfig(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除主播邀请配置", "t_anchor_invite_config", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除主播邀请配置",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：获取主播邀请配置是否存在</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月18日 下午4:03:19</p>
	 *
	 * @param uid 主播uid
	 * @return
	 */
	public String getAnchorInviteConfigExist(long uid){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("nickname", "");
		try {
			Integer id = ianchor_Invite_ConfigDao.getAnchorInviteConfigIdWithUid(uid);
			if(id!=null)
				jsonObject.put("code", 1);
			User user = iuserDao.getUser(uid);
			if(user!=null)
				jsonObject.put("nickname", user.getNickname());
			else
				jsonObject.put("code", -1);
		} catch (Exception e) {
			log.error("获取主播邀请配置是否存在",e);
		}
		return jsonObject.toString();
	}
}

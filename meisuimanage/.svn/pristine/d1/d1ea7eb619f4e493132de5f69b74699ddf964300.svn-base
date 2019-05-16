package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ilevel_ScoreDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Level_Score;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：Level_ScoreService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午4:48:34
 */
@Service
public class Level_ScoreService {
	private static Logger log = Logger.getLogger(Level_ScoreService.class.getClass());
	@Autowired
	private Ilevel_ScoreDao ilevel_ScoreDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：等级列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getLevelScoreList(int page, Model model)
	{
		try {
			List<Level_Score> level_ScoreList = ilevel_ScoreDao.getLevelScoreList( (page-1)*20, 20);
			int totalRecord = ilevel_ScoreDao.getLevelScoreCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("levelscore/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("levelScoreList", level_ScoreList);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "levelscore");
		} catch (Exception e) {
			log.error("等级列表", e);
		}
		return "levelscore/list";
	}
	/**
	 * 
	 * <p>功能描述：等级编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 等级id
	 * @param model
	 * @return
	 */
	public String editLevelScore(int id, Model model)
	{
		Level_Score level_Score = ilevel_ScoreDao.getLevelScore(id);
		model.addAttribute("levelScore", level_Score);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "levelscore");
		return "levelscore/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存等级信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 等级id
	 * @param name 等级名称
	 * @param icon 等级icon
	 * @param level 等级
	 * @param score 经验分数
	 * @param recharge_rmb 充值金额
	 * @param is_online 是否上线 1是 0否
	 * @param color 等级图标色值
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveLevelScore(int id, String name, String icon, int level, int score, int recharge_rmb, int is_online, String color, String w_name, HttpServletRequest request)
	{
		try{	
			Level_Score level_Score = new Level_Score();
			level_Score.setName(name);
			level_Score.setIcon(icon);
			level_Score.setLevel(level);
			level_Score.setScore(score);
			level_Score.setRecharge_rmb(recharge_rmb);
			level_Score.setIs_online(is_online);
			level_Score.setColor(color);
			w_name = String.valueOf(request.getAttribute("w_name"));
			level_Score.setW_name(w_name);
			Date date = new Date();
			level_Score.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				level_Score.setId(id);
				result = ilevel_ScoreDao.updateLevelScore(level_Score);
				imanage_RecordDao.insertManageRecord(w_name, "更新等级信息", "t_level_score", level_Score.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				level_Score.setCreate_time(date);
				result = ilevel_ScoreDao.insertLevelScore(level_Score);
				imanage_RecordDao.insertManageRecord(w_name, "添加等级信息", "t_level_score", level_Score.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存等级信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：删除等级</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 等级id
	 * @param request
	 * @return
	 */
	public int deleteLevelScore(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = ilevel_ScoreDao.deleteLevelScore(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除等级", "t_level_score", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除等级",e);
		}
		return result;
	}
}

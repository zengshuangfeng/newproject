package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Iuser_Check_RuleDao;
import com.meisui.manage.entity.User_Check_Rule;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：User_Check_RuleService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午11:15:56
 */
@Service
public class User_Check_RuleService {
	private static Logger log = Logger.getLogger(User_Check_RuleService.class.getClass());
	@Autowired
	private Iuser_Check_RuleDao iuser_Check_RuleDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：每日签到列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午11:18:00</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getUserCheckRuleList(int page, Model model)
	{
		try {
			List<User_Check_Rule> user_Check_RuleList = iuser_Check_RuleDao.getUserCheckRuleList((page-1)*20, 20);
			int totalRecord = iuser_Check_RuleDao.getUserCheckRuleCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("checkrule/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userCheckRuleList", user_Check_RuleList);
			model.addAttribute("activeUrl", "checkrule");
		} catch (Exception e) {
			log.error("每日签到列表", e);
		}
		return "checkrule/list";
	}
	/**
	 * 
	 * <p>功能描述：提取每日签到信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午11:22:38</p>
	 *
	 * @param id 每日签到id
	 * @return
	 */
	public String getUserCheckRule(int id)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			User_Check_Rule user_Check_Rule = iuser_Check_RuleDao.getUserCheckRule(id);		
			jsonObject = JSONObject.fromObject(user_Check_Rule);
		} catch (Exception e) {
			log.error("获取每日签到信息", e);
		}
		return jsonObject.toString();
	}
	/**
	 * 
	 * <p>功能描述：更新每日签到</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年1月4日 上午11:20:35</p>
	 *
	 * @param id 每日签到id
	 * @param days 签到第几天
	 * @param reward_count 奖励钻石数
	 * @param min_rand 最小随机数
	 * @param max_rand 最大随机数
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveUserCheckRule(int id, int days, int reward_count, int min_rand, int max_rand, String w_name, HttpServletRequest request)
	{
		try{	
			User_Check_Rule user_Check_Rule =  new User_Check_Rule();
			user_Check_Rule.setDays(days);
			user_Check_Rule.setReward_count(reward_count);
			w_name = String.valueOf(request.getAttribute("w_name"));
			user_Check_Rule.setW_name(w_name);
			user_Check_Rule.setMin_rand(min_rand);
			user_Check_Rule.setMax_rand(max_rand);
			Date date = new Date();
			int result = 0;
			user_Check_Rule.setId(id);
			user_Check_Rule.setCreate_time(date);
			result = iuser_Check_RuleDao.updateUserCheckRule(user_Check_Rule);
			imanage_RecordDao.insertManageRecord(w_name, "更新每日签到", "t_user_check_rule", user_Check_Rule.getId(), IPUtil.getIp(request), date);
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("更新每日签到", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
}

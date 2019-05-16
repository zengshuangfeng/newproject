package com.meisui.manage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Igame_ProfitDao;
import com.meisui.manage.entity.Game_Profit;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Game_ProfitService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午1:56:39
 */
@Service
public class Game_ProfitService {
	private static Logger log = Logger.getLogger(Game_CapitalService.class.getClass());
	@Autowired
	private Igame_ProfitDao igame_ProfitDao;
	/***
	 * 
	 * <p>功能描述：游戏历史收益列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:00:09</p>
	 *
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getList(Date start_time, Date end_time, int page, Model model)
	{
		try {
			String end_timeString = end_time != null?AuthUtil.formatDateToString(end_time, "yyyy-MM-dd"):"";
			if(end_time != null)
			{
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_time); 
				calendar.add(Calendar.DATE,1);
				end_time=calendar.getTime();
			}
			List<Game_Profit> game_ProfitList = igame_ProfitDao.getGameCapitalList(start_time, end_time, (page-1)*20, 20);
			int totalRecord = igame_ProfitDao.getGameCapitalCount(start_time, end_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("gameprofit/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("end_time", end_timeString);
			model.addAttribute("activeUrl", "gameprofit");
			model.addAttribute("gameProfitList", game_ProfitList);
		} catch (Exception e) {
			log.error("游戏历史收益列表", e);
		}
		return "gameprofit/list";
	}
}

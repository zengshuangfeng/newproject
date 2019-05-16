package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.forman.foundation.library.DateUtils;
import com.forman.foundation.library.DoubleUtil;
import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.Igame_CapitalDao;
import com.meisui.manage.dao.Igameroom_RecordDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IsquareDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.All_Game_Capital;
import com.meisui.manage.entity.Game_Capital;
import com.meisui.manage.entity.Gameroom_Record;
import com.meisui.manage.entity.Gameroom_Record_Total;
import com.meisui.manage.entity.Square;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：Gameroom_RecordService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 下午2:39:39
 */
@Repository
public class Gameroom_RecordService {
	private static Logger log = Logger.getLogger(Game_CapitalService.class.getClass());
	@Autowired
	private Igameroom_RecordDao igameroom_RecordDao;
	@Autowired
	private Igame_CapitalDao igame_CapitalDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IsquareDao isquareDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：游戏房间列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:49:44</p>
	 *
	 * @param anchor_state -1全部 1直播中
	 * @param start_time 开始时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	//临时
	public String getList(int anchor_state, Date start_time, int page, Model model)
	{
		try {
			Date date = new Date();
			List<All_Game_Capital> all_Game_CapitalList = new ArrayList<All_Game_Capital>();
			for(int type=0;type<5;type++)
			{
				Map<String, String> gameMap = RedisUtil.Gethgetall(4, "allgamecapital_"+DateUtils.sdf.format(date)+"_"+type);
				long user_all = Long.valueOf(null==gameMap.get("user_all")?"0":gameMap.get("user_all"));
				long user_win = Long.valueOf(null==gameMap.get("user_win")?"0":gameMap.get("user_win"));
				All_Game_Capital all_Game_Capital = new All_Game_Capital();
				all_Game_Capital.setUser_all(user_all);
				long gap = 0L;
				if(user_all>0)
				{
					gap = user_all - user_win;
					all_Game_Capital.setBanker_win(gap);
					all_Game_Capital.setPercent(DoubleUtil.mul(DoubleUtil.div(gap*1.0d, user_all, 4), 100));
				}
				all_Game_Capital.setType(type);
				all_Game_CapitalList.add(all_Game_Capital);					
			}
			String win_str = RedisUtil.Gethget(4, "allgamecapital_"+DateUtils.sdf.format(date), "user_win");
			String all_str = RedisUtil.Gethget(4, "allgamecapital_"+DateUtils.sdf.format(date), "user_all");
			long win = 0L, all = 0L, gap = 0L;
			if(!StringUtils.isBlank(win_str))
				win = Long.parseLong(win_str);
			if(!StringUtils.isBlank(all_str))
				all = Long.parseLong(all_str);
			double percent = 0.00;
			if(all>0)
			{
				gap = all - win;
				percent = DoubleUtil.mul(DoubleUtil.div(gap*1.0d, all, 4), 100);
			}
			model.addAttribute("all", all);
			model.addAttribute("gap", gap);
			model.addAttribute("percent", percent);
			model.addAttribute("allGameCapitalList", all_Game_CapitalList);
			model.addAttribute("activeUrl", "gameroomrecord");
		} catch (Exception e) {
			log.error("游戏房间列表", e);
		}
		return "gameroomrecord/templist";
	}
	/*public String getList(int anchor_state, Date start_time, int page, Model model)
	{
		try {
			Date current_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(new Date()),"yyyy-MM-dd");
			if(start_time==null)
				start_time = current_date;
			List<Gameroom_Record_Total> gameroom_Record_TotalList = igameroom_RecordDao.getGameroomRecordTotalList(start_time, anchor_state, (page-1)*20, 20);
			for (Gameroom_Record_Total gameroom_Record_Total : gameroom_Record_TotalList) {
				if(StringUtils.isBlank(gameroom_Record_Total.getT()))
				{
					if(gameroom_Record_Total.getUid()==0)
					{
						long uid = iuserDao.getUserIdWithF_uuid(String.valueOf(gameroom_Record_Total.getF_uuid()));
						gameroom_Record_Total.setUid(uid);
					}
					gameroom_Record_Total.setT(iuserDao.getUserInfoExtraToken(gameroom_Record_Total.getUid()));
				}
				gameroom_Record_Total.setNickname(RedisUtil.Gethget(0, gameroom_Record_Total.getT(), "nickname"));
			}
			int totalRecord = igameroom_RecordDao.getGameroomRecordTotalCount(start_time, anchor_state);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("gameroomrecord/list");
			model.addAttribute("showPage", pageUtil);

			Long total_bet_count = igameroom_RecordDao.getTotalBetCountSum(current_date);
			if(total_bet_count==null)
				total_bet_count = 0L;
			Long current_virtual_count = igameroom_RecordDao.getCurrentVirtualCountSum();
			if(current_virtual_count==null)
				current_virtual_count = 0L;
			Game_Capital game_Capital = igame_CapitalDao.getGameCapital(0);
			double percent = 0;
			if(game_Capital==null||game_Capital.getCapital_pool()==0)
				current_virtual_count = 0L;
			else {
				percent = DoubleUtil.mul(DoubleUtil.div((current_virtual_count+game_Capital.getCapital_pool()),game_Capital.getCapital_pool(),4),100);
			}
			model.addAttribute("total_bet_count", total_bet_count);
			model.addAttribute("current_virtual_count", current_virtual_count);
			model.addAttribute("percent", percent);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("activeUrl", "gameroomrecord");
			model.addAttribute("anchor_state", anchor_state);
			model.addAttribute("gameroomRecordTotalList", gameroom_Record_TotalList);

		} catch (Exception e) {
			log.error("游戏房间列表", e);
		}
		return "gameroomrecord/list";
	}*/
	/**
	 * 
	 * <p>功能描述：游戏房间干预</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:45:11</p>
	 *
	 * @param w_name
	 * @param id 游戏房间数据id
	 * @param is_intervene 是否干预 1是 0否
	 * @param request
	 * @return
	 */
	public int updateIntervene(String w_name, int id, HttpServletRequest request){
		int result=0;
		try {
			if (id>0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = igameroom_RecordDao.updateGameroomRecordTotalIntervene(id);
				imanage_RecordDao.insertManageRecord(w_name, "游戏房间游戏干预", "t_gameroom_record", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("游戏房间游戏干预",e);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述：主播游戏列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 下午2:49:33</p>
	 *
	 * @param f_uuid 主播房间号
	 * @param start_time 开始时间
	 * @param page
	 * @param model
	 * @return
	 */
	public String getListWithF_uuid(long f_uuid, Date start_time, int page, Model model)
	{
		try {
			Date current_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(new Date()),"yyyy-MM-dd");
			if(start_time==null)
				start_time = current_date;
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(start_time); 
			calendar.add(Calendar.DATE,1);
			Date end_time=calendar.getTime();
			List<Gameroom_Record> gameroom_RecordList = igameroom_RecordDao.getGameroomRecordListWithF_uuid(f_uuid, start_time, end_time, (page-1)*20, 20);
			int totalRecord = igameroom_RecordDao.getGameroomRecordCountWithF_uuid(f_uuid, start_time, end_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("gameroomrecord/anchorlist");			
			Long total_bet_count = igameroom_RecordDao.getTotalBetCountSumWithF_uuid(f_uuid, start_time);
			if(total_bet_count==null)
				total_bet_count = 0L;
			Long current_virtual_count = igameroom_RecordDao.getCurrentVirtualCountSumWithF_uuid(f_uuid, start_time);
			if(current_virtual_count==null)
				current_virtual_count = 0L;
			model.addAttribute("total_bet_count", total_bet_count);
			model.addAttribute("current_virtual_count", current_virtual_count);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("start_time", start_time != null?AuthUtil.formatDateToString(start_time, "yyyy-MM-dd"):"");
			model.addAttribute("activeUrl", "gameroomrecord");
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("gameroomRecordList", gameroom_RecordList);
		} catch (Exception e) {
			log.error("游戏房间列表", e);
		}
		return "gameroomrecord/anchorlist";
	}
}

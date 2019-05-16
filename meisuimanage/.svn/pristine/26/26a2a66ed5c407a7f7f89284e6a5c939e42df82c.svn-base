package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Igame_CapitalDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Game_Capital;
import com.meisui.manage.utils.IPUtil;

/**
 * <p>文件名称：Game_CapitalService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月6日 上午11:23:25
 */
@Service
public class Game_CapitalService {
	private static Logger log = Logger.getLogger(Game_CapitalService.class.getClass());
	@Autowired
	private Igame_CapitalDao igame_CapitalDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：游戏调控设置</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 上午11:31:11</p>
	 *
	 * @param model
	 * @return
	 */
	public String getList(Model model)
	{
		try {
			List<Game_Capital> game_CapitalList = igame_CapitalDao.getGameCapitalList();
			model.addAttribute("gameCapitalList", game_CapitalList);
			model.addAttribute("activeUrl", "gamecapital");
		} catch (Exception e) {
			log.error("游戏调控设置", e);
		}
		return "gamecapital/list";
	}
	/**
	 * 
	 * <p>功能描述：保存游戏调控设置信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月6日 上午11:39:19</p>
	 *
	 * @param id 控盘表id
	 * @param upper_limit 房间上限
	 * @param lower_limit 房间下限
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String save(int id, float upper_limit, float lower_limit, String w_name, HttpServletRequest request)
	{
		try {
			Game_Capital game_capital = new Game_Capital();
			game_capital.setUpper_limit(upper_limit);
			game_capital.setLower_limit(lower_limit);
			int result = 0;
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			if(id>0)
			{
				game_capital.setId(id);
				result = igame_CapitalDao.updateGameCapital(game_capital);
				imanage_RecordDao.insertManageRecord(w_name, "更新游戏调控设置，上限："+upper_limit+"，下限："+lower_limit, "t_game_capital", game_capital.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				result = igame_CapitalDao.insertGameCapital(game_capital);
				imanage_RecordDao.insertManageRecord(w_name, "添加游戏调控设置，上限："+upper_limit+"，下限："+lower_limit, "t_game_capital", game_capital.getId(), IPUtil.getIp(request), date);
			}		
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			log.error("保存游戏调控设置信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
}

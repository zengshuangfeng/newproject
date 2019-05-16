package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Igame_TypeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Game_Type;
import com.meisui.manage.utils.IPUtil;

/**
 * <p>文件名称：Game_TypeService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年3月15日 上午10:12:43
 */
@Service
public class Game_TypeService {
	private static Logger log = Logger.getLogger(Game_TypeService.class.getClass());
	@Autowired
	private Igame_TypeDao igame_TypeDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：游戏列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 上午10:19:44</p>
	 *
	 * @param model
	 * @return
	 */
	public String getList(Model model)
	{
		try {
			List<Game_Type> game_TypeList = igame_TypeDao.getGameTypeList();
			model.addAttribute("gameTypeList", game_TypeList);
			model.addAttribute("activeUrl", "gametype");
		} catch (Exception e) {
			log.error("游戏列表", e);
		}
		return "gametype/list";
	}
	/**
	 * 
	 * <p>功能描述：更新游戏是否开启</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 上午10:19:55</p>
	 *
	 * @param id 游戏id
	 * @param is_open 是否开启 1 是 0否
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateOpen(int id, int is_open, String w_name, HttpServletRequest request)
	{
		try {
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			igame_TypeDao.updateGameTypeOpen(id, is_open, w_name, date);
			imanage_RecordDao.insertManageRecord(w_name, "更新游戏是否开启", "t_game_type", id, IPUtil.getIp(request), date);
			return 1;
		} catch (Exception e) {
			log.error("更新游戏是否开启", e);
		}
		return 0;
	}
	/**
	 * 
	 * <p>功能描述：更新游戏排序</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月15日 上午10:19:55</p>
	 *
	 * @param id 游戏id
	 * @param sort 排序
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public int updateSort(int id, int sort, String w_name, HttpServletRequest request)
	{
		try {
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			igame_TypeDao.updateGameTypeSort(id, sort, w_name, date);
			imanage_RecordDao.insertManageRecord(w_name, "更新游戏排序", "t_game_type", id, IPUtil.getIp(request), date);
			return 1;
		} catch (Exception e) {
			log.error("更新游戏排序", e);
		}
		return 0;
	}
}

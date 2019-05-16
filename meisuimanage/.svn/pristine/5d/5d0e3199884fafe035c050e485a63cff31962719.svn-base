package com.meisui.manage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IChristmasDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Christmas_Prize;
import com.meisui.manage.entity.Christmas_User;
import com.meisui.manage.entity.Community_Recommend;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class ChristmasService {
	private static Logger log = Logger.getLogger(ChristmasService.class.getClass());
	@Autowired
	private IChristmasDao Ichristmasdao;
	@Autowired
	private IuserDao iuserdao;
	@Autowired
	private Igift_InfoDao igift_dao;

	public String getChristmasList(String f_uuid, int uid, int type, String start_time, String end_time, int page,
			Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			
			List<Christmas_User> christmasList = Ichristmasdao.getChristmasList(f_uuid,uid,type, b_time, e_time,
					(page - 1) * 20, 20);
			int totalRecord = Ichristmasdao.getChristmasRecordCount(f_uuid,uid,type, b_time, e_time);
			
			for (Christmas_User christmaslist : christmasList) {			
				User user=iuserdao.getUser((long)christmaslist.getUid());
				if(user !=null){
					christmaslist.setF_uuid(user.getF_uuid());//用户房间号
					christmaslist.setNickname(user.getNickname());
				}
				
				Christmas_Prize christmas_prize=Ichristmasdao.getChristmasPrize(christmaslist.getPrize_id());//通过prize_id获取数据
				if(christmas_prize!=null){
					Gift_Info gift=igift_dao.getGiftInfo(christmas_prize.getGift_id());
					if(gift!=null)
					christmaslist.setGift_name(gift.getGift_name());
					Christmas_Prize cPrize=new Christmas_Prize();
					cPrize.setType(christmas_prize.getType());
					cPrize.setGift_id(christmas_prize.getGift_id());
					cPrize.setPic(christmas_prize.getPic());
					cPrize.setPrize_count(christmas_prize.getPrize_count());
					christmaslist.setChristmas_prize(cPrize);
				}
						
			}
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("christmas/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("christmasList", christmasList);
			model.addAttribute("activeUrl", "christmas");
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("uid", uid);
			model.addAttribute("type", type);			
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		} catch (Exception e) {
			log.error("圣诞节中奖明细", e);
		}
		return "christmas/list";
	}

}

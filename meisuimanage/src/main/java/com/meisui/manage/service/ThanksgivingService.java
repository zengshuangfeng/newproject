package com.meisui.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.ThanksgivingDao;
import com.meisui.manage.entity.Thanksgiving;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.PageUtil;

@Service
public class ThanksgivingService {
	
	@Autowired
	private ThanksgivingDao thanksDao;
	@Autowired
	private IuserDao userDao;

	public String list(long uid, int page, Model model) {
		List<Thanksgiving> list = thanksDao.list(uid,(page - 1)* 20, 20);
		for(Thanksgiving thanks : list){
			long tuid = thanks.getUid();
			User user = userDao.getUser(tuid);
			long greatCount = 0;
			String tempGreatCount = RedisUtil.get(8, String.valueOf(thanks.getId()));//虚拟赞数
			if(tempGreatCount != null){
				greatCount = Long.parseLong(tempGreatCount);
			}
			greatCount += RedisUtil.Gethlen(7, String.valueOf(thanks.getId()));
			thanks.setGreatcount(greatCount);
			thanks.setNickname(user.getNickname());
			thanks.setFuuid(user.getF_uuid());
			thanks.setLevel(user.getLevel());
		}
		int totalRecord = thanksDao.listCount(uid);
		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
		pageUtil.setTotalRecord(totalRecord);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(14);
		pageUtil.setUrlName("thanks/list");
		model.addAttribute("showPage", pageUtil);
		model.addAttribute("list", list);
		model.addAttribute("activeUrl", "thanks");
		model.addAttribute("uid", uid);
		return "thanks/list";
	}

	public int delete(long id) {
		int success = 0;
		try{
			thanksDao.delete(id);
			success = 1;
		}catch(Exception e){
			
		}
		return success;
		
	}

	public int addGreatCount(long id, int num) {
		int success = 0;
		int addGreatCount = 0;
		try{
			String tempNum = RedisUtil.get(8, String.valueOf(id));
			if(tempNum != null){
				addGreatCount = Integer.parseInt(tempNum);
			}
			addGreatCount += num;
			RedisUtil.SetShardedJedis(8, String.valueOf(id), String.valueOf(addGreatCount));
			addGreatCount += RedisUtil.Gethlen(7, String.valueOf(id));
			thanksDao.addGreatCount(id, addGreatCount);
			success = 1;
		}catch(Exception e){
			
		}
		return success;
	}
	

}

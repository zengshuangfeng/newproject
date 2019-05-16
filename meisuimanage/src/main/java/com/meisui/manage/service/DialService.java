package com.meisui.manage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IdialDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Dial_Prize;
import com.meisui.manage.entity.Dial_User;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class DialService {
	private static Logger log = Logger.getLogger(DialService.class.getClass());
	@Autowired
	private IdialDao idialdao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private Igift_InfoDao igift_infoDao;
	@Autowired
	private IuserDao iuseDao;
	
	public String getDialList(int page, Model model) {		
		try {
			Date date=new Date();		
			List<Dial_Prize> dialList = idialdao.getDialList((page-1)*20, 20);
			int totalRecord = idialdao.getDialListCount();
			for(Dial_Prize dial:dialList){
				if(dial.getType()==0){
					Gift_Info gift_info=igift_infoDao.getGiftInfo(dial.getGift_id());
					if(gift_info!=null)
					dial.setPrize_name(gift_info.getGift_name());
				}	
			Date endtime=dial.getEnd_time();			
			if(endtime.getTime()>date.getTime()){
				dial.setState(1);//上架
			}else{
				dial.setState(0);//下架
				}				
			}		
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("dial/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "dial");
			model.addAttribute("dialList", dialList);
		} catch (Exception e) {
			log.error("转盘抽奖列表", e);
		}
		return "dial/list";
	}

	public String getAddList(Model model) {
		try{
			List<Gift_Info> giftlist=igift_infoDao.getGiftInfoWithIsOnline();
			model.addAttribute("giftlist", giftlist);
			model.addAttribute("activeUrl", "dial");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		}catch(Exception e){
			log.error("转盘抽奖新增奖品",e);
		}
		return "dial/add";
	}

	public Map<String, Object> getGiftnameShow(Integer gift_id, Model model) {
		Map<String,Object> map=new HashMap<String, Object>();	
		Gift_Info giftinfo=igift_infoDao.getGiftInfo(gift_id);
		if(giftinfo !=null){
			String gift_name=giftinfo.getGift_name();
			String pic=giftinfo.getGift_head();
			int id=giftinfo.getId();
			map.put("gift_name", gift_name);
			map.put("pic", pic);
			map.put("id", id);
			map.put("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl","dial");
			return map;
		}else{
			return map;
		}
	}

	public String Save(int id,int gift_id, String pic,String giftpic, int type, String prize_name, int prize_count, double probability,
			int inventory, Date start_time, Date end_time, HttpServletRequest request, Model model) {
		
		try{	
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date=new Date();
			Dial_Prize dialprize = new Dial_Prize();
			dialprize.setType(type);
			dialprize.setGift_id(gift_id);
			if(type==0){
				dialprize.setPic(giftpic);
			}else{
				dialprize.setPic(pic);
			}			
			dialprize.setPrize_count(prize_count);
			dialprize.setProbability(probability);
			dialprize.setInventory(inventory);
			dialprize.setStart_time(start_time);
			dialprize.setEnd_time(end_time);	
			dialprize.setW_name(w_name);		
			dialprize.setIs_del(0);		
			int result = 0;
			if(id>0)
			{
				dialprize.setId(id);
				dialprize.setUpdate_time(date);
				result = idialdao.updatedialprize(dialprize);
				imanage_RecordDao.insertManageRecord(w_name, "更新转盘抽奖信息,奖品数量"+dialprize.getPrize_count()+",中奖概率为"+dialprize.getProbability()+",库存为"+dialprize.getInventory(), "t_dial_prize", dialprize.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				dialprize.setCreate_time(date);
				result = idialdao.insertdialprize(dialprize);
				imanage_RecordDao.insertManageRecord(w_name, "添加转盘抽奖,奖品数量"+dialprize.getPrize_count()+",中奖概率为"+dialprize.getProbability()+",库存为"+dialprize.getInventory(), "t_dial_prize", dialprize.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存奖品添加", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String edit(int id, Model model) {
		try {
			Dial_Prize dialprize = idialdao.getDialPrize(id);
			List<Gift_Info> giftlist=igift_infoDao.getGiftInfoWithIsOnline();
			if(dialprize.getType()==0 && dialprize.getGift_id()>0){
				Gift_Info giftinfo=igift_infoDao.getGiftInfo(dialprize.getGift_id());
				model.addAttribute("giftname", giftinfo.getGift_name());
			}else if(dialprize.getType()==1){
				model.addAttribute("giftname", "钻石");
			}else if(dialprize.getType()==2){
				model.addAttribute("giftname", "积分");
			}
			model.addAttribute("giftlist", giftlist);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("dialprize", dialprize);
			model.addAttribute("gift_id", dialprize.getGift_id());
			model.addAttribute("activeUrl", "dial");
		} catch (Exception e) {
			log.error("转盘抽奖信息编辑页面", e);
		}
		return "dial/edit";
	}

	public String getDialUser(Long f_uuid, int page,Model model) {
		try {
			User user=iuseDao.getUserByF_uuid(f_uuid);
			long uid=0;
			if(user !=null){
				uid=user.getId();
			}else{
				uid=0L;
			}
			List<Dial_User> dialuserlist = idialdao.getDialUserList(uid,(page-1)*20, 20);
			int totalRecord = idialdao.getDialUserListCount(uid);
			for(Dial_User dialuser:dialuserlist){
				//获取中奖人员房间号和昵称
				User userinfo=iuseDao.getUser((long)dialuser.getUid());
				dialuser.setF_uuid(userinfo.getF_uuid());
				dialuser.setNickname(userinfo.getNickname());
				//或许奖品类型
				Dial_Prize dialprize=idialdao.getDialPrize(dialuser.getPrize_id());
				if(dialprize!=null){
					dialuser.setType(dialprize.getType());
					dialuser.setGift_id(dialprize.getGift_id());
				}				
			}		
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("dial/dialuser");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "dial");
			model.addAttribute("dialuserlist", dialuserlist);
			model.addAttribute("f_uuid", f_uuid);
		} catch (Exception e) {
			log.error("转盘抽奖中奖人员列表", e);
		}
		return "dial/dialuser";
	}	
}

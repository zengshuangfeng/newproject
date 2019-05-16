package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Igift_BoxDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Gift_Box;
import com.meisui.manage.entity.Gift_Box_Record;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

@Service
public class Gift_BoxService {
	private static Logger log = Logger.getLogger(Gift_BoxService.class.getClass());
	@Autowired
	private Igift_BoxDao giftboxdao;
	@Autowired
	private Igift_InfoDao giftinfodao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IuserDao iuserDao;
	
	public String deleteGiftBox(int id,int count, int uid, int type, String w_name, HttpServletRequest request) {
		int result=0;
		String url="/userinfo/detail?id="+uid+"&type=6";
		try {
			Date date=new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				
				Gift_Box giftbox=giftboxdao.getGiftBoxByid(id);
			int	giftcount=giftbox.getGift_count();
			if(giftcount-count >=0){
				result = giftboxdao.deleteGiftBox(id,count,w_name,date);
			}else{
				result=0;
			}		
				if(result>0){
					Gift_Box giftboxlist=giftboxdao.getGiftBoxByid(id);
					int gift_id=giftboxlist.getGift_id();
					int box_type=giftboxlist.getBox_type();
					
					giftboxdao.insertGiftBoxRecord(uid,count,gift_id,box_type,w_name);
					
				}
			
		} catch (Exception e) {
			log.error("删除礼物箱记录",e);
		}
		
		return "redirect:" + url;
	}

	public String getGiftBoxList(int uid, int gift_id, int page, Model model) {
		
		try {
			List<Gift_Box_Record> gift_BoxRecordList = giftboxdao.getGiftBoxRecordList(uid,gift_id,(page-1)*20, 20);
			for(Gift_Box_Record giftbox:gift_BoxRecordList){
				Gift_Info gift_info=giftinfodao.getGiftInfo(giftbox.getGift_id());
				giftbox.setGift_name(gift_info.getGift_name());
				
				Long id=(long) giftbox.getUid();
				 User user=iuserDao.getUser(id);
				 giftbox.setF_uuid(user.getF_uuid()); 
				 giftbox.setNickname( user.getNickname());
				 int userid=(int)user.getId();
				 giftbox.setUid(userid);
			}
			
			
			int totalRecord = giftboxdao.getGiftBoxRecordCount(uid,gift_id);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("giftbox/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("giftBoxRecordList", gift_BoxRecordList);
			model.addAttribute("activeUrl", "giftboxlist");
			model.addAttribute("gift_id", gift_id);
			model.addAttribute("uid", uid);
		} catch (Exception e) {
			log.error("礼物箱列表", e);
		}
		return "giftbox/list";
	}

	public String getOperateLog(String begin_time, String end_time, int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			
			List<Gift_Box_Record> operatelist = giftboxdao.getOperateLogList(b_time,e_time,(page - 1) * 20 ,20);
			for(Gift_Box_Record list:operatelist){
				Gift_Info gift_info=giftinfodao.getGiftInfo(list.getGift_id());
				list.setGift_name(gift_info.getGift_name());			
			}
			int totalRecord = giftboxdao.getOperateLogListCount(b_time,e_time);
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("giftbox/operatelog");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "giftboxlist");
			model.addAttribute("operatelist", operatelist);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);

		}catch (Exception e) {
			log.error("礼物箱操作日志", e);
		}
		return "giftbox/operatelog";
	}

	public String addGiftBox(Model model)
	{
		
		List<Gift_Info> giftinfo=giftboxdao.getAllGiftInfo();
		
		model.addAttribute("activeUrl", "giftboxlist");
		model.addAttribute("giftinfo", giftinfo);
		return "giftbox/add";
	}

	public String saveGiftBox(int uid, int gift_id, int gift_count, String w_name, HttpServletRequest request) {
		
		try{	
			Date date = new Date();
			int result = 0;
			int is_exist=0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			
			Gift_Box gift_box=new Gift_Box();
			gift_box.setUid(uid);
			gift_box.setGift_id(gift_id);
			gift_box.setGift_count(gift_count);
			
			gift_box.setW_name(w_name);
			gift_box.setBox_type(0);
			
			Gift_Box box=giftboxdao.getGiftBoxByUidAndGiftid(uid,gift_id);
			if(box !=null){
				is_exist=1;
				gift_box.setId(box.getId());
			}
			
			Gift_Box_Record gift_box_record=new Gift_Box_Record();
			gift_box_record.setUid(uid);
			gift_box_record.setBox_type(0);
			gift_box_record.setGift_id(gift_id);
			gift_box_record.setGift_count(gift_count);
			gift_box_record.setCreate_time(date);
			gift_box_record.setW_name(w_name);
			
			if(is_exist>0)
			{

				result = giftboxdao.updateGiftBox(gift_box);
				giftboxdao.inserGiftBoxRecord(gift_box_record);
				imanage_RecordDao.insertManageRecord(w_name, "更新礼物箱信息", "gift_box", gift_box.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				
				result = giftboxdao.insertGiftBox(gift_box);
				giftboxdao.inserGiftBoxRecord(gift_box_record);
				imanage_RecordDao.insertManageRecord(w_name, "添加礼物箱信息", "gift_box", gift_box.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("礼物箱赠送礼物信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	
}

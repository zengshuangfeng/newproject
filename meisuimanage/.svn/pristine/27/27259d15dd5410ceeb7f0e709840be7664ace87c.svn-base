package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IVIP_Room;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.VIPChange;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;


/**
 * 
 * <p>文件名称：VIPRoomService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年9月26日下午4:40:04 
 */
@Service(value="vipRoomService")
public class VIPRoomService {
	
	private static Logger log = Logger.getLogger(VIPRoomService.class.getClass());
	@Autowired
	private IVIP_Room ivip_Room;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	public String getVIPChangeList(int page,Model model){
		
		List<VIPChange> list = ivip_Room.getVIPChangeList((page-1)*20, 20);
		
		int totalRecord = ivip_Room.getVIPChangeCount();
		
		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
		pageUtil.setTotalRecord(totalRecord);
		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
		pageUtil.setCurrentPage(page);
		pageUtil.setColumns(14);
		pageUtil.setUrlName("viproom/vipchangelist");
		model.addAttribute("showPage", pageUtil);
		model.addAttribute("vipchangelist", list);
		model.addAttribute("activeUrl", "viproom");
			
		return "viproom/vipchangelist";
	}
	
	public String edit(int id,Model model){
		VIPChange vipChange = ivip_Room.getVIPChange(id);
		model.addAttribute("vipChange", vipChange);
		model.addAttribute("activeUrl", "vipChange");
		return "viproom/edit";
	}
	
	public String save(int id,String name,int days,int change_rmb,int is_online,String w_name,HttpServletRequest request){
		try {
			VIPChange vipChange = new VIPChange();
			vipChange.setName(name);
			vipChange.setDays(days);
			vipChange.setChange_rmb(change_rmb);
			vipChange.setIs_online(is_online);
			vipChange.setW_name(String.valueOf(request.getAttribute("w_name")));
			Date date = new Date();
			vipChange.setUpdate_time(date);
			int result = 0;
			if (id>0) {//更新
				vipChange.setId(id);
				result = ivip_Room.updateVIPChange(vipChange);
				imanage_RecordDao.insertManageRecord(w_name, "更新VIP房设置", "t_vip_change", vipChange.getId(), IPUtil.getIp(request), date);
			}else {//插入
				vipChange.setCreate_time(date);
				result = ivip_Room.insertVIPChange(vipChange);
				imanage_RecordDao.insertManageRecord(w_name, "添加VIP房设置", "t_vip_change", vipChange.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} 
		catch(Exception ex)
		{
			log.error("保存VIP房设置", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

}

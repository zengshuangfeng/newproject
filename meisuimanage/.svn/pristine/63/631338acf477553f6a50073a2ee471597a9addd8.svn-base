package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.VehicleDao;
import com.meisui.manage.entity.MengYan;
import com.meisui.manage.entity.Vehicle;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class VehicleService {
	private static Logger log = Logger.getLogger(VehicleService.class.getClass());
	@Autowired
	private VehicleDao vehicledao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	
	public String getVehicleList(int id, String name, int is_online, int level, int page, Model model) {
		try {
			List<Vehicle> vehicleList=vehicledao.getVehicleList(id,name,is_online,level, (page-1)*20, 20);
			int totalRecord=vehicledao.getVehicleCount(id,name,is_online,level);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("vehicle/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("activeUrl", "vehicle");
			model.addAttribute("vehicleList", vehicleList);
			model.addAttribute("id", id);
			model.addAttribute("is_online", is_online);
			model.addAttribute("name", name);
			model.addAttribute("level", level);
		} catch (Exception e) {
			log.error("座驾列表", e);
		}
		return "vehicle/list";
	}


	public String Save(int id, String name, String info, int level, String pic,String static_pic, String special_pic, int price, int time_limit,
			int rare_level, int divide, int sort, String platform, int is_online,int type,String alias_name, HttpServletRequest request,
			Model model) {
		try{	
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date=new Date();
			Vehicle vehicle = new Vehicle();
			vehicle.setName(name);	
			vehicle.setInfo(info);
			vehicle.setLevel(level);
			vehicle.setPic(pic);
			vehicle.setPrice(price);
			vehicle.setTime_limit(time_limit);
			vehicle.setRare_level(rare_level);
			vehicle.setDivide(divide);
			vehicle.setSort(sort);
			if (platform.equals("10,20"))
				platform = "00";
			vehicle.setPlatform(platform);
			vehicle.setIs_online(is_online);
			vehicle.setIs_del(0);
			vehicle.setStatic_pic(static_pic);
			vehicle.setSpecial_pic(special_pic);
			vehicle.setType(type);
			vehicle.setAlias_name(alias_name);
			int result = 0;
			if(id>0)
			{
				vehicle.setId(id);
				vehicle.setUpdate_date(date);
				result = vehicledao.updateVehicle(vehicle);
				imanage_RecordDao.insertManageRecord(w_name, "更新座驾信息", "t_vehicle", vehicle.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				vehicle.setCreate_date(date);
				result = vehicledao.insertVehiclen(vehicle);
				imanage_RecordDao.insertManageRecord(w_name, "添加座驾信息", "t_vehicle", vehicle.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存座驾列表信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}


	public String edit(int id, Model model) {
		try {
			Vehicle vehicle = vehicledao.getVehicleById(id);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("activeUrl", "vehicle");
		} catch (Exception e) {
			log.error("座驾信息编辑页面", e);
		}
		return "vehicle/edit";
	}


	public int delete(int id, HttpServletRequest request) {
		int result=0;
		try{
			
			String w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除座驾信息", "t_vehicle", id, IPUtil.getIp(request), new Date());
			result=vehicledao.deleteVehicle(id);
			return result;
		} catch (Exception e) {
			log.error("座驾删除", e);
			return result;
		}
	}

}

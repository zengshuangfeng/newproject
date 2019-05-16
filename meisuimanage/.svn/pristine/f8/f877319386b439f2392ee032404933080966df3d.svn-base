package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IchangeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Change;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：ChangeService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午3:02:23
 */
@Service
public class ChangeService {
	private static Logger log = Logger.getLogger(ChangeService.class.getClass());
	@Autowired
	private IchangeDao ichangeDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	/**
	 * 
	 * <p>功能描述：钻石套餐列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:05:14</p>
	 *
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getChangeList(int page,  Model model)
	{
		try {
			List<Change> changeList = ichangeDao.getChangeList((page-1)*20, 20);
			int totalRecord = ichangeDao.getChangeCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("change/list");
			model.addAttribute("changeList", changeList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "change");
		} catch (Exception e) {
			log.error("钻石套餐列表", e);
		}
		return "change/list";
	}
	/**
	 * 
	 * <p>功能描述：钻石套餐编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:06:17</p>
	 *
	 * @param id 钻石套餐id
	 * @param model
	 * @return
	 */
	public String editChange(int id, Model model)
	{
		Change change = ichangeDao.getChange(id);
		model.addAttribute("change", change);
		model.addAttribute("activeUrl", "change");
		return "change/edit";
	}
	/**
	 * 
	 * <p>功能描述：保存钻石套餐信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:07:56</p>
	 *
	 * @param id 钻石套餐id
	 * @param virtual_count 虚拟币
	 * @param change_rmb 需人民币
	 * @param is_online 是否上线 1是 0否
	 * @param w_name 编辑人员
	 * @param model
	 * @return
	 */
	public String saveChange(int id, int virtual_count, int change_rmb, int is_online,int is_virtual, String w_name, HttpServletRequest request)
	{
		try {
			Change change = new Change();
			change.setChange_rmb(change_rmb);
			change.setVirtual_count(virtual_count);
			change.setIs_online(is_online);
			w_name = String.valueOf(request.getAttribute("w_name"));
			change.setW_name(w_name);
			Date date = new Date();
			change.setUpdate_time(date);
			change.setIs_virtual(is_virtual);
			int result = 0;
			if(id>0)
			{
				change.setId(id);
				result = ichangeDao.updateChange(change);
				imanage_RecordDao.insertManageRecord(w_name, "更新钻石套餐信息", "change", change.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				change.setCreate_time(date);
				result = ichangeDao.insertChange(change);
				imanage_RecordDao.insertManageRecord(w_name, "添加钻石套餐信息", "change", change.getId(), IPUtil.getIp(request), date);
			}		
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			log.error("保存钻石套餐信息", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：钻石套餐信息删除</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:14:06</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 钻石套餐id
	 * @param request
	 * @return
	 */
	public int delete(String w_name, int id, HttpServletRequest request) {	
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除钻石套餐信息", "change", id, IPUtil.getIp(request), new Date());
			return ichangeDao.deleteChange(id, w_name, date);
		} catch (Exception e) {
			log.error("钻石套餐信息删除", e);
			return 0;
		}
	}
	/**
	 * 
	 * <p>功能描述：更新钻石套餐是否上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午3:16:23</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 钻石套餐id
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	public int updateIsOnline(String w_name, int id, int is_online, HttpServletRequest request) {	
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "更新钻石套餐上线状态", "change", id, IPUtil.getIp(request), new Date());
			return ichangeDao.updateChangeOnline(id, is_online, w_name, date);
		} catch (Exception e) {
			log.error("更新钻石套餐是否上线状态", e);
			return 0;
		}
	}
	public int updateIsVirtual(String w_name, int id, int is_virtual, HttpServletRequest request) {
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "更新钻石套餐上线状态", "change", id, IPUtil.getIp(request), new Date());
			return ichangeDao.updateChangeVirtual(id, is_virtual, w_name, date);
		} catch (Exception e) {
			log.error("更新钻石充值会员价是否上线状态", e);
			return 0;
		}
	}
}

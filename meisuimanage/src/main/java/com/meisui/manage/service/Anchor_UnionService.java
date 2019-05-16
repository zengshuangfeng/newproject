package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.IareaDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Area;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

/**
 * <p>文件名称：Anchor_UnionService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月7日 下午2:59:23
 */
@Service
public class Anchor_UnionService {
	private static Logger log = Logger.getLogger(Anchor_UnionService.class.getClass());
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IareaDao iareaDao;
	/**
	 * 
	 * <p>功能描述：工会列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:02:59</p>
	 *
	 * @param page 页码
	 * @param name 工会名称
	 * @param remark 工会备注
	 * @param model
	 * @return
	 */
	public String getAnchorUnionList(int page, String name, String remark, Model model)
	{
		try {
			List<Anchor_Union> anchor_UnionList = ianchor_UnionDao.getAnchorUnionList(name, remark, (page-1)*20, 20);
			int totalRecord = ianchor_UnionDao.getAnchorUnionCount(name, remark);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorunion/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("anchorUnionList", anchor_UnionList);
			model.addAttribute("activeUrl", "anchorunion");
			model.addAttribute("name", name);
			model.addAttribute("remark", remark);
		} catch (Exception e) {
			log.error("工会列表", e);
		}
		return "anchorunion/list";
	}
	/***
	 * 
	 * <p>功能描述：保存主播工会信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:12:28</p>
	 *
	 * @param id 主播工会id
	 * @param name 工会名称
	 * @param remark 工会备注
	 * @param divide_proportion 分成比例
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	public String saveAnchorUnion(int id, String name, String remark, int divide_proportion, int one_divide_proportion, int type,String username, String password, String commander, String weixin, String qq, String tel, String email, String card_holder, String bank_card_no, int province, int city, String bank_name, String company, String business_license, String w_name, HttpServletRequest request)
	{
		try{	
			Anchor_Union anchor_Union = new Anchor_Union();
			anchor_Union.setName(name);
			anchor_Union.setRemark(remark);
			anchor_Union.setDivide_proportion(divide_proportion);
			anchor_Union.setOne_divide_proportion(one_divide_proportion);
			anchor_Union.setType(type);
			w_name = String.valueOf(request.getAttribute("w_name"));
			anchor_Union.setW_name(w_name);
			anchor_Union.setUsername(username);
			if(!StringUtils.isBlank(password))
				anchor_Union.setPassword(AuthUtil.MD5(password));
			anchor_Union.setCommander(commander);
			anchor_Union.setWeixin(weixin);
			anchor_Union.setQq(qq);
			anchor_Union.setTel(tel);
			anchor_Union.setEmail(email);
			anchor_Union.setCard_holder(card_holder);
			anchor_Union.setBank_card_no(bank_card_no);
			anchor_Union.setProvince(province);
			anchor_Union.setCity(city);
			anchor_Union.setBank_name(bank_name);
			anchor_Union.setCompany(company);
			anchor_Union.setBusiness_license(business_license);
			Date date = new Date();
			anchor_Union.setUpdate_time(date);
			int result = 0;
			if(id>0)
			{
				anchor_Union.setId(id);
				result = ianchor_UnionDao.updateAnchorUnion(anchor_Union);
				imanage_RecordDao.insertManageRecord(w_name, "更新主播工会信息", "t_anchor_union", anchor_Union.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				anchor_Union.setCreate_time(date);
				result = ianchor_UnionDao.insertAnchorUnion(anchor_Union);
				imanage_RecordDao.insertManageRecord(w_name, "添加主播工会信息", "t_anchor_union", anchor_Union.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存主播工会信息", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	/**
	 * 
	 * <p>功能描述：编辑主播工会信息页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月7日 下午3:17:29</p>
	 *
	 * @param id 主播工会id
	 * @param model
	 */
	public void editAnchorUnion(int id, Model model)
	{
		try {
			Anchor_Union anchor_Union = ianchor_UnionDao.getAnchorUnion(id);
			List<Area> areaList = iareaDao.getAreaListWithFid(0);
			List<Area> areaList2 = new ArrayList<Area>();
			if(anchor_Union.getProvince()>0)
				areaList2 = iareaDao.getAreaListWithFid(anchor_Union.getProvince());
			model.addAttribute("anchorUnion", anchor_Union);
			model.addAttribute("areaList", areaList);
			model.addAttribute("areaList2", areaList2);
			model.addAttribute("activeUrl", "anchorunion");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		} catch (Exception e) {
			log.error("编辑主播工会信息页面", e);
		}
	}	
	public void addAnchorUnion(Model model)
	{
		try {
			List<Area> areaList = iareaDao.getAreaListWithFid(0);
			model.addAttribute("areaList", areaList);
			model.addAttribute("activeUrl", "anchorunion");
		} catch (Exception e) {
			log.error("添加主播工会信息页面", e);
		}
	}	
	/**
	 * 
	 * <p>功能描述：删除工会</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年3月14日 上午11:01:17</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 工会id
	 * @param request
	 * @return
	 */
	public int delete(String w_name, int id, HttpServletRequest request) {	
		try{
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "删除工会", "t_anchor_union", id, IPUtil.getIp(request), new Date());
			return ianchor_UnionDao.deleteAnchorUnion(id, date, w_name);
		} catch (Exception e) {
			log.error("工会删除", e);
			return 0;
		}
	}
	public int getUsernameExist(String username, int id) {	
		try{
			Integer union_id = ianchor_UnionDao.getAnchorUnionId(id, username);
			if(union_id==null)
				union_id = 0;
			return union_id;
		} catch (Exception e) {
			log.error("获取公会用户名是否存在", e);
			return 0;
		}
	}
	public String getAreaList(int f_id)
	{
		JSONArray jsonArray = new JSONArray();
		try {
			List<Area> areaList = iareaDao.getAreaListWithFid(f_id);
			jsonArray = JSONArray.fromObject(areaList);
		} catch (Exception e) {
			log.error("我的公会", e);
		}
		return jsonArray.toString();
	}
}

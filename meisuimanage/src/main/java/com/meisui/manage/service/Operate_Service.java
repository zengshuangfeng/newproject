package com.meisui.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IProvince_CenterDao;
import com.meisui.manage.dao.IRechargeQuotaDao;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.Iagent_PromoterDao;
import com.meisui.manage.dao.Ianchor_Guard_ChangeDao;
import com.meisui.manage.dao.IareaDao;
import com.meisui.manage.dao.Iflow_RecordDao;
import com.meisui.manage.dao.Igift_InfoDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IrechargeDao;
import com.meisui.manage.dao.IspendingDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Agent;
import com.meisui.manage.entity.Agent_Promoter;
import com.meisui.manage.entity.Anchor_Guard;
import com.meisui.manage.entity.Anchor_Guard_Change2;
import com.meisui.manage.entity.Area;
import com.meisui.manage.entity.Gift_Info;
import com.meisui.manage.entity.Operate_Center;
import com.meisui.manage.entity.Profit;
import com.meisui.manage.entity.Province_Center;
import com.meisui.manage.entity.PublicEntity;
import com.meisui.manage.entity.Recharge_Quota_Virtual;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;

import net.sf.json.JSONArray;

@Service
public class Operate_Service {

	private static Logger log = Logger.getLogger(Operate_Service.class.getClass());

	@Autowired
	private Iflow_RecordDao flow_RecordDao;
	@Autowired
	private Ioperate_CenterDao operate_CenterDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IrechargeDao rechargeDao;
	@Autowired
	private IspendingDao spendingDao;
	@Autowired
	private IareaDao areaDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IprofitDao profitDao;
	@Autowired
	private IagentDao agentDao;
	@Autowired
	private Igift_InfoDao giftDao;
	@Autowired
	private Iagent_PromoterDao agent_PromoterDao;
	@Autowired
	private Ioperate_CenterDao operate_centerDao;
	@Autowired
	private IRechargeQuotaDao rechargequotadao;
	@Autowired
	private IProvince_CenterDao iprovince_centerDao;
	@Autowired
	private Ianchor_Guard_ChangeDao iancor_guard_changeDao;

	public String list(String begin_time, String end_time,int province_center_id, int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Operate_Center> list = operate_CenterDao.getList(province_center_id,(page - 1) * 20 ,20);
			int totalRecord = operate_CenterDao.getListCount();
			for(Operate_Center center : list) {
				PublicEntity publicEntity = new PublicEntity();
				if("".equals(b_time) && "".equals(e_time)){
					int inviteCount = iuserDao.getInviteUidListByCenterId(center.getId());
					publicEntity.setInvite_Count(inviteCount);
				}else{
					int inviteCount = iuserDao.getInviteUidListByCenterId2(center.getId(),b_time,e_time);
					publicEntity.setInvite_Count(inviteCount);
				}

				long give = spendingDao.getTotalSpendingByCenterId(center.getId(), b_time, e_time);//用户总赠送流水
				long recharge = rechargeDao.getRechargeByCenterId(center.getId(), b_time, e_time);//总充值				

				long live_Total_Glamour = profitDao.getProfitSumByType(center.getId(), 0, b_time, e_time);//直播所得总魅力值
				long spread_Total_Glamour = profitDao.getProfitSumByType(center.getId(), 8, b_time, e_time);//推广所得总魅力值
				
				publicEntity.setInvite_Total_Recharge(recharge);//总充值金额
				publicEntity.setInvite_Total_Give(give);
				publicEntity.setLive_Total_Glamour(live_Total_Glamour);
				publicEntity.setSpread_Total_Glamour(spread_Total_Glamour);
				center.setPublicEntity(publicEntity);
				
				
				long count=rechargequotadao.totalVirtualCount(center.getId());
				long totalvirtualcount=center.getRecharge_quota()-count;
				center.setTotalvirtualcount(totalvirtualcount);
				
				Province_Center province=operate_CenterDao.getProviceCenter(center.getProvince_center_id());
				if(province !=null){//如果该运营中心有上级省代运营
					center.setProvince_center_name(province.getName());
				}else{//若没有
					center.setProvince_center_name("");
				}
			}
			int total_invite = 0;
			if("".equals(b_time) && "".equals(e_time)){
				total_invite = iuserDao.totalInvite();
			}else{
				total_invite=iuserDao.totalInvite2(b_time,e_time);
			}
			List<Province_Center> provinceCenterList = operate_centerDao.getProvinceListAll();
			
			long total_give = spendingDao.totalGive(b_time, e_time);
			long total_recharge = rechargeDao.totalRecharge(b_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("list", list);
			model.addAttribute("total_invite", total_invite);
			model.addAttribute("total_give", total_give);
			model.addAttribute("total_recharge", total_recharge);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("provinceCenterList", provinceCenterList);
			model.addAttribute("province_center_id", province_center_id);

		}catch (Exception e) {
			log.error("运营中心列表错误", e);
		}
		return "operatecenter/centerlist";
	}

	public String add(Model model) {
		List<Area> list = areaDao.getProvinceList();
		List<Province_Center> province_center=iprovince_centerDao.getProvinceCenterList();
		model.addAttribute("areaList", list);
		model.addAttribute("activeUrl", "operate");
		model.addAttribute("province_center", province_center);
		return "operatecenter/add";
	}

	public String getCity(Integer f_id) {
		if (f_id == 0) {
			return "1";
		}
		List<Area> cityList = areaDao.getAreaListWithFid(f_id);
		JSONArray jsonArray = JSONArray.fromObject(cityList);
		return jsonArray.toString();
	}

	public String edit(int centerId, Model model) {
		try {
			Operate_Center center = operate_CenterDao.getOperateCenterById(centerId);
			List<Area> list = areaDao.getProvinceList();
			List<Province_Center> province_center=iprovince_centerDao.getProvinceCenterList();
			model.addAttribute("areaList", list);
			model.addAttribute("center", center);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("province_center", province_center);
		}catch (Exception e) {
			log.error("运营中心编辑页错误", e);
		}
		return "operatecenter/add";
	}

	public String save(int id ,String name, int divide, String username, String password, String nickname, String contact,
			String card_name, String card_no, String province, String city, String card_bank, String company,
			String remark, int province_center_id,int settlement_type, HttpServletRequest request) {
		try {
			String w_name = String.valueOf(request.getAttribute("w_name"));

			Date date = new Date();
			Operate_Center center = new Operate_Center();
			center.setName(name);
			center.setDivide(divide);
			center.setUsername(username);
			center.setContact(contact);
			center.setCard_name(card_name);
			center.setCard_no(card_no);
			center.setProvince(province);
			center.setCity(city);
			center.setCard_bank(card_bank);
			center.setCompany(company);
			center.setRemark(remark);
			center.setSettlement_type(settlement_type);
			center.setW_name(w_name);
			center.setProvince_center_id(province_center_id);
			if(id > 0) {
				center.setId(id);
				if(StringUtils.isNotBlank(password)) {
					String pass = operate_CenterDao.getPasswordByCenterId(id);
					if(!pass.equals(password)) {
						center.setPassword(AuthUtil.MD5(password));
					}
				}
				center.setUpdate_time(date);
				operate_CenterDao.update(center);
			}else {			
				center.setCreate_time(date);
				center.setPassword(AuthUtil.MD5(password));
				operate_CenterDao.save(center);
				imanage_RecordDao.insertManageRecord(w_name, "保存运营中心", "t_operate_center", center.getId(), IPUtil.getIp(request), date);
			}
			return "{\"code\":0,\"msg\":\"保存成功\"}";		

		}catch (Exception e) {
			log.error("运营中心保存失败", e);
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public int getUsernameExist(String username, int id)
	{
		try
		{
			Integer agent_id = operate_CenterDao.getOperateUsernameExist(username, id);
			if(agent_id!=null)
				return 1;
		}
		catch (Exception e) {
			log.error("获取代理后台用户名是否存在", e);
		}
		return 0;
	}

	public int saveRemark(String begin_time, String end_time, String remark) {
		try {
			if(StringUtils.isNotBlank(begin_time) && StringUtils.isNotBlank(end_time)) {
				begin_time = begin_time + " 00:00:00";
				end_time = end_time + " 23:59:59";
			}
			flow_RecordDao.addRemark(begin_time, end_time, remark, new Date());
			return 1;
		}catch (Exception e) {
			log.error("批量备注保存错误", e);
		}
		return 0;
	}

	public void excel(String begin_time, String end_time,int province_center_id, HttpServletRequest request, HttpServletResponse response) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			int total_invite = 0;
			if("".equals(b_time) && "".equals(e_time)){
				total_invite = iuserDao.totalInvite();
			}else{
				total_invite=iuserDao.totalInvite2(b_time,e_time);
			}

			long total_give = spendingDao.totalGive(b_time, e_time);
			long total_recharge = rechargeDao.totalRecharge(b_time, e_time);
			List<Operate_Center> list = operate_CenterDao.getListAllByprovinceid(province_center_id);
			for(Operate_Center center : list) {
				PublicEntity publicEntity = new PublicEntity();
				if("".equals(b_time) && "".equals(e_time)){
					int inviteCount = iuserDao.getInviteUidListByCenterId(center.getId());
					publicEntity.setInvite_Count(inviteCount);
				}else{
					int inviteCount = iuserDao.getInviteUidListByCenterId2(center.getId(),b_time,e_time);
					publicEntity.setInvite_Count(inviteCount);
				}

				long give = 0;
				long live_Total_Glamour = 0;//直播所得总魅力值
				long spread_Total_Glamour = 0;//推广所得总魅力值
				long recharge = 0;
				give = spendingDao.getTotalSpendingByCenterId(center.getId(), b_time, e_time);//用户总赠送流水
				recharge = rechargeDao.getRechargeByCenterId(center.getId(), b_time, e_time);//总充值
				live_Total_Glamour = profitDao.getProfitSumByType(center.getId(), 0, b_time, e_time);
				spread_Total_Glamour = profitDao.getProfitSumByType(center.getId(), 8, b_time, e_time);
				publicEntity.setInvite_Total_Recharge(recharge);//总充值金额
				publicEntity.setInvite_Total_Give(give);
				publicEntity.setLive_Total_Glamour(live_Total_Glamour);
				publicEntity.setSpread_Total_Glamour(spread_Total_Glamour);
				center.setPublicEntity(publicEntity);
				
				Province_Center province=operate_CenterDao.getProviceCenter(center.getProvince_center_id());
				if(province !=null){//如果该运营中心有上级省代运营
					center.setProvince_center_name(province.getName());
				}else{//若没有
					center.setProvince_center_name("");
				}
			}
			ServletOutputStream outputStream = response.getOutputStream();  
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles1 = new String[] {"总邀请人","被邀请人总充值","被邀请人总送礼流水"};
			String[] titles2 = new String[]{"运营中心ID","运营中心昵称","分成比例","邀请人数","邀请用户总充值","邀请用户总送礼流水","直播累计所获魅力值","推广累计所获魅力值","入驻时间","备注","所属省代理运营中心"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles1.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFRow headRow1 = sheet.createRow(2);
			XSSFCell cell = null; 

			for(int i=0;i<titles1.length;i++) {
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles1[i]);  
			}
			XSSFRow _bodyRow = sheet.createRow(1); 
			cell = _bodyRow.createCell(0);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(total_invite);

			cell = _bodyRow.createCell(1);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(total_recharge);

			cell = _bodyRow.createCell(2);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(total_give);

			for (int i = 0; i < titles2.length; i++)  
			{  
				cell = headRow1.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles2[i]);  

			}
			String fileName = new String(("运营中心列表"+begin_time+"~"+end_time).getBytes(),"ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			int j=2;
			for (Operate_Center center : list) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 
				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getId());

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getNickname());

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getDivide()+"%");

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getPublicEntity().getInvite_Count());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getPublicEntity().getInvite_Total_Recharge());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getPublicEntity().getInvite_Total_Give());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getPublicEntity().getLive_Total_Glamour());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getPublicEntity().getSpread_Total_Glamour());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getCreate_time()==null?"":AuthUtil.formatDateToString(center.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getRemark());
				
				cell = bodyRow.createCell(10);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getProvince_center_name());
				j++;
			}
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			} 
		}catch (Exception e) {
			log.error("运营中心excel导出错误",e);
		}
	}

	public String profitList(int type, String begin_time, String end_time, Model model, int page, int centerId) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			List<Profit> list = new ArrayList<Profit>(20);
			list = profitDao.getCenterProfitList(type, b_time, e_time, (page - 1) * 20, 20, centerId);
			int totalRecord = profitDao.getCenterProfitListCount(type, b_time, e_time, centerId);
			Map<Long, User> user_map = new HashMap<Long, User>();
			Map<Integer, String> agentname_map = new HashMap<Integer, String>();
			Map<Integer, Gift_Info> gift_map = new HashMap<Integer, Gift_Info>();
			Map<Integer, Anchor_Guard_Change2> guard_map = new HashMap<Integer, Anchor_Guard_Change2>();
			for(Profit profit : list) {
				if(user_map.containsKey(profit.getUid())) {
					profit.setF_uuid(user_map.get(profit.getUid()).getF_uuid());
					profit.setNickname(user_map.get(profit.getUid()).getNickname());
					profit.setAgent_id(user_map.get(profit.getUid()).getAgent_id());
				}else {
					User user = iuserDao.getUser(profit.getUid());
					profit.setF_uuid(user.getF_uuid());
					profit.setNickname(user.getNickname());
					profit.setAgent_id(user.getAgent_id());
					user_map.put(user.getId(), user);
				}
				if(agentname_map.containsKey(profit.getAgent_id())) {
					profit.setAgentname(agentname_map.get(profit.getAgent_id()));
				}else {
					String agentname = agentDao.getAgentNameById(profit.getAgent_id());
					profit.setAgentname(agentname);
					agentname_map.put(profit.getAgent_id(), agentname);
				}
				
				if(profit.getType() ==9 || profit.getType() ==10){
					if(guard_map.containsKey(profit.getGift_id())) {
						profit.setGift_name(guard_map.get(profit.getGift_id()).getName());
						profit.setVirtual(guard_map.get(profit.getGift_id()).getChange_virtual());
					}else {						
						Anchor_Guard_Change2 anchorguard=iancor_guard_changeDao.getAnchorGuardNewByid(profit.getGift_id());
						profit.setGift_name(anchorguard.getName());
						profit.setVirtual(anchorguard.getChange_virtual());
						guard_map.put(profit.getGift_id(), anchorguard);
					}
					
				}else{
					if(gift_map.containsKey(profit.getGift_id())) {
						profit.setGift_name(gift_map.get(profit.getGift_id()).getGift_name());
						profit.setVirtual(gift_map.get(profit.getGift_id()).getGift_virtual());
					}else {
						Gift_Info gift = giftDao.getGiftInfo(profit.getGift_id());
						profit.setGift_name(gift.getGift_name());
						profit.setVirtual(gift.getGift_virtual());
						gift_map.put(profit.getGift_id(), gift);
					}
				}
				
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/profit");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("list", list);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("type", type);
			model.addAttribute("centerId", centerId);
		}catch (Exception e) {
			log.error("运营中心收益明细列表错误", e);
		}
		return "operatecenter/profitlist";
	}
	
	

	public String agentList(String begin_time,String end_time,int centerId, String agentName, int agentId, int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			List<Agent> list = agentDao.getAgentListByOperateCenterId(centerId, agentName, agentId, (page - 1) * 20, 20);
			int totalRecord = agentDao.getAgentListCountByOperateCenterId(centerId, agentName, agentId);
			for(Agent agent : list) {
				if("".equals(b_time) && "".equals(e_time)){
					int inviteCount = iuserDao.getUidListByAgentId(agent.getId());
					agent.setTotalInvite(inviteCount);
					int totalRecharge = rechargeDao.getRechargeRmbSumWithAgentId(agent.getId());
					agent.setTotalRecharge(totalRecharge);
					int totalAnchor = profitDao.getProfitSumByTypeAndAgentId(agent.getId(), 0);
					agent.setTotalAnchor(totalAnchor);
					int totalPromoter = profitDao.getProfitSumByTypeAndAgentId(agent.getId(), 8);
					agent.setTotalPromoter(totalPromoter);
				}else{
					int inviteCount = iuserDao.getUidListByAgentId2(centerId,agent.getId(),b_time,e_time);
					agent.setTotalInvite(inviteCount);
					int totalRecharge = rechargeDao.getRechargeRmbSumWithAgentId2(agent.getId(),b_time,e_time);
					agent.setTotalRecharge(totalRecharge);
					int totalAnchor = profitDao.getProfitSumByTypeAndAgentId2(agent.getId(), 0,b_time,e_time);
					agent.setTotalAnchor(totalAnchor);
					int totalPromoter = profitDao.getProfitSumByTypeAndAgentId2(agent.getId(), 8,b_time,e_time);
					agent.setTotalPromoter(totalPromoter);
				}
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/agent");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("list", list);
			model.addAttribute("centerId", centerId);
			model.addAttribute("agentName", agentName);
			model.addAttribute("agentId", agentId);
			model.addAttribute("begin_time", begin_time);
			model.addAttribute("end_time", end_time);
		}catch (Exception e) {
			log.error("下属代理列表错误", e);
		}
		return "operatecenter/agentlist";
	}


	public String AllagentList( String agentName, int agentId,int operate_center_id, int page, Model model) {
		try {
			List<Agent> list = agentDao.getAllAgentListByOperateCenterId(agentName, agentId,operate_center_id, (page - 1) * 20, 20);
			int totalRecord = agentDao.getAllAgentListCountByOperateCenterId(agentName, agentId,operate_center_id);
			int operatecenterid=0;
			int settelementtype=0;
			for(Agent agent : list) {
				int inviteCount = iuserDao.getUidListByAgentId(agent.getId());
				int totalRecharge = rechargeDao.getRechargeRmbSumWithAgentId(agent.getId());
				int totalAnchor = profitDao.getProfitSumByTypeAndAgentId(agent.getId(),0);
				int totalPromoter = profitDao.getProfitSumByTypeAndAgentId(agent.getId(),8);
				agent.setTotalInvite(inviteCount);
				agent.setTotalRecharge(totalRecharge);
				agent.setTotalAnchor(totalAnchor);
				agent.setTotalPromoter(totalPromoter);

				operatecenterid=agent.getOperate_center_id();
				Operate_Center operatecenter = operate_CenterDao.getOperateCenterById(operatecenterid);
				settelementtype=operatecenter.getSettlement_type();
				agent.setSettlement_type(settelementtype);
			}
			List<Operate_Center> operateCenterList = operate_centerDao.getListAll();
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/allagent");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "allagent");
			model.addAttribute("list", list);
			model.addAttribute("agentName", agentName);
			model.addAttribute("agentId", agentId);
			model.addAttribute("operate_center_id", operate_center_id);
			model.addAttribute("operate_CenterList", operateCenterList);
		}catch (Exception e) {
			log.error("代理列表错误", e);
		}
		return "operatecenter/allagentlist";
	}


	public String centerInvites(int centerId, long uid, int page, Model model) {
		try {
			List<User> list = iuserDao.getInviteUsersByCenterId(centerId, uid, (page - 1 ) * 20, 20);
			int totalRecord = iuserDao.getInviteUsersCountByCenterId(centerId, uid);
			for(User user : list) {
				user.setSum_recharge(rechargeDao.getRechargeRmbSumWithUid2(user.getId()));
				user.setSum_give(spendingDao.getSpendingSumAllWithUid(user.getId()));
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("operate/centerinvites");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "operate");
			model.addAttribute("list", list);
			model.addAttribute("centerId", centerId);
			model.addAttribute("uid", uid);
		}catch (Exception e) {
			log.error("查看运营中心所以邀请人列表错误", e);
		}
		return "operatecenter/centerinvite";
	}

	public void updateIsForbid(int id, int is_forbid, HttpServletRequest request) {
		try {
			String w_name = String.valueOf(request.getAttribute("w_name"));
			operate_CenterDao.updateIsForbid(id, is_forbid);
			imanage_RecordDao.insertManageRecord(w_name, "禁用或解禁运营中心", "t_operate_center", id, IPUtil.getIp(request), new Date());
		}catch (Exception e) {
			log.error("禁用或解禁错误", e);
		}

	}

	public void promoterExcel(String begin_time, String end_time, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}		
			List<Agent_Promoter> agent_PromoterList = agent_PromoterDao.getAllAgentPromoter();
			for (Agent_Promoter agent_Promoter : agent_PromoterList) {
				User promoter_user = iuserDao.getUser(agent_Promoter.getUid());
				agent_Promoter.setNickname(promoter_user.getNickname());
				agent_Promoter.setInvite_code(promoter_user.getInvite_code());
				Integer invite_recharge_sum = 0;

				Long invite_spending_sum=0L;
				if("".equals(b_time) && "".equals(e_time)){
					agent_Promoter.setInvite_count(iuserDao.getAllInviteUserCountWithPromoter( agent_Promoter.getId(), agent_Promoter.getUid()));
					invite_recharge_sum = rechargeDao.getAllRechargeSumWithPromoterid(agent_Promoter.getId(), agent_Promoter.getUid());
					invite_spending_sum = spendingDao.getAllSpendingSumWithPromoterid( agent_Promoter.getId(), agent_Promoter.getUid());

				}else{
					agent_Promoter.setInvite_count(iuserDao.getAllInviteUserCountWithPromoter2( agent_Promoter.getOperate_center_id(), agent_Promoter.getAgent_id(),agent_Promoter.getUid(),b_time,e_time));							
					invite_spending_sum = spendingDao.getAllSpendingSumWithPromoterid2( agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);												
					invite_recharge_sum=rechargeDao.getAllRechargeSumWithPromoterid2(agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);															
				}	
				agent_Promoter.setInvite_recharge_sum(invite_recharge_sum);				
				agent_Promoter.setInvite_spending_sum(invite_spending_sum);				
				agent_Promoter.setF_uuid(promoter_user.getF_uuid());
				Operate_Center operate_center=operate_centerDao.getOperateCenterById(agent_Promoter.getOperate_center_id());
				agent_Promoter.setOperate_center_name(operate_center.getName());
				Agent agent=agentDao.getAgent(agent_Promoter.getAgent_id());
				agent_Promoter.setAgent_name(agent.getName());
			}

			int total_invite =0;
			if("".equals(b_time) && "".equals(e_time)){
				total_invite = iuserDao.totalInvite();
			}else{
				total_invite=iuserDao.totalInvite2(b_time,e_time);
			}			
			long total_give = spendingDao.totalSpending_virtual(b_time, e_time);
			long total_recharge = rechargeDao.totalRecharge(b_time, e_time);
			long spread_Total_Glamour = profitDao.getProfitSumByType2( 8, b_time, e_time);//推广所得总魅力值
			ServletOutputStream outputStream = response.getOutputStream();  
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  
			String[] titles1 = new String[] {"被邀请人总人数","被邀请人总充值","被邀请人累计消费钻石","总推广累计所得魅力值"};
			String[] titles2 = new String[]{"推广员房间号","推广员昵称","推广员邀请码","所属运营中心","所属代理","邀请人数","邀请用户总充值","邀请人用户总送礼流水","入驻时间","备注"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 
			for(int i=0;i<titles1.length;i++)
			{ 
				sheet.setColumnWidth(i, 20*256);  
			}
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow = sheet.createRow(0);  
			XSSFRow headRow1 = sheet.createRow(2);
			XSSFCell cell = null; 

			for(int i=0;i<titles1.length;i++) {
				cell = headRow.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles1[i]);  
			}
			XSSFRow _bodyRow = sheet.createRow(1); 
			cell = _bodyRow.createCell(0);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(total_invite);

			cell = _bodyRow.createCell(1);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(total_recharge);

			cell = _bodyRow.createCell(2);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(total_give);

			cell = _bodyRow.createCell(3);  
			cell.setCellStyle(bodyStyle);  
			cell.setCellValue(spread_Total_Glamour);


			for (int i = 0; i < titles2.length; i++)  
			{  
				cell = headRow1.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles2[i]);  

			}
			String fileName = new String(("推广员列表"+begin_time+"~"+end_time).getBytes(),"ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			int j=2;
			for (Agent_Promoter center : agent_PromoterList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 
				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getF_uuid());

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getNickname());

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_code());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getOperate_center_name());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getAgent_name());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_count());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_recharge_sum());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_spending_sum());

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getCreate_time()==null?"":AuthUtil.formatDateToString(center.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(9);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getRemark());
				j++;
			}
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			} 
		}catch (Exception e) {
			log.error("推广员列表excel导出错误",e);
		}
	}

	public void AgentExcel(int centerId, String agentName,int agentId, String begin_time, String end_time,  HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			List<Agent> list = agentDao.getAgentList(centerId, agentName, agentId);

			for(Agent agent : list) {
				if("".equals(b_time) && "".equals(e_time)){
					int inviteCount = iuserDao.getUidListByAgentId(agent.getId());
					agent.setTotalInvite(inviteCount);
					int totalRecharge = rechargeDao.getRechargeRmbSumWithAgentId(agent.getId());
					agent.setTotalRecharge(totalRecharge);
					int totalAnchor = profitDao.getProfitSumByTypeAndAgentId(agent.getId(), 0);
					agent.setTotalAnchor(totalAnchor);
					int totalPromoter = profitDao.getProfitSumByTypeAndAgentId(agent.getId(), 8);
					agent.setTotalPromoter(totalPromoter);
				}else{
					int inviteCount = iuserDao.getUidListByAgentId2(centerId,agent.getId(),b_time,e_time);
					agent.setTotalInvite(inviteCount);
					int totalRecharge = rechargeDao.getRechargeRmbSumWithAgentId2(agent.getId(),b_time,e_time);
					agent.setTotalRecharge(totalRecharge);
					int totalAnchor = profitDao.getProfitSumByTypeAndAgentId2(agent.getId(), 0,b_time,e_time);
					agent.setTotalAnchor(totalAnchor);
					int totalPromoter = profitDao.getProfitSumByTypeAndAgentId2(agent.getId(), 8,b_time,e_time);
					agent.setTotalPromoter(totalPromoter);
				}

			}

			ServletOutputStream outputStream = response.getOutputStream();  
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  

			String[] titles2 = new String[]{"代理ID","代理名称","代理分成","邀请人数","邀请用户总充值","直播累计所得魅力值","推广累计所获魅力值","入驻时间","备注"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 

			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  
			XSSFRow headRow1 = sheet.createRow(2);
			XSSFCell cell = null; 

			for (int i = 0; i < titles2.length; i++)  
			{  
				cell = headRow1.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles2[i]);  

			}
			String fileName = new String(("下属代理列表"+begin_time+"~"+end_time).getBytes(),"ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			int j=2;
			for (Agent center : list) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 
				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getId());

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getName());

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getDivide()+"%");

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getTotalInvite());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getTotalRecharge());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getTotalAnchor());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getTotalPromoter());

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getCreate_time()==null?"":AuthUtil.formatDateToString(center.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(8);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getRemark());
				j++;
			}
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			} 
		}catch (Exception e) {
			log.error("代理下属列表excel导出错误",e);
		}

	}

	public void promoterListExcel( int operate_center_id, int agentId, long uid, String remark, String begin_time, String end_time, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String b_time = "";
			String e_time = "";

			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			List<Agent_Promoter> agent_PromoterList = agent_PromoterDao.getAgentList(operate_center_id, agentId, uid, remark);
			for (Agent_Promoter agent_Promoter : agent_PromoterList) {
				User promoter_user = iuserDao.getUser(agent_Promoter.getUid());
				agent_Promoter.setNickname(promoter_user.getNickname());
				agent_Promoter.setInvite_code(promoter_user.getInvite_code());

				if("".equals(b_time) && "".equals(e_time)){
					agent_Promoter.setInvite_count(iuserDao.getInviteUserCountWithPromoter(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid()));
					Integer invite_recharge_sum = rechargeDao.getRechargeSumWithPromoterid(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid());
					agent_Promoter.setInvite_recharge_sum(invite_recharge_sum);
					Long invite_spending_sum = spendingDao.getSpendingSumWithPromoterid(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid());
					agent_Promoter.setInvite_spending_sum(invite_spending_sum);
				}else{
					agent_Promoter.setInvite_count(iuserDao.getInviteUserCountWithPromoter2(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time));										
					Integer invite_recharge_sum = rechargeDao.getRechargeSumWithPromoterid2(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);								
					Long invite_spending_sum = spendingDao.getSpendingSumWithPromoterid2(operate_center_id, agentId, agent_Promoter.getId(), agent_Promoter.getUid(),b_time,e_time);
					agent_Promoter.setInvite_recharge_sum(invite_recharge_sum);	
					agent_Promoter.setInvite_spending_sum(invite_spending_sum);
				}

			}

			ServletOutputStream outputStream = response.getOutputStream();  
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  

			String[] titles2 = new String[]{"推广员UID","推广员昵称","推广员邀请码","邀请人数","邀请用户总充值","邀请人用户送礼总流水","入驻时间","备注"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 

			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  

			XSSFRow headRow1 = sheet.createRow(2);
			XSSFCell cell = null; 

			for (int i = 0; i < titles2.length; i++)  
			{  
				cell = headRow1.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles2[i]);  
			}
			String fileName = new String(("下属推广员列表"+begin_time+"~"+end_time).getBytes(),"ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			int j=2;
			for (Agent_Promoter center : agent_PromoterList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 
				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getUid());

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getNickname());

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_code());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_count());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_recharge_sum());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getInvite_spending_sum());

				cell = bodyRow.createCell(6);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getCreate_time()==null?"":AuthUtil.formatDateToString(center.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));

				cell = bodyRow.createCell(7);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getRemark());
				j++;
			}
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			} 
		}catch (Exception e) {
			log.error("下属推广员列表excel导出错误",e);
		}

	}

	public void InviteListExcel( String begin_time, String end_time,int centerId, int agent_id,int agent_promoter_id, long uid,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String b_time = "";
			String e_time = "";
			if(StringUtils.isNotBlank(begin_time)) {
				b_time = begin_time + " 00:00:00";
			}
			if(StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}

			long agent_promoter_uid = 0;
			if(agent_promoter_id>0)//本身推广员也属于被自己邀请进来
			{
				agent_promoter_uid = agent_PromoterDao.getUId(agent_promoter_id);
			}
			List<User> invite_UserList = iuserDao.getAllInviteUserList(centerId, agent_id, agent_promoter_id, uid, agent_promoter_uid,b_time,e_time);
			for (User user : invite_UserList) {
				Date last_login_time = iuserDao.getUserLastLoginTime(user.getId());
				if (last_login_time != null)
					user.setLast_login_time(last_login_time);

				Integer recharge_sum = rechargeDao.getRechargeRmbSumWithUid(user.getId(),b_time,e_time);
				user.setSum_recharge(recharge_sum);
				Long spending_sum = spendingDao.getSpendingSum2(user.getId(),b_time,e_time);
				user.setSum_give(spending_sum);
			}

			ServletOutputStream outputStream = response.getOutputStream();  
			// 创建一个workbook 对应一个excel应用文件  
			XSSFWorkbook workBook = new XSSFWorkbook();  
			// 在workbook中添加一个sheet,对应Excel文件中的sheet  

			String[] titles2 = new String[]{"被邀请人UID","被邀请人昵称","总充值","总送礼","注册时间","最后登录时间"};
			XSSFSheet sheet = workBook.createSheet("sheet1"); 

			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);  
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
			// 构建表头  

			XSSFRow headRow1 = sheet.createRow(2);
			XSSFCell cell = null; 

			for (int i = 0; i < titles2.length; i++)  
			{  
				cell = headRow1.createCell(i);  
				cell.setCellStyle(headStyle);  
				cell.setCellValue(titles2[i]);  
			}
			String fileName = new String(("被邀请用户列表"+begin_time+"~"+end_time).getBytes(),"ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			int j=2;
			for (User center : invite_UserList) {
				XSSFRow bodyRow = sheet.createRow(j + 1); 
				cell = bodyRow.createCell(0);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getId());

				cell = bodyRow.createCell(1);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getNickname());

				cell = bodyRow.createCell(2);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getSum_recharge());

				cell = bodyRow.createCell(3);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getSum_give());

				cell = bodyRow.createCell(4);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getRegister_time());

				cell = bodyRow.createCell(5);  
				cell.setCellStyle(bodyStyle);  
				cell.setCellValue(center.getLast_login_time());
				j++;
			}
			try  
			{  
				workBook.write(outputStream);  
				outputStream.flush();  
				outputStream.close();  
			}  
			catch (IOException e)  
			{  
				e.printStackTrace();  
			}  
			finally  
			{  
				try  
				{  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
			} 
		}catch (Exception e) {
			log.error("被邀请用户列表excel导出错误",e);
		}
	}

}

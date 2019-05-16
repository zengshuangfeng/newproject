package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.AuthenticationDao;
import com.meisui.manage.dao.IaccountReceivableDao;
import com.meisui.manage.dao.IadminDao;
import com.meisui.manage.dao.IagentDao;
import com.meisui.manage.dao.IauditRecordDao;
import com.meisui.manage.dao.ImessageDao;
import com.meisui.manage.entity.AccountReceivable;
import com.meisui.manage.entity.AuditRecords;
import com.meisui.manage.entity.Authentication;
import com.meisui.manage.entity.Message;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

@Service
public class AuthenticationService {
	private static Logger log = Logger.getLogger(AuthenticationService.class.getClass());
	@Autowired
	private AuthenticationDao authenticationDao;
	
	@Autowired
	private IagentDao iagentDao;
	
	@Autowired
	private IauditRecordDao auditRecordsdao;
	@Autowired
	private IaccountReceivableDao accountReceivableDao;
	@Autowired
	private ImessageDao imessageDao;
	@Autowired
	private IadminDao iadminDao;

	public String getAuthenticationList(int page,Model model) {
		
		try {
			
			List<Authentication> getAllauthenticationList=authenticationDao.getAuthenticationList((page-1)*20, 20);
			for(Authentication auth:getAllauthenticationList){
				AuditRecords aut=auditRecordsdao.getAuditRecod(auth.getId());
				if(auth!=null && aut !=null){
					if(auth.getIs_status()==1 ||auth.getIs_status()==2 ||auth.getIs_status()==3 || auth.getIs_status()==4){//初审通过
						auth.setChecktime(aut.getCheck_time());
					}	
				}
				
				if(auth !=null)
				if(auth.getProvince_id()>0 && auth.getOperate_center_id()>0 && auth.getAgent_id()>0){//代理
					auth.setStauts(1);
				}else if(auth.getProvince_id()>0 && auth.getOperate_center_id()>0 && auth.getAgent_id()==0){//运营
					auth.setStauts(2);
				}else if(auth.getProvince_id()>0 && auth.getOperate_center_id()==0 && auth.getAgent_id()==0){//省代
					auth.setStauts(3);
				}
			}	
			int totalRecord = authenticationDao.getAuthenticationCount();
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("authentication/list");
			model.addAttribute("getAllauthenticationList", getAllauthenticationList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "authentication");
		} catch (Exception e) {
			log.error("身份认证审核记录列表", e);
		}
		
		return "authentication/list";
	}
	
	
	/**
	 * 实名验证初审审核通过
	 * */
	public String checkPass(int id ,int is_status, String w_name, HttpServletRequest request) {
		try {
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			result=authenticationDao.checkPass(id,is_status);
			AuditRecords auditRecord = new AuditRecords();
			Authentication authentication =authenticationDao.getAuthentication(id);
			auditRecord.setAuthentication_id(id);
			auditRecord.setIs_status(0);
			auditRecord.setCheck_name(w_name);
			auditRecord.setCheck_time(new Date());
			auditRecord.setError_type("实名初审");
			auditRecord.setProvince_id(authentication.getProvince_id());
			if(StringUtils.isNotBlank(String.valueOf(authentication.getOperate_center_id()))) {
				auditRecord.setOperate_center_id(authentication.getOperate_center_id());
			}
			if(StringUtils.isNotBlank(String.valueOf(authentication.getAgent_id()))) {
				auditRecord.setAgent_id(authentication.getAgent_id());
			}
			int r=auditRecordsdao.saveAuditRecord(auditRecord);//添加审核历史记录
			if(r>0){
				Date date=new Date();
				Message message=new Message();//消息表，当提交认证信息时，会往超管后台提交一条信息
				 int from_manage_id=iadminDao.getAdminByNickname(w_name);
				String title="收到一份实名审核结果";
				String content="您好，您收到一份来自超管后台("+w_name+")"+"的审核结果。";
				message.setFrom_manage_id(from_manage_id);//超管发消息
				if(authentication.getAgent_id() >0){
					message.setTo_agent_id(authentication.getAgent_id());
				}else if(authentication.getOperate_center_id()>0 && authentication.getAgent_id()==0){
					message.setTo_operate_center_id(authentication.getOperate_center_id());
				}else if(authentication.getProvince_id()>0 && authentication.getOperate_center_id()==0 && authentication.getAgent_id()==0){
					message.setTo_pronvice_id(authentication.getProvince_id());
				}			
				message.setTitle(title);
				message.setContent(content);
				message.setIs_read(0);
				message.setCreate_time(date);
				message.setW_name(w_name);
				imessageDao.InsertMessage(message);
			}	
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
	/**
	 * 实名验证初审审核未通过
	 * @return 
	 * */
	public String checkunPass(Integer id ,Integer is_status,String remark, String w_name, HttpServletRequest request) {
		try {
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			result=authenticationDao.checkPass(id,is_status);
			AuditRecords auditRecord = new AuditRecords();
			Authentication authentication =authenticationDao.getAuthentication(id);
			auditRecord.setAuthentication_id(id);
			auditRecord.setIs_status(1);
			auditRecord.setCheck_name(w_name);
			auditRecord.setRemark(remark);
			auditRecord.setCheck_time(new Date());
			auditRecord.setError_type("实名初审");
			auditRecord.setProvince_id(authentication.getProvince_id());
			if(StringUtils.isNotBlank(String.valueOf(authentication.getOperate_center_id()))) {
				auditRecord.setOperate_center_id(authentication.getOperate_center_id());
			}
			if(StringUtils.isNotBlank(String.valueOf(authentication.getAgent_id()))) {
				auditRecord.setAgent_id(authentication.getAgent_id());
			}
			int r=auditRecordsdao.saveAuditRecord(auditRecord);//添加审核历史记录
			if(r>0){
				Date date=new Date();
				Message message=new Message();//消息表，当提交认证信息时，会往超管后台提交一条信息
				 int from_manage_id=iadminDao.getAdminByNickname(w_name);
				String title="收到一份实名审核结果";
				String content="您好，您收到一份来自超管后台("+w_name+")"+"的审核结果。";
				message.setFrom_manage_id(from_manage_id);//超管发消息
				if(authentication.getAgent_id() >0){
					message.setTo_agent_id(authentication.getAgent_id());
				}else if(authentication.getOperate_center_id()>0 && authentication.getAgent_id()==0){
					message.setTo_operate_center_id(authentication.getOperate_center_id());
				}else if(authentication.getProvince_id()>0 && authentication.getOperate_center_id()==0 && authentication.getAgent_id()==0){
					message.setTo_pronvice_id(authentication.getProvince_id());
				}			
				message.setTitle(title);
				message.setContent(content);
				message.setIs_read(0);
				message.setCreate_time(date);
				message.setW_name(w_name);
				imessageDao.InsertMessage(message);
			}	
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	public String getAuthentication(int id,Model model) {
		Authentication authentication=authenticationDao.getAuthentication(id);
		if(authentication!=null) {
			if(authentication.getBusiness_license_deadtime()!=null){
				String s_time=authentication.getBusiness_license_deadtime().substring(0, 10);
				String e_time=authentication.getBusiness_license_deadtime().substring(12, 22);
				model.addAttribute("s_time", s_time);
				model.addAttribute("e_time", e_time);
			}
			
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("authentication", authentication);
			model.addAttribute("activeUrl", "authentication");
		}
		return "authentication/edit";
	}
	
	/**
	 * 实名验证复审审核通过
	 * */
	public String checkaccountPass(int authentication_id ,int is_status,String payer_name,String pay_account,String pay_number,
			String pay_type ,String remark,String w_name, HttpServletRequest request) {
		try {
			int result = 0;
			w_name = String.valueOf(request.getAttribute("w_name"));
			result=authenticationDao.checkPass(authentication_id,is_status);
			AuditRecords auditRecord = new AuditRecords();
			if(is_status==3) {
				auditRecord.setIs_status(0);
			}else if(is_status==4) {
				auditRecord.setIs_status(1);
			}
			AccountReceivable account= new AccountReceivable();
			account.setAuthentication_id(authentication_id);
			account.setPayer_name(payer_name);
			account.setPay_account(pay_account);
			account.setPay_number(pay_number);
			account.setPay_type(pay_type);
			account.setRemark(remark);
			accountReceivableDao.updateAccountReceivable(account);
			Authentication authentication =authenticationDao.getAuthentication(authentication_id);
			auditRecord.setAuthentication_id(authentication_id);
			auditRecord.setRemark(remark);
			auditRecord.setCheck_name(w_name);
			auditRecord.setCheck_time(new Date());
			auditRecord.setError_type("实名复审");
			if(StringUtils.isNotBlank(String.valueOf(authentication.getProvince_id()))) {
				auditRecord.setProvince_id(authentication.getProvince_id());
			}
			if(StringUtils.isNotBlank(String.valueOf(authentication.getOperate_center_id()))) {
				auditRecord.setOperate_center_id(authentication.getOperate_center_id());
			}
			if(StringUtils.isNotBlank(String.valueOf(authentication.getAgent_id()))) {
				auditRecord.setAgent_id(authentication.getAgent_id());
			}
			int r=auditRecordsdao.saveAuditRecord(auditRecord);//添加审核历史记录
			if(r>0){
				Date date=new Date();
				Message message=new Message();//消息表，当提交认证信息时，会往超管后台提交一条信息
				 int from_manage_id=iadminDao.getAdminByNickname(w_name);
				String title="收到一份实名审核结果";
				String content="您好，您收到一份来自超管后台("+w_name+")"+"的审核结果。";
				message.setFrom_manage_id(from_manage_id);//超管发消息
				if(authentication.getAgent_id() >0){
					message.setTo_agent_id(authentication.getAgent_id());
				}else if(authentication.getOperate_center_id()>0 && authentication.getAgent_id()==0){
					message.setTo_operate_center_id(authentication.getOperate_center_id());
				}else if(authentication.getProvince_id()>0 && authentication.getOperate_center_id()==0 && authentication.getAgent_id()==0){
					message.setTo_pronvice_id(authentication.getProvince_id());
				}			
				message.setTitle(title);
				message.setContent(content);
				message.setIs_read(0);
				message.setCreate_time(date);
				message.setW_name(w_name);
				imessageDao.InsertMessage(message);
			}	
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}
	
}

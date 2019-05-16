package com.meisui.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IauditRecordDao;
import com.meisui.manage.entity.AuditRecords;
import com.meisui.manage.entity.Authentication;
import com.meisui.manage.utils.PageUtil;

@Service
public class AuditRecordService {
	@Autowired
	private IauditRecordDao auditRecordDao;
	
	
	
	/**
	 * 添加审核记录历史列表
	 * */

	public void saveAuditRecord(AuditRecords auditRecord) {
		auditRecordDao.saveAuditRecord(auditRecord);
	}
	
	
	/**
	 * 获取审核记录历史列表
	 * */
	public String getAuditRecordList(int authentication_id,int page , Model model) {
		try {
			List<AuditRecords> getAllauditRecordList=auditRecordDao.getAuditRecordList(authentication_id,(page-1)*20, 20);
			int totalRecord = auditRecordDao.getAuditRecordsCount(authentication_id);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setUrlName("auditrecords/list");
			model.addAttribute("getAllauditRecordList", getAllauditRecordList);
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "auditrecords");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "authentication/recordlist";
	}
}

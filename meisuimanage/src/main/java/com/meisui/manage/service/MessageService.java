package com.meisui.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IadminDao;
import com.meisui.manage.dao.ImessageDao;
import com.meisui.manage.dao.Ioperate_CenterDao;
import com.meisui.manage.entity.Message;
import com.meisui.manage.utils.PageUtil;

@Service
public class MessageService extends CommonService{
	private static Logger log = Logger.getLogger(MessageService.class.getClass());
	@Autowired
	private Ioperate_CenterDao ioperate_CenterDao;
	@Autowired
	private ImessageDao imessageDao;
	@Autowired
	private IadminDao iadminDao;

	public String getMessageList(int page, Model model, HttpServletRequest request) {
		try
		{
			String nickname = String.valueOf(request.getAttribute("w_name"));
			int id=iadminDao.getAdminByNickname(nickname);
			List<Message> messageList = imessageDao.getMessageList(id,(page-1)*20, 20);			
			int totalRecord = imessageDao.getMassageCount(id);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("message/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("messageList", messageList);	
			model.addAttribute("activeUrl", "message");
		
		}
		catch (Exception e) {
			log.error("消息列表", e);
		}
		return "message/list";
	}

	public String getGoAuth(int id,int page, Model model, HttpServletRequest request) {
		try
		{
			imessageDao.updateMessageByisread(id,1);//标记为已读
		
		}
		catch (Exception e) {
			log.error("消息转发", e);
		}
		return "redirect:/authentication/list";
	}

	public String updateRead(String hid,int read, Model model, HttpServletRequest request) {
		try
		{
			String[] arry= hid.split(",");
			for(String arr:arry){
				if(read==0){
					imessageDao.updateMessageByisread(Integer.parseInt(arr),0);//标记为未读
				}else if(read==1){
					imessageDao.updateMessageByisread(Integer.parseInt(arr),1);//标记为已读
				}
				
			}
		
		}
		catch (Exception e) {
			log.error("批量标记已读", e);
		}
		return "redirect:/message/list";
	}
	
	
	
	
}

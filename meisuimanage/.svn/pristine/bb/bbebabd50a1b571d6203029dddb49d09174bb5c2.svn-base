package com.meisui.manage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.IattentionDao;
import com.meisui.manage.entity.Attention;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：AttentionService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月29日 上午10:06:36
 */
@Service
public class AttentionService {
	private static Logger log = Logger.getLogger(AttentionService.class.getClass());
	@Autowired
	private IattentionDao iattentionDao;
	/**
	 * 
	 * <p>功能描述：关注列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月29日 上午10:10:07</p>
	 *
	 * @param attention_uid 被关注的uid
	 * @param f_uuid 主播房间号id
	 * @param page 页码
	 * @param model
	 * @return
	 */
	public String getAttentionList(long attention_uid, long f_uuid, int page, Model model)
	{
		try {
			List<Attention> attentionList = iattentionDao.getAttentionList(attention_uid, (page-1)*20, 20);
			int totalRecord = iattentionDao.getAttentionCount(attention_uid);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useranchor/attention/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("attentionList", attentionList);
			model.addAttribute("activeUrl", "useranchor");
			model.addAttribute("attention_uid", attention_uid);
			model.addAttribute("f_uuid", f_uuid);
		} catch (Exception e) {
			log.error("关注列表", e);
		}
		return "attention/list";
	}
}

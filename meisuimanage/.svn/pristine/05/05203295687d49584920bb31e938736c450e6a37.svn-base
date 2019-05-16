package com.meisui.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meisui.manage.service.Manage_RecordService;


/**
 * <p>文件名称：Product_Category_Controller.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年4月5日 下午2:00:10
 */
@Controller
@RequestMapping("/managerecord")
public class Manage_RecordController {
	@Autowired
	private Manage_RecordService manage_RecordService;
	/**
	 * 
	 * <p>功能描述：后台操作记录管理列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年6月8日 上午11:20:43</p>
	 *
	 * @param page 页码
	 * @param s 操作人员
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="page",required=false,defaultValue="1")int page,
			@RequestParam(value="s",required=false,defaultValue="")String s,
			@RequestParam(value = "s_time", required = false, defaultValue = "") @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false, defaultValue = "") @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			Model model){
		return manage_RecordService.getManageRecordList(page, s,s_time,e_time,model);
		
	}
}

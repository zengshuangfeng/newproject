package com.meisui.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.service.RechargeService;
import com.meisui.manage.service.StatisticalService;

/**
 * <p>文件名称：RechargeController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月26日 下午4:48:18
 */
@RequestMapping("/")
@Controller
public class RechargeController {
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private StatisticalService statisticalService;
	/**
	 * 
	 * <p>功能描述：充值记录列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月26日 下午4:47:42</p>
	 *
	 * @param uid 用户id
	 * @param pay_type 付款方式 0:支付宝 1:微信
	 * @param nickname 昵称
	 * @param o_id 订单号
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "recharge/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "pay_type", required = false,defaultValue="-1") int pay_type, 
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "o_id", required = false,defaultValue="") String o_id, 
			@RequestParam(value = "platform_channel", required = false,defaultValue="") String platform_channel,
			@RequestParam(value = "is_pay", required = false,defaultValue="1") int is_pay,  
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value = "pay_virtual", required = false,defaultValue="-1") int pay_virtual, 
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			@CookieValue(value="admin",required=false,defaultValue="")String cookie,
			Model model) {	
		return rechargeService.getRechargeList(uid, f_uuid, pay_type, nickname, o_id, platform_channel, is_pay, start_time, end_time, operate_center_id, agent_id,pay_virtual,cookie, page, model);
	}
	
	/**
	 * 
	 * <p>功能描述：充值统计列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年9月8日 上午9:47:42</p>
	 *
	 * @param start_time
	 * @param end_time
	 * @param statistics_type
	 * @param channel	 
	 * @param recharge_type
	 * @param model	 
	 * @return
	 */	
	@RequestMapping(value = "rechargestatistics/list", method = RequestMethod.GET)
	public String getStatisticsList(@RequestParam(value = "start_time",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value = "statistics_type",required = false,defaultValue = "-1") int statistics_type,
			@RequestParam(value = "channel_platform",required = false,defaultValue = "") String channel_platform,
			@RequestParam(value = "recharge_type",required = false,defaultValue = "-1") int recharge_type,
			@RequestParam(value = "page", required = false,defaultValue="1") int page,
			Model model){	
		return rechargeService.getStatisticsList(start_time,end_time,statistics_type,channel_platform,recharge_type,page,model);	
	}
	
	/**
     * 
     * <p>功能描述：赠送记录列表</p>
     * <p>创建人：</p>
     * <p>创建日期：2016年12月26日 下午4:47:42</p>
     *
     * @param uid 用户id
     * @param pay_type 付款方式 0:支付宝 1:微信
     * @param nickname 昵称
     * @param o_id 订单号
     * @param page 页码
     * @param model
     * @return
     */
    @RequestMapping(value = "balancevirtual/list", method = RequestMethod.GET)
    public String getList2(@RequestParam(value = "s_time", required = false,defaultValue="") String s_time, 
            @RequestParam(value = "e_time", required = false,defaultValue="") String e_time, 
            @RequestParam(value = "diamondsa", required = false,defaultValue="") String diamondsa, 
            @RequestParam(value = "diamondsb", required = false,defaultValue="-1") String diamondsb, 
            @RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
            @RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
            @RequestParam(value = "nickname", required = false,defaultValue="") String nickname,       
            @RequestParam(value = "page", required = false,defaultValue="1") int page, 
            Model model) {  
        return rechargeService.getBalance_virtualrecordList(uid, f_uuid, s_time,  e_time,  diamondsa,  diamondsb, nickname,  page, model);
    }
    @RequestMapping(value = "recharge/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
			@RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
			@RequestParam(value = "pay_type", required = false,defaultValue="-1") int pay_type, 
			@RequestParam(value = "type", required = false,defaultValue="-1") int type, 
			@RequestParam(value = "nickname", required = false,defaultValue="") String nickname, 
			@RequestParam(value = "o_id", required = false,defaultValue="") String o_id, 
			@RequestParam(value = "platform_channel", required = false,defaultValue="") String platform_channel,
			@RequestParam(value = "is_pay", required = false,defaultValue="1") int is_pay,  
			@RequestParam(value = "operate_centerid", required = false,defaultValue="0") int operate_centerid,  
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "pay_virtual", required = false,defaultValue="-1") int pay_virtual, 
			@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
			HttpServletResponse response) {	
		return rechargeService.exportExcel(uid, f_uuid, pay_type, nickname, o_id, platform_channel, is_pay,operate_centerid,agent_id, pay_virtual,start_time, end_time, type, response);
	}
    @RequestMapping(value = {"recharge/exportexcel2","balancevirtual/exportexcel2"}, method = RequestMethod.GET)
   	public String exportExcel2(@RequestParam(value = "s_time", required = false,defaultValue="") String s_time, 
            @RequestParam(value = "e_time", required = false,defaultValue="") String e_time, 
            @RequestParam(value = "diamondsa", required = false,defaultValue="") String diamondsa, 
            @RequestParam(value = "diamondsb", required = false,defaultValue="-1") String diamondsb, 
            @RequestParam(value = "uid", required = false,defaultValue="0") long uid, 
            @RequestParam(value = "f_uuid", required = false,defaultValue="0") long f_uuid, 
            @RequestParam(value = "nickname", required = false,defaultValue="") String nickname,          
   			HttpServletResponse response) {	
   		return rechargeService.exportExcel2(uid, f_uuid, s_time, e_time, diamondsa, diamondsb, nickname, response);
   	}
    @RequestMapping(value = "rechargestatistics/exportexcel", method = RequestMethod.GET)  
    public String exportExcel3(@RequestParam(value = "start_time",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start_time,
			@RequestParam(value = "end_time",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end_time,
			@RequestParam(value = "statistics_type",required = false,defaultValue = "-1") int statistics_type,
			@RequestParam(value = "channel_platform",required = false,defaultValue = "") String channel_platform,
			@RequestParam(value = "recharge_type",required = false,defaultValue = "-1") int recharge_type,
			HttpServletResponse response){
    	return rechargeService.exportExcel3(start_time,end_time,statistics_type,channel_platform,recharge_type,response);
    }
    @ResponseBody
	@RequestMapping(value = "recharge/agentlist", produces = "text/json;charset=UTF-8")
	public String agentList(int centerId) {
		return statisticalService.agentList(centerId);
	}
    
}

package com.meisui.manage.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.meisui.manage.dao.Igift_BoxDao;
import com.meisui.manage.service.Gift_BoxService;
import com.meisui.manage.service.Gift_InfoService;
import com.meisui.manage.service.StatisticalService;

/**
 * <p>文件名称：GiftController.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2016年12月27日 下午3:21:08
 */
@RequestMapping("/")
@Controller
public class GiftController {
	@Autowired
	private Gift_InfoService gift_InfoService;
	@Autowired
	private Gift_BoxService gift_boxService;
	@Autowired
	private StatisticalService statisticalService;
	/**
	 * 
	 * <p>功能描述：礼物列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:05:58</p>
	 *
	 * @param gift_id 礼物id
	 * @param gift_name 礼物名称
	 * @param is_online 是否上线 1是 0否
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gift/list", method = RequestMethod.GET)
	public String getList(@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "is_private", required = false, defaultValue="0") int is_private,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return gift_InfoService.getGiftInfoList(gift_id, gift_name, is_online, is_private,type, page, model);
	}
	
	@RequestMapping(value = "gift/box", method = RequestMethod.GET)
	public String getBoxList(@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "is_online", required = false, defaultValue="-1") int is_online,
			@RequestParam(value = "is_private", required = false, defaultValue="0") int is_private,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return gift_InfoService.getGiftBoxList(gift_id, gift_name, is_online, is_private, page, model);
	}
	
	/**
	 * 
	 * <p>功能描述：礼物添加页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:22:43</p>
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gift/add", method = RequestMethod.GET)
	public String add(
			@RequestParam(value="gift_type",required = false)Integer gift_type,
			Model model) {	
		return gift_InfoService.addGiftInfo(gift_type,model);
	}
	
	@RequestMapping(value = "gift/box/add", method = RequestMethod.GET)
	public String boxadd(Model model) {	
		return gift_InfoService.addGiftInfoBox(model);
	}
	/**
	 * 
	 * <p>功能描述：礼物编辑页面</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:11:19</p>
	 *
	 * @param id 礼物id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gift/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return gift_InfoService.editGiftInfo(id, model);
	}
	
	@RequestMapping(value = "gift/box/edit", method = RequestMethod.GET)
	public String editbox(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return gift_InfoService.editGiftInfoBox(id, model);
	}
	/**
	 * 
	 * <p>功能描述：保存礼物信息</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:13:21</p>
	 *
	 * @param id 礼物id
	 * @param gift_head 礼物图片
	 * @param gift_name 礼物名称
	 * @param gift_description 礼物文案
	 * @param gift_virtual 所需虚拟币
	 * @param gift_level 所需等级
	 * @param sort 排序
	 * @param is_online 是否上线 1是 0否
	 * @param anchor_virtual 主播可获得魅力值
	 * @param gift_type 礼物类型 0连击 1特效连击 2飘屏
	 * @param screen_type 飘屏客户端礼物 1法拉利 2宝马车
	 * @param verison 版本
	 * @param w_name 编辑人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "gift/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String save(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "gift_head", required = false, defaultValue="") String gift_head,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "gift_description", required = false, defaultValue="") String gift_description,
			@RequestParam(value = "type", required = false, defaultValue="0") int type,
			@RequestParam(value = "gift_virtual", required = false, defaultValue="0") int gift_virtual,
			@RequestParam(value = "gift_level", required = false, defaultValue="0") int gift_level,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "anchor_virtual", required = false, defaultValue="0") int anchor_virtual,
			@RequestParam(value = "gift_type", required = false, defaultValue="0") int gift_type,
			@RequestParam(value = "screen_type", required = false, defaultValue="0") int screen_type,
			@RequestParam(value = "version", required = false, defaultValue="1.0") String version,
			@RequestParam(value = "gift_key", required = false, defaultValue="0") int gift_key,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "quantifier", required = false, defaultValue="") String quantifier,
			@RequestParam(value = "module", required = false, defaultValue="0") String module,
			@RequestParam(value = "is_private", required = false, defaultValue="0") int is_private,
			@RequestParam(value = "anchor_divide", required = false) Integer anchor_divide,
			@RequestParam(value = "promoter_divide", required = false) Integer promoter_divide,		
			@RequestParam(value="gift_time",required = false,defaultValue="0")int gift_time,
			@RequestParam(value="gift_webp",required = false,defaultValue="")String gift_webp,
			@RequestParam(value="gift_head2",required = false,defaultValue="")String gift_head2,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return gift_InfoService.saveGiftInfo(id, gift_head, gift_name, gift_description,type, gift_virtual, gift_level, sort, is_online, anchor_virtual, gift_type, screen_type, version, gift_key, quantifier, module, is_private, anchor_divide, promoter_divide, gift_time, gift_webp, gift_head2, w_name, request);
	}
	
	@ResponseBody
	@RequestMapping(value = "gift/box/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String savebox(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "gift_head", required = false, defaultValue="") String gift_head,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "gift_description", required = false, defaultValue="") String gift_description,
			@RequestParam(value = "gift_virtual", required = false, defaultValue="0") int gift_virtual,
			@RequestParam(value = "gift_level", required = false, defaultValue="0") int gift_level,
			@RequestParam(value = "sort", required = false, defaultValue="0") int sort,
			@RequestParam(value = "is_online", required = false, defaultValue="0") int is_online,
			@RequestParam(value = "anchor_virtual", required = false, defaultValue="0") int anchor_virtual,
			@RequestParam(value = "gift_type", required = false, defaultValue="0") int gift_type,
			@RequestParam(value = "screen_type", required = false, defaultValue="0") int screen_type,
			@RequestParam(value = "version", required = false, defaultValue="1.0") String version,
			@RequestParam(value = "gift_key", required = false, defaultValue="0") int gift_key,
			@RequestParam(value = "name", required = false, defaultValue="") String name,
			@RequestParam(value = "quantifier", required = false, defaultValue="") String quantifier,
			@RequestParam(value = "module", required = false, defaultValue="0") String module,
			@RequestParam(value = "is_private", required = false, defaultValue="0") int is_private,
			@RequestParam(value = "anchor_divide", required = false) Integer anchor_divide,
			@RequestParam(value = "promoter_divide", required = false) Integer promoter_divide,		
			@RequestParam(value = "old_gift_id", required = false, defaultValue="0") int old_gift_id,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return gift_InfoService.saveGiftInfoBox(id, gift_head, gift_name, gift_description, gift_virtual, gift_level, sort, is_online, anchor_virtual, gift_type, screen_type, version, gift_key, quantifier, module, is_private,anchor_divide,promoter_divide, old_gift_id, w_name, request);
	}
	/**
	 * 
	 * <p>功能描述：删除礼物</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:20:35</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 礼物id
	 * @param request
	 * @return
	 */	
	@ResponseBody
	@RequestMapping(value = "gift/delete", method = RequestMethod.POST)
	public int delete(@RequestParam(value = "id", required = false,defaultValue="0") int id,
			@CookieValue(value="w_name", defaultValue="")String w_name, 
			HttpServletRequest request,
			Model model) {	
		return gift_InfoService.deleteGiftInfo(w_name, id, request);
	}
	

	/**
	 * 
	 * <p>功能描述：更新礼物上线状态</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午3:19:31</p>
	 *
	 * @param w_name 编辑人员
	 * @param id 礼物id
	 * @param is_online 是否上线 1是 0否
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "gift/updateonline", method = RequestMethod.POST)
	public int updateOnline(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			@RequestParam(value = "is_online", required = false,defaultValue="0") int is_online, 
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request,
			Model model) {	
		return gift_InfoService.updateGiftInfoOnline(w_name, id, is_online, request);
	}
	/**
	 * 
	 * <p>功能描述：送礼记录</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2016年12月27日 下午4:23:42</p>
	 *
	 * @param s_time 开始时间
	 * @param e_time 结束时间
	 * @param gift_id 礼物id
	 * @param uid 送礼者uid
	 * @param spending_uid 接收者uid
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "giftspending/list", method = RequestMethod.GET)
	public String getSpendingList(@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "is_online", required = false) Integer is_online,
			@RequestParam(value = "uid", required = false, defaultValue="0") long uid,
			@RequestParam(value = "spending_uid", required = false, defaultValue="0") long spending_uid,
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "type", required = false,defaultValue="0") int type, 
			@RequestParam(value = "page", required = false,defaultValue="1") int page, 
			Model model) {	
		return gift_InfoService.getSpendingList(s_time, e_time, gift_id, gift_name, is_online, uid, spending_uid,operate_center_id,agent_id,type, page, model);
	}
	
	 @ResponseBody
		@RequestMapping(value = "giftspending/agentlist", produces = "text/json;charset=UTF-8")
		public String agentList(int centerId) {
			return statisticalService.agentList(centerId);
		}
	/**
	 * 
	 * <p>功能描述：礼物统计</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年09月22日 下午13:50:42</p>
	 *
	 * @param start_time 开始时间
	 * @param end_time 结束时间
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "giftstatistics/list", method = RequestMethod.GET)	
	public String getSpendingStatisticsList(
			@RequestParam(value="start_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_date,
			@RequestParam(value="end_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_date,
			@RequestParam(value = "type", required = false,defaultValue="0") int type, 
			@RequestParam(value="page",required=false,defaultValue="1")int page,
			Model model){
		
		return gift_InfoService.getSpendingStatisticsList(start_date,end_date,type,page,model);
	}
	
    @RequestMapping(value = "giftspending/exportexcel", method = RequestMethod.GET)
	public String exportExcel(@RequestParam(value = "s_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date s_time,
			@RequestParam(value = "e_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date e_time,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "gift_name", required = false, defaultValue="") String gift_name,
			@RequestParam(value = "is_online", required = false) Integer is_online,
			@RequestParam(value = "uid", required = false, defaultValue="0") long uid,
			@RequestParam(value = "spending_uid", required = false, defaultValue="0") long spending_uid,
			@RequestParam(value = "operate_center_id", required = false,defaultValue="0") int operate_center_id, 
			@RequestParam(value = "agent_id", required = false,defaultValue="0") int agent_id, 
			@RequestParam(value = "type", required = false,defaultValue="0") int type, 
			HttpServletResponse response) {	
		return gift_InfoService.exportExcel(s_time, e_time, gift_id, gift_name, is_online, uid, spending_uid,operate_center_id, agent_id,type,response);
	}
    
    @RequestMapping(value = "giftstatistics/exportexcel2", method = RequestMethod.GET)   
	public String exportExcel2(
			@RequestParam(value="start_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date start_date,
			@RequestParam(value="end_date",required=false)@DateTimeFormat(pattern="yyyy-MM-dd")Date end_date,
			@RequestParam(value = "type", required = false,defaultValue="0") int type, 
			HttpServletResponse response
			){
		return gift_InfoService.exportExcel2(start_date, end_date,type, response);
	}
	@RequestMapping(value = "shortgift/list", method = RequestMethod.GET)
	public String getShortGiftList(Model model) {	
		return gift_InfoService.getShortGiftList(model);
	}
	@RequestMapping(value = "shortgift/edit", method = RequestMethod.GET)
	public String shortGiftedit(@RequestParam(value = "id", required = false,defaultValue="0") int id, 
			Model model) {	
		return gift_InfoService.editShortGift(id, model);
	}
	@ResponseBody
	@RequestMapping(value = "shortgift/getgiftinfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getGiftInfo(@RequestParam(value = "gift_id", required = false, defaultValue = "0") int gift_id) {
		return gift_InfoService.getGiftInfo(gift_id);
	}
	@ResponseBody
	@RequestMapping(value = "shortgift/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String shortGiftSave(@RequestParam(value = "id", required = false, defaultValue="0") int id,
			@RequestParam(value = "gift_id", required = false, defaultValue="0") int gift_id,
			@RequestParam(value = "gift_pic", required = false, defaultValue="") String gift_pic,
			@RequestParam(value = "remark", required = false, defaultValue="") String remark,
			@CookieValue(value="w_name", defaultValue="")String w_name,
			HttpServletRequest request) {	
		return gift_InfoService.saveShortGift(id,gift_id,gift_pic, w_name,remark, request);
	}
}

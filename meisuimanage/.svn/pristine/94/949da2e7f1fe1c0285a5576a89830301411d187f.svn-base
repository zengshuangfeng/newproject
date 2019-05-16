package com.meisui.manage.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Ianchor_RecommendDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Recommend;
import com.meisui.manage.entity.MengYan;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;

import net.sf.json.JSONObject;

@Service
public class Anchor_RecommendService {
	private static Logger log = Logger.getLogger(Anchor_RecommendService.class.getClass());
	@Autowired
	private Ianchor_RecommendDao ianchor_recommendDao;
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	
	
	public String getAnchorRecommendList(String f_uuid, String nickname, int page, Model model,
			HttpServletRequest request) {
		try {
			List<Anchor_Recommend> RecommendList = ianchor_recommendDao.getAnchorRecommendList(f_uuid, nickname, (page-1)*20, 20);
			for(Anchor_Recommend  anchor_recommend: RecommendList){		
				if(anchor_recommend !=null){
					User user=iuserDao.getUserByF_uuid(Long.parseLong(anchor_recommend.getF_uuid()));				
					anchor_recommend.setNickname(user.getNickname());			
				}
			}	
			int totalRecord = ianchor_recommendDao.getAnchorRecommendCount(f_uuid, nickname);
			
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("anchorrecommend/list");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("RecommendList", RecommendList);
			model.addAttribute("activeUrl", "anchorrecommend");
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
		} catch (Exception e) {
			log.error("主播首页推荐列表", e);
		}
		return "anchorrecommend/list";
	}


	public int delete(int id, HttpServletRequest request) {
		int result=0;
		try{			
			String w_name = String.valueOf(request.getAttribute("w_name"));			
			result=ianchor_recommendDao.deleteAnchorRecommend(id);
			if(result>0){
				imanage_RecordDao.insertManageRecord(w_name, "主播首页推荐删除", "t_anchor_recommend", id, IPUtil.getIp(request), new Date());	
			}
			return result;
		} catch (Exception e) {
			log.error("主播首页推荐删除", e);
			return result;
		}
	}


	public String getFuuidExist(long f_uuid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			User user = iuserDao.getUserByF_uuid(f_uuid);// 通过房间号获取该用户uid
			jsonObject.put("nickname", user==null?"":user.getNickname());
			if (user != null) {
				User useranchor = iuserDao.getUserByisAnchor(user.getId());// 通过uid过滤不是主播的用户
				if (useranchor != null) {
				User user2=iuserDao.getUser(useranchor.getId());//通过uid得到值
				Integer extis = ianchor_recommendDao.getAnchorRecommendbyuid(user2.getF_uuid());
					if (extis > 0)
						jsonObject.put("code", 1);// 该主播在社区推荐表已存在
				} else
					jsonObject.put("code", -1);// 该用户不是主播

			} else {
				jsonObject.put("code", -1);// 该用户不是主播
			}

		} catch (Exception e) {
			log.error("主播是否存在", e);
		}
		return jsonObject.toString();
	}


	public String Save(int id,String f_uuid, String pic, int sort, HttpServletRequest request, Model model) {
		try{	
			String w_name = String.valueOf(request.getAttribute("w_name"));
			Date date=new Date();
			
			User user = iuserDao.getUserByF_uuid(Long.parseLong(f_uuid));
			Anchor_Recommend anchorrecommend = new Anchor_Recommend();
			anchorrecommend.setF_uuid(f_uuid);
			if ("".equals(pic)) {
				anchorrecommend.setPic(user.getHead());// 头像上传用上传头像图片
			} else {
				anchorrecommend.setPic(pic);
				// 若头像不上传，则用原来头像图片
			}		
			anchorrecommend.setSort(sort);
			anchorrecommend.setW_name(w_name);		
			anchorrecommend.setIs_del(0);		
			int result = 0;
			if(id>0)
			{
				anchorrecommend.setId(id);
				anchorrecommend.setUpdate_time(date);
				result = ianchor_recommendDao.updateAnchorRecommend(anchorrecommend);
				imanage_RecordDao.insertManageRecord(w_name, "更新首页主播推荐", "t_anchor_recommend", anchorrecommend.getId(), IPUtil.getIp(request), date);
			}
			else
			{
				anchorrecommend.setCreate_time(date);
				anchorrecommend.setUpdate_time(date);
				result = ianchor_recommendDao.insertAnchorRecommend(anchorrecommend);
				imanage_RecordDao.insertManageRecord(w_name, "添加首页主播推荐", "t_anchor_recommend", anchorrecommend.getId(), IPUtil.getIp(request), date);
			}
			if(result > 0){
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		}
		catch(Exception ex)
		{
			log.error("保存首页主播推荐", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}


	public String edit(int id, Model model) {
		try {
			Anchor_Recommend recommend = ianchor_recommendDao.getAnchorRecommend(id);
			User user=iuserDao.getUserByF_uuid(Long.parseLong(recommend.getF_uuid()));
			recommend.setNickname(user.getNickname());
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("recommend", recommend);
			model.addAttribute("activeUrl", "anchorrecommend");
		} catch (Exception e) {
			log.error("首页主播推荐编辑页面", e);
		}
		return "anchorrecommend/edit";
	}

}

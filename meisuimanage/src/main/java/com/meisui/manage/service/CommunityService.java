package com.meisui.manage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IcommunityDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.dao.IviewDao;
import com.meisui.manage.entity.Community_Recommend;
import com.meisui.manage.entity.Post;
import com.meisui.manage.entity.Post_Comment;
import com.meisui.manage.entity.Post_Pic;
import com.meisui.manage.entity.Post_Tag;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.meisui.manage.utils.QiNiuUtil;
import com.meisui.manage.utils.Tools;

import net.sf.json.JSONObject;

@Service
public class CommunityService {
	private static Logger log = Logger.getLogger(CommunityService.class.getClass());
	@Autowired
	private IcommunityDao communitydao;
	@Autowired
	private IuserDao iuserdao;
	@Autowired
	private Imanage_RecordDao imanage_RecordDao;
	@Autowired
	private IviewDao iviewDao;

	public String getPopularAnchor(int uid, String nickname, String start_time, String end_time, int page,
			Model model) {

		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int type = 0;
			List<Community_Recommend> CommunityList = communitydao.getCommunityList(uid, nickname, type, b_time, e_time,
					(page - 1) * 20, 20);
			for (Community_Recommend communitylist : CommunityList) {
				User user = iuserdao.getUser((long) communitylist.getUid());
				communitylist.setNickname(user.getNickname());
				communitylist.setHead(user.getHead());
				Date startDate = sdf.parse(communitylist.getStart_time());
				Date endDate = sdf.parse(communitylist.getEnd_time());
				if (startDate.getTime() > date.getTime()) {// 若开始时间大于当前时间，则表示未开始
					communitylist.setStatus(0);
				} else if (endDate.getTime() < date.getTime()) {// 若结束时间小于当前时间，表示活动结束，下线
					communitylist.setStatus(1);
				} else if (startDate.getTime() < date.getTime() && endDate.getTime() > date.getTime()) {
					communitylist.setStatus(2);
				}

			}
			int totalRecord = communitydao.getCommunityRecommendRecordCount(uid, nickname, type, b_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/popularanchor");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("CommunityList", CommunityList);
			model.addAttribute("activeUrl", "popularanchor");
			model.addAttribute("nickname", nickname);
			model.addAttribute("uid", uid);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		} catch (Exception e) {
			log.error("热门主播列表", e);
		}
		return "communityrecommend/popularanchor";
	}

	public String addPopularAnchor(Model model) {		
		model.addAttribute("activeUrl", "popularanchor");
		return "communityrecommend/popularanchoradd";
	}

	public int PopularAnchordelete(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = communitydao.deletePopularAnchor(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除热门主播", "t_community_recommend", id,
						IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除热门主播", e);
		}
		return result;
	}

	public String savePopularAnchor(int id, int uid, String title, String pic, String start_time, String end_time,
			int sort, int is_online, String w_name, HttpServletRequest request) {
		try {
			Date date = new Date();
			User user = iuserdao.getUser((long) uid);
			Community_Recommend communityrecommend = new Community_Recommend();
			communityrecommend.setUid(uid);
			communityrecommend.setType(0);
			communityrecommend.setTitle(title);
			if ("".equals(pic)) {
				communityrecommend.setPic(user.getHead());// 头像上传用上传头像图片
			} else {
				communityrecommend.setPic(pic);
				;// 若头像不上传，则用原来头像图片
			}
			communityrecommend.setStart_time(start_time);
			communityrecommend.setEnd_time(end_time);
			communityrecommend.setSort(sort);
			communityrecommend.setIs_del(0);
			communityrecommend.setIs_online(is_online);
			communityrecommend.setW_name(w_name);

			int result = 0;
			if (id > 0) {
				communityrecommend.setId(id);
				communityrecommend.setUpdate_time(date);
				result = communitydao.updatePopularAnchor(communityrecommend);
				imanage_RecordDao.insertManageRecord(w_name, "更新热门主播信息", "t_community_recommend",
						communityrecommend.getId(), IPUtil.getIp(request), date);
			} else {
				communityrecommend.setCreate_time(date);
				result = communitydao.insertPopularAnchor(communityrecommend);
				imanage_RecordDao.insertManageRecord(w_name, "添加热门主播", "t_community_recommend",
						communityrecommend.getId(), IPUtil.getIp(request), date);
			}
			if (result > 0) {
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception ex) {
			log.error("保存热门主播", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String editPopularAnchor(int id, Model model) {
		Community_Recommend communityrecommend = communitydao.getCommunityByid(id);
		communityrecommend.setId(id);
		User user = iuserdao.getUser((long) communityrecommend.getUid());
		communityrecommend.setHead(user.getHead());
		model.addAttribute("communityrecommend", communityrecommend);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "popularanchor");
		return "communityrecommend/popularanchoredit";
	}

	public String gethotlive(long f_uuid, String start_time, String end_time, int page, Model model) {

		try {
			String b_time = "";
			String e_time = "";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			User user = iuserdao.getUserByF_uuid(f_uuid);
			int uid = 0;
			if (user != null) {
				uid = (int) user.getId();
			} else {
				uid = 0;
			}
			int type = 1;
			List<Community_Recommend> CommunityList = communitydao.getCommunityList(uid, "", type, b_time, e_time,
					(page - 1) * 20, 20);

			for (Community_Recommend communitylist : CommunityList) {
				User userlist = iuserdao.getUser((long) communitylist.getUid());
				communitylist.setF_uuid(Long.parseLong(userlist.getF_uuid()));

				Date startDate = sdf.parse(communitylist.getStart_time());
				Date endDate = sdf.parse(communitylist.getEnd_time());
				if (startDate.getTime() > date.getTime()) {// 若开始时间大于当前时间，则表示未开始
					communitylist.setStatus(0);
				} else if (endDate.getTime() < date.getTime()) {// 若结束时间小于当前时间，表示活动结束，下线
					communitylist.setStatus(1);
				} else if (startDate.getTime() < date.getTime() && endDate.getTime() > date.getTime()) {
					communitylist.setStatus(2);
				}
			}

			int totalRecord = communitydao.getCommunityRecommendRecordCount(uid, "", type, b_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/hotlive");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("CommunityList", CommunityList);
			model.addAttribute("activeUrl", "hotlive");
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		} catch (Exception e) {
			log.error("热门直播列表", e);
		}
		return "communityrecommend/hotlive";
	}

	public int Hotlivedelete(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = communitydao.deleteHotlive(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除热门直播", "t_community_recommend", id,
						IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除热门直播", e);
		}
		return result;
	}

	public String HotliveAdd(Model model) {
		model.addAttribute("activeUrl", "hotlive");
		return "communityrecommend/hotliveadd";
	}

	@SuppressWarnings("unused")
	public String saveHotLive(int id, long f_uuid, String pic, String start_time, String end_time, int sort,
			int is_online, String w_name, HttpServletRequest request) {
		try {
			User user = iuserdao.getUserByF_uuid(f_uuid);
			User_Anchor useranchor = iuserdao.getUserAnchor(user.getId());
			w_name = String.valueOf(request.getAttribute("w_name"));
			if (user != null) {
				Date date = new Date();
				Community_Recommend communityrecommend = new Community_Recommend();
				communityrecommend.setUid((int) user.getId());
				communityrecommend.setType(1);
				if ("".equals(pic)) {
					communityrecommend.setPic(useranchor.getAnchor_cover());// 头像上传用上传头像图片
				} else {
					communityrecommend.setPic(pic);
					;// 若头像不上传，则用原来头像图片
				}
				communityrecommend.setStart_time(start_time);
				communityrecommend.setEnd_time(end_time);
				communityrecommend.setSort(sort);
				communityrecommend.setIs_del(0);
				communityrecommend.setIs_online(is_online);
				communityrecommend.setW_name(w_name);

				int result = 0;
				if (id > 0) {
					communityrecommend.setId(id);
					communityrecommend.setUpdate_time(date);
					result = communitydao.updatHotLive(communityrecommend);
					imanage_RecordDao.insertManageRecord(w_name, "更新热门直播信息", "t_community_recommend",
							communityrecommend.getId(), IPUtil.getIp(request), date);
				} else {
					communityrecommend.setCreate_time(date);
					result = communitydao.insertHotLive(communityrecommend);
					imanage_RecordDao.insertManageRecord(w_name, "添加热门直播", "t_community_recommend",
							communityrecommend.getId(), IPUtil.getIp(request), date);
				}
				if (result > 0) {
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}
			} else {
				return "{\"code\":-1,\"msg\":\"房间号不存在\"}";
			}
		} catch (Exception ex) {
			log.error("保存热门直播", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String HotLiveEdit(int id, Model model) {
		Community_Recommend hotlive = communitydao.getCommunityByid(id);
		User user = iuserdao.getUser((long) hotlive.getUid());
		hotlive.setF_uuid(Long.parseLong(user.getF_uuid()));
		hotlive.setId(id);
		model.addAttribute("hotlive", hotlive);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "hotlive");
		return "communityrecommend/hotliveedit";
	}

	public String getPicManager(long uid, String start_time, String end_time, String title, int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			int type = 0;
			List<Post> postList = communitydao.getPostList(uid, 0, type, b_time, e_time, title, (page - 1) * 20, 20);

			for (Post post : postList) {
				List<Post_Pic> postpiclist = communitydao.getPostListBypostid(post.getId());
				String pic = "";
				for (Post_Pic postpic : postpiclist) {
					pic = pic + postpic.getPic() + ",";
				}

				post.setPost_pic(pic);
				User user = iuserdao.getUser((long) post.getUid());
				post.setNickname(user.getNickname());
				Integer view_count = iviewDao.getViewCount(post.getId());
				post.setView_count(view_count);
			}

			int totalRecord = communitydao.getPostRecordCount(uid, 0, type, b_time, e_time, title);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/picmanager");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("postList", postList);
			model.addAttribute("activeUrl", "picmanager");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("uid", uid);
			model.addAttribute("title", title);
		} catch (Exception e) {
			log.error("照片管理列表", e);
		}
		return "communityrecommend/picmanager";
	}

	public String PicManagerAdd(Model model) {
		List<Post_Tag> posttag=communitydao.getPostTag();
		model.addAttribute("posttag", posttag);
		model.addAttribute("activeUrl", "picmanager");		
		return "communityrecommend/picmanageradd";
	}

	public String savePicManager(int id, long uid, String title, int zan_count, int share_count, String pic,
			int is_online, int tag_id,String w_name, HttpServletRequest request) {
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			Post post = new Post();
			post.setUid((int) uid);
			post.setZan_count(zan_count);
			post.setShare_count(share_count);
			post.setW_name(w_name);
			post.setType(0);
			post.setTitle(title);
			post.setIs_del(0);
			post.setIs_online(is_online);
			post.setTag_id(tag_id);
			if (pic.startsWith(","))
				pic = pic.substring(1);
			String[] _pic = pic.split(",");
			if (_pic.length > 6) {
				return "{\"code\":-1,\"msg\":\"照片数量不能大于6张\"}";
			}
			int result = 0;
			QiNiuUtil qiNiuUtil = new QiNiuUtil();
			if (id > 0) {
				post.setId(id);
				post.setUpdate_time(date);
				result = communitydao.updatePicManager(post);
				Map<String, Integer> map = new HashMap<>();
				List<String> new_pic = Arrays.asList(_pic);
				int i = 1;
				for (String string : new_pic) {
					map.put(string, i);
					i++;
				}
				List<String> old_pic = communitydao.selectPicNameList(id);
				for (String string : old_pic) {
					Integer pic_sort = map.get(string);
					communitydao.updatePicSort(id, string, pic_sort);
				}
				List<String> addList = Tools.compare(old_pic, new_pic);
				for (String add : addList) {
					int sort = map.get(add);
					if (StringUtils.isNotBlank(add)) {
						String url = PropertyUtil.getValue("meisui_pic_url") + add;
						String width_height = qiNiuUtil.getImageSize(url);
						int width = 0, height = 0;
						if (!StringUtils.isBlank(width_height)) {
							String[] width_heights = width_height.split("x");
							width = Integer.parseInt(width_heights[0]);
							height = Integer.parseInt(width_heights[1]);
						}
						communitydao.addPostPic(post.getId(), add, sort, width, height);
					}
				}
				if (result > 0) {
					RedisUtil.SetHsetJedis(12, "post" + id, "zan_count", String.valueOf(zan_count)); // 将点赞数放进redis中
					RedisUtil.SetHsetJedis(12, "post" + id, "share_count", String.valueOf(share_count)); // 将分享放进redis中
					imanage_RecordDao.insertManageRecord(w_name, "更新照片管理信息", "t_post", post.getId(),
							IPUtil.getIp(request), date);
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}

			} else {
				post.setCreate_time(date);
				post.setUpdate_time(date);
				result = communitydao.insertPicManager(post);
				int sort = 0;
				for (String p : _pic) {
					sort = sort + 1;
					if (StringUtils.isNotBlank(p)) {
						String url = PropertyUtil.getValue("meisui_pic_url") + p;
						String width_height = qiNiuUtil.getImageSize(url);
						int width = 0, height = 0;
						if (!StringUtils.isBlank(width_height)) {
							String[] width_heights = width_height.split("x");
							width = Integer.parseInt(width_heights[0]);
							height = Integer.parseInt(width_heights[1]);
						}
						communitydao.addPostPic(post.getId(), p, sort, width, height);
					}
				}
				//User_Info userinfo = iuserdao.getUserInfo(uid);// 获取当前帖子数
				int postcount=communitydao.getPostCount(post.getUid());//重新统计当前帖子数
				iuserdao.updatepostcountByuid(uid, postcount);// 当增加帖子时，user_info表的帖子数也随之加1
				if (result > 0) {
					RedisUtil.SetHsetJedis(12, "post" + post.getId(), "zan_count", String.valueOf(zan_count)); // 将点赞数放进redis中
					RedisUtil.SetHsetJedis(12, "post" + post.getId(), "share_count", String.valueOf(share_count)); // 将分享放进redis中
					imanage_RecordDao.insertManageRecord(w_name, "添加照片管理", "t_post", post.getId(),
							IPUtil.getIp(request), date);
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}
			}
		} catch (Exception ex) {
			log.error("保存照片管理", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String PicManagerEdit(int id, Model model) {
		Post postlist = communitydao.getPostByid(id);
		postlist.setId(id);

		List<Post_Pic> postpic = new ArrayList<Post_Pic>();

		if (id > 0) {
			postpic = communitydao.getPostPicListBySpId(id);
		}
		List<Post_Tag> posttag=communitydao.getPostTag();
		model.addAttribute("posttag", posttag);
		model.addAttribute("postpic", postpic);
		model.addAttribute("postlist", postlist);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "picmanager");
		return "communityrecommend/picmanageredit";
	}

	public int delPic(int id, HttpServletRequest request) {
		try {
			communitydao.deletePic(id);
			String w_name = String.valueOf(request.getAttribute("w_name"));
			imanage_RecordDao.insertManageRecord(w_name, "图片删除", "t_post_pic", id, IPUtil.getIp(request), new Date());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int PostOnline(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = communitydao.PostIsOnline(id, 1, w_name, date);
				if(result>0){
					Post post=communitydao.getPostByid(id);
					int postcount=communitydao.getPostCount(post.getUid());//重新统计当前帖子数
					iuserdao.updatepostcountByuid(post.getUid(), postcount);// 当增加帖子时，user_info表的帖子数也随之加1
					imanage_RecordDao.insertManageRecord(w_name, "照片管理上线操作", "t_post", id, IPUtil.getIp(request), date);
				}
				
			}
		} catch (Exception e) {
			log.error("照片管理上线操作", e);
		}
		return result;
	}

	public int PostDownline(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = communitydao.PostIsOnline(id, 0, w_name, date);
				if(result>0){
					Post post=communitydao.getPostByid(id);
					int postcount=communitydao.getPostCount(post.getUid());//重新统计当前帖子数
					iuserdao.updatepostcountByuid(post.getUid(), postcount);// 当增加帖子时，user_info表的帖子数也随之加1
					imanage_RecordDao.insertManageRecord(w_name, "照片管理上线操作", "t_post", id, IPUtil.getIp(request), date);
				}
				
			}
		} catch (Exception e) {
			log.error("照片管理下线操作", e);
		}
		return result;
	}

	public int PostDelete(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = communitydao.PostDelete(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "照片管理删除操作", "t_post", id, IPUtil.getIp(request), date);
				Post post = communitydao.getPostByid(id);
				if (result > 0) {// 删除成功后，user_info表的帖子数也随之减1
					//User_Info userinfo = iuserdao.getUserInfo((long) post.getUid());// 获取当前帖子数
					int postcount=communitydao.getPostCount(post.getUid());//重新统计当前帖子数
					iuserdao.updatepostcountByuid((long) post.getUid(), postcount);
				}
			}
		} catch (Exception e) {
			log.error("照片管理删除操作", e);
		}
		return result;
	}

	public String getPostComment(long post_id, long uid, String nickname, String comment, String start_time,
			String end_time, int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Post_Comment> commentlist = communitydao.getPostCommentList(post_id, uid, nickname, comment, b_time,
					e_time, (page - 1) * 20, 20);

			for (Post_Comment post : commentlist) {
				User user = iuserdao.getUser((long) post.getUid());
				post.setNickname(user.getNickname());
			}
			List<User> userlist = iuserdao.getUserByIsvest();// 列出前100个马甲号
			Integer topexist = communitydao.getIstopexist((int) post_id);
			int topstatus = 0;
			if (topexist != null) {
				topstatus = 1;
			}
			int totalRecord = communitydao.getPostCommentRecordCount(post_id, uid, nickname, comment, b_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/picmanager/comment");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("commentlist", commentlist);
			model.addAttribute("activeUrl", "picmanager");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("uid", uid);
			model.addAttribute("comment", comment);
			model.addAttribute("nickname", nickname);
			model.addAttribute("post_id", post_id);
			model.addAttribute("userlist", userlist);
			model.addAttribute("topstatus", topstatus);
		} catch (Exception e) {
			log.error("照片管理评论列表", e);
		}
		return "communityrecommend/comment";
	}

	public int CommentDelete(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				int post_id = communitydao.getpostcomentByid(id);

				result = communitydao.CommentDelete(id, w_name, date);

				if (result > 0) {
					/*communitydao.updateCommentCount(post_id, -1);// 在评论添加成功后，帖子表中评论数减1*/				
					/*RedisUtil.SetHincrbyJedis2(12, "post" + post_id, "comment_count", -1);*/
					int count=communitydao.getPostCommentCount(post_id);
					communitydao.updateCommentCount(post_id, count);
					RedisUtil.SetHsetJedis(12, "post" + post_id, "comment_count", String.valueOf(count)); 
					imanage_RecordDao.insertManageRecord(w_name, "评论删除操作", "t_post", id, IPUtil.getIp(request), date);
				}

			}
		} catch (Exception e) {
			log.error("评论删除操作", e);
		}
		return result;
	}

	public int topComment(String w_name, int id, int is_top, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				if (is_top == 1) {
					result = communitydao.topComment(id, 0, w_name, date);
				} else if (is_top == 0) {
					result = communitydao.topComment(id, 1, w_name, date);
				}

				imanage_RecordDao.insertManageRecord(w_name, "置顶评论", "t_post", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("置顶评论", e);
		}
		return result;
	}

	public String addPicmanagerComment(int post_id, long uid, int zan_count, int is_top, String comment, String w_name,
			HttpServletRequest request) {
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			Post_Comment post_comment = new Post_Comment();
			post_comment.setPost_id(post_id);
			post_comment.setUid((int) uid);
			post_comment.setZan_count(zan_count);
			post_comment.setIs_top(is_top);
			post_comment.setComment(comment);
			post_comment.setW_name(w_name);
			post_comment.setCreate_time(date);
			post_comment.setIs_del(0);
			post_comment.setUpdate_time(date);
			int result = 0;
			result = communitydao.insertAddPicmanagerComment(post_comment);

			if (result > 0) {
				int count=communitydao.getPostCommentCount(post_id);
				/*communitydao.updateCommentCount(post_comment.getPost_id(), 1);// 在评论添加成功后，帖子表中评论数加1*/				
				RedisUtil.SetHsetJedis(12, "comment" + post_comment.getId(), "zan_count", String.valueOf(zan_count)); // 将点赞数放进redis中
				/*RedisUtil.SetHincrbyJedis2(12, "post" + post_comment.getPost_id(), "comment_count", 1);*/	
				communitydao.updateCommentCount(post_comment.getPost_id(), count);
				RedisUtil.SetHsetJedis(12, "post" + post_comment.getPost_id(), "comment_count", String.valueOf(count)); 
				imanage_RecordDao.insertManageRecord(w_name, "添加评论", "t_post_comment", post_comment.getId(),
						IPUtil.getIp(request), date);
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception ex) {
			log.error("添加评论", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String getVideoManager(long uid, long f_uuid, String start_time, String end_time, String title, int page,
			Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			int type = 1;
			List<Post> postList = communitydao.getPostList(uid, f_uuid, type, b_time, e_time, title, (page - 1) * 20,
					20);
			for (Post post : postList) {
				User user = iuserdao.getUser((long) post.getUid());
				post.setNickname(user.getNickname());
				post.setF_uuid(Long.parseLong(user.getF_uuid()));
				Integer view_count = iviewDao.getViewCount(post.getId());
				post.setView_count(view_count);
			}

			int totalRecord = communitydao.getPostRecordCount(uid, f_uuid, type, b_time, e_time, title);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/videomanager");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("postList", postList);
			model.addAttribute("activeUrl", "videomanager");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("uid", uid);
			model.addAttribute("title", title);
			model.addAttribute("f_uuid", f_uuid);
		} catch (Exception e) {
			log.error("照片管理列表", e);
		}
		return "communityrecommend/videomanager";
	}

	public String videoManagerAdd(Model model) {
		List<Post_Tag> posttag=communitydao.getPostTag();
		model.addAttribute("posttag", posttag);
		model.addAttribute("activeUrl", "videomanager");
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		return "communityrecommend/videomanageradd";
	}

	public String saveVideoManager(int id, long uid, String title, int zan_count, int share_count, String video,
			int is_online,int tag_id, String w_name, HttpServletRequest request) {
		try {
			w_name = String.valueOf(request.getAttribute("w_name"));
			Date date = new Date();
			Post post = new Post();
			post.setUid((int) uid);
			post.setZan_count(zan_count);
			post.setShare_count(share_count);
			post.setW_name(w_name);
			post.setType(1);
			post.setTitle(title);
			post.setIs_del(0);
			post.setIs_online(is_online);
			post.setVideo(video);
			post.setTag_id(tag_id);
			if (!StringUtils.isBlank(video)) {
				String url = PropertyUtil.getValue("meisui_pic_url") + video;
				QiNiuUtil qiNiuUtil = new QiNiuUtil();
				String width_height = qiNiuUtil.getVideoWidthHeight(url);
				if (!StringUtils.isBlank(width_height)) {
					String[] width_heights = width_height.split("x");
					post.setVideo_width(Integer.parseInt(width_heights[0]));
					post.setVideo_height(Integer.parseInt(width_heights[1]));
				}
			}
			int result = 0;
			if (id > 0) {
				post.setId(id);
				post.setUpdate_time(date);
				result = communitydao.updateVideoManager(post);

				if (result > 0) {
					RedisUtil.SetHsetJedis(12, "post" + id, "zan_count", String.valueOf(zan_count)); // 将点赞数放进redis中
					RedisUtil.SetHsetJedis(12, "post" + id, "share_count", String.valueOf(share_count)); // 将分享放进redis中
					imanage_RecordDao.insertManageRecord(w_name, "更新视频管理信息", "t_post", post.getId(),
							IPUtil.getIp(request), date);
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}
			} else {
				post.setCreate_time(date);
				post.setUpdate_time(date);
				result = communitydao.insertVideoManager(post);

			//	User_Info userinfo = iuserdao.getUserInfo(uid);// 获取当前帖子数
				int postcount=communitydao.getPostCount(post.getUid());//重新统计当前帖子数
				iuserdao.updatepostcountByuid(uid, postcount);// 当增加帖子时，user_info表的帖子数也随之加1
				if (result > 0) {
					RedisUtil.SetHsetJedis(12, "post" + post.getId(), "zan_count", String.valueOf(zan_count)); // 将点赞数放进redis中
					RedisUtil.SetHsetJedis(12, "post" + post.getId(), "share_count", String.valueOf(share_count)); // 将分享数放进redis中
					imanage_RecordDao.insertManageRecord(w_name, "添加视频管理", "t_post", post.getId(),
							IPUtil.getIp(request), date);
					return "{\"code\":0,\"msg\":\"保存成功\"}";
				}
			}

		} catch (Exception ex) {
			log.error("保存视频管理", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public String videoManagerEdit(int id, Model model) {
		List<Post_Tag> posttag=communitydao.getPostTag();
		model.addAttribute("posttag", posttag);
		Post postlist = communitydao.getPostByid(id);
		model.addAttribute("postlist", postlist);
		model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
		model.addAttribute("activeUrl", "videomanager");
		return "communityrecommend/videomanageredit";
	}

	public String getVideoComment(long post_id, long uid, String nickname, String comment, String start_time,
			String end_time, int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Post_Comment> commentlist = communitydao.getPostCommentList(post_id, uid, nickname, comment, b_time,
					e_time, (page - 1) * 20, 20);

			for (Post_Comment post : commentlist) {
				User user = iuserdao.getUser((long) post.getUid());
				post.setNickname(user.getNickname());

			}
			List<User> userlist = iuserdao.getUserByIsvest();// 列出前100个马甲号

			Integer topexist = communitydao.getIstopexist((int) post_id);
			int topstatus = 0;
			if (topexist != null) {
				topstatus = 1;
			}
			int totalRecord = communitydao.getPostCommentRecordCount(post_id, uid, nickname, comment, b_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/videomanager/comment");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("commentlist", commentlist);
			model.addAttribute("activeUrl", "videomanager");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("uid", uid);
			model.addAttribute("comment", comment);
			model.addAttribute("nickname", nickname);
			model.addAttribute("post_id", post_id);
			model.addAttribute("userlist", userlist);
			model.addAttribute("topstatus", topstatus);
		} catch (Exception e) {
			log.error("视频管理评论列表", e);
		}
		return "communityrecommend/videocomment";
	}

	public String getFuuidExist(long f_uuid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			User user = iuserdao.getUserByF_uuid(f_uuid);// 通过房间号获取该用户uid
			if (user != null) {
				User useranchor = iuserdao.getUserByisAnchor(user.getId());// 通过uid过滤不是主播的用户
				if (useranchor != null) {
					Integer extis = communitydao.getcommunityrecommendbyuid(useranchor.getId(), 1);
					if (extis > 0)
						jsonObject.put("code", 1);// 该主播在社区推荐表已存在
				} else
					jsonObject.put("code", -1);// 该用户不是主播

			} else {
				jsonObject.put("code", -1);// 该用户不是主播
			}

		} catch (Exception e) {
			log.error("热门直播，主播是否存在", e);
		}
		return jsonObject.toString();
	}

	public String getPopularAnchorExist(long uid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			User useranchor = iuserdao.getUserByisAnchor(uid);// 通过uid过滤不是主播的用户
			if (useranchor != null) {
				Integer extis = communitydao.getcommunityrecommendbyuid(useranchor.getId(), 0);
				if (extis > 0)
					jsonObject.put("code", 1);// 该主播在社区推荐表已存在
			} else
				jsonObject.put("code", -1);// 该用户不是主播
		} catch (Exception e) {
			log.error("热门主播，主播是否存在", e);
		}
		return jsonObject.toString();
	}

	public String getPopularAnchorExist2(long uid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		try {
			User useranchor = iuserdao.getUserByisAnchor(uid);// 通过uid过滤不是主播的用户
			if (useranchor == null)
				jsonObject.put("code", -1);// 该用户不是主播
		} catch (Exception e) {
			log.error("热门主播，主播是否存在", e);
		}
		return jsonObject.toString();
	}

	public String getCommentManager(long uid, String nickname, String comment, String start_time, String end_time,
			int page, Model model) {
		try {
			String b_time = "";
			String e_time = "";
			if (StringUtils.isNotBlank(start_time)) {
				b_time = start_time + " 00:00:00";
			}
			if (StringUtils.isNotBlank(end_time)) {
				e_time = end_time + " 23:59:59";
			}
			List<Post_Comment> commentlist = communitydao.getPostCommentManagerList(uid, nickname, comment, b_time,
					e_time, (page - 1) * 20, 20);

			for (Post_Comment post : commentlist) {
				User user = iuserdao.getUser((long) post.getUid());
				post.setNickname(user.getNickname());

			}
			List<User> userlist = iuserdao.getUserByIsvest();// 列出前100个马甲号

			int totalRecord = communitydao.getPostCommentManagerRecordCount(uid, nickname, comment, b_time, e_time);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/commentmanager");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("commentlist", commentlist);
			model.addAttribute("activeUrl", "commentmanager");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
			model.addAttribute("uid", uid);
			model.addAttribute("comment", comment);
			model.addAttribute("nickname", nickname);
			model.addAttribute("userlist", userlist);
		} catch (Exception e) {
			log.error("评论管理列表", e);
		}
		return "communityrecommend/commentmanager";
	}

	public String getTagManager(String name, int page, Model model) {
		try {	
			List<Post_Tag> taglist = communitydao.getTagList(name, (page - 1) * 20, 20);
			int totalRecord = communitydao.getTagRecordCount(name);
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("community/tagmanager");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("taglist", taglist);
			model.addAttribute("activeUrl", "tagmanager");
			model.addAttribute("uploadUrl", PropertyUtil.getValue("meisui_pic_url"));	
			model.addAttribute("name", name);
		} catch (Exception e) {
			log.error("标签管理列表", e);
		}
		return "communityrecommend/tagmanager";
	}

	public String saveTagManager(int id, String name, String w_name, HttpServletRequest request) {
		try {
			Date date = new Date();
			w_name = String.valueOf(request.getAttribute("w_name"));
			Post_Tag post_tag  = new Post_Tag();
			post_tag.setName(name);
			post_tag.setW_name(w_name);
			post_tag.setIs_del(0);
			int result = 0;
			if (id > 0) {
				post_tag.setId(id);
				post_tag.setUpdate_time(date);
				result = communitydao.updateTagManager(post_tag);
				imanage_RecordDao.insertManageRecord(w_name, "更新标签管理信息", "t_post_manager",
						post_tag.getId(), IPUtil.getIp(request), date);
			} else {
				post_tag.setCreate_time(date); 
				post_tag.setUpdate_time(date);
				result = communitydao.insertTagManager(post_tag);
				imanage_RecordDao.insertManageRecord(w_name, "标签管理添加标签", "t_post_manager",
						post_tag.getId(), IPUtil.getIp(request), date);
			}
			if (result > 0) {
				return "{\"code\":0,\"msg\":\"保存成功\"}";
			}
		} catch (Exception ex) {
			log.error("保存标签", ex);
			ex.printStackTrace();
		}
		return "{\"code\":-1,\"msg\":\"保存失败\"}";
	}

	public int TagManagerdelete(String w_name, int id, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = communitydao.deleteTag(id, w_name, date);
				imanage_RecordDao.insertManageRecord(w_name, "删除帖子标签", "t_post_tag", id,
						IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("删除帖子标签", e);
		}
		return result;
	}

	public int topPost(String w_name, int id, int is_top, HttpServletRequest request) {
		int result = 0;
		try {
			if (id > 0) {
				Date date = new Date();
				w_name = String.valueOf(request.getAttribute("w_name"));
				if (is_top == 1) {
					result = communitydao.topPost(id, 0, w_name);//取消置顶
				} else if (is_top == 0) {
					result = communitydao.topPost(id, 1, w_name);//置顶
				}

				imanage_RecordDao.insertManageRecord(w_name, "帖子置顶", "t_post", id, IPUtil.getIp(request), date);
			}
		} catch (Exception e) {
			log.error("帖子置顶", e);
		}
		return result;
	}

}

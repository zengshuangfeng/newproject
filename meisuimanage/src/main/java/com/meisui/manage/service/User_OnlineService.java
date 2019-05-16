package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.User_Online;
import com.meisui.manage.utils.PageUtil;

/**
 * <p>文件名称：User_OnlineService.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年2月14日 下午4:25:48
 */
@Service
public class User_OnlineService {
	private static Logger log = Logger.getLogger(User_OnlineService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	/**
	 * 
	 * <p>功能描述：用户在线列表</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2017年2月14日 下午4:26:22</p>
	 *
	 * @param uid 用户uid
	 * @param nickname 用户昵称
	 * @param page 页码
	 * @param model
	 */
	public String getUserOnlineList(String uid, String f_uuid, String nickname, int page, Model model)
	{
		try {
			Set<String> keyStrings = RedisUtil.GetWhereKeys(3, "uuid_user*");
			int i=0;
			List<User_Online> user_OnlineList = new ArrayList<User_Online>();
			for (String key : keyStrings) {
				String anchor_f_uuid = key.replace("uuid_user", "");
				long anchor_uid = iuserDao.getUserIdWithF_uuid(anchor_f_uuid);
				String anchor_token = iuserDao.getUserInfoExtraToken(anchor_uid);
				String anchor_state = RedisUtil.Gethget(0, anchor_token, "anchor_state");
				if(anchor_state.equals("1"))
				{
					Set<String> userKeyStrings = RedisUtil.GetZrevrangeJedis(3, key, 0, 10000000);
					for (String userKey : userKeyStrings) {
						String uidString = RedisUtil.Gethget(0, userKey, "uid");
						String nicknameString = RedisUtil.Gethget(0, userKey, "nickname");
						String f_uuidString = RedisUtil.Gethget(0, userKey, "f_uuid");
						if(nicknameString==null)
							nicknameString = "";
						if((!StringUtils.isBlank(uid)&&uid.equals(uidString))||(!StringUtils.isBlank(f_uuid)&&f_uuid.equals(f_uuidString))||(!StringUtils.isBlank(nickname)&&nicknameString.contains(nickname))||(StringUtils.isBlank(uid)&&StringUtils.isBlank(nickname)&&StringUtils.isBlank(f_uuid)))
						{
							i++;
							if(i>(page-1)*20&&i<page*20)
							{
								User_Online user_Online = new User_Online();
								user_Online.setNickname(nicknameString);
								user_Online.setUid(uidString);
								user_Online.setF_uuid(f_uuidString);
								user_Online.setLevel(RedisUtil.Gethget(0, userKey, "level"));
								user_Online.setSex(RedisUtil.Gethget(0, userKey, "sex"));
								user_Online.setIs_anchor(RedisUtil.Gethget(0, userKey, "is_anchor"));
								user_Online.setAnchor_nickname(RedisUtil.Gethget(0, anchor_token, "nickname"));
								user_OnlineList.add(user_Online);
							}
						}
					}
				}
			}
			int totalRecord = i;
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("useronline/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("userOnlineList", user_OnlineList);
			model.addAttribute("uid", uid);
			model.addAttribute("f_uuid", f_uuid);
			model.addAttribute("nickname", nickname);
			model.addAttribute("activeUrl", "useronline");
		} catch (Exception e) {
			log.error("用户在线列表", e);
		}
		return "useronline/list";
	}
}

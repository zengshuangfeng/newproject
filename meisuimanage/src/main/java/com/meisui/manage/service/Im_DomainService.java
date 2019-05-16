package com.meisui.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.entity.Im_Domain;

@Service
public class Im_DomainService {
	private static Logger log = Logger.getLogger(Im_DomainService.class.getClass());
	public String get(Model model)
	{
		try {
			Set<String> domainList = RedisUtil.GetWhereKeys(2, "*");
			List<Im_Domain> im_DomainList = new ArrayList<Im_Domain>();
			for (String domainModel : domainList) {
				String online_time = RedisUtil.Gethget(2, domainModel, "online_time");
				String online_user = RedisUtil.Gethget(2, domainModel, "online_user");
				String online_sort = RedisUtil.Gethget(2, domainModel, "online_sort");
				String online_f_uuids = RedisUtil.Gethget(2, domainModel, "online_f_uuids");
				String port = RedisUtil.Gethget(2, domainModel, "port");
				Im_Domain im_Domain = new Im_Domain();
				im_Domain.setOnline_time(online_time);
				im_Domain.setOnline_user(online_user);
				im_Domain.setOnline_sort(online_sort);
				im_Domain.setOnline_f_uuids(online_f_uuids);
				im_Domain.setPort(port);
				im_Domain.setDomain(domainModel);
				im_DomainList.add(im_Domain);
			}
			model.addAttribute("imDomainList", im_DomainList);
			model.addAttribute("activeUrl", "imdomain");

		} catch (Exception e) {
			log.error("im_domain", e);
		}
		return "imdomain/list";
	}
}

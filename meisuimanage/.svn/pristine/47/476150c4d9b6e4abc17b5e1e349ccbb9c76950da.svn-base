package com.meisui.manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meisui.manage.dao.UserVestDao;
import com.meisui.manage.entity.UserVest;
import com.meisui.manage.service.User_VestService;

@RequestMapping("/robot")
@Controller
public class RobotController {

	@Autowired
	private User_VestService user_VestService;
	
	@Autowired
	private UserVestDao userVestDao;
	
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	@ResponseBody
	public String getList(HttpServletRequest request) {	
		Random random = new Random(); 
		while(true){
			List<UserVest> list=userVestDao.getUserVestList(0);
			for(UserVest vest:list){
				user_VestService.saveInfo(0, vest.getNickname(), vest.getSex(),vest.getHead(),random.nextInt(3)+1, "", 0, 0, 0, "admin", request);
			}
			userVestDao.updateUserVestListByIds(list);
			if(null==list||list.size()<500){
				break;
			}
		}
		return "success";
	}
	
}

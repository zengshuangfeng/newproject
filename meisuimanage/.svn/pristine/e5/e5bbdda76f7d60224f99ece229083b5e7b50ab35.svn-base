package com.meisui.manage.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.forman.foundation.library.RedisUtil;
import com.meisui.manage.dao.IconfigDao;
import com.meisui.manage.service.CommonService;
import com.meisui.manage.utils.QiNiuUtil;



@Controller
@RequestMapping("/common")
public class CommonController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private IconfigDao iconfigDao;

	/***
	 * 
	 * <p>功能描述：上传图片</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 上午11:39:28</p>
	 *
	 * @param folder 七牛空间
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "f", required = false, defaultValue="")  String f,
			String folder, 
			MultipartHttpServletRequest request, HttpServletResponse response){

		return commonService.upload(folder, f, request, response);
	}
	@ResponseBody
	@RequestMapping(value = "/uploadvideo", method = RequestMethod.POST)
	public String uploadVideo(@RequestParam(value = "f", required = false, defaultValue="")  String f,
			String folder, 
			MultipartHttpServletRequest request, HttpServletResponse response){

		return commonService.uploadVideo(folder, f, request, response);
	}
	/***
	 * 
	 * <p>功能描述：文本编辑器上传图片</p>
	 * <p>创建人：</p>
	 * <p>创建日期：2015年12月30日 上午11:39:55</p>
	 *
	 * @param folder 七牛空间
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/editorupload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void editorUpload(String folder, MultipartHttpServletRequest request, HttpServletResponse response){    

		commonService.editorUpload(folder, request, response);
	}	
	/*@ResponseBody
	@RequestMapping(value = "/size", method = RequestMethod.GET)
	public String size()
	{
		commonService.setVideoSize();
		return "success";
	}*/
	/*@ResponseBody
	@RequestMapping(value = "/water", method = RequestMethod.GET)
	public String water()
	{
		QiNiuUtil qiNiuUtil = new QiNiuUtil();
		qiNiuUtil.setWaterMark("40575d9458e04d7aa6227f274e24da52.mp4");
		return "success";
	}*/
	/*@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test()
	{
		commonService.test(209518L);
		return "success";
	}*/
	/*@ResponseBody
	@RequestMapping(value = "/updatelevel", method = RequestMethod.GET)
	public String updatelevel()
	{
		commonService.updateLevel();
		return "success";
	}*/
	/*	@ResponseBody
	@RequestMapping(value = "/sethead", method = RequestMethod.GET)
	public String setHead()
	{
		commonService.setHead();
		return "success";
	}*/
	/*@ResponseBody
	@RequestMapping(value = "/total", method = RequestMethod.GET)
	public String total(@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time)
	{
		commonService.total(start_time, end_time);
		return "success";
	}*/
	/*@RequestMapping(value = "/exporttrival", method = RequestMethod.GET)
	public String exportTrival(@RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
			@RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time,HttpServletResponse response) throws IOException, ParseException {	
		return commonService.getTrivalAnchor(start_time, end_time, response);
	}*/
	/*@ResponseBody
	@RequestMapping(value = "/setcode", method = RequestMethod.GET)
	public String setCode()
	{
		commonService.setCode();
		return "success";
	}*/
	@ResponseBody
	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public String setData(@RequestParam(value = "key", required = false,defaultValue="") String mapkey)
	{
		Set<String> keyList = RedisUtil.GetWhereKeys(0, "*sms*");
		for (String key : keyList) {
			try {
				Map<String, String> map = RedisUtil.Gethgetall(0, key);
				if(map.size()>5)
				{
					System.out.println(key);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			/*for (Map.Entry<String, String> entry : map.entrySet()) {
				if(entry.getKey().equals(mapkey))
				{
					System.out.println(key);
				}
			}*/
		}
		System.out.println(keyList.size());
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "/set2", method = RequestMethod.GET)
	public String setData2(@RequestParam(value = "key", required = false,defaultValue="") String mapkey)
	{
		Set<String> keyList = RedisUtil.GetWhereKeys(0, "*sms*");
		for (String key : keyList) {
			try {
				Map<String, String> map = RedisUtil.Gethgetall(0, key);

				for (Map.Entry<String, String> entry : map.entrySet()) {
					if(entry.getKey().equals(mapkey))
					{
						System.out.println(key);
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//System.out.println(keyList.size());
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String test()
	{
		
		return "success";
	}
	/*@ResponseBody
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public String test2()
	{
		Set<String> keyList = RedisUtil.GetWhereKeys(8, "*");
		for (String key : keyList) {
			try {
				System.out.println(key);
				RedisUtil.del(8, key);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//System.out.println(keyList.size());
		return "success";
	}*/
	/*@ResponseBody
	@RequestMapping(value = "/test5", method = RequestMethod.GET)
	public String test2()
	{
		String[] strings = new String[] {"a","b","c","d","e","f","g","h","k","m","n","p","q","r","s","t","u","v","w","x","y","3","4","5","6","7","8","9"};
		for(int i=0;i<32000;i++)
		{
			String invite = "";
			for(int j=0;j<5;j++)
			{
				Random random = new Random();
				String str_invite = strings[random.nextInt(strings.length)];
				invite = invite.concat(str_invite);
			}
			if(iconfigDao.getInviteMakeExist(invite)==null)
			{
				iconfigDao.insertInviteMake(invite, 1);
				RedisUtil.lpush(4, "invite_make", invite);
			}
		}
		//System.out.println(keyList.size());
		return "success";
	}*/
}

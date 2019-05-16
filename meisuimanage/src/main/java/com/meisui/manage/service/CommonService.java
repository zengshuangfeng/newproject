package com.meisui.manage.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.forman.foundation.library.RedisUtil;
import com.forman.log4j.Log4jHandel;
import com.meisui.manage.dao.Ianchor_PayDao;
import com.meisui.manage.dao.Ianchor_TimeDao;
import com.meisui.manage.dao.Ianchor_UnionDao;
import com.meisui.manage.dao.IconfigDao;
import com.meisui.manage.dao.IprofitDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Anchor_Time;
import com.meisui.manage.entity.Anchor_Union;
import com.meisui.manage.entity.Anchor_Week_Export;
import com.meisui.manage.entity.Pic_Upload;
import com.meisui.manage.entity.Uid_Make;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.PropertyUtil;
import com.meisui.manage.utils.QiNiuUtil;

import net.sf.json.JSONObject;

/***
 * 
 * <p>
 * 文件名称：Common_Service.java
 * </p>
 * <p>
 * 文件描述：公共部分相关操作
 * </p>
 * <p>
 * 版权所有： 版权所有(C)2013-2099
 * </p>
 * <p>
 * 公 司： 每美
 * </p>
 * <p>
 * 内容摘要：
 * </p>
 * <p>
 * 其他说明：
 * </p>
 *
 * @version 1.0
 * @author <a> href="mailto:@vmei.me"></a>
 * @since 2015年12月30日 上午11:42:06
 */
@Service
public class CommonService {
	private static Logger log = Logger.getLogger(CommonService.class.getClass());
	@Autowired
	private IuserDao iuserDao;
	@Autowired
	private IconfigDao iconfigDao;
	@Autowired
	private Ianchor_UnionDao ianchor_UnionDao;
	@Autowired
	private IprofitDao iprofitDao;
	@Autowired
	private Ianchor_PayDao ianchor_PayDao;
	@Autowired
	private Ianchor_TimeDao ianchor_TimeDao;

	/***
	 * 
	 * <p>
	 * 功能描述：上传图片
	 * </p>
	 * <p>
	 * 创建人：
	 * </p>
	 * <p>
	 * 创建日期：2015年12月30日 上午11:39:28
	 * </p>
	 *
	 * @param folder
	 *            七牛空间
	 * @param request
	 * @param response
	 * @return
	 */
	public String upload(String folder, String f, MultipartHttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = null;
			String name = "";
			while (itr.hasNext()) {
				file = request.getFile(itr.next());
				name = file.getOriginalFilename();
				String newFilenameBase = UUID.randomUUID().toString().replace("-", "");
				String originalFileExtension = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf("."));
				String newFilename = newFilenameBase + originalFileExtension;
				if (!StringUtils.isBlank(f)) {
					if (f.startsWith(","))
						f.substring(1);
					newFilename = (f.contains(".") ? f.substring(0, f.lastIndexOf(".")) : f) + ".jpg";
				}
				folder = folder.replace("/", "");
				QiNiuUtil qiNiuUtil = new QiNiuUtil();
				Log4jHandel.myinfo(folder);
				qiNiuUtil.upLoadFile(newFilename, file, folder);
				jsonObject.put("src", PropertyUtil.getValue("meisui_pic_url") + newFilename);
				jsonObject.put("oname", name);
				jsonObject.put("nname", newFilename);
			}
		} catch (Exception e) {
			log.error("上传图片异常", e);
			jsonObject.put("e", e.getMessage());
		}
		result = jsonObject.toString();
		return result;
	}

	public String uploadVideo(String folder, String f, MultipartHttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = null;
			String name = "";
			while (itr.hasNext()) {
				file = request.getFile(itr.next());
				name = file.getOriginalFilename();
				String newFilenameBase = "meisui_" + UUID.randomUUID().toString().replace("-", "");
				String originalFileExtension = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf("."));
				String newFilename = newFilenameBase + originalFileExtension;
				if (!StringUtils.isBlank(f)) {
					if (f.startsWith(","))
						f.substring(1);
					newFilename = (f.contains(".") ? f.substring(0, f.lastIndexOf(".")) : f);
				}
				folder = folder.replace("/", "");
				QiNiuUtil qiNiuUtil = new QiNiuUtil();
				qiNiuUtil.upLoadFile(newFilename, file, folder);
				jsonObject.put("src", PropertyUtil.getValue("meisui_pic_url") + newFilename);
				jsonObject.put("oname", name);
				jsonObject.put("nname", newFilename);
			}
		} catch (Exception e) {
			log.error("上传视频异常", e);
			jsonObject.put("e", e.getMessage());
		}
		result = jsonObject.toString();
		return result;
	}

	/***
	 * 
	 * <p>
	 * 功能描述：文本编辑器上传图片
	 * </p>
	 * <p>
	 * 创建人：
	 * </p>
	 * <p>
	 * 创建日期：2015年12月30日 上午11:39:55
	 * </p>
	 *
	 * @param folder
	 *            七牛空间
	 * @param request
	 * @param response
	 */
	public void editorUpload(String folder, MultipartHttpServletRequest request, HttpServletResponse response) {
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = null;
			String result = "";
			while (itr.hasNext()) {
				file = request.getFile(itr.next());
				String newFilenameBase = UUID.randomUUID().toString().replace("-", "");
				String originalFileExtension = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf("."));
				String newFilename = newFilenameBase + originalFileExtension;

				QiNiuUtil qiNiuUtil = new QiNiuUtil();
				qiNiuUtil.upLoadFile(newFilename, file, folder);
				String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
				result = PropertyUtil.getValue(folder + "_pic_url") + newFilename;
				result = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("
						.concat(CKEditorFuncNum).concat(", '").concat(result).concat("', '');</script>");

				PrintWriter out = response.getWriter();
				out.print(result);
				out.flush();
			}
		} catch (Exception e) {
			log.error("编辑器上传图片异常", e);
		}
	}

	public void test(long id) {
		List<Uid_Make> uid_MakeList = iuserDao.getUIdMakeList(id);
		long lastid = 0;

		for (Uid_Make uid_Make : uid_MakeList) {
			RedisUtil.lpush(4, "uid_make", String.valueOf(uid_Make.getUid_makeamends()));
			lastid = uid_Make.getId();
		}
		/*
		 * System.out.println(lastid); if(uid_MakeList.size()>0) test(lastid);
		 */
	}

	public void updateLevel() {
		/*
		 * List<User> users = iconfigDao.getUserList(); for (User user : users) { try {
		 * long score = iconfigDao.getScoreWithLevel(user.getLevel()); score = score+0;
		 * iuserDao.updateUserScore(score, user.getId()); String token =
		 * iuserDao.getUserInfoExtraToken(user.getId()); if(!StringUtils.isBlank(token))
		 * { RedisUtil.SetHsetJedis(0, token, "level", String.valueOf(user.getLevel()));
		 * RedisUtil.SetHsetJedis(0, token, "score", String.valueOf(score));
		 * System.out.println(RedisUtil.Gethget(0, token, "level")); }
		 * 
		 * } catch (Exception e) { log.info(user.getId()); } }
		 */
	}

	public void setHead() {
		QiNiuUtil qiNiuUtil = new QiNiuUtil();
		/*
		 * List<User> userList = iconfigDao.getHeadUserList(); for (User user :
		 * userList) { String imageString = qiNiuUtil.upload("peipeipic",
		 * user.getHead()); if(!imageString.equals(user.getHead()))
		 * iconfigDao.insertPicUpload(user.getId(), user.getHead(), imageString); }
		 */
		/* List<User> userList = iconfigDao.getVestUserList(); */
		List<Pic_Upload> pic_UploadList = iconfigDao.getPicUploadList();
		for (Pic_Upload pic_Upload : pic_UploadList) {
			iconfigDao.updateUserHead(pic_Upload.getOld_pic(), pic_Upload.getNew_pic());
			String token = iuserDao.getUserInfoExtraToken(pic_Upload.getUid());
			if (!StringUtils.isBlank(token))
				RedisUtil.SetHsetJedis(0, token, "head", pic_Upload.getNew_pic());
		}
		/*
		 * List<User> userVestList = iconfigDao.getUserVestList(); for (User user :
		 * userVestList) { user.setHead(user.getHead().replace("./",
		 * "http://imageoss.dccentury.com/")); String imageString =
		 * qiNiuUtil.upload("peipeipic", user.getHead());
		 * if(!imageString.equals(user.getHead()))
		 * iconfigDao.updateVestUserHead(user.getId(), imageString); }
		 */
	}

	public void total(Date start_time, Date end_time) {
		List<Long> uidList = iconfigDao.getProfitUidList(start_time, end_time);
		for (Long uid : uidList) {
			User user = iuserDao.getUser(uid);
			User_Anchor user_Anchor = iuserDao.getUserAnchor(uid);
			Anchor_Week_Export anchor_Week_Export = new Anchor_Week_Export();
			anchor_Week_Export.setUid(uid);
			anchor_Week_Export.setF_uuid(Long.parseLong(user.getF_uuid()));
			anchor_Week_Export.setNickname(user.getNickname());
			Anchor_Union anchor_Union = ianchor_UnionDao.getAnchorUnion(user_Anchor.getUnion_id());
			if (anchor_Union != null)
				anchor_Week_Export.setUnion(anchor_Union.getName());
			for (int i = 0; i < 7; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(start_time);
				calendar.add(Calendar.DATE, i);
				Date current_s_time = calendar.getTime();

				Calendar calendar2 = new GregorianCalendar();
				calendar2.setTime(start_time);
				calendar2.add(Calendar.DATE, i + 1);
				Date current_e_time = calendar2.getTime();

				Long total_anchor_virtual = iprofitDao.getProfitSum(uid, current_s_time, current_e_time);
				if (total_anchor_virtual == null)
					total_anchor_virtual = 0L;
				anchor_Week_Export.setTotal_anchor_virtual(total_anchor_virtual);

				Long total_stake = ianchor_PayDao.getUserTotalStake(anchor_Week_Export.getF_uuid(),
						current_s_time.getTime(), current_e_time.getTime());
				if (total_stake == null)
					total_stake = 0L;
				anchor_Week_Export.setTotal_stake(total_stake);
				long millminute = 0L;
				List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList(uid, 0, current_s_time,
						current_e_time, 0, 10000);
				for (Anchor_Time anchor_Time : anchor_TimeList) {
					if (anchor_Time.getStart_time().before(current_s_time))
						anchor_Time.setStart_time(current_s_time);
					if (anchor_Time.getEnd_time().after(current_e_time))
						anchor_Time.setEnd_time(current_e_time);
					millminute += anchor_Time.getEnd_time().getTime() - anchor_Time.getStart_time().getTime();
				}
				anchor_Week_Export.setTotal_time(getTotalTime(millminute));
				anchor_Week_Export.setStart_time(current_s_time);
				anchor_Week_Export.setEnd_time(current_e_time);
				anchor_Week_Export.setCreate_time(new Date());
				iconfigDao.insertAnchorWeekExport(anchor_Week_Export);
			}
		}
	}

	private String getTotalTime(long millminute) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;

		Long hour = millminute / hh;
		Long minute = (millminute - hour * hh) / mi;
		Long second = (millminute - hour * hh - minute * mi) / ss;
		StringBuffer sbBuffer = new StringBuffer();
		if (hour > 0) {
			sbBuffer.append(hour);
			sbBuffer.append("小时");
		}
		if (minute > 0) {
			sbBuffer.append(minute);
			sbBuffer.append("分钟");
		}
		if (second > 0) {
			sbBuffer.append(second);
			sbBuffer.append("秒");
		}
		return sbBuffer.toString();
	}

	public String getTrivalAnchor(Date start_time, Date end_time, HttpServletResponse response)
			throws IOException, ParseException {
		ServletOutputStream outputStream = response.getOutputStream();
		Date date = new Date();

		String fileName = new String(("06-12~06-18试播主播").getBytes(), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("sheet1");
		ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		String[] titles = new String[] { "主播UID", "主播房间号", "主播昵称", "所属家族", "试播时间", "试播时长" };
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		List<User_Anchor> user_AnchorList = iconfigDao.getTrialAnchorList(start_time, end_time);
		int j = 0;
		for (User_Anchor user_Anchor : user_AnchorList) {
			List<Anchor_Time> anchor_TimeList = ianchor_TimeDao.getAnchorTimeList2(user_Anchor.getUid(), 0, start_time,
					end_time);
			String dateString = "";
			long totalmillminute = 0L;
			for (Anchor_Time anchor_Time : anchor_TimeList) {
				long millminute = anchor_Time.getEnd_time().getTime() - anchor_Time.getStart_time().getTime();
				String currentdateString = AuthUtil.formatDateToString(anchor_Time.getStart_time(), "MM月dd日");
				if (StringUtils.isBlank(dateString))
					dateString = currentdateString;
				if (currentdateString.equals(dateString))
					totalmillminute += millminute;
			}
			XSSFRow bodyRow = sheet.createRow(j + 1);

			cell = bodyRow.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(user_Anchor.getUid());

			cell = bodyRow.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(user_Anchor.getF_uuid());

			cell = bodyRow.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(user_Anchor.getNickname());

			cell = bodyRow.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(user_Anchor.getUnion_name());

			cell = bodyRow.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(dateString);

			cell = bodyRow.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(getTotalTime(totalmillminute));

			j++;
		}
		try {
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setCode() {/*
							 * for (int i = 100001; i < 999999; i++) {
							 * if(i!=111111&&i!=222222&&i!=333333&&i!=444444&&i!=555555&&i!=666666&&i!=
							 * 777777&&i!=888888) iconfigDao.insertAnchorCode(String.valueOf(i)); }
							 */
	}

	public void setData() {
		List<Long> uidList = iconfigDao.getTotalFlowUidList();
		for (Long uid : uidList) {
			long banlance = iconfigDao.getBalance(uid);
			long banlance2 = 0L;
			List<Long> flowList = iconfigDao.getTotalFlowWithUid(uid, "游戏押注");
			for (Long flow : flowList) {
				banlance2 += flow;
			}
			flowList = iconfigDao.getTotalFlowWithUid(uid, "游戏获胜");
			for (Long flow : flowList) {
				banlance2 -= flow;
			}
			flowList = iconfigDao.getTotalFlowWithUid(uid, "充值");
			for (Long flow : flowList) {
				if (flow == 999800)
					banlance2 -= 999700;
			}
			flowList = iconfigDao.getTotalFlowWithUid(uid, "在线领取");
			for (Long flow : flowList) {
				banlance2 -= flow;
			}
			flowList = iconfigDao.getTotalFlowWithUid(uid, "送礼");
			for (Long flow : flowList) {
				banlance2 += flow;
			}
			flowList = iconfigDao.getTotalFlowWithUid(uid, "每日签到");
			for (Long flow : flowList) {
				banlance2 -= flow;
			}
			flowList = iconfigDao.getTotalFlowWithUid(uid, "任务领取");
			for (Long flow : flowList) {
				banlance2 -= flow;
			}
			List<Long> aaList = iconfigDao.getBalanceRecordList(uid);
			for (Long aa : aaList) {
				banlance2 -= aa;
			}
			// iconfigDao.insertTestBanlance(uid, banlance, banlance2);
		}
	}
}

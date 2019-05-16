package com.meisui.manage.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.meisui.manage.dao.Iactivity_AnnualrDao;
import com.meisui.manage.dao.IchangeDao;
import com.meisui.manage.dao.Imanage_RecordDao;
import com.meisui.manage.dao.IuserDao;
import com.meisui.manage.entity.Activity_Annual;
import com.meisui.manage.entity.Activity_Annual_Discount;
import com.meisui.manage.entity.Activity_Annual_Login;
import com.meisui.manage.entity.Activity_Annual_Prize;
import com.meisui.manage.entity.Activity_Annual_Prize_User;
import com.meisui.manage.entity.Change;
import com.meisui.manage.entity.MoneyTree;
import com.meisui.manage.entity.Recharge;
import com.meisui.manage.entity.Recharge_Quota_Virtual;
import com.meisui.manage.entity.User;
import com.meisui.manage.utils.AuthUtil;
import com.meisui.manage.utils.ExcelUtil;
import com.meisui.manage.utils.IPUtil;
import com.meisui.manage.utils.PageUtil;
@Service
public class Activity_AnnualService {
	private static Logger log = Logger.getLogger(Activity_AnnualService.class.getClass());
		@Autowired
		private  Iactivity_AnnualrDao iactivity_annualDao;
		@Autowired
		private IuserDao iuserdao;
		@Autowired
		private Imanage_RecordDao imanage_RecordDao;
		@Autowired
		private IchangeDao ichangedao;
		
		@SuppressWarnings("unused")
		public String getActivityAnnualList(int type,Date s_time,Date e_time, int page, Model model) {
		try {
			int totalRecord = 0;
			String e_timeString = e_time != null ? AuthUtil.formatDateToString(e_time, "yyyy-MM-dd") : "";
			if (e_time != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(e_time);
				calendar.add(Calendar.DATE, 1);
				e_time = calendar.getTime();
			}
			
			if (type == -1)
				type = 0;
			if(type==0){
				
				ArrayList<MoneyTree> list=new ArrayList<MoneyTree>();
				
				//23号
					MoneyTree moneytree=new MoneyTree();	
					String statetime="2019-01-23 20:00:00";
					String endtime="2019-01-23 23:59:59";						
					int recharge1=iactivity_annualDao.getAllRecharge(statetime,endtime);//充值金额
					int  balance1=iactivity_annualDao.getAllBalanceVirtual(statetime,endtime);//赠送时充值金额
					int allrecharge1=recharge1+balance1;//总充值金额
					int allbalance_virtual1=iactivity_annualDao.getAllPrizecount(statetime,endtime,1)*88;//支出钻石总额
					int pigcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,2);//支出小金猪个数
					int yguardcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,3);//月支出守护个数
					int jguardcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,4);//季支出守护个数
					int nguardcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,5);//年支出守护个数
					int allguardcount1=yguardcount1+jguardcount1+nguardcount1;//支出守护个数
					int travelcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,6);//支出旅游券个数
					int moneycount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,7);//支出现金个数				
					moneytree.setAllbalance_virtual(allbalance_virtual1);
					moneytree.setAllrecharge(allrecharge1);
					moneytree.setGuardcount(allguardcount1);
					moneytree.setMoneycount(moneycount1);
					moneytree.setPigcount(pigcount1);
					moneytree.setTravelcount(travelcount1);
					moneytree.setActivitytime("2019-01-23 20:00 - 24:00");
					List<Activity_Annual> activityannual=iactivity_annualDao.getActivityAnnual("2019-01-23");
					moneytree.setActivity_annual(activityannual);
					moneytree.setStatetime(statetime);
					moneytree.setEndtime(endtime);
					list.add(moneytree);
			//24号		
					MoneyTree moneytree2=new MoneyTree();
					String statetime2="2019-01-24 20:00:00";
					String endtime2="2019-01-24 23:59:59";						
					int recharge2=iactivity_annualDao.getAllRecharge(statetime2,endtime2);//充值金额
					int  balance2=iactivity_annualDao.getAllBalanceVirtual(statetime2,endtime2);//赠送时充值金额
					int allrecharge2=recharge2+balance2;//总充值金额
					int allbalance_virtual2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,1)*88;//支出钻石总额
					int pigcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,2);//支出小金猪个数
					int yguardcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,3);//月支出守护个数
					int jguardcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,4);//季支出守护个数
					int nguardcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,5);//年支出守护个数
					int allguardcount2=yguardcount2+jguardcount2+nguardcount2;//支出守护个数
					int travelcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,6);//支出旅游券个数
					int moneycount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,7);//支出现金个数
					moneytree2.setAllbalance_virtual(allbalance_virtual2);
					moneytree2.setAllrecharge(allrecharge2);
					moneytree2.setGuardcount(allguardcount2);
					moneytree2.setMoneycount(moneycount2);
					moneytree2.setPigcount(pigcount2);
					moneytree2.setTravelcount(travelcount2);	
					moneytree2.setActivitytime("2019-01-24 20:00 - 24:00");
					List<Activity_Annual> activityannual2=iactivity_annualDao.getActivityAnnual("2019-01-24");
					moneytree2.setActivity_annual(activityannual2);
					moneytree2.setStatetime(statetime2);
					moneytree2.setEndtime(endtime2);
					list.add(moneytree2);
					//25号		
					MoneyTree moneytree3=new MoneyTree();
					String statetime3="2019-01-25 20:00:00";
					String endtime3="2019-01-25 23:59:59";						
					int recharge3=iactivity_annualDao.getAllRecharge(statetime3,endtime3);//充值金额
					int  balance3=iactivity_annualDao.getAllBalanceVirtual(statetime3,endtime3);//赠送时充值金额
					int allrecharge3=recharge3+balance3;//总充值金额
					int allbalance_virtual3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,1)*88;//支出钻石总额
					int pigcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,2);//支出小金猪个数
					int yguardcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,3);//月支出守护个数
					int jguardcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,4);//季支出守护个数
					int nguardcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,5);//年支出守护个数
					int allguardcount3=yguardcount3+jguardcount3+nguardcount3;//支出守护个数
					int travelcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,6);//支出旅游券个数
					int moneycount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,7);//支出现金个数
					moneytree3.setAllbalance_virtual(allbalance_virtual3);
					moneytree3.setAllrecharge(allrecharge3);
					moneytree3.setGuardcount(allguardcount3);
					moneytree3.setMoneycount(moneycount3);
					moneytree3.setPigcount(pigcount3);
					moneytree3.setTravelcount(travelcount3);	
					moneytree3.setActivitytime("2019-01-25 20:00 - 24:00");
					List<Activity_Annual> activityannual3=iactivity_annualDao.getActivityAnnual("2019-01-25");
					moneytree3.setActivity_annual(activityannual3);
					moneytree3.setStatetime(statetime3);
					moneytree3.setEndtime(endtime3);
					list.add(moneytree3);
					//26号		
					MoneyTree moneytree4=new MoneyTree();
					String statetime4="2019-01-26 20:00:00";
					String endtime4="2019-01-26 23:59:59";						
					int recharge4=iactivity_annualDao.getAllRecharge(statetime4,endtime4);//充值金额
					int  balance4=iactivity_annualDao.getAllBalanceVirtual(statetime4,endtime4);//赠送时充值金额
					int allrecharge4=recharge4+balance4;//总充值金额
					int allbalance_virtual4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,1)*88;//支出钻石总额
					int pigcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,2);//支出小金猪个数
					int yguardcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,3);//月支出守护个数
					int jguardcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,4);//季支出守护个数
					int nguardcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,5);//年支出守护个数
					int allguardcount4=yguardcount4+jguardcount4+nguardcount4;//支出守护个数
					int travelcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,6);//支出旅游券个数
					int moneycount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,7);//支出现金个数
					moneytree4.setAllbalance_virtual(allbalance_virtual4);
					moneytree4.setAllrecharge(allrecharge4);
					moneytree4.setGuardcount(allguardcount4);
					moneytree4.setMoneycount(moneycount4);
					moneytree4.setPigcount(pigcount4);
					moneytree4.setTravelcount(travelcount4);	
					moneytree4.setActivitytime("2019-01-26 20:00 - 24:00");
					List<Activity_Annual> activityannual4=iactivity_annualDao.getActivityAnnual("2019-01-26");
					moneytree4.setActivity_annual(activityannual4);
					moneytree4.setStatetime(statetime4);
					moneytree4.setEndtime(endtime4);
					list.add(moneytree4);
					//27号		
					MoneyTree moneytree5=new MoneyTree();
					String statetime5="2019-01-27 20:00:00";
					String endtime5="2019-01-27 23:59:59";						
					int recharge5=iactivity_annualDao.getAllRecharge(statetime5,endtime5);//充值金额
					int  balance5=iactivity_annualDao.getAllBalanceVirtual(statetime4,endtime4);//赠送时充值金额
					int allrecharge5=recharge5+balance5;//总充值金额
					int allbalance_virtual5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,1)*88;//支出钻石总额
					int pigcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,2);//支出小金猪个数
					int yguardcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,3);//月支出守护个数
					int jguardcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,4);//季支出守护个数
					int nguardcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,5);//年支出守护个数
					int allguardcount5=yguardcount5+jguardcount5+nguardcount5;//支出守护个数
					int travelcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,6);//支出旅游券个数
					int moneycount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,7);//支出现金个数
					moneytree5.setAllbalance_virtual(allbalance_virtual5);
					moneytree5.setAllrecharge(allrecharge5);
					moneytree5.setGuardcount(allguardcount5);
					moneytree5.setMoneycount(moneycount5);
					moneytree5.setPigcount(pigcount5);
					moneytree5.setTravelcount(travelcount5);	
					moneytree5.setActivitytime("2019-01-27 20:00 - 24:00");
					List<Activity_Annual> activityannual5=iactivity_annualDao.getActivityAnnual("2019-01-27");
					moneytree5.setActivity_annual(activityannual5);
					moneytree5.setStatetime(statetime5);
					moneytree5.setEndtime(endtime5);
					list.add(moneytree5);
					//28号		
					MoneyTree moneytree6=new MoneyTree();
					String statetime6="2019-01-28 20:00:00";
					String endtime6="2019-01-28 23:59:59";						
					int recharge6=iactivity_annualDao.getAllRecharge(statetime6,endtime6);//充值金额
					int  balance6=iactivity_annualDao.getAllBalanceVirtual(statetime6,endtime6);//赠送时充值金额
					int allrecharge6=recharge6+balance6;//总充值金额
					int allbalance_virtual6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,1)*88;//支出钻石总额
					int pigcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,2);//支出小金猪个数
					int yguardcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,3);//月支出守护个数
					int jguardcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,4);//季支出守护个数
					int nguardcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,5);//年支出守护个数
					int allguardcount6=yguardcount6+jguardcount6+nguardcount6;//支出守护个数
					int travelcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,6);//支出旅游券个数
					int moneycount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,7);//支出现金个数
					moneytree6.setAllbalance_virtual(allbalance_virtual6);
					moneytree6.setAllrecharge(allrecharge6);
					moneytree6.setGuardcount(allguardcount6);
					moneytree6.setMoneycount(moneycount6);
					moneytree6.setPigcount(pigcount6);
					moneytree6.setTravelcount(travelcount6);	
					moneytree6.setActivitytime("2019-01-28 20:00 - 24:00");
					List<Activity_Annual> activityannual6=iactivity_annualDao.getActivityAnnual("2019-01-28");
					moneytree6.setActivity_annual(activityannual6);
					moneytree6.setStatetime(statetime6);
					moneytree6.setEndtime(endtime6);
					list.add(moneytree6);
					//29号		
					MoneyTree moneytree7=new MoneyTree();
					String statetime7="2019-01-29 20:00:00";
					String endtime7="2019-01-29 23:59:59";						
					int recharge7=iactivity_annualDao.getAllRecharge(statetime7,endtime7);//充值金额
					int  balance7=iactivity_annualDao.getAllBalanceVirtual(statetime7,endtime7);//赠送时充值金额
					int allrecharge7=recharge7+balance7;//总充值金额
					int allbalance_virtual7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,1)*88;//支出钻石总额
					int pigcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,2);//支出小金猪个数
					int yguardcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,3);//月支出守护个数
					int jguardcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,4);//季支出守护个数
					int nguardcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,5);//年支出守护个数
					int allguardcount7=yguardcount7+jguardcount7+nguardcount7;//支出守护个数
					int travelcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,6);//支出旅游券个数
					int moneycount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,7);//支出现金个数
					moneytree7.setAllbalance_virtual(allbalance_virtual7);
					moneytree7.setAllrecharge(allrecharge7);
					moneytree7.setGuardcount(allguardcount7);
					moneytree7.setMoneycount(moneycount7);
					moneytree7.setPigcount(pigcount7);
					moneytree7.setTravelcount(travelcount7);
					moneytree7.setActivitytime("2019-01-29 20:00 - 24:00");
					List<Activity_Annual> activityannual7=iactivity_annualDao.getActivityAnnual("2019-01-29");
					moneytree7.setActivity_annual(activityannual7);
					moneytree7.setStatetime(statetime7);
					moneytree7.setEndtime(endtime7);
					list.add(moneytree7);
					//30号		
					MoneyTree moneytree8=new MoneyTree();
					String statetime8="2019-01-30 20:00:00";
					String endtime8="2019-01-30 23:59:59";						
					int recharge8=iactivity_annualDao.getAllRecharge(statetime8,endtime8);//充值金额
					int  balance8=iactivity_annualDao.getAllBalanceVirtual(statetime8,endtime8);//赠送时充值金额
					int allrecharge8=recharge8+balance8;//总充值金额
					int allbalance_virtual8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,1)*88;//支出钻石总额
					int pigcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,2);//支出小金猪个数
					int yguardcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,3);//月支出守护个数
					int jguardcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,4);//季支出守护个数
					int nguardcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,5);//年支出守护个数
					int allguardcount8=yguardcount8+jguardcount8+nguardcount8;//支出守护个数
					int travelcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,6);//支出旅游券个数
					int moneycount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,7);//支出现金个数
					moneytree8.setAllbalance_virtual(allbalance_virtual8);
					moneytree8.setAllrecharge(allrecharge8);
					moneytree8.setGuardcount(allguardcount8);
					moneytree8.setMoneycount(moneycount8);
					moneytree8.setPigcount(pigcount8);
					moneytree8.setTravelcount(travelcount8);	
					moneytree8.setActivitytime("2019-01-30 20:00 - 24:00");
					List<Activity_Annual> activityannual8=iactivity_annualDao.getActivityAnnual("2019-01-30");
					moneytree8.setActivity_annual(activityannual8);
					moneytree8.setStatetime(statetime8);
					moneytree8.setEndtime(endtime8);
					list.add(moneytree8);
					//31号		
					MoneyTree moneytree9=new MoneyTree();
					String statetime9="2019-01-31 20:00:00";
					String endtime9="2019-01-31 23:59:59";						
					int recharge9=iactivity_annualDao.getAllRecharge(statetime9,endtime9);//充值金额
					int  balance9=iactivity_annualDao.getAllBalanceVirtual(statetime9,endtime9);//赠送时充值金额
					int allrecharge9=recharge9+balance9;//总充值金额
					int allbalance_virtual9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,1)*88;//支出钻石总额
					int pigcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,2);//支出小金猪个数
					int yguardcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,3);//月支出守护个数
					int jguardcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,4);//季支出守护个数
					int nguardcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,5);//年支出守护个数
					int allguardcount9=yguardcount9+jguardcount9+nguardcount9;//支出守护个数
					int travelcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,6);//支出旅游券个数
					int moneycount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,7);//支出现金个数
					moneytree9.setAllbalance_virtual(allbalance_virtual9);
					moneytree9.setAllrecharge(allrecharge9);
					moneytree9.setGuardcount(allguardcount9);
					moneytree9.setMoneycount(moneycount9);
					moneytree9.setPigcount(pigcount9);
					moneytree9.setTravelcount(travelcount9);
					moneytree9.setActivitytime("2019-01-31 20:00 - 24:00");
					List<Activity_Annual> activityannual9=iactivity_annualDao.getActivityAnnual("2019-01-31");
					moneytree9.setActivity_annual(activityannual9);
					moneytree9.setStatetime(statetime9);
					moneytree9.setEndtime(endtime9);
					list.add(moneytree9);
				
				model.addAttribute("activityuserlist", list);
			
			}else if(type==1){			
				List<Activity_Annual_Login> activityList = iactivity_annualDao.getActivityAnnualList(s_time,e_time, (page-1)*20, 20);
				for(Activity_Annual_Login activityannual:activityList){			
					User user=iuserdao.getUser((long)activityannual.getUid());
					if(user !=null){
						activityannual.setNickname(user.getNickname());
						activityannual.setF_uuid(user.getF_uuid());
					}		
					Activity_Annual_Discount discount=iactivity_annualDao.getActivityAnnualDiscount(activityannual.getUid());
					Activity_Annual_Discount annualdiscount=new Activity_Annual_Discount();
					
					if(discount !=null){//折扣
						annualdiscount.setDiscount(discount.getDiscount());
						//判断是否使用了折扣				
						Recharge recharge=iactivity_annualDao.getRechargeLimitone(discount.getCreate_time(),discount.getUid());
						
						if(recharge !=null ){//充值了
							Change change=ichangedao.getChange(recharge.getChange_id());
							if(recharge.getRecharge_rmb()<change.getChange_rmb()){//则说明使用了折扣卡
								annualdiscount.setIs_use(1);
								annualdiscount.setRecharge_rmb(recharge.getRecharge_rmb());//折后充值金额
								DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  					
								annualdiscount.setRecharge_time(format.parse(recharge.getCreate_time()));//折后充值时间
							}else{//反之，没使用
								annualdiscount.setIs_use(0);
							}
							activityannual.setAnnualdiscount(annualdiscount);
						}else{//没有充值
							annualdiscount.setIs_use(0);
							activityannual.setAnnualdiscount(annualdiscount);
						}
					}else{//没有点击折扣
						annualdiscount.setDiscount(0);
						
					}
					activityannual.setAnnualdiscount(annualdiscount);		
				}
			totalRecord = iactivity_annualDao.getActivityAnnualCount(s_time,e_time);		
			model.addAttribute("activityList", activityList);	
			model.addAttribute("s_time", s_time != null ? AuthUtil.formatDateToString(s_time, "yyyy-MM-dd") : "");
			model.addAttribute("e_time", e_timeString);
			}
			PageUtil pageUtil = new PageUtil(20, totalRecord, page);
			pageUtil.setTotalRecord(totalRecord);
			pageUtil.setPageNumStart(pageUtil.getPageNumStart());
			pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
			pageUtil.setCurrentPage(page);
			pageUtil.setColumns(14);
			pageUtil.setUrlName("activityannual/list");
			model.addAttribute("showPage", pageUtil);
			model.addAttribute("activeUrl", "activityannual");
			model.addAttribute("type", type);
	
		} catch (Exception e) {
			log.error("年度盛典回归老用户列表", e);
		}
		return "activityannual/list";
	}

		public String getActivityAnnualDetail(int wheel, int activity_id, int count,int page, Model model) {
			try {
			
				int totalRecord=0;
				List<Activity_Annual_Prize_User> prizeuser=iactivity_annualDao.getAnnual_Prize_User(activity_id,(page-1)*20, 20);
				totalRecord=iactivity_annualDao.getgetAnnual_Prize_UserCount(activity_id);
					for(Activity_Annual_Prize_User prize_user:prizeuser){
						
						if(prize_user.getPrize_id()==1 || prize_user.getPrize_id()==2){//若礼物是钻石和金猪，则设为领取状态
							iactivity_annualDao.updateIstake(1,prize_user.getId());
							prize_user.setIs_take(1);
						}	
						
						User user=iuserdao.getUser((long)prize_user.getUid());
						prize_user.setNickname(user.getNickname());
						Activity_Annual_Prize prize=iactivity_annualDao.getActivityAnnualPrize(prize_user.getPrize_id());
						prize_user.setPrize_name(prize.getPrize_name());
						
					}
					model.addAttribute("prizeuser", prizeuser);
				
				
				PageUtil pageUtil = new PageUtil(20, totalRecord, page);
				pageUtil.setTotalRecord(totalRecord);
				pageUtil.setPageNumStart(pageUtil.getPageNumStart());
				pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
				pageUtil.setCurrentPage(page);
				pageUtil.setColumns(14);
				pageUtil.setUrlName("activityannual/detail");
				model.addAttribute("showPage", pageUtil);
				model.addAttribute("activeUrl", "activityannual");			
				model.addAttribute("count", count);
				model.addAttribute("activity_id", activity_id);
				model.addAttribute("wheel", wheel);
				
			} catch (Exception e) {
				log.error("金钱树查看明细", e);
			}
			return "activityannual/detail";
		}

		public int saveUserRemak(int id, String remark, String w_name, HttpServletRequest request) {
			int result = 0;
			try {
				w_name = String.valueOf(request.getAttribute("w_name"));
				result = iactivity_annualDao.saveRemak(id, remark,w_name);
				if (result > 0)
					imanage_RecordDao.insertManageRecord(w_name, "修改备注,id：" + id, "t_user", 0, IPUtil.getIp(request), new Date());
			} catch (Exception e) {
				log.error("修改备注", e);
			}
			return result;
		}

		public String exportExcel(HttpServletResponse response) {
			try {
				ServletOutputStream outputStream = response.getOutputStream();  
				Date date = new Date();

				Date end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 23:59:59");
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_date); 
				calendar.add(Calendar.DATE,-1);
				Date start_date=calendar.getTime();
				String fileName = new String(("年度盛典金钱树"+AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
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
				String[] titles = new String[]{"活动时间","总充值金额","支出钻石总额","支出小金猪个数","支出守护个数","支出旅游券个数","支出现金券个数"};
				for (int i = 0; i < titles.length; i++)  
				{  
					cell = headRow.createCell(i);  
					cell.setCellStyle(headStyle);  
					cell.setCellValue(titles[i]);  
				}  
				
			
				// 构建表体数据  
				
				ArrayList<MoneyTree> list=new ArrayList<MoneyTree>();
				
				//23号
					MoneyTree moneytree=new MoneyTree();	
					String statetime="2019-01-23 20:00:00";
					String endtime="2019-01-23 23:59:59";						
					int recharge1=iactivity_annualDao.getAllRecharge(statetime,endtime);//充值金额
					int  balance1=iactivity_annualDao.getAllBalanceVirtual(statetime,endtime);//赠送时充值金额
					int allrecharge1=recharge1+balance1;//总充值金额
					int allbalance_virtual1=iactivity_annualDao.getAllPrizecount(statetime,endtime,1)*88;//支出钻石总额
					int pigcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,2);//支出小金猪个数
					int yguardcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,3);//月支出守护个数
					int jguardcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,4);//季支出守护个数
					int nguardcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,5);//年支出守护个数
					int allguardcount1=yguardcount1+jguardcount1+nguardcount1;//支出守护个数
					int travelcount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,6);//支出旅游券个数
					int moneycount1=iactivity_annualDao.getAllPrizecount(statetime,endtime,7);//支出现金个数				
					moneytree.setAllbalance_virtual(allbalance_virtual1);
					moneytree.setAllrecharge(allrecharge1);
					moneytree.setGuardcount(allguardcount1);
					moneytree.setMoneycount(moneycount1);
					moneytree.setPigcount(pigcount1);
					moneytree.setTravelcount(travelcount1);
					moneytree.setActivitytime("2019-01-23 20:00 - 24:00");
					list.add(moneytree);
			//24号		
					MoneyTree moneytree2=new MoneyTree();
					String statetime2="2019-01-24 20:00:00";
					String endtime2="2019-01-24 23:59:59";						
					int recharge2=iactivity_annualDao.getAllRecharge(statetime2,endtime2);//充值金额
					int  balance2=iactivity_annualDao.getAllBalanceVirtual(statetime2,endtime2);//赠送时充值金额
					int allrecharge2=recharge2+balance2;//总充值金额
					int allbalance_virtual2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,1)*88;//支出钻石总额
					int pigcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,2);//支出小金猪个数
					int yguardcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,3);//月支出守护个数
					int jguardcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,4);//季支出守护个数
					int nguardcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,5);//年支出守护个数
					int allguardcount2=yguardcount2+jguardcount2+nguardcount2;//支出守护个数
					int travelcount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,6);//支出旅游券个数
					int moneycount2=iactivity_annualDao.getAllPrizecount(statetime2,endtime2,7);//支出现金个数
					moneytree2.setAllbalance_virtual(allbalance_virtual2);
					moneytree2.setAllrecharge(allrecharge2);
					moneytree2.setGuardcount(allguardcount2);
					moneytree2.setMoneycount(moneycount2);
					moneytree2.setPigcount(pigcount2);
					moneytree2.setTravelcount(travelcount2);	
					moneytree2.setActivitytime("2019-01-24 20:00 - 24:00");
					list.add(moneytree2);
					//25号		
					MoneyTree moneytree3=new MoneyTree();
					String statetime3="2019-01-25 20:00:00";
					String endtime3="2019-01-25 23:59:59";						
					int recharge3=iactivity_annualDao.getAllRecharge(statetime3,endtime3);//充值金额
					int  balance3=iactivity_annualDao.getAllBalanceVirtual(statetime3,endtime3);//赠送时充值金额
					int allrecharge3=recharge3+balance3;//总充值金额
					int allbalance_virtual3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,1)*88;//支出钻石总额
					int pigcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,2);//支出小金猪个数
					int yguardcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,3);//月支出守护个数
					int jguardcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,4);//季支出守护个数
					int nguardcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,5);//年支出守护个数
					int allguardcount3=yguardcount3+jguardcount3+nguardcount3;//支出守护个数
					int travelcount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,6);//支出旅游券个数
					int moneycount3=iactivity_annualDao.getAllPrizecount(statetime3,endtime3,7);//支出现金个数
					moneytree3.setAllbalance_virtual(allbalance_virtual3);
					moneytree3.setAllrecharge(allrecharge3);
					moneytree3.setGuardcount(allguardcount3);
					moneytree3.setMoneycount(moneycount3);
					moneytree3.setPigcount(pigcount3);
					moneytree3.setTravelcount(travelcount3);	
					moneytree3.setActivitytime("2019-01-25 20:00 - 24:00");
					list.add(moneytree3);
					//26号		
					MoneyTree moneytree4=new MoneyTree();
					String statetime4="2019-01-26 20:00:00";
					String endtime4="2019-01-26 23:59:59";						
					int recharge4=iactivity_annualDao.getAllRecharge(statetime4,endtime4);//充值金额
					int  balance4=iactivity_annualDao.getAllBalanceVirtual(statetime4,endtime4);//赠送时充值金额
					int allrecharge4=recharge4+balance4;//总充值金额
					int allbalance_virtual4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,1)*88;//支出钻石总额
					int pigcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,2);//支出小金猪个数
					int yguardcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,3);//月支出守护个数
					int jguardcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,4);//季支出守护个数
					int nguardcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,5);//年支出守护个数
					int allguardcount4=yguardcount4+jguardcount4+nguardcount4;//支出守护个数
					int travelcount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,6);//支出旅游券个数
					int moneycount4=iactivity_annualDao.getAllPrizecount(statetime4,endtime4,7);//支出现金个数
					moneytree4.setAllbalance_virtual(allbalance_virtual4);
					moneytree4.setAllrecharge(allrecharge4);
					moneytree4.setGuardcount(allguardcount4);
					moneytree4.setMoneycount(moneycount4);
					moneytree4.setPigcount(pigcount4);
					moneytree4.setTravelcount(travelcount4);	
					moneytree4.setActivitytime("2019-01-26 20:00 - 24:00");
					list.add(moneytree4);
					//27号		
					MoneyTree moneytree5=new MoneyTree();
					String statetime5="2019-01-27 20:00:00";
					String endtime5="2019-01-27 23:59:59";						
					int recharge5=iactivity_annualDao.getAllRecharge(statetime5,endtime5);//充值金额
					int  balance5=iactivity_annualDao.getAllBalanceVirtual(statetime4,endtime4);//赠送时充值金额
					int allrecharge5=recharge5+balance5;//总充值金额
					int allbalance_virtual5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,1)*88;//支出钻石总额
					int pigcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,2);//支出小金猪个数
					int yguardcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,3);//月支出守护个数
					int jguardcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,4);//季支出守护个数
					int nguardcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,5);//年支出守护个数
					int allguardcount5=yguardcount5+jguardcount5+nguardcount5;//支出守护个数
					int travelcount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,6);//支出旅游券个数
					int moneycount5=iactivity_annualDao.getAllPrizecount(statetime5,endtime5,7);//支出现金个数
					moneytree5.setAllbalance_virtual(allbalance_virtual5);
					moneytree5.setAllrecharge(allrecharge5);
					moneytree5.setGuardcount(allguardcount5);
					moneytree5.setMoneycount(moneycount5);
					moneytree5.setPigcount(pigcount5);
					moneytree5.setTravelcount(travelcount5);	
					moneytree5.setActivitytime("2019-01-27 20:00 - 24:00");
					list.add(moneytree5);
					//28号		
					MoneyTree moneytree6=new MoneyTree();
					String statetime6="2019-01-28 20:00:00";
					String endtime6="2019-01-28 23:59:59";						
					int recharge6=iactivity_annualDao.getAllRecharge(statetime6,endtime6);//充值金额
					int  balance6=iactivity_annualDao.getAllBalanceVirtual(statetime6,endtime6);//赠送时充值金额
					int allrecharge6=recharge6+balance6;//总充值金额
					int allbalance_virtual6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,1)*88;//支出钻石总额
					int pigcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,2);//支出小金猪个数
					int yguardcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,3);//月支出守护个数
					int jguardcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,4);//季支出守护个数
					int nguardcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,5);//年支出守护个数
					int allguardcount6=yguardcount6+jguardcount6+nguardcount6;//支出守护个数
					int travelcount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,6);//支出旅游券个数
					int moneycount6=iactivity_annualDao.getAllPrizecount(statetime6,endtime6,7);//支出现金个数
					moneytree6.setAllbalance_virtual(allbalance_virtual6);
					moneytree6.setAllrecharge(allrecharge6);
					moneytree6.setGuardcount(allguardcount6);
					moneytree6.setMoneycount(moneycount6);
					moneytree6.setPigcount(pigcount6);
					moneytree6.setTravelcount(travelcount6);	
					moneytree6.setActivitytime("2019-01-28 20:00 - 24:00");
					list.add(moneytree6);
					//29号		
					MoneyTree moneytree7=new MoneyTree();
					String statetime7="2019-01-29 20:00:00";
					String endtime7="2019-01-29 23:59:59";						
					int recharge7=iactivity_annualDao.getAllRecharge(statetime7,endtime7);//充值金额
					int  balance7=iactivity_annualDao.getAllBalanceVirtual(statetime7,endtime7);//赠送时充值金额
					int allrecharge7=recharge7+balance7;//总充值金额
					int allbalance_virtual7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,1)*88;//支出钻石总额
					int pigcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,2);//支出小金猪个数
					int yguardcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,3);//月支出守护个数
					int jguardcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,4);//季支出守护个数
					int nguardcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,5);//年支出守护个数
					int allguardcount7=yguardcount7+jguardcount7+nguardcount7;//支出守护个数
					int travelcount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,6);//支出旅游券个数
					int moneycount7=iactivity_annualDao.getAllPrizecount(statetime7,endtime7,7);//支出现金个数
					moneytree7.setAllbalance_virtual(allbalance_virtual7);
					moneytree7.setAllrecharge(allrecharge7);
					moneytree7.setGuardcount(allguardcount7);
					moneytree7.setMoneycount(moneycount7);
					moneytree7.setPigcount(pigcount7);
					moneytree7.setTravelcount(travelcount7);
					moneytree7.setActivitytime("2019-01-29 20:00 - 24:00");
					list.add(moneytree7);
					//30号		
					MoneyTree moneytree8=new MoneyTree();
					String statetime8="2019-01-30 20:00:00";
					String endtime8="2019-01-30 23:59:59";						
					int recharge8=iactivity_annualDao.getAllRecharge(statetime8,endtime8);//充值金额
					int  balance8=iactivity_annualDao.getAllBalanceVirtual(statetime8,endtime8);//赠送时充值金额
					int allrecharge8=recharge8+balance8;//总充值金额
					int allbalance_virtual8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,1)*88;//支出钻石总额
					int pigcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,2);//支出小金猪个数
					int yguardcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,3);//月支出守护个数
					int jguardcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,4);//季支出守护个数
					int nguardcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,5);//年支出守护个数
					int allguardcount8=yguardcount8+jguardcount8+nguardcount8;//支出守护个数
					int travelcount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,6);//支出旅游券个数
					int moneycount8=iactivity_annualDao.getAllPrizecount(statetime8,endtime8,7);//支出现金个数
					moneytree8.setAllbalance_virtual(allbalance_virtual8);
					moneytree8.setAllrecharge(allrecharge8);
					moneytree8.setGuardcount(allguardcount8);
					moneytree8.setMoneycount(moneycount8);
					moneytree8.setPigcount(pigcount8);
					moneytree8.setTravelcount(travelcount8);	
					moneytree8.setActivitytime("2019-01-30 20:00 - 24:00");
					list.add(moneytree8);
					//31号		
					MoneyTree moneytree9=new MoneyTree();
					String statetime9="2019-01-31 20:00:00";
					String endtime9="2019-01-31 23:59:59";						
					int recharge9=iactivity_annualDao.getAllRecharge(statetime9,endtime9);//充值金额
					int  balance9=iactivity_annualDao.getAllBalanceVirtual(statetime9,endtime9);//赠送时充值金额
					int allrecharge9=recharge9+balance9;//总充值金额
					int allbalance_virtual9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,1)*88;//支出钻石总额
					int pigcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,2);//支出小金猪个数
					int yguardcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,3);//月支出守护个数
					int jguardcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,4);//季支出守护个数
					int nguardcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,5);//年支出守护个数
					int allguardcount9=yguardcount9+jguardcount9+nguardcount9;//支出守护个数
					int travelcount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,6);//支出旅游券个数
					int moneycount9=iactivity_annualDao.getAllPrizecount(statetime9,endtime9,7);//支出现金个数
					moneytree9.setAllbalance_virtual(allbalance_virtual9);
					moneytree9.setAllrecharge(allrecharge9);
					moneytree9.setGuardcount(allguardcount9);
					moneytree9.setMoneycount(moneycount9);
					moneytree9.setPigcount(pigcount9);
					moneytree9.setTravelcount(travelcount9);
					moneytree9.setActivitytime("2019-01-31 20:00 - 24:00");
					list.add(moneytree9);
		
				for (int j = 0; j < list.size(); j++)  
				{  
					XSSFRow bodyRow = sheet.createRow(j + 1); 

					cell = bodyRow.createCell(0);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getActivitytime());  

					cell = bodyRow.createCell(1);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getAllrecharge());   

					cell = bodyRow.createCell(2);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getAllbalance_virtual());

					cell = bodyRow.createCell(3);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getPigcount());

					cell = bodyRow.createCell(4);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(list.get(j).getGuardcount());

					cell = bodyRow.createCell(5);  
					cell.setCellStyle(bodyStyle); 
					cell.setCellValue(list.get(j).getTravelcount());

					cell = bodyRow.createCell(6);  
					cell.setCellStyle(bodyStyle); 
					cell.setCellValue(list.get(j).getMoneycount());			

				}  
				
				try  
				{  
					workBook.write(outputStream);  
					outputStream.flush();  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
				finally  
				{  
					try  
					{  
						outputStream.close();  
					}  
					catch (IOException e)  
					{  
						e.printStackTrace();  
					}  
				}  
			} catch (Exception e) {
				log.error("导出年度盛典金钱树", e);
			}
			return null;
		}

		public String exportExcel2(Date s_time, Date e_time, HttpServletResponse response) {
			try {
				ServletOutputStream outputStream = response.getOutputStream();  
				Date date = new Date();

				Date end_date = AuthUtil.formatStringToDate(AuthUtil.formatDateToString(date, "yyyy-MM-dd")+" 23:59:59");
				Calendar calendar = new GregorianCalendar(); 
				calendar.setTime(end_date); 
				calendar.add(Calendar.DATE,1);
				Date start_date=calendar.getTime();
				if (e_time != null) {
					calendar.setTime(e_time);
					calendar.add(Calendar.DATE, 1);
					e_time = calendar.getTime();
				}
				String fileName = new String(("老用户回归"+AuthUtil.formatDateToString(start_date, "yyyy-MM-dd")).getBytes(), "ISO8859_1");  
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
				String[] titles = new String[]{"用户UID","用户昵称","获得折扣","是否使用了折扣","折扣后充值","回归时间","使用折扣充值时间"};
				for (int i = 0; i < titles.length; i++)  
				{  
					cell = headRow.createCell(i);  
					cell.setCellStyle(headStyle);  
					cell.setCellValue(titles[i]);  
				}  
				
			
				// 构建表体数据  

				List<Activity_Annual_Login> activityList = iactivity_annualDao.getActivityAnnualList(s_time,e_time, 0, 1000000000);
				for(Activity_Annual_Login activityannual:activityList){			
					User user=iuserdao.getUser((long)activityannual.getUid());
					if(user !=null){
						activityannual.setNickname(user.getNickname());
						activityannual.setF_uuid(user.getF_uuid());
					}		
					Activity_Annual_Discount discount=iactivity_annualDao.getActivityAnnualDiscount(activityannual.getUid());
					Activity_Annual_Discount annualdiscount=new Activity_Annual_Discount();
					if(discount !=null){//折扣
						annualdiscount.setDiscount(discount.getDiscount());
						//判断是否使用了折扣				
						Recharge recharge=iactivity_annualDao.getRechargeLimitone(discount.getCreate_time(),discount.getUid());
						
						if(recharge !=null ){//充值了
							Change change=ichangedao.getChange(recharge.getChange_id());
							if(recharge.getRecharge_rmb()<change.getChange_rmb()){//则说明使用了折扣卡
								annualdiscount.setIs_use(1);
								annualdiscount.setRecharge_rmb(recharge.getRecharge_rmb());//折后充值金额
								DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  					
								annualdiscount.setRecharge_time(format.parse(recharge.getCreate_time()));//折后充值时间
							}else{//反之，没使用
								annualdiscount.setIs_use(0);
							}
							activityannual.setAnnualdiscount(annualdiscount);
						}else{//没有充值
							annualdiscount.setIs_use(0);
							activityannual.setAnnualdiscount(annualdiscount);
						}
					}else{//没有点击折扣
						annualdiscount.setDiscount(0);
						
					}
					activityannual.setAnnualdiscount(annualdiscount);		
				}
				
		
				for (int j = 0; j < activityList.size(); j++)  
				{  
					XSSFRow bodyRow = sheet.createRow(j + 1); 

					cell = bodyRow.createCell(0);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(activityList.get(j).getUid());  

					cell = bodyRow.createCell(1);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(activityList.get(j).getNickname());   

					cell = bodyRow.createCell(2);  
					cell.setCellStyle(bodyStyle);  
					cell.setCellValue(activityList.get(j).getAnnualdiscount().getDiscount());

					cell = bodyRow.createCell(3);  
					cell.setCellStyle(bodyStyle);  
					if(activityList.get(j).getAnnualdiscount().getIs_use()==0){
						cell.setCellValue("否");
					}else if(activityList.get(j).getAnnualdiscount().getIs_use()==1){
						cell.setCellValue("是");
					}
					
					cell = bodyRow.createCell(4);  
					cell.setCellStyle(bodyStyle); 
					cell.setCellValue(activityList.get(j).getAnnualdiscount().getRecharge_rmb());
					
					cell = bodyRow.createCell(5);  
					cell.setCellStyle(bodyStyle);  
					Date logintime=activityList.get(j).getLogin_time();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 String dateString = formatter.format(logintime);
					cell.setCellValue(dateString);

					cell = bodyRow.createCell(6);  
					cell.setCellStyle(bodyStyle);
					Date rechargetime=activityList.get(j).getAnnualdiscount().getRecharge_time();
					if(rechargetime!=null){
						String dateString2 = formatter.format(rechargetime);
						cell.setCellValue(dateString2);		
					}else{
						cell.setCellValue("--");
					}
					 
				

				}  
				
				try  
				{  
					workBook.write(outputStream);  
					outputStream.flush();  
					outputStream.close();  
				}  
				catch (IOException e)  
				{  
					e.printStackTrace();  
				}  
				finally  
				{  
					try  
					{  
						outputStream.close();  
					}  
					catch (IOException e)  
					{  
						e.printStackTrace();  
					}  
				}  
			} catch (Exception e) {
				log.error("导出年度盛典金钱树", e);
			}
			return null;
			}

		public String ChangeTake(int id, int is_take, HttpServletRequest request) {
			try
			{
				String w_name = String.valueOf(request.getAttribute("w_name"));
				int result = iactivity_annualDao.updatePrizeUser(id, is_take,w_name);
				if(result>0)
				{
					return "{\"code\":0,\"msg\":\"保存成功\"}";	
				}
				
			}
			catch (Exception e) {
				log.error("改变红包领取状态",e);
			}
			return "{\"code\":-1,\"msg\":\"保存失败\"}";	
		}
			
}

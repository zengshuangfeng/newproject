package com.meisui.manage.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Anchor_Virtual_Change_Record;
import com.meisui.manage.entity.Anchor_Week_Export;
import com.meisui.manage.entity.Area;
import com.meisui.manage.entity.Package_Extra;
import com.meisui.manage.entity.Pic_Upload;
import com.meisui.manage.entity.User;
import com.meisui.manage.entity.User_Anchor;

/**
 * <p>文件名称：IconfigDao.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年4月19日 上午11:34:17
 */
@Repository
public interface IconfigDao {
	String getConfigValue(@Param("attribute")String attribute);
	List<Area> getAreaList(@Param("f_id")Integer f_id);
	List<User> getUserList();
	Long getScoreWithLevel(@Param("level")Integer level);
	List<User> getHeadUserList();
	Integer insertPicUpload(@Param("uid")Long uid, @Param("old_pic")String old_pic, @Param("new_pic")String new_pic);
	List<User> getVestUserList();
	List<Pic_Upload> getPicUploadList();
	Integer updateUserHead(@Param("old_head")String old_head, @Param("head")String head);
	List<User> getUserVestList();
	Integer updateVestUserHead(@Param("id")Long id, @Param("head")String head);
	List<Anchor_Virtual_Change_Record> getClearList();
	List<Long> getProfitUidList(@Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer insertAnchorWeekExport(Anchor_Week_Export entity);
	List<Anchor_Week_Export> getAnchorWeekExportList(@Param("start_time")Date start_time, @Param("end_time")Date end_time);
	List<User_Anchor> getTrialAnchorList(@Param("start_time")Date start_time, @Param("end_time")Date end_time);
	Integer insertAnchorCode(@Param("anchor_code")String anchor_code);
	List<Long> getTotalFlowUidList();
	List<Long> getTotalFlowWithUid(@Param("uid")Long uid, @Param("type")String type);
	List<Long> getBalanceRecordList(@Param("uid")Long uid);
	Integer insertTestBanlance(@Param("uid")Long uid, @Param("balance_virtual")Long balance_virtual, @Param("balance_virtual2")Long balance_virtual2);
	Long getBalance(@Param("uid")Long uid);
	Integer setFeeCountLimit(@Param("attribute")String attribute,@Param("values")String values);
	List<Package_Extra> getPackAgeExtraChannelList();
	Integer getInviteMakeExist(@Param("invite_makeamends")String invite_makeamends);
	Integer insertInviteMake(@Param("invite_makeamends")String invite_makeamends, @Param("is_use")Integer is_use);
}

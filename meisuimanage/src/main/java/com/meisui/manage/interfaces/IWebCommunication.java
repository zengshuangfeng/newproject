package com.meisui.manage.interfaces;

/**
 * <p>文件名称：IWebCommunication.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司：Forman </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年12月26日 下午7:34:15
 */

public interface IWebCommunication {
	/**
     * 
     * <p>功能描述：后台禁播</p>
     * <p>创建人：sky</p>
     * <p>创建日期：2016年12月26日 下午7:41:56</p>
     *
     * @param 主播f_uuid
     * @param 主播token
     * @return
     */
  public  boolean adminJin(String f_uuid,String token);
  
  /**
   * 
   * <p>功能描述：警告</p>
   * <p>创建人：sky</p>
   * <p>创建日期：2017年1月19日 下午4:33:06</p>
   *
   * @param f_uuid
   * @param token
   * @return
   */
  public  boolean adminJinGao(String f_uuid,String token,String title,String msg,String time);
  /**
   * 
   * <p>功能描述：加关注</p>
   * <p>创建人：sky</p>
   * <p>创建日期：2016年12月26日 下午8:00:01</p>
   *
   * @param f_uuid
   * @param sign
   * @return
   */
  public boolean addfollow(String f_uuid,boolean sign,String token);
   
  /**
   * 
   * <p>功能描述：禁言</p>
   * <p>创建人：sky</p>
   * <p>创建日期：2016年12月26日 下午8:31:16</p>
   *
   * @param uid
   * @return
   */
  public boolean gaguid(String token,String hours);
  /**
   * 
   * <p>功能描述：任务达到未领取钻石发送包</p>
   * <p>创建人：sky</p>
   * <p>创建日期：2017年1月12日 上午11:01:58</p>
   *
   * @param uid
   * @return
   */
  public boolean sendtask(String uid);
  /**
   * 关闭房间
   */
  public boolean closeroom(String f_uuid);
  /**
   * 
   * <p>功能描述：超管禁言</p>
   * <p>创建人：sky</p>
   * <p>创建日期：2017年2月23日 上午11:12:52</p>
   *
   * @param usertoken 被禁用户的token
   * @param f_uuid   主播房间
   * @param sign  0全局禁言1房间禁言
   * @param ifjin  0禁言1解禁
   * @return
   */
  public boolean isjin(String usertoken,String f_uuid,boolean sign,boolean ifjin);
  /**
   * 
   * <p>功能描述：用户升级接口包</p>
   * <p>创建人：sky</p>
   * <p>创建日期：2017年2月22日 下午5:53:03</p>
   *
   * @param token
   * @return
   */
  public boolean updatelevel(String token);
  /**
   * 公告
   * @param msg
   * @return
   */
  public boolean message(String msg);
}

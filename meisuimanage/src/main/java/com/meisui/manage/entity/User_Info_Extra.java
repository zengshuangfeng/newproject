package com.meisui.manage.entity;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * <p>文件名称：user_info_extra.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2015-2099</p>
 * <p>公   司： Vmei </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @since 2016年1月12日 下午5:00:39
 */

public class User_Info_Extra {
    private String t1;
    private String token;
    private long uid;
    private String login_time;
    private String apn;
    private String network;
    private long ip;
    public String getT1() {
        return t1;
    }
    public void setT1(String t1) {
        this.t1 = t1;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getLogin_time() {
        return login_time;
    }
    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }
    public String getApn() {
        return StringEscapeUtils.escapeHtml4(apn);
    }
    public void setApn(String apn) {
        this.apn = apn;
    }
    public String getNetwork() {
        return StringEscapeUtils.escapeHtml4(network);
    }
    public void setNetwork(String network) {
        this.network = network;
    }
    public long getIp() {
        return ip;
    }
    public void setIp(long ip) {
        this.ip = ip;
    }

}

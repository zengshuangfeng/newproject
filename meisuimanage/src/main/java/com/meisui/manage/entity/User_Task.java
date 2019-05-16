package com.meisui.manage.entity;

import java.util.Date;

import com.meisui.manage.utils.AuthUtil;

/**
 * <p>文件名称：User_Task.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年1月4日 上午10:19:23
 */

public class User_Task {
	private int id;//任务id
	private int type;//类型 1分享 2游戏 3打赏
	private String icon;//图标
	private String name;//任务名称
	private int num;//完成次数
	private int reward_count;//奖励钻石
	private int sort;//排序
	private String w_name;//编辑人员
	private String create_time;//生效时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getReward_count() {
		return reward_count;
	}
	public void setReward_count(int reward_count) {
		this.reward_count = reward_count;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = AuthUtil.formatDateToString(create_time);
	}
}

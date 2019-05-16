package com.meisui.manage.entity;

/**
 * <p>文件名称：Area.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年5月5日 上午10:06:00
 */

public class Area {
	private int id;//地区id
	private int f_id;
	private String name;//地区名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

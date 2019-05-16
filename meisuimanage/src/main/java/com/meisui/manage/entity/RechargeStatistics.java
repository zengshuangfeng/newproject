package com.meisui.manage.entity;

/**
 * 
 * <p>文件名称：RechargeStatistics.java</p>
 * <p>文件描述：</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司：  </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:"></a>
 * @since 2017年9月11日上午10:06:46 
 */
public class RechargeStatistics {
	
	private String recharge_year;//年
	private String recharge_month;//月
	private String recharge_day;//日
	private Integer people_count;//充值人数
	private Integer recharge_count;//充值次数
	private Long recharge_money;//充值金额
	private Long virtual_money;//充值虚拟金额
	/**
	 * @return the recharge_year
	 */
	public String getRecharge_year() {
		return recharge_year;
	}
	/**
	 * @param recharge_year the recharge_year to set
	 */
	public void setRecharge_year(String recharge_year) {
		this.recharge_year = recharge_year;
	}
	/**
	 * @return the recharge_month
	 */
	public String getRecharge_month() {
		return recharge_month;
	}
	/**
	 * @param recharge_month the recharge_month to set
	 */
	public void setRecharge_month(String recharge_month) {
		this.recharge_month = recharge_month;
	}
	/**
	 * @return the recharge_day
	 */
	public String getRecharge_day() {
		return recharge_day;
	}
	/**
	 * @param recharge_day the recharge_day to set
	 */
	public void setRecharge_day(String recharge_day) {
		this.recharge_day = recharge_day;
	}
	/**
	 * @return the people_count
	 */
	public Integer getPeople_count() {
		return people_count;
	}
	/**
	 * @param people_count the people_count to set
	 */
	public void setPeople_count(Integer people_count) {
		this.people_count = people_count;
	}
	/**
	 * @return the recharge_count
	 */
	public Integer getRecharge_count() {
		return recharge_count;
	}
	/**
	 * @param recharge_count the recharge_count to set
	 */
	public void setRecharge_count(Integer recharge_count) {
		this.recharge_count = recharge_count;
	}
	/**
	 * @return the recharge_money
	 */
	public Long getRecharge_money() {
		return recharge_money;
	}
	/**
	 * @param recharge_money the recharge_money to set
	 */
	public void setRecharge_money(Long recharge_money) {
		this.recharge_money = recharge_money;
	}
	/**
	 * @return the virtual_money
	 */
	public Long getVirtual_money() {
		return virtual_money;
	}
	/**
	 * @param virtual_money the virtual_money to set
	 */
	public void setVirtual_money(Long virtual_money) {
		this.virtual_money = virtual_money;
	}
}

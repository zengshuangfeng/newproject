package com.meisui.manage.entity;

public class RegisterLoginStatistics {
	
	private String statis_date;
	private long register_count;
	private long user_count;
	private long login_count;
	/**
	 * @return the statis_date
	 */
	public String getStatis_date() {
		return statis_date;
	}
	/**
	 * @param statis_date the statis_date to set
	 */
	public void setStatis_date(String statis_date) {
		this.statis_date = statis_date;
	}
	/**
	 * @return the register_count
	 */
	public long getRegister_count() {
		return register_count;
	}
	/**
	 * @param register_count the register_count to set
	 */
	public void setRegister_count(long register_count) {
		this.register_count = register_count;
	}
	/**
	 * @return the user_count
	 */
	public long getUser_count() {
		return user_count;
	}
	/**
	 * @param user_count the user_count to set
	 */
	public void setUser_count(long user_count) {
		this.user_count = user_count;
	}
	/**
	 * @return the login_count
	 */
	public long getLogin_count() {
		return login_count;
	}
	/**
	 * @param login_count the login_count to set
	 */
	public void setLogin_count(long login_count) {
		this.login_count = login_count;
	}
}

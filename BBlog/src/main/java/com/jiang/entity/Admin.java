package com.jiang.entity;

import java.util.Date;

/**
 * 管理员实体类
 * 
 * @author JH
 *
 */
public class Admin {

	/* 管理员id */
	private Integer id;
	/* 管理员邮箱 */
	private String email;
	/* 管理员密码 */
	private String password;
	
	private Date addTime;
	
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

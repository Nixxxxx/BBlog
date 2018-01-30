package com.jiang.entity;

import java.util.Date;

/**
 * 管理员
 * @author JH
 *
 */
public class Admin {

	private Integer id;			/* 管理员id */
	private String email;		/* 管理员邮箱 */
	private String password;	/* 管理员密码 */
	private Date createTime;	/* 创建时间 */
	private Integer status;		/* 管理员状态  0正常  1已禁用 */

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

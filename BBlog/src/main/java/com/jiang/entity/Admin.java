package com.jiang.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管理员实体类
 * 
 * @author JH
 *
 */
@Entity
@Table(name = "t_admin")
public class Admin {

	/* 管理员id */
	private Integer id;
	/* 管理员邮箱 */
	private String email;
	/* 管理员密码 */
	private String password;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false, unique = true, length = 40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

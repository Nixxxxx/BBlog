package com.jiang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_user")
public class User {
	
	private Integer id;
	private String userName;
	private String email;
	private String password;
	private String imagePath;
	private String mood;
	private String signInIP;
	private String signUpIP;
	private Integer status;
	
	@Id
	@Column(name = "id",nullable = false,unique = true)
	@GenericGenerator(name = "generator",strategy = "native")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "userName", nullable = false, unique = true, length = 20)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "email", nullable = false, unique = true, length = 20)
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
	
	@Column(name = "imagePath", length = 40)
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Column(name = "mood", length = 50)
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	
	@Column(name = "signInIP", nullable = false, length = 20)
	public String getSignInIP() {
		return signInIP;
	}
	public void setSignInIP(String signInIP) {
		this.signInIP = signInIP;
	}
	
	@Column(name = "signUpIP", nullable = false, length = 20)
	public String getSignUpIP() {
		return signUpIP;
	}
	public void setSignUpIP(String signUpIP) {
		this.signUpIP = signUpIP;
	}
	
	@Column(name = "status", nullable = false, length = 1)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

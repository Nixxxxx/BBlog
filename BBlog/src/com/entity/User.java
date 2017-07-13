package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_user")
public class User {
	private int id;
	private String userName;
	private String email;
	private String password;
	private String imagePath;
	private String mood;
	private String administrator;
	
	@Id
	@Column(name = "id",nullable = false,unique = true)
	@GenericGenerator(name = "generator",strategy = "native")
	@GeneratedValue(generator = "generator")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "userName",nullable = false,unique = true,length = 20)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "email",nullable = false,unique = true,length = 20)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password",nullable = false,length = 40)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "imagePath",nullable = false,length = 40)
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Column(name = "mood",nullable = false,length = 50)
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	
	@Column(name = "administrator",nullable = false,length = 1)
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	
}

package com.jiang.entity;

public class Blogger {

	private Integer id;			/* id */
	private String email;		/* 邮箱 */
	private String name;		/* 姓名 */
	private String avatarPath;	/* 头像路径 */
	private String profile;		/* 介绍 */
	private Integer status;		/* 状态  0正常  1隐藏 */
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

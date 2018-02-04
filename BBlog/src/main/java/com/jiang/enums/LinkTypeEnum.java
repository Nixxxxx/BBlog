package com.jiang.enums;

public enum LinkTypeEnum {

	INDEX(1, "主页"),
	BLOG(2, "博客"),
	MOVIE(3, "电影榜单");
	
	private Integer code;
	private String message;

	private LinkTypeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

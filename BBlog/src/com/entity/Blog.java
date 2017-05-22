package com.entity;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int blogId;
	private String title;
	private String content;
	private int typeId=-1;
	private String typeName;
	private Date releaseDate;
	private String releaseDateStr;
	private int blogCount;
	
	
	
	
	public Blog(String title, String content, int typeId) {
		super();
		this.title = title;
		this.content = content;
		this.typeId = typeId;
	}
	public Blog() {
		super();
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReleaseDateStr() {
		return releaseDateStr;
	}
	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}
	public int getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}
	
	
}

package com.entity;

import java.io.Serializable;

public class BlogType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int blogTypeId;
	private String typeName;
	private int blogCount;
	
	
	
	public BlogType(String typeName) {
		super();
		this.typeName = typeName;
	}
	
	
	public BlogType(int blogTypeId, String typeName) {
		super();
		this.blogTypeId = blogTypeId;
		this.typeName = typeName;
	}

	

	public BlogType(int blogTypeId, String typeName, int blogCount) {
		super();
		this.blogTypeId = blogTypeId;
		this.typeName = typeName;
		this.blogCount = blogCount;
	}


	public BlogType() {
		super();
	}

	public int getBlogTypeId() {
		return blogTypeId;
	}
	public void setBlogTypeId(int blogTypeId) {
		this.blogTypeId = blogTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public int getBlogCount() {
		return blogCount;
	}


	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}
	
}

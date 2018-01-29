package com.jiang.entity;

/**
 * 博客类型实体类
 * @author JH
 *
 */
public class BlogType {

	/* 博客类型id */
	private Integer id;
	/* 博客类型名 */
	private String typeName;
	/* 此博客类型下博客数量 */
	private Integer count;
	
	public BlogType() {
		super();
	}

	public BlogType(String typeName) {
		this.typeName = typeName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}

package com.jiang.entity;

import java.util.Date;

/**
 * 博客类型实体类
 * @author JH
 *
 */
public class BlogType {

	private Integer id;			/* 博客类型id */
	private String typeName;	/* 博客类型名 */
	private Integer count;		/* 此博客类型下博客数量 */
	private Date createTime;	/* 创建时间 */
	private Integer status;		/* 状态 */
	
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

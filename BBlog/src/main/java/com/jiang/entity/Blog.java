package com.jiang.entity;

import java.util.Date;

/**
 * 博客实体类
 * @author JH
 *
 */
public class Blog {
	
	private Integer id;				/* 博客id */
	private BlogType blogType;		/* 博客类型 */
	private String title;			/* 博客标题 */
	private String content;			/* 博客内容（带html标签） */
	private String contentNoTag;	/* 博客内容（不带html标签） */
	private String summary;			/* 博客概要 */
	private Integer reader;			/* 阅读量 */
	private Date updateTime;		/* 更新时间 */
	private Date createTime;		/* 创建时间 */
	private Integer status;			/* 状态  0正常 1不可看 */
		
	public Blog(BlogType findById, String title2, String content2, Date date) {
		
	}
	public Blog() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BlogType getBlogType() {
		return blogType;
	}
	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
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
	public String getContentNoTag() {
		return contentNoTag;
	}
	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getReader() {
		return reader;
	}
	public void setReader(Integer reader) {
		this.reader = reader;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

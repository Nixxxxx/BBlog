package com.jiang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 博客实体类
 * @author JH
 *
 */
@Entity
@Table(name = "t_blog")
public class Blog {
	
	/* 博客id */
	private Integer id;
	/* 博客类型 */
	private BlogType blogType;
	/* 博客标题 */
	private String title;
	/* 博客内容（带html标签） */
	private String content;
	/* 博客内容（不带html标签） */
	private String contentNoTag;
	/* 博客概要 */
	private String summary;
	/* 博客阅读量 */
	private Integer reader;
	/* 博客更新时间 */
	private Date updateTime;
	/* 博客创建时间 */
	private Date createTime;

	
	public Blog() {
		super();
	}


	public Blog(BlogType blogType, String title, String content, Date date) {
		this.blogType = blogType;
		this.title = title;
		this.content = content;
		this.reader = 0;
		this.updateTime = this.createTime = date;
	}

	
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


	@ManyToOne
	@JoinColumn(name = "typeId")
	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}


	@Column(name = "title", nullable = false,length = 60)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "contentNoTag", nullable = false)
	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}


	@Column(name = "summary", nullable = false, length = 255)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "reader", nullable = false, length = 10)
	public Integer getReader() {
		return reader;
	}

	public void setReader(Integer reader) {
		this.reader = reader;
	}

	
	@Column(name = "updateTime", nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	@Column(name = "createTime", nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

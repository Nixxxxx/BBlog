package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.BlogTypeService;

@Entity
@Table(name = "t_blog")
public class Blog {
	
	@Autowired
	private BlogTypeService blogTypeService;

	@Id
	@Column(name = "id",nullable = false,unique = true)
	@GenericGenerator(name = "generator",strategy = "native")
	@GeneratedValue(generator = "generator")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "typeId")
	private BlogType blogType;

	@Column(name = "title",nullable = false,length = 60)
	private String title;
	
	@Column(name = "content",nullable = false)
	private String content;
	
	@Column(name = "reader", nullable = false, length = 10)
	private int reader;
	
	@Column(name = "updateTime",nullable = false)
	private Date updateTime;
	
	@Column(name = "createTime", nullable = false)
	private Date createTime;

	public Blog(int typeId, String title, String content, Date date) {
		this.blogType = blogTypeService.findById(typeId);
		this.title = title;
		this.content = content;
		this.reader = 0;
		this.updateTime = this.createTime = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getReader() {
		return reader;
	}

	public void setReader(int reader) {
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
}

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
import org.springframework.beans.factory.annotation.Autowired;

import com.jiang.service.BlogTypeService;

@Entity
@Table(name = "t_blog")
public class Blog {
	
	@Autowired
	private BlogTypeService blogTypeService;

	
	private int id;
	private BlogType blogType;
	private String title;
	private String content;
	private int reader;
	private Date updateTime;
	private Date createTime;

	
	public Blog() {
		super();
	}


	public Blog(int typeId, String title, String content, Date date) {
		this.blogType = blogTypeService.findById(typeId);
		this.title = title;
		this.content = content;
		this.reader = 0;
		this.updateTime = this.createTime = date;
	}

	
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

	
	@Column(name = "reader", nullable = false, length = 10)
	public int getReader() {
		return reader;
	}

	public void setReader(int reader) {
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

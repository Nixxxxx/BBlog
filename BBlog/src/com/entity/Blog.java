package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_blog")
public class Blog {

	private int id;
	private int typeId;
	private String title;
	private String content;
	private Date updateTime;
	
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
	
	@Column(name = "typeId",nullable = false,length = 10)
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	@Column(name = "title",nullable = false,length = 60)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "content",nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "updateTime",nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

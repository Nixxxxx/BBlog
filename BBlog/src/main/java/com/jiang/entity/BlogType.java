package com.jiang.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 博客类型实体类
 * @author JH
 *
 */
@Entity
@Table(name = "t_blogtype")
public class BlogType {

	/* 博客类型id */
	private Integer id;
	/* 博客类型名 */
	private String typeName;
	/* 此博客类型下博客数量 */
	private Integer count;
	/* 此博客类型下博客集合 */
	private Set<Blog> blogs = new HashSet<Blog>();
	
	
	public BlogType() {
		super();
	}

	public BlogType(String typeName) {
		this.typeName = typeName;
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
	
	@Column(name = "typename", nullable = false, length = 40)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Transient
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name = "id")
	@Transient
	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	
	
	
}

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

@Entity
@Table(name = "t_blogtype")
public class BlogType {

	private int id;
	private String typeName;
	private int count;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
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

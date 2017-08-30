package com.jiang.dao;

import java.util.List;

import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

public interface BlogTypeDao {

	public void insert(BlogType blogType);

	public void update(BlogType blogType);

	public void delete(Integer id);

	public List<BlogType> findList(PageBean pageBean);

	public List<BlogType> countList();
	
	public List<BlogType> findAll();
	
	public BlogType findById(Integer id);
	
}

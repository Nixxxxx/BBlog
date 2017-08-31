package com.jiang.service;

import java.util.List;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

public interface BlogService {


	public Blog findById(Integer id);

	public List<Blog> findListByTypeId(PageBean pageBean, Integer typeId);
	
	public List<Blog> findByTypeId(Integer typeId);

	public List<Blog> findAll();
	
	public boolean insert(Blog blog);

	public boolean delete(Integer id);

	public boolean update(Blog blog);
}

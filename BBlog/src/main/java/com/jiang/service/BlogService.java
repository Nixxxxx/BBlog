package com.jiang.service;

import java.util.List;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

public interface BlogService {


	public Blog findById(int id);

	public List<Blog> findListByTypeId(PageBean pageBean, int typeId);
	
	public List<Blog> findByTypeId(int typeId);

	public List<Blog> findAll();

	public boolean insert(Blog blog);

	public boolean delete(int id);

	public boolean update(Blog blog);
}

package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

public interface BlogDao {

	public void insert(Blog blog);
	
	public Blog update(Blog blog);

	public void delete(int id);
	
	public List<Blog> findListByTypeId(PageBean pageBean, int typeId);
	
	public List<Blog> findByTypeId(int typeId);
	
	public List<Blog> findAll();
	
	public Blog findById(int id);

}

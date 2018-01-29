package com.jiang.service;

import java.util.List;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

/**
 * 博客service接口
 * @author JH
 *
 */
public interface BlogService {
	
	public boolean save(Blog blog);
	
	public boolean delete(Integer id);

	public Blog findById(Integer id);

	public List<Blog> findListByTypeId(PageBean pageBean, Integer typeId);
	
	public List<Blog> findByTypeId(Integer typeId);
	
	public List<Blog> findAll();
}

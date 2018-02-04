package com.jiang.service;

import java.util.List;

import com.jiang.entity.BlogType;

/**
 * 博客类型service接口
 * @author JH
 *
 */
public interface BlogTypeService {

	public boolean save(BlogType blogType);

	public boolean delete(Integer id);

	public BlogType findById(Integer id);
	
	public List<BlogType> countList();

	public List<BlogType> findAll();

	public List<BlogType> findByPage(Integer page, Integer quantity);
	
	public boolean checkTypeName(String typeName, int id);
	
}

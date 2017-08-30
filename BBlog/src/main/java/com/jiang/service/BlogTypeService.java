package com.jiang.service;

import java.util.List;

import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

public interface BlogTypeService {


	public boolean insert(BlogType blogType);

	public BlogType findById(Integer id);

	public boolean delete(Integer id);

	public boolean update(BlogType blogType);
	
	public List<BlogType> countList();

	public List<BlogType> findAll();

	public List<BlogType> findList(PageBean pageBean);
	
}

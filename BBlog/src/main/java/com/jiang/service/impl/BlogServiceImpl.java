package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.BlogDao;
import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;
import com.jiang.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;

	@Override
	public Blog findOne(Integer id) {
		return blogDao.findOne(id);
	}
	
	@Override
	public boolean save(Blog blog) {
		blogDao.save(blog);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		blogDao.delete(id);
		return true;
	}

	@Override
	public List<Blog> findListByTypeId(PageBean pageBean, Integer typeId) {
		return blogDao.findListByTypeId(pageBean, typeId);
	}
	
	@Override
	public List<Blog> findByTypeId(Integer typeId) {
		return blogDao.findAllByTypeId(typeId);
	}


	@Override
	public List<Blog> findAll() {
		return blogDao.findAll();
	}

}

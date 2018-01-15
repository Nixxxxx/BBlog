package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.BlogTypeDao;
import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;
import com.jiang.service.BlogTypeService;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Autowired
	private BlogTypeDao blogTypeDao;

	@Override
	public boolean save(BlogType blogType) {
		blogTypeDao.save(blogType);
		return true;
	}

	@Override
	public BlogType findOne(Integer id) {
		return blogTypeDao.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		blogTypeDao.delete(id);
		return true;
	}
	
	@Override
	public List<BlogType> countList(){
		return blogTypeDao.findcount();
	}

	@Override
	public List<BlogType> findAll() {
		return blogTypeDao.findAll();
	}

	@Override
	public List<BlogType> findList(PageBean pageBean) {
		return blogTypeDao.findList(pageBean);
	}
	
	
}

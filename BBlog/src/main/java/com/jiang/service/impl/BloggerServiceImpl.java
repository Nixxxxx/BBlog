package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.BloggerDao;
import com.jiang.entity.Blogger;
import com.jiang.service.BloggerService;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Autowired
	private BloggerDao bloggerDao;
	
	@Override
	public List<Blogger> findAll(){
		return bloggerDao.findAll();
	}
	
	@Override
	public Blogger findOne(Integer id) {
		return bloggerDao.findOne(id);
	}

	@Override
	public boolean save(Blogger user) {
		bloggerDao.save(user);
		return true;
	}
	
}

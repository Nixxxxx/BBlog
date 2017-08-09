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
	
	public List<Blogger> findAll(){
		return bloggerDao.findAll();
	}
	
	public Blogger findById(int id) {
		return bloggerDao.findById(id);
	}

	public boolean insert(Blogger user) {
		return bloggerDao.insert(user);
	}

	public boolean update(Blogger user) {
		return bloggerDao.update(user);
	}
}

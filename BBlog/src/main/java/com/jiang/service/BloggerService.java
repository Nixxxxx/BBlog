package com.jiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiang.dao.BloggerDao;
import com.jiang.entity.Blogger;

@Service
public class BloggerService {

	@Resource
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

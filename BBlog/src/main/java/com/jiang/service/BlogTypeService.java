package com.jiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiang.dao.BlogTypeDao;
import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

@Service
public class BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;

	public boolean insert(BlogType blogType) {
		blogTypeDao.insert(blogType);
		return true;
	}

	public BlogType findById(int id) {
		return blogTypeDao.findById(id);
	}

	public boolean delete(int id) {
		blogTypeDao.delete(id);
		return true;
	}

	public boolean update(BlogType blogType) {
		blogTypeDao.update(blogType);
		return true;
	}

	public List<BlogType> findAll() {
		return blogTypeDao.findAll();
	}

	public List<BlogType> find(PageBean pageBean, BlogType s_blogType) {
		return blogTypeDao.find(pageBean, s_blogType);
	}
}

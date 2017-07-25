package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BlogTypeDao;
import com.entity.BlogType;
import com.entity.PageBean;

@Service
public class BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;

	public boolean save(BlogType blogType) {
		blogTypeDao.save(blogType);
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

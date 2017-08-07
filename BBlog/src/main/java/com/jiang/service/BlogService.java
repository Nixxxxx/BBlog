package com.jiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiang.dao.BlogDao;
import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

@Service
public class BlogService {

	@Resource
	private BlogDao blogDao;

	public Blog findById(int id) {
		return blogDao.findById(id);
	}

	public List<Blog> findListByTypeId(PageBean pageBean, int typeId) {
		return blogDao.findListByTypeId(pageBean, typeId);
	}
	
	public List<Blog> findByTypeId(int typeId) {
		return blogDao.findByTypeId(typeId);
	}

	public List<Blog> findAll() {
		return blogDao.findAll();
	}

	public boolean insert(Blog blog) {
		blogDao.insert(blog);
		return true;
	}

	public boolean delete(int id) {
		blogDao.delete(id);
		return true;
	}

	public boolean update(Blog blog) {
		blogDao.update(blog);
		return true;
	}
}

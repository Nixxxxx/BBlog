package com.jiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.BlogDao;
import com.jiang.entity.Blog;
import com.jiang.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;

	@Override
	public Blog findById(Integer id) {
		return blogDao.findById(id);
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
	public List<Blog> findListByTypeId(Integer typeId, Integer page, Integer quantity) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", (page-1)*10);
		map.put("quantity", quantity);
		return blogDao.findListByTypeId(map);
	}
	
	@Override
	public List<Blog> findByTypeId(Integer typeId) {
		return blogDao.findAllByTypeId(typeId);
	}


	@Override
	public List<Blog> findAll() {
		return blogDao.findAll();
	}

	@Override
	public boolean check(String title, int typeId, Integer id){
		List<Blog> blogs = blogDao.findAll();
		for(Blog blog:blogs){
			if(blog.getTitle().equals(title) && blog.getBlogType().getId() == typeId && blog.getId() != id)
				return false;
		}
		return true;
	}
}

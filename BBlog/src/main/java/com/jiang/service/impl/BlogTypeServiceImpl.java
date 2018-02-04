package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.BlogTypeDao;
import com.jiang.entity.BlogType;
import com.jiang.service.BlogTypeService;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Autowired
	private BlogTypeDao blogTypeDao;
	
	public boolean checkTypeName(String typeName, int id){
		List<BlogType> blogTypes = blogTypeDao.findAll();
		for(BlogType blogType:blogTypes){
			if(blogType.getTypeName().equals(typeName) && blogType.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public boolean save(BlogType blogType) {
		blogTypeDao.save(blogType);
		return true;
	}

	@Override
	public BlogType findById(Integer id) {
		return blogTypeDao.findById(id);
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
	public List<BlogType> findByPage(Integer page, Integer quantity) {
		return null;
	}
	
	
}

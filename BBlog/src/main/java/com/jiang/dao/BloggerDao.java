package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Blogger;

public interface BloggerDao {
	
	public boolean insert(Blogger blogger);
	
	public boolean delete(Integer id);
	
	public boolean update(Blogger blogger);
	

	public Blogger findById(Integer id);

	public List<Blogger> findAll();

}

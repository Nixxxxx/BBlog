package com.jiang.service;

import java.util.List;

import com.jiang.entity.Blogger;

public interface BloggerService {

	
	public List<Blogger> findAll();
	
	public Blogger findById(int id);

	public boolean insert(Blogger user);

	public boolean update(Blogger user);
}

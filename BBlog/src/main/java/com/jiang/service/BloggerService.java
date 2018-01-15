package com.jiang.service;

import java.util.List;

import com.jiang.entity.Blogger;

/**
 * 博主service接口
 * @author JH
 *
 */
public interface BloggerService {
	
	public List<Blogger> findAll();
	
	public Blogger findOne(Integer id);

	public boolean save(Blogger user);

}

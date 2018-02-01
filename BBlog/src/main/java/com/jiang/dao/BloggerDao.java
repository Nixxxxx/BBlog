package com.jiang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jiang.entity.Blogger;

/**
 * 博主dao接口
 * @author JH
 *
 */
@Mapper
public interface BloggerDao {
	
	public boolean save(Blogger blogger);
	
	public boolean update(Blogger blogger);
	
	public boolean delete(Integer id);
	
	public Blogger findById(Integer id);

	public List<Blogger> findAll();
}

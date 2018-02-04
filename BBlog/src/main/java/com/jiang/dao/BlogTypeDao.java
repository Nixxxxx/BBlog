package com.jiang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jiang.entity.BlogType;

/**
 * 博客类型dao接口
 * @author JH
 *
 */
@Mapper
public interface BlogTypeDao {
	
	public boolean save(BlogType blogType);
	
	public boolean update(BlogType blogType);
	
	public boolean delete(Integer id);
	
	public BlogType findById(Integer id);
	
	public List<BlogType> findAll();

	/**
	 * 分页查询博客类型
	 * @param pageBean
	 * @return
	 */
	public List<BlogType> findList(Integer page, Integer quantity);

	/**
	 * 查询每个博客类型下的博客数量
	 * @return
	 */
	public List<BlogType> findcount();
	
}

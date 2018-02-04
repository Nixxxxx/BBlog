package com.jiang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jiang.entity.Blog;

/**
 * 博客dao接口
 * @author JH
 *
 */
@Mapper
public interface BlogDao {
	
	public boolean save(Blog blog);
	
	public boolean update(Blog blog);
	
	public boolean delete(Integer id);
	
	public Blog findById(Integer id);
	
	public List<Blog> findAll();
	
	/**
	 * 根据typeId分页查询博客
	 * @param pageBean
	 * @param typeId
	 * @return
	 */
	public List<Blog> findListByTypeId(Map<String, Object> map);
	
	/**
	 * 根据typeId查出所有博客
	 * @param typeId
	 * @return
	 */
	public List<Blog> findAllByTypeId(Integer typeId);
	
}

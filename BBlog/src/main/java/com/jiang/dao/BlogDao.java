package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Blog;
import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

/**
 * 博客dao接口
 * @author JH
 *
 */
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
	public List<Blog> findListByTypeId(PageBean pageBean, Integer typeId);
	
	/**
	 * 根据typeId查出所有博客
	 * @param typeId
	 * @return
	 */
	public List<Blog> findAllByTypeId(Integer typeId);
	
}

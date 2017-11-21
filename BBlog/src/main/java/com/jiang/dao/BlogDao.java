package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

/**
 * 博客dao接口
 * @author JH
 *
 */
public interface BlogDao {

	/**
	 * 插入博客
	 * @param blog
	 */
	public void insert(Blog blog);
	
	/**
	 * 更新博客
	 * @param blog
	 * @return
	 */
	public Blog update(Blog blog);

	/**
	 * 删除博客
	 * @param id
	 */
	public void delete(Integer id);
	
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
	public List<Blog> findByTypeId(Integer typeId);
	
	/**
	 * 查出所有博客
	 * @return
	 */
	public List<Blog> findAll();
	
	/**
	 * 根据id查询博客
	 * @param id
	 * @return
	 */
	public Blog findById(Integer id);

}

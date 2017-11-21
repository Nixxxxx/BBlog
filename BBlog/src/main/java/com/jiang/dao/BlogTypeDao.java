package com.jiang.dao;

import java.util.List;

import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

/**
 * 博客类型dao接口
 * @author JH
 *
 */
public interface BlogTypeDao {

	/**
	 * 添加博客类型
	 * @param blogType
	 */
	public void insert(BlogType blogType);

	/**
	 * 更新博客类型
	 * @param blogType
	 */
	public void update(BlogType blogType);

	/**
	 * 删除博客类型
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 分页查询博客类型
	 * @param pageBean
	 * @return
	 */
	public List<BlogType> findList(PageBean pageBean);

	/**
	 * 查询每个博客类型下的博客数量
	 * @return
	 */
	public List<BlogType> countList();
	
	/*
	 * 查询所有博客类型列表
	 */
	public List<BlogType> findAll();
	
	/**
	 * 根据id查询博客类型
	 * @param id
	 * @return
	 */
	public BlogType findById(Integer id);
	
}

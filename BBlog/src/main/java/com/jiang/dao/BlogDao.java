package com.jiang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;

/**
 * 博客dao接口
 * @author JH
 *
 */
public interface BlogDao extends JpaRepository<Blog, Integer>{
	
	/**
	 * 根据typeId分页查询博客
	 * @param pageBean
	 * @param typeId
	 * @return
	 */
	@Query(value="select * from t_film where id<?1 order by id desc limit 1",nativeQuery = true)
	public List<Blog> findListByTypeId(PageBean pageBean, Integer typeId);
	
	/**
	 * 根据typeId查出所有博客
	 * @param typeId
	 * @return
	 */
	@Query(value="select * from t_film where id<?1 order by id desc limit 1",nativeQuery = true)
	public List<Blog> findAllByTypeId(Integer typeId);
	
}

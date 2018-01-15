package com.jiang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

/**
 * 博客类型dao接口
 * @author JH
 *
 */
public interface BlogTypeDao extends JpaRepository<BlogType, Integer>{

	/**
	 * 分页查询博客类型
	 * @param pageBean
	 * @return
	 */
	@Query(value="select * from t_film where id<?1 order by id desc limit 1",nativeQuery = true)
	public List<BlogType> findList(PageBean pageBean);

	/**
	 * 查询每个博客类型下的博客数量
	 * @return
	 */
	@Query(value="select * from t_film where id<?1 order by id desc limit 1",nativeQuery = true)
	public List<BlogType> findcount();
	
}

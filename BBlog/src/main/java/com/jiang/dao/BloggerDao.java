package com.jiang.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiang.entity.Blogger;

/**
 * 博主dao接口
 * @author JH
 *
 */
public interface BloggerDao extends JpaRepository<Blogger, Integer>{
	

}

package com.jiang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

/**
 * 管理员dao接口
 * @author JH
 *
 */
public interface AdminDao extends JpaRepository<Admin, Integer>{
	
	/**
	 * 修改管理员密码
	 * @param id
	 * @param password
	 * @return
	 */
	@Query(value="select * from t_admin where id<?1 order by id desc limit 1",nativeQuery = true)
	public boolean changePassword(Integer id, String password);
	
	/**
	 * 分页查询管理员列表
	 * @param pageBean
	 * @return
	 */
	@Query(value="select * from t_admin where id<?1 order by id desc limit 1",nativeQuery = true)
	public List<Admin> findList(PageBean pageBean);

	/**
	 * 根据email查询管理员
	 * @param email
	 * @return
	 */
	@Query(value="select * from t_film where id<?1 order by id desc limit 1",nativeQuery = true)
	public Admin findByEmail(String email);
	
}

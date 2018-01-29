package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Admin;
import com.jiang.entity.BlogType;
import com.jiang.entity.PageBean;

/**
 * 管理员dao接口
 * @author JH
 *
 */
public interface AdminDao {
	
	public boolean save(Admin admin);
	
	public boolean update(Admin admin);
	
	public boolean delete(Integer id);
	
	public Admin findById(Integer id);
	
	public List<Admin> findAll();
	
	/**
	 * 修改管理员密码
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean changePassword(Integer id, String password);
	
	/**
	 * 分页查询管理员列表
	 * @param pageBean
	 * @return
	 */
	public List<Admin> findList(PageBean pageBean);

	/**
	 * 根据email查询管理员
	 * @param email
	 * @return
	 */
	public Admin findByEmail(String email);
	
}

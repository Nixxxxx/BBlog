package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

/**
 * 管理员dao接口
 * @author JH
 *
 */
public interface AdminDao {
	
	/**
	 * 添加管理员
	 * @param admin
	 * @return
	 */
	public boolean insert(Admin admin);
	
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 更新管理员
	 * @param admin
	 * @return
	 */
	public boolean update(Admin admin);
	
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
	 * 根据id查询管理员
	 * @param id
	 * @return
	 */
	public Admin findById(Integer id);

	/**
	 * 查出所有管理员
	 * @return
	 */
	public List<Admin> findAll();

	/**
	 * 根据email查询管理员
	 * @param email
	 * @return
	 */
	public Admin findByEmail(String email);
	
}

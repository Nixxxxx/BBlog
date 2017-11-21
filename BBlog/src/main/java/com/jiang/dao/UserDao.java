package com.jiang.dao;

import java.util.List;

import com.jiang.entity.PageBean;
import com.jiang.entity.User;

/**
 * 用户dao接口
 * @author JH
 *
 */
public interface UserDao {
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean insert(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean changePassword(Integer id, String password);
	
	/**
	 * 分页查询用户列表
	 * @param pageBean
	 * @return
	 */
	public List<User> findList(PageBean pageBean);
	
	/**
	 * 查询所有用户列表
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public User findById(Integer id);
	
	/**
	 * 根据用户email查找用户
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
	
}

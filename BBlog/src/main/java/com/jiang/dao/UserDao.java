package com.jiang.dao;

import java.util.List;

import com.jiang.entity.PageBean;
import com.jiang.entity.User;

public interface UserDao {
	
	public boolean insert(User user);
	
	public boolean delete(int id);
	
	public boolean update(User user);
	
	public boolean changePassword(int id, String password);
	
	public List<User> findList(PageBean pageBean);
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public User findByEmail(String email);
	
}

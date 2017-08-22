package com.jiang.service;

import java.util.List;

import com.jiang.entity.PageBean;
import com.jiang.entity.User;

public interface UserService {

	
	public List<User> findAll();

	public User findById(int id);

	public boolean insert(User user);

	public boolean update(User user);

	public List<User> findList(PageBean pageBean);

	public boolean delete(int id);
	
	public User findByEmail(String email);

}

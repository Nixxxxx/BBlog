package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.UserDao;
import com.jiang.entity.PageBean;
import com.jiang.entity.User;
import com.jiang.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

	public boolean insert(User user) {
		return userDao.insert(user);
	}

	public boolean update(User user) {
		return userDao.update(user);
	}

	public List<User> findList(PageBean pageBean) {
		return userDao.findList(pageBean);
	}

	public boolean delete(int id) {
		return userDao.delete(id);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}

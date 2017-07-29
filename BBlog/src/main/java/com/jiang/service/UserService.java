package com.jiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiang.dao.UserDao;
import com.jiang.entity.User;

@Service
public class UserService {

	@Resource
	private UserDao userDao;
	
	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

	public boolean save(User user) {
		return userDao.save(user);
	}

	public boolean update(User user) {
		return userDao.update(user);
	}

}

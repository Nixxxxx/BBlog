package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;

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

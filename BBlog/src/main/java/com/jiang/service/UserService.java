package com.jiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiang.dao.UserDao;
import com.jiang.entity.PageBean;
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

	public boolean insert(User user) {
		return userDao.insert(user);
	}

	public boolean update(User user) {
		return userDao.update(user);
	}

	public List<User> findList(PageBean pageBean) {
		return userDao.findList(pageBean);
	}

}

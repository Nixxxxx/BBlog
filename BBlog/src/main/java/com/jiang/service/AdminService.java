package com.jiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiang.dao.AdminDao;
import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

@Service
public class AdminService {
	
	@Resource
	private AdminDao adminDao;
	
	public List<Admin> findAll(){
		return adminDao.findAll();
	}
	
	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	public boolean insert(Admin admin) {
		return adminDao.insert(admin);
	}

	public boolean update(Admin admin) {
		return adminDao.update(admin);
	}

	public List<Admin> findList(PageBean pageBean) {
		return adminDao.findList(pageBean);
	}

	public boolean delete(int id) {
		return adminDao.delete(id);
	}
}

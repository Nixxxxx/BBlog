package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.AdminDao;
import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;
import com.jiang.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
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

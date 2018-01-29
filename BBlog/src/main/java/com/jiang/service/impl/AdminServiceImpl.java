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
	
	@Override
	public List<Admin> findAll(){
		return adminDao.findAll();
	}
	
	@Override
	public Admin findById(Integer id) {
		return adminDao.findById(id);
	}

	@Override
	public boolean save(Admin admin) {
		adminDao.save(admin);
		return true;
	}
	
	@Override
	public boolean delete(Integer id) {
		adminDao.delete(id);
		return false;
	}

	@Override
	public List<Admin> findList(PageBean pageBean) {
		return adminDao.findList(pageBean);
	}
	
	@Override
	public Admin findByEmail(String email){
		return adminDao.findByEmail(email);
	}

}

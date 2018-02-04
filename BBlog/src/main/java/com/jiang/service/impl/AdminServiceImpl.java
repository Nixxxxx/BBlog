package com.jiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.AdminDao;
import com.jiang.entity.Admin;
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
	public Admin findByEmail(String email){
		return adminDao.findByEmail(email);
	}
	
	@Override
	public List<Admin> findByPage(Integer page, int quantity) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", (page-1)*10);
		map.put("quantity", quantity);
		return adminDao.findByPage(map);
	}
	
	public boolean checkEmail(String email, Integer id) {
		List<Admin> admins = adminDao.findAll();
		for(Admin admin:admins){
			if(admin.getEmail().equals(email) && admin.getId() != id)
				return false;
		}
		return true;
	}

}

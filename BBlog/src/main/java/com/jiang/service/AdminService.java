package com.jiang.service;

import java.util.List;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

public interface AdminService {
	
	
	public List<Admin> findAll();
	
	public Admin findById(int id);

	public boolean insert(Admin admin);

	public boolean update(Admin admin);

	public List<Admin> findList(PageBean pageBean);

	public boolean delete(int id);

	public Admin findByEmail(String email);
}

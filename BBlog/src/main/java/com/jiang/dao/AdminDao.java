package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

public interface AdminDao {
	
	public boolean insert(Admin admin);
	
	public boolean delete(int id);
	
	public boolean update(Admin admin);
	
	public boolean changePassword(int id, String password);
	
	public List<Admin> findList(PageBean pageBean);

	public Admin findById(int id);

	public List<Admin> findAll();

	public Admin findByEmail(String email);
	
}

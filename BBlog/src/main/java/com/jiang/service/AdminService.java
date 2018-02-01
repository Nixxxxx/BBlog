package com.jiang.service;

import java.util.List;

import com.jiang.entity.Admin;
import com.jiang.entity.PageBean;

/**
 * 管理员service接口
 * @author JH
 *
 */
public interface AdminService {
	
	public List<Admin> findAll();
	
	public Admin findById(Integer id);

	public boolean save(Admin admin);

	public List<Admin> findList(PageBean pageBean);

	public boolean delete(Integer id);

	public Admin findByEmail(String email);
	
	public List<Admin> findByPage(Integer page, int quantity);
	
	public boolean checkEmail(String email, Integer id);
}

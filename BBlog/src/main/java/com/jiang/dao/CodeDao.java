package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Code;

public interface CodeDao {
	
	public boolean insert(Code code);
	
	public boolean delete(int id);
	
	public Code findById(int id);
	
	public List<Code> findAll();
	
}

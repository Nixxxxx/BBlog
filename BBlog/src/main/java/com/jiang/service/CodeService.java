package com.jiang.service;

import java.util.List;

import com.jiang.entity.Code;

public interface CodeService {

	
	public Code findById(Integer id);
	
	public List<Code> findAll();

	public boolean insert(Code code);

	public boolean delete(Integer id);

}

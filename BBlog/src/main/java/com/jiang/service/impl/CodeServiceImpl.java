package com.jiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.dao.CodeDao;
import com.jiang.entity.Code;
import com.jiang.service.CodeService;

@Service("codeService")
public class CodeServiceImpl implements CodeService{

	@Autowired
	private CodeDao codeDao;
	

	public Code findById(int id) {
		return codeDao.findById(id);
	}
	
	public List<Code> findAll(){
		return codeDao.findAll();
	}

	public boolean insert(Code code) {
		return codeDao.insert(code);
	}


	public boolean delete(int id) {
		return codeDao.delete(id);
	}

}

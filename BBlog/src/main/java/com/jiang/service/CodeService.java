package com.jiang.service;

import java.util.List;

import com.jiang.entity.Code;

/**
 * 注册激活码service接口
 * @author JH
 *
 */
public interface CodeService {
	
	public Code findById(Integer id);
	
	public List<Code> findAll();

	public boolean insert(Code code);

	public boolean delete(Integer id);

}

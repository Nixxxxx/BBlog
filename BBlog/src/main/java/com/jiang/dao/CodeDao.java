package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Code;

/**
 * 注册激活码dao接口
 * @author JH
 *
 */
public interface CodeDao {
	
	/**
	 * 添加注册激活码
	 * @param code
	 * @return
	 */
	public boolean insert(Code code);
	
	/**
	 * 删除注册激活码
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 根据id查询注册激活码
	 * @param id
	 * @return
	 */
	public Code findById(Integer id);
	
	/**
	 * 查询所有注册激活码列表
	 * @return
	 */
	public List<Code> findAll();
	
}

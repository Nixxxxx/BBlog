package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Blogger;

/**
 * 博主dao接口
 * @author JH
 *
 */
public interface BloggerDao {
	
	/**
	 * 添加博主
	 * @param blogger
	 * @return
	 */
	public boolean insert(Blogger blogger);
	
	/**
	 * 删除博主
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 更新博客
	 * @param blogger
	 * @return
	 */
	public boolean update(Blogger blogger);
	
	/**
	 * 根据id查找博主
	 * @param id
	 * @return
	 */
	public Blogger findById(Integer id);

	/**
	 * 查出所有博主
	 * @return
	 */
	public List<Blogger> findAll();

}

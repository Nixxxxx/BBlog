package com.jiang.dao;

import java.util.List;

import com.jiang.entity.Carousel;

public interface CarouselDao {
	
	public boolean save(Carousel carousel);
	
	public boolean update(Carousel carousel);
	
	public boolean delete(Integer id);
	
	public Carousel findById(Integer id);

	public List<Carousel> findAll();
}

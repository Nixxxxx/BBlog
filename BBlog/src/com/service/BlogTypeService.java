package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BlogTypeDao;

@Service
public class BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;
}

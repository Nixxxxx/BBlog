package com.jiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiang.service.BloggerService;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Autowired
	private BloggerService bloggerService;
	
}

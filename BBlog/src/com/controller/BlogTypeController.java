package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.BlogTypeService;

@Controller
@RequestMapping(value = "/blogType")
public class BlogTypeController {

	@Autowired
	private BlogTypeService blogTypeService;

	@RequestMapping(value = "/showList")
	public ModelAndView showList(){
		return new ModelAndView("blog/blogTypeList");
	}
	
	
}

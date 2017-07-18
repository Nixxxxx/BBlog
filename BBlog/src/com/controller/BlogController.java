package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.BlogService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	private String msg;
	private boolean result;
	private JSONObject resultJson=new JSONObject();
	
	@RequestMapping(value = "/writing")
	public ModelAndView writing(){
		return new ModelAndView("blog/blog");
	}
}

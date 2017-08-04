package com.jiang.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiang.service.BlogService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	private BlogService blogService;

}

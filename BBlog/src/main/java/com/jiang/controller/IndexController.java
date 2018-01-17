package com.jiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("foreground/index");
		return mav;
	}
}

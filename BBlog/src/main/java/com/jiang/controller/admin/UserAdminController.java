package com.jiang.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("userList",userService.findAll());
		mav.addObject("PagePath","./admin/user/list.jsp");
		return mav;
	}

	
}

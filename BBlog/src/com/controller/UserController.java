package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private String msg;
	private boolean success;
	private JSONObject resultJson=new JSONObject();
}

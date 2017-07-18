package com.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.service.UserService;
import com.util.MD5Util;
import com.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private String msg;
	private boolean result;
	private JSONObject resultJson=new JSONObject();
	
	@RequestMapping(value = "/signIn")
	public void signIn(HttpServletRequest request, HttpServletResponse response){
		result = false;
		String captcha = request.getParameter("captcha");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String checkbox = request.getParameter("checkbox");
		List<User> users = userService.findAll();
		String sRand = (String)request.getSession().getAttribute("sRand");
		if(captcha.equalsIgnoreCase(sRand)){
			for(User user:users){
				if(user.getEmail().equals(email)&&MD5Util.getMD5Code(password).equals(user.getPassword())){
					request.getSession().setAttribute("user", user);
					if("true".equals(checkbox)){
						Cookie cookie = new Cookie(user.getEmail(),user.getPassword());
						cookie.setMaxAge(1*60*60*24*7);
					}
					result = true;
					break;
				}else msg = "邮箱或密码错误";
			}
		}else msg = "验证码错误";
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	
	public boolean checkUserName(String userName, int id){
		List<User> users = userService.findAll();
		for (User user : users) {
			if (user.getUserName().equals(userName) && user.getId() != id)
				return false;
		}
		return true;
	}
	
	public boolean checkEmail(String email, int id){
		List<User> users = userService.findAll();
		for(User user:users){
			if(user.getEmail().equals(email) && user.getId() != id)
				return false;
		}
		return true;
	}

	@RequestMapping(value = "/signUp")
	public void signUp(HttpServletRequest request, HttpServletResponse response) {
		result = false;
		String captcha = request.getParameter("captcha");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String sRand = (String)request.getSession().getAttribute("sRand");
		if(captcha.equalsIgnoreCase(sRand)){
			if(!checkEmail(email, 0)){
				msg = "该邮箱已存在";
			}else if(!checkUserName(userName, 0)){
				msg = "该用户名已存在";
			}else {
				User user = new User();
				user.setEmail(email);
				user.setUserName(userName);
				user.setPassword(MD5Util.getMD5Code(password));
				if(userService.save(user)){
					result = true;
					request.getSession().setAttribute("user", user);
				}else msg = "注册失败";
			}
		}else msg = "验证码错误";
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}



	public String signOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return null;
	}

	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request, HttpServletResponse response) {
		result = false;
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String mood = request.getParameter("mood");
		if (!checkEmail(email, id)) {
			msg = "该邮箱已存在";
		} else if(!checkUserName(userName, id)){
			msg = "该用户名已存在";
		}else {
			User user = userService.findById(id);
			user.setEmail(email);
			user.setUserName(userName);
			user.setMood(mood);
			if(userService.update(user)){
				result = true;
				request.getSession().setAttribute("user", userService.findById(id));
			}
			else msg = "更新失败";
		}
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping(value = "/showMessage")
	public ModelAndView pageJump() {
		return new ModelAndView("user/message");
	}
	
}

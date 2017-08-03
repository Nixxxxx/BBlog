package com.jiang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.User;
import com.jiang.service.UserService;
import com.jiang.util.MD5Util;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private String msg;
	private boolean result;
	private JSONObject resultJson=new JSONObject();
	
	@RequestMapping("/signIn")
	public void signIn(User user, HttpServletRequest request, HttpServletResponse response){
		result = false;
		String sRand = (String)request.getSession().getAttribute("sRand");
		String captcha = request.getParameter("captcha");
		List<User> users = userService.findAll();
		if(captcha.equalsIgnoreCase(sRand)){
			for(User u:users){
				if(user.getEmail().equals(u.getEmail()) && MD5Util.getMD5Code(u.getPassword()).equals(u.getPassword())){
					request.getSession().setAttribute("user", u);
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

	@RequestMapping("/signUp")
	public void signUp(User user, HttpServletRequest request, HttpServletResponse response) {
		result = false;
		String captcha = request.getParameter("captcha");
		String sRand = (String)request.getSession().getAttribute("sRand");
		if(captcha.equalsIgnoreCase(sRand)){
			if(!checkEmail(user.getEmail(), 0)){
				msg = "该邮箱已存在";
			}else if(!checkUserName(user.getPassword(), 0)){
				msg = "该用户名已存在";
			}else {
				user.setPassword(MD5Util.getMD5Code(request.getParameter("password")));
				if(userService.insert(user)){
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

	@RequestMapping("/update")
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

	@RequestMapping("/message")
	public ModelAndView showMessage() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/user/message.jsp");
		return mav;
	}
	
}

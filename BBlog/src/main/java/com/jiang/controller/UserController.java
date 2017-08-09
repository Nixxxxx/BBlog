package com.jiang.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.User;
import com.jiang.service.UserService;
import com.jiang.util.MD5Util;
import com.jiang.util.RequestUtil;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/signIn")
	public void signIn(User u, HttpServletRequest request, HttpServletResponse response){
		List<User> users = userService.findAll();
		String msg = "";
		boolean result = false;
		JSONObject resultJson=new JSONObject();
		for(User user:users){
			if(u.getEmail().equals(user.getEmail()) && MD5Util.getMD5Code(u.getPassword()).equals(user.getPassword())){
				user.setSignInIP(RequestUtil.getRemoteIP(request));
				userService.update(user);
				request.getSession().setAttribute("user", user);
				result = true;
				break;
			}else {
				request.getSession().setAttribute("user", u);
				msg = "邮箱或密码错误";
			}
		}
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
	public void signUp(User user, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		boolean result = false;
		JSONObject resultJson=new JSONObject();
		if(captcha.equalsIgnoreCase(sRand)){
			if(!checkEmail(user.getEmail(), 0)){
				msg = "该邮箱已存在";
			}else if(!checkUserName(user.getPassword(), 0)){
				msg = "该用户名已存在";
			}else {
				user.setPassword(MD5Util.getMD5Code(request.getParameter("password")));
				user.setSignUpIP(RequestUtil.getRemoteIP(request));
				user.setSignInIP(user.getSignUpIP());
				if(userService.insert(user)){
					result = true;
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
	public void update(@RequestParam("imageFile") MultipartFile imageFile, User u,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		boolean result = false;
		if (!checkEmail(u.getEmail(), u.getId())) {
			msg = "该邮箱已存在";
		} else if(!checkUserName(u.getUserName(), u.getId())){
			msg = "该用户名已存在";
		} else {
			if(!imageFile.isEmpty()){
				String filePath = request.getServletContext().getRealPath("/");
				String imagePath = "static/uploadImage/userAvater/"+u.getId()+"."+imageFile.getOriginalFilename().split("\\.")[1];
				u.setImagePath(imagePath);
				try {
					imageFile.transferTo(new File(filePath +imagePath));
				} catch (Exception e) {
					e.printStackTrace();
					msg = "更新异常";
				}
			}else {
				u.setImagePath(userService.findById(u.getId()).getImagePath());
			}
			User user = userService.findById(u.getId());
			user.setEmail(u.getEmail());
			user.setUserName(u.getUserName());
			user.setImagePath(u.getImagePath());
			user.setMood(u.getMood());
			if(userService.update(user)){
				result = true;
				msg = "更新成功";
				request.getSession().setAttribute("user", userService.findById(u.getId()));
			}else msg = "更新失败";
		}
		if(result == true){
			response.sendRedirect("info");
		}else {
			JSONObject resultJson=new JSONObject();
			resultJson.put("result", result);
			resultJson.put("msg", msg);
			ResponseUtil.writeJson(response, resultJson);
		}
	}

	@RequestMapping("/info")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/user/info.jsp");
		return mav;
	}
	
}

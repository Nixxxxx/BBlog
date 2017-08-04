package com.jiang.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blogger;
import com.jiang.service.BloggerService;
import com.jiang.util.MD5Util;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Autowired
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	public void signIn(Blogger bgr, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response){
		List<Blogger> bloggers = bloggerService.findAll();
		String msg = "";
		boolean result = false;
		if(captcha.equalsIgnoreCase(sRand)){
			for(Blogger blogger:bloggers){
				if(bgr.getEmail().equals(blogger.getEmail())&&MD5Util.getMD5Code(bgr.getPassword()).equals(blogger.getPassword())){
					request.getSession().setAttribute("blogger", blogger);
					result = true;
					break;
				}else msg = "邮箱或密码错误";
			}
		}else msg = "验证码错误";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	
	public boolean checkUserName(String userName, int id){
		List<Blogger> bloggers = bloggerService.findAll();
		for (Blogger blogger : bloggers) {
			if (blogger.getUserName().equals(userName) && blogger.getId() != id)
				return false;
		}
		return true;
	}
	
	public boolean checkEmail(String email, int id){
		List<Blogger> bloggers = bloggerService.findAll();
		for(Blogger blogger:bloggers){
			if(blogger.getEmail().equals(email) && blogger.getId() != id)
				return false;
		}
		return true;
	}

	@RequestMapping(value = "/signUp")
	public void signUp(Blogger blogger, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String captcha = request.getParameter("captcha");
		String sRand = (String)request.getSession().getAttribute("sRand");
		String msg = "";
		if(captcha.equalsIgnoreCase(sRand)){
			if(!checkEmail(blogger.getEmail(), 0)){
				msg = "该邮箱已存在";
			}else if(!checkUserName(blogger.getUserName(), 0)){
				msg = "该用户名已存在";
			}else {
				blogger.setPassword(MD5Util.getMD5Code(blogger.getPassword()));
				if(bloggerService.insert(blogger)){
					result = true;
				}else msg = "注册失败";
			}
		}else msg = "验证码错误";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}



	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("blogger");
		return null;
	}

	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String msg = "";
		if (!checkEmail(email, id)) {
			msg = "该邮箱已存在";
		} else if(!checkUserName(userName, id)){
			msg = "该用户名已存在";
		}else {
			Blogger blogger = bloggerService.findById(id);
			blogger.setEmail(email);
			blogger.setUserName(userName);
			if(bloggerService.update(blogger)){
				result = true;
				request.getSession().setAttribute("user", bloggerService.findById(id));
			}
			else msg = "更新失败";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/message")
	public ModelAndView showMessage() {
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/blogger/blogger.jsp");
		return mav;
	}
}

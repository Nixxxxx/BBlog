package com.jiang.controller;

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

import com.jiang.entity.Admin;
import com.jiang.service.AdminService;
import com.jiang.service.BloggerService;
import com.jiang.util.CryptographyUtil;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/")
public class BloggerController {
	
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/info")
	public ModelAndView info(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("blogger", bloggerService.findById(1));
		mav.addObject("pagePath" , "./foreground/about.jsp");
		return mav;
	}
	
	@RequestMapping("/login")
	public void signIn(Admin adm, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response){
		List<Admin> admins = adminService.findAll();
		String msg = "";
		boolean result = false;
		if(captcha.equalsIgnoreCase(sRand)){
			for(Admin admin:admins){
				if(adm.getEmail().equals(admin.getEmail()) && CryptographyUtil.md5(adm.getPassword(), "jiang").equals(admin.getPassword())){
					request.getSession().setAttribute("admin", admin);
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
}

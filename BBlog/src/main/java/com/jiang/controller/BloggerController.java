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

import com.jiang.entity.Blogger;
import com.jiang.service.BloggerService;
import com.jiang.util.MD5Util;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Autowired
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	public void signIn(Blogger b, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response){
		List<Blogger> bloggers = bloggerService.findAll();
		String msg = "";
		boolean result = false;
		if(captcha.equalsIgnoreCase(sRand)){
			for(Blogger blogger:bloggers){
				if(b.getEmail().equals(blogger.getEmail())&&MD5Util.getMD5Code(b.getPassword()).equals(blogger.getPassword())){
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
}

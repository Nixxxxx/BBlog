package com.jiang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Admin;
import com.jiang.service.BloggerService;
import com.jiang.util.CryptographyUtil;

@Controller
@RequestMapping("/")
public class BloggerController {
	
	@Autowired
	private BloggerService bloggerService;

	@RequestMapping("/info")
	public ModelAndView info(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("blogger", bloggerService.findById(1));
		mav.addObject("pagePath" , "./foreground/about.jsp");
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/login")
	public Map<String, Object> login(Admin adm, @RequestParam String captcha,@SessionAttribute String sRand,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		String msg = "";
		boolean result = false;
		if(captcha.equalsIgnoreCase(sRand)){
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(adm.getEmail() , CryptographyUtil.md5(adm.getPassword(), "jiang"));
			try{
				subject.login(token);
//				Session session = subject.getSession();
//				System.out.println("sessionId:"+session.getId());
//				System.out.println("sessionHost:"+session.getHost());
//				System.out.println("sessionTimeout:"+session.getTimeout());
//				session.setAttribute("info", "session的数据");
				result = true;
			}catch(Exception e){
				e.printStackTrace();
				msg = "邮箱或密码错";
			}
		}else msg = "验证码错误";
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	
	
	
}
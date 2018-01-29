package com.jiang.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
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
	
	@RequestMapping("/upload")
	public String imgUpload(@RequestParam("upload") MultipartFile upload,
			HttpServletRequest request, HttpServletResponse response) throws IOException {  
        response.setCharacterEncoding("utf-8");  
        PrintWriter out = response.getWriter();  
        // CKEditor提交的很重要的一个参数  
        String callback = request.getParameter("CKEditorFuncNum");
		String fileName = UUID.randomUUID().toString().replace("-", "") 
				+ "."+upload.getOriginalFilename().split("\\.")[1];
		String imagePath = "/root/BBlog/image/upload/";
        if(!upload.isEmpty()){
			try {
				File file = new File(imagePath + fileName);
				if (!file.exists()) { // 如果路径不存在，创建  
					file.mkdirs();  
				} 
				upload.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
        out.println("<script type=\"text/javascript\">");  
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
                + ",'" + "/BBlog/image/upload/" + fileName + "','')");  
        out.println("</script>");  
        return null;  
    }
	
}
package com.jiang.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(value = {"/", "/index"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("foreground/index");
		mav.addObject("pagePath", "./index/main.ftl");
		return mav;
	}
	
	@PostMapping("/upload")
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

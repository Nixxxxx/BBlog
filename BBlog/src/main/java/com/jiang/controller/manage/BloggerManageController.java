package com.jiang.controller.manage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blogger;
import com.jiang.service.BloggerService;

@Controller
@RequestMapping("/manage/blogger")
public class BloggerManageController {

	@Autowired
	private BloggerService bloggerService;
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(@RequestParam("imageFile") MultipartFile imageFile, Blogger bgr, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> map = new HashMap<>();
		boolean result = false;
		String msg;
		if(!imageFile.isEmpty()){
			String fileName = "blogger" + "." + imageFile.getOriginalFilename().split("\\.")[1];
			String imagePath = "/root/BBlog/image/avater/";
			bgr.setAvatarPath("/BBlog/image/avater/"+fileName);
			try {
				File file = new File(imagePath+fileName);
				if (!file.exists()) { // 如果路径不存在，创建 
					file.mkdirs();  
				} 
			imageFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "更新异常";
			}
		}else {
			bgr.setAvatarPath(bloggerService.findById(1).getAvatarPath());
		}
		if(bloggerService.save(bgr)){
			result = true;
			msg = "更新成功";
		}else msg = "更新失败";
		if(result == true){
			response.sendRedirect("info");
		}else {
			map.put("result", result);
			map.put("msg", msg);
		}
		return map;
	}

	@RequestMapping("/info")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/bloggerInfo.jsp");
		mav.addObject("blogger", bloggerService.findById(1));
		return mav;
	}
}

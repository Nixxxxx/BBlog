package com.jiang.controller.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blogger;
import com.jiang.service.BloggerService;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Autowired
	private BloggerService bloggerService;
	
	@RequestMapping("/update")
	public void update(@RequestParam("imageFile") MultipartFile imageFile, Blogger bgr, 
			HttpServletRequest request, HttpServletResponse response){
		boolean result = false;
		String msg;
		if(!imageFile.isEmpty()){
			String filePath = request.getServletContext().getRealPath("/");
			String imagePath = "static/uploadImage/blogger/bloggerAvater."
					+imageFile.getOriginalFilename().split("\\.")[1];
			bgr.setImagePath(imagePath);
			try {
				imageFile.transferTo(new File(filePath +imagePath));
			} catch (Exception e) {
				e.printStackTrace();
				msg = "更新异常";
			}
		}else {
			bgr.setImagePath(bloggerService.findById(1).getImagePath());
		}
		if(bloggerService.update(bgr)){
			result = true;
			msg = "更新成功";
		}
		else msg = "更新失败";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/info")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/bloggerInfo.jsp");
		mav.addObject("blogger", bloggerService.findById(1));
		return mav;
	}
}

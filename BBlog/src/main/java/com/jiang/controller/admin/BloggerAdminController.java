package com.jiang.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blogger;
import com.jiang.service.BloggerService;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Autowired
	private BloggerService bloggerService;
	
	@RequestMapping(value = "/update")
	public void update(Blogger bgr, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		String msg = "";
		if(bloggerService.update(bgr)){
			result = true;
		}
		else msg = "更新失败";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/info")
	public ModelAndView showMessage() {
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./admin/blogger/info.jsp");
		return mav;
	}
}

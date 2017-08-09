package com.jiang.controller.admin;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiang.entity.BlogType;
import com.jiang.entity.Blogger;
import com.jiang.service.BlogTypeService;
import com.jiang.service.BloggerService;
import com.jiang.util.ResponseUtil;

/**
 * 管理员系统Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Autowired
	private BloggerService bloggerService;
	
	@Autowired
	private BlogTypeService blogTypeService;
	
	
	/**
	 * 刷新系统缓存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletResponse response,HttpServletRequest request)throws Exception{
		ServletContext application = request.getServletContext();
		Blogger blogger = bloggerService.findById(1); // 查询博主信息
		application.setAttribute("blogger", blogger);
		
		List<BlogType> blogTypeCountList = blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		
		ResponseUtil.writeJson(response, result);
		return null;
	}
}

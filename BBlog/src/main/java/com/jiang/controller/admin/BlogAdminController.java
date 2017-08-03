package com.jiang.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiang.entity.Blog;
import com.jiang.service.BlogService;
import com.jiang.service.BlogTypeService;
import com.jiang.util.ResponseUtil;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	public boolean check(String title, int typeId, int id){
		List<Blog> blogs = blogService.findAll();
		for(Blog blog:blogs){
			if(blog.getTitle().equals(title) && blog.getBlogType().getId() == typeId && blog.getId() != id)
				return false;
		}
		return true;
	}
	
	@RequestMapping("/insert")
	public void insert(HttpServletRequest request,HttpServletResponse response){
		int typeId = Integer.parseInt(request.getParameter("typeName"));
		String title = request.getParameter("title");
		boolean result;
		String msg;
		if(check(title, typeId, 0)){
			Blog blog = new Blog(typeId, title, request.getParameter("content"), new Date());
			result = blogService.insert(blog);
			msg = result?"":"保存失败";
		}else {
			msg = "类型名已存在";
			result = false;
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}
	
	@RequestMapping("/del")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		boolean result = blogService.delete(id);
		String msg = result?"":"删除失败";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		String title = request.getParameter("title");
		boolean result;
		String msg;
		if(check(title, typeId, id)){
			Blog blog = blogService.findById(id);
			blog.setBlogType(blogTypeService.findById(typeId));
			blog.setTitle(title);
			blog.setContent(request.getParameter("content"));
			blog.setUpdateTime(new Date());
			result = blogService.update(blog);
			msg = result?"":"更新失败";
		}else {
			msg = "文章已存在";
			result = false;
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

}
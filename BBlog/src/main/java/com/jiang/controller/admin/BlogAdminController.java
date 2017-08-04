package com.jiang.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public void insert(Blog blog, HttpServletRequest request,HttpServletResponse response){
		boolean result;
		String msg;
		if(check(blog.getTitle(), blog.getBlogType().getId(), 0)){
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
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
	public void delete(@RequestParam Integer id, HttpServletRequest request,HttpServletResponse response){
		boolean result = blogService.delete(id);
		String msg = result?"":"删除失败";
		JSONObject resultJson = new JSONObject();
		resultJson.put("result",result);
		resultJson.put("msg", msg);
		ResponseUtil.writeJson(response, resultJson);
	}

	@RequestMapping("/update")
	public void update(Blog bg, HttpServletRequest request, HttpServletResponse response){
		boolean result;
		String msg;
		if(check(bg.getTitle(), bg.getBlogType().getId(), bg.getId())){
			Blog blog = blogService.findById(bg.getId());
			blog.setBlogType(blogTypeService.findById(bg.getBlogType().getId()));
			blog.setTitle(bg.getTitle());
			blog.setContent(bg.getContent());
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
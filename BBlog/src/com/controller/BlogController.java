package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Blog;
import com.entity.PageBean;
import com.service.BlogService;
import com.service.BlogTypeService;
import com.util.PageUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	@RequestMapping("/write")
	public ModelAndView write(){
		ModelAndView mav = new ModelAndView("blog/write");
		return mav;
	}
	
	@RequestMapping("/read")
	public ModelAndView read(){
		ModelAndView mav = new ModelAndView("blog/read");
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView showList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("blog/blogList");
		String page = request.getParameter("page");
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		if (StringUtil.isEmpty(page)) {
			page = "1";
		} else {
			// s_Blog=(Blog) session.getAttribute("s_Blog");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		List<Blog> blogList = blogService.findByTypeId(pageBean, typeId);
		int total = blogService.findAll().size();
		String pageCode = PageUtil.rootPageTion("blog/showList", total, pageBean.getPage(),
				pageBean.getPageSize(), null, null);
		mav.addObject("pageCode", pageCode);
		mav.addObject("blogList", blogList);
		return mav;
	}
	
	public boolean check(String title, int typeId, int id){
		List<Blog> blogs = blogService.findAll();
		for(Blog blog:blogs){
			if(blog.getTitle().equals(title) && blog.getBlogType().getId() == typeId && blog.getId() != id)
				return false;
		}
		return true;
	}
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		int typeId = Integer.parseInt(request.getParameter("typeName"));
		String title = request.getParameter("title");
		boolean result;
		String msg;
		if(check(title, typeId, 0)){
			Blog blog = new Blog(typeId, title, request.getParameter("content"), new Date());
			result = blogService.save(blog);
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

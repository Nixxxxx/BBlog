package com.jiang.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;
import com.jiang.service.BlogService;
import com.jiang.service.BlogTypeService;
import com.jiang.util.PageUtil;
import com.jiang.util.ResponseUtil;
import com.jiang.util.StringUtil;

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
	
	@RequestMapping("/write")
	public ModelAndView write(){
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./blog/write.jsp");
		return mav;
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
	
	@RequestMapping("/articles/{id}")
	public ModelAndView read(@PathVariable("id") Integer id){
		Blog blog = blogService.findById(id);
		blog.setReader(blog.getReader()+1);
		blogService.update(blog);
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./foreground/blog/article.jsp");
		mav.addObject("blog", blogService.findById(id));
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			@RequestParam(required = false)String typeId, HttpServletRequest request){
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 5);
		int nowTypeId = (typeId == null?0:Integer.parseInt(typeId));
		List<Blog> blogList = blogService.findListByTypeId(pageBean, nowTypeId);
		int total = blogList.size();
		String pageCode = PageUtil.genPagination("admin/blog/list", total, pageBean.getPage(),pageBean.getPageSize(), "typeId="+nowTypeId+"&");
		ModelAndView mav = new ModelAndView("admin/index");
		mav.addObject("pagePath", "./blog/blogManage.jsp");
		mav.addObject("pageCode", pageCode);
		mav.addObject("blogList", blogList);
		return mav;
	}

}
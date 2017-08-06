package com.jiang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.entity.Blog;
import com.jiang.entity.PageBean;
import com.jiang.service.BlogService;
import com.jiang.util.PageUtil;
import com.jiang.util.StringUtil;


@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/articles/{id}")
	public ModelAndView read(@PathVariable("id") Integer id){
		Blog blog = blogService.findById(id);
		blog.setReader(blog.getReader()+1);
		blogService.update(blog);
		ModelAndView mav = new ModelAndView("index");
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
		int total;
		if(nowTypeId == 0){
			total = blogService.findAll().size();
		}else{
			total = blogService.findByTypeId(nowTypeId).size();
		}
		String pageCode = PageUtil.genPagination("blog/list", total, pageBean.getPage(),pageBean.getPageSize(), "typeId="+nowTypeId);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/blog/list.jsp");
		mav.addObject("pageCode", pageCode);
		mav.addObject("blogList", blogList);
		return mav;
	}
	
		
}

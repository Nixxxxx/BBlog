package com.jiang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/write")
	public ModelAndView write(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/blog/write.jsp");
		return mav;
	}
	
	@RequestMapping("/articles/{id}")
	public ModelAndView read(@PathVariable("id") Integer id){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/blog/article.jsp");
		Blog blog = blogService.findById(id);
		blog.setReader(blog.getReader()+1);
		blogService.update(blog);
		mav.addObject("blog", blogService.findById(id));
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView showList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/blog/list.jsp");
		String page = request.getParameter("page");
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		if (StringUtil.isEmpty(page)) {
			page = "1";
		} else {
			// s_Blog=(Blog) session.getAttribute("s_Blog");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 5);
		List<Blog> blogList = blogService.findByTypeId(pageBean, typeId);
		int total = blogService.findAll().size();
		String pageCode = PageUtil.rootPageTion("blog/list?typeId="+typeId+"&", total, pageBean.getPage(),
				pageBean.getPageSize(), null, null);
		mav.addObject("pageCode", pageCode);
		mav.addObject("blogList", blogList);
		return mav;
	}
	
		
}

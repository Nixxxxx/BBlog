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
import com.jiang.lucene.BlogIndex;
import com.jiang.service.BlogService;
import com.jiang.util.PageUtil;
import com.jiang.util.StringUtil;


@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	// 博客索引
	private BlogIndex blogIndex = new BlogIndex();
	
	
	@RequestMapping("/articles/{id}")
	public ModelAndView read(@PathVariable("id") Integer id){
		Blog blog = blogService.findById(id);
		blog.setReader(blog.getReader()+1);
		blogService.update(blog);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/blog/article.jsp");
		mav.addObject("blog", blogService.findById(id));
//		mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(id),blogService.getNextBlog(id)));
		mav.addObject("pageTitle", blog.getTitle()+"_Java开源博客系统");
		return mav;
	}

	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false)String page, 
			@RequestParam(required = false)String typeId, HttpServletRequest request) throws Exception{
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
	
	/**
	 * 根据关键字查询相关博客信息
	 * @param q
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search")
	public ModelAndView search(String q, String page, HttpServletRequest request)throws Exception{
		if(StringUtil.isEmpty(page)){
			page = "1";
		}
		List<Blog> blogList = blogIndex.searchBlog(q.trim());
		Integer toIndex = blogList.size() >= Integer.parseInt(page)*10?Integer.parseInt(page)*10:blogList.size();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pagePath", "./foreground/search.jsp");
		mav.addObject("blogList", blogList.subList((Integer.parseInt(page)-1)*10, toIndex));
		mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q, 10));
		mav.addObject("q", q);
		mav.addObject("resultTotal", blogList.size());
//		mav.addObject("pageTitle", "搜索关键字'"+q+"'结果页面_Java开源博客系统");
		return mav;
	}
	
	/**
	 * 获取下一篇博客和下一篇博客代码
	 * @param lastBlog
	 * @param nextBlog
	 * @return
	 */
//	private String genUpAndDownPageCode(Blog lastBlog,Blog nextBlog){
//		StringBuffer pageCode=new StringBuffer();
//		if(lastBlog == null || lastBlog.getId() == null){
//			pageCode.append("<p>上一篇：没有了</p>");
//		}else{
//			pageCode.append("<p>上一篇：<a href='blog/articles/"+lastBlog.getId()+"'>"+lastBlog.getTitle()+"</a></p>");
//		}
//		if(nextBlog==null || nextBlog.getId()==null){
//			pageCode.append("<p>下一篇：没有了</p>");
//		}else{
//			pageCode.append("<p>下一篇：<a href='blog/articles/"+nextBlog.getId()+"'>"+nextBlog.getTitle()+"</a></p>");
//		}
//		return pageCode.toString();
//	}
	
	/**
	 * 获取上一页，下一页代码 查询博客用到
	 * @param page 当前页
	 * @param totalNum 总记录数
	 * @param q 查询关键字
	 * @param pageSize 每页大小
	 * @param projectContext
	 * @return
	 */
	private String genUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize){
		long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		if(totalPage==0){
			return "";
		}else{
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager'>");
			if(page>1){
				pageCode.append("<li><a href='blog/search?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
			}else{
				pageCode.append("<li class='disabled'><a>上一页</a></li>");
			}
			if(page<totalPage){
				pageCode.append("<li><a href='/blog/search?page="+(page+1)+"&q="+q+"'>下一页</a></li>");				
			}else{
				pageCode.append("<li class='disabled'><a>下一页</a></li>");				
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
		
}

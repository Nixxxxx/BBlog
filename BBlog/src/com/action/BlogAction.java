package com.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.BlogDao;
import com.entity.Blog;
import com.entity.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.util.PageUtil;
import com.util.StringUtil;

public class BlogAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	private BlogDao blogDao=new BlogDao();
	private Blog blog;
	
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String blogSaveOrUpdate(){
		blog.setReleaseDate(new Date());
		Blog currentBlog=blogDao.saveOrUpdate(blog);
		request.setAttribute("currentBlog",currentBlog);
		return SUCCESS;
	}
	
	
	public String delete(){
		blogDao.delete(blog);
		request.removeAttribute("currentBlog");
		return SUCCESS;
	}
	
	public String writing(){
		int blogId=Integer.parseInt(request.getParameter("blogId"));
		Blog currentBlog=blogDao.findById(blogId);
		request.setAttribute("currentBlog",currentBlog);
		request.setAttribute("mainPage", "/back/blog/blog.jsp");
		return SUCCESS;
	}
	
	
	public String showBlogList(){
		String page=request.getParameter("page");
		int typeId=Integer.parseInt(request.getParameter("blogTypeId"));
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		List<Blog> blogList=blogDao.find(pageBean,typeId);
		int total=blogDao.findByTypeId(typeId).size();
		String pageCode=PageUtil.rootPageTion("blog!showBlogList",total, pageBean.getPage(),pageBean.getPageSize(),null,null);
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("blogList",blogList);
		request.setAttribute("mainPage","/back/blog/blogList.jsp");
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
}

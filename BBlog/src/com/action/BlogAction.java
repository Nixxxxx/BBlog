package com.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.BlogDao;
import com.entity.Blog;
import com.opensymphony.xwork2.ActionSupport;

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
		HttpSession session=request.getSession();
		blog.setReleaseDate(new Date());
		Blog currentBlog=blogDao.saveOrUpdate(blog);
		session.setAttribute("currentBlog",currentBlog);
		return SUCCESS;
	}
	
	
	public String blogDelete(){
		HttpSession session=request.getSession();
		blogDao.delete(blog);
		session.removeAttribute("currentBlog");
		return SUCCESS;
	}
	
	public String blogSearch(){
		int blogId=Integer.parseInt(request.getParameter("blogId"));
		HttpSession session=request.getSession();
		Blog currentBlog=blogDao.search(blogId);
		session.setAttribute("currentBlog",currentBlog);
		request.setAttribute("mainPage", "/blog/blog.jsp");
		return SUCCESS;
	}
	
	public String blogWriting(){
		HttpSession session=request.getSession();
		session.removeAttribute("currntBlog");
		request.setAttribute("mainPage", "/blog/writing.jsp");
		return SUCCESS;
	}
	
	public String blogListSearch(){
		int typeId=Integer.parseInt(request.getParameter("blogTypeId"));
		List<Blog> blogList=blogDao.blogListSearch(typeId);
		request.setAttribute("blogList",blogList);
		request.setAttribute("mainPage","/blog/blogList.jsp");
		return SUCCESS;
	}
	
	public String blogList(){
		HttpSession session=request.getSession();
		List<Blog> blogList=blogDao.getBlogs();
		session.setAttribute("blogList", blogList);
		request.setAttribute("mainPage", "/blog/blogList.jsp");
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
}

package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.BlogDao;
import com.dao.BlogTypeDao;
import com.entity.Blog;
import com.entity.BlogType;
import com.opensymphony.xwork2.ActionSupport;

public class BlogTypeAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	BlogTypeDao blogTypeDao=new BlogTypeDao();
	BlogDao blogDao=new BlogDao();
	BlogType blogType=new BlogType();
	
	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}


	public String blogTypeList(){
		HttpSession session=request.getSession();
		List<BlogType> blogTypeList=blogTypeDao.blogTypeList();
		session.setAttribute("blogTypeList", blogTypeList);
		session.setAttribute("mainPage", "blog/blogTypeList.jsp");
		return SUCCESS;
	}
	
	
	public String blogTypeSave() throws IOException{
		String typeName=request.getParameter("typeName");
		if(checkBlogTypeName(typeName)){
			BlogType blogType=new BlogType(typeName);
			blogTypeDao.save(blogType);
			request.setAttribute("result", true);
		}else {
			request.setAttribute("result", false);
		}
		blogTypeList();
		return SUCCESS;
	}
	
	
	public String blogTypeUpdate() throws IOException{
		int blogTypeId=Integer.parseInt(request.getParameter("blogTypeId"));
		String typeName=request.getParameter("typeName");
		if(checkBlogTypeName(typeName)){
			BlogType blogType=new BlogType(blogTypeId,typeName);
			blogTypeDao.update(blogType);
			request.setAttribute("result", true);
		}else {
			request.setAttribute("result", false);
		}
		blogTypeList();
		return SUCCESS;
	}
	
	public String blogTypeDelete(){
		List<Blog> blogs=blogDao.blogListSearch(blogType.getBlogTypeId());
		if(blogs.isEmpty()){
			blogTypeDao.delete(blogType);
			blogTypeList();
			request.setAttribute("result", true);
		}else request.setAttribute("result", false);
		return SUCCESS;
	}
	
	public void blogTypeCountList(){
		HttpSession session=request.getSession();
		List<BlogType> blogTypeCountList=blogTypeDao.blogTypeCountList();
		session.setAttribute("blogTypeCountList", blogTypeCountList);
	}
	
	public boolean checkBlogTypeName(String typeName) throws IOException {
		List<BlogType> blogTypeList=blogTypeDao.blogTypeList();
		int flag=1;
		for(BlogType blogType:blogTypeList){
			if(blogType.getTypeName().equals(typeName))
				flag=0;
		}
		if(flag==0){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
}

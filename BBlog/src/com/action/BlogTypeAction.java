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


	public String showBlogTypeList(){
		List<BlogType> blogTypeList=blogTypeDao.blogTypeList();
		request.setAttribute("blogTypeList", blogTypeList);
		request.setAttribute("mainPage", "/back/blog/blogTypeList.jsp");
		return SUCCESS;
	}
	
	
	public String save() throws IOException{
		String typeName=request.getParameter("typeName");
		if(checkBlogTypeName(typeName)){
			BlogType blogType=new BlogType(typeName);
			blogTypeDao.save(blogType);
			request.setAttribute("result", true);
		}else {
			request.setAttribute("result", false);
		}
		showBlogTypeList();
		return SUCCESS;
	}
	
	
	public String update() throws IOException{
		int blogTypeId=Integer.parseInt(request.getParameter("blogTypeId"));
		String typeName=request.getParameter("typeName");
		if(checkBlogTypeName(typeName)){
			BlogType blogType=new BlogType(blogTypeId,typeName);
			blogTypeDao.update(blogType);
			request.setAttribute("result", true);
		}else {
			request.setAttribute("result", false);
		}
		showBlogTypeList();
		return SUCCESS;
	}
	
	public String delete(){
		List<Blog> blogList=blogDao.findByTypeId(blogType.getBlogTypeId());
		if(blogList.isEmpty()){
			blogTypeDao.delete(blogType);
			showBlogTypeList();
			request.setAttribute("result", true);
		}else request.setAttribute("result", false);
		return SUCCESS;
	}
	
	public void blogTypeCountList(){
		List<BlogType> blogTypeCountList=blogTypeDao.blogTypeCountList();
		request.setAttribute("blogTypeCountList", blogTypeCountList);
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

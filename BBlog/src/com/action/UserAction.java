package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.UserDao;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ResponseUtil;

import net.sf.json.JSONObject;

public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	private UserDao userDao=new UserDao();
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public void checkUserName() throws IOException {
	    HttpServletResponse response = null;
        response = ServletActionContext.getResponse();
		String userName=request.getParameter("userName");
		String result=null;
		List<User> users=userDao.getUsers();
		JSONObject resultJson=new JSONObject();
		int flag=1;
		for(User user:users){
			if(user.getUserName().equals(userName))
				flag=0;
		}
		if(flag==0){
			result="该用户名已存在";
		}else{
			result="该用户名可用";
		}
		resultJson.put("result",result);
		ResponseUtil.writeJson(response,resultJson);
	}
	
	
	public String register(){
		HttpSession session=request.getSession();
		User currentUser = userDao.register(user);
		session.setAttribute("currentUser", currentUser);
		return SUCCESS;
	}
	
	
	public String login(){
		HttpSession session=request.getSession();
		User currentUser = userDao.login(user);
		if(currentUser==null){
			request.setAttribute("user", user);
			request.setAttribute("error", "用户名或密码错误！");
			return ERROR;
		}else{
//			if("remember-me".equals(remember)){
//				rememberMe(userName,password,response);
//			}
			session.setAttribute("currentUser", currentUser);
			return SUCCESS;
		}
	}

	
	public String logout(){
		request.getSession().removeAttribute("currentUser");
		return SUCCESS;
	}
	
	public String update(){
		HttpSession session=request.getSession();
		User currentUser = userDao.checkUserName(user.getUserName());
		if(currentUser!=null){
			request.setAttribute("error", "用户名已存在！");
			return SUCCESS;
		}else{
			currentUser=userDao.update(user);
			session.setAttribute("currentUser", currentUser);
			return SUCCESS;
		}
	}
	
	public void rememberMe(String userName,String password,HttpServletResponse response){
		Cookie user=new Cookie("user",userName+"-"+password);
		user.setMaxAge(1*60*60*24*7);
		response.addCookie(user);
	}


	public String pageJump(){
		String key=request.getParameter("key");
		request.getSession().setAttribute("mainPage","/user/"+key+".jsp");
		return SUCCESS;
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
}

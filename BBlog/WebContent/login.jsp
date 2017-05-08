<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.entity.User"%>
<%
	    if(request.getAttribute("user")==null){
		String userName=null;
		String password=null;
		
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("user")){
				userName=cookies[i].getValue().split("-")[0];
				password=cookies[i].getValue().split("-")[1];
			}
		}
		
		if(userName==null){
			userName="";
		}
		
		if(password==null){
			password="";
		}
		
		pageContext.setAttribute("user", new User(userName,password));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Nix's Blog - 登录</title>
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function checkForm(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		if(userName==null || userName==""){
			$("#error").text("用户名不能为空");
			return false;
		}else if(password==null || password==""){
			$("#error").text("密码不能为空");
			return false;
			}
		else return true;
	}
</script>
<style>
	body {
		background:#eee;
	}
</style>
</head>
<body>
   <div class="container">
       <div class="row">
           <div class="col-lg-4 col-lg-offset-4">
               <h3 class="text-center">登录</h3>
               <h5 class="text-center text-danger" id="error">${error }</h5>                   
			   <hr class="clean">
               <form class="form-horizontal" role="form" action="user!login" method="post" onsubmit="return checkForm()">
					<div class="form-group input-group">
                       <span class="input-group-addon">用户</span>
                       <input type="text" id="userName" class="form-control" name="user.userName" value="${user.userName}"
						maxlength="20" size="20" placeholder="Username">
                   </div>
                   <div class="form-group input-group">
                       <span class="input-group-addon">密码</span>
                       <input type="password" id="password" class="form-control" name="user.password" value="${user.password}"
						maxlength="8" placeholder="Password">
                   </div>

                   <div class="form-group">
						<input id="remember" name="remember" type="checkbox" value="remember-me">记住我 
                       <button type="submit" class="btn btn-success btn-block">登入</button>
                   </div>

					<div class="text-center m-t m-b">
						<a href="register.jsp">Forget password/Register</a>
					</div>
               </form>
           </div>
       </div>
   </div>

 
</body>
</html>
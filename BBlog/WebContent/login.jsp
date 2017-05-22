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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/avater.jpg">
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/statics/bootstrap3/css/bootstrap.min.css" rel="stylesheet">

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
               <h5 class="text-center text-danger invisible" id="error_msg">错误信息</h5>                   
			   <hr class="clean">
               <form id="login_form" class="form-horizontal">
					<div class="form-group input-group">
                       <span class="input-group-addon">用户</span>
                       <input type="text" id="userName" class="form-control" name="userName" value="${user.userName}"
						maxlength="20" size="20" placeholder="Username">
                   </div>
                   <div class="form-group input-group">
                       <span class="input-group-addon">密码</span>
                       <input type="password" id="password" class="form-control" name="password" value="${user.password}"
						maxlength="8" placeholder="Password">
                   </div>

                   <div class="form-group">
						<input id="remember" type="checkbox">记住我 
                       <button type="submit" class="btn btn-success btn-block">登入</button>
                   </div>

					<div class="text-center m-t m-b">
						<a href="register.jsp">Register</a>
					</div>
               </form>
           </div>
       </div>
   </div>
</body>
<script type="text/javascript">
	$(function () {
	    var $error_msg = $("#error_msg");

	    var show_error = function (error_msg) {
	        $error_msg.text(error_msg).removeClass("invisible");
	    };

	    $("#login_form").submit(function () {
	        $error_msg.addClass("invisible")
	        var userName = $.trim($("#userName").val());
	        var password = $.trim($("#password").val());
	        var u_pattern = /^[a-zA-Z0-9_@]{4,20}$/;
	        if (!u_pattern.test(userName)) {
	            show_error("请输入正确格式的用户名");
	            return false;
	        }
	        if (password == "") {
	            show_error("请输入密码");
	            return false;
	        }
	        if (!u_pattern.test(password)) {
	            show_error("请输入正确格式的密码");
	            return false;
	        }

	        
	        var login_btn = $("#login_btn");
	        $.ajax({
	            url: "user!login",
	            type: "POST",
	            data: {
	                userName: userName,
	                password: password,
	                checkbox:$("#remember").prop("checked")
	            },
	            dataType: "json",
	            beforeSend: function () {
	                login_btn.button("loading");
	            },
	            complete: function () {
	                //重置登录按钮
	                login_btn.button("reset");
	            },
	            success: function (data) {
	            	if(data.success){
	            		window.location.href ="user!main";
	            	}else{
	                    show_error(data.result);
	            	}
	            },
	            error: function (XMLHttpRequest, textStatus) {
	                if (textStatus == "timeout") {
	                    show_error("登录超时");
	                } else {
	                    show_error("登录失败");
	                }
	            }
	        });
	        return false;
	    });
	})
</script>
</html>
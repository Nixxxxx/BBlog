<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nix's Blog - 注册</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/avater.jpg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
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
               <h3 class="text-center">注册</h3>
               <h5 class="text-center text-danger invisible" id="error_msg">错误信息</h5>            
			   <hr class="clean">
               <form class="form-horizontal" action="user!register" id="register_form" method="post">
					<div class="form-group input-group">
                       <span class="input-group-addon">用户</span>
                       <input type="text" id="userName" class="form-control" name="user.userName" value="${user.userName}"
						onblur="checkUserName()" maxlength="20" size="20" placeholder="Username">
                   </div>
                   <div class="form-group input-group">
                       <span class="input-group-addon">密码</span>
                       <input type="password" id="password" class="form-control" name="user.password" value="${user.password}"
						onblur="checkPassword()" maxlength="8" placeholder="Password">
                   </div>
                   <div class="form-group input-group">
                       <span class="input-group-addon">确认</span>
                       <input type="password" id="confirmPassword" class="form-control" name="confirmPassword"
						onblur="checkPassword()" maxlength="8" placeholder="Password">
                   </div>

                   <div class="form-group"> 
                     <div class="col-lg-6">
                       <input type="button" class="btn btn-success btn-block" onclick="register()" value="注册"/>
                     </div>
                     <div class="col-lg-6">
                       <input type="button" class="btn btn-success btn-block" onclick="resetting()" value="重置"/>
                     </div>
                   </div>

					<div class="text-center m-t m-b">
						<a href="login.jsp">Login</a>
					</div>
					 
	               <font id="tip" color="red"></font>  
	               <font id="tip1" color="red"></font>  
	               <font id="tip2" color="red"></font>  
               </form>
           </div>
       </div>
   </div>
</body>
<script type="text/javascript">
	function register(){
		if($("#userName").val()!=""&&$("#tip").text()=="该用户名可用" &&$("#password").val()!=""&&
				$("#password").val()==$("#confirmPassword").val()){
			alert("注册成功，前去登录");
			$("#register_form").submit();
		}else $("#error").text("请填写合法信息");
	}
	
	function resetting(){
		$("#userName").val("");
		$("#password").val("");
		$("#confirmPassword").val("");
		$("#tip").text("");
		$("#tip1").text("");
		$("#tip2").text("");
	}
	
	function checkUserName(){
		var userName = $("#userName").val(); 
		if(userName==""||userName==null)
			$("#tip").text("用户名不能为空！");
		else	
			$.ajax({    
		        url : "user!checkUserName.action",    
		        type : "POST", 
		        dataType: json,
		        data : {userName:userName},   
		        success : function(data){ 
		        	$("#tip").text(data.result);
		        }    
		    });
	}
	
	 function checkPassword(){
		if($("#password").val()=="")
			$("#tip1").text("密码不能为空!");
		else if($("#confirmPassword").val()==""){
			$("#tip2").text("验证密码不能为空!");
			$("#tip1").text("");
		}else if($("#password").val()!=$("#confirmPassword").val()){
			$("#tip2").text("两次输入密码不同!");
			$("#tip1").text("");
		}else {
			$("#tip1").text("");
			$("#tip2").text("");
		}
	} 
</script>
</html>
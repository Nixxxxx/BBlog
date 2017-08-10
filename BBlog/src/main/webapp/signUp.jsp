<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nix's Blog - 注册</title>
<link rel="shortcut icon" href="static/images/avater.jpg">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<style>
body {
	background: #eee;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<h3 class="text-center">注册</h3>
				<h5 class="text-center text-danger invisible" id="errorMsg">错误信息</h5>
				<hr class="clean">
				<form class="form-horizontal" id="signUpForm">
					<div class="text-danger wrapper-xs text-center invisible"
						id="errorMsg">错误信息</div>
					<div class="form-group input-group">
						<span class="input-group-addon">用&nbsp;&nbsp;&nbsp;户</span> 
						<input type="text" id="userName" class="form-control" maxlength="20" placeholder="Username" required>
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;箱</span> 
						<input type="text" id="email" class="form-control" maxlength="20" placeholder="Email" required>
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span> 
						<input type="password" id="password" class="form-control" maxlength="20" placeholder="Password" required>
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon">确&nbsp;&nbsp;&nbsp;认</span> 
						<input type="password" id="confirmPassword" class="form-control" maxlength="20" placeholder="Password" required>
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon">验证码</span>
						<div class="row">
							<div class="col-xs-7">
								<input type="text" class="form-control no-border" id="captcha" size=8 value="${imageCode }" placeholder="Captcha">
							</div>
							<div class="col-xs-5">
								<img id="randImage" name="randImage"
									style="cursor: pointer; height: 34px; width: 100%"
									title="点击可更换" onclick="javascript:loadImage();" src="image.jsp">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-6">
							<input type="submit" id="signUpBtn" class="btn btn-success btn-block" value="注册" />
						</div>
						<div class="col-lg-6">
							<input type="button" class="btn btn-success btn-block" value="重置" />
						</div>
					</div>

					<div class="text-center m-t m-b">
						<a href="signIn.jsp">Sign in</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(function() {
		var errorMsg = $("#errorMsg");

		var showError = function(msg) {
			errorMsg.text(msg).removeClass("invisible");
		};

		$("#signUpForm").submit(function() {
			errorMsg.addClass("invisible")
			var email = $.trim($("#email").val());
			var userName = $.trim($("#userName").val());
			var password = $.trim($("#password").val());
			var confirmPassword = $.trim($("#confirmPassword").val());
			var captcha = $.trim($("#captcha").val());
			var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var uPattern = /^[a-zA-Z0-9_@]{4,20}$/;
			var cPattern = /^[a-zA-Z0-9]{4}$/;
			if (!cPattern.test(captcha)) {
				showError("请输入正确格式的验证码");
				return false;
			}
			if(password.localeCompare(confirmPassword) != 0){
				showError("两次输入密码不同");
				return false;
			}
			if (!uPattern.test(userName)) {
				showError("请输入正确格式的用户名");
				return false;
			}
			if (!ePattern.test(email)) {
				showError("请输入正确格式的邮箱");
				return false;
			}
			if (!uPattern.test(password)) {
				showError("请输入正确格式的密码");
				return false;
			}
			var signUpBtn = $("#signUpBtn");
			$.ajax({
				url : "user/signUp",
				type : "post",
				data : {
					userName : userName,
					password : password,
					email : email,
					captcha : captcha,
				},
				dataType : "json",
				beforeSend : function() {
					signUpBtn.button("loading");
				},
				complete : function() {
					//重置登录按钮
					signUpBtn.button("reset");
					//重置验证码
					$("#randImage").trigger("click");
				},
				success : function(data) {
					if (data.result) {
						alert(data.msg);
						window.location.href = "signIn.jsp";
					} else {
						showError(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus) {
					if (textStatus == "timeout") {
						showError("注册超时");
					} else {
						showError("注册失败");
					}
				}
			});
			return false;
		});
	})

	function loadImage() {
		$("#randImage").attr("src", "image.jsp?" + Math.random()); //document.getElementById("randImage")定义一个img对象     src="image.jsp?"指定对象        +Math.random()为了使每次访问image.jsp的地址不一样，这样浏览器不会取本地缓存的数据。
	}
</script>
</html>
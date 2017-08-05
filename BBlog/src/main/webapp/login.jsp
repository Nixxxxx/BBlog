<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<title>Nix's Blog - 登录</title>
<link rel="shortcut icon" href="static/images/avater.jpg">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
body {
	background: #eee;
}
</style>
</head>
<body>
	<div class="container  w-xxl w-auto-xs">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<hr class="clean">
				<h3 class="text-center">Sign in</h3>
				<hr class="clean">
				<form id="signInForm" class="form-horizontal">
					<div class="text-danger wrapper-xs text-center invisible" id="errorMsg">错误信息</div>
					<div class="form-group input-group">
						<span class="input-group-addon">邮&nbsp&nbsp&nbsp箱</span> 
						<input type="text" class="form-control" id="email" placeholder="Email">
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon">密&nbsp&nbsp&nbsp码</span> 
						<input type="password" class="form-control" id="password" placeholder="Password">
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon">验证码</span> 
						<div class="row">
							<div class="col-xs-7">
								<input type="text" class="form-control no-border" id="captcha" value="${imageCode }" placeholder="Captcha">
							</div>
							<div class="col-xs-5">
								<img id="randImage" name="randImage" style="cursor: pointer; height: 34px; width: 100%"
									title="点击可更换" onclick="javascript:loadImage();" src="image.jsp">
							</div>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success btn-block">登入</button>
					</div>

					<div class="text-center m-t m-b">
						<a href="signUp.jsp">Sign up</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		var errorMsg = $("#errorMsg");

		var showError = function(msg) {
			errorMsg.text(msg).removeClass("invisible");
		};

		$("#signInForm").submit(function() {
			errorMsg.addClass("invisible")
			var email = $.trim($("#email").val());
			var password = $.trim($("#password").val());
			var captcha = $.trim($("#captcha").val());
			var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var uPattern = /^[a-zA-Z0-9_@.]{4,20}$/;
			var cPattern = /^[a-zA-Z0-9]{4}$/;
			if (password == "") {
				showError("请输入密码");
				return false;
			}
			if (!cPattern.test(captcha)) {
				showError("请输入正确格式的验证码");
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
			var signInBtn = $("#signInBtn");
			$.ajax({
				url : "admin/login",
				type : "post",
				data : {
					email : email,
					password : password,
					captcha : captcha,
				},
				dataType : "json",
				beforeSend : function() {
					signInBtn.button("loading");
				},
				complete : function() {
					//重置登录按钮
					signInBtn.button("reset");
					//重置验证码
					$("#randImage").trigger("click");
				},
				success : function(data) {
					if (data.result) {
						window.location.href = "admin/index";
					} else {
						showError(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus) {
					if (textStatus == "timeout") {
						showError("登录超时");
					} else {
						showError("登录失败");
					}
				}
			});
			return false;
		});
	})

	function loadImage() {
		$("#randImage").attr("src", "image.jsp?" + Math.random()); //src="image.jsp?"指定对象        +Math.random()为了使每次访问image.jsp的地址不一样，这样浏览器不会取本地缓存的数据。
	}
</script>
</body>
</html>
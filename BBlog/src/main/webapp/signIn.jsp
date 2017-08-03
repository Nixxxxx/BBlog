<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java开源博客系统</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/avater.jpg">
<!-- 用户登陆页css样式 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/signIn.css">
</head>
<body>
<div class="top_div">
</div>
<form id="signInForm">
	<div style="background:#ffffff; margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
			<div class="tou">
			</div>
			<div class="initial_left_hand" id="left_hand">
			</div>
			<div class="initial_right_hand" id="right_hand">
			</div>
		</div>
		<P style="padding: 30px 0px 10px; position: relative;">
			<span class="u_logo"></span>
			<input id="email" name="email" class="ipt" type="text" placeholder="Email" value="${user.email }" required> 
	    </P>
		<P style="position: relative;">
			<span class="p_logo"></span>         
			<input id="password" name="password" class="ipt" type="password" placeholder="Password" value="${user.password }" required>   
	  	</P>
		<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
			<P style="margin: 0px 35px 20px 45px;">
			<a href="signUp.jsp" style="float: left; color:#008ead;">Sign up</a>
			<font color="red invisible" id="errorMsg"></font>
	        <span style="float: right;"> 
	          <input id="signInBtn" type="submit" style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" value="登录"/> 
	        </span>         
	        </P>
	    </div>
	</div>
</form>
<div style="text-align:center;padding-top: 30px">
Copyright © 2012-2016 Java知识分享网 版权所有
</div>
   
   
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		//得到焦点
		$("#password").focus(function(){
			$("#left_hand").animate({
				left: "150",
				top: " -38"
			},{step: function(){
				if(parseInt($("#left_hand").css("left"))>140){
					$("#left_hand").attr("class","left_hand");
				}
			}}, 2000);
			$("#right_hand").animate({
				right: "-64",
				top: "-38px"
			},{step: function(){
				if(parseInt($("#right_hand").css("right"))> -70){
					$("#right_hand").attr("class","right_hand");
				}
			}}, 2000);
		});
		//失去焦点
		$("#password").blur(function(){
			$("#left_hand").attr("class","initial_left_hand");
			$("#left_hand").attr("style","left:100px;top:-12px;");
			$("#right_hand").attr("class","initial_right_hand");
			$("#right_hand").attr("style","right:-112px;top:-12px");
		});
		
		
		
		var errorMsg = $("#errorMsg");

		var showError = function(msg) {
			errorMsg.text(msg).removeClass("invisible");
		};

		$("#signInForm").submit(function() {
			errorMsg.addClass("invisible")
			var email = $.trim($("#email").val());
			var password = $.trim($("#password").val());
			var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var uPattern = /^[a-zA-Z0-9_@.]{4,20}$/;
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
				url : "user/signIn",
				type : "post",
				data : {
					email : email,
					password : password
				},
				dataType : "json",
				beforeSend : function() {
					signInBtn.button("loading");
				},
				complete : function() {
					//重置登录按钮
					signInBtn.button("reset");
				},
				success : function(data) {
					if (data.result) {
						window.location.href = "index.jsp";
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

</script>
</body>
</html>
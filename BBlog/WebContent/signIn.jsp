<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
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
               <h5 class="text-center text-danger invisible" id="errorMsg">错误信息</h5>                   
			   <hr class="clean">
               <form id="login_form" class="form-horizontal">
					<div class="form-group input-group">
                       <span class="input-group-addon">用户</span>
                       <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" placeholder="Username">
                   </div>
                   <div class="form-group input-group">
                       <span class="input-group-addon">密码</span>
                       <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="Password">
                   </div>
                   <div class="form-group input-group">
                    <div class="row">
                        <div class="col-xs-7">
                            <input type="text" class="form-control no-border" id="captcha" name="captcha" size=8 value="${imageCode }" placeholder="Captcha">
                        </div>
                        <div class="col-xs-5">
                            <img id="randImage" name="randImage" style="cursor: pointer;height: 34px;width: 100%" title="点击可更换"
                                 onclick="javascript:loadImage();"src="image.jsp">
                        </div>
                    </div>
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
    var $errorMsg = $("#errorMsg");

    var show_error = function (errorMsg) {
        $errorMsg.text(errorMsg).removeClass("invisible");
    };

    $("#signInForm").submit(function () {
        $errorMsg.addClass("invisible")
        var adminName = $.trim($("#userName").val());
        var password = $.trim($("#password").val());
        var captcha = $.trim($("#captcha").val());
        var u_pattern = /^[a-zA-Z0-9_@]{4,20}$/;
        var c_pattern = /^[a-zA-Z0-9]{4}$/;
        if (!u_pattern.test(adminName)) {
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
        if (!c_pattern.test(captcha)) {
            show_error("请输入正确格式的验证码");
            return false;
        }
        var $signInBtn = $("#signInBtn");
        if($("#type").val() == 1){
        	url = "admin/signIn";
        	href = "admin/index";
        }else{
        	url = "user/signIn";
        	href = "index.jsp";
        }
        $.ajax({
            url: url,
            type: "POST",
            data: {
            	adminName: adminName,
                password: password,
                captcha: captcha,
                checkbox: $("#checkbox").prop("checked")
            },
            dataType: "json",
            beforeSend: function () {
                $signInBtn.button("loading");
            },
            complete: function () {
                //重置登录按钮
                $signInBtn.button("reset");
                //重置验证码
                $("#randImage").trigger("click");
            },
            success: function (data) {
            	if(data.result == ""){
            		window.location.href = href;
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
    
    
    function loadImage(){
  		$("#randImage").attr("src","image.jsp?"+Math.random());  //document.getElementById("randImage")定义一个img对象     src="image.jsp?"指定对象        +Math.random()为了使每次访问image.jsp的地址不一样，这样浏览器不会取本地缓存的数据。
  	}
</script>
</html>
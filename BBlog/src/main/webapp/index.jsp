<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>Nix的个人博客</title>
<base href="<%=basePath%>">
<!-- logo -->
<link rel="shortcut icon" href="static/images/avater.jpg">
<link rel="stylesheet" href="static/css/index.css">
<link rel="stylesheet" href="static/css/blog.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: #eee;">
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
	  <div class="container-fluid container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#">Nix&nbsp;</a>
	    </div>
	    
	
	    <div class="collapse navbar-collapse">
		    <ul class="nav navbar-nav">
		    	<li><a href="blog/list">博客</a></li>
		        <li><a href="#">留言</a></li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">工具<span class="caret"> </span></a>
					<ul class="dropdown-menu">
		        	<li><a href="waterMark/index">图片水印</a></li>
		  			<li><a href="signIn.jsp">切换账号</a> </li>
			 		</ul> 
			 	</li> 
		    </ul>
	      
	      
		<c:if test="${user != null }">
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.userName }<span class="caret"> </span></a>
				<ul class="dropdown-menu">
				<li><a href="#">当前用户：${user.userName }</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="user/info">个人中心</a></li>
	  			<li><a href="signIn.jsp">切换账号</a> </li>
		 		<li><a href="javascript:signOut()">退出</a></li>
		 		</ul> 
		 	</li> 
		</ul> 
		</c:if>
		<c:if test="${user == null }">
		<ul class="nav navbar-nav navbar-right">
			<li ><a href="signIn.jsp">登录</a>
		 	</li>
		 </ul>
		</c:if>
	  </div>
	 </div>
	</div>
</div>


<div class="container" style="padding-top:75px;">
	<div class="row-fluid">
		<div class="col-md-9">
			<c:if test="${pagePath != null }">
			<jsp:include page="${pagePath }"></jsp:include>
			</c:if>
		</div>
		<div class="col-md-3">
	        <form class="form-horizontal" action="" method="post">
	            <input  id="search" class="form-control" name="s" type="text">
	            <button type="submit" style="display:none" class="animation red-btn"><span class="glyphicon glyphicon glyphicon-search"></span></button>
	        </form> 
			<div class="card">
	            <div class="header" style="background:url(static/images/avater.jpg)"></div>
	            <div class="avater">
	                <img alt="avater" src="${blogger.imagePath }">
	            </div>
	            <div class="content">
	                <h3>Nix</h3>
	                <ul class="status">
	                    <li><span class="normal">99</span><br>文章</li>
	                    <li><span class="normal">99</span><br>精选</li>
	                    <li><span class="normal">99</span><br>分类</li>
	                </ul>
	            </div>
            <div style="clear:both;"></div>
        	</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<span class="glyphicon glyphicon-th-list"></span>&nbsp;分类
				</div>
				<ul class="nav nav-sidebar">
		 			<c:if test="${blogTypeCountList != null }">
					<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
		            <li class="active">
		                <a href="blog/list?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }<sup>${blogTypeCount.count }</sup></a>
		            </li>
		            </c:forEach>
		            </c:if>
	       		</ul>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./foreground/common/footer.jsp"/>
     
       
<a class="to-top" id="toTop" style="cursor: pointer; display:none;">
	<span class="topicon"><i class="glyphicon glyphicon-chevron-up"></i></span>
	<span class="toptext">Top</span>
</a> 
       
<script type="text/javascript">
	$(function(){
		
		function signOut(){
			if(confirm("您确定要退出系统吗？")){
				window.location.href = "user/signOut";
			}
		}
		
		
		
		//回到顶部
		var clientHeight = document.documentElement.clientHeight;
		var timer = null;
		var isTop = true;
		
		window.onscroll = function(){
			var osTop = document.documentElement.scrollTop || document.body.scrollTop;
			if(osTop >= clientHeight){
				$("#toTop").css("display","block");
			}else{
				$("#toTop").css("display","none");
			}
			if(!isTop){
				clearInterval(timer);
			}
			isTop = false;
		}
		
		$("#toTop").click(function(){
			timer = setInterval(function(){
				var osTop = document.documentElement.scrollTop||document.body.scrollTop;
				var speed = Math.floor(-osTop/6);
				document.documentElement.scrollTop = document.body.scrollTop = osTop+speed;
				isTop = true;
				if(osTop == 0){
					clearInterval(timer);
				}
		},30);
		})
	})
	
</script>       

</body>
</html>
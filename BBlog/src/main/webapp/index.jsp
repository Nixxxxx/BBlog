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
<link rel="stylesheet" href="static/css/main.min.css">
<link href="http://apps.bdimg.com/libs/fontawesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: #eee;">

<jsp:include page="./foreground/common/top.jsp"/>
<div class="container" style="padding-top:75px;">
	<div class="row-fluid">
		<div class="col-md-9">
			<c:if test="${pagePath != null }">
			<jsp:include page="${pagePath }"></jsp:include>
			</c:if>
		</div>
		<div class="col-md-3">
	        <form class="form-horizontal" action="blog/search" method="post">
	            <input type="text" class="form-control" name="q" placeholder="搜索">
	        </form> 
			<div class="card">
	            <div class="header" style="background:url(static/images/avater.jpg)"></div>
	            <div class="avater">
	                <img alt="avater" src="${blogger.imagePath }">
	            </div>
	            <div class="content">
	                <h3>${blogger.userName }</h3>
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
  
<a class="to-top" id="toTop" style="cursor: pointer; display:none;">
	<span class="topicon"><i class="glyphicon glyphicon-chevron-up"></i></span>
	<span class="toptext">Top</span>
</a> 

<jsp:include page="./foreground/common/footer.jsp"/>
     
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
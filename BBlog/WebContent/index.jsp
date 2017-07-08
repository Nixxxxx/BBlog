<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Nix的个人博客</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/avater.jpg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/blog.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap-theme.min.css">

</head>
<body>
	<s:action name="blogType!blogTypeCountList" namespace="/" executeResult="true">
	</s:action>
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
	    
	
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    <ul class="nav navbar-nav">
		    	<li><a href="blog!showBlogList?blogTypeId=0">博客</a></li>
		        <li><a href="main!pageJump?key=message">留言 </a></li>
		        <li><a href="about.jsp">关于</a></li>
		     </ul>
	      
	      
		<c:if test="${currentUser!=null }">
		<div>
		 <ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${currentUser.userName }<span class="caret"> </span></a>
				<ul class="dropdown-menu">
				<li><a href="#">当前用户：${currentUser.userName }</a></li>
				<li role="separator" class="divider"></li>
			<c:if test="${currentUser.administrator==1 }">
				<li><a href="blogType!showBlogTypeList">管理</a></li>
				<li><a href="blog!writing">写博客</a></li>
			</c:if>
				<li><a href="#">私信</a></li>
	 			<li role="separator" class="divider"></li>
				<li><a href="user!pageJump?key=message">个人中心</a></li>
	  			<li><a href="login.jsp">切换账号</a> </li>
		 		<li><a href="javascript:logout()">退出</a></li>
		 		</ul> 
		 	</li> 
		 </ul> 
		</div>
		</c:if>
		<c:if test="${currentUser==null }">
		<ul class="nav navbar-nav navbar-right">
			<li ><a href="login.jsp">登录</a>
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
			<c:if test="${mainPage!=null }">
			<jsp:include page="${mainPage }"></jsp:include>
			</c:if>
		</div>
		<div class="col-md-3">
	        <form class="form-horizontal" action="" method="post">
	            <input  id="search" class="form-control" name="s" type="text" value="" >
	            <button type="submit" style="display:none" class="animation red-btn"><span class="glyphicon glyphicon glyphicon-search"></span></button>
	        </form> 
			<div class="card">
	            <div class="header" style="background:url(${pageContext.request.contextPath}/statics/images/avater.jpg)"></div>
	            <div class="avater">
	                <img alt="avater" src="${pageContext.request.contextPath}/statics/images/avater.jpg">
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
		                <a href="blog!showBlogList?blogTypeId=${blogTypeCount.blogTypeId }">${blogTypeCount.typeName }<sup>${blogTypeCount.blogCount }</sup></a>
		            </li>
		            </c:forEach>
		            </c:if>
	       		</ul>
			</div>
		</div>
	</div>
</div>




<div class="main-footer">
        <div class="container">
            <div class="logo">
            NIX BLOG
            </div>
            
        </div>
        
        <h5 class="copyright text-center">
           &copy; 2017 Tony &nbsp;|&nbsp;  湘ICP备16001500号 &nbsp;|&nbsp;
        </h5>
</div>
     
       
<a class="to-top" id="toTop" style="cursor: pointer; display:none;">
	<span class="topicon"><i class="glyphicon glyphicon-chevron-up"></i></span>
	<span class="toptext">Top</span>
</a> 
       
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function logout(){
		if(confirm("您确定要退出系统吗？")){
			window.location.href="user!logout";
		}
	}
	
	window.onload=function(){//回到顶部
		var clientHeight=document.documentElement.clientHeight;
		var timer=null;
		var isTop=true;
		
		window.onscroll=function(){
			var osTop=document.documentElement.scrollTop||document.body.scrollTop;
			if(osTop>=clientHeight){
				$("#toTop").css("display","block");
			}else{
				$("#toTop").css("display","none");
			}
			if(!isTop){
				clearInterval(timer);
			}
			isTop=false;
		}
		
		$("#toTop").click(function(){
			timer=setInterval(function(){
				var osTop=document.documentElement.scrollTop||document.body.scrollTop;
				var speed=Math.floor(-osTop/6);
				document.documentElement.scrollTop=document.body.scrollTop=osTop+speed;
				isTop=true;
				if(osTop==0){
					clearInterval(timer);
				}
		},30);
		})
	}
	
</script>       

</body>
</html>
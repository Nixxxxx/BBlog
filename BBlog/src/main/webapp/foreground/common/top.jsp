<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
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
		        <li><a href="info">关于</a></li>
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
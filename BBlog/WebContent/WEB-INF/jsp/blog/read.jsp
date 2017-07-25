<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Nix的个人博客</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/blog.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap3/css/bootstrap-theme.min.css">

<style>
body {
	background-color: rgb(221, 221, 221);
}
</style>
</head>
<body>
<ol class="breadcrumb">
	<li>博客</li>
	<li>${blog.blogType.typeName }</li>
</ol>
<div class="data_list">
	<div class="data_list_title">
		<div class="blog_title">
			<h3>${blog.title }</h3>
		</div>
		<div class="blog_info">
			发布时间：
			<fmt:formatDate value="${blog.updateDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日志类别：${blog.blogType.typeName }
		</div>
	</div>
	<div class="blog_content">${blog.content }</div>
	<c:if test="${user.administrator == 1 }">
		<div class="blog_action">
			<form id="blogForm">
				<button class="btn btn-primary" type="button" href="blog/write'">修改博客</button>
				<input class="btn btn-danger" type="submit" value="删除博客"></input> 
				<input type="hidden" name="id" value="${blog.id }" />
			</form>
		</div>
	</c:if>




	<%-- <div class="articleDetail container">
      	<div class="row">
		<div class="col-md-12">
			<div class="articleContent">
				<!-- 标题 -->
				<div class="title">Wordpress主题Simple停止更新公告</div>
				<!-- 访问量 ...-->
				<div class="secTitleBar">
					<ul>
						<li>分类：<a href="" rel="category tag">Wordpress</a></li>
						<li>发表：2016-10-04</li>
						<li>围观(11,236)</li>
						<li><a href="">评论(26)</a></li>
					</ul>
				</div>
				<!-- 内容 -->
				<div class="articleCon post_content">
					${currentBlog.content }
				</div>
				</div>
				</div>
				</div> --%>
</div>

<script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function (){

	function blogDelete(){
		if(confirm("您确定要删除这个日志吗？")){
			history.back();
			return true;
		}
		return false;
	}
})
</script>
</body>
</html>
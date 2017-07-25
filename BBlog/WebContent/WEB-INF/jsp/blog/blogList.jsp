<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap3/css/style.css">
<style>
body {
	background-color: rgb(221, 221, 221);
}
</style>
<ol class="breadcrumb">
	<li>博客</li>
	<li>${blogList[0].blogType.typeName}</li>
</ol>
<c:forEach var="blog" items="${blogList }" status="status">
	<div class="article">
		<div class="articleHeader">
			<div class="articleTitle">
				<a href="blog/read?id=${blog.id}">${blog.title }</a>
			</div>
			<span class="cate-Div"> <i
				class="glyphicon glyphicon-map-marker">${blog.blogType.typeName }</i></span>
		</div>
		<div class="articleBody clearfix">
			<!--缩略图-->
			<div class="articleThumb">
				<a href="blog/read?id=${blog.id}">
				<img src="./article2-1.png" srcset="http://vince.qiniudn.com/wp-content/uploads/2016/03/article2-1.png?imageView2/2/w/800/h/400 2x"
					alt=${blog.title } class="wp-post-image" width="400" height="200"></a>
			</div>
			<!--摘要-->
			<div class="articleFeed">
				<p>文章介绍
					今天带来的是一款倒计时插件，自己写的（偷师自慕课网），精心美化了一下，多终端适配，有女盆友的给以发给女朋友看一下，装装逼也是可以的，说不懂就能嘿咻嘿咻…/笑抽
					图片预览</p>
			</div>
		</div>
		<div class="articleFooter clearfix">
			<ul class="articleStatu">
				<li><i class="glyphicon glyphicon-calendar"></i> <fmt:formatDate
						value="${blog.updateDate }" type="date" pattern="yyyy-MM-dd" /></li>
				<li><i class="glyphicon glyphicon-eye-open"></i>${blog.reader }</li>
				<li><a href="blog/list?blogTypeId=${blog.blogType.id}"><i
						class="glyphicon glyphicon-folder-close"></i>${blog.blogType.typeName }</a></li>
			</ul>
			<a href="blog/list?blogId=${blog.id}"
				class="btn btn-readmore btn-info btn-md">阅读更多</a>
		</div>
	</div>
</c:forEach>

<div class="box-footer clearfix">
	<div class="pull-right">
		<nav aria-label="Page navigation">
			<ul class="pagination">${pageCode } </ul>
		</nav>
	</div>
</div>


<script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function (){

	
})
</script>
</body>
</html>

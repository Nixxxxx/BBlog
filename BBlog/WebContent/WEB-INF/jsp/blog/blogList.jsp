<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" media="all"
	href="${pageContext.request.contextPath}/statics/bootstrap3/css/style.css">
<style>
body {
	background-color: rgb(221, 221, 221);
}
</style>
<ol class="breadcrumb">
	<li>博客</li>
	<li>${blogList[0].typeName }</li>
</ol>
<c:forEach var="blog" items="${blogList }">
	<div class="article">
		<div class="articleHeader">
			<div class="articleTitle">
				<a href="blog!search?blogId=${blog.blogId}">${blog.title }</a>
			</div>
			<span class="cate-Div"> <i
				class="glyphicon glyphicon-map-marker">${blog.typeName }</i></span>
		</div>
		<div class="articleBody clearfix">
			<!--缩略图-->
			<div class="articleThumb">
				<a href="blog!search?blogId=${blog.blogId}"><img
					src="./article2-1.png"
					srcset="http://vince.qiniudn.com/wp-content/uploads/2016/03/article2-1.png?imageView2/2/w/800/h/400 2x"
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
						value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd" /></li>
				<li><i class="glyphicon glyphicon-eye-open"></i>4,179次浏览</li>
				<li><a href="blog!showBlogList?blogTypeId=${blog.typeId }"><i
						class="glyphicon glyphicon-folder-close"></i>${blog.typeName }</a></li>
			</ul>
			<a href="blog!showBlogList?blogId=${blog.blogId}"
				class="btn btn-readmore btn-info btn-md">阅读更多</a>
		</div>
	</div>
</c:forEach>

<div class="box-footer clearfix">
	<div class="pull-right">
		<nav aria-label="Page navigation">
			<ul class="pagination">${pageCode }
			</ul>
		</nav>
	</div>
</div>

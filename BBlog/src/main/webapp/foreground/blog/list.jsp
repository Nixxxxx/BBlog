<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css">
<ol class="breadcrumb">
	<li>博客</li>
	<li>${blogList[0].blogType.typeName}</li>
</ol>
<c:forEach var="blog" items="${blogList }" varStatus="status">
	<div class="article">
		<div class="articleHeader">
			<div class="articleTitle">
				<a href="blog/articles/${blog.id }">${blog.title }</a>
			</div>
			<span class="cate-Div"> <i
				class="glyphicon glyphicon-map-marker">${blog.blogType.typeName }</i></span>
		</div>
		<div class="articleBody clearfix">
			<!--缩略图-->
			<div class="articleThumb">
				<a href="blog/articles/${blog.id }">
				<img src="./article2-1.png" srcset="http://vince.qiniudn.com/wp-content/uploads/2016/03/article2-1.png?imageView2/2/w/800/h/400 2x"
					alt=${blog.title } class="wp-post-image" width="400" height="200"></a>
			</div>
			<!--摘要-->
			<div class="articleFeed">
				<p></p>
			</div>
		</div>
		<div class="articleFooter clearfix">
			<ul class="articleStatu">
				<li><i class="glyphicon glyphicon-calendar"></i> 
				<fmt:formatDate value="${blog.updateTime }" type="date" pattern="yyyy-MM-dd" /></li>
				<li><i class="glyphicon glyphicon-eye-open"></i>${blog.reader }</li>
				<li><a href="blog/list?typeId=${blog.blogType.id }">
					<i class="glyphicon glyphicon-folder-close"></i>
					${blog.blogType.typeName }</a></li>
			</ul>
			<a href="blog/articles/${blog.id }"
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


<script type="text/javascript">
$(function (){
	
})
</script>

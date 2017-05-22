<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	import="com.entity.Blog"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
body{
	background-color:rgb(221,221,221);
}
</style>
<script type="text/javascript">
	function blogDelete(){
		if(confirm("您确定要删除这个日志吗？")){
			history.back();
			return true;
		}
		return false;
	}
</script>
	<ol class="breadcrumb">
	  <li>博客</li>
	  <li>${currentBlog.typeName }</li>
	</ol>
	<div class="data_list">
		<div class="data_list_title">
		<div class="blog_title"><h3>${currentBlog.title }</h3></div>
		<div class="blog_info">
			发布时间：<fmt:formatDate value="${currentBlog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日志类别：${currentBlog.typeName }
		</div>
		</div>
		<div class="blog_content">
			${currentBlog.content }
		</div>
		<c:if test="${currentUser.administrator==1 }">
		<div class="blog_action">
			<form id="blog_form" action="blog!delete" onsubmit="return blogDelete()">
				<button class="btn btn-primary" type="button" onclick="javascript:window.location='blog!writing'">修改博客</button>
				<input class="btn btn-danger" type="submit" value="删除博客"></input>
				<input type="hidden" name="blog.blogId" value="${currentBlog.blogId }"/>
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
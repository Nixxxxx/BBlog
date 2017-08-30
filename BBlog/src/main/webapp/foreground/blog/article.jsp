<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<ol class="breadcrumb">
	<li>博客</li>
	<li>${blog.blogType.typeName }</li>
	<li class="active">${blog.title }</li>
</ol>
<div class="data_list">
	<div class="data_list_title">
		<div class="blog_title">
		  <h3>${blog.title }</h3>
		  <div style="padding-left: 330px;padding-bottom: 20px;padding-top: 10px">
		    <div class="bshare-custom">
		      <a title="分享到QQ空间" class="bshare-qzone"></a>
		      <a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
		      <a title="分享到腾讯微博" class="bshare-qqmb"></a>
		      <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
		      <span class="BSHARE_COUNT bshare-share-count">0</span>
		    </div>
		    <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script>
		    <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
		  </div>
		</div>
		<div class="blog_info">
			发布时间：<fmt:formatDate value="${blog.updateTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			日志类别：${blog.blogType.typeName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			阅读：${blog.reader }
		</div>
	</div>
	<div class="blog_content">${blog.content }</div>

	<div class="blog_lastAndNextPage">
		${pageCode }
	</div>



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

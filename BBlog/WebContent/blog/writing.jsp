<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	import="com.entity.Blog"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<style>
body{
	background-color:rgb(221,221,221);
}
</style>
<script type="text/javascript">
	function checkForm(){
		/* var title=$("title").val();
		var content=CKEDITOR.instances.content.getData();
		var typeId=$("typeId").val();
		if(title==null||title==""){
			$("error").text("标题不能为空！");
			return false;
		}
		if(content==null||content==""){
			$("error").text("内容不能为空！");
			return false;
		}
		if(typeId==null||typeId==""){
			$("error").text("请选择日志类别！");
			return false;
		} */
		return true;
	}
</script>
<div class="data_list">
	<div class="data_list_title">
	<c:choose>
		<c:when test="${currentBlog.blogId!=null }">
			<input type="hidden" name="blog.blogId" value="${currentBlog.blogId }">
			<i class="glyphicon glyphicon-edit"></i>
			修改博客</div>
		</c:when>
		<c:otherwise>
			<i class="glyphicon glyphicon-pencil"></i>
			写博客</div>
		</c:otherwise>
	</c:choose>
	<form action="blog!blogSaveOrUpdate" method="post" onsubmit="return checkForm()">
		<div>
			<div class="blog_title">
				<input type="text" id="title"  name="blog.title" value="${currentBlog.title }" class="input-xlarge"  style="margin-top:5px;height:30px;"  placeholder="日志标题..."/></div>
			<div>
				<textarea class="ckeditor" id="content" name="blog.content">${currentBlog.content }</textarea>
			</div>
			<div class="blog_type">
				<select id="typeId" name="typeId">
					<option value="">请选择日志类别...</option>
					<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
						<option value="${blogTypeCount.blogTypeId }" ${blogTypeCount.blogTypeId==blog.typeId?'selected':'' }>${blogTypeCount.typeName }</option>
						<%-- <input type="hidden" name="blog.typeName" value="${blogTypeCount.typeName }"> --%>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="submit" class="btn btn-primary" value="保存"/>
				<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
				<font id="error" color="red">${error }</font>  
			</div>
		</div>
	</form>
</div>
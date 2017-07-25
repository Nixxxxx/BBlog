<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.entity.Blog"%>
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
<div class="data_list">
	<div class="data_list_title">
		<c:if test="${blog.blogId != null }">
			<input type="hidden" name="id" value="${blog.blogId }">
			<i class="glyphicon glyphicon-edit"></i>修改博客
		</c:if>
		<c:if test="${blog.blogId == null }">
			<i class="glyphicon glyphicon-pencil"></i>写博客
		</c:if>
	</div>
</div>
<form action="blog/save">
	<div>
		<div class="blog_title">
			<input type="text" id="title" name="title" value="${blog.title }" class="input-xlarge"
				style="margin-top: 5px; height: 30px;" placeholder="日志标题..." />
		</div>
		<div>
			<textarea class="ckeditor" id="content" name="content">${blog.content }</textarea>
		</div>
		<div class="blog_type">
			<select id="typeId" name="typeId">
				<option value="">请选择日志类别...</option>
				<c:forEach var="blogType" items="${blogTypeList }">
					<option value="${blogType.id }" ${blogType.id == blog.typeId?'selected':'' }>
						${blogType.typeName }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<input type="submit" class="btn btn-primary" value="保存">
			<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
			<font id="error" color="red">${error }</font>
		</div>
	</div>
</form>

<script src="${pageContext.request.contextPath}/statics/js/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function (){
	
})
	function checkForm(){
		var title=$("#title").val();
		var content=CKEDITOR.instances.content.getData();
		var typeId=$("#typeId").val();
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
		}
		return true;
	}
</script>
</body>
</html>
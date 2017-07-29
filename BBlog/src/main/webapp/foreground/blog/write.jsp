<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<form>
	<div>
		<div class="blog_title">
			<input type="text" id="title" name="title" value="${blog.title }" class="input-xlarge"
				style="margin-top: 5px; height: 30px;" placeholder="日志标题..." required>
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
			<button class="btn btn-primary" onclick="javascript:history.back()">返回</button>
			<div class="text-danger wrapper-xs text-center invisible" id="errorMsg">错误信息</div>
		</div>
	</div>
</form>

<script src="${pageContext.request.contextPath}/statics/js/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function (){
	var errorMsg = $("#errorMsg");

	var showError = function(msg) {
		errorMsg.text(msg).removeClass("invisible");
	};

	$("#signInForm").submit(function() {
		errorMsg.addClass("invisible");
		var content = CKEDITOR.instances.content.getData();
		var typeId = $("#typeId").val();
		if(content==null||content==""){
			$("error").text("内容不能为空！");
			return false;
		}
		if(typeId==null||typeId==""){
			$("error").text("请选择日志类别！");
			return false;
		}
		
		var signInBtn = $("#signInBtn");
		$.ajax({
			url : "user/signIn",
			type : "POST",
			data : {
				email : email,
				password : password,
				captcha : captcha,
				checkbox : $("#checkbox").prop("checked")
			},
			dataType : "json",
			beforeSend : function() {
				signInBtn.button("loading");
			},
			complete : function() {
				//重置登录按钮
				signInBtn.button("reset");
				//重置验证码
				$("#randImage").trigger("click");
			},
			success : function(data) {
				if (data.result) {
					window.location.href = "index.jsp";
				} else {
					showError(data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus) {
				if (textStatus == "timeout") {
					showError("登录超时");
				} else {
					showError("登录失败");
				}
			}
		});
		return false;
	});
	
})
	function checkForm(){
		return true;
	}
</script>
</body>
</html>
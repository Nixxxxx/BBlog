<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	import="com.entity.BlogType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var result=${result }
		if(!result){
			<%session.setAttribute("result",true); %>
			alert("操作未成功！");
		}
	}

	function BlogTypeSave(){
		var typeName=prompt("输入类名","");
		if(typeName!=null){
			window.location.href="blogType!blogTypeSave?typeName="+typeName;
		}
	}
	
	$("blogTypeDelete_form").submit=function(){
		if(confirm("您确定要删除这个日志类别吗？")){
			return true;
		}
		return false;
	}
	
	function BlogTypeUpdate(blogTypeId){
		var typeName=prompt("输入类名","");
		if(typeName!=null){
			window.location.href="blogType!blogTypeUpdate?blogTypeId="+blogTypeId+"&typeName="+typeName;
		}
	}
	
	function BlogListSearch(blogTypeId){
		window.location.href="blog!blogListSearch?blogTypeId="+blogTypeId;
	}
</script>
<div class="data_list">
		<div class="data_list_title">
		<i class="glyphicon glyphicon-list"></i>
		日记类别列表
		<span class="blogType_add">
			<button class="btn btn-mini btn-success" type="button" onclick="BlogTypeSave()">添加日志类别</button>
		</span>
		</div>
		<div>
			<table class="table table-hover table-striped">
			  <tr>
			  	<th>编号</th>
			  	<th>类别名称</th>
			  	<th>操作</th>
			  </tr>
			  <c:forEach var="blogType" items="${blogTypeList }">
			  	<tr>
			  		<form id="blogTypeDelete_form" action="blogType!blogTypeDelete" method="post">
			  		<td>${blogType.blogTypeId }</td>
			  		<td><a class="btn btn-default" onclick="BlogListSearch(${blogType.blogTypeId })">${blogType.typeName }</a></td>
			  		<td><button class="btn btn-mini btn-info" type="button" onclick="BlogTypeUpdate(${blogType.blogTypeId })">修改</button>&nbsp;
			  		<input class="btn btn-mini btn-danger" type="submit" value="删除">
			  		</td></form>
			  	</tr>
			  </c:forEach>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>

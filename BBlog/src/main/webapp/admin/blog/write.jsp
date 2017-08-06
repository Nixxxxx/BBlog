<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>

<div class="data_list">
	<div class="data_list_title">
		<c:if test="${blog.id != null }">
			<input type="hidden" name="id" value="${blog.id }">
			<i class="glyphicon glyphicon-edit"></i>修改博客
		</c:if>
		<c:if test="${blog.id == null }">
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
				   <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
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

<script type="text/javascript">
$(function (){
	var errorMsg = $("#errorMsg");

	var showError = function(msg) {
		errorMsg.text(msg).removeClass("invisible");
	};

	$("#signInForm").submit(function() {
		errorMsg.addClass("invisible");
		var content = CKEDITOR.instances.content.getData();
		var typeId = $.trim("#typeId").val();
		if(content == null|| content == ""){
			showError("内容不能为空！");
			return false;
		}
		if(typeId == null || typeId == ""){
			showError("请选择日志类别！");
			return false;
		}
		
		var signInBtn = $("#signInBtn");
		$.ajax({
			url : "admin/blog/insert",
			type : "POST",
			data : {
			},
			dataType : "json",
			beforeSend : function() {
				signInBtn.button("loading");
			},
			complete : function() {
				//重置登录按钮
				signInBtn.button("reset");
			},
			success : function(data) {
				if (data.result) {
					window.location.reload();
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
	
</script>
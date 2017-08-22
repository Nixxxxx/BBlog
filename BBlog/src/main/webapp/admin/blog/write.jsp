<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="content-header">
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i>首页</li>
		<li>博客管理</li>
		<li class="active">写博客</li>
	</ol>
	</section>
	
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title"><i class="glyphicon glyphicon-edit"></i>写博客</h3>
				</div>
				<div class="box-body table-responsive no-padding">
					<form id="update_form">
						<c:if test="${blog != null }">
							<input type="hidden" id="id" name="id" value="${blog.id }">
						</c:if>
						<div class="text-danger wrapper-xs text-center invisible" id="error_msg">错误信息</div>
						<div class="blog_title">
							<input type="text" id="title" name="title" value="${blog.title }" class="form-control"
								 placeholder="日志标题..." required>
						</div>
						<div>
							<textarea id="content" name="content">${blog.content }</textarea>
						</div>
						<div class="form-control select2">
							<select id="typeId" name="typeId">
								<option value="">请选择日志类别...</option>
								<c:forEach var="blogType" items="${blogTypeList }">
									<option value="${blogType.id }" ${blogType.id == blog.blogType.id?'selected':'' }>
										${blogType.typeName }</option>
								</c:forEach>
							</select>
						</div>
						<div>
							<button type="submit" id="updateBtn" class="btn btn-primary">保存</button>
							<button class="btn btn-primary" onclick="javascript:history.back()">返回</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

<script src="static/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function (){
	CKEDITOR.replace( 'content', {
		height: 300,
		width: 1000,
	} ); 
	
	var $error_msg = $("#error_msg");

    var show_error = function (error_msg) {
        $error_msg.text(error_msg).removeClass("invisible");
    };

    var updateForm = $("#update_form");
    updateForm.submit(function() {
		$error_msg.addClass("invisible");
		var content = CKEDITOR.instances.content.getData();
		var title = $.trim($("#title").val());
		var typeId = $.trim($("#typeId").val());
		if(content == null|| content == ""){
			show_error("内容不能为空！");
			return false;
		}
		if(typeId == null || typeId == ""){
			show_error("请选择日志类别！");
			return false;
		}
		if($("#id").length>0){
			var url = "admin/blog/update";
			var id = $.trim($("#id").val());
		}else {
			var url = "admin/blog/insert";
			var id = 0;
		}
		var summary = CKEDITOR.instances.content.document.getBody().getText().substr(0,100);
		var updateBtn = $("#updateBtn");
		$.ajax({
			url : url,
			type : "post",
			data : {
				id : id,
				title : title,
				content : content,
				typeId : typeId,
				summary : summary,
			},
			dataType : "json",
			beforeSend : function() {
				updateBtn.button("loading");
			},
			complete : function() {
				updateBtn.button("reset");
			},
			success : function(data) {
				alert(data.msg);
				if (data.result) {
					window.location.reload();
				}
			},
			error : function(XMLHttpRequest, textStatus) {
				if (textStatus == "timeout") {
					show_error("登录超时");
				} else {
					show_error("登录失败");
				}
			}
		});
		return false;
	});
	
})
	
</script>
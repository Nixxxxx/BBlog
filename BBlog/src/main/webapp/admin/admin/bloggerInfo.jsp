<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section class="content-header">
	<ol class="breadcrumb">
		<li>系统管理</li>
		<li class="active">博主信息</li>
	</ol>
</section>
<div class="sidebar-content">
	<div class="row">
		<div class="col-sm-3 thumbnail">
			<img src="${blogger.imagePath }"> 
			<div class="text-danger wrapper-xs text-center invisible" id="error_msg">错误信息</div>
		</div>
		<div class="col-sm-7 col-sm-offset-1">
			<form action="admin/blogger/update" method="post" class="form-horizontal" id="updateForm" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${blogger.id }"> 
				<div class="form-group input-group">
					<span class="input-group-addon">头&nbsp;&nbsp;&nbsp;像</span> 
					<input type="file" id="imageFile" name="imageFile" accept="image" disabled>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;箱</span> 
					<input type="text" class="form-control" id="email" name="email" 
					readonly value="${blogger.email }" maxlength="20" size="20" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">用户名</span> 
					<input type="text" class="form-control" id="userName" name="userName"
					readonly value="${blogger.userName }" maxlength="20" size="20" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">关于博主</span>
					<textarea id="profile" name="profile">${blogger.profile }</textarea>
				</div>

				<div class="form-group">
					<input type="button" id="edit" class="btn btn-success btn-block" value="编辑">
					<input type="submit" id="updateBtn" class="btn btn-success btn-block invisible" value="保存">
				</div>
			</form>
		</div>
	</div>
</div>

<script src="static/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
	CKEDITOR.replace( 'profile', {
		height: 300,
		width: 650,
	} );
	
	$("#edit").click(function (){
		$("#edit").addClass("invisible");
		$("#updateBtn").removeClass("invisible");
		$("[readonly]").attr("readonly", false);
		$("#imageFile").attr("disabled", false);
	})

	
})
	
</script>
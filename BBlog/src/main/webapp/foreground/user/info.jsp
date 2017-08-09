<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section class="content-header">
	<ol class="breadcrumb">
		<li>用户</li>
		<li class="active">个人中心</li>
	</ol>
</section>
<div class="sidebar-content">
	<div class="row">
		<div class="col-sm-3 thumbnail">
			<img src="${user.imagePath }"> 
			<div class="text-danger wrapper-xs text-center invisible" id="error_msg">错误信息</div>
		</div>
		<div class="col-sm-7 col-sm-offset-1">
			<form class="form-horizontal" id="updateForm" action="user/update" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value="${user.id }"> 
				<div class="form-group input-group">
					<span class="input-group-addon">头&nbsp;&nbsp;&nbsp;像</span> 
					<input type="file" id="imageFile" name="imageFile" accept="image" disabled>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;箱</span> 
					<input type="text" class="form-control" id="email" name="email" 
					readonly value="${user.email }" maxlength="20" size="20" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">用户名</span> 
					<input type="text" class="form-control" id="userName" name="userName"
					readonly value="${user.userName }" maxlength="20" size="20" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">个性签名</span>
					<textarea id="mood" class="form-control" name="mood" rows="3" readonly required>${user.mood }</textarea>
				</div>

				<div class="form-group">
					<input type="button" id="edit" class="btn btn-success btn-block" value="编辑">
					<input type="submit" id="updateBtn" class="btn btn-success btn-block invisible"  value="保存">
				</div>
			</form>
		</div>
	</div>
</div>


<script type="text/javascript">
$(function() {
	var $error_msg = $("#error_msg");

    var show_error = function (error_msg) {
        $error_msg.text(error_msg).removeClass("invisible");
    };
	
	$("#edit").click(function (){
		if($("#id").val() != ""){
			$("#edit").addClass("invisible");
			$("#updateBtn").removeClass("invisible");
			$("[readonly]").attr("readonly", false);
			$("#imageFile").attr("disabled", false);
		}
	})

	$("#updateForm").submit(function() {
		$error_msg.addClass("invisible")
		var email = $.trim($("#email").val());
		var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if (!ePattern.test(email)) {
			show_error("请输入正确格式的邮箱");
			return false;
		}
		return true;
	});
})
	
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
		var errorMsg = $("#errorMsg");

		var showError = function(msg) {
			errorMsg.text(msg).removeClass("invisible");
		};
		
		$("#edit").click(function (){
			$("#edit").addClass("invisible");
			$("#updateBtn").removeClass("invisible");
			$("[readonly]").attr("readonly", false);
			$("#imagePath").attr("disabled", false);
		})

		$("#updateForm").submit(function() {
			errorMsg.addClass("invisible")
			var email = $.trim($("#email").val());
			var userName = $.trim($("#userName").val());
			var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var uPattern = /^[a-zA-Z0-9_@.]{4,20}$/;
			if (!ePattern.test(email)) {
				showError("请输入正确格式的邮箱");
				return false;
			}
			if (!uPattern.test(userName)) {
				showError("请输入正确格式的用户名");
				return false;
			}
			var updateBtn = $("#updateBtn");
			$.ajax({
				url : "user/update",
				type : "POST",
				data : $("#updateForm").serialize(),
				dataType : "json",
				beforeSend : function() {
					updateBtn.button("loading");
				},
				complete : function() {
					//重置登录按钮
					updateBtn.button("reset");
				},
				success : function(data) {
					if (data.result) {
						window.location.reload();
					} else {
						showError(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus) {
					if (textStatus === "timeout") {
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

<section class="content-header">
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li>用户</li>
		<li class="active">个人中心</li>
	</ol>
</section>
<div class="sidebar-content">
	<div class="row">
		<div class="col-sm-3 thumbnail">
			<img src="${pageContext.request.contextPath}/statics/images/avater.jpg"> 
			<div class="text-danger wrapper-xs text-center invisible" id="errorMsg">错误信息</div>
		</div>
		<div class="col-sm-7 col-sm-offset-1">
			<form class="form-horizontal" id="updateForm" method="post">
				<input type="hidden" name="id" value="${user.id }"> 
				<div class="form-group input-group">
					<span class="input-group-addon">头&nbsp&nbsp&nbsp像</span> 
					<input type="file" id="imagePath" name="imagePath" accept="image" disabled=true>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">邮&nbsp&nbsp&nbsp箱</span> 
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
					<textarea class="form-control" name="mood" rows="3" readonly required>${user.mood }</textarea>
				</div>

				<div class="form-group">
					<input type="button" id="edit" class="btn btn-success btn-block" value="编辑">
					<input type="submit" id="updateBtn" class="btn btn-success btn-block invisible"  value="保存">
				</div>
			</form>
		</div>
	</div>
</div>


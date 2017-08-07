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
			<img src="${pageContext.request.contextPath}/static/images/avater.jpg"> 
			<div class="text-danger wrapper-xs text-center invisible" id="errorMsg">错误信息</div>
		</div>
		<div class="col-sm-7 col-sm-offset-1">
			<form action="admin/blogger/update" method="post" class="form-horizontal" id="updateForm" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${blogger.id }"> 
				<div class="form-group input-group">
					<span class="input-group-addon">头&nbsp&nbsp&nbsp像</span> 
					<input type="file" id="imageFile" name="imageFile" accept="image" disabled>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">邮&nbsp&nbsp&nbsp箱</span> 
					<input type="text" class="form-control" id="email" name="email" 
					readonly value="${blogger.email }" maxlength="20" size="20" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">用户名</span> 
					<input type="text" class="form-control" id="userName" name="userName"
					readonly value="${blogger.userName }" maxlength="20" size="20" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">个性签名</span>
					<textarea class="form-control" name="profile" rows="3" readonly required>${blogger.profile }</textarea>
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
		var errorMsg = $("#errorMsg");

		var showError = function(msg) {
			errorMsg.text(msg).removeClass("invisible");
		};
		
		$("#edit").click(function (){
			$("#edit").addClass("invisible");
			$("#updateBtn").removeClass("invisible");
			$("[readonly]").attr("readonly", false);
			$("#imageFile").attr("disabled", false);
		})

		/* $("#updateForm").submit(function() {
			errorMsg.addClass("invisible")
			var updateBtn = $("#updateBtn");
			$.ajax({
				url : "admin/blogger/update",
				type : "post",
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
		}); */
	})
	
</script>
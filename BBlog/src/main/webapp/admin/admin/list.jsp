<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<section class="content-header">
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i>首页</li>
		<li>系统管理</li>
		<li class="active">管理员管理</li>
	</ol>
	</section>

	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">管理员列表</h3>

					<div class="box-tools">
						<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-remote="false"
							data-target="#admin_add_modal" data-backdrop="static">添加管理员</button>
					</div>
				</div>
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered text-center">
						<thead>
							<tr>
								<th>序号</th>
								<th>邮箱</th>
								<th>密码</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:if test="${adminList != null }">
							<c:forEach var="admin" items="${adminList }" varStatus="status">
								<tr>
									<td>${status.index+1 }</td>
									<td class="admin_email">${admin.email }</td>
									<td class="admin_password">${admin.password }</td>
									<td>
										<a data-id="${admin.id }" class="update" href="javascript:void(0)" data-toggle="modal"
										data-remote="false" data-target="#admin_update_modal" data-backdrop="static"> <i class="fa fa-edit"></i> 编辑</a>
										<a data-id="${admin.id }" class="del" href="javascript:void(0)"> <i class="fa fa-trash"></i> 删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${adminList == null }">
							<tr>
								<td colspan="4">无记录！</td>
							</tr>
						</c:if>
					</table>
				</div>
				<div class="box-footer clearfix">
					<div class="pull-right">
						<nav aria-label="Page navigation">
						<ul class="pagination">${pageCode }</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	
<!-- 添加Modal -->
	<div class="modal fade" id="admin_add_modal" tabindex="-1" role="dialog" aria-labelledby="admin_add_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="admin_add_label">添加管理员</h3>
					</div>
					<form class="form-horizontal" id="admin_add_form">
			          <div class="text-danger wrapper-xs text-center invisible" id="error_msg1">错误信息</div>
						<div class="box-body">
							<div class="form-group">
								<label for="admin_add_email" class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="admin_add_email"
										maxlength="30" placeholder="Email" required>
								</div>
							</div>
							<div class="form-group">
								<label for="admin_add_password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="admin_add_password"
										maxlength="20" placeholder="Password" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right" id="admin_add_button" data-loading-text="添加中...">添加</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<!-- 编辑Modal -->
	<div class="modal fade" id="admin_update_modal" tabindex="-1" role="dialog" aria-labelledby="admin_update_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="admin_update_label">编辑</h3>
					</div>
					<form class="form-horizontal" id="admin_update_form">
			          <div class="text-danger wrapper-xs text-center invisible" id="error_msg2">错误信息</div>
						<div class="box-body">
							<input type="hidden" id="admin_update_id" name="id">
							<div class="form-group">
								<label for="admin_update_email" class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="admin_update_email"
										maxlength="30" placeholder="Email" required>
								</div>
							</div>
							<div class="form-group">
								<label for="admin_update_password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="admin_update_password"
										maxlength="20" placeholder="Password" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right" id="admin_update_button" data-loading-text="更新中...">更新</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script>
    $(function(){

        //删除
        $(".del").click(function () {

            if (confirm("确认要删除吗？")) {
                var id = $(this).data("id");
                $.ajax({
                    url: "admin/del",
                    type: "post",
                    data: {id: id},
                    dataType: "json",
                    success: function (data) {
                        alert(data.msg);
                        if (data.result) {
                        	window.location.reload();
                        }
                    },
                    error: function (XMLHttpRequest, textStatus) {
                        if (textStatus === "timeout") {
                            alert("删除超时！");
                        } else {
                            alert("删除失败！");
                        }
                    }
                })
            }
        });

        //更新modal
        $(".update").click(function () {
            $("#admin_update_id").val($(this).data("id"));
            $("#admin_update_email").val($(this).parent().prevAll(".admin_email").text());
            $("#admin_update_password").val($(this).parent().prevAll(".admin_password").text());
        });

        //更新
        var $admin_update_form = $("#admin_update_form");
        $admin_update_form.submit(function () {
        	var $error_msg = $("#error_msg2");
            var show_error = function (error_msg) {
                $error_msg.text(error_msg).removeClass("invisible");
            };
        	$error_msg.addClass("invisible");

            var id = $.trim($("#admin_update_id").val());
            var email = $.trim($("#admin_update_email").val());
			var password = $.trim($("#admin_update_password").val());
			var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var uPattern = /^[a-zA-Z0-9_@.]{4,20}$/;
			if (!ePattern.test(email)) {
				show_error("请输入正确格式的邮箱");
				return false;
			}
			if (!uPattern.test(password)) {
				show_error("请输入正确格式的密码");
				return false;
			}
			
            var $update_btn = $("#admin_update_button");
            $.ajax({
                url: "admin/update",
                type: "post",
                dataType: "json",
                data: {
                	id :id,
                	email : email,
                	password : password,
                	},
                beforeSend: function () {
                    $update_btn.button("loading");
                },
                complete: function () {
                    $update_btn.button("reset");
                },
                success: function (data) {
                    alert(data.msg);
                    if (data.result) {
                        window.location.reload();
                    }
                },
                error: function (XMLHttpRequest, textStatus) {
                    if (textStatus === "timeout") {
                        alert("更新超时！");
                    } else {
                        alert("更新失败！");
                    }
                }
            });
            return false;
        });

        //添加
        var $admin_add_form = $("#admin_add_form");
        $admin_add_form.submit(function () {
        	var $error_msg = $("#error_msg1");
            var show_error = function (error_msg) {
                $error_msg.text(error_msg).removeClass("invisible");
            };
        	$error_msg.addClass("invisible");
        	
            var email = $.trim($("#admin_add_email").val());
			var password = $.trim($("#admin_add_password").val());
			var ePattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var uPattern = /^[a-zA-Z0-9_@.]{4,20}$/;
			if (!ePattern.test(email)) {
				show_error("请输入正确格式的邮箱");
				return false;
			}
			if (!uPattern.test(password)) {
				show_error("请输入正确格式的密码");
				return false;
			}
            var $add_btn = $("#admin_add_button");
            $.ajax({
                url: "admin/insert",
                type: "POST",
                data: {
                	email : email,
                	password : password,
                	},
                dataType: "json",
                beforeSend: function () {
                    $add_btn.button("loading");
                },
                complete: function () {
                    $add_btn.button("reset");
                },
                success: function (data) {
                    alert(data.msg);
                    if (data.result) {
                        window.location.reload();
                    }
                },
                error: function (XMLHttpRequest, textStatus) {
                    if (textStatus === "timeout") {
                        alert("添加超时！");
                    } else {
                        alert("添加失败！");
                    }
                }
            });
            return false;
        });

    })
</script>
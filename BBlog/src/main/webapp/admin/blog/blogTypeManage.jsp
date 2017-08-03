<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<section class="content-header">
	<ol class="breadcrumb">
		<li><a href="main/showInfo"><i
				class="fa fa-dashboard"></i>首页</a></li>
		<li>博客管理</li>
		<li class="active">博客类型列表</li>
	</ol>
	</section>

	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">博客类型列表</h3>

					<div class="box-tools">
						<button type="button" class="btn btn-info btn-sm"
							data-toggle="modal" data-remote="false"
							data-target="#blogType_add_modal" data-backdrop="static">添加博客类型</button>
					</div>
				</div>
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered text-center">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:if test="${blogTypeList != null }">
							<c:forEach var="blogType" items="${blogTypeList }" varStatus="status">
								<tr>
									<td>${status.index+1 }</td>
									<td class="admin_name">${blogType.userName }</td>
									<td>${admin.addTime }</td>
									<td><a data-id="${blogType.id }" class="update"
										href="javascript:void(0)" data-toggle="modal"
										data-remote="false" data-target="#blogType_update_modal"
										data-backdrop="static"> <i class="fa fa-edit"></i> 编辑</a>
										<a data-id="${blogType.id }" class="del"
										href="javascript:void(0)"> <i class="fa fa-trash"></i> 删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${blogTypeList == null }">
							<tr>
								<td colspan="3">无记录！</td>
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

	<div class="modal fade" id="blogType_update_modal" tabindex="-1"
		role="dialog" aria-labelledby="blogType_update_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="blogType_update_label">编辑人员</h3>
					</div>
					<form class="form-horizontal" id="blogType_update_form">
						<div class="box-body">
							<input type="hidden" id="blogType_id" name="id">
							<div class="form-group">
								<label for="blogType_typeName" class="col-sm-2 control-label">类型名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="blogType_typeName"
										name="typeName" maxlength="10" placeholder="请输入用户名" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right" id="blogType_update_button" data-loading-text="更新中...">更新</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="blogType_add_modal" tabindex="-1"
		role="dialog" aria-labelledby="blogType_add_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="blogType_add_label">添加管理员</h3>
					</div>
					<form class="form-horizontal" method="post" id="blogType_add_form">
						<div class="text-danger wrapper-xs text-center invisible"
							id="error_msg1">错误信息</div>
						<div class="box-body">
							<div class="form-group">
								<label for="add_blogType_typeName" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="add_blogType_typeName"
										name="typeName" maxlength="10" placeholder="请输入用户名" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right"
								id="blogType_add_button" data-loading-text="添加中...">添加</button>
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
                    url: "admin/blogType/del",
                    type: "POST",
                    data: {id: id},
                    dataType: "json",
                    success: function (data) {
                        alert(data.msg);
                        if (data.success) {
                            //菜单栏当前选中
                        	window.location.href="admin/showSetting";
                            $now_selected = $("ul.treeview-menu>li.active>a");
                            $now_selected.trigger("click");
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
            $("#blogType_id").val($(this).data("id"));
            $("#blogType_typeName").val($(this).parent().prevAll(".blogType_typeName").text());
        });

        //更新
        var $blogType_update_form = $("#blogType_update_form");
        $blogType_update_form.submit(function () {

            var $update_btn = $("#blogType_update_button");

            $.ajax({
                url: "admin/blogType/update",
                type: "POST",
                dataType: "json",
                data: $blogType_update_form.serialize(),
                beforeSend: function () {
                    $update_btn.button("loading");
                },
                complete: function () {
                    $update_btn.button("reset");
                },
                success: function (data) {
                    alert(data.msg);
                    if (data.success) {
                        //菜单栏当前选中
                        window.location.href="admin/blogType/showSetting";
                        $now_selected = $("ul.treeview-menu>li.active>a");
                        $now_selected.trigger("click");
                        $("#blogType_update_modal").modal("hide");
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
        var $blogType_add_form = $("#blogType_add_form");
        $blogType_add_form.submit(function () {
        	var $error_msg = $("#error_msg1");

            var show_error = function (error_msg) {
                $error_msg.text(error_msg).removeClass("invisible");
            };
        	$error_msg.addClass("invisible");
            var userName = $.trim($("#blogType_add_form input[name=userName]").val());
            var password = $.trim($("#blogType_add_form input[name=password]").val());
            var confirmPassword = $.trim($("#blogType_add_form input[name=confirmPassword]").val());
            var $add_btn = $("#blogType_add_button");
            $.ajax({
                url: "admin/blogType/insert",
                type: "POST",
                dataType: "json",
                data: $admin_add_form.serialize(),
                beforeSend: function () {
                    $add_btn.button("loading");
                },
                complete: function () {
                    $add_btn.button("reset");
                },
                success: function (data) {
                    alert(data.msg);
                    if (data.success) {
                        //菜单栏当前选中
                        window.location.href="admin/blogType/showSetting";
                        $now_selected = $("ul.treeview-menu>li.active>a");
                        $now_selected.trigger("click");
                        $("#blogType_add_modal").modal("hide");
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


        //更新modal
        $(".change_password").click(function () {
            $("#change_blogType_id").val($(this).data("id"));
            $("#change_blogType_typeName").text($(this).parent().prevAll(".blogType_typeName").text());
        });

    })
</script>
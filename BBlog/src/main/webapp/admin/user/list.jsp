<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<section class="content-header">
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i>首页</li>
		<li>用户管理</li>
		<li class="active">博客类型管理</li>
	</ol>
	</section>

	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">用户列表列表</h3>

					<div class="box-tools">
						<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-remote="false"
							data-target="#user_add_modal" data-backdrop="static">添加博客类型</button>
					</div>
				</div>
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered text-center">
						<thead>
							<tr>
								<th>序号</th>
								<th>博客类型</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:if test="${userList != null }">
							<c:forEach var="user" items="${userList }" varStatus="status">
								<tr>
									<td>${status.index+1 }</td>
									<td class="user_typeName">${user.typeName }</td>
									<td>
										<a href="admin/blog/list?typeId=${user.id }"> <i class="fa fa-th"></i> 查看博客</a>
										<a data-id="${user.id }" class="update" href="javascript:void(0)" data-toggle="modal"
										data-remote="false" data-target="#user_update_modal" data-backdrop="static"> <i class="fa fa-edit"></i> 编辑</a>
										<a data-id="${user.id }" class="del" href="javascript:void(0)"> <i class="fa fa-trash"></i> 删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${userList == null }">
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
	
<!-- 添加Modal -->
	<div class="modal fade" id="user_add_modal" tabindex="-1" role="dialog" aria-labelledby="user_add_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="user_add_label">添加博客类型</h3>
					</div>
					<form class="form-horizontal" id="user_add_form">
						<div class="box-body">
							<div class="form-group">
								<label for="user_add_typeName" class="col-sm-2 control-label">类型名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="user_add_typeName"
										name="typeName" maxlength="10" placeholder="请输入用户名" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right" id="user_add_button" data-loading-text="添加中...">添加</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<!-- 编辑Modal -->
	<div class="modal fade" id="user_update_modal" tabindex="-1" role="dialog" aria-labelledby="user_update_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="user_update_label">编辑</h3>
					</div>
					<form class="form-horizontal" id="user_update_form">
						<div class="box-body">
							<input type="hidden" id="user_update_id" name="id">
							<div class="form-group">
								<label for="user_update_typeName" class="col-sm-2 control-label">类型名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="user_update_typeName"
										name="typeName" maxlength="10" placeholder="请输入用户名" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right" id="user_update_button" data-loading-text="更新中...">更新</button>
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
                    url: "admin/user/del",
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
            $("#user_update_id").val($(this).data("id"));
            $("#user_update_typeName").val($(this).parent().prevAll(".user_typeName").text());
        });

        //更新
        var $user_update_form = $("#user_update_form");
        $user_update_form.submit(function () {
            var $update_btn = $("#user_update_button");

            $.ajax({
                url: "admin/user/update",
                type: "post",
                dataType: "json",
                data: $user_update_form.serialize(),
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
        var $user_add_form = $("#user_add_form");
        $user_add_form.submit(function () {
            var typeName = $.trim($("#user_add_form input[name=typeName]").val());
            var $add_btn = $("#user_add_button");
            $.ajax({
                url: "admin/user/insert",
                type: "POST",
                data: {typeName : typeName},
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
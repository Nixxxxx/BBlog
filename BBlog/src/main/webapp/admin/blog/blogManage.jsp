<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<section class="content-header">
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i>首页</li>
		<li>博客管理</li>
		<li class="active">博客列表</li>
	</ol>
	</section>

	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">博客列表</h3>
				</div>
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered text-center">
						<thead>
							<tr>
								<th>序号</th>
								<th>标题</th>
								<th>博客类型</th>
								<th>阅读量</th>
								<th>更新时间</th>
								<th>首次编写时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:if test="${blogList != null }">
							<c:forEach var="blog" items="${blogList }" varStatus="status">
								<tr>
									<td>${status.index+1 }</td>
									<td class="blog_title">${blog.title }</td>
									<td class="blog_title">${blog.blogType.typeName }</td>
									<td class="blog_title">${blog.reader }</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd hh-mm-ss" value="${blog.updateTime }"/></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd hh-mm-ss" value="${blog.createTime }"/></td>
									<td><a data-id="${blog.id }" class="update"
										href="javascript:void(0)" data-toggle="modal"
										data-remote="false" data-target="#blog_update_modal"
										data-backdrop="static"> <i class="fa fa-edit"></i> 编辑</a>
										<a data-id="${blog.id }" class="del"
										href="javascript:void(0)"> <i class="fa fa-trash"></i> 删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${blogList == null }">
							<tr>
								<td colspan="7">无记录！</td>
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

	<div class="modal fade" id="blog_update_modal" tabindex="-1"
		role="dialog" aria-labelledby="blog_update_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="blog_update_label">编辑人员</h3>
					</div>
					<form class="form-horizontal" id="blog_update_form">
						<div class="box-body">
							<input type="hidden" id="blog_id" name="id">
							<div class="form-group">
								<label for="blog_typeName" class="col-sm-2 control-label">类型名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="blog_typeName"
										name="typeName" maxlength="10" placeholder="请输入用户名" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right" id="blog_update_button" data-loading-text="更新中...">更新</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="blog_add_modal" tabindex="-1"
		role="dialog" aria-labelledby="blog_add_label">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="box box-info">
					<div class="box-header with-border">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="box-title" id="blog_add_label">添加管理员</h3>
					</div>
					<form class="form-horizontal" method="post" id="blog_add_form">
						<div class="text-danger wrapper-xs text-center invisible"
							id="error_msg1">错误信息</div>
						<div class="box-body">
							<div class="form-group">
								<label for="add_blog_typeName" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="add_blog_typeName"
										name="typeName" maxlength="10" placeholder="请输入用户名" required>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-info pull-right"
								id="blog_add_button" data-loading-text="添加中...">添加</button>
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
                    url: "admin/blog/del",
                    type: "POST",
                    data: {id: id},
                    dataType: "json",
                    success: function (data) {
                        alert(data.msg);
                        if (data.success) {
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
            $("#blog_id").val($(this).data("id"));
            $("#blog_typeName").val($(this).parent().prevAll(".blog_typeName").text());
        });

        //更新
        var $blog_update_form = $("#blog_update_form");
        $blog_update_form.submit(function () {

            var $update_btn = $("#blog_update_button");

            $.ajax({
                url: "admin/blog/update",
                type: "POST",
                dataType: "json",
                data: $blog_update_form.serialize(),
                beforeSend: function () {
                    $update_btn.button("loading");
                },
                complete: function () {
                    $update_btn.button("reset");
                },
                success: function (data) {
                    alert(data.msg);
                    if (data.success) {
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
        var $blog_add_form = $("#blog_add_form");
        $blog_add_form.submit(function () {
        	var $error_msg = $("#error_msg1");

            var show_error = function (error_msg) {
                $error_msg.text(error_msg).removeClass("invisible");
            };
        	$error_msg.addClass("invisible");
            var userName = $.trim($("#blog_add_form input[name=userName]").val());
            var password = $.trim($("#blog_add_form input[name=password]").val());
            var confirmPassword = $.trim($("#blog_add_form input[name=confirmPassword]").val());
            var $add_btn = $("#blog_add_button");
            $.ajax({
                url: "admin/blog/insert",
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


        //更新modal
        $(".change_password").click(function () {
            $("#change_blog_id").val($(this).data("id"));
            $("#change_blog_typeName").text($(this).parent().prevAll(".blog_typeName").text());
        });

    })
</script>
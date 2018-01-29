<div class="content-wrapper">
	<section class="content-header">
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Tables</a></li>
			<li class="active">Simple</li>
		</ol>
	</section>

	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Responsive Hover Table</h3>

						<div class="box-tools">
							<div class="input-group input-group-sm" style="width: 150px;">
								<input type="text" name="table_search"
									class="form-control pull-right" placeholder="Search">

								<div class="input-group-btn">
									<button type="submit" class="btn btn-default">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">
							<tbody>
								<tr>
									<th>ID</th>
									<th>User</th>
									<th>Date</th>
									<th>Status</th>
									<th>Reason</th>
								</tr>
								<tr>
									<td>219</td>
									<td>Alexander Pierce</td>
									<td>11-7-2014</td>
									<td><span class="label label-warning">Pending</span></td>
									<td>Bacon ipsum dolor</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-5">
				<div class="dataTables_info" id="example2_info" role="status"
					aria-live="polite">Showing 1 to 10 of 57 entries</div>
			</div>
			<div class="col-sm-7">
				<div class="dataTables_paginate paging_simple_numbers"
					id="example2_paginate">
					<ul class="pagination">
						<li class="paginate_button previous disabled"
							id="example2_previous"><a href="#" aria-controls="example2"
							data-dt-idx="0" tabindex="0">首页</a></li>
						<li class="paginate_button active"><a href="#"
							aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
						<li class="paginate_button "><a href="#"
							aria-controls="example2" data-dt-idx="2" tabindex="0">2</a></li>
						<li class="paginate_button next" id="example2_next"><a
							href="#" aria-controls="example2" data-dt-idx="7" tabindex="0">尾页</a></li>
					</ul>
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
</div>
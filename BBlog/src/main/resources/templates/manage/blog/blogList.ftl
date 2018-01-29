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
</div>
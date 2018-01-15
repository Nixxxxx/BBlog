<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Top Navigation</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="../../bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/admin-lte/2.4.2/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/admin-lte/2.4.2/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="//fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">

		<header class="main-header">
			<nav class="navbar navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<a href="../../index2.html" class="navbar-brand"><b>Admin</b>LTE</a>
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar-collapse">
							<i class="fa fa-bars"></i>
						</button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse pull-left"
						id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">博客 <span class="sr-only">(current)</span></a></li>
							<li><a href="#">关于 </a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Dropdown <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li class="divider"></li>
									<li><a href="#">Separated link</a></li>
									<li class="divider"></li>
									<li><a href="#">One more separated link</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" id="navbar-search-input"
									placeholder="Search">
							</div>
						</form>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</header>
		<!-- Full Width Column -->
		<div class="content-wrapper">
			<div class="container">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<ol class="breadcrumb">
						<li><i class="fa fa-dashboard"></i> Home</li>
						<li>Layout</li>
						<li class="active">Top Navigation</li>
					</ol>
				</section>

				<!-- Main content -->
				<section class="content">
					<div class="col-md-4">
						<!-- Widget: user widget style 1 -->
						<div class="box box-widget widget-user">
							<!-- Add the bg color to the header using any of the bg-* classes -->
							<div class="widget-user-header bg-black"
								style="background: url('../dist/img/photo1.png') center center;">
								<h3 class="widget-user-username">Elizabeth Pierce</h3>
								<h5 class="widget-user-desc">Web Designer</h5>
							</div>
							<div class="widget-user-image">
								<img class="img-circle" src="../dist/img/user3-128x128.jpg"
									alt="User Avatar">
							</div>
							<div class="box-footer">
								<div class="row">
									<div class="col-sm-4 border-right">
										<div class="description-block">
											<h5 class="description-header">3,200</h5>
											<span class="description-text">SALES</span>
										</div>
										<!-- /.description-block -->
									</div>
									<!-- /.col -->
									<div class="col-sm-4 border-right">
										<div class="description-block">
											<h5 class="description-header">13,000</h5>
											<span class="description-text">FOLLOWERS</span>
										</div>
										<!-- /.description-block -->
									</div>
									<!-- /.col -->
									<div class="col-sm-4">
										<div class="description-block">
											<h5 class="description-header">35</h5>
											<span class="description-text">PRODUCTS</span>
										</div>
										<!-- /.description-block -->
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
						</div>
						<!-- /.widget-user -->
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.container -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer text-center">
			<div class="container">
				<strong>Copyright &copy; 2017 ~</strong>
			</div>
			<!-- /.container -->
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script
		src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="//cdn.bootcss.com/admin-lte/2.4.2/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="//cdn.bootcss.com/admin-lte/2.4.2/js/demo.js"></script>
</body>
</html>
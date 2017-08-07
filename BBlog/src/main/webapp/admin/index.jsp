<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客后台</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<base href="<%=basePath%>">

<!-- logo -->
<link rel="shortcut icon" href="static/images/avater.jpg">
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/jQuery-slimScroll/1.3.8/jquery.slimscroll.min.js"></script>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
<link rel="stylesheet" href="//cdn.bootcss.com/admin-lte/2.3.11/css/AdminLTE.css">
<link rel="stylesheet" href="//cdn.bootcss.com/admin-lte/2.3.11/css/skins/_all-skins.min.css">

<style>
body, button, input, select, textarea, h1, h2, h3, h4, h5, h6 {
	font-family: Microsoft YaHei, "宋体", Tahoma, Helvetica, Arial,
		"\5b8b\4f53", sans-serif;
}

.content-header>.breadcrumb {
	position: relative;
	margin-top: 5px;
	top: 0;
	right: 0;
	float: none;
	background: #d2d6de;
	padding-left: 10px;
}

.content-header>.breadcrumb li:before {
	color: #97a0b3;
}

.navbar-nav>.user-menu {
	padding-right: 30px;
}

.navbar-nav>.user-menu>.dropdown-menu>li.user-header {
	height: 150px;
}

.navbar-nav>.user-menu>.dropdown-menu>li.user-body {
	padding: 8px;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<a href="./" class="logo">
			<span class="logo-mini"><b>Blog</b></span>
			<span class="logo-lg"><b>My Blog</b></span>
		</a>
		<nav class="navbar navbar-static-top">
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span></a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown notifications-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-bell-o"></i> <span class="label label-warning">0</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">no notice</li>
						<li>
							<!-- inner menu: contains the actual data --> <!--<ul class="menu">
                  <li>
                    <a href="javascript:void(0);"> <i class="fa fa-users text-aqua"></i> no notice </a>
                  </li>
                </ul>-->
						</li>
						<li class="footer"><a href="javascript:void(0);">View all</a></li>
					</ul></li>
				<li class="dropdown user user-menu">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
					<img src="#" class="user-image" alt="User Image">
						<span class="hidden-xs">${admin.email }</span>
				  </a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img src="#" class="img-circle" alt="User Image">
							<p>
                				${admin.email }-超级管理员
							</p></li>
						<!-- Menu Body -->
						<li class="user-body">
							<div class="row">
								<div class="col-xs-5">上次登录时间</div>
								<div class="col-xs-7"></div>
								<div class="col-xs-5">上次登录IP</div>
								<div class="col-xs-7"></div>
							</div> <!-- /.row -->
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="javascript:void(0)" class="btn btn-success btn-flat">修改信息</a>
							</div>
							<div class="pull-right">
								<a href="admin/logout" class="btn btn-info btn-flat">退出登录</a>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		</nav> 
		</header>
		<aside class="main-sidebar">
		<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="header">MAIN NAVIGATION</li>
			<li class="treeview">
				<a href="#"><i class="fa fa-dashboard"></i>
					<span>总览</span> 
					<span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li><a href="#">
					<i class="fa fa-circle-o"></i> 总览</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#"><i class="fa fa-dashboard"></i>
					<span>博客管理</span> 
					<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li><a href="admin/blogType/list">
					<i class="fa fa-circle-o"></i> 博客类型管理 </a></li>
					<li><a href="admin/blog/list">
					<i class="fa fa-circle-o"></i> 博客管理 </a></li>
					<li><a href="admin/blog/edit">
					<i class="fa fa-circle-o"></i> 写博客 </a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#"><i class="fa fa-dashboard"></i>
					<span>用户管理</span> 
					<span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li><a href="admin/user/list">
					<i class="fa fa-circle-o"></i> 用户列表 </a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#"><i class="fa fa-dashboard"></i>
					<span>系统管理</span> 
					<span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li><a href="admin/list">
					<i class="fa fa-circle-o"></i> 管理员列表 </a></li>
					<li><a href="admin/blogger/info">
					<i class="fa fa-circle-o"></i> 博主信息 </a></li>
				</ul>
			</li>
			
			
			
			
			<li class="header">THEMES</li>
			<li><a href="javascript:void(0)" data-skin="skin-blue">
				<i class="fa fa-circle-o text-aqua"></i> <span>Blue</span></a></li>
			<li><a href="javascript:void(0)" data-skin="skin-green">
				<i class="fa fa-circle-o text-green"></i> <span>Green</span></a></li>
			<li><a href="javascript:void(0)" data-skin="skin-red">
				<i class="fa fa-circle-o text-red"></i> <span>Red</span></a></li>
		</ul>
		</section>
	</aside>

		<div class="content-wrapper">
			<c:if test="${pagePath != null }">
				<jsp:include page="${pagePath }" />
			</c:if>
		</div>
		
		<footer class="main-footer text-center"> 
			<strong> <a href="#" target="_blank">Nix</a></strong> 
			All Rights Reserved &copy; 2017 
		</footer>
	</div>

	<script src="//cdn.bootcss.com/admin-lte/2.3.11/js/app.min.js"></script>
	<script src="//cdn.bootcss.com/admin-lte/2.3.11/js/demo.js"></script>

	<!-- <script>
 $(function () {
    /**
     * 根据url高亮菜单
     * @param pathname location.pathname
     */
    function select_menu(pathname) {
        
        var $now_selected = null;
        $(".treeview-menu a").each(function () {
            if (pathname.indexOf($(this).data("menu")) >= 0) {
                $now_selected = $(this);
                return false;
            }
        });
        $(".treeview-menu:visible").hide();
        $(".treeview.active").removeClass("active");
        $(".treeview li.active").removeClass("active");
        $now_selected.parents(".treeview").addClass("active");
        $now_selected.parents(".treeview-menu").show();
        $now_selected.parent().addClass("active");
    }

    select_menu(location.pathname);

    //定时检测location.href是否变化 如果变化则选定当前url的菜单
    var old_href = location.href;

    function checkHrefChange() {
        if (old_href == location.href)
            return;
        old_href = location.href;
        select_menu(location.pathname);
    }
    setInterval(checkHrefChange, 300);


})
</script> -->
</body>
</html>
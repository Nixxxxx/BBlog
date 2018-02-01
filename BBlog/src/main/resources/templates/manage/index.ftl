<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Simple Tables</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="icon" href="../../favicon.png">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/admin-lte/2.4.2/css/AdminLTE.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/admin-lte/2.4.2/css/skins/_all-skins.min.css">
  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<#include "./common/navbar.ftl">
${page }
<#if pagePath??>
	<#include "${pagePath }">
</#if>
</div>

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<script src="//cdn.bootcss.com/admin-lte/2.4.2/js/adminlte.min.js"></script>
<script src="//cdn.bootcss.com/admin-lte/2.4.2/js/demo.js"></script>
</body>
</html>
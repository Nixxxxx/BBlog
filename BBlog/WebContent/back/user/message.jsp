<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
body{
	background-color:rgb(221,221,221);
}
</style>
<script type="text/javascript">
	function checkForm(){
		var userName=$("#userName").val();
		if(userName==null||userName==""){
			$("#error").text("用户名不能为空");
		}else $("#update_form").submit();
	}
	
	function edit(){
		$("#choice").val("保存");
		$("[readonly=true]").attr("readonly",false);
		$("#choice").attr("onclick","checkForm()");
		$("#imagePath").attr("disabled",false);
	}
</script>
	<ol class="breadcrumb">
	  	<li class="active">用户</li>
	  	<li class="active">个人中心</li>
  		<li ></li>
	</ol>
<div class="sidebar-content">
	<div class="row">
		<div class="col-sm-3 thumbnail">
			<img src="./statics/images/avater.jpg">
			<font id="error" color="red">${error }</font>
		</div>
		<div class="col-sm-9">
			<form class="form-horizontal" role="form"  id="update_form" action="user!update" method="post" >
				 <div class="form-group input-group">
                     <span class="input-group-addon">头像</span>
					 <input type="file" id="imagePath" name="user.imagePath" accept="image" disabled=true>
                 </div>
		 	  	 <div class="form-group input-group">
                     <span class="input-group-addon">用户名称</span>
                     <input type="text" class="form-control" id="userName" name="user.userName" readonly=true value="${currentUser.userName }"
					 maxlength="20" size="20">
                 </div>
                 <div class="form-group input-group">
                     <span class="input-group-addon">个性签名</span>
                     <textarea id="mood" class="form-control" name="user.mood" rows="3" readonly=true>${currentUser.mood }</textarea>
                 </div>

                 <div class="form-group"> 
                 	<input type="button" id="choice" class="btn btn-success btn-block" onclick="edit()" value="编辑"></input></td>
					<input type="hidden" id="userId" name="user.userId" value="${currentUser.userId }"/>
					<input type="hidden" id="password" name="user.password" value="${currentUser.password }"/>
                 </div> 
	        </form>
        </div>
	</div>
</div>
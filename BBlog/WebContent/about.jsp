<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap3/css/font-awesome.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/bootstrap3/css/main.css" rel="stylesheet">
 
	<link type="text/css" rel="stylesheet" charset="UTF-8" href="${pageContext.request.contextPath}/bootstrap3/css/translateelement.css">
  <script type="text/javascript">
  try {
	var AG_onLoad=function(func){
		if(document.readyState==="complete"||document.readyState==="interactive")
			func();
		else if(document.addEventListener)
			document.addEventListener("DOMContentLoaded",func);
		else if(document.attachEvent)document.attachEvent("DOMContentLoaded",func)};
	var AG_removeElementById = function(id) {
		var element = document.getElementById(id); 
		if (element && element.parentNode) { 
			element.parentNode.removeChild(element); }};
	var AG_removeElementBySelector = function(selector) { 
		if (!document.querySelectorAll) 
		{ return; } 
		var nodes = document.querySelectorAll(selector); 
		if (nodes) { for (var i = 0; i < nodes.length; i++) { 
			if (nodes[i] && nodes[i].parentNode) { 
				nodes[i].parentNode.removeChild(nodes[i]); 
				} } } };
	var AG_each = function(selector, fn) { if (!document.querySelectorAll) return; var elements = document.querySelectorAll(selector); for (var i = 0; i < elements.length; i++) { fn(elements[i]); }; };
	var AG_removeParent = function(el, fn) { while (el && el.parentNode) { if (fn(el)) { el.parentNode.removeChild(el); return; } el = el.parentNode; } };
	window.AG_onLoad = function(func) { if (window.addEventListener) { window.addEventListener('DOMContentLoaded', func); } };
	window.AG_removeElementById = function(id) { var element = document.getElementById(id); if (element && element.parentNode) { element.parentNode.removeChild(element); }};
	window.AG_removeElementBySelector = function(selector) { if (!document.querySelectorAll) { return; } var nodes = document.querySelectorAll(selector); if (nodes) { for (var i = 0; i < nodes.length; i++) { if (nodes[i] && nodes[i].parentNode) { nodes[i].parentNode.removeChild(nodes[i]); } } } };
	window.AG_each = function(selector, fn) { if (!document.querySelectorAll) return; var elements = document.querySelectorAll(selector); for (var i = 0; i < elements.length; i++) { fn(elements[i]); }; };
	} catch (ex) { console.error('Error executing AG js: ' + ex); }
</script>
<style id="style-1-cropbar-clipper">
	body{
		background: url(images/background.JPG) no-repeat;
		background-size: <span style="font-family: 微软雅黑; background-color: rgb(239, 239, 239);">cover</span>;
		font-size: 16px;} 
	.en-markup-crop-options {
	    top: 18px !important;
	    left: 50% !important;
	    margin-left: -100px !important;
	    width: 200px !important;
	    border: 2px rgba(255,255,255,.38) solid !important;
	    border-radius: 4px !important;
	}
	
	.en-markup-crop-options div div:first-of-type {
	    margin-left: 0px !important;
	}
</style>

<body>

    <div class="container">
    	<div class="col-lg-6 col-lg-offset-3">
    	
    		<!-- ===== vCard Navigation ===== -->
    		<div class="row w">
    			<div class="col-md-4">
    				<img class="img-responsive" src="images/avatar.jpg" alt="">
					<ul class="nav nav-tabs nav-stacked" id="myTab">
					  <li class="active"><a href="#about">About</a></li>
					  <li class=""><a href="#profile">Profile</a></li>
					  <li class=""><a href="#portfolio">Portfolio</a></li>
					  <li class=""><a href="#contact">Contact</a></li>
					</ul>    			
				</div><!-- col-md-4 -->

    		<!-- ===== vCard Content ===== -->
    			<div class="col-md-8">
	    			<div class="tab-content">
	    			
	    			  <!-- ===== First Tab ===== -->
					  <div class="tab-pane active" id="about">
					  	<h3>Victoria Wallberg</h3>
					  	<h5>Web Designer</h5>
					  	<hr>
					  	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p> 
					  	<p>Has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it.</p>
					  	<p class="pull-right red"><i class="icon-heart"></i></p>
					  </div><!-- tab about -->
					  
	    			  <!-- ===== Second Tab ===== -->
					  <div class="tab-pane" id="profile">
					  	<h4><i class="icon-briefcase"></i> Job Experience</h4>
					  	<p class="sm">
					  		<grey>Black Tie Corp.</grey> | June 2013 - Actual.<br>
					  		<grey>Other Corp.</grey> | January 2011 - May 2013.<br>
					  	</p>
					  
						  <h4><i class="icon-file-text-alt"></i> Education</h4>
					  	<p class="sm">
					  		<grey>Web Designer</grey> | Greenville University.<br>
					  		<grey>Business Master</grey> | Loyal Univesrity.<br>
					  	</p>
					  	
						  <h4><i class="icon-trophy"></i> Awards</h4>
					  	<p class="sm">
					  		<grey>Best Web Design</grey> | Black Tie Site.<br>
					  		<grey>Designer of the Year</grey> | 2012.<br>
					  	</p>
					  </div><!-- Tab Profile -->
					  
	    			  <!-- ===== Third Tab ===== -->
					  <div class="tab-pane" id="portfolio">
						  <div class="row">
							  <div class="col-xs-6 centered">
							  	<img class="img-responsive" src="images/p01.png" alt="">
							  	<a href="#"><h6><i class="icon-link"></i> Project Name</h6></a>
							  </div><!-- col-xs-6 -->
							  <div class="col-xs-6 centered">
							  	<img class="img-responsive" src="images/p02.png" alt="">
							  	<a href="#"><h6><i class="icon-link"></i> Project Name</h6></a>
							  </div><!-- col-xs-6 -->
						  </div><!-- row -->
						  
						  <div class="row">
						  	<br>	
							  <div class="col-xs-6 centered">
							  	<img class="img-responsive" src="images/p03.png" alt="">
							  	<a href="#"><h6><i class="icon-link"></i> Project Name</h6></a>
							  </div><!-- col-xs-6 -->
							  <div class="col-xs-6 centered">
							  	<img class="img-responsive" src="images/p04.png" alt="">
							  	<a href="#"><h6><i class="icon-link"></i> Project Name</h6></a>
							  </div><!-- col-xs-6 -->
						  </div><!-- row -->
					  </div><!-- /Tab Portfolio -->
					  
	    			  <!-- ===== Fourth Tab ===== -->
					  <div class="tab-pane" id="contact">
						  <h4>Contact Information</h4>
						  <hr>
						  <div class="row">
							  <div class="col-xs-6">
								  <p class="sm">
								  	<i class="icon-globe"></i> - BlackTie.co <br>
									<i class="icon-envelope"></i> - victoria@blacktie.co  
								  </p>
							  </div><!-- col-xs-6 -->
							  
							  <div class="col-xs-6">
								  <p class="sm">
								  	<i class="icon-phone"></i> - +44 2009-4839 <br>
									<i class="icon-apple"></i> - 902 3940-4439  
								  </p>
							  </div><!-- col-xs-6 -->
						  </div><!-- row -->
						  
						  <h4>Social Links</h4>
						  <hr>
						  <div class="row">
							  <div class="col-xs-6">
								  <p class="sm">
								  	<i class="icon-facebook"></i> - Vicky.Wallberg <br>
									<i class="icon-tumblr"></i> - Love-Vicky-Site  
								  </p>
							  </div><!-- col-xs-6 -->
							  
							  <div class="col-xs-6">
								  <p class="sm">
								  	<i class="icon-dribbble"></i> - Vicoooria <br>
									<i class="icon-twitter"></i> - @BlackTie_co  
								  </p>
							  </div><!-- col-xs-6 -->
						  </div><!-- row -->
					  </div><!-- Tab Contact -->
					  
					</div><!-- Tab Content -->
    			</div><!-- col-md-8 -->
    		</div><!-- row w -->
    	</div><!-- col-lg-6 -->
    </div><!-- /.container -->


    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	<script>
	$('#myTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})	
	</script>
</body>
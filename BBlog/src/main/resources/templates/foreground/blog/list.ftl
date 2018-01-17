<ol class="breadcrumb">
	<li><i class="fa fa-dashboard"></i> 首页</li>
	<li class="active">博客</li>
</ol>
<div class="nav-tabs-custom">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#activity" data-toggle="tab"
			aria-expanded="true">Activity</a></li>
		<li class=""><a href="#timeline" data-toggle="tab"
			aria-expanded="false">Timeline</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="activity">
			<#list blogList as blog>
			<div class="post">
				<div class="user-block">
					<img class="img-circle img-bordered-sm"
						src="../../dist/img/user1-128x128.jpg" alt="user image"> <span
						class="username"> <a href="#">Jonathan Burke Jr.</a> <a
						href="#" class="pull-right btn-box-tool"></a>
					</span> <span class="description">Shared publicly - 7:30 PM today</span>
				</div>
				<p>${blog.summary}</p>
				<ul class="list-inline">
					<li><a href="#" class="link-black text-sm"><i
							class="fa fa-share margin-r-5"></i> Share</a></li>
					<li><a href="#" class="link-black text-sm"><i
							class="fa fa-eye margin-r-5"></i> Like</a></li>
					<li class="pull-right"><a href="#" class="link-black text-sm"><i
							class="fa fa-comments-o margin-r-5"></i> Comments (5)</a></li>
				</ul>
			</div>
			</#list>
			<ul class="pagination pull-right">
				<li class="paginate_button previous disabled"><a
					href="#" aria-controls="example2" data-dt-idx="0" tabindex="0">首页</a></li>
				<li class="paginate_button"><a href="#"
					aria-controls="example2" data-dt-idx="1" tabindex="0">&lt;</a></li>
				<li class="paginate_button active"><a href="#"
					aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
				<li class="paginate_button"><a href="#"
					aria-controls="example2" data-dt-idx="1" tabindex="0">&gt;</a></li>
				<li class="paginate_button previous disabled"><a
					href="#" aria-controls="example2" data-dt-idx="0" tabindex="0">尾页</a></li>
			</ul>
		</div>
		<div class="tab-pane" id="timeline">
			<ul class="timeline timeline-inverse">
				<li class="time-label"><span class="bg-red"> 10 Feb.2014
				</span></li>
				<li><i class="fa fa-envelope bg-blue"></i>
					<div class="timeline-item">
						<span class="time"><i class="fa fa-clock-o"></i> 12:05</span>
						<h3 class="timeline-header">
							<a href="#">Support Team</a> sent you an email
						</h3>
						<div class="timeline-body">Etsy doostang zoodles disqus
							groupon greplin oooj voxy zoodles, weebly ning heekya handango
							imeem plugg dopplr jibjab, movity jajah plickers sifteo edmodo
							ifttt zimbra. Babblely odeo kaboodle quora plaxo ideeli hulu
							weebly balihoo...</div>
						<div class="timeline-footer">
							<a class="btn btn-primary btn-xs">Read more</a> <a
								class="btn btn-danger btn-xs">Delete</a>
						</div>
					</div></li>
				<li><i class="fa fa-user bg-aqua"></i>

					<div class="timeline-item">
						<span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>

						<h3 class="timeline-header no-border">
							<a href="#">Sarah Young</a> accepted your friend request
						</h3>
					</div></li>
				<li><i class="fa fa-comments bg-yellow"></i>

					<div class="timeline-item">
						<span class="time"><i class="fa fa-clock-o"></i> 27 mins
							ago</span>

						<h3 class="timeline-header">
							<a href="#">Jay White</a> commented on your post
						</h3>

						<div class="timeline-body">Take me to your leader!
							Switzerland is small and neutral! We are more like Germany,
							ambitious and misunderstood!</div>
						<div class="timeline-footer">
							<a class="btn btn-warning btn-flat btn-xs">View comment</a>
						</div>
					</div></li>
				<li class="time-label"><span class="bg-green"> 3 Jan.
						2014 </span></li>
				<li><i class="fa fa-camera bg-purple"></i>

					<div class="timeline-item">
						<span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>

						<h3 class="timeline-header">
							<a href="#">Mina Lee</a> uploaded new photos
						</h3>

						<div class="timeline-body">
							<img src="http://placehold.it/150x100" alt="..." class="margin">
							<img src="http://placehold.it/150x100" alt="..." class="margin">
							<img src="http://placehold.it/150x100" alt="..." class="margin">
							<img src="http://placehold.it/150x100" alt="..." class="margin">
						</div>
					</div></li>
				<li><i class="fa fa-clock-o bg-gray"></i></li>
			</ul>
		</div>
	</div>
</div>

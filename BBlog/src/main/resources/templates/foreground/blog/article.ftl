<ol class="breadcrumb">
	<li><i class="fa fa-dashboard"></i> 首页</li>
	<li>博客</li>
	<li class="active">{title}</li>
</ol>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><i class="fa fa-hand-peace-o"></i>文章</h3>
		<div class="box-tools pull-right">
			<a href="#" class="btn btn-box-tool" data-toggle="tooltip" title=""
				data-original-title="上一篇"><i class="fa fa-arrow-left"></i></a>
			<a href="#" class="btn btn-box-tool" data-toggle="tooltip" title=""
				data-original-title="下一篇"><i class="fa fa-arrow-right"></i></a>
		</div>
	</div>
	<div class="box-body no-padding">
		<div class="mailbox-read-info">
			<h3>Message Subject Is Placed Here</h3>
			<h5>
				From: help@example.com <span class="mailbox-read-time pull-right">15
					Feb. 2016 11:03 PM</span>
			</h5>
		</div>
		<div class="mailbox-controls with-border text-center">
			<div class="btn-group">
				<button type="button" class="btn btn-default btn-sm"
					data-toggle="tooltip" data-container="body" title=""
					data-original-title="Reply">
					<i class="fa fa-reply"></i>
				</button>
				<button type="button" class="btn btn-default btn-sm"
					data-toggle="tooltip" data-container="body" title=""
					data-original-title="Forward">
					<i class="fa fa-share"></i>
				</button>
			</div>
			<button type="button" class="btn btn-default btn-sm"
				data-toggle="tooltip" title="" data-original-title="Print">
				<i class="fa fa-print"></i>
			</button>
		</div>
		<div class="mailbox-read-message">{cotent}</div>
	</div>
	<div class="box-footer">
		<div class="pull-right">
			<button type="button" class="btn btn-default">
				<i class="fa fa-reply"></i> Reply
			</button>
			<button type="button" class="btn btn-default">
				<i class="fa fa-share"></i> Forward
			</button>
		</div>
	</div>

	<div id="SOHUCS"></div>
	<script type="text/javascript">
		(function() {
			var appid = 'cytq41Lfx';
			var conf = 'prod_c0088c2946c4ac7085129483d05720cd';
			var width = window.innerWidth
					|| document.documentElement.clientWidth;
			if (width < 960) {
				window.document
						.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id='
								+ appid + '&conf=' + conf + '"><\/script>');
			} else {
				var loadJs = function(d, a) {
					var c = document.getElementsByTagName("head")[0]
							|| document.head || document.documentElement;
					var b = document.createElement("script");
					b.setAttribute("type", "text/javascript");
					b.setAttribute("charset", "UTF-8");
					b.setAttribute("src", d);
					if (typeof a === "function") {
						if (window.attachEvent) {
							b.onreadystatechange = function() {
								var e = b.readyState;
								if (e === "loaded" || e === "complete") {
									b.onreadystatechange = null;
									a()
								}
							}
						} else {
							b.onload = a
						}
					}
					c.appendChild(b)
				};
				loadJs("http://changyan.sohu.com/upload/changyan.js",
						function() {
							window.changyan.api.config({
								appid : appid,
								conf : conf
							})
						});
			}
		})();
	</script>
</div>
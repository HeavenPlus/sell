<!DOCTYPE html>
<html>
	<#include "header.ftl">
<body>
	<div id="wrapper" class="toggled">
	<#-- 边栏 -->
	<#include "nav.ftl">
	<#-- 主要内容 -->
	<div id="page-content-wrapper">
		
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="alert alert-dismissable alert-danger">
						 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
						<h4>
						错误！
						</h4> <strong>${msg}</strong> <a href="${url}" class="alert-link">3S后页面跳转</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
<script>
	setTimeout('location.href="${url}"',3000);
</script>
</body>
</html>

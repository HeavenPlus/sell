<head>
<meta charset="UTF-8">
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/sell/css/style.css">
<title>错误提示</title>
</head>
<body>
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
	
<script>
	setTimeout('location.href="${url}"',3000);
</script>
</body>
</html>

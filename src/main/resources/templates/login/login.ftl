<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>卖家后台管理系统登陆</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="/sell/seller/login" method="post">
					<div class="form-group">
					<strong>${username!''}</strong>
					<strong>${msg!''}</strong>
					<br>
						<label for="usr">用户名:</label> <input type="text"
							class="form-control" id="usr" name="username">
					</div>
					<div class="form-group">
						<strong>${password!''}</strong><br>
						<label for="pwd">密码:</label> <input type="password"
							class="form-control" id="pwd" name="password">
					</div>
					<button type="submit" class="btn btn-default">登陆</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
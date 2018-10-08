<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<title>订单页面</title>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>订单ID</th>
						<th>姓名</th>
						<th>手机号</th>
						<th>地址</th>
						<th>金额</th>
						<th>订单状态</th>
						<th>支付状态</th>
						<th>创建时间</th>
						<th colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
					<#list orderDTO.content as order>
					<tr>
						<td>${order.orderId}</td>
						<td>${order.buyerName}</td>
						<td>${order.buyerPhone}</td>
						<td>${order.buyerAddress}</td>
						<td>${order.orderAmount}</td>
						<td>${order.getOrderStatusEunm().msg}</td>
						<td>${order.getPayStatusEunm().msg}</td>
						<td>${order.createTime}</td>
						<td><a href="/sell/seller/order/detail?orderId=${order.orderId}">详情</a></td>
						<#if order.getOrderStatus()==0>
							<td><a href="/sell/seller/order/cancel?orderId=${order.orderId}">取消</a></td>
						<#else>
							<td></td>
						</#if>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
		<div class="col-md-12 column">
			<ul class="pagination pull-right">
				<#if currentPage lte 1>
					<li class=disabled><a href="#">上一页</a></li>
				<#else>
					<li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
				</#if>
				<#list 1..orderDTO.getTotalPages() as index>
					<#if currentPage==index>
						<li class=disabled><a href="#">${index}</a></li>
					<#else>
						<li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
					</#if>
				</#list>
				<#if currentPage gte orderDTO.getTotalPages()>
					<li class="disabled"><a href="#">下一页</a></li>
				<#else>
					<li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
				</#if>
			</ul>
		</div>
	</div>
</div>
</body>
</html>

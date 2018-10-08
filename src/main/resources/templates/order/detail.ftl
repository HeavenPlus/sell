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
					<div class="col-md-4 column">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>订单ID</th>
									<th>订单总金额</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${orderDTO.orderId}</td>
									<td>${orderDTO.orderAmount}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>商品ID</th>
								<th>商品名称</th>
								<th>价格</th>
								<th>数量</th>
								<th>总额</th>
							</tr>
						</thead>
						<tbody>
							<#list orderDTO.orderDetail as detail>
								<tr>
									<td>${detail.productId}</td>
									<td>${detail.productName}</td>
									<td>${detail.productPrice}</td>
									<td>${detail.productQuantity}</td>
									<td>${detail.productPrice * detail.productQuantity}</td>
								</tr>
							</#list>
						</tbody>
					</table>
					<#if orderDTO.orderStatus==0>
						<a type="button" class="btn btn-default btn-primary" href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a>
					 	<a type="button" class="btn btn-default btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
					</#if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>

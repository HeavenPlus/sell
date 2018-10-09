<!DOCTYPE html>
<html>
	<#include "../order/header.ftl">
<body>
<div id="wrapper" class="toggled">
	<#-- 边栏 -->
	<#include "../order/nav.ftl">
	<#-- 主要内容 -->
	<div id="page-content-wrapper">
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form role="form" action="/sell/seller/product/save" method="POST">
				<div class="form-group">
					 <label for="exampleInputEmail1">商品名称</label><input class="form-control" id="exampleInputEmail1" type="text" name="productName" value="${(productInfo.productName)!""}"/>
					 <label for="exampleInputEmail1">商品价格</label><input class="form-control" id="exampleInputEmail1" type="text" name="productPrice" value="${(productInfo.productPrice)!""}"/>
					 <label for="exampleInputEmail1">商品库存</label><input class="form-control" id="exampleInputEmail1" type="text" name="productStock" value="${(productInfo.productStock)!""}"/>
					 <label for="exampleInputEmail1">商品描述</label><input class="form-control" id="exampleInputEmail1" type="text" name="productDescription" value="${(productInfo.productDescription)!""}"/>
					 <label for="exampleInputEmail1">商品图片</label><br><img width=100 height=100 src="${(productInfo.productIcon)!""}"><input class="form-control" id="exampleInputEmail1" type="text" name="productIcon" value="${(productInfo.productIcon)!""}"/>
					 <label for="exampleInputEmail1">商品类目</label>
					 <select class="form-control" id="exampleInputEmail1" name="categoryType">
					 	<#list productCategory as category>
						 	<#if (productInfo.categoryType)?? &&category.categoryType==productInfo.categoryType>
								<option value="${category.categoryType}" selected>${category.categoryName}</option>
						 	<#else>
								<option value="${category.categoryType}">${category.categoryName}</option>
						 	</#if>
						</#list>
					 </select>
				</div>
				<input hidden name="productId" value="${(productInfo.productId)!''}">
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</div>
</div>
	</div>
</div>

</body>
</html>

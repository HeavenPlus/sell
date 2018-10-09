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
                    <form role="form" action="/sell/seller/category/save" method="POST">
                        <div class="form-group">
                            <label>名称</label><input type="text" class="form-control" name="categoryName" value="${(productCategory.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类别</label><input type="text" class="form-control" name="categoryType" value="${(productCategory.categoryType)!''}"/>
                        </div>
                        <input hidden name="categoryId" value="${(productCategory.categoryId)!''}">

                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>

            </div>
        </div>
    </div>

</div>

</body>

</html>
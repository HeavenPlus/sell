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
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>类目id</th>
                            <th>类目名称</th>
                            <th>类目类别</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productCategory as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>
                                <a href="/sell/seller/category/index?categoryId=${category.categoryId}">编辑</a>
                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

</body>

</html>
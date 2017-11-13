<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <!--边栏-->
    <#include "../common/nav.ftl">
    <!--主要内容区域-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <#--添加偏移量让其居中显示-->
                        <div class="col-md-4 column col-md-offset-4">
                            <form role="form" method="post" action="/sell/seller/category/save">
                                <#--埋入hidden categoryId-->
                                <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!""}">
                                <div class="form-group">
                                    <label>名称</label>
                                    <#--此处注意写法-->
                                    <input name="categoryName" type="text" class="form-control" id="categoryName"
                                           value="${(productCategory.categoryName)!""}" />
                                </div>
                                <div class="form-group">
                                    <label>类型</label>
                                    <input name="categoryType" type="number" class="form-control" id="categoryType"
                                           value="${(productCategory.categoryType)!""}" />
                                </div>
                                <button type="submit" class="btn btn-success">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
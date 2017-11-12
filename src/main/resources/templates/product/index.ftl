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
                            <form role="form" method="post" action="/sell/seller/product/save">
                                <#--埋入hidden productId-->
                                <input hidden type="text" name="productId" value="${(productInfo.productId)!""}">
                                <div class="form-group">
                                    <label>名称</label>
                                    <#--此处注意写法-->
                                    <input name="productName" type="text" class="form-control" id="productName"
                                           value="${(productInfo.productName)!""}" />
                                </div>
                                <div class="form-group">
                                    <label>价格</label>
                                    <input name="productPrice" type="text" class="form-control" id="productPrice"
                                           value="${(productInfo.productPrice)!""}" />
                                </div>
                                <div class="form-group">
                                    <label>库存</label>
                                    <#--库存必为数字类型-->
                                    <input name="productStock" type="number" class="form-control" id="productStock"
                                           value="${(productInfo.productStock)!""}" />
                                </div>
                                <div class="form-group">
                                    <label>描述</label>
                                    <input name="productDescription" type="text" class="form-control" id="productDescription"
                                           value="${(productInfo.productDescription)!""}" />
                                </div>
                                <div class="form-group">
                                    <label>图片</label>
                                    <#--添加缩略的属性, 设置宽高-->
                                    <img class="thumbnail" width="100" height="100" src="${(productInfo.productIcon)!""}" alt="商品图片">
                                    <input name="productIcon" type="text" class="form-control" id="productIcon"
                                           value="${(productInfo.productIcon)!""}" />
                                </div>
                                <div class="form-group">
                                    <label>类目</label>
                                    <select name="categoryType" id="categoryType" class="form-control">
                                        <#list categoryList as category>
                                            <option value="${category.categoryType}"
                                                <#--进行相应的判断，若存在 且相等-->
                                                <#if (productInfo.categoryType)??
                                                        && productInfo.categoryType == category.categoryType>
                                                    <#--加上selected关键字-->
                                                    selected
                                                </#if>
                                                >${category.categoryName}
                                            </option>
                                        </#list>
                                    </select>
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
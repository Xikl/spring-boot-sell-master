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
                    <!--表格-->
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>
                                    商品id
                                </th>
                                <th>
                                    名称
                                </th>
                                <th>
                                    图片
                                </th>
                                <th>
                                    单价
                                </th>
                                <th>
                                    库存
                                </th>
                                <th>
                                    描述
                                </th>
                                <th>
                                    类目
                                </th>
                                <th>
                                    创建时间
                                </th>
                                <th>
                                    修改时间
                                </th>
                                <th colspan="2">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>
                                ${productInfo.productId}
                                </td>
                                <td>
                                ${productInfo.productName}
                                </td>
                                <td>
                                <img width="100" height="100" src="${productInfo.productIcon}" alt="图片">
                                </td>
                                <td>
                                ${productInfo.productPrice}
                                </td>
                                <td>
                                ${productInfo.productStock}
                                </td>
                                <td>
                                ${productInfo.productDescription}
                                </td>
                                <td>
                                ${productInfo.categoryType}
                                </td>
                                <td>
                                ${productInfo.createTime}
                                </td>
                                <td>
                                ${productInfo.updateTime}
                                </td>
                                <td>
                                    <a href="/sell/seller/product/index/${productInfo.productId}" type="button"
                                       class="btn btn-xs btn-success">修改</a>
                                </td>
                                <td>
                                    <#if productInfo.getProductStatusEnums().msg == "在架">
                                        <a href="/sell/seller/product/off_sell/${productInfo.productId}" type="button"
                                           class="btn btn-xs btn-warning">下架</a>
                                        <#else>
                                            <a href="/sell/seller/product/on_sell/${productInfo.productId}" type="button"
                                               class="btn btn-xs btn-warning">上架</a>
                                    </#if>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <!--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <!--定义分页总数-->
                        <#assign totalPage = productInfoPage.getTotalPages()/>
                            <!--为第一页的时候-->
                        <#if currentPage lte 1>
                            <li class="hidden">
                                <a href="#">上一页</a>
                            </li>
                            <!--不为第一页的时候-->
                        <#else>
                            <li>
                                <!--获得后台传过来的CurrentPage和Size对象-->
                                <a href="/sell/seller/product/list?page=${currentPage - 1}&size=${size}">上一页</a>
                            </li>
                        </#if>
                            <!--开始循环分页-->
                        <#list 1..totalPage as index>
                            <#if currentPage == index>
                                <li class="disabled">
                                    <a href="#">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte totalPage>
                            <li class="hidden">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
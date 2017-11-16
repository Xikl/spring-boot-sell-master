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
                                    订单id
                                </th>
                                <th>
                                    姓名
                                </th>
                                <th>
                                    手机号
                                </th>
                                <th>
                                    地址
                                </th>
                                <th>
                                    金额
                                </th>
                                <th>
                                    订单状态
                                </th>
                                <th>
                                    支付状态
                                </th>
                                <th>
                                    创建时间
                                </th>
                                <th colspan="2">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>
                                ${orderDTO.orderId}
                                </td>
                                <td>
                                ${orderDTO.buyerName}
                                </td>
                                <td>
                                ${orderDTO.buyerPhone}
                                </td>
                                <td>
                                ${orderDTO.buyerAddress}
                                </td>
                                <td>
                                ${orderDTO.buyerAmount}
                                </td>
                                <td>
                                ${orderDTO.getOrderStatusEnums().msg}
                                </td>
                                <td>
                                ${orderDTO.getPayStatusEnums().getMsg()}
                                </td>
                                <td>
                                ${orderDTO.createTime}
                                </td>
                                <td>
                                    <a href="/sell/seller/order/detail/${orderDTO.orderId}" type="button"
                                       class="btn btn-xs btn-success">详情</a>
                                </td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnums().msg == "新订单">
                                        <a href="/sell/seller/order/cancel/${orderDTO.orderId}" type="button"
                                           class="btn btn-xs btn-warning">取消</a>
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
                        <#assign totalPage = orderDTOPage.getTotalPages()/>
                            <!--为第一页的时候-->
                        <#if currentPage lte 1>
                            <li class="hidden">
                                <a href="#">上一页</a>
                            </li>
                            <!--不为第一页的时候-->
                        <#else>
                            <li>
                                <!--获得后台传过来的CurrentPage和Size对象-->
                                <a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a>
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
                                    <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte totalPage>
                            <li class="hidden">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    var websocket = null;
    if("WebSocket" in window){
        websocket = new WebSocket("ws://localhost:8080/sell/webSocket");
    }else{
        alter('你的浏览器不支持websocket')
    }

    websocket.onopen = function (event) {
        console.log("建立连接");

    };

    websocket.onclose = function (event) {
        console.log("关闭连接");
    };
    
    websocket.onmessage = function (event) {
        console.log("收到消息" + event.data);
        /*播放音乐 或者别的*/
    };

    websocket.onerror = function (event) {
        console.log("通信发生错误");
        alert("通信发生错误");
    };

    window.onbeforeunload = function () {
        websocket.close();
    }
</script>
</body>
</html>
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


    <div class="modal fade" id="modal-container-84160" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        提醒
                    </h4>
                </div>
                <div class="modal-body">
                    你有新的订单.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
                </div>
            </div>

        </div>

    </div>

<script>
    var websocket = null;

    if('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    };

    websocket.onclose = function (event) {
        console.log('连接关闭');
    };

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        /*播放音乐 或者别的*/

    };

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    };

    window.onbeforeunload = function () {
        websocket.close();
    };
</script>
</body>
</html>
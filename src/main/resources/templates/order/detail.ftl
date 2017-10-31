<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>卖家订单详情</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>
                                ${orderDTO.orderId}
                            </td>
                            <td>
                                ${orderDTO.buyerAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-8 column">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                            <tr class="warning">
                                <td>
                                    ${orderDetail.productId}
                                </td>
                                <td>
                                    ${orderDetail.productName}
                                </td>
                                <td>
                                    ${orderDetail.productPrice}
                                </td>
                                <td>
                                    ${orderDetail.productQuantity}
                                </td>
                                <!--总额-->
                                <td>
                                    ${orderDetail.productQuantity * orderDetail.productPrice}
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <#if orderDTO.getOrderStatusEnums().msg == "新订单">
                <a href="/sell/seller/order/finish/${orderDTO.orderId}" type="button" class="btn btn-default btn-success">
                    完成订单</a>
                <a href="/sell/seller/order/cancel/${orderDTO.orderId}" type="button" class="btn btn-default btn-warning">
                    取消订单</a>
                <#else>
                    <a href="/sell/seller/order/list" type="button" class="btn btn-default btn-info">该订单已经被取消或者完结，点我返回</a>
            </#if>
        </div>
    </div>
</div>
</body>
</html>
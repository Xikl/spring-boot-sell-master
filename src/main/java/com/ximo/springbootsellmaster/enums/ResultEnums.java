package com.ximo.springbootsellmaster.enums;

import lombok.Getter;

/**
 * 异常消息枚举
 * Created by 朱文赵
 * 2017/9/11
 */
@Getter
public enum ResultEnums {

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不足"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单中无商品详情"),
    PAY_STATUS_ERROR(17, "订单支付状态错误"),
    CART_EMPTY(18, "购物车不能为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于该用户"),
    WX_MP_ERROR(20, "微信公众账号方面错误"),
    ORDER_CANCEL_SUCCESS(21, "订单取消成功"),
    ORDER_FINISH_SUCCESS(22, "订单完结成功"),
    PRODUCT_STATUS_ERROR(23, "商品订单信息错误"),
    PRODUCT_ON_SALE_SUCCESS(24, "商品上架成功"),
    PRODUCT_OFF_SALE_SUCCESS(25, "商品上架成功"),
    ;

    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

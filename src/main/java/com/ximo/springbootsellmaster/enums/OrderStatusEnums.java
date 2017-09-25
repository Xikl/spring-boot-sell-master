package com.ximo.springbootsellmaster.enums;

import lombok.Getter;

/**
 * 订单状态
 * Created by 朱文赵
 * 2017/9/10
 */
@Getter
public enum  OrderStatusEnums implements CodeEnums<Integer>{

    NEW(0, "新订单"),
    FINISHED(1, "已完结"),
    CANCEL(2, "已取消")
    ;

    private Integer code;

    private String msg;

    OrderStatusEnums(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /** @see com.ximo.springbootsellmaster.util.EnumUtil*/
//    public static OrderStatusEnums getOrderStatusEnums(Integer code){
//        for (OrderStatusEnums orderStatusEnums: OrderStatusEnums.values()) {
//            if (orderStatusEnums.getCode().equals(code)){
//                return orderStatusEnums;
//            }
//        }
//        return null;
//    }

}

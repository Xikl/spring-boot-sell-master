package com.ximo.springbootsellmaster.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by 朱文赵
 * 2017/9/9
 */
@Getter
public enum ProductStatusEnums {

    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    /** 状态 */
    private Integer code;

    /** 标注的信息 */
    private String msg;

    ProductStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

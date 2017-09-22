package com.ximo.springbootsellmaster.enums;

import lombok.Getter;

/**
 * 支付状态
 * Created by 朱文赵
 * 2017/9/10
 */
@Getter
public enum PayStatusEnums implements CodeEnums<Integer>{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功")
    ;
    private Integer code;

    private String msg;

    PayStatusEnums(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}

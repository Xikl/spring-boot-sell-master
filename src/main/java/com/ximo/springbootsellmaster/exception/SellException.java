package com.ximo.springbootsellmaster.exception;

import com.ximo.springbootsellmaster.enums.ResultEnums;
import lombok.Getter;

/**
 * Created by 朱文赵
 * 2017/9/11
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    /**
     * 获得异常枚举对象的msg和code
     * @param resultEnums 异常枚举对象
     */
    public SellException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.getCode();
    }

    /**
     * 异常构造函数
     * @param code
     * @param msg
     */
    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}

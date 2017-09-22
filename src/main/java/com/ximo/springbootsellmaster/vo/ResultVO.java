package com.ximo.springbootsellmaster.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http请求方茴的最外层对象
 * Created by 朱文赵
 * 2017/9/9
 */
@Data
@AllArgsConstructor
public class ResultVO {

    /** 错误码 */
    private Integer code;

    /** 返回的信息*/
    private String msg;

    /** 返回的对象 */
    private Object data;

}

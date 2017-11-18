package com.ximo.springbootsellmaster.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * Created by 朱文赵
 * 2017/9/9
 */
@Data
@AllArgsConstructor
public class ResultVO implements Serializable{

    private static final long serialVersionUID = -1111249638091519785L;

    /** 错误码 */
    private Integer code;

    /** 返回的信息*/
    private String msg;

    /** 返回的对象 */
    private Object data;

}

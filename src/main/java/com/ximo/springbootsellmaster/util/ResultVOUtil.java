package com.ximo.springbootsellmaster.util;

import com.ximo.springbootsellmaster.vo.ResultVO;

/**
 * 放回结果工具包
 * Created by 朱文赵
 * 2017/9/10
 */
public class ResultVOUtil {

    /**
     * 成功带参数
     * @param data
     * @return
     */
    public static ResultVO success(Object data){
        return new ResultVO(0, "成功", data);
    }

    /**
     * 成功不带参数
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * 错误是的方法
     * @param code 错误码
     * @param msg 错误信息
     * @return
     */
    public static ResultVO error(Integer code, String msg){
        return new ResultVO(code, msg, null);
    }



}

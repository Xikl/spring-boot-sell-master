package com.ximo.springbootsellmaster.constant;

/**
 * @description: redis 常量
 * @author: 朱文赵
 * @date: 2017/11/14
 */
public interface RedisConstant {

    /** 过期时间 两小时*/
    Integer EXPIRE =  7200;

    /** token前缀*/
    String TOKEN_PREFIX = "token_%s";

}

package com.ximo.springbootsellmaster.util;

import java.util.Random;

/**
 * 生成随机数
 * Created by 朱文赵
 * 2017/9/11
 */
public class KeyUtil {

    /**
     * 生成唯一的主键，采用当前时间+随机数的格式，多线程锁定
     * synchronized 多线程的关键字
     * @return 数据库的主键（String类型）
     */
    public static synchronized String generateUniqueKey(){
        //随机数
        Random random = new Random();
        //生成六位数的随机数
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}

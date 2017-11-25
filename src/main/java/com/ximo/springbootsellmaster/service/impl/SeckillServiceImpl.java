package com.ximo.springbootsellmaster.service.impl;

import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ximo.springbootsellmaster.constant.SeckillConstant.TIME_OUT;

/**
 * @description: 假装自己有秒杀的类
 * @author: 朱文赵
 * @date: 2017/11/18
 */
@Service
public class SeckillServiceImpl {

    @Autowired
    private RedisLock redisLock;

    /**
     * 模拟测试分布式锁的实现
     * @param productId
     */
    public void seckill(String productId){
        long time = System.currentTimeMillis() + TIME_OUT;
        if (!redisLock.lock(productId, String.valueOf(time))){
            throw new SellException(ResultEnums.SECKILL_ERROR);
        }
        //do something
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisLock.unlock(productId, String.valueOf(time));
    }




}

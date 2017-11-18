package com.ximo.springbootsellmaster.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 朱文赵
 * @date: 2017/11/18
 */
@Component
@Slf4j
public class RedisLock {

    /** 只对键值 都是String类型的*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 基于redis加锁
     * @param key
     * @param value 当前时间 和超时时间
     */
    public boolean lock(String key, String value){
        /* setnx 在java中改了一个名字setIfAbsent */
        if(stringRedisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }
        /*获得当前的值*/
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        /*如果锁过期*/
        if(StringUtils.isNotEmpty(currentValue)
                && Long.valueOf(currentValue) < System.currentTimeMillis()){
            //获得上一个锁的时间
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            /*不为空, 且等于当前的值*/
            if(StringUtils.isNotEmpty(oldValue)
                    && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 基于redis实现解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(StringUtils.isNotEmpty(currentValue)
                    && currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常, e={}", e);
        }
    }
}

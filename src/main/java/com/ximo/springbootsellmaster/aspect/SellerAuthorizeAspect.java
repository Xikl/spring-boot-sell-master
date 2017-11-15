package com.ximo.springbootsellmaster.aspect;

import com.ximo.springbootsellmaster.constant.CookieConstant;
import com.ximo.springbootsellmaster.constant.RedisConstant;
import com.ximo.springbootsellmaster.exception.SellerAuthorizeException;
import com.ximo.springbootsellmaster.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 卖家授权控制
 * @author: 朱文赵
 * @date: 2017/11/15
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 拦截所有的Seller开的Controller 排除 SellerUserController
     */
    @Pointcut("execution(public * com.ximo.springbootsellmaster.controller.seller.Seller*.*(..))" +
        "&& !execution(public * com.ximo.springbootsellmaster.controller.seller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        /*获得request请求*/
        HttpServletRequest request = requestAttributes.getRequest();
        /*查询cookie*/
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("【登录校验】cookie中没有token");
            throw new SellerAuthorizeException();
        }

        /*去redis中查询*/
        String tokenValue = stringRedisTemplate.opsForValue()
                .get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】redis中没有tokenValue");
            throw new SellerAuthorizeException();
        }

    }




}

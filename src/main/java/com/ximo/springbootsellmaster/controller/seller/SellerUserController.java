package com.ximo.springbootsellmaster.controller.seller;

import ch.qos.logback.core.util.TimeUtil;
import com.ximo.springbootsellmaster.config.ProjectUrlConfig;
import com.ximo.springbootsellmaster.constant.CookieConstant;
import com.ximo.springbootsellmaster.constant.RedisConstant;
import com.ximo.springbootsellmaster.domain.SellerInfo;
import com.ximo.springbootsellmaster.enums.ResultEnums;
import com.ximo.springbootsellmaster.service.SellerService;
import com.ximo.springbootsellmaster.util.CookieUtil;
import com.ximo.springbootsellmaster.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description: 卖家用户controller
 * @author: 朱文赵
 * @date: 2017/11/14
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    /** 注入 string 操作的 类*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map){
        //1.openid 和 数据库进行匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            /*跳转到错误页面*/
            return ModelUtil.error(map, ResultEnums.LOGIN_FAIL.getMsg(), ModelUtil.ORDER_DEFAULT_URL);
        }

        //2.设置token到redis
        String token = UUID.randomUUID().toString();
        /*过期时间*/
        Integer expire = RedisConstant.EXPIRE;
        /*使用String format属性设置 前缀+token 时间格式为 timeUnit. 秒*/
        stringRedisTemplate.opsForValue().set((String.format(RedisConstant.TOKEN_PREFIX, token)),
                openid, expire, TimeUnit.SECONDS);

        //3.设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + ModelUtil.ORDER_DEFAULT_URL);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
        //从cookie 中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null){
            //2 清除redis
            stringRedisTemplate.opsForValue().getOperations()
                    .delete((String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue())));
            //3 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        return ModelUtil.success(map, ResultEnums.LOGIN_SUCCESS.getMsg(), ModelUtil.ORDER_DEFAULT_URL);
    }

}

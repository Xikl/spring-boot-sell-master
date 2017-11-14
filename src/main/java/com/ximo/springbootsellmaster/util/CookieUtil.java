package com.ximo.springbootsellmaster.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: cookie工具类
 * @author: 朱文赵
 * @date: 2017/11/14
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response HttpServletResponse
     * @param name  cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


}

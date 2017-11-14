package com.ximo.springbootsellmaster.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 获得cookie的值
     * @param request HttpServletRequest
     * @param name cookie的名字
     * @return cookie
     */
    public static Cookie get(HttpServletRequest request, String name){
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    /**
     * 读取cookie  -> map<String, Cookie>
     * @param request HttpServletRequest
     * @return cookieMap
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[]  cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}

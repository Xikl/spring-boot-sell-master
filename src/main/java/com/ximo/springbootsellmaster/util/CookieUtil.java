package com.ximo.springbootsellmaster.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱文赵
 * @date 2018/4/23 10:15
 * @description cookie工具类 只能在controller中使用
 */
public class CookieUtil {

    /** 私有构造*/
    private CookieUtil() {
    }

    /**
     * 设置cookie
     *
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void set(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response().addCookie(cookie);
    }


    /**
     * 获得cookie的值
     *
     * @param name    cookie的名字
     * @return cookie
     */
    public static Cookie get(String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request());
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }
        return null;
    }

    /**
     * 读取cookie  -> map<String, Cookie>
     *
     * @param request HttpServletRequest
     * @return cookieMap
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>(16);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 删除cookie
     *
     * @param name 要删除的cookie名字
     */
    public static void delete(String name) {
        Cookie cookie = get(name);
        assert cookie != null;
        cookie.setMaxAge(0);
        cookie.setValue(null);
        cookie.setPath("/");
        response().addCookie(cookie);
    }

    /**
     * 获得response
     *
     * @return response
     */
    private static HttpServletResponse response() {
        return servletRequestAttributes().getResponse();
    }

    /**
     * 获得request
     *
     * @return request
     */
    private static HttpServletRequest request() {
        return servletRequestAttributes().getRequest();
    }

    /**
     * 获得 servletRequestAttributes 对象
     *
     * @return servletRequestAttributes
     */
    private static ServletRequestAttributes servletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

}

package com.ximo.springbootsellmaster.util;

import com.ximo.springbootsellmaster.exception.SellException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 模板返回util信息
 * Created by 朱文赵
 * 2017/9/25
 */
public class ModelUtil{

    public static final String ORDER_DEFAULT_URL = "/sell/seller/order/list";
    public static final String PRODUCT_DEFAULT_URL = "/sell/seller/product/list";
    public static final String PRODUCT_INDEX_URL = "/sell/seller/product/index";
    public static final String CATEGORY_INDEX_URL = "/sell/seller/category/index";
    public static final String CATEGORY_DEFAULT_URL = "/sell/seller/category/list";

    private static final String ERROR_PAGE = "common/error";
    private static final String SUCCESS_PAGE = "common/success";

    /**
     * 错误信息返回工具
     * @param model 视图类
     * @param e
     */
    public static String error(Model model, SellException e){
        defaultAdd(model, e.getMessage(), ORDER_DEFAULT_URL);
        return ERROR_PAGE;
    }

    /**
     * 成功的时候返回的界面
     * @param model
     * @param msg
     * @return
     */
    public static String success(Model model, String msg){
        defaultAdd(model, msg, ORDER_DEFAULT_URL);
        return SUCCESS_PAGE;
    }

    /**
     * 为model私有的添加模型方法 订单模块
     * @param model
     * @param msg
     */
    private static void defaultAdd(Model model, String msg, String url){
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
    }

    /**
     * 为map类型写的私有方法 产品模块
     * @param map
     * @param msg
     */
    private static void defaultAdd(Map<String, Object> map, String msg, String url){
        map.put("msg", msg);
        map.put("url", url);
    }

    /**
     * 产品修改成功，页面回调方法
     * @param map
     * @param msg
     * @return
     */
    public static ModelAndView success(Map<String, Object> map, String msg, String url){
        defaultAdd(map, msg, url);
        return new ModelAndView(SUCCESS_PAGE, map);
    }

    /**
     * 产品修改失败，页面回调方法
     * @param map
     * @param msg
     * @return
     */
    public static ModelAndView error(Map<String, Object> map, String msg, String url){
        defaultAdd(map, msg, url);
        return new ModelAndView(ERROR_PAGE, map);
    }

}

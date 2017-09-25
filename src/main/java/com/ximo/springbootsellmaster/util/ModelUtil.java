package com.ximo.springbootsellmaster.util;

import com.ximo.springbootsellmaster.exception.SellException;
import org.springframework.ui.Model;

/**
 * 模板返回util信息
 * Created by 朱文赵
 * 2017/9/25
 */
public class ModelUtil{

    private static final String DEFAULT_URL = "/sell/seller/order/list";
    private static final String ERROR_PAGE = "common/error";
    private static final String SUCCESS_PAGE = "common/success";

    /**
     * 错误信息返回工具
     * @param model 视图类
     * @param e
     */
    public static String error(Model model, SellException e){
        defaultAdd(model, e.getMessage());
        return ERROR_PAGE;
    }

    /**
     * 成功的时候返回的界面
     * @param model
     * @param msg
     * @return
     */
    public static String success(Model model, String msg){
        defaultAdd(model, msg);
        return SUCCESS_PAGE;
    }

    /**
     * 私有的添加模型方法
     * @param model
     * @param msg
     */
    private static void defaultAdd(Model model, String msg){
        model.addAttribute("msg", msg);
        model.addAttribute("url", DEFAULT_URL);
    }



}

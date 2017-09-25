package com.ximo.springbootsellmaster.util;

import com.ximo.springbootsellmaster.exception.SellException;
import org.springframework.ui.Model;

/**
 * 模板返回util信息
 * Created by 朱文赵
 * 2017/9/25
 */
public class ModelUtil{

    /**
     * 错误信息返回工具
     * @param model 视图类
     * @param e
     */
    public static String error(Model model, SellException e){
        model.addAttribute("msg", e.getMessage());
        model.addAttribute("url", "/sell/seller/order/list");
        return "common/error";
    }


}
